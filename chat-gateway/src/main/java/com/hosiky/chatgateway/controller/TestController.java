package com.hosiky.chatgateway.controller;

import com.hosiky.chatgateway.config.ChatProducer;
import com.hosiky.chatgateway.domain.ChatMsg;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final ChatProducer chatProducer;

    @GetMapping("/send")
    public String send(@RequestParam Long from,
                       @RequestParam Long to,
                       @RequestParam String content) {
        ChatMsg msg = new ChatMsg();
        msg.setFromUserId(from);
        msg.setToUserId(to);
        msg.setContent(content);
        chatProducer.send(msg);
        return "ok";
    }
}
