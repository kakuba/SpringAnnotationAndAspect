package pl.spring.demo.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.common.MyAdapter;
import pl.spring.demo.to.AthorTo;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class TestAdapter {
	
	@Autowired
	private MyAdapter adapter; //= context.getBean("adapter", Adapter.class);
	
	
	@Test
	public void testShouldAdaptToBookEntity() {
		//given
		BookTo bookTo = new BookTo(1L, "testTitle", "testFirstName testLastName");
		//when
		BookEntity bookEntity = adapter.convertToBookEntity(bookTo); 
		//then
		BookEntityAssert.assertThat(bookEntity).hasId(1L).hasTitle("testTitle");
		assertEquals("testFirstName", bookEntity.getAuthors().get(0).getFirstName());
		assertEquals("testLastName", bookEntity.getAuthors().get(0).getLastName());
	}
	@Test
	public void testShouldAdaptToBookEntityOnlyFirstName() {
		//given
		BookTo bookTo = new BookTo(1L, "testTitle", "testFirstName");
		//when
		BookEntity bookEntity = adapter.convertToBookEntity(bookTo); 
		//then
		BookEntityAssert.assertThat(bookEntity).hasId(1L).hasTitle("testTitle");
		assertEquals("testFirstName", bookEntity.getAuthors().get(0).getFirstName());
		assertEquals(null , bookEntity.getAuthors().get(0).getLastName());
	}
	@Test
	public void testShouldAdaptToBookEntityWithTwoAuthors() {
		//given
		BookTo bookTo = new BookTo(1L, "testTitle", "testFirstName1 testLastName1,testFirstName2 testLastName2");
		//when
		BookEntity bookEntity = adapter.convertToBookEntity(bookTo); 
		//then
		BookEntityAssert.assertThat(bookEntity).hasId(1L).hasTitle("testTitle");
		assertEquals("testFirstName1", bookEntity.getAuthors().get(0).getFirstName());
		assertEquals("testLastName1", bookEntity.getAuthors().get(0).getLastName());
		assertEquals("testFirstName2", bookEntity.getAuthors().get(1).getFirstName());
		assertEquals("testLastName2", bookEntity.getAuthors().get(1).getLastName());
	}
	@Test
	public void testShouldAdaptToBookTo() {
		//given
		BookEntity bookEntity = new BookEntity(1L, "testTitle", Arrays.asList(new AthorTo(null, "testFirstName", "testLastName")));
		//when
		BookTo bookTo = adapter.convertToBookTo(bookEntity); 
		//then
		BookToAssert.assertThat(bookTo).hasId(1L).hasTitle("testTitle").hasAuthors("testFirstName testLastName");
	}
	@Test
	public void testShouldAdaptToBookToList() {
		//given
		List<BookEntity> bookEntityList = Arrays.asList(new BookEntity(1L, "testTitle1", Arrays.asList(new AthorTo(null, "testFirstName1", "testLastName1"))), new BookEntity(2L, "testTitle2", Arrays.asList(new AthorTo(null, "testFirstName2", "testLastName2"))));
		//when
		List<BookTo> bookToList = adapter.convertToListBookTo(bookEntityList); 
		//then
		BookToAssert.assertThat(bookToList.get(0)).hasId(1L).hasTitle("testTitle1").hasAuthors("testFirstName1 testLastName1");
		BookToAssert.assertThat(bookToList.get(1)).hasId(2L).hasTitle("testTitle2").hasAuthors("testFirstName2 testLastName2");
	}
	@Test
	public void testShouldAdaptToBookEntityList() {
		//given
		List<BookTo> bookToList = Arrays.asList(new BookTo(1L, "testTitle1", "testFirstName1 testLastName1"), new BookTo(2L, "testTitle2", "testFirstName2 testLastName2"));
		//when
		List<BookEntity> bookEntityList = adapter.convertToListBookEntity(bookToList);
		//then
		BookEntityAssert.assertThat(bookEntityList.get(0)).hasId(1L).hasTitle("testTitle1");
		assertEquals("testFirstName1", bookEntityList.get(0).getAuthors().get(0).getFirstName());
		assertEquals("testLastName1", bookEntityList.get(0).getAuthors().get(0).getLastName());
		BookEntityAssert.assertThat(bookEntityList.get(1)).hasId(2L).hasTitle("testTitle2");
		assertEquals("testFirstName2", bookEntityList.get(1).getAuthors().get(0).getFirstName());
		assertEquals("testLastName2", bookEntityList.get(1).getAuthors().get(0).getLastName());
	}

}
