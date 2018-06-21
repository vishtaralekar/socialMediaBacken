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
@Table(name = "SUBSCRIBE")
public class Subscribe implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long subscribeId;
	
	@Column(name = "subid1")
	private Long subid1;

	@Column(name = "subid2")
	private Long subid2;

	@Column(name = "substatus")
	private Long substatus;

	public Long getSubscribeId() {
		return subscribeId;
	}

	public void setSubscribeId(Long subscribeId) {
		this.subscribeId = subscribeId;
	}

	public Long getSubid1() {
		return subid1;
	}

	public void setSubid1(Long subid1) {
		this.subid1 = subid1;
	}

	public Long getSubid2() {
		return subid2;
	}

	public void setSubid2(Long subid2) {
		this.subid2 = subid2;
	}

	public Long getSubstatus() {
		return substatus;
	}

	public void setSubstatus(Long substatus) {
		this.substatus = substatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Subscribe [subscribeId=" + subscribeId + ", subid1=" + subid1 + ", subid2=" + subid2 + ", substatus="
				+ substatus + "]";
	}

	
}
