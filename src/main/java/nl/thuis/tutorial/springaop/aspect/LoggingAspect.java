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
@Order(2) // define the order in relation to the other aspects
public class LoggingAspect {

	// In the aspect all the related advices (methods) are stored. For every unrelated advice create a new aspect
	// You can order the aspects-classes via the @Order annotation
	
	// Advice declerations with pointcut-expressions
	/**
	 * Start this advice before every "public void addAccount()"-method in any class
	 */
	@Before("execution(public void addAccount())") // The parameter is called a pointcut
	public void beforeAddAccountAdvice() {
		System.out.println("----LOGGING----- This is executed before addAccount-method");
	}		
	
	// Advices with pointcut-method decleration
	
	/**
	 * Start this advice before every method in the repository package except in getters and setters
	 * The joinPoint gives metadata about the method. i.e. method signature and method params
	 */
	@Order(1) // Define order of methods in relation to other methods within the aspect
	@Before("nl.thuis.tutorial.springaop.aspect.PointcutDeclerations.executeBeforeEveryMethodExceptGetAndSetInRepositoryPackage()") // The parameter is called a pointcut-method decleration
	public void beforeEveryMethodsExceptGetAndSetInPackageAdvice(JoinPoint joinPoint) {
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
		
		System.out.println("----LOGGING----- |" + sig +  "| This is executed before every method within a package except in getters and setters");		
	}
	
	/**
	 * Start this advice before every method in the repository package
	 */
	@Order(2)	// Define order of methods in relation to other methods within the aspect
	@Before("nl.thuis.tutorial.springaop.aspect.PointcutDeclerations.executeBeforeEveryMethodInRepositoryPackage()") // The parameter is called a pointcut-method decleration
	public void beforeEveryMethodsInPackageAdvice() {
		System.out.println("----LOGGING----- This is executed before every method within a package");
	}
	
}
