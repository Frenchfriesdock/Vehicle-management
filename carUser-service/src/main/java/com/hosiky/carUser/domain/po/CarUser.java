package com.hosiky.carUser.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@TableName("caruser")
public class CarUser implements Serializable {

    @TableId(value = "carUserId")
    private Integer carUserId;
    private String carUserName;
    private String password;
    private String image;

    @TableField("contact_name")
    private String contactName;
    @TableField("contact_phone")
    private String contactPhone;
    private String address;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
