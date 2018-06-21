package com.friend.management.FM.model;

public class ReceiveUpdateReqBody {
	
	private String sender;
	
	private String text;
	
	private String target;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "ReceiveUpdateReqBody [sender=" + sender + ", text=" + text + ", target=" + target + "]";
	}
	
	

}
