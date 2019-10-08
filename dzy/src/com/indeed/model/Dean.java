package com.indeed.model;

import java.sql.Timestamp;

//平台主任（院长）
public class Dean {
 
    private int userid;	
    private int id;

    private String name;

    private String sex;

    private Timestamp date;

    private String jobTitle;

    private String major;

    private String education;

    private String degree;

    private String telephone;

    private String mobilephone;

    private String email;

    public Dean() {
    }

    public Dean(String name, String sex, Timestamp date, String jobTitle, String major, String education, String degree, String telephone, String mobilephone, String email,int userid) {
        this.name = name;
        this.sex = sex;
        this.date = date;
        this.jobTitle = jobTitle;
        this.major = major;
        this.education = education;
        this.degree = degree;
        this.telephone = telephone;
        this.mobilephone = mobilephone;
        this.email = email;
        this.userid=userid;
    }
    
    public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
