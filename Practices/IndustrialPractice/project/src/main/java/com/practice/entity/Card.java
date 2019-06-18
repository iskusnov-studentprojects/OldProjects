package com.practice.entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
public class Card implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCard;
    private String number;
    Integer idClient;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date validityDateBeg;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date validityDateEnd;
    private Double cardSum;
    private Integer blocked;
    private Double lastKnownBonusBalance;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastKnownBonusBalanceDate;
    private Double discountPercent;
    private String pincode;
    private Integer purchases;

    public Card() {
    }

    public Card(String number,
                Integer idClient,
                Date validityDateBeg,
                Date validityDateEnd,
                Double cardSum,
                Integer blocked,
                Double lastKnownBonusBalance,
                Date lastKnownBonusBalanceDate,
                Double discountPercent,
                String pincode,
                Integer purchases)
    {
        this.number = number;
        this.idClient = idClient;
        this.validityDateBeg = validityDateBeg;
        this.validityDateEnd = validityDateEnd;
        this.cardSum = cardSum;
        this.blocked = blocked;
        this.lastKnownBonusBalance = lastKnownBonusBalance;
        this.lastKnownBonusBalanceDate = lastKnownBonusBalanceDate;
        this.discountPercent = discountPercent;
        this.pincode = pincode;
        this.purchases = purchases;
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