package com.exmyth.attendance.model;

/**
 * 封装用户的聊天内容
 */
public class Message {
	private String user;
	private String date;
	private String content;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}