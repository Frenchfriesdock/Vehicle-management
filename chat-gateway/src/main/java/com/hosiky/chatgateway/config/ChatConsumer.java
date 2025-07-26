package com.hosiky.chatgateway.config;

import com.hosiky.chatgateway.controller.ChatWebSocketServer;
import com.hosiky.chatgateway.domain.ChatMsg;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(
        topic = "temp_chat_msg",
        consumerGroup = "${rocketmq.consumer.group}")
public class ChatConsumer implements RocketMQListener<ChatMsg> {

    @Override
    public void onMessage(ChatMsg msg) {
        // 推给目标用户
        ChatWebSocketServer.sendToUser(msg.getToUserId(), msg);

    }
}