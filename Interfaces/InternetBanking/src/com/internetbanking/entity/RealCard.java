package com.internetbanking.entity;

import java.util.Date;

public class RealCard {
	String number;
	String firstName;
	String lastName;
	Date validDate;
	String securePassword;
	String type;
	Integer cash;
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getNumber() {
		return number;
	}
	public String getSecurePassword() {
		return securePassword;
	}
	public Date getValidDate() {
		return validDate;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public void setSecurePassword(String securePassword) {
		this.securePassword = securePassword;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getCash() {
		return cash;
	}
	public void setCash(Integer cash) {
		this.cash = cash;
	}
}
