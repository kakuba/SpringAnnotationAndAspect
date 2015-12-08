package pl.spring.demo.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookEntity;


@Aspect
public class BookDaoAdvisor /*implements MethodBeforeAdvice*/ {
	private BookDaoImpl bookDaoImpl = new BookDaoImpl();
	private Sequence sequence;
	
    
	@Before("execution(* pl.spring.demo.dao.impl.BookDaoImpl.save(..))")
	public void setIdIfNull(JoinPoint jPoint) {
		Object[] args = jPoint.getArgs();
		BookEntity bookEntity = (BookEntity) args[0];
		
		if (bookEntity.getId() != null) {
			throw new BookNotNullIdException();
		}
		if (bookEntity.getId() == null) {
	        bookEntity.setId(sequence.nextValue(bookDaoImpl.getALL_BOOKS()));
		}
	}
	
	@Autowired
	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}
}
