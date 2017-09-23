package by.htp.librarydb.console.controller;


import java.util.Date;
import by.htp.librarydb.console.view.ConsoleOutPut;
import by.htp.librarydb.console.view.impl.SimpleConsoleOutput;
import by.htp.librarydb.dao.impl.BookMySqlDao;
import by.htp.librarydb.dao.utils.TranslatorDate;
import by.htp.librarydb.domain.vo.Catalog;
import by.htp.librarydb.service.CatalogService;
import by.htp.librarydb.service.impl.DefaultCatalogImple;
import by.htp.librarydb.service.impl.FileInputImpl;

public class MainController {

	public static void main(String[] args) {
		
		ConsoleOutPut consolout = new SimpleConsoleOutput();
		CatalogService service = new DefaultCatalogImple();
		
		Catalog catalog = service.getCatalog();
		consolout.printCatalog(catalog);
		System.out.println();
		
		String dateStr = "2015-01-01";
		Date dateUtil = TranslatorDate.transformIntoDate(dateStr);
		Catalog catalog1 = service.getCatalog(dateUtil);
		consolout.printCatalog(catalog1);
		System.out.println();
		
		String nameBook = "%Around%";
		Catalog catalog2 = service.getCatalogByNamePages(nameBook, 500);
		consolout.printCatalog(catalog2);
		System.out.println();
		
		BookMySqlDao book = new BookMySqlDao();
		System.out.println(book.getBookId(2));
				
		CatalogService serviceInput = new FileInputImpl();
		Catalog catalog3 = serviceInput.getCatalog();
		consolout.printCatalog(catalog3);
		System.out.println();
		
		
		String dateStr2 = "2015-01-01";
		Date dateUtil2 = TranslatorDate.transformIntoDate(dateStr2);
		Catalog catalog4 = serviceInput.getCatalog(dateUtil2);
		consolout.printCatalog(catalog4);
	}

}

