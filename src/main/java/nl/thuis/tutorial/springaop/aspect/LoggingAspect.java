package nl.thuis.tutorial.springaop.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
	
	private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

	// In the aspect all the related advices (methods) are stored. For every unrelated advice create a new aspect
	// You can order the aspects-classes via the @Order annotation
	
	// @Before - runs before the method
	// @AfterReturning - runs after the method is successfully executed
	// @AfterThrowing - Runs after the method throws exception
	// @After - Runs after the method is executed
	// @Around - Runs before and after the method
	
	// Advice declerations with pointcut-expressions
	/**
	 * Start this advice before every "public void addAccount()"-method in any class
	 */
	@Before("execution(public void addAccount())") // The parameter is called a pointcut
	public void beforeAddAccountAdvice() {
		logger.info("----LOGGING----- This is executed before addAccount-method");
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
				logger.info(account.getName());
			}
		}
		
		logger.info("----LOGGING----- |" + sig +  "| This is executed before every method within a package except in getters and setters");		
	}
	
	/**
	 * Start this advice before every method in the repository package
	 */
	@Order(2)	// Define order of methods in relation to other methods within the aspect
	@Before("nl.thuis.tutorial.springaop.aspect.PointcutDeclerations.executeBeforeEveryMethodInRepositoryPackage()") // The parameter is called a pointcut-method decleration
	public void beforeEveryMethodsInPackageAdvice() {
		logger.info("----LOGGING----- This is executed before every method within a package");
	}
	
	//////// AFTER RETURNING ADVICE ////////////
	
	/**
	 * Start this advice after the findAccount-Method is successfully executed
	 * The pointcut contains the expression when this advice should be executed
	 * The returning contains the value that is returned by the method
	 * The param that contains the returning value of the method must match the class-type and the returning variable
	 * It is also possible to modify the return value. This way you can format or enrich the data. But this is not recommended
	 * Every programmer needs to know about the AOP-modified value because else programmers think that the method is broken 
	 */
	@AfterReturning(pointcut="execution(* nl.thuis.tutorial.springaop.repository.AccountRepository.findAccounts(..))", returning="result")
	public void afterReturningFindAccountAdvice(JoinPoint joinPoint, List<Account> result) {
		
		logger.info("LOGGING --> Executing After Method Successfully Advice: " + joinPoint.getSignature().toShortString());
		
		logger.info("LOGGING --> Executing After Method Successfully Advice: " + result);	
		
		// Change result from the method and gives new answer back to calling application
		if(!result.isEmpty()) {
			Account account = result.get(0);
			account.setName("Daffy Duck");
		}
	}
	
	//////// AFTER THROWING ADVICE ////////////

	
	/**
	 * Start this advice after the findAccount-method throws an exception
	 * The pointcut contains the expression when this advice should be executed
	 * The throwing contains the variable with the exception
	 * @param joinPoint contains metadata of the method (signature and params)
	 * @param exception contains the exception that is thrown. This param must match with the throwing-variable
	 * In AfterThrowing only the exception can be logged and seen. Use the AroundAdvice for catching and process the exception
	 * The AfterThrowing runs always as last, even after the After-advice
	 */
	@AfterThrowing(pointcut="execution(* nl.thuis.tutorial.springaop.repository.AccountRepository.findAccounts(..))", throwing="exception")
	public void afterThrowingFindAccountAdvice(JoinPoint joinPoint, Throwable exception) {
		
		logger.info("LOGGING --> Executing After Method Exception Advice: " + joinPoint.getSignature().toShortString());
		
		logger.info("LOGGING --> Executing After Method Exception Advice: " + exception.getMessage());	
		
	}
	
	//////// AFTER FINALLY ADVICE ////////////
	
	/**
	 * Start this advice afther the findAccount-method regardless success or not
	 * It is not possible to intercept the exception
	 * Also the @After annotation works as a finally-block and thus is executed after the method but before AfterThrowing or AfterReturning
	 * @param joinPoint
	 * @param exception
	 */
	@After("execution(* nl.thuis.tutorial.springaop.repository.AccountRepository.findAccounts(..))")
	public void afterFinallyFindAccountAdvice(JoinPoint joinPoint) {
		
		logger.info("LOGGING --> Executing After Finally Method Advice: " + joinPoint.getSignature().toShortString());
				
	}
	
	//////// AROUND ADVICE ////////////

	/**
	 * This advice is executed before and after the getFortune() method. Regardless success or not
	 * Also you can rethrow, catch, swallow or stop an exception if it occurs
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* nl.thuis.tutorial.springaop.service.TrafficFortuneService.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint pjp) throws Throwable {
		
		logger.info("LOGGING --> Executing AROUND Advice: " + pjp.getSignature().toShortString());
		
		long start = System.currentTimeMillis();
		
		Object result = null;
		
		try {
			// execute the method
			result = pjp.proceed();
		} catch (Exception e) {
			// log the exception
			logger.warning(e.getMessage());
			
			// give default answer when exception is catched
			result = "Major accident! But everything is already fixed.";
			
			// retrow exception
			// throw e;
		}
	
		long end = System.currentTimeMillis();
		
		long time = end - start;
		logger.info("LOGGING --> Executing AROUND Advice: The method takes " + time / 1000.0 + " miliseconds!");
		
		// return the result to calling object
		return result;
	}
	
	
}
