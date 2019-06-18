package com.internetbanking.entity;

import java.util.Date;

public class Card {
	Integer id;
	String login;
	String number;
	String cardName;
	Date validDate;
	String securePassword;
	Boolean mainCard;
	String type;
	public Integer getId() {
		return id;
	}
	public String getNumber() {
		return number;
	}
	public Date getValidDate() {
		return validDate;
	}
	public String getLogin() {
		return login;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getSecuredNumber(){
		String stars = "";
		for(Integer i = 0; i < number.length() - 8; i++)
			stars += "*";
		return number.subSequence(0, 4) + stars + number.substring(number.length() - 4, number.length());
	}
	public String getSecurePassword() {
		return securePassword;
	}
	public Boolean isMainCard() {
		return mainCard;
	}
	public void setMainCard(Boolean mainCard) {
		this.mainCard = mainCard;
	}
	public void setSecurePassword(String securePassword) {
		this.securePassword = securePassword;
	}
}
