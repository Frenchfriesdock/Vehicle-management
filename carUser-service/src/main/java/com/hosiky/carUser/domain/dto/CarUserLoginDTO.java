package com.hosiky.carUser.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CarUserLoginDTO implements Serializable {

        private Integer carUserId;

        private String password;

        private String carUserName;

}
