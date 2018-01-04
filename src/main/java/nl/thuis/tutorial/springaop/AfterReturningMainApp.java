package nl.thuis.tutorial.springaop;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import nl.thuis.tutorial.springaop.bean.Account;
import nl.thuis.tutorial.springaop.config.SpringConfig;
import nl.thuis.tutorial.springaop.repository.AccountRepository;

public class AfterReturningMainApp {

	public static void main(String[] args) {
		
		// read Spring config file
		AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext(SpringConfig.class);
		
		// Get bean from context
		AccountRepository accountRepository = config.getBean("accountRepository", AccountRepository.class);
		
		// Call method to find accounts
		List<Account> accounts = accountRepository.findAccounts();
		
		// print accounts
		System.out.println(accounts);
		
		// Closing context
		config.close();
	}
}
