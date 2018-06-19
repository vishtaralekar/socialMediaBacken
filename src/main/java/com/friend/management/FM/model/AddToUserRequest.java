package com.friend.management.FM.model;

import java.time.LocalDateTime;

import com.friend.management.FM.domain.User;

public class AddToUserRequest {

	private Long userId;

	private String username;

	private String emailId;

	private String friends;

	private String followed;

	private LocalDateTime create_dt;

	private LocalDateTime last_update_dt;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFriends() {
		return friends;
	}

	public void setFriends(String friends) {
		this.friends = friends;
	}

	public String getFollowed() {
		return followed;
	}

	public void setFollowed(String followed) {
		this.followed = followed;
	}

	public LocalDateTime getCreate_dt() {
		return create_dt;
	}

	public void setCreate_dt(LocalDateTime create_dt) {
		this.create_dt = create_dt;
	}

	public LocalDateTime getLast_update_dt() {
		return last_update_dt;
	}

	public void setLast_update_dt(LocalDateTime last_update_dt) {
		this.last_update_dt = last_update_dt;
	}

	public void wrapDetails(User user) {
		this.userId = user.getUserId();
		this.username = user.getUsername();
		this.emailId = user.getEmailId();
		this.friends = user.getFriends();
		this.followed = user.getFollowed();
		this.create_dt = user.getCreate_dt();
		this.last_update_dt = user.getLast_update_dt();

	}

}