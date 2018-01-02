package nl.thuis.tutorial.springaop.repository;

import org.springframework.stereotype.Repository;

import nl.thuis.tutorial.springaop.bean.Account;

@Repository
public class AccountRepository {
	
	private String name;
	private String serviceCode;

	public void addAccount() {
		
		System.out.println(getClass() + " : Add Account to DB");
	}
	
	public void addAccount(Account account) {
		
		System.out.println(getClass() + " : Add Account to DB with Account Param");
	}

	public String getName() {
		System.out.println(getClass() + " : get name! " + name);
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + " : set name! " + name);
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass() + " : get servicecode! " + serviceCode);
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass() + " : set servicecode! " + serviceCode);
		this.serviceCode = serviceCode;
	}
	
	
}
	