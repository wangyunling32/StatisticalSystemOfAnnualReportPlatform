package com.indeed.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.indeed.Util.DBUtil;
import com.indeed.Util.UserException;
import com.indeed.model.User;

import sun.net.www.content.text.plain;

public class UserDaoImpl implements IUserDao {

	@Override
	public void add(User user) {
		//获得链接对象
		Connection connection = DBUtil.getConnection();
		//准备sql语句
		String sql = "select count(*) from users where username = ?";
		//创建语句传输对象
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			//接收结果集
			resultSet = preparedStatement.executeQuery();
			//遍历结果集
			while(resultSet.next()) {
				if (resultSet.getInt(1) > 0) {
					throw new UserException("用户已存在") ;
				}
			}
			
			sql = "INSERT INTO users(username,password,platform_name,platform_number,approve_date,approve_number,technosphere) VALUES (?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getPlatform_name());
			preparedStatement.setString(4, user.getPlatform_number());
			preparedStatement.setDate(5, user.getApprove_date());
			preparedStatement.setString(6, user.getApprove_number());
			preparedStatement.setString(7, user.getTechnosphere());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭资源
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		
	}

	@Override
	public User load(int id) {
		// TODO Auto-generated method stub
		Connection con = DBUtil.getConnection();
        String sql = "SELECT * FROM users WHERE id = ?";
        PreparedStatement ps = null;
        User user = null;
        ResultSet rs = null;
        try {
            user = new User();
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()){
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPlatform_name(rs.getString("platform_name"));
                user.setPlatform_number(rs.getString("platform_number"));
                user.setApprove_date(rs.getDate("approve_date"));
                user.setApprove_number(rs.getString("approve_number"));
                user.setPassword(rs.getString("password"));
                user.setTechnosphere(rs.getString("technosphere"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return  user;
	}

	public List<User> loadUser(String username){

        Connection con = DBUtil.getConnection();
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement ps = null;
        List<User> users = new ArrayList<User>();
        ResultSet rs = null;
        try {
            User user = new User();
            ps = con.prepareStatement(sql);
            ps.setString(1,username);
            rs = ps.executeQuery();
            while (rs.next()){
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPlatform_name(rs.getString("platform_name"));
                user.setPlatform_number(rs.getString("platform_number"));
                user.setApprove_date(rs.getDate("approve_date"));
                user.setApprove_number(rs.getString("approve_number"));
                user.setPassword(rs.getString("password"));
                user.setTechnosphere(rs.getString("technosphere"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

	@Override
	public User load(String username, String password) {
		// TODO Auto-generated method stub
		 Connection con = DBUtil.getConnection();
	        String sql = "SELECT * FROM users WHERE username = ? and password = ?";
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        User user = null;
	        try {
	            ps = con.prepareStatement(sql);
	            ps.setString(1,username);
	            ps.setString(2,password);
	            rs = ps.executeQuery();
	            while (rs.next()){      //查询到用户
	                user = new User();
	                user.setId(rs.getInt("id"));
	                user.setUsername(rs.getString("username"));
	                user.setPlatform_name(rs.getString("platform_name"));
	                user.setPlatform_number(rs.getString("platform_number"));
	                user.setApprove_date(rs.getDate("approve_date"));
	                user.setApprove_number(rs.getString("approve_number"));
	                user.setPassword(rs.getString("password"));
	                user.setTechnosphere(rs.getString("technosphere"));
	                return user;
	            }
	        } catch (SQLException e) {      //出错
	            e.printStackTrace();
	        }
	        //查询失败
	        return user;
	    }
	@Override
	public User load(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
