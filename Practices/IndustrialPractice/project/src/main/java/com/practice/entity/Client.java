package com.practice.entity;

import java.util.Date;
import javax.persistence.*;
@Entity
@Table
public class Client {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idClient;
	private String name;
	private int sex;
	@Temporal(TemporalType.DATE)
	private Date birthday;
	private String address;
	private String phonenumber;
	private String document;
	private String inn;

	public Client() {
	}

	public Client(int idClient, String name, int sex, Date birthday, String address, String phonenumber,
			String document, String inn) {
		this.idClient = idClient;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.address = address;
		this.phonenumber = phonenumber;
		this.document = document;
		this.inn = inn;
	}

	public int getIdClient() {
		return idClient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}
}
