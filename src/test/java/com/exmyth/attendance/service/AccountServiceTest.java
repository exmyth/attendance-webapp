package com.exmyth.attendance.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exmyth.attendance.model.Account;
import com.exmyth.attendance.service.AccountService;

public class AccountServiceTest {
	
	private AccountService accountService;

	@Before
	public void before(){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-mybatis.xml"});
		accountService = (AccountService) context.getBean("accountService");
	}
	
	@Test
	public void test1(){
		Account account = accountService.getAccountById(1);
		System.out.println(account.getNickname());
	}
}
