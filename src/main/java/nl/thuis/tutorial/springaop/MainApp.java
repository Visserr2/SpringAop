package nl.thuis.tutorial.springaop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import nl.thuis.tutorial.springaop.config.SpringConfig;
import nl.thuis.tutorial.springaop.repository.AccountRepository;

public class MainApp {

	public static void main(String[] args) {
		
		// read Spring config file
		AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext(SpringConfig.class);
		
		// Get bean from context
		AccountRepository repository = config.getBean("accountRepository", AccountRepository.class);
	
		// Use method from repository
		repository.addAccount();
		
		// Closing context
		config.close();
	}
}
