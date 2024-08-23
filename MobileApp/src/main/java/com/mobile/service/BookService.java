package com.mobile.service;

import com.mobile.model.Book;
import com.mobile.vo.BookVo;

public interface BookService {

	Book register(BookVo bookVo);

	Book update(BookVo bookVo);

	void delete(int bookId);

}
