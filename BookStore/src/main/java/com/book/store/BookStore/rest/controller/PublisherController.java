package com.book.store.BookStore.rest.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.store.BookStore.common.BaseController;
import com.book.store.BookStore.model.Publisher;
import com.book.store.BookStore.service.PublisherService;
import com.book.store.BookStore.vo.AjaxResponseBody;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController extends BaseController {

	@Autowired
	private PublisherService publisherService;

	@GetMapping
	public ResponseEntity<List<Publisher>> getAllPublishers() {
		return ResponseEntity.ok(publisherService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<AjaxResponseBody> getPublisherById(@PathVariable long id, Locale locale) throws IOException {
		AjaxResponseBody response = new AjaxResponseBody();
		Publisher publisher = publisherService.findById(id);
		response.setMessage(getMessage(locale.getLanguage().toLowerCase(), "0001"));
		response.setCode("0001");
		response.setResult(publisher);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
