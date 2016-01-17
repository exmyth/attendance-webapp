package com.exmyth.attendance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exmyth.attendance.dao.AccountMapper;
import com.exmyth.attendance.model.Account;
import com.exmyth.attendance.service.AccountService;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	private AccountMapper accountMapper;

	@Autowired
	public void setAccountMapper(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}

	@Override
	public Account getAccountById(long id) {
		return accountMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Account> getAll() {
		return accountMapper.getAll();
	}

	@Override
	public List<Account> getAll2() {
		return accountMapper.getAll2();
	}
	
	@Override
	public List<Account> getAll3() {
		return accountMapper.getAll3();
	}
	
	@Override
	public List<Account> getAll4() {
		return accountMapper.getAll4();
	}
}
