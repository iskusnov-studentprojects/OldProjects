package com.entity;

import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Work
 */
@Entity
@Table(name = "Card")
public class Card {
	@Id
	@Column(name = "idCard")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idCard;
	String number;
	Integer idClient;
	@Temporal(javax.persistence.TemporalType.DATE)
	Date validityDateBeg;
	@Temporal(javax.persistence.TemporalType.DATE)
	Date validityDateEnd;
	Double cardSum;
	Integer blocked;
	Double lastKnownBonusBalance;
	@Temporal(javax.persistence.TemporalType.DATE)
	Date lastKnownBonusBalanceDate;
	Double discountPercent;
	String pincode;
	Integer purchases;

	public Card() {
	}

	public Card(String _number, Integer _idClient, Date _validityDateBeg, Date _validityDateEnd, Double _cardSum,
			Integer _blocked, Double _lastKnownBonusBalance, Date _lastKnownBonusBalanceDate, Double _discountPercent,
			String _pincode, Integer _purchases) {
		number = _number;
		idClient = _idClient;
		validityDateBeg = _validityDateBeg;
		validityDateEnd = _validityDateEnd;
		cardSum = _cardSum;
		blocked = _blocked;
		lastKnownBonusBalance = _lastKnownBonusBalance;
		lastKnownBonusBalanceDate = _lastKnownBonusBalanceDate;
		discountPercent = _discountPercent;
		pincode = _pincode;
		purchases = _purchases;
	}

	public Integer getIdCard() {
		return idCard;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public Date getValidityDateBeg() {
		return validityDateBeg;
	}

	public void setValidityDateBeg(Date validityDateBeg) {
		this.validityDateBeg = validityDateBeg;
	}

	public Date getValidityDateEnd() {
		return validityDateEnd;
	}

	public void setValidityDateEnd(Date validityDateEnd) {
		this.validityDateEnd = validityDateEnd;
	}

	public Double getCardSum() {
		return cardSum;
	}

	public void setCardSum(Double cardSum) {
		this.cardSum = cardSum;
	}

	public Integer getBlocked() {
		return blocked;
	}

	public void setBlocked(Integer blocked) {
		this.blocked = blocked;
	}

	public Double getLastKnownBonusBalance() {
		return lastKnownBonusBalance;
	}

	public void setLastKnownBonusBalance(Double lastKnownBonusBalance) {
		this.lastKnownBonusBalance = lastKnownBonusBalance;
	}

	public Date getLastKnownBonusBalanceDate() {
		return lastKnownBonusBalanceDate;
	}

	public void setLastKnownBonusBalanceDate(Date lastKnownBonusBalanceDate) {
		this.lastKnownBonusBalanceDate = lastKnownBonusBalanceDate;
	}

	public Double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Integer getPurchases() {
		return purchases;
	}

	public void setPurchases(Integer purchases) {
		this.purchases = purchases;
	}

}
