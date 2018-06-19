package com.friend.management.FM.model;

public class RelationshipModel {
	
	

	private AddToUserRequest ruserid1;

	private AddToUserRequest ruserid2;

	private Long status;

	private Long actionuserid;

	public AddToUserRequest getRuserid1() {
		return ruserid1;
	}

	public void setRuserid1(AddToUserRequest ruserid1) {
		this.ruserid1 = ruserid1;
	}

	public AddToUserRequest getRuserid2() {
		return ruserid2;
	}

	public void setRuserid2(AddToUserRequest ruserid2) {
		this.ruserid2 = ruserid2;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getActionuserid() {
		return actionuserid;
	}

	public void setActionuserid(Long actionuserid) {
		this.actionuserid = actionuserid;
	}

	/*public void warpDetails(Relationship relationship) {
		AddToUserRequest addreq = new AddToUserRequest();
		addreq.wrapDetails(relationship.getRuserid1());
		this.ruserid1 = addreq;

		AddToUserRequest addreq1 = new AddToUserRequest();
		addreq1.wrapDetails(relationship.getRuserid2());
		this.ruserid2 = addreq1;

		this.status = relationship.getStatus();
		this.actionuserid = relationship.getActionuserid();
	}*/
}
