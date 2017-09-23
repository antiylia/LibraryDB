package by.htp.librarydb.service.impl;

import java.util.Date;
import java.util.List;

import by.htp.librarydb.dao.BookDao;
import by.htp.librarydb.dao.BookDaoAddition;
import by.htp.librarydb.dao.impl.BookMySqlDao;
import by.htp.librarydb.dao.utils.TranslatorDate;
import by.htp.librarydb.domain.entity.Book;
import by.htp.librarydb.domain.vo.Catalog;
import by.htp.librarydb.service.CatalogService;

public class DefaultCatalogImple implements CatalogService {

	private BookDao dao;
	private BookDaoAddition daoAddition;

	{
		// TODO !!! Change to factory
		dao = new BookMySqlDao();
		daoAddition = new BookMySqlDao();
	}

	@Override
	public Catalog getCatalog() {
		Catalog catalog = new Catalog();
		catalog.setTitle("New catalog of Books");
		catalog.setDateCreation(new Date());
		List<Book> book = dao.fetchBooks();
		catalog.setBooks(book);

		return catalog;
	}

	@Override
	public Catalog getCatalog(Date date) {
		Catalog catalog = new Catalog();
		catalog.setTitle("New catalog of Books, date creation >= " + TranslatorDate.transformDateIntoString(date));
		catalog.setDateCreation(new Date());
		List<Book> book = dao.fetchBooks(date);

		catalog.setBooks(book);
		return catalog;
	}

	@Override
	public Catalog getCatalogByNamePages(String name, int pages) {
		Catalog catalog = new Catalog();
		catalog.setTitle("New catalog of Books, keyword: " + name.substring(1, name.length() - 1) + " pages " + pages);
		catalog.setDateCreation(new Date());
		List<Book> book = daoAddition.getBooks(name, pages);
		catalog.setBooks(book);
		return catalog;
	}

}
