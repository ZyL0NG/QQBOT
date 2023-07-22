package com.hxyy.listen.entity;

import lombok.Data;

@Data
public class Params {

    private String message_type;
    private String user_id;
    private String group_id;
    private String message;
    private boolean auto_escape;

}
