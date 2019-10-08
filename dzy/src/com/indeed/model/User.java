package com.indeed.model;
import java.sql.Date;
public class User {
	private int id;
	private String  username;
	private String  password;
	private String platform_name;
	private String platform_number;
	private Date approve_date;
	private String approve_number;
	private String technosphere;
	 
	 public User(String username, String password, String platformName, String platformNo, Date approvalDate, String approvalNum, String field) {
	        this.username = username;
	        this.password = password;
	        this.platform_name = platformName;
	        this.platform_number = platformNo;
	        this.approve_date = approvalDate;
	        this.approve_number = approvalNum;
	        this.technosphere = field;
	    }
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPlatform_name() {
		return platform_name;
	}
	public void setPlatform_name(String platform_name) {
		this.platform_name = platform_name;
	}
	public String getPlatform_number() {
		return platform_number;
	}
	public void setPlatform_number(String platform_number) {
		this.platform_number = platform_number;
	}
	public Date getApprove_date() {
		return approve_date;
	}
	public void setApprove_date(Date approve_date) {
		this.approve_date = approve_date;
	}
	public String getApprove_number() {
		return approve_number;
	}
	public void setApprove_number(String approve_number) {
		this.approve_number = approve_number;
	}
	public String getTechnosphere() {
		return technosphere;
	}
	public void setTechnosphere(String technosphere) {
		this.technosphere = technosphere;
	}
	
}
