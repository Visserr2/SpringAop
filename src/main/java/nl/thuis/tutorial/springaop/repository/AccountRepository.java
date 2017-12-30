package nl.thuis.tutorial.springaop.repository;

import org.springframework.stereotype.Repository;

import nl.thuis.tutorial.springaop.bean.Account;

@Repository
public class AccountRepository {

	public void addAccount() {
		
		System.out.println(getClass() + " : Add Account to DB");
	}
	
	public void addAccount(Account account) {
		
		System.out.println(getClass() + " : Add Account to DB with Account Param");
	}
}
	