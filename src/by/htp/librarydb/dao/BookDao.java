package by.htp.librarydb.dao;

import java.util.Date;
import java.util.List;

import by.htp.librarydb.domain.entity.Book;

public interface BookDao {
	
	List<Book> fetchBooks ();
	List<Book> fetchBooks(Date date);
			
}
