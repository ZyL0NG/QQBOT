package com.hxyy.listen.entity;

import lombok.Data;

@Data
public class Request {

    //发送消息的json结构
    private String action;
    private Params params;
    private String echo;

}
