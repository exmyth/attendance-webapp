package com.exmyth.attendance.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.exmyth.attendance.model.Message;

/**
 * 处理聊天消息
 */
@Controller
@RequestMapping("/chatController")
public class ChatController {
	//存放所有的用户请求
	private final Map<String, DeferredResult<Message>> chatRequests = new ConcurrentHashMap<String, DeferredResult<Message>>();
	//时间格式化
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 
	 * 登录
	 * @param name 用户名
	 * @param session 会话
	 * @return 聊天室页面
	 */
	@RequestMapping(value = "/login")
	public String login(@RequestParam String name, HttpSession session){
		session.setAttribute("user", name);
		Message msg = new Message();
		msg.setUser("系统");
		msg.setDate(sdf.format(new Date()));
		msg.setContent(name + "已加入");
		//通知所有用户有人进入聊天室
		processMessage(msg);
		return "room";
	}
	/**
	 * 
	 * 
	 * 读取最新消息
	 * @param session 会话
	 * @return DeferredResult<Message>
	 */
	@RequestMapping(value = "/getMessages", method = RequestMethod.GET)
	@ResponseBody
	public DeferredResult<Message> getMessages(HttpSession session){
		//取出当前登录用户
		final String user = (String)session.getAttribute("user");
		//创建DeferredResult<Message>
		DeferredResult<Message> dr = new DeferredResult<Message>();
		//若用户不存在则直接返回，否则将其放入用户请求列表中然后返回
		if(null == user){
			return dr;
		}else{
			//当DeferredResult对客户端响应后将其从列表中移除
			dr.onCompletion(new Runnable() {
				@Override
				public void run() {
					// TODO 自动生成的方法存根
					chatRequests.remove(user);
				}
			});
			chatRequests.put(user, dr);
			return dr;
		}
	}
	/**
	 * 
	 * 接收客户端消息
	 * @param session 会话
	 * @param content 消息内容
	 * @return Map<String, String>
	 */
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> sendMessage(HttpSession session, @RequestParam String content){
		Message msg = new Message();
		msg.setContent(content);
		msg.setDate(sdf.format(new Date()));
		msg.setUser((String)session.getAttribute("user"));
		//发布消息给所有用户
		processMessage(msg);
		Map<String, String> map = new HashMap<String, String>(1);
		map.put("success", "true");
		return map;
	}
	/**
	 * 
	 * 退出聊天室
	 * @param session 会话
	 * @return Map<String, String>
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> logout(HttpSession session){
		Message msg = new Message();
		String user = (String)session.getAttribute("user");
		msg.setContent("已离开");
		msg.setDate(sdf.format(new Date()));
		msg.setUser(user);
		chatRequests.remove(user);
		//通知所有用户有人离开聊天室
		processMessage(msg);
		Map<String, String> map = new HashMap<String, String>(1);
		map.put("success", "true");
		return map;
	}
	/**
	 * 
	 * 将消息信息发布给所有在线用户
	 * @param msg 消息
	 */
	private void processMessage(Message msg){
		Set<String> keys = chatRequests.keySet();
		for(String key : keys){
			chatRequests.get(key).setResult(msg);
		}
	}
}