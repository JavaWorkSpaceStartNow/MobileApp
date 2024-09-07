package com.book.store.vo;

import lombok.Data;

@Data
public class BookVo {
	private int id;

	private String bookTitle;

	private String isbn;

	private int publisherId;

	private String bookFileName;

	private int borrowDuration;

	private Double borrowPrice;

	private String base64String;

}
