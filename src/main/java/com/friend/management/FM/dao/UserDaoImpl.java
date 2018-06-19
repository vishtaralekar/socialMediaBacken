package com.friend.management.FM.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.friend.management.FM.domain.User;
import com.friend.management.FM.repository.UserRepository;

@Component
public class UserDaoImpl implements UserDao {

	public static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public void saveUser(User user) {

		try {
			userRepository.save(user);
		} catch (Exception e) {
			String error = String.format("Error occured while saving user with name [%s] ", user.getUsername());
			logger.error(error);
			throw e;

		}
	}

	@Override
	public User getUser(Long userid) {
		User user = new User();
		try {
			user = userRepository.getOne(userid);
		} catch (Exception e) {
			String error = String.format("Error occured while fetching user details");
			logger.error(error);
			throw e;
		}
		return user;

	}
	@Override
	public List<User> getUserList() {

		List<User> userList = new ArrayList<User>();
		try {
			userList = userRepository.findAll();
		} catch (Exception e) {
			String error = String.format("Error occured while fetching user list");
			logger.error(error);
			throw e;
		}
		return userList;
	}
	

}
