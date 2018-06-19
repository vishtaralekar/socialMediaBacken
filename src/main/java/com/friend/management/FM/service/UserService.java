package com.friend.management.FM.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.friend.management.FM.dao.UserDaoImpl;
import com.friend.management.FM.domain.User;
import com.friend.management.FM.model.AddToUserRequest;
import com.friend.management.FM.model.SmResponseStatus;

@Service
public class UserService {

	@Autowired
	private UserDaoImpl userImpl;

	public static final Logger logger = LoggerFactory.getLogger(UserService.class);

	public SmResponseStatus addUser(AddToUserRequest addToUserRequest) {

		String message = null;
		try {

			User user = wrapUser(null, addToUserRequest);

			user.setUsername(user.getUsername());

			userImpl.saveUser(user);

			logger.info("User saved Sucessfully with name [{}] ", user.getUsername());

			message = String.format("User saved Sucessfully with name [%s] ", user.getUsername());

		} catch (Exception e) {
			String error = String.format("Error occured while saving user data with name [%s] ",
					addToUserRequest.getUsername());
			logger.error(error, e);

		}
		return new SmResponseStatus(message);

	}
	public List<User> getUserList() {

		List<User> userList = new ArrayList<>();
		try {
			userList = userImpl.getUserList();
		} catch (Exception e) {
			String error = String.format("Error occured while fetching user List");
			logger.error(error, e);
			throw e;
		}
		return userList;

	}

	public AddToUserRequest getUser(Long userid) {

		User user = null;
		AddToUserRequest addToUserRequest = new AddToUserRequest();

		try {
			user = userImpl.getUser(userid);
			
			addToUserRequest.wrapDetails(user);
		} catch (Exception e) {
			String error = String.format("Error occured while fetching user List");
			logger.error(error, e);
			throw e;
		}
		return addToUserRequest;

	}
		private User wrapUser(Long userId, AddToUserRequest addToUserRequest) {
		User user = new User();
		if (userId != null) {
			user.setUserId(userId);
		}
		user.setUsername(addToUserRequest.getUsername());
		user.setEmailId(addToUserRequest.getEmailId());
		user.setFriends(addToUserRequest.getFriends());
		user.setFollowed(addToUserRequest.getFollowed());
		user.setCreate_dt(addToUserRequest.getCreate_dt());
		user.setLast_update_dt(addToUserRequest.getLast_update_dt());
		return user;

	}
}