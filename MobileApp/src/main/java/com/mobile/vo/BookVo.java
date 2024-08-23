package com.mobile.vo;

public class BookVo {

	private int id;

	private String bookTitle;

	private String isbn;

	private int publisherId;

	private String bookFileName;

	private int borrowDuration;

	private Double borrowPrice;

	private String base64String;

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

	public int getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
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

	public String getBase64String() {
		return base64String;
	}

	@Override
	public String toString() {
		return "BookVo [bookTitle=" + bookTitle + ", isbn=" + isbn + ", publisherId=" + publisherId + ", bookFileName="
				+ bookFileName + ", borrowDuration=" + borrowDuration + ", borrowPrice=" + borrowPrice + "]";
	}

}
