package com.example.java_demo_test.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.respository.BankDao;
import com.example.java_demo_test.service.ifs.BankService;
import com.example.java_demo_test.vo.BankResponse;

@Service
public class BankServiceImpl implements BankService {

	private String accountPattern = "\\w{3,8}";

	private String pwdPattern = "[\\S]{8,16}";//不限定特殊號至少要有一個
	
	/*
	 * 限定特殊符號至少要有一個
	 * \p 表示比對的字元具備某種特性
	 * Punct  表示 標點符號 !"#$%&’()*+,-./:;<=>?@[]^_`{|}~
	 */
	private String pwdPattern2 = "^(?=.*[\\p{Punct}])[\\S]{8,12}$";
	
	private String pwdPattern21 = "^(?=.*[\\S\\W])[\\S]{8,12}$";
	
	private String pwdPattern3 = "^(?=.*[\\p{Punct}])(?!.*[\\s\\t\\r\\n\\f][\\p{Print}]){8,12}$";

	@Autowired
	private BankDao bankDao;

	@Override
	public void addInfo(Bank bank) {
		// 檢查帳號
		checkAccount(bank);
		// 檢查密碼
		checkPwd(bank);
		// 檢查餘額: 不能為負數
		if (bank.getAmount() < 0) {
			System.out.println("Amount is error!!");
			return;
		}
		// 新增資料到 bank 這張表
		bankDao.save(bank);
	}

	private void checkAccount(Bank bank) {
		if (bank == null) {
			System.out.println("Bank is null!!");
			return;
		}
		String account = bank.getAccount();
		if (account == null) {
			System.out.println("Account is null!!");
			return;
		}
		// 檢查: 1. 帳號長度 3~8; 2. 帳號不能有任何的空白; 3. 帳號不能有特殊符號
		if (!account.matches(accountPattern)) {
			System.out.println("Account is error!!");
			return;
		}
	}

	private void checkPwd(Bank bank) {
		// 檢查密碼: 1. 非 null；2. 不能全空白或有空白 3. 長度 8~16 ；4. 包含一些特殊字元
		String pwd = bank.getPwd();
		if (pwd == null || !pwd.matches(pwdPattern)) {
			System.out.println("Password is error!!");
			return;
		}
	}

	@Override
	public Bank getAmountById(String id) {
		if (!StringUtils.hasText(id)) {
			return new Bank();
		}
		Optional<Bank> op = bankDao.findById(id);

//		if(!op.isPresent()) {
//			return new Bank();
//		}
//		return op.get();

//		Bank bank = op.orElse(new Bank());
//		return bank;
		return op.orElse(new Bank());
	}

	@Override
	public BankResponse deposit(Bank bank) {
//		if (bank == null) {
//			return new Bank();
//		}
//		if (!StringUtils.hasText(bank.getAccount())) {
//			return new Bank();
//		}
//
//		if (!StringUtils.hasText(bank.getPwd())) {
//			return new Bank();
//		}
//
//		if (bank.getAmount() <= 0) {
//			return new Bank();
//		}
		// =============================
		if (bank == null 
				|| !StringUtils.hasText(bank.getAccount()) 
				|| !StringUtils.hasText(bank.getPwd())
				|| bank.getAmount() <= 0) {
			return new BankResponse(new Bank(), "帳號或密碼錯誤!!");
		}
//		Optional<Bank> op = bankDao.findById(bank.getAccount());
//		if(!op.isPresent()) {
//			return new Bank();
//		}
//		Bank resBank = op.get();
//		if(!resBank.getPwd().equals(bank.getPwd())) {
//			return new Bank();
//		}	
		
		Bank resBank = bankDao.findByAccountAndPwd(bank.getAccount(), bank.getPwd());
		if(resBank == null) {
			return new BankResponse(new Bank(), "資料不存在!!");
		}
		int oldAmount = resBank.getAmount();
		int depositAmount = bank.getAmount();
		// 原本金額 + 存款金額
		int newAmount = oldAmount + depositAmount;
		// 設定存款後的金額
		resBank.setAmount(newAmount);
//		Bank newBank = bankDao.save(resBank);
//		return newBank;
		return new BankResponse(bankDao.save(resBank), "存款成功!!");
	}

	@Override
	public BankResponse withdraw(Bank bank) {
		if (bank == null 
				|| !StringUtils.hasText(bank.getAccount()) 
				|| !StringUtils.hasText(bank.getPwd())
				|| bank.getAmount() <= 0) {
			return new BankResponse(new Bank(), "帳號或密碼錯誤!!");
		}
		Bank resBank = bankDao.findByAccountAndPwd(bank.getAccount(), bank.getPwd());
		if(resBank == null) {
			return new BankResponse(new Bank(), "資料不存在!!");
		}
		int oldAmount = resBank.getAmount();
		int withdrawAmount = bank.getAmount();
		//判斷提款金額不能大於原有金額
		if (withdrawAmount > oldAmount) {
			return new BankResponse(new Bank(), "餘額不足!!");
		}
		// 原本金額 - 提款金額
		int newAmount = oldAmount - withdrawAmount;
		// 設定提款後的金額
		resBank.setAmount(newAmount);
//		Bank newBank = bankDao.save(resBank);
//		return newBank;
		return new BankResponse(bankDao.save(resBank), "提款成功!!");
	}
}
