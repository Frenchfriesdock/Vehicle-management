package com.hosiky.chatgateway.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMsg {
    private Long fromUserId;
    private Long toUserId;
    private String role;
    private String content;
    private Long sessionId;
    private LocalDateTime ts = LocalDateTime.now();
}
