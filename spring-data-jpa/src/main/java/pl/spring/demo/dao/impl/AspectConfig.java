package pl.spring.demo.dao.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import pl.spring.demo.aop.BookDaoAdvisor;

@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {
	@Bean
	public BookDaoAdvisor bookDaoAdvisor() {
		return new BookDaoAdvisor();
	}
	
}
