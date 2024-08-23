package com.mobile.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.mobile.service.FileUplodeService;

@Service
public class FileUplodeServiceImpl implements FileUplodeService {

	private String createFileUploadPath(String type, long id) {
		return "D:\\home\\book" + File.separator + type + File.separator + id;
	}

	@Override
	public String uploadBase64Image(byte[] decodeBase64, long id, String type, String fileName) throws IOException {
		File imagePathFile = new File(createFileUploadPath(type, id));
		if (!imagePathFile.exists()) {
			imagePathFile.mkdirs();
		}
		BufferedImage bfImage = null;
		BufferedImage newBufferedImage = null;
		File imageFinal;
		String extension = fileName.split("\\.")[1];
		// String imageFileName = null;
		try {
			bfImage = ImageIO.read(new ByteArrayInputStream(decodeBase64));
			if (bfImage != null) {
				newBufferedImage = new BufferedImage(bfImage.getWidth(), bfImage.getHeight(),
						extension.equalsIgnoreCase("png") ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
				newBufferedImage.createGraphics().drawImage(bfImage, 0, 0, null);
			}
			// imageFileName = String.format("%s/%d/%s", type, id, fileName);
			imageFinal = new File(imagePathFile.getPath() + File.separator + fileName);
			if (bfImage != null) {
				ImageIO.write(newBufferedImage, extension, imageFinal);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bfImage.flush();
			newBufferedImage.flush();
		}

		File file = docsDowload(fileName);
		Path path = Paths.get(file.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.set("Content-Disposition", "attachment; filename=" + type);
		return fileName;
	}

	private File docsDowload(String fileName) {
		File dowloadFile = null;
		String tempDir = System.getProperty("java.io.tmpdir");
		tempDir = tempDir + "export" + File.separator;
		File docsFilePath = new File(tempDir);
		if (!docsFilePath.exists()) {
			docsFilePath.mkdirs();
		}
		dowloadFile = new File(docsFilePath.getPath() + File.separator + fileName);
		return dowloadFile;
	}

}
