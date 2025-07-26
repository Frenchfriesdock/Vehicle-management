package com.hosiky.chatgateway.controller;

import com.hosiky.chatgateway.domain.ChatMsg;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/char/{userId}")
@Component
public class ChatWebSocketServer {
//    定义在线会话池
    private static final Map<Long, Session> ONLINE = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(@PathParam("userId") Long userId, Session session) {
        ONLINE.put(userId, session);
        System.out.println("用户" + userId + "上线，当前在线 " + ONLINE.size());
    }

    @OnClose
    public void onClose(@PathParam("userId") Long userId, Session session) {
        ONLINE.remove(userId);
        System.out.println("用户" + userId + "下线，当前在线 " + ONLINE.size());
    }

    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * 提供外部RocketMQ 消费者或测试用 调用，将消息推送给指定用户
     */

    public static void sendToUser(Long userId, ChatMsg msg) {
        Session session = ONLINE.get(userId);
        if (session != null && session.isOpen()) {
            session.getAsyncRemote().sendObject(msg);
        }
    }
}
