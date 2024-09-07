package com.book.store.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

	String docsUpload(byte[] arr, String fileName);

	File docsDowload(String fileName);

	String uploadBase64Image(
			byte[] decodeBase64, long id, String type, String fileName, String positionType)
			throws FileNotFoundException, IOException;

	File readFile(String type, long id, String documentType);

	String uploadImage(MultipartFile imageFile, long id, String type);

	boolean deleteFile(String type, long id, String name);

	boolean deleteFileByPath(String fullPath);

}
