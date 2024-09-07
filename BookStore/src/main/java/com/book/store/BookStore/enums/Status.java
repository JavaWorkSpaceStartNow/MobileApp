package com.book.store.BookStore.enums;

public enum Status {

	ISSUED("ISSUED"), COMPLETED("COMPLETED");

	private String displayStatus;

	private Status(String displayStatus) {
		this.displayStatus = displayStatus;
	}

	public String getDisplayStatus() {
		return displayStatus;
	}

}
