package com.naeem.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naeem.demo.model.BankAccount;


@Repository
public interface BankRepo extends JpaRepository<BankAccount, Integer> {

	    BankAccount findByEmail(String email); // ✅ NEW

	
}
