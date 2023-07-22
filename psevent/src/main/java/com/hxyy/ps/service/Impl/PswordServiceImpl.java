package com.hxyy.ps.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxyy.ps.entity.Message;
import com.hxyy.ps.entity.Psword;
import com.hxyy.ps.mapper.PswordMapper;
import com.hxyy.ps.service.PswordService;



import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * (Psword)表服务实现类
 *
 * @author makejava
 * @since 2023-05-30 15:33:29
 */
@Service("pswordService")
public class PswordServiceImpl extends ServiceImpl<PswordMapper, Psword> implements PswordService {
    //向数据库插入记录
    public boolean recordMs(Message message){

        //构造插入数据库的实体对象
        Psword psMessage = new Psword();
        psMessage.setQqId(message.getUser_id());
        psMessage.setId(UUID.randomUUID().toString());

        String[] parts = message.getMessage().split(" ");
        if(parts.length!=2) {
            message.setMessage("输入格式无效，请注意格式为:”关键字+空格+密码“，请重新输入“1");
            return false;
        }
        //根据输入语句格式：key:ps拆分
        psMessage.setTheme(parts[0]);
        psMessage.setPsword(parts[1]);
        baseMapper.insert(psMessage);
        message.setMessage("存储成功");
        return true;

    }

    @Override
    public boolean check(Message message) {
        QueryWrapper<Psword> wrapper=new QueryWrapper<>();
        wrapper.eq("qq_id",message.getUser_id())
                .eq("theme", message.getMessage());
        List<Psword> pswords = baseMapper.selectList(wrapper);
        if(pswords.isEmpty()) {
            message.setMessage("查询失败，请好好想想您存储了这个记录了吗？");
        } else {
            String temp = pswords.stream()
                    .map(Psword::getPsword)
                    .collect(Collectors.joining(", "));
            message.setMessage("关于" + message.getMessage() + "您储存了" + pswords.size() + "条记录，分别为:  "+ temp);
        }
        return true;
    }

    @Override
    public boolean checkall(Message message) {
        QueryWrapper<Psword> wrapper=new QueryWrapper<>();
        wrapper.eq("qq_id",message.getUser_id());
        List<Psword> pswords = baseMapper.selectList(wrapper);
        if(pswords.isEmpty()) {
            message.setMessage("您还未存储过信息");
        } else {
            String temp = pswords.stream()
                    .map(Psword::getPsword)
                    .collect(Collectors.joining(", "));
            message.setMessage("您存储的内容如下"+temp);
        }
        return true;
    }
}

