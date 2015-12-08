package pl.spring.demo.mock;
/**
 * @COPYRIGHT (C) 2015 Schenker AG
 *
 * All rights reserved
 */

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import pl.spring.demo.common.MyAdapter;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.AthorTo;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

/**
 * TODO The class BookServiceImplTest is supposed to be documented...
 *
 * @author AOTRZONS
 */
public class BookServiceImplTest {
    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookDao bookDao;
    @Mock
    private MyAdapter adapter;

    @Before
    public void setUpt() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShouldSaveBook() {
        // given
        BookTo book = new BookTo(null, "title", "author");
        List<AthorTo> authorList = Arrays.asList(new AthorTo(null, "author", null));
        BookEntity bookEntity = new BookEntity(null, "title", authorList);
        BookEntity bookEntity2 = new BookEntity(1L, "title", authorList);
        BookTo bookTo = new BookTo(1L, "title", "author");
        
        Mockito.when(bookDao.save(bookEntity)).thenReturn(bookEntity2);
        Mockito.when(adapter.convertToBookTo(bookEntity2)).thenReturn(bookTo);
        Mockito.when(adapter.convertToBookEntity(book)).thenReturn(bookEntity);
        // when
        BookTo result = bookService.saveBook(book);
        // then
        Mockito.verify(bookDao).save(bookEntity);
        Mockito.verify(adapter).convertToBookTo(bookEntity2);
        assertEquals(1L, result.getId().longValue());
    }
}
