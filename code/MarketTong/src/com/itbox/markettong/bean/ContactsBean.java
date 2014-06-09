package com.itbox.markettong.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class ContactsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int ITEM = 0;
	public static final int SECTION = 1;
	private int type;
	private String letter;
	private long id;
	private long photoId;
	private String name;
	private String companyName;
	private String comPosition;
	private String phone1;
	private String phone2;
	private String phone3;
	private String email;
	private String qq;
	private String other1;
	private String other2;
	private String address;
	private ArrayList<String> list = new ArrayList<String>();
    private String lastName;
    
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		if (letter != null && letter.length() > 1) {
			letter = letter.substring(0, 1).toUpperCase();
		}
		this.letter = letter;
	}

	public void addPhone(String phone) {
		list.add(phone);
	}

	public ArrayList<String> getPhones() {
		return list;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getComPosition() {
		return comPosition;
	}

	public void setComPosition(String comPosition) {
		this.comPosition = comPosition;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String emali) {
		this.email = emali;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getOther1() {
		return other1;
	}

	public void setOther1(String other1) {
		this.other1 = other1;
	}

	public String getOther2() {
		return other2;
	}

	public void setOther2(String other2) {
		this.other2 = other2;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private int contactId;

	public long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(long photoId) {
		this.photoId = photoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

}
