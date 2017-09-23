package by.htp.librarydb.dao.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.htp.librarydb.dao.BookDao;
import by.htp.librarydb.dao.utils.TranslatorDate;
import by.htp.librarydb.domain.entity.Book;

/*
В пакете by.htp.db.dao.impl создать класс BookFileDAO реализующий интерфейс BookDao.
В данном классе реализовать методы: получения списка книг из обычного текстового файла 
и получения списка книг  по условию даты (аналогично получению таких же данных из БД).
*/

public class BookFileDAO implements BookDao {

	private static final String REGULAR = "\\d\\d?\\s[[A-Za-z]*_?[A-Za-z]*]*\\s\\d\\d?\\d?\\s\\d\\s";
	private static final String REGULAR_DATE = "\\d\\d\\d\\d-\\d\\d-\\d\\d";

	@Override
	public List<Book> fetchBooks() {
		List<Book> listbook = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader("D:\\ECLIPSE\\LibraryDB\\src\\Books' titles.txt"))) {
			String line;
			boolean flag = true;

			do {
				line = in.readLine();
				if (line != null) {
					if (!line.isEmpty()) {
						if (line.matches(REGULAR + REGULAR_DATE)) {
							String[] array = divideString(line);
							Book book = bookCreator(array);
							listbook.add(book);
						}
					} else
						flag = false;
				} else
					flag = false;
			} while (flag);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listbook;
	}

	@Override
	public List<Book> fetchBooks(Date date) {
		List<Book> listbook = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader("D:\\ECLIPSE\\LibraryDB\\src\\Books' titles.txt"))) {
			String line;
			boolean flag = true;

			do {
				line = in.readLine();
				if (line != null) {
					if (!line.isEmpty()) {
						if (line.matches(REGULAR + REGULAR_DATE)) {
							String[] array = divideString(line);
							Book book = bookCreator(array);
							if (book.getDate().getTime() >= date.getTime()) {
								listbook.add(book);
							}
						}
					} else
						flag = false;
				} else
					flag = false;
			} while (flag);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listbook;
	}

	private static String[] divideString(String str) {
		String arrayWords[] = new String[10];
		if (str != null) {
			arrayWords = str.split(" ");
		}
		return arrayWords;
	}

	private static Book bookCreator(String arrayWords[]) {
		Book book = new Book();
		if (arrayWords.length >= 5) {
			book.setId(Integer.parseInt(arrayWords[0]));
			book.setTitle(arrayWords[1]);
			book.setPages(Integer.parseInt(arrayWords[2]));
			book.setAuthor_id(Integer.parseInt(arrayWords[3]));
			book.setDate(TranslatorDate.transformIntoDate(arrayWords[4]));
		}
		return book;
	}

}
