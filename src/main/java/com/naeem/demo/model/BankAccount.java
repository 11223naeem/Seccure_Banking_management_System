package com.naeem.demo.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BankAccount {
	@Id
    private int accNo;
    private String name;
    private double balance;
    private String email;

    

    public BankAccount() {
		super();
	}




    public BankAccount(int accNo, String name, double balance) {

		this.accNo = accNo;
		this.name = name;
		this.balance = balance;
		
	}

	public BankAccount(int accNo, String name, double balance,String email) {

		this.accNo = accNo;
		this.name = name;
		this.balance = balance;
		this.email=email;
	}
	
	
	
    
	public int getAccNo() {
		return accNo;
	}


	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
    	
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }

    public void rename(String newName) {
        name = newName;
    }

    public double getBalance() {
        return balance;
    }

	@Override
	public String toString() {
		return "BankAccount [accNo=" + accNo + ", name=" + name + ", balance=" + balance + "]";
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
   
}
