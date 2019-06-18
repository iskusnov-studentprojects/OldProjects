package com.internetbanking.entity;

import java.util.Date;

public class Autorecharge {
	Integer id;
	Integer idCard;
	String login;
	Boolean byTime;
	Date timeSinceDate;
	Date timeToDate;
	Integer time;
	Integer timeSumm;
	Boolean byAmount;	
	Date amountSinceDate;
	Date amountToDate;
	Integer amountSumm;
	Integer amountRechargeSumm;
	public Date getAmountSinceDate() {
		return amountSinceDate;
	}public Integer getAmountSumm() {
		return amountSumm;
	}public Date getAmountToDate() {
		return amountToDate;
	}public Integer getAmountRechargeSumm() {
		return amountRechargeSumm;
	}public Boolean getByAmount() {
		return byAmount;
	}public Boolean getByTime() {
		return byTime;
	}public Integer getId() {
		return id;
	}public Integer getIdCard() {
		return idCard;
	}public String getLogin() {
		return login;
	}public Integer getTime() {
		return time;
	}public Date getTimeSinceDate() {
		return timeSinceDate;
	}public Integer getTimeSumm() {
		return timeSumm;
	}public Date getTimeToDate() {
		return timeToDate;
	}public void setAmountSinceDate(Date amountSinceDate) {
		this.amountSinceDate = amountSinceDate;
	}public void setAmountSumm(Integer amountSumm) {
		this.amountSumm = amountSumm;
	}public void setAmountToDate(Date amountToDate) {
		this.amountToDate = amountToDate;
	}public void setByAmount(Boolean byAmount) {
		this.byAmount = byAmount;
	}public void setAmountRechargeSumm(Integer amountRechargeSumm) {
		this.amountRechargeSumm = amountRechargeSumm;
	}public void setByTime(Boolean byTime) {
		this.byTime = byTime;
	}public void setIdCard(Integer idCard) {
		this.idCard = idCard;
	}public void setId(Integer id) {
		this.id = id;
	}public void setLogin(String login) {
		this.login = login;
	}public void setTime(Integer time) {
		this.time = time;
	}public void setTimeSinceDate(Date timeSinceDate) {
		this.timeSinceDate = timeSinceDate;
	}public void setTimeSumm(Integer timeSumm) {
		this.timeSumm = timeSumm;
	}public void setTimeToDate(Date timeToDate) {
		this.timeToDate = timeToDate;
	}
}
