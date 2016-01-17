package com.exmyth.attendance.service;

import java.util.List;

import com.exmyth.attendance.model.Account;

public interface AccountService {
	public Account getAccountById(long id);
	public List<Account> getAll();
	public List<Account> getAll2();
}
