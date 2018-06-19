package com.friend.management.FM.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.friend.management.FM.config.AppConstants;
import com.friend.management.FM.config.UriConstants;
import com.friend.management.FM.core.CustomException;
import com.friend.management.FM.domain.User;
import com.friend.management.FM.model.AddToUserRequest;
import com.friend.management.FM.model.SmResponseStatus;
import com.friend.management.FM.service.UserService;

@RestController
@RequestMapping(value = UriConstants.USERS)
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * Enpoint to Add User
	 * 
	 * @param addToUserRequest
	 * @return Return SmResponseStatus which is the response message
	 */
	@RequestMapping(value = UriConstants.BLANK, method = RequestMethod.POST, produces = AppConstants.JSON)
	public SmResponseStatus adduser(@RequestBody AddToUserRequest addToUserRequest) {

		logger.info("Request received to add user name [{}]]", addToUserRequest.getUsername());

		SmResponseStatus smResponseStatus = null;

		if (addToUserRequest.getUsername() == null) {
			String error = String.format("user name can not empty");
			logger.error(error);
			throw new CustomException(error);
		}

		smResponseStatus = userService.addUser(addToUserRequest);

		logger.info("User Sucessfully added with name [{}] ]", addToUserRequest.getUsername());
		return smResponseStatus;

	}

	@RequestMapping(value = UriConstants.USERID, method = RequestMethod.GET, produces = AppConstants.JSON)
	public AddToUserRequest getUser(@PathVariable Long userId) {

		logger.info("Request received to fetch User details by userId");

		AddToUserRequest addToUserRequest = userService.getUser(userId);

		logger.info("User by id [{}] fetched successfully", userId);

		return addToUserRequest;

	}

	/**
	 * Endpoint to get user list
	 * 
	 * @return List<User>
	 */
	@RequestMapping(value = UriConstants.BLANK, method = RequestMethod.GET, produces = AppConstants.JSON)
	public List<User> getUserList() {

		logger.info("Request received to fetch User List");

		List<User> userList = new ArrayList<>();

		userList = userService.getUserList();

		logger.info("User List fetched successfully");

		return userList;

	}
	
}
