package by.htp.librarydb.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import by.htp.librarydb.dao.utils.TranslatorDate;
import by.htp.librarydb.domain.entity.Book;
import by.htp.librarydb.domain.vo.Catalog;
import by.htp.librarydb.service.impl.DefaultCatalogImple;

public class CatalogServiceTestDefaultImpl {

	private static CatalogService service;

	@Before
	public void initCatalogService() {
		service = new DefaultCatalogImple();
	}

	/** testing method - getCatalog() */
	@Test
	public void catalogNotNull() {

		Catalog catalog = service.getCatalog();
		assertNotNull("Catalog is Null", catalog);
	}

	@Test
	public void catalogNotEmptyFields() {
		Catalog catalog = service.getCatalog();
		assertNotNull("Book Title is Null", catalog.getTitle());
		assertNotNull("Book Date is Null", catalog.getDateCreation());
		assertNotNull("Book ListBooks is Null", catalog.getBooks());
	}

	@Test
	public void catalogEmptyBooks() {
		Catalog catalog = service.getCatalog();
		assertNotNull("", catalog.getBooks());
		assertTrue("Catalog hasn't any books", catalog.getBooks().size() > 0);
	}

	/** testing method - getCatalog(Date date) */

	@Test
	public void catalogSelectedByDateNotNull() {
		Date date = TranslatorDate.transformIntoDate("2010-01-01");
		Catalog catalog = service.getCatalog(date);
		assertNotNull("Catalog is Null", catalog);
	}

	@Test
	public void catalogSelectedByDateNotEmptyFields() {
		Date date = TranslatorDate.transformIntoDate("2010-01-01");
		Catalog catalog = service.getCatalog(date);
		assertNotNull("Book Title is Null", catalog.getTitle());
		assertNotNull("Book Date is Null", catalog.getDateCreation());
		assertNotNull("Book ListBooks is Null", catalog.getBooks());
	}

	@Test
	public void catalogSelectedByDateEmptyBooks() {
		Date date = TranslatorDate.transformIntoDate("2010-01-01");
		Catalog catalog = service.getCatalog(date);
		assertNotNull("", catalog.getBooks());
		assertTrue("Catalog hasn't any books", catalog.getBooks().size() > 0);
	}

	@Test
	public void catalogBooksHaveCorrectDate() {
		Date date = TranslatorDate.transformIntoDate("2010-01-01");
		Catalog catalog = service.getCatalog(date);
		boolean flag = true;
		List<Book> listbooks = catalog.getBooks();

		Iterator<Book> it = listbooks.iterator();
		if (it.hasNext()) {
			Book book = it.next();

			if (book.getDate().getTime() < date.getTime()) {
				flag = false;
			}
		}
		assertTrue("Catalog has books with uncorrect date", flag);
	}

	/** testing method - getCatalogByNamePages (String name, int pages) */

	@Test
	public void catalogBooksHaveCorrectNameAndPages() {
		int pages = 0;
		String keyWord1 = "%Around%";
		String keyWord2 = "Around";
		Catalog catalog = service.getCatalogByNamePages(keyWord1, pages);
		boolean flag = false;
		List<Book> listbooks = catalog.getBooks();

		Iterator<Book> it = listbooks.iterator();
		if (it.hasNext()) {
			Book book = it.next();

			if (book.getTitle().contains(keyWord2) & book.getPages() > pages) {
				flag = true;
			}
		}
		assertTrue("Catalog has books with uncorrect name and amount pages", flag);
	}

}
