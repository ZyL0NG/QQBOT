package com.hxyy.ps.controller;


import com.hxyy.ps.entity.Message;
import com.hxyy.ps.service.PswordService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Psword)表控制层
 *
 * @author makejava
 * @since 2023-05-30 15:33:26
 */
@RestController
@RequestMapping("psword")
public class PswordController {
    @Autowired
    PswordService pswordService;

    @PostMapping("/register")
    public Message insert(@RequestBody Message message){
        pswordService.recordMs(message);
        return message;
    }
    @PostMapping("/check")
    public Message select(@RequestBody Message message){
        pswordService.check(message);
        return message;
    }
    @PostMapping("/checkall")
    public Message selectall(@RequestBody Message message){
        pswordService.checkall(message);
        return message;
    }

}

