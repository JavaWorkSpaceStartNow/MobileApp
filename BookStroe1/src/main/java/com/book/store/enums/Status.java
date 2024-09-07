package com.book.store.enums;

import java.io.Serializable;

public enum Status implements Serializable {

	ISSUED("ISSUED"), COMPLETED("COMPLETED");

	private String displayStatus;

	private Status(String displayStatus) {
		this.displayStatus = displayStatus;
	}

	public String getDisplayStatus() {
		return displayStatus;
	}

}