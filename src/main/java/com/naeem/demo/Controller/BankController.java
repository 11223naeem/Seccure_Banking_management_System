package com.naeem.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.naeem.demo.model.BankAccount;
import com.naeem.demo.repo.BankRepo;
import com.naeem.demo.service.BankService;
import com.naeem.demo.service.TransactionService;


@Controller


public class BankController {
	
	@Autowired
	TransactionService Tservice;
	
	@Autowired
	BankRepo repo;
	
	@Autowired
	BankService service;
	
	@RequestMapping("/")
	public String index() {
		return "index";
		
	}
	@RequestMapping("/dashboard")
	public String dash() {
		return "dashboard";
	}
	
	@RequestMapping("/login")
	public String LoginPage() {
		
		return "login";
	}
	
	@RequestMapping("/loginAccount")
	public String LoginPages(@RequestParam int accno) {
	 BankAccount acc =repo.findById(accno).orElse(null);
		if(acc==null) {
			return "error-page";
		}
		return "dashboard";
	}
	
		@RequestMapping("/createAccount")
		public String CreateAccount() {
			return "createAccount";
			
		}
		@RequestMapping("/CreateAccounts")
		public String CreateAccountPage(@RequestParam() int accno ,@RequestParam() String name,@RequestParam() double balance) {
			BankAccount acc = new BankAccount(accno, name, balance);
			repo.save(acc);
			return "login";
			
		}
		@RequestMapping("/Details")
		
		public String AccountDetailst(@RequestParam() int accno , Model model) {
			 BankAccount acc = service.getDetails(accno);
			    
			    	if (acc == null) {
			            model.addAttribute("title", "Error");
			            model.addAttribute("message", "Account Not Found");
			            
			            return "result";
			        }
			    	else {

			        model.addAttribute("title", "Account Details");
			        model.addAttribute("message", acc.toString());
			       
			    }
			        return "result";
			    } 
		
		
		@RequestMapping("/nameChange")
		
		public String nameChange (@RequestParam() int num , @RequestParam() String Nname,Model model) {
			BankAccount acc = service.getDetails(num);
			if (acc == null) {
	            model.addAttribute("title", "Error");
	            model.addAttribute("message", "Account Not Found");
	            
	            return "result";
	        }
	    	else {

	        model.addAttribute("title", "Account Details");
	        service.changeName(Nname,num);

	        model.addAttribute("message", acc.toString());
	        			
	       
	    }
			
			 return "result";
		}
		
		@RequestMapping("/BankBalance")
		
		public String Check_balance(@RequestParam() int num , Model model ) {
			BankAccount acc = service.getDetails(num);
			if (acc == null) {
	            model.addAttribute("title", "Error");
	            model.addAttribute("message", "Account Not Found");
	            
	            return "result";
	        }
	    	else {

	        model.addAttribute("title", "Account Details");
	        model.addAttribute("message",  String.valueOf(acc.getBalance()));
	       
	    }
			 

			 
			 return  "result"; 
		}
		
		
		@RequestMapping("/Deposite")
		public String Deposite(@RequestParam() int num,@RequestParam() double amount ,Model model) {
	    	try {

	    		Tservice.deposite(amount, num);
	        model.addAttribute("title", "Account Deposited Successfully");
	        model.addAttribute("message", "your deposited amount is : "+String.valueOf(amount)+"  " + "your current balance is  : "+String.valueOf(service.getDetails(num).getBalance()));
	    	}catch(Exception e) {
	    		 model.addAttribute("title", "Error");
		            model.addAttribute("message", e.getMessage());
	    		}
			return  "result"; 
			}
		
		
		@RequestMapping("/Widthraw")
		
		public String  Widthraw (@RequestParam() int num,@RequestParam() double amount, Model model ) {
			try {
				Tservice.widthraw(amount,num);
		        model.addAttribute("title", "Account Widthraw Successfully");
		        model.addAttribute("message", "Widthrawal amount is  : "+String.valueOf(amount));
				}catch(Exception e) {
					model.addAttribute("title", "Error");
		            model.addAttribute("message", e.getMessage());
				}
			
				return "result";
		}
		
		@RequestMapping("/testTransfer")
		public String testTransfer(@RequestParam int from,
		                           @RequestParam int to,
		                           @RequestParam double amount,
		                           Model model) {

		    try {
		        Tservice.transfer(from, to, amount);
		        model.addAttribute("message", "Transfer Success");
		    } catch (Exception e) {
		        model.addAttribute("message", "Transfer Failed: " + e.getMessage());
		    }

		    return "result";
		}
		
		
		}
			
		
