package com.friend.management.FM.model;

import java.util.List;

public class FriendsReqBody {

	private String email;

	private List<String> friends;

	public List<String> getFriends() {
		return friends;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFriends(List<String> friends) {
		this.friends = friends;
	}

	@Override
	public String toString() {
		return "FriendsReqBody [friends=" + friends + ", email=" + email + "]";
	}

}
