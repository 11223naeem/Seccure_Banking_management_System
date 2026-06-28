package com.naeem.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naeem.demo.model.BankAccount;
import com.naeem.demo.repo.BankRepo;

@Service
public class BankService {
	
	@Autowired
	BankRepo repo;
	
	

	public BankAccount getDetails(int accno) {
		
		return repo.findById(accno).orElse(null);
	}

	public void changeName(String Nname,int num) {
		BankAccount acc= repo.findById(num).orElse(null);
		
		acc.setName(Nname);
		repo.save(acc);
		
	}
	
	
}
