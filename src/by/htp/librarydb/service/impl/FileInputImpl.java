package by.htp.librarydb.service.impl;

import java.util.Date;
import java.util.List;

import by.htp.librarydb.dao.BookDao;
import by.htp.librarydb.dao.impl.BookFileDAO;
import by.htp.librarydb.domain.entity.Book;
import by.htp.librarydb.domain.vo.Catalog;
import by.htp.librarydb.service.CatalogService;

public class FileInputImpl implements CatalogService {

	private BookDao dao;

	{
		dao = new BookFileDAO();
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
		catalog.setTitle("New catalog of Books");
		catalog.setDateCreation(new Date());
		List<Book> book = dao.fetchBooks(date);
		catalog.setBooks(book);
		return catalog;
	}

	@Override
	public Catalog getCatalogByNamePages(String name, int pages) {
		return null;
	}

}
