package com.mobile.model;

import java.io.Serializable;

import com.mobile.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "book_iss_order")
public class BookIssueOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int id;

	@Column(name = "total_amount")
	private Double totalAmount;

	@Enumerated
	@Column(name = "order_status")
	private Status orderStatus;

}
