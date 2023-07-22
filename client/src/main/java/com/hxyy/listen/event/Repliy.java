package com.hxyy.listen.event;


import com.alibaba.fastjson.JSONObject;
import com.hxyy.listen.entity.Message;
import com.hxyy.listen.entity.Params;
import com.hxyy.listen.entity.Request;

import static com.hxyy.listen.client.Client.sendMessage;


public class Repliy {
    public boolean RespondByMs(Message message, String content){
        Params params = new Params();
        params.setMessage_type(message.getMessage_type());
        params.setUser_id(message.getUser_id());
        params.setGroup_id(null);
        params.setMessage(content);
        params.setAuto_escape(true);

        Request paramsRequest = new Request();
        paramsRequest.setAction("send_msg");
        paramsRequest.setParams(params);

        try {
            sendMessage(JSONObject.toJSONString(paramsRequest));
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public boolean RespondById(String id, String content){
        Params params = new Params();
        params.setMessage_type("private");
        params.setUser_id(id);
        params.setGroup_id(null);
        params.setMessage(content);
        params.setAuto_escape(true);

        Request paramsRequest = new Request();
        paramsRequest.setAction("send_msg");
        paramsRequest.setParams(params);

        try {
            sendMessage(JSONObject.toJSONString(paramsRequest));
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
