package com.indeed.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.indeed.Util.DBUtil;
import com.indeed.model.Admin;

public class AdminDao {
	public int addDean(Admin admin){

        Connection con = DBUtil.getConnection();
        String sql = "INSERT INTO `admin` (`username`, `password`, `bumen`, `gonghao`) VALUES(?,?,?,?)";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,admin.getUsername());
            ps.setString(2,admin.getPassword());
            ps.setString(3,admin.getBumen());
            ps.setString(4,admin.getGonghao());
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

}
