package com.hxyy.listen.event;



import com.hxyy.listen.entity.Message;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class ClientToFunc {

    protected static ConcurrentHashMap<String, Queue<Message>> map=new ConcurrentHashMap<>();

    //按 QQID 构建消息队列
    public void ListById(Message message){
        if(!map.containsKey(message.getUser_id())){
            Thread thread = new Thread(() -> {
                try {
                    Queue<Message> queue=new LinkedList<>();
                    queue.offer(message);
                    map.put(message.getUser_id(), queue);
                    while(!map.get(message.getUser_id()).isEmpty()){
                        solution(message.getUser_id());
                    }
                    // 当前用户对应的队列为空就删除用户，结束线程
                    map.remove(message.getUser_id());
                    Thread.currentThread().interrupt();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
        }else {
            Queue<Message> list=map.get(message.getUser_id());
            list.offer(message);
        }
    }

    //处理消息队列
    public void solution(String id) throws InterruptedException {
        Match match=new Match();
        Repliy repliy=new Repliy();

        //匹配内容返回对应url，并且对url做不为空判断
        String port=match.toFunction(map.get(id).peek(),id);

        if(port!=null) {
            accessController(map.get(id).peek(), port);
            map.get(id).poll();
        }else if(map.get(id).size()==0){
            repliy.RespondById(id, "我等太久了，先撤了");
        }else {
            repliy.RespondByMs(map.get(id).peek(),"如果您想记录数据，请输入：1，想查询数据请输入：2");
            map.get(id).poll();
        }
    }


    public boolean accessController(Message message,String port) {

        //通过url访问对应接口
        String url = "http://108.174.60.150:" + port;
        Repliy repliy = new Repliy();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Message> response = null;
        Message responseBody;
        try {
            // 创建HttpHeaders对象，并设置Content-Type为application/json
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // 创建HttpEntity对象，封装Message对象和请求头
            HttpEntity<Message> entity = new HttpEntity<>(message, headers);

            // 发送POST请求，并将Message对象作为请求体传递
            response = restTemplate.exchange(url, HttpMethod.POST, entity, Message.class);
            responseBody = response.getBody();

            // 处理响应结果，根据具体需求进行操作
            repliy.RespondByMs(message, responseBody.getMessage());
            return true;
        } catch (RestClientException e) {
            repliy.RespondByMs(message, "出错了，去问问花溪悠悠怎么回事？");
            return false;
        }
    }
}
