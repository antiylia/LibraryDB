package by.htp.librarydb.console.view.impl;

import by.htp.librarydb.console.view.ConsoleOutPut;
import by.htp.librarydb.dao.utils.TranslatorDate;
import by.htp.librarydb.domain.entity.Book;
import by.htp.librarydb.domain.vo.Catalog;

public class SimpleConsoleOutput implements ConsoleOutPut {

	@Override
	public void printCatalog(Catalog catalog) {
		System.out.println("Catalog title: " + catalog.getTitle());
		System.out.println("Catalog date: " + TranslatorDate.transformDateIntoString(catalog.getDateCreation()));
		
		for (Book book : catalog.getBooks()) {
		System.out.println(book);
		}
		
	}

}
