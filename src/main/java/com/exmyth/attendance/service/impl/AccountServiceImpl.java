package com.exmyth.attendance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exmyth.attendance.dao.AccountMapper;
import com.exmyth.attendance.model.Account;
import com.exmyth.attendance.service.AccountService;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountMapper accountMapper;
	
	@Override
	public Account getAccountById(long id) {
		return accountMapper.selectByPrimaryKey(id);
	}
}
