package com.book.store.BookStore.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.store.BookStore.model.Roles;
import com.book.store.BookStore.model.User;
import com.book.store.BookStore.repositories.RoleRepository;
import com.book.store.BookStore.repositories.UserRepository;
import com.book.store.BookStore.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public String verify(User user) {
		return null;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		return optionalUser.orElse(null);
	}

	@Override
	public User save(User user) {
		if (user.getUserId() != 0) {
			Roles role = roleRepository.findByName(user.getRole());
			Set<Roles> roles = new HashSet<Roles>();
			roles.add(role);
			user.setRoles(roles);
			user.setRegistrationDate(new Date());
		} else {
			user.setUpdatedDate(new Date());
		}
		return userRepository.saveAndFlush(user);
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User update(Long id, User userDetails) {
		User user = userRepository.findById(id).orElse(null);
		user.setName(userDetails.getName());
		user.setUpdatedDate(new Date());
		user.setUsername(userDetails.getUsername());
		user.setPassword(userDetails.getPassword());
		Roles role = roleRepository.findByName(userDetails.getRole());
		Set<Roles> roles = new HashSet<Roles>();
		roles.add(role);
		user.setRoles(roles);
		return userRepository.save(user);
	}

}
