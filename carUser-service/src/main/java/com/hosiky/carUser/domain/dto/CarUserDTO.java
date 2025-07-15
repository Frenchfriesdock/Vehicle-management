package com.hosiky.carUser.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CarUserDTO implements Serializable {

    private String carUserId;

    private String carUserName;
    private String contactName;
    private String contactPhone;
    private String address;

    private String email;//用户头像地址
    private LocalDateTime updateTime;//更新时间
}
