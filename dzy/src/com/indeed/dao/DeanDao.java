package com.indeed.dao;

import com.indeed.model.Dean;
import com.indeed.model.Organization;
import com.indeed.model.Platform;
import com.indeed.Util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeanDao {

    public int addDean(Dean dean){

        Connection con = DBUtil.getConnection();
        String sql = "INSERT INTO `dean` (`name`, `sex`, `date`, `job_title`, `major`, `education`, `degree`, `telephone`, `mobilephone`, `email`,`userid`) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,dean.getName());
            ps.setString(2,dean.getSex());
            ps.setTimestamp(3,dean.getDate());
            ps.setString(4,dean.getJobTitle());
            ps.setString(5,dean.getMajor());
            ps.setString(6,dean.getEducation());
            ps.setString(7,dean.getDegree());
            ps.setString(8,dean.getTelephone());
            ps.setString(9,dean.getMobilephone());
            ps.setString(10,dean.getEmail());
            ps.setInt(11, dean.getUserid());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            while (rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs);
            DBUtil.close(ps);
            DBUtil.close(con);
        }
        return id;
    }

    public Dean loadDean(int id){

        Connection con = DBUtil.getConnection();
        String sql = "SELECT * FROM dean WHERE id = ?;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Dean dean = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()){
                dean = new Dean();
                dean.setId(rs.getInt("id"));
                dean.setName(rs.getString("name"));
                dean.setSex(rs.getString("sex"));
                dean.setDate(rs.getTimestamp("date"));
                dean.setJobTitle(rs.getString("job_title"));
                dean.setMajor(rs.getString("major"));
                dean.setEducation(rs.getString("education"));
                dean.setDegree(rs.getString("degree"));
                dean.setTelephone(rs.getString("telephone"));
                dean.setMobilephone(rs.getString("mobilephone"));
                dean.setEmail(rs.getString("email"));
            }
            return dean;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs);
            DBUtil.close(ps);
            DBUtil.close(con);
        }
        return dean;
    }
    
    public List<Dean> load(String name,int userid)
    {
    			Connection connection = DBUtil.getConnection();
    			String sql = "SELECT * FROM dean WHERE name like ? and userid = ?";
    			PreparedStatement ps = null;
    			ResultSet rs = null;
    			List<Dean> deans = new ArrayList<Dean>();
    			try {
    				ps = connection.prepareStatement(sql);
    				ps.setString(1, "%"+name+"%");
    				ps.setInt(2, userid);
    				rs = ps.executeQuery();
    				while(rs.next()) {
    					    Dean dean = new Dean();
    		                dean.setId(rs.getInt("id"));
    		                dean.setName(rs.getString("name"));
    		                dean.setSex(rs.getString("sex"));
    		                dean.setDate(rs.getTimestamp("date"));
    		                dean.setJobTitle(rs.getString("job_title"));
    		                dean.setMajor(rs.getString("major"));
    		                dean.setEducation(rs.getString("education"));
    		                dean.setDegree(rs.getString("degree"));
    		                dean.setTelephone(rs.getString("telephone"));
    		                dean.setMobilephone(rs.getString("mobilephone"));
    		                dean.setEmail(rs.getString("email"));
    					    deans.add(dean);
    				}
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}finally {
    				DBUtil.close(rs);
    				DBUtil.close(ps);
    				DBUtil.close(connection);
    			}
    			return deans;
    }

}
