package nl.thuis.tutorial.springaop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutDeclerations {
	
	// Pointcut declerations

	@Pointcut("execution(* nl.thuis.tutorial.springaop.repository.*.*(..) )")
	public void executeBeforeEveryMethodInRepositoryPackage() {}

	@Pointcut("execution(* nl.thuis.tutorial.springaop.repository.*.get*(..) )")
	public void executeBeforeEveryGetMethodInRepositoryPackage() {}

	@Pointcut("execution(* nl.thuis.tutorial.springaop.repository.*.set*(..) )")
	public void executeBeforeEverySetMethodInRepositoryPackage() {}

	@Pointcut("executeBeforeEveryMethodInRepositoryPackage() && !(executeBeforeEveryGetMethodInRepositoryPackage() || executeBeforeEverySetMethodInRepositoryPackage())")
	public void executeBeforeEveryMethodExceptGetAndSetInRepositoryPackage() {}

}
