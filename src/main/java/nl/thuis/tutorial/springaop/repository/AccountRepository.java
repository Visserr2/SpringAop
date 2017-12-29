package nl.thuis.tutorial.springaop.repository;

import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {

	public void addAccount() {
		
		System.out.println(getClass() + " : Add Account to DB");
	}
}
	