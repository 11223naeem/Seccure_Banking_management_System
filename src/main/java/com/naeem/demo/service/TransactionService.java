package com.naeem.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naeem.demo.model.BankAccount;
import com.naeem.demo.repo.BankRepo;

@Service
public class TransactionService {
	@Autowired
	BankRepo repo;
	
	

	@Transactional
	public void deposite(double amount,int num) {
		BankAccount acc=repo.findById(num).orElse(null);
		if(acc==null) {
			throw new RuntimeException("Account not found");
		}
		acc.deposit(amount);
		repo.save(acc);
		
		
	}
	@Transactional
	public void widthraw(double amount , int accno) {
		BankAccount acc=repo.findById(accno).orElse(null);
		if(acc==null) {
			throw new RuntimeException("Account not Exist");
		}
		else if(amount>acc.getBalance()) {
			throw new RuntimeException("insufficient balance ");
		}
		else {
			acc.withdraw(amount);
			repo.save(acc);
		}
		
		
	}
	
	@Transactional
	public void transfer(int fromAcc, int toAcc, double amount) {

	    BankAccount sender = repo.findById(fromAcc)
	            .orElseThrow(() -> new RuntimeException("Sender not found"));

	    BankAccount receiver = repo.findById(toAcc)
	            .orElseThrow(() -> new RuntimeException("Receiver not found"));
	    if(fromAcc==toAcc) {
	    	throw new RuntimeException("cannot send to the same account ");
	    }
	    if(amount<=0) {
	    	throw new RuntimeException("Invalid Amount ");
	    }
	    if(amount>sender.getBalance()) {
	    	throw new RuntimeException("Insufficient balance");
	    }

	    // Withdraw
	    sender.withdraw(amount);

	    // Deposit (won't execute)
	    receiver.deposit(amount);
	}
	
	

}
