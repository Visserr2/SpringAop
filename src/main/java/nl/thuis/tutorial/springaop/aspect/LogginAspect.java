package nl.thuis.tutorial.springaop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogginAspect {

	// In the aspect all the related advices (methods) are stored
	
	/**
	 * Start this advice before every "public void addAccount()"-method
	 */
	@Before("execution(public void addAccount())") // The parameter is called a pointcut
	public void beforeAddAccountAdvice() {
		System.out.println("This is executed before addAccount-method");
	}
}
