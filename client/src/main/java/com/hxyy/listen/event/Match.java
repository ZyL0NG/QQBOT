package com.hxyy.listen.event;


import com.hxyy.listen.entity.Message;

public class Match extends ClientToFunc {

    private static final long WAIT_TIME = 60 * 1000; // 等待时间1分钟

    public String toFunction(Message message, String id) {
        Repliy repliy=new Repliy();

        if("1".equals(message.getMessage())) {
            long startTime = System.currentTimeMillis();
            long endTime = startTime + WAIT_TIME;
            repliy.RespondByMs(message, "请输入格式为”关键字+空格(注意只有一个空格)+信息“的内容，此后您可以凭关键字向我查询对应信息");
            map.get(id).poll();

            while (map.get(id).isEmpty() && System.currentTimeMillis() < endTime) {
                // 等待1秒钟再进行下一次循环
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return null;
                }
                if (!map.get(id).isEmpty()) {
                    // map.get(id) 不为空，退出循环
                    return "8111/psword/register";
                }
            }

        } else if ("2".equals(message.getMessage())) {
            long startTime = System.currentTimeMillis();
            long endTime = startTime + WAIT_TIME;
            repliy.RespondByMs(message, "请输入关键字");
            map.get(id).poll();

            while (map.get(id).isEmpty() && System.currentTimeMillis() < endTime) {
                // 等待1秒钟再进行下一次循环
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return null;
                }
                if (!map.get(id).isEmpty()) {
                    // map.get(id) 不为空，退出循环
                    return "8111/psword/check";
                }
            }
        } else {
//            repliy.Respond(message,"如果您想记录数据，请输入：1，想查询数据请输入：2");
        }
        return null;
    }
}
