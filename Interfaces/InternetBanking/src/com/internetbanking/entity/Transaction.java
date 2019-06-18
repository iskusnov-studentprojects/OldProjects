package com.internetbanking.entity;

import java.sql.Time;
import java.util.Date;

public class Transaction {
	Integer id;
	Date date;
	Time time;
	String type;
	String cardNumber;
	String category;
	int summ;
	
	public Date getDate() {
		return date;
	}
	public Integer getSumm() {
		return summ;
	}
	public Time getTime() {
		return time;
	}
	public String getType() {
		return type;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setSumm(Integer summ) {
		this.summ = summ;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
