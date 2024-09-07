package com.book.store.BookStore.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "book")
public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;

	@Column(name = "book_title", nullable = false)
	private String bookTitle;

	@Column(name = "isbn")
	private String isbn;

	@ManyToOne
	@JoinColumn(name = "publisher_id", nullable = false)
	private Publisher publisher;

	@Column(name = "book_file_name")
	private String bookFileName;

	@Column(name = "borrow_duration")
	private Integer borrowDuration;

	@Column(name = "borrow_price")
	private Double borrowPrice;

	@OneToMany(mappedBy = "book")
	private List<BookIssue> bookIssues;

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
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

	public Integer getBorrowDuration() {
		return borrowDuration;
	}

	public void setBorrowDuration(Integer borrowDuration) {
		this.borrowDuration = borrowDuration;
	}

	public Double getBorrowPrice() {
		return borrowPrice;
	}

	public void setBorrowPrice(Double borrowPrice) {
		this.borrowPrice = borrowPrice;
	}

	public List<BookIssue> getBookIssues() {
		return bookIssues;
	}

	public void setBookIssues(List<BookIssue> bookIssues) {
		this.bookIssues = bookIssues;
	}

}
