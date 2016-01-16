package com.exmyth.attendance.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exmyth.attendance.model.Account;

@RunWith(SpringJUnit4ClassRunner.class) // extends SpringJUnit4ClassRunner
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class AccountServiceTest {
	@Autowired
	private AccountService accountService;

	@Test
	public void test1() {
		Account account = accountService.getAccountById(1);
		System.out.println(account.getNickname());
	}
}
