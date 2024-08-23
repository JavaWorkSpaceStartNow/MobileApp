package com.mobile.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "book_issue")
public class BookIssue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_issue_id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@Column(name = "issue_date", nullable = false, updatable = false)
	private Date issueDate;

	@Column(name = "return_date", nullable = false)
	private Date returnDate;

	@Column(name = "date_returned", nullable = false)
	private Date dateReturned;

	@Column(name = "borrow_amount")
	private Double borrowAmount;

	@Column(name = "late_return_fee_amount")
	private Double lateReturnFeeAmount;

}
