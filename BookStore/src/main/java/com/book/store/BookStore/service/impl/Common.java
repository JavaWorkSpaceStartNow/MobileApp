package com.book.store.BookStore.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.book.store.BookStore.model.Book;

public class Common {
	@Value("${book.store.fileupload.basepath}")
	private String basepath;

	private List<String> imageMessages = new ArrayList<>();

	private String createFileUploadPath(String type, long id) {
		return basepath + File.separator + type + File.separator + id;
	}

	// Used For File Export & Download
	public String docsUpload(byte[] arr, String fileName) {
		String tempDir = System.getProperty("java.io.tmpdir");
		tempDir = tempDir + "export" + File.separator;
		File docsFilePath = new File(tempDir);
		if (!docsFilePath.exists()) {
			docsFilePath.mkdirs();
		}
		File docFinal = new File(docsFilePath.getPath() + File.separator + fileName);
		try {
			Files.write(docFinal.toPath(), arr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempDir + docFinal.getName();
	}

	// Used For File Export & Download
	public File docsDowload(String fileName) {
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

	public String uploadBase64Image(byte[] decodeBase64, long id, String type, String fileName, String positionType)
			throws FileNotFoundException, IOException {
		File imagePathFile = new File(createFileUploadPath(type, id));
		if (!imagePathFile.exists()) {
			imagePathFile.mkdirs();
		}
		BufferedImage bfImage = null;
		BufferedImage newBufferedImage = null;
		File imageFinal = null;
		String extension = "";
		if (fileName.contains("png"))
			extension = "png";
		if (fileName.contains("jpeg"))
			extension = "jpeg";
		if (fileName.contains("jpg"))
			extension = "jpg";
		if (fileName.contains("svg"))
			extension = "svg";

		// System.out.println("---"+fileName);

		if (!extension.equals("svg")) {
			// System.out.println("---");
			try {
				bfImage = ImageIO.read(new ByteArrayInputStream(decodeBase64));
				if (bfImage != null) {
					newBufferedImage = new BufferedImage(bfImage.getWidth(), bfImage.getHeight(),
							fileName.contains("png") ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
					newBufferedImage.createGraphics().drawImage(bfImage, 0, 0, null);
				}
				if (!StringUtils.isEmpty(positionType))
					imageFinal = new File(
							imagePathFile.getPath() + File.separator + id + "_" + positionType + "." + extension);
				else
					imageFinal = new File(imagePathFile.getPath() + File.separator + id + "." + extension);
				if (bfImage != null) {
					ImageIO.write(newBufferedImage, extension, imageFinal);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bfImage != null)
					bfImage.flush();
				if (newBufferedImage != null)
					newBufferedImage.flush();
			}
		} else if (extension.equals("svg")) {
			IOUtils.copy(new ByteArrayInputStream(decodeBase64),
					new FileOutputStream(imagePathFile.getPath() + File.separator + id + ".svg"));
			return id + ".svg";
		}
		return imageFinal.getName();
	}

	public File readFile(String type, long id, String documentType) {
		if (id != 0) {
			try {
				Book book = null;
				File imageFile = null;
				switch (type) {
				case "book": {
					imageFile = new File(basepath + File.separator + "book" + File.separator + book.getBookId()
							+ File.separator + book.getBookFileName());
					break;
				}
				}
				if (imageFile != null && imageFile.exists()) {
					return imageFile;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} else
			return null;
	}

	public String uploadImage(MultipartFile imageFile, long id, String type) {
		imageMessages = new ArrayList<>();
		try {
			if (imageFile == null || imageFile.isEmpty()) {
				return null;
			}
			String extension = imageFile.getOriginalFilename().substring(imageFile.getOriginalFilename().length() - 3);
			if (extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("png")) {
			} else {
				imageMessages.add("Image extension must be JPG or PNG.");
				return null;
			}
			String imagePath = createFileUploadPath(type, id);
			String fileName = String.format("%d.%s", id, extension);
			File imagePathFile = new File(imagePath);
			if (!imagePathFile.exists()) {
				imagePathFile.mkdirs();
			}
			try {
				BufferedImage bfImage = null;
				if (imageFile != null) {
					bfImage = ImageIO.read(new ByteArrayInputStream(imageFile.getBytes()));
				}
				File imageFinal = new File(imagePathFile.getPath() + File.separator + fileName);
				if (bfImage != null) {
					ImageIO.write(bfImage, extension, imageFinal);
					bfImage.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteFile(String type, long id, String name) {
		File myObj = new File(basepath + File.separator + type + File.separator + id + File.separator + name);
		if (myObj.exists()) {
			return myObj.delete();
		}
		return false;
	}

	public boolean deleteFileByPath(String fullPath) {
		File myObj = new File(fullPath);
		if (myObj.exists()) {
			return myObj.delete();
		}
		return false;
	}

	public String fileUpload(byte[] arr, String fileName, String folder) {
		String filePath = basepath + File.separator + folder + File.separator;
		File docsFilePath = new File(filePath);
		if (!docsFilePath.exists()) {
			docsFilePath.mkdirs();
		}
		File docFinal = new File(docsFilePath.getPath() + File.separator + fileName);
		try {
			Files.write(docFinal.toPath(), arr);
		} catch (IOException e) {
		}
		return filePath + docFinal.getName();
	}

}
