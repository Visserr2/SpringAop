package nl.thuis.tutorial.springaop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import nl.thuis.tutorial.springaop.config.SpringConfig;
import nl.thuis.tutorial.springaop.repository.AccountRepository;
import nl.thuis.tutorial.springaop.repository.MembershipRepository;

public class MainApp {

	public static void main(String[] args) {
		
		// read Spring config file
		AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext(SpringConfig.class);
		
		// Get bean from context
		AccountRepository accountRepository = config.getBean("accountRepository", AccountRepository.class);
		MembershipRepository membershiprepository = config.getBean("membershipRepository", MembershipRepository.class);
	
		// Use method from repository
		accountRepository.addAccount();
		membershiprepository.addAccount();
		
		// Closing context
		config.close();
	}
}
