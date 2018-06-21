package com.friend.management.FM.model;

import java.util.List;

public class CommonFriendsModel {

	private List<String> friends;
	
	private String email;

	public List<String> getFriends() {
		return friends;
	}

	public void setFriends(List<String> friends) {
		this.friends = friends;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CommonFriendsModel [friends=" + friends + ", email=" + email + "]";
	}
	
	
}
