package com.indeed.dao;

import java.util.List;

import com.indeed.model.User;

public interface IUserDao {
	public void add(User user);
	public User load(int id);
	public User load(String username);
	public User load(String username,String password);
	List<User> loadUser(String username);
}
