package com.whck.iot.model.dmo;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the user database table.
 * 
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String email;
	
	private String username;

	private String activateCode;

	private String address;

	private String cname;

	private Date cnclDate;

	private Integer isAdmin;

	private Date logonDate;

	private String name;

	private String password;

	private String phone;

	private Date regDate;

	private String remarks;

	private Integer state;

	public User() {
	}

	public String getActivateCode() {
		return this.activateCode;
	}

	public String getAddress() {
		return this.address;
	}

	public String getCname() {
		return this.cname;
	}

	public Date getCnclDate() {
		return this.cnclDate;
	}

	public String getEmail() {
		return email;
	}

	public Integer getId() {
		return id;
	}

	public Integer getIsAdmin() {
		return this.isAdmin;
	}

	public Date getLogonDate() {
		return this.logonDate;
	}

	public String getName() {
		return this.name;
	}

	public String getPassword() {
		return this.password;
	}

	public String getPhone() {
		return this.phone;
	}

	public Date getRegDate() {
		return this.regDate;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public Integer getState() {
		return this.state;
	}

	public String getUsername() {
		return this.username;
	}

	public void setActivateCode(String activateCode) {
		this.activateCode = activateCode;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public void setCnclDate(Date cnclDate) {
		this.cnclDate = cnclDate;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public void setLogonDate(Date logonDate) {
		this.logonDate = logonDate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}