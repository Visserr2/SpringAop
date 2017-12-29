package nl.thuis.tutorial.springaop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy // Spring enables using AspectJ Proxies for AOP
@ComponentScan("nl.thuis.tutorial.springaop")
public class SpringConfig {

}
