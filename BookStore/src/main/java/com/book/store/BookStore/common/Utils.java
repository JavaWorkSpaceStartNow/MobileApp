package com.book.store.BookStore.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static String dateToString(String format, Date sDate) {
		if (sDate != null)
			return new SimpleDateFormat(format).format(sDate);
		return "";
	}

	public static Date stringToDate(String sDate, String format) {
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(sDate);
		} catch (ParseException e) {
		}
		return date;
	}

}
