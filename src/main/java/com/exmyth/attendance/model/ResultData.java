package com.exmyth.attendance.model;

public class ResultData {
	private long time;
	private boolean success;
	private String message;
	public ResultData(long time, boolean success, String message) {
		super();
		this.time = time;
		this.success = success;
		this.message = message;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
