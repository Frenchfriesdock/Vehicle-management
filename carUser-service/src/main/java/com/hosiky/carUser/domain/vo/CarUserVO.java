package com.hosiky.carUser.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CarUserVO implements Serializable {

    private String carUserId;
    private String carUserName;


    private String email;//邮箱
    private String image;//用户头像地址

    private String contactName;
    private String contactPhone;
    private String address;

    private LocalDateTime updateTime;//更新时间
}
