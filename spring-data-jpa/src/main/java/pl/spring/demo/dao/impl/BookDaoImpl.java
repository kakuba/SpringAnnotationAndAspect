package pl.spring.demo.dao.impl;

import pl.spring.demo.annotation.NullableId;

import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.to.AthorTo;
import pl.spring.demo.to.BookEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.google.common.base.Splitter;
@Component
public class BookDaoImpl implements BookDao {
	
	private final Set<BookEntity> ALL_BOOKS = new HashSet<>();

	@Autowired
    private Sequence sequence;

    public BookDaoImpl() {
        addTestBooks();
    }

    @Override
    public List<BookEntity> findAll() {
        return new ArrayList<>(ALL_BOOKS);
    }

    @Override
    public List<BookEntity> findBookByTitle(String title) {
        List<BookEntity> resultList = new ArrayList<>();
        String upperCaseTitle = title.toUpperCase();
        for (BookEntity bookEntity : ALL_BOOKS) {
        	String upperCaseTitleBookEntity = bookEntity.getTitle().toUpperCase();
        	if (upperCaseTitleBookEntity.equals(upperCaseTitle)) {
        		resultList.add(bookEntity);
        	}
        }
    	return resultList;
    	//return null;
    }

    @Override
    public List<BookEntity> findBooksByAuthor(String author) {
    	List<BookEntity> resultList = new ArrayList<>();
    	String firstName = convertAuthorToList(author).get(0);
    	String lastName = convertAuthorToList(author).get(1);
        for (BookEntity bookEntity : ALL_BOOKS) {
        	for (AthorTo athorTo : bookEntity.getAuthors()) {
        		String upperCaseFirstNameBookEntity = athorTo.getFirstName().toUpperCase();
        		String upperCaseLastNameBookEntity = athorTo.getLastName().toUpperCase();
        		if (upperCaseFirstNameBookEntity.equals(firstName) || upperCaseLastNameBookEntity.equals(lastName)) {
        			resultList.add(bookEntity);
        		}
        	}
        }
    	return resultList;
    	//return null;
    }
    
    private List<String> convertAuthorToList(String author) {
    	String upperCaseAuthor = author.toUpperCase();
		List<String> names= Splitter.on(" ").splitToList(upperCaseAuthor);
    	if (names.size() == 1) {
    		names.add(names.get(0));
    	}
		return names ;
    }
    
    @Override
    //@NullableId
    public BookEntity save(BookEntity book) {
        /*if (book.getId() == null) {
            book.setId(sequence.nextValue(ALL_BOOKS));
        }*/
        ALL_BOOKS.add(book);
        return book;
    }

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }

    private void addTestBooks() {
    	List<AthorTo> authors1 = Arrays.asList(new AthorTo(1L, "Wiliam", "Szekspir"));
    	List<AthorTo> authors2 = Arrays.asList(new AthorTo(2L, "Hanna", "Ożgowska"));
    	List<AthorTo> authors3 = Arrays.asList(new AthorTo(3L, "Jan", "Parandowski"));
    	List<AthorTo> authors4 = Arrays.asList(new AthorTo(4L, "Edmund", "Niziurski"));
    	List<AthorTo> authors5 = Arrays.asList(new AthorTo(5L, "Zbigniew", "Nienacki"));
    	List<AthorTo> authors6 = Arrays.asList(new AthorTo(6L, "Aleksander", "Fredro"));
        ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia", authors1));
        ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole", authors2));
        ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza", authors3));
        ALL_BOOKS.add(new BookEntity(4L, "Awantura w Niekłaju", authors4));
        ALL_BOOKS.add(new BookEntity(5L, "Pan Samochodzik i Fantomas", authors5));
        ALL_BOOKS.add(new BookEntity(6L, "Zemsta", authors6));
    }
    public Set<BookEntity> getALL_BOOKS() {
    	return ALL_BOOKS;
    }
}
