package nl.thuis.tutorial.springaop.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipRepository {

	public String addAccount() {
		System.out.println(getClass() + " : Add Account to DB");
		
		return "String";
	}
}
