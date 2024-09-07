package com.book.store.BookStore.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.book.store.BookStore.model.User;
import com.book.store.BookStore.repositories.UserRepository;
import com.book.store.BookStore.service.FileUploadeService;

@Service
public class FileUploadeServiceImpl implements FileUploadeService {

	@Value("${book.store.fileupload.basepath}")
	private String basepath;

	@Autowired
	private UserRepository userRepository;

	private String createFileUploadPath(String type, long id) {
		return basepath + File.separator + type + File.separator + id;
	}

	@Override
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
		return docFinal.getName();
	}

	@Override
	public String uploadBase64Image(byte[] decodeBase64, long id, String type, String fileName)
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
		if (fileName.contains("jpg") || fileName.contains("JPG"))
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

	@Override
	public boolean deleteFile(String type, long id, String name) {
		File myObj = new File(basepath + File.separator + type + File.separator + id + File.separator + name);
		if (myObj.exists()) {
			return myObj.delete();
		}
		return false;
	}

	@Override
	public File readFile(String type, long id, String documentType) {
		if (id != 0) {
			try {
				User user = userRepository.findById(id).orElse(null);
				File imageFile = null;
				switch (type) {
				case "user": {
					imageFile = new File(basepath + File.separator + "user" + File.separator + user.getUserId()
							+ File.separator + user.getAvtar());
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

}
