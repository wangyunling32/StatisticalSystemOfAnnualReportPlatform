package com.indeed.Util;

import com.indeed.dao.UserDaoImpl;

public class DaoFactory {
	public static UserDaoImpl getUserDao() {
		return new UserDaoImpl();
	}
}
