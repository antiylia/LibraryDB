package by.htp.librarydb.domain.entity;

import java.io.Serializable;
import java.util.Date;

import by.htp.librarydb.dao.utils.TranslatorDate;

public class Book implements Serializable {

	private static final long serialVersionUID = -8255262278759192704L;

	private int id;
	private String title;
	private int pages;
	private int author_id;
	private Date date;

	public Book(int id, String title, int pages, int author_id) {
		super();
		this.id = id;
		this.title = title;
		this.pages = pages;
		this.author_id = author_id;
	}

	public Book(int id, String title, int pages, int author_id, Date date) {
		this.id = id;
		this.title = title;
		this.pages = pages;
		this.author_id = author_id;
		this.date = date;
	}

	public Book() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", pages=" + pages + ", author_id=" + author_id + ", date="
				+ TranslatorDate.transformDateIntoString(date) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + author_id;
		result = prime * result + id;
		result = prime * result + pages;
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
		Book other = (Book) obj;
		if (author_id != other.author_id)
			return false;
		if (id != other.id)
			return false;
		if (pages != other.pages)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
