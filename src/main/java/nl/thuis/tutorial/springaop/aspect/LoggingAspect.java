package nl.thuis.tutorial.springaop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	// In the aspect all the related advices (methods) are stored
	
	// Pointcut declerations
	
	@Pointcut("execution(* nl.thuis.tutorial.springaop.repository.*.*(..) )")
	private void executeBeforeEveryMethodInRepositoryPackage() {}
	
	@Pointcut("execution(* nl.thuis.tutorial.springaop.repository.*.get*(..) )")
	private void executeBeforeEveryGetMethodInRepositoryPackage() {}
	
	@Pointcut("execution(* nl.thuis.tutorial.springaop.repository.*.set*(..) )")
	private void executeBeforeEverySetMethodInRepositoryPackage() {}
	
	@Pointcut("executeBeforeEveryMethodInRepositoryPackage() && !(executeBeforeEveryGetMethodInRepositoryPackage() || executeBeforeEverySetMethodInRepositoryPackage())")
	private void executeBeforeEveryMethodExceptGetAndSetInRepositoryPackage() {}
	
	// Advice declerations with pointcut-expressions
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
	
	// Advices with pointcut-method decleration
	
	/**
	 * Start this advice before every method in the repository package
	 */
	@Before("executeBeforeEveryMethodInRepositoryPackage()") // The parameter is called a pointcut-method decleration
	public void beforeEveryMethodsInPackageAdvice() {
		System.out.println("This is executed before every method within a package");
	}
	
	/**
	 * Start this advice before every method in the repository package except in getters and setters
	 */
	@Before("executeBeforeEveryMethodExceptGetAndSetInRepositoryPackage()") // The parameter is called a pointcut-method decleration
	public void beforeEveryMethodsExceptGetAndSetInPackageAdvice() {
		System.out.println("This is executed before every method within a package except in getters and setters");
	}
}
