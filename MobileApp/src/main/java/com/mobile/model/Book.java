package com.mobile.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "book")
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private int id;

	@Column(name = "book_title")
	private String bookTitle;

	@Column(name = "isbn")
	private String isbn;

	@ManyToOne
	@JoinColumn(name = "publisher_id")
	private Publisher publisher;

	@Column(name = "book_file_name")
	private String bookFileName;

	@Column(name = "borrow_duration")
	private int borrowDuration;

	@Column(name = "borrow_price")
	private Double borrowPrice;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public String getBookFileName() {
		return bookFileName;
	}

	public void setBookFileName(String bookFileName) {
		this.bookFileName = bookFileName;
	}

	public int getBorrowDuration() {
		return borrowDuration;
	}

	public void setBorrowDuration(int borrowDuration) {
		this.borrowDuration = borrowDuration;
	}

	public Double getBorrowPrice() {
		return borrowPrice;
	}

	public void setBorrowPrice(Double borrowPrice) {
		this.borrowPrice = borrowPrice;
	}

//	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
//	private BookIssue bookIssue;

}
