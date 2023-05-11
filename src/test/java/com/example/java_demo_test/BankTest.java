package com.example.java_demo_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.respository.BankDao;
import com.example.java_demo_test.service.ifs.BankService;
import com.example.java_demo_test.vo.BankResponse;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class BankTest {

	@Autowired
	private BankDao bankDao;

	@Autowired
	private BankService bankService;

	private String pattern = "\\w{3,8}";

	@Test
	public void addBankInfo() {
		System.out.println("===============");
		Bank bank = new Bank("A01", "AA123", 1000);
		// �s�W��ƨ� bank �o�i��
		bankDao.save(bank);
	}

	@Test
	public void addInfoTest() {
		Bank bank = new Bank("AA999", "AA123456@", 2000);
		bankService.addInfo(bank);
	}
	
	@Test
	public void getAmountByIdTest() {
		Bank bank = bankService.getAmountById("A01");
		System.out.println("�b��: " + bank.getAccount() + " �l�B: " + bank.getAmount());
	}
	
	@Test
	public void depositTest() {
//		Bank bank = new Bank("AA999", "AA123456@", 2000);
//		Bank oldBank = bankDao.save(bank);
		// �Ыذ����
		Bank oldBank = bankDao.save(new Bank("AA999", "AA123456@", 2000));
		// �s��
		Bank newBank = new Bank("AA999", "AA123456@", 3000);
		BankResponse response = bankService.deposit(newBank);
		// �T�{���B���s�i�h
		Bank resBank = response.getBank();
		Assert.isTrue(resBank.getAmount() == (oldBank.getAmount() + newBank.getAmount()), "�s�ڿ��~!!");
		Assert.isTrue(response.getMessage().equals("�s�ڦ��\!!"), "�s�ڥ���!!");
		//�R�����ո��
		bankDao.delete(resBank);
	}
	
	@Test
	public void withdrawTest() {
		// �Ыذ����
		Bank oldBank = bankDao.save(new Bank("AA999", "AA123456@", 5000));
		// ����
		Bank newBank = new Bank("AA999", "AA123456@", 3000);
		BankResponse response = bankService.withdraw(newBank);
		// �T�{���ڪ��B
		Bank resBank = response.getBank();
		Assert.isTrue(resBank.getAmount() == (oldBank.getAmount() - newBank.getAmount()), "���ڿ��~!!");
		Assert.isTrue(response.getMessage().equals("���ڦ��\!!"), "���ڥ���!!");
		//�R�����ո��
		bankDao.delete(resBank);
	}
}
