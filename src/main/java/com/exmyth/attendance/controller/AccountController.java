package com.exmyth.attendance.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exmyth.attendance.model.Account;
import com.exmyth.attendance.service.AccountService;

@Controller
@RequestMapping("/accountController")
public class AccountController {
	@Autowired
	private AccountService accountService;
	@RequestMapping("/home")
	public String home(long id,HttpServletRequest request) {
		Account account = accountService.getAccountById(id);
		request.setAttribute("account", account);
		return "home";
	}
}
