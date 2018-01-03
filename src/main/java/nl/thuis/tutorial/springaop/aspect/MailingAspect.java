package nl.thuis.tutorial.springaop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3) // define the order in relation to the other aspects
public class MailingAspect {
	
	// In the aspect all the related advices (methods) are stored. For every unrelated advice create a new aspect
	// You can order the aspects-classes via the @Order annotation
	
	// Advice declerations with pointcut-expressions
	
	/**
	 * Start this advice before every method that start with "add" and has an Account-param every class
	 * The ".." is match to zero or more params of any type
	 */
	@Order(1) // Define order of methods in relation to other methods within the aspect
	@Before("execution(* add*(nl.thuis.tutorial.springaop.bean.Account, ..) )") // The parameter is called a pointcut
	public void beforeAddMethodsWithParamAdvice() {
		System.out.println("----MAILING----- This is executed before every 'add'-method with Account Param in every class");
	}
	
	/**
	 * Start this advice before every method that start with "add" and returns a String every class
	 */
	@Order(2) // Define order of methods in relation to other methods within the aspect
	@Before("execution(String add*() )") // The parameter is called a pointcut
	public void beforeAddMethodsWithReturnStringAdvice() {
		System.out.println("----MAILING----- This is executed before every 'add'-method that returns a String in every class");
	}

}
