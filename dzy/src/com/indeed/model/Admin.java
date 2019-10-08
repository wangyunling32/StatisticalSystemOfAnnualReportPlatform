package com.indeed.model;
import java.sql.Timestamp;
public class Admin {
	private int id;
	private String  username;
	private String  password;
	private String  bumen;//部门名称
	private String gonghao;//工号
	public Admin(String name,String pass,String bumen,String gonghao)
	{
		this.username=name;
		this.password=pass;
		this.bumen=bumen;
		this.gonghao=gonghao;
	}
	public Admin() {}
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
	public String getBumen() {
		return bumen;
	}
	public void setBumen(String bumen) {
		this.bumen = bumen;
	}
	public String getGonghao() {
		return gonghao;
	}
	public void setGonghao(String gonghao) {
		this.gonghao = gonghao;
	}
	
	
}
