package nl.thuis.tutorial.springaop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1) // define the order in relation to the other aspects
public class SecurityAspect {
	
	// In the aspect all the related advices (methods) are stored. For every unrelated advice create a new aspect
	// You can order the aspects-classes via the @Order annotation
	
	// Advice declerations with pointcut-expressions
	
	/**
	 * Start this advice before every method that start with "add" every class
	 */
	@Order(1)	// Define order of methods in relation to other methods within the aspect
	@Before("execution(* add*() )") // The parameter is called a pointcut
	public void beforeAddMethodsAdvice() {
		System.out.println("----SECURITY----- This is executed before every 'add'-method in every class");
	}
	
	/**
	 * Start this advice before "public void addAccount()"-method in AccountRepository
	 */
	@Order(2)	// Define order of methods in relation to other methods within the aspect
	@Before("execution(public void nl.thuis.tutorial.springaop.repository.AccountRepository.addAccount())") // The parameter is called a pointcut
	public void beforeAccountRepositoryAddAccountAdvice() {
		System.out.println("----SECURITY----- This is executed before addAccount-method in AccountRepository");
	}

}
