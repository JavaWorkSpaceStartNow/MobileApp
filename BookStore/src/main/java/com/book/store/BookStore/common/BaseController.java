package com.book.store.BookStore.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseController {

	public static String getMessage(String lang, String key) throws java.io.IOException {
		String filePath = "";
		if (lang.split(",")[0].equals("en")) {
			filePath = "/common/messages.properties";
		} else {
			filePath = "/common/messages_" + lang.split(",")[0] + ".properties";
		}
		Properties properties = new Properties();
		try {
			InputStream fis = BaseController.class.getResourceAsStream(filePath);
			properties.load(fis);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(key);
	}

}
