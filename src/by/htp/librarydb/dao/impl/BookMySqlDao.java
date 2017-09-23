package by.htp.librarydb.dao.impl;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.htp.librarydb.dao.BookDao;
import by.htp.librarydb.dao.BookDaoAddition;
import by.htp.librarydb.dao.utils.DBConnector;
import by.htp.librarydb.domain.entity.Book;

public class BookMySqlDao implements BookDao, BookDaoAddition {

	private static final String SQL_SELECT_BOOKS = "SELECT * FROM book";
	private static final String SQL_SELECT_BOOKS_DATE = "SELECT * FROM book WHERE date>=?";

	private static final String SQL_SELECT_BOOKS_ACODI_ID = "SELECT id, title, pages, author_id, date FROM book WHERE id=?";

	private final static String QUERY_INSERT_BOOKS = "INSERT INTO book (id, title, pages, author_id) VALUES (?, ?, ?, ?)";

	private final static String QUERY_DELETE_BOOK = "DELETE FROM book WHERE id=?";
	private final static String QUERY_FIND_DELETED_BOOK = "SELECT id, title, pages, author_id FROM book WHERE id=?";

	private final static String QUERY_FIND_BOOKS = "SELECT id, title, pages, author_id, date FROM book WHERE title LIKE ? AND pages > ? ";

	private final static String QUERY_SELECT_ALL_BOOKS = "SELECT id, title, pages, author_id FROM book";

	@Override
	public List<Book> fetchBooks() {
		List<Book> books = new ArrayList<Book>();
		Connection connection = DBConnector.getConnection();

		try {
			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(SQL_SELECT_BOOKS);

			Book book = null;
			int id = 0;
			String title = "";
			int pages = 0;

			while (res.next()) {
				book = new Book();
				id = res.getInt("id");
				title = res.getString("title");
				pages = res.getInt("pages");
				Date date1 = res.getDate("date");
				book.setId(id);
				book.setTitle(title);
				book.setPages(pages);
				books.add(book);
				book.setDate(date1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.closeConnection(connection);
		}
		return books;
	}

	@Override
	public List<Book> fetchBooks(Date date) {
		List<Book> books = new ArrayList<Book>();
		Connection connection = DBConnector.getConnection();
		try {
			PreparedStatement st = connection.prepareStatement(SQL_SELECT_BOOKS_DATE);
			java.sql.Date dateSql = new java.sql.Date(date.getTime());

			st.setDate(1, dateSql);
			ResultSet res = st.executeQuery();

			Book book = null;
			int id = 0;
			String title = "";
			int pages = 0;

			while (res.next()) {
				book = new Book();
				id = res.getInt("id");
				title = res.getString("title");
				pages = res.getInt("pages");
				Date date1 = res.getDate("date");
				book.setId(id);
				book.setTitle(title);
				book.setPages(pages);
				book.setDate(date1);

				books.add(book);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.closeConnection(connection);
		}
		return books;
	}

	
	public Book getBookId(int i) {
		Book book = null;
		Connection connection = DBConnector.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(SQL_SELECT_BOOKS_ACODI_ID);
			ps.setInt(1, i);

			ResultSet res = ps.executeQuery();

			int id = 0;
			String title = "";
			int pages = 0;

			while (res.next()) {
				book = new Book();
				id = res.getInt("id");
				title = res.getString("title");
				pages = res.getInt("pages");
				int author_id = res.getInt("author_id");
				Date date1 = res.getDate("date");
				book.setId(id);
				book.setTitle(title);
				book.setPages(pages);
				book.setAuthor_id(author_id);
				book.setDate(date1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.closeConnection(connection);
		}
		return book;
	}

	public List<Book> getAllBooks() {
		List<Book> items = new ArrayList<>();
		Connection connection = DBConnector.getConnection();

		try {
			Statement st = connection.createStatement();
			ResultSet result = st.executeQuery(QUERY_SELECT_ALL_BOOKS);

			Book book = null;
			while (result.next()) {
				book = new Book();
				int id = result.getInt("id");
				String title = result.getString("title");
				int pg = result.getInt("pages");
				int author_id = result.getInt("author_id");
				book.setId(id);
				book.setTitle(title);
				book.setPages(pg);
				book.setAuthor_id(author_id);
				items.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.closeConnection(connection);
		}
		return items;
	}

	public Book deleteBookFromTable(int index) {
		Connection connection = DBConnector.getConnection();
		PreparedStatement psForBufer = null;
		PreparedStatement psForDelete = null;
		Book book = null;
		try {
			psForBufer = connection.prepareStatement(QUERY_FIND_DELETED_BOOK);
			psForBufer.setInt(1, index);
			ResultSet result = psForBufer.executeQuery();

			while (result.next()) {
				book = new Book();
				int id = result.getInt("id");
				String title = result.getString("title");
				int pg = result.getInt("pages");
				int author_id = result.getInt("author_id");
				book.setId(id);
				book.setTitle(title);
				book.setPages(pg);
				book.setAuthor_id(author_id);
			}

			psForDelete = connection.prepareStatement(QUERY_DELETE_BOOK);
			psForDelete.setInt(1, index);

			psForDelete.executeUpdate();

		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			DBConnector.closeConnection(connection);
		}
		return book;
	}

	public List<Book> getBooks(String nameBook, int pages) {
		List<Book> items = new ArrayList<>();
		Connection connection = DBConnector.getConnection();

		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(QUERY_FIND_BOOKS);

			ps.setString(1, nameBook);
			ps.setInt(2, pages);		

			ResultSet result = ps.executeQuery();

			Book book = null;

			while (result.next()) {
				book = new Book();
				int id = result.getInt("id");
				String title = result.getString("title");
				int pg = result.getInt("pages");
				int author_id = result.getInt("author_id");
				Date date1 = result.getDate("date");
				book.setId(id);
				book.setTitle(title);
				book.setPages(pg);
				book.setAuthor_id(author_id);
				book.setDate(date1);
				items.add(book);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			DBConnector.closeConnection(connection);
		}
		return items;
	}

	public int[] fillTable(ArrayList<Book> booksList) {
		int[] upDataCounts = null;
		Connection connection = DBConnector.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_BOOKS);

			for (Book book : booksList) {
				statement.setInt(1, book.getId());
				statement.setString(2, book.getTitle());
				statement.setInt(3, book.getPages());
				statement.setInt(4, book.getAuthor_id());
				statement.addBatch();

			}
			upDataCounts = statement.executeBatch();

		} catch (BatchUpdateException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.closeConnection(connection);
		}
		return upDataCounts;
	}	
}
