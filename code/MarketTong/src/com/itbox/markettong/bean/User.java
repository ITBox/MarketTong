package com.itbox.markettong.bean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "user", id = User.ID)
public class User extends Model {

	public static final String ID = "_id";
	public static final String USER_NAME = "u_name";
	public static final String USER_PHONE = "u_phone";
	public static final String USER_COMPANY = "u_company";
	public static final String USER_COMPANY_POS = "u_company_pos";

	@Column(name = User.USER_NAME)
	private String userName;// 姓名
	@Column(name = User.USER_PHONE)
	private String userPhone;// 电话
	@Column(name = User.USER_COMPANY)
	private String userCompany;// 公司
	@Column(name = User.USER_COMPANY_POS)
	private String userCompanyPos;// 职位

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserCompany() {
		return userCompany;
	}

	public void setUserCompany(String userCompany) {
		this.userCompany = userCompany;
	}

}
