package pl.spring.demo.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import pl.spring.demo.to.AthorTo;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;
@Component
public class MyAdapter {
	
	public List<BookTo> convertToListBookTo(List<BookEntity> bookEntityList) {
		List<BookTo> bookToList = new ArrayList<>();
		for (BookEntity bookEntity : bookEntityList) {
			BookTo bookTo = convertToBookTo(bookEntity);
			bookToList.add(bookTo);
		}
		return bookToList ;
	}
	
	public BookTo convertToBookTo(BookEntity bookEntity) {
		BookTo bookTo = new BookTo();
		bookTo.setId(bookEntity.getId());
		if (bookEntity.getTitle() == null) {
			bookEntity.setTitle("title");
		}
		bookTo.setTitle(bookEntity.getTitle());
		bookTo.setAuthors(convertAuthorsToString(bookEntity.getAuthors()));
		return bookTo;
	}
	
	public List<BookEntity> convertToListBookEntity(List<BookTo> bookToList) {
		List<BookEntity> bookEntityList = new ArrayList<>();
		for (BookTo bookTo : bookToList) {
			BookEntity bookEntity = convertToBookEntity(bookTo);
			bookEntityList.add(bookEntity);
		}
		return bookEntityList ;
	}
	
	public BookEntity convertToBookEntity(BookTo bookTo) {
		BookEntity bookEntity = new BookEntity();
		bookEntity.setId(bookTo.getId());
		bookEntity.setTitle(bookTo.getTitle());
		bookEntity.setAuthors(convertAuthorsFromString(bookTo.getAuthors()));
		return bookEntity;
	}
	


	private String convertAuthorsToString(List<AthorTo> authorList) {
		  List<String> authors = new ArrayList<String>();
		  for (AthorTo authorTo : authorList) {
		    String author = authorTo.getFirstName() + " " + authorTo.getLastName();
		    authors.add(author);
		  }
		  return Joiner.on(",").join(authors);
		}
			

	private List<AthorTo> convertAuthorsFromString(String authors) {
		  List<AthorTo> authorList = new ArrayList<AthorTo>();
		  if (authors == null) {
			  AthorTo authorTo = new AthorTo(null, "firstName", "lastName");
			  authorList.add(authorTo);
		  } else {
			  List<String> authorNameList = Splitter.on(",").splitToList(authors);
			  for (String author : authorNameList) {
				  List<String> names = Splitter.on(" ").splitToList(author);
				  
				  AthorTo authorTo = new AthorTo();
				  authorTo.setFirstName(names.get(0));
				  if (names.size() > 1) {
					  authorTo.setLastName(names.get(1));
				  }
				  
				  authorList.add(authorTo);
			  }
		  }
		  
		  return authorList;
		}
	
}
