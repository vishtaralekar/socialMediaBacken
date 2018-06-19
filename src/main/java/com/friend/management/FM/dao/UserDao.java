package com.friend.management.FM.dao;

import java.util.List;

import com.friend.management.FM.domain.User;

public interface UserDao {

	public void saveUser(User user);

	public List<User> getUserList();

	public User getUser(Long id);

}
