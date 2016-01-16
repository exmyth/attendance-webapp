package com.exmyth.attendance.service;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.exmyth.attendance.model.Account;

@RunWith(SpringJUnit4ClassRunner.class) // extends SpringJUnit4ClassRunner
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class AccountServiceTest {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AccountServiceTest.class);

	@Autowired
	private AccountService accountService;

	@Test
	public void test1() {
		Account account = accountService.getAccountById(1);
		logger.info(JSON.toJSONStringWithDateFormat(account,"yyyy-MM-dd HH:mm:dd"));
	}
}
