package com.hxyy.listen.client;
import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import com.alibaba.fastjson.JSONObject;


import com.hxyy.listen.entity.Message;
import com.hxyy.listen.event.ClientToFunc;

import java.io.IOException;
import java.net.URI;

@ClientEndpoint
public class Client {
    private Session session;
    private static Client INSTENCE;
    private Client(String url) throws DeploymentException, IOException {
        session= ContainerProvider.getWebSocketContainer().connectToServer(this, URI.create(url));
    }
    public synchronized static boolean connect(String url){
        try {
            INSTENCE=new Client(url);
            return true;
        } catch (DeploymentException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }
    @OnOpen
    public void onOpen(Session session){
        System.out.println("连接成功");
    }

    @OnMessage
    public void onMessage(String json){
        Message message= JSONObject.parseObject(json, Message.class);
        if("message".equals(message.getPost_type())) {
            ClientToFunc clientToFunc=new ClientToFunc();
            clientToFunc.ListById(message);
        }
    }

    @OnClose
    public void onClose(Session session){
        System.out.println("连接关闭");
    }

    @OnError
    public void onError(Session session,Throwable throwable){
        System.out.println("连接发生异常");
    }

    public static void sendMessage(String json){
        Client.INSTENCE.session.getAsyncRemote().sendText(json);
    }


}
