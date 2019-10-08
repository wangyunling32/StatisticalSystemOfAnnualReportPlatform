package com.indeed.dao;

import com.indeed.model.Organization;
import com.indeed.Util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrganizationDao {

    public int addOrganization(Organization organization){

        Connection con = DBUtil.getConnection();
        String sql = "INSERT INTO `organization` (`name`, `usci`, `faren_name`, `telephone`, `type`,`userid`) VALUES (?,?,?,?,?,?);";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        try {
            ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,organization.getName());
            ps.setString(2,organization.getUsci());
            ps.setString(3,organization.getFarenName());
            ps.setString(4,organization.getTelephone());
            ps.setString(5,organization.getType());
            ps.setInt(6, organization.getUserid());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            while (rs.next()) {
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

    public Organization loadOrganization(int id){

        Connection con = DBUtil.getConnection();
        String sql = "SELECT * FROM organization WHERE id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Organization organization = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()){
                organization = new Organization();
                organization.setId(rs.getInt("id"));
                organization.setName(rs.getString("name"));
                organization.setUsci(rs.getString("usci"));
                organization.setFarenName(rs.getString("faren_name"));
                organization.setTelephone(rs.getString("telephone"));
                organization.setType(rs.getString("type"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(rs);
            DBUtil.close(ps);
            DBUtil.close(con);
        }
        return organization;
    }
    //依托单位名称模糊查询
    public List<Organization> load(String name,int userid) {
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT * FROM organization WHERE name like ? and userid = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Organization> organizations = new ArrayList<Organization>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, "%"+name+"%");
			ps.setInt(2, userid);
			rs = ps.executeQuery();
			while(rs.next()) {
				Organization organization = new Organization();
				organization.setId(rs.getInt("id"));
	            organization.setName(rs.getString("name"));
	            organization.setUsci(rs.getString("usci"));
	            organization.setFarenName(rs.getString("faren_name"));
	            organization.setTelephone(rs.getString("telephone"));
	            organization.setType(rs.getString("type"));
				organizations.add(organization);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
		return organizations;
	}
}
