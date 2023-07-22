package com.hxyy.ps.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Psword)表实体类
 *
 * @author makejava
 * @since 2023-05-30 15:33:28
 */
@Data
@TableName("psword")
public class Psword  {
    @TableId
    private String id;

    private String qqId;
    
    private String theme;
    
    private String psword;
    
    private String gmtCreat;

}

