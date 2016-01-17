package com.exmyth.attendance.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import com.exmyth.attendance.model.UserChatCommand;

@Controller
public class CoordinationController {

    //用于转发数据(sendTo)
    private SimpMessagingTemplate template;

    @Autowired
    public CoordinationController(SimpMessagingTemplate t) {
        template = t;
    }

    /**
     * WebSocket聊天的相应接收方法和转发方法
     *
     * @param userChat 关于用户聊天的各个信息
     */
    @MessageMapping("/coordination")
    @SendTo("/userChat")
    public void userChat(UserChatCommand userChat) {
        //找到需要发送的地址
        String dest = "/userChat/chat" + userChat.getCoordinationId();
        /*
        try {
            userChat.setName(URLDecoder.decode(userChat.getName(), "utf-8"));
            userChat.setChatContent(URLDecoder.decode(userChat.getChatContent(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        */
        //发送用户的聊天记录
        this.template.convertAndSend(dest, userChat);
    }


    /**
     * 初始化，初始化文章和聊天记录
     *
     * @param coordinationId 协同空间的id
     */
    @SubscribeMapping("/initDocument/{coordinationId}/{fileId}")
    public Map<String,Object> initDocument(@DestinationVariable("coordinationId") int coordinationId, @DestinationVariable("fileId") int fileId) {
        System.out.println("------------新用户进入文档，协同空间初始化---------");
        Map<String, Object> document = new HashMap<String, Object>();
        document.put("chat","Hello");
        return document;
    }
}