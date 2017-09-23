package by.htp.librarydb.dao;

import java.util.ArrayList;
import java.util.List;

import by.htp.librarydb.domain.entity.Book;

public interface BookDaoAddition {
	
	Book getBookId(int i);
	List<Book> getAllBooks();
	Book deleteBookFromTable(int index);
	List<Book> getBooks(String nameBook, int pages);
	int[] fillTable(ArrayList<Book> booksList);
	
}
