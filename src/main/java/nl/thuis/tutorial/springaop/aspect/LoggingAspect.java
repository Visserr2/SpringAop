package nl.thuis.tutorial.springaop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	// In the aspect all the related advices (methods) are stored
	
	/**
	 * Start this advice before every "public void addAccount()"-method in any class
	 */
	@Before("execution(public void addAccount())") // The parameter is called a pointcut
	public void beforeAddAccountAdvice() {
		System.out.println("This is executed before addAccount-method");
	}
	
	/**
	 * Start this advice before "public void addAccount()"-method in AccountRepository
	 */
	@Before("execution(public void nl.thuis.tutorial.springaop.repository.AccountRepository.addAccount())") // The parameter is called a pointcut
	public void beforeAccountRepositoryAddAccountAdvice() {
		System.out.println("This is executed before addAccount-method in AccountRepository");
	}
	
	/**
	 * Start this advice before every method that start with "add" every class
	 */
	@Before("execution(* add*() )") // The parameter is called a pointcut
	public void beforeAddMethodsAdvice() {
		System.out.println("This is executed before every 'add'-method in every class");
	}
	
	/**
	 * Start this advice before every method that start with "add" and returns a String every class
	 */
	@Before("execution(String add*() )") // The parameter is called a pointcut
	public void beforeAddMethodsWithReturnStringAdvice() {
		System.out.println("This is executed before every 'add'-method that returns a String in every class");
	}
		
	/**
	 * Start this advice before every method that start with "add" and has an Account-param every class
	 * The ".." is match to zero or more params of any type
	 */
	@Before("execution(* add*(nl.thuis.tutorial.springaop.bean.Account, ..) )") // The parameter is called a pointcut
	public void beforeAddMethodsWithParamAdvice() {
		System.out.println("This is executed before every 'add'-method with Account Param in every class");
	}
	
	/**
	 * Start this advice before every method in the repository package
	 */
	@Before("execution(* nl.thuis.tutorial.springaop.repository.*.*(..) )") // The parameter is called a pointcut
	public void beforeAddMethodsInPackageAdvice() {
		System.out.println("This is executed before every 'add'-method within a package");
	}
}