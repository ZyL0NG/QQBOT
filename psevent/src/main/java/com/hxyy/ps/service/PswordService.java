package com.hxyy.ps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hxyy.ps.entity.Message;
import com.hxyy.ps.entity.Psword;



/**
 * (Psword)表服务接口
 *
 * @author makejava
 * @since 2023-05-30 15:33:29
 */
public interface PswordService extends IService<Psword> {
    boolean recordMs(Message message);

    boolean check(Message message);

    boolean checkall(Message message);
}

