package com.friend.management.FM.model;

import java.util.List;

public class ReceiveUpdateModel {

	private boolean success;
	private List<String> recipient;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<String> getRecipient() {
		return recipient;
	}
	public void setRecipient(List<String> recipient) {
		this.recipient = recipient;
	}
	@Override
	public String toString() {
		return "ReceiveUpdateModel [success=" + success + ", recipient=" + recipient + "]";
	}

	
}
