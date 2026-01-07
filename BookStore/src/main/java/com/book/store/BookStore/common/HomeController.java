package com.book.store.BookStore.common;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.store.BookStore.service.FileUploadeService;

@Controller
public class HomeController {

	@Autowired
	private FileUploadeService fileUploadService;

	@RequestMapping(value = { "/image/{type}/{id}" }, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImage(@PathVariable("type") String type, @PathVariable("id") int id,
			@RequestParam(name = "identityDocumentType", required = false) String identityDocumentType)
			throws IOException {
		File photoFile = null;
		byte[] imageContent = null;
		try {
			photoFile = fileUploadService.readFile(type, id, identityDocumentType);
			System.out.println("===>" + photoFile);
			if (photoFile != null) {
				InputStream in1 = new FileInputStream(photoFile);
				imageContent = IOUtils.toByteArray(in1);
			}
			System.err.println("===="+imageContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		final HttpHeaders headers = new HttpHeaders();
		if (photoFile != null && photoFile.isFile() && photoFile.getName().endsWith(".svg"))
			headers.set("Content-Type", "image/svg+xml");
		else
			headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	}
}
