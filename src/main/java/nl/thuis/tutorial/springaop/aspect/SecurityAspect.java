package nl.thuis.tutorial.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import nl.thuis.tutorial.springaop.bean.Account;

@Aspect
@Component
@Order(1) // define the order in relation to the other aspects
public class SecurityAspect {
	
	// In the aspect all the related advices (methods) are stored. For every unrelated advice create a new aspect
	// You can order the aspects-classes via the @Order annotation
	
	// Advice declerations with pointcut-expressions
	
	/**
	 * Start this advice before every method that start with "add" every class
	 * The joinPoint gives metadata about the method. i.e. method signature and method params
	 */
	@Order(1)	// Define order of methods in relation to other methods within the aspect
	@Before("execution(* add*() )") // The parameter is called a pointcut
	public void beforeAddMethodsAdvice(JoinPoint joinPoint) {
		// Get method signature
		MethodSignature sig = (MethodSignature) joinPoint.getSignature();

		// get params, if any
		Object[] params = joinPoint.getArgs();
		// loop through params and check for account param
		for(Object object: params) {
			if(object instanceof Account) {
				Account account = (Account) object;
				System.out.println(account.getName());
			}
		}

		System.out.println("----SECURITY----- |" + sig + "| This is executed before every 'add'-method in every class");
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
