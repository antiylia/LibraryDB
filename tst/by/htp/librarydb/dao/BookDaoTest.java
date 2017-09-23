package by.htp.librarydb.dao;

import static org.junit.Assert.*;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import by.htp.librarydb.dao.impl.BookMySqlDao;
import by.htp.librarydb.domain.entity.Book;

public class BookDaoTest {

	private static BookDao dao;
	private static BookDaoAddition daoAddition;

	@BeforeClass
	public static void initDao() {
		dao = new BookMySqlDao();
		daoAddition = new BookMySqlDao();
	}

	@Test
	public void testNullList() {
		List<Book> books = dao.fetchBooks();

		assertNotNull("The returned list is null", books);
	}

	@Test
	public void testEmptyList() {
		List<Book> books = dao.fetchBooks();
		assertTrue("The returned list contains zero books", books.size() > 0);
	}

	@Test
	public void testEmptyBook() {
		Book booktest = daoAddition.getBookId(2);
		assertNotNull(booktest);
	}

	@Test
	public void testIDCorrect() {
		int i = 2;
		Book booktest = daoAddition.getBookId(i);
		assertTrue(booktest.getId() == i);
	}

}
