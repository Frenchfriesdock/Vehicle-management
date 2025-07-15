package com.hosiky.carUser.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CarUserLoginVo implements Serializable {

    private Integer carUserId;
    private String carUserName;
    private String token;
}
