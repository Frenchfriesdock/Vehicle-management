package com.hosiky.chatgateway.config;

import com.hosiky.chatgateway.domain.ChatMsg;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatProducer {
    @Autowired
    private RocketMQTemplate rocketTemplate;

    public void send(ChatMsg msg) {
        rocketTemplate.convertAndSend("temp_chat_msg", msg);
    }
}