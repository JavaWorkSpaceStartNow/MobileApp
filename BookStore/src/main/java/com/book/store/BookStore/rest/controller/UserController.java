package com.book.store.BookStore.rest.controller;

import java.io.IOException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.book.store.BookStore.common.BaseController;
import com.book.store.BookStore.model.User;
import com.book.store.BookStore.service.FileUploadeService;
import com.book.store.BookStore.service.UserService;
import com.book.store.BookStore.vo.AjaxResponseBody;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private FileUploadeService fileUploadeService;

	@PostMapping("/login")
	public String login(@RequestBody User user) {
		return userService.verify(user);
	}

	@GetMapping
	@ApiOperation(value = "Users List", response = AjaxResponseBody.class)
	public ResponseEntity<AjaxResponseBody> getAllUsers(Locale locale) throws IOException {
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMessage(getMessage(locale.getLanguage().toLowerCase(), "0001"));
		response.setCode("0001");
		response.setResult(userService.findAll());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Fetch User", response = AjaxResponseBody.class)
	public ResponseEntity<AjaxResponseBody> getUserById(@PathVariable Long id, Locale locale) throws IOException {
		AjaxResponseBody response = new AjaxResponseBody();
		User user = userService.findById(id);
		if (user != null) {
			response.setMessage(getMessage(locale.getLanguage().toLowerCase(), "0002"));
			response.setCode("0002");
			response.setResult(user);
		} else {
			response.setMessage(getMessage(locale.getLanguage().toLowerCase(), "0003"));
			response.setCode("0003");
			response.setResult(user);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Register User", response = AjaxResponseBody.class)
	public ResponseEntity<AjaxResponseBody> createUser(@RequestBody User user, Locale locale) throws IOException {
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMessage(getMessage(locale.getLanguage().toLowerCase(), "0004"));
		response.setCode("0004");
		response.setResult(userService.save(user));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Update User", response = AjaxResponseBody.class)
	public ResponseEntity<AjaxResponseBody> updateUser(@PathVariable Long id, @RequestBody User userDetails,
			Locale locale) throws IOException {
		AjaxResponseBody response = new AjaxResponseBody();
		User user = userService.findById(id);
		if (user != null) {
			response.setMessage(getMessage(locale.getLanguage().toLowerCase(), "0005"));
			response.setCode("0005");
			response.setResult(userService.update(user.getUserId(), userDetails));
		} else {
			response.setMessage(getMessage(locale.getLanguage().toLowerCase(), "0003"));
			response.setCode("0003");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete User", response = AjaxResponseBody.class)
	public ResponseEntity<AjaxResponseBody> deleteUser(@PathVariable Long id, Locale locale) throws IOException {
		AjaxResponseBody response = new AjaxResponseBody();
		User user = userService.findById(id);
		if (user != null) {
			userService.deleteById(id);
			response.setMessage(getMessage(locale.getLanguage().toLowerCase(), "0006"));
			response.setCode("0006");
		} else {
			response.setMessage(getMessage(locale.getLanguage().toLowerCase(), "0003"));
			response.setCode("0003");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/uplodeImage/{id}")
	@ApiOperation(value = "Uplode Image", response = AjaxResponseBody.class)
	public ResponseEntity<AjaxResponseBody> uploadeImage(@PathVariable Long id,
			@RequestPart(required = true) MultipartFile avtar, Locale locale) throws IOException {
		AjaxResponseBody response = new AjaxResponseBody();
		User user = userService.findById(id);
		if (user != null) {
			user.setAvtar(
					fileUploadeService.fileUpload(avtar.getBytes(), user.getName() + "_" + user.getUserId(), "user"));
			user.setAvtar(fileUploadeService.uploadBase64Image(avtar.getBytes(), user.getUserId(), "user",
					avtar.getOriginalFilename()));
			//fileUploadeService.deleteFile("user", user.getUserId(), user.getAvtar());
			userService.save(user);
			response.setMessage(getMessage(locale.getLanguage().toLowerCase(), "007"));
			response.setCode("007");
		} else {
			response.setMessage(getMessage(locale.getLanguage().toLowerCase(), "0003"));
			response.setCode("0003");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
