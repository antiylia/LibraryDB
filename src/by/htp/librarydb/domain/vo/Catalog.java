package by.htp.librarydb.domain.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import by.htp.librarydb.dao.utils.TranslatorDate;
import by.htp.librarydb.domain.entity.Book;

public class Catalog implements Serializable {

	private static final long serialVersionUID = 5771179785979919804L;

	private String title;
	private Date dateCreation;
	private List<Book> books;

	public Catalog(String title, Date dateCreation, List<Book> books) {
		this.title = title;
		this.dateCreation = dateCreation;
		this.books = books;
	}

	public Catalog() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Catalog [title=" + title + ", dateCreation=" + TranslatorDate.transformDateIntoString(dateCreation)
				+ ", books=" + books + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((books == null) ? 0 : books.hashCode());
		result = prime * result + ((dateCreation == null) ? 0 : dateCreation.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Catalog other = (Catalog) obj;
		if (books == null) {
			if (other.books != null)
				return false;
		} else if (!books.equals(other.books))
			return false;
		if (dateCreation == null) {
			if (other.dateCreation != null)
				return false;
		} else if (!dateCreation.equals(other.dateCreation))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
