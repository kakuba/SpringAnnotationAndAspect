package pl.spring.demo.service.impl;

import pl.spring.demo.common.MyAdapter;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
@Service("bookService")
public class BookServiceImpl implements BookService {
	@Autowired
	MyAdapter adapter ;//= context.getBean("adapter", Adapter.class);
    private BookDao bookDao;
    
    
    @Override
    public List<BookTo> findAllBooks() {
    	List<BookTo> bookToList = adapter.convertToListBookTo(bookDao.findAll());
    	return bookToList;
    	//return bookDao.findAll();
    }

    @Override
    public List<BookTo> findBooksByTitle(String title) {
    	List<BookTo> bookToList = adapter.convertToListBookTo(bookDao.findBookByTitle(title));
    	return bookToList;
    	//return bookDao.findBookByTitle(title);
    }

    @Override
    public List<BookTo> findBooksByAuthor(String author) {
    	List<BookTo> bookToList = adapter.convertToListBookTo(bookDao.findBooksByAuthor(author));
    	return bookToList;
    	//return bookDao.findBooksByAuthor(author);
    }

    @Override
    public BookTo saveBook(BookTo book) {
        BookTo bookTo = adapter.convertToBookTo(bookDao.save(adapter.convertToBookEntity(book)));
    	return bookTo;
        //return bookDao.save(book);
    }
    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
