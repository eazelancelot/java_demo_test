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

	private String pwdPattern = "[\\S]{8,16}";//�����w�S���ܤ֭n���@��
	
	/*
	 * ���w�S��Ÿ��ܤ֭n���@��
	 * \p ��ܤ�諸�r����ƬY�دS��
	 * Punct  ��� ���I�Ÿ� !"#$%&��()*+,-./:;<=>?@[]^_`{|}~
	 */
	private String pwdPattern2 = "^(?=.*[\\p{Punct}])[\\S]{8,12}$";
	
	private String pwdPattern21 = "^(?=.*[\\S\\W])[\\S]{8,12}$";
	
	private String pwdPattern3 = "^(?=.*[\\p{Punct}])(?!.*[\\s\\t\\r\\n\\f][\\p{Print}]){8,12}$";

	@Autowired
	private BankDao bankDao;

	@Override
	public void addInfo(Bank bank) {
		// �ˬd�b��
		checkAccount(bank);
		// �ˬd�K�X
		checkPwd(bank);
		// �ˬd�l�B: ���ର�t��
		if (bank.getAmount() < 0) {
			System.out.println("Amount is error!!");
			return;
		}
		// �s�W��ƨ� bank �o�i��
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
		// �ˬd: 1. �b������ 3~8; 2. �b�����঳���󪺪ť�; 3. �b�����঳�S��Ÿ�
		if (!account.matches(accountPattern)) {
			System.out.println("Account is error!!");
			return;
		}
	}

	private void checkPwd(Bank bank) {
		// �ˬd�K�X: 1. �D null�F2. ������ťթΦ��ť� 3. ���� 8~16 �F4. �]�t�@�ǯS��r��
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
			return new BankResponse(new Bank(), "�b���αK�X���~!!");
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
			return new BankResponse(new Bank(), "��Ƥ��s�b!!");
		}
		int oldAmount = resBank.getAmount();
		int depositAmount = bank.getAmount();
		// �쥻���B + �s�ڪ��B
		int newAmount = oldAmount + depositAmount;
		// �]�w�s�ګ᪺���B
		resBank.setAmount(newAmount);
//		Bank newBank = bankDao.save(resBank);
//		return newBank;
		return new BankResponse(bankDao.save(resBank), "�s�ڦ��\!!");
	}

	@Override
	public BankResponse withdraw(Bank bank) {
		if (bank == null 
				|| !StringUtils.hasText(bank.getAccount()) 
				|| !StringUtils.hasText(bank.getPwd())
				|| bank.getAmount() <= 0) {
			return new BankResponse(new Bank(), "�b���αK�X���~!!");
		}
		Bank resBank = bankDao.findByAccountAndPwd(bank.getAccount(), bank.getPwd());
		if(resBank == null) {
			return new BankResponse(new Bank(), "��Ƥ��s�b!!");
		}
		int oldAmount = resBank.getAmount();
		int withdrawAmount = bank.getAmount();
		//�P�_���ڪ��B����j��즳���B
		if (withdrawAmount > oldAmount) {
			return new BankResponse(new Bank(), "�l�B����!!");
		}
		// �쥻���B - ���ڪ��B
		int newAmount = oldAmount - withdrawAmount;
		// �]�w���ګ᪺���B
		resBank.setAmount(newAmount);
//		Bank newBank = bankDao.save(resBank);
//		return newBank;
		return new BankResponse(bankDao.save(resBank), "���ڦ��\!!");
	}
}
