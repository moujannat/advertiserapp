package com.spring.projects.advertiserapp.model;

//@Data
public class Advertiser {

	private String name;
	private String contactName;
	private double creditLimit;

	public Advertiser(String name, String contactName, double creditLimit) {
		super();
		this.name = name;
		this.contactName = contactName;
		this.creditLimit = creditLimit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

}
