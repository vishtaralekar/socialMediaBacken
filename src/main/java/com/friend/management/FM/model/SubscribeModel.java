package com.friend.management.FM.model;

import java.util.List;

public class SubscribeModel {
	
	private List<String> email;
	
	private String requestor;

	private String target;

	public String getRequestor() {
		return requestor;
	}

	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public List<String> getEmail() {
		return email;
	}

	public void setEmail(List<String> email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "SubscribeModel [email=" + email + ", requestor=" + requestor + ", target=" + target + "]";
	}

	

}
