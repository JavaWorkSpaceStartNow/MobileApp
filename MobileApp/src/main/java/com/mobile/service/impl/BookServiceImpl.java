package com.mobile.service.impl;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.model.Book;
import com.mobile.model.Publisher;
import com.mobile.repositories.BookRepositery;
import com.mobile.repositories.PublisherRepository;
import com.mobile.service.BookService;
import com.mobile.service.FileUplodeService;
import com.mobile.vo.BookVo;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepositery bookRepositery;

	@Autowired
	private PublisherRepository publisherRepository;

	@Autowired
	private FileUplodeService fileUplodeService;

	@Override
	public Book register(BookVo bookVo) {
		Book book = new Book();
		book.setBookTitle(bookVo.getBookTitle());
		book.setIsbn(bookVo.getIsbn());
		Publisher publisher = publisherRepository.findById(bookVo.getPublisherId()).orElse(null);
		book.setPublisher(publisher);
		book.setBorrowDuration(bookVo.getBorrowDuration());
		book.setBorrowPrice(bookVo.getBorrowPrice());
		bookRepositery.save(book);
		String base64 = bookVo.getBase64String();
		if (base64 != null && !base64.isEmpty()) {
			String frontExt = base64.split(",")[0].split("/")[1].split(";")[0];// data:image/png;base64
			try {
				book.setBookFileName(fileUplodeService.uploadBase64Image(Base64.getDecoder().decode((base64.split(","))[1]),
						book.getId(), "books", book.getId() + "." + frontExt));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bookRepositery.save(book);
	}

	@Override
	public Book update(BookVo bookVo) {
		Book book = bookRepositery.findById(bookVo.getId()).orElse(null);
		if (book != null) {
			book.setBookTitle(bookVo.getBookTitle());
			book.setIsbn(bookVo.getIsbn());
			Publisher publisher = publisherRepository.findById(bookVo.getPublisherId()).orElse(null);
			book.setPublisher(publisher);
			book.setBorrowDuration(bookVo.getBorrowDuration());
			book.setBorrowPrice(bookVo.getBorrowPrice());
			bookRepositery.save(book);
			return book;
		}
		return null;
	}

	@Override
	public void delete(int bookId) {
		bookRepositery.deleteById(bookId);
	}

}
