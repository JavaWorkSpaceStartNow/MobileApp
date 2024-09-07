package com.book.store.BookStore.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "book_issue")
public class BookIssue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookIssueId;

	@ManyToOne
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "issue_date")
	private LocalDateTime issueDate;

	@Column(name = "return_date")
	private LocalDateTime returnDate;

	@Column(name = "date_returned")
	private LocalDateTime dateReturned;

	@Column(name = "borrow_amount")
	private Double borrowAmount;

	@Column(name = "late_return_fee_amount")
	private Double lateReturnFeeAmount;

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private BookIssOrder bookIssOrder;

	public Long getBookIssueId() {
		return bookIssueId;
	}

	public void setBookIssueId(Long bookIssueId) {
		this.bookIssueId = bookIssueId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDateTime issueDate) {
		this.issueDate = issueDate;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	public LocalDateTime getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(LocalDateTime dateReturned) {
		this.dateReturned = dateReturned;
	}

	public Double getBorrowAmount() {
		return borrowAmount;
	}

	public void setBorrowAmount(Double borrowAmount) {
		this.borrowAmount = borrowAmount;
	}

	public Double getLateReturnFeeAmount() {
		return lateReturnFeeAmount;
	}

	public void setLateReturnFeeAmount(Double lateReturnFeeAmount) {
		this.lateReturnFeeAmount = lateReturnFeeAmount;
	}

	public BookIssOrder getBookIssOrder() {
		return bookIssOrder;
	}

	public void setBookIssOrder(BookIssOrder bookIssOrder) {
		this.bookIssOrder = bookIssOrder;
	}

}
