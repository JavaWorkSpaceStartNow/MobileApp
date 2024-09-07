package com.book.store.BookStore.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileUploadeService {

	String fileUpload(byte[] arr, String fileName, String folder);

	String uploadBase64Image(byte[] decodeBase64, long id, String type, String fileName)
			throws FileNotFoundException, IOException;

	boolean deleteFile(String type, long id, String name);

	File readFile(String type, long id, String documentType);

}
