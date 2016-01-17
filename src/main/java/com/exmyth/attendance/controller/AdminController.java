package com.exmyth.attendance.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.exmyth.attendance.service.WebSocketService;
import com.exmyth.attendance.websocket.handler.SystemWebSocketHandler;

@Controller
public class AdminController {
 
    static Logger logger = Logger.getLogger(AdminController.class);
 
    @Autowired(required = false)
    private WebSocketService adminService;
 
    @Bean
    public SystemWebSocketHandler systemWebSocketHandler() {
        return new SystemWebSocketHandler();
    }
 
 
    @RequestMapping("/auditing")
    @ResponseBody
    public String auditing(HttpServletRequest request){
        //无关代码都省略了
        int unReadNewsCount = adminService.getUnreadNews("admin");
        systemWebSocketHandler().sendMessageToUser("admin", new TextMessage(unReadNewsCount + ""));
        return "SUCCESS";
    }
}