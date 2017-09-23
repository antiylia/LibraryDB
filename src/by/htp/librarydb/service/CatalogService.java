package by.htp.librarydb.service;

import java.util.Date;

import by.htp.librarydb.domain.vo.Catalog;

public interface CatalogService {

	public Catalog getCatalog();
	public Catalog getCatalog(Date date);
	
	public Catalog getCatalogByNamePages (String name, int pages);
		
}
