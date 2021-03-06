package com.friend.management.FM.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Embeddable
@Table(name = "RELATIONSHIP1")
public class Relationship implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long relationShipId;

	@Column(name = "ruserid1")
	private Long ruserid1;

	@Column(name = "ruserid2")
	private Long ruserid2;

	@Column(name = "status")
	private Long status;

	@Column(name = "action_user_id ")
	private Long actionuserid;

	public Long getRuserid1() {
		return ruserid1;
	}

	public void setRuserid1(Long ruserid1) {
		this.ruserid1 = ruserid1;
	}

	public Long getRuserid2() {
		return ruserid2;
	}

	public void setRuserid2(Long ruserid2) {
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

	@Override
	public String toString() {
		return "Relationship [relationShipId=" + relationShipId + ", ruserid1=" + ruserid1 + ", ruserid2=" + ruserid2
				+ ", status=" + status + ", actionuserid=" + actionuserid + "]";
	}

}
