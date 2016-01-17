package com.exmyth.attendance.controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exmyth.attendance.model.ResultData;
  
@Controller
@RequestMapping
public class ChatAction {
  
    private static final Log log = LogFactory.getLog(ChatAction.class);
  
    @MessageMapping("/hello")
    @SendTo("/tweet/fuck")
    public ResultData chat(String message) throws Exception {
        long time = System.currentTimeMillis();
        log.info(time+":"+message);
        return new ResultData(time,true,message);
    }
  
}