package com.indeed.dao;
import java.sql.Statement;
import com.indeed.model.Dean;
import com.indeed.model.Organization;
import com.indeed.model.Platform;
import com.indeed.model.User;
import com.indeed.Util.DBUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlatformDao {

    public void addPlatform(Platform platform){

        Connection con = DBUtil.getConnection();
        String sql = "INSERT INTO `platform` (`Puserid`, `Porganizationid`, `Pdeanid`, `Pgrade`, `Pdistrict`, `Pform`, `Punite`, `Pjjjgj`, `Pindustry`, `Psubject`, `Pgjdw`, `Pwebsite`, `Pweburl`, `Paddress`, `Ppostcode`,`status`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,platform.getUser().getId());
            ps.setInt(2,platform.getOrganization().getId());
            ps.setInt(3,platform.getDean().getId());
            ps.setInt(4,platform.getGrade());
            ps.setString(5,platform.getDistrict());
            ps.setInt(6,platform.getForm());
            ps.setBoolean(7,platform.isUnite());
            ps.setBoolean(8,platform.isJjjgj());
            ps.setString(9,platform.getIndustry());
            ps.setString(10,platform.getSubject());
            
            //将共建单位数组用分号连接
            String gjdw = "";
            for (String g:platform.getGjdw()){
                gjdw += (g + ";");
            }
            ps.setString(11,gjdw);
            ps.setString(12,platform.getSitename());
            ps.setString(13,platform.getWebsite());
            ps.setString(14,platform.getAddr());
            ps.setString(15,platform.getPostcode());
            ps.setInt(16, platform.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(ps);
            DBUtil.close(con);
        }
    }

    public Platform loadPlatform(int id){

        UserDaoImpl userDao = new UserDaoImpl();
        DeanDao deanDao = new DeanDao();
        OrganizationDao organizationDao = new OrganizationDao();

        Connection con = DBUtil.getConnection();
        String sql = "SELECT * FROM platform WHERE id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Platform platform = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()) {

                platform = new Platform();

                User user = userDao.load(rs.getInt("Puserid"));
                Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
                Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));

                platform.setId(rs.getInt("id"));
                platform.setUser(user);
                platform.setDean(dean);
                platform.setOrganization(organization);
                platform.setGrade(rs.getInt("Pgrade"));
                platform.setDistrict(rs.getString("Pdistrict"));
                platform.setId(rs.getInt("Pform"));
                platform.setUnite(rs.getBoolean("Punite"));
                platform.setJjjgj(rs.getBoolean("Pjjjgj"));
                platform.setIndustry(rs.getString("Pindustry"));
                platform.setSubject(rs.getString("Psubject"));
                platform.setGjdw(rs.getString("Pgjdw").split(";"));
                platform.setSitename(rs.getString("Pwebsite"));
                platform.setWebsite(rs.getString("Pweburl"));
                platform.setAddr(rs.getString("Paddress"));
                platform.setPostcode(rs.getString("Ppostcode"));
                platform.setStatus(rs.getInt("status"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(rs);
            DBUtil.close(ps);
            DBUtil.close(con);
        }
        return platform;
    }
    
    public Platform loadSubject(String subject){

        UserDaoImpl userDao = new UserDaoImpl();
        DeanDao deanDao = new DeanDao();
        OrganizationDao organizationDao = new OrganizationDao();

        Connection con = DBUtil.getConnection();
        String sql = "SELECT * FROM platform WHERE Psubject like ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Platform platform = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,subject+"%");
            rs = ps.executeQuery();
            while (rs.next()) {

                platform = new Platform();

                User user = userDao.load(rs.getInt("Puserid"));
                Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
                Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));

                platform.setId(rs.getInt("id"));
                platform.setUser(user);
                platform.setDean(dean);
                platform.setOrganization(organization);
                platform.setGrade(rs.getInt("Pgrade"));
                platform.setDistrict(rs.getString("Pdistrict"));
                platform.setId(rs.getInt("Pform"));
                platform.setUnite(rs.getBoolean("Punite"));
                platform.setJjjgj(rs.getBoolean("Pjjjgj"));
                platform.setIndustry(rs.getString("Pindustry"));
                platform.setSubject(rs.getString("Psubject"));
                platform.setGjdw(rs.getString("Pgjdw").split(";"));
                platform.setSitename(rs.getString("Pwebsite"));
                platform.setWebsite(rs.getString("Pweburl"));
                platform.setAddr(rs.getString("Paddress"));
                platform.setPostcode(rs.getString("Ppostcode"));
                platform.setStatus(rs.getInt("status"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(rs);
            DBUtil.close(ps);
            DBUtil.close(con);
        }
        return platform;
    }
    
    public List<Platform> load(String name,int Puserid)
    {
    	DeanDao deanDao = new DeanDao();
        OrganizationDao organizationDao = new OrganizationDao();
        Connection con = DBUtil.getConnection();
        String sql = "SELECT * FROM platform WHERE Puserid = ?";
        PreparedStatement ps = null;
        List<Platform> platforms = new ArrayList<Platform>();
        ResultSet rs = null;
        try {
            Platform platform = new Platform();
            ps = con.prepareStatement(sql);
            ps.setInt(1,Puserid);
            
            rs = ps.executeQuery();
            while (rs.next()){
                platform.setId(rs.getInt("id"));
                Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
                Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));

                platform.setId(rs.getInt("id"));
                platform.setDean(dean);
                platform.setOrganization(organization);
                platform.setGrade(rs.getInt("Pgrade"));
                platform.setDistrict(rs.getString("Pdistrict"));
                platform.setId(rs.getInt("Pform"));
                platform.setUnite(rs.getBoolean("Punite"));
                platform.setJjjgj(rs.getBoolean("Pjjjgj"));
                platform.setIndustry(rs.getString("Pindustry"));
                platform.setSubject(rs.getString("Psubject"));
                platform.setGjdw(rs.getString("Pgjdw").split(";"));
                platform.setSitename(rs.getString("Pwebsite"));
                platform.setWebsite(rs.getString("Pweburl"));
                platform.setAddr(rs.getString("Paddress"));
                platform.setPostcode(rs.getString("Ppostcode"));
                platform.setStatus(rs.getInt("status"));
                platforms.add(platform);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return platforms;
    }
    
    public List<Platform> loadPlatfrom(int Puserid){
    	DeanDao deanDao = new DeanDao();
        OrganizationDao organizationDao = new OrganizationDao();
        Connection con = DBUtil.getConnection();
        String sql = "SELECT * FROM platform WHERE Puserid = ?";
        PreparedStatement ps = null;
        List<Platform> platforms = new ArrayList<Platform>();
        ResultSet rs = null;
        try {
            Platform platform = new Platform();
            ps = con.prepareStatement(sql);
            ps.setInt(1,Puserid);
            rs = ps.executeQuery();
            while (rs.next()){
                platform.setId(rs.getInt("id"));
                Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
                Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));

                platform.setId(rs.getInt("id"));
                platform.setDean(dean);
                platform.setOrganization(organization);
                platform.setGrade(rs.getInt("Pgrade"));
                platform.setDistrict(rs.getString("Pdistrict"));
                platform.setId(rs.getInt("Pform"));
                platform.setUnite(rs.getBoolean("Punite"));
                platform.setJjjgj(rs.getBoolean("Pjjjgj"));
                platform.setIndustry(rs.getString("Pindustry"));
                platform.setSubject(rs.getString("Psubject"));
                platform.setGjdw(rs.getString("Pgjdw").split(";"));
                platform.setSitename(rs.getString("Pwebsite"));
                platform.setWebsite(rs.getString("Pweburl"));
                platform.setAddr(rs.getString("Paddress"));
                platform.setPostcode(rs.getString("Ppostcode"));
                platform.setStatus(rs.getInt("status"));
                platforms.add(platform);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return platforms;
    }
    
    //查询所有
    public List<Platform> loadAll() {
		// TODO Auto-generated method stub
		Connection connection = DBUtil.getConnection();
		
		String sql = "select * from platform";		
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<Platform> platforms = new ArrayList<Platform>();
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				UserDaoImpl userDao = new UserDaoImpl();
				OrganizationDao organizationDao = new OrganizationDao();
				DeanDao deanDao = new DeanDao();
				User user = userDao.load(resultSet.getInt("Puserid"));
				System.out.println(user);
				Organization organization = organizationDao.loadOrganization(resultSet.getInt("Porganizationid"));
				Dean dean = deanDao.loadDean(resultSet.getInt("Pdeanid"));
				
				Platform platform = new Platform();
				platform.setId(resultSet.getInt("id"));
				platform.setUser(user);
				platform.setOrganization(organization);
				platform.setDean(dean);
				platform.setGrade(resultSet.getInt("Pgrade"));
				platform.setDistrict(resultSet.getString("Pdistrict"));
				platform.setForm(resultSet.getInt("Pform"));
				platform.setUnite(resultSet.getBoolean("Punite"));
				platform.setJjjgj(resultSet.getBoolean("Pjjjgj"));
				platform.setIndustry(resultSet.getString("Pindustry"));
				platform.setSubject(resultSet.getString("Psubject"));
				platform.setGjdw(resultSet.getString("Pgjdw").split(";"));
				platform.setSitename(resultSet.getString("Pwebsite"));
				platform.setWebsite(resultSet.getString("Pweburl"));
				platform.setAddr(resultSet.getString("Paddress"));
				platform.setPostcode(resultSet.getString("Ppostcode"));
				platform.setStatus(resultSet.getInt("status"));
				platforms.add(platform);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
            DBUtil.close(preparedStatement);
            DBUtil.close(connection);
        }
		
		return platforms;
	}
    public List<Platform> loadShenheAll() {
		// TODO Auto-generated method stub
		Connection connection = DBUtil.getConnection();
		
		String sql = "select * from platform where status!=0";		
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<Platform> platforms = new ArrayList<Platform>();
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				UserDaoImpl userDao = new UserDaoImpl();
				OrganizationDao organizationDao = new OrganizationDao();
				DeanDao deanDao = new DeanDao();
				User user = userDao.load(resultSet.getInt("Puserid"));
				Organization organization = organizationDao.loadOrganization(resultSet.getInt("Porganizationid"));
				Dean dean = deanDao.loadDean(resultSet.getInt("Pdeanid"));
				
				Platform platform = new Platform();
				platform.setId(resultSet.getInt("id"));
				platform.setUser(user);
				platform.setOrganization(organization);
				platform.setDean(dean);
				platform.setGrade(resultSet.getInt("Pgrade"));
				platform.setDistrict(resultSet.getString("Pdistrict"));
				platform.setForm(resultSet.getInt("Pform"));
				platform.setUnite(resultSet.getBoolean("Punite"));
				platform.setJjjgj(resultSet.getBoolean("Pjjjgj"));
				platform.setIndustry(resultSet.getString("Pindustry"));
				platform.setSubject(resultSet.getString("Psubject"));
				platform.setGjdw(resultSet.getString("Pgjdw").split(";"));
				platform.setSitename(resultSet.getString("Pwebsite"));
				platform.setWebsite(resultSet.getString("Pweburl"));
				platform.setAddr(resultSet.getString("Paddress"));
				platform.setPostcode(resultSet.getString("Ppostcode"));
				platform.setStatus(resultSet.getInt("status"));
				platforms.add(platform);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
            DBUtil.close(preparedStatement);
            DBUtil.close(connection);
        }
		
		return platforms;
	}
    
    //分类查询
    public List<Platform> loadHangye(String shouhang){
    	DeanDao deanDao = new DeanDao();
        OrganizationDao organizationDao = new OrganizationDao();
        Connection con = DBUtil.getConnection();
        String sql = "SELECT * FROM platform WHERE Pindustry like '"+shouhang+"%' ";
        PreparedStatement ps = null;
        List<Platform> platforms = new ArrayList<Platform>();
        ResultSet rs = null;
        try {
            Platform platform = new Platform();
            ps = con.prepareStatement(sql);
            //ps.setString(2,shouhang+"%" );
            rs = ps.executeQuery();
            while (rs.next()){
            	UserDaoImpl userDao = new UserDaoImpl();
				organizationDao = new OrganizationDao();
				deanDao = new DeanDao();
				User user = userDao.load(rs.getInt("Puserid"));
				Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
				Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
				
				platform = new Platform();
				platform.setId(rs.getInt("id"));
				platform.setUser(user);
				platform.setOrganization(organization);
				platform.setDean(dean);
				platform.setGrade(rs.getInt("Pgrade"));
				platform.setDistrict(rs.getString("Pdistrict"));
				platform.setForm(rs.getInt("Pform"));
				platform.setUnite(rs.getBoolean("Punite"));
				platform.setJjjgj(rs.getBoolean("Pjjjgj"));
				platform.setIndustry(rs.getString("Pindustry"));
				platform.setSubject(rs.getString("Psubject"));
				platform.setGjdw(rs.getString("Pgjdw").split(";"));
				platform.setSitename(rs.getString("Pwebsite"));
				platform.setWebsite(rs.getString("Pweburl"));
				platform.setAddr(rs.getString("Paddress"));
				platform.setPostcode(rs.getString("Ppostcode"));
				platform.setStatus(rs.getInt("status"));
				platforms.add(platform);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return platforms;
    }
    
    //是否京冀共建
    public Platform loadJj(Boolean jingji,int Puserid){

        UserDaoImpl userDao = new UserDaoImpl();
        DeanDao deanDao = new DeanDao();
        OrganizationDao organizationDao = new OrganizationDao();

        Connection con = DBUtil.getConnection();
        String sql = "SELECT * FROM platform WHERE Puserid = ?and jingji=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Platform platform = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,Puserid);
            rs = ps.executeQuery();
            while (rs.next()) {

                platform = new Platform();

                User user = userDao.load(rs.getInt("Puserid"));
                Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
                Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));

                platform.setId(rs.getInt("id"));
                platform.setUser(user);
                platform.setDean(dean);
                platform.setOrganization(organization);
                platform.setGrade(rs.getInt("Pgrade"));
                platform.setDistrict(rs.getString("Pdistrict"));
                platform.setId(rs.getInt("Pform"));
                platform.setUnite(rs.getBoolean("Punite"));
                platform.setJjjgj(rs.getBoolean("Pjjjgj"));
                platform.setIndustry(rs.getString("Pindustry"));
                platform.setSubject(rs.getString("Psubject"));
                platform.setGjdw(rs.getString("Pgjdw").split(";"));
                platform.setSitename(rs.getString("Pwebsite"));
                platform.setWebsite(rs.getString("Pweburl"));
                platform.setAddr(rs.getString("Paddress"));
                platform.setPostcode(rs.getString("Ppostcode"));
                platform.setStatus(rs.getInt("status"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(rs);
            DBUtil.close(ps);
            DBUtil.close(con);
        }
        return platform;
    }

    
public List<Platform> loadByMuti(int grade,int jjjgj,String name){
    	
    	Connection con = DBUtil.getConnection();
    	
    	String sql = "SELECT * FROM platform join users on platform.Puserid=users.id WHERE Pgrade=? AND Pjjjgj=? AND platform_name LIKE ?";
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	List<Platform> platforms = new ArrayList<Platform>();
    	try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, grade);
			ps.setInt(2, jjjgj);
			ps.setString(3, name + "%");
			rs = ps.executeQuery();
			while(rs.next()) {
				UserDaoImpl userDao = new UserDaoImpl();
				OrganizationDao organizationDao = new OrganizationDao();
				DeanDao deanDao = new DeanDao();
				User user = userDao.load(rs.getInt("Puserid"));
				Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
				Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
				
				Platform platform = new Platform();
				platform.setId(rs.getInt("id"));
				platform.setUser(user);
				platform.setOrganization(organization);
				platform.setDean(dean);
				platform.setGrade(rs.getInt("Pgrade"));
				platform.setDistrict(rs.getString("Pdistrict"));
				platform.setForm(rs.getInt("Pform"));
				platform.setUnite(rs.getBoolean("Punite"));
				platform.setJjjgj(rs.getBoolean("Pjjjgj"));
				platform.setIndustry(rs.getString("Pindustry"));
				platform.setSubject(rs.getString("Psubject"));
				platform.setGjdw(rs.getString("Pgjdw").split(";"));
				platform.setSitename(rs.getString("Pwebsite"));
				platform.setWebsite(rs.getString("Pweburl"));
				platform.setAddr(rs.getString("Paddress"));
				platform.setPostcode(rs.getString("Ppostcode"));
				platform.setStatus(rs.getInt("status"));
				platforms.add(platform);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return platforms;
	}	
public List<Platform> loadByMuti1(int grade,int jjjgj,Date date){
	
	Connection con = DBUtil.getConnection();
	
	String sql = "SELECT * FROM platform join users on platform.Puserid=users.id WHERE Pgrade=? AND Pjjjgj=? AND approve_date=?";
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Platform> platforms = new ArrayList<Platform>();
	try {
		ps = con.prepareStatement(sql);
		ps.setInt(1, grade);
		ps.setInt(2, jjjgj);
		ps.setDate(3, date);
		rs = ps.executeQuery();
		while(rs.next()) {
			UserDaoImpl userDao = new UserDaoImpl();
			OrganizationDao organizationDao = new OrganizationDao();
			DeanDao deanDao = new DeanDao();
			User user = userDao.load(rs.getInt("Puserid"));
			Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
			Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
			
			Platform platform = new Platform();
			platform.setId(rs.getInt("id"));
			platform.setUser(user);
			platform.setOrganization(organization);
			platform.setDean(dean);
			platform.setGrade(rs.getInt("Pgrade"));
			platform.setDistrict(rs.getString("Pdistrict"));
			platform.setForm(rs.getInt("Pform"));
			platform.setUnite(rs.getBoolean("Punite"));
			platform.setJjjgj(rs.getBoolean("Pjjjgj"));
			platform.setIndustry(rs.getString("Pindustry"));
			platform.setSubject(rs.getString("Psubject"));
			platform.setGjdw(rs.getString("Pgjdw").split(";"));
			platform.setSitename(rs.getString("Pwebsite"));
			platform.setWebsite(rs.getString("Pweburl"));
			platform.setAddr(rs.getString("Paddress"));
			platform.setPostcode(rs.getString("Ppostcode"));
			platform.setStatus(rs.getInt("status"));
			platforms.add(platform);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return platforms;
}	
public List<Platform> loadByMuti2(Date date,int jjjgj,String name){
	
	Connection con = DBUtil.getConnection();
	
	String sql = "SELECT * FROM platform join users on platform.Puserid=users.id WHERE approve_date=? and Pgrade=? AND Pjjjgj=? ";
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Platform> platforms = new ArrayList<Platform>();
	try {
		ps = con.prepareStatement(sql);
		ps.setDate(1, date);
		ps.setInt(2, jjjgj);
		ps.setString(3, name + "%");
		rs = ps.executeQuery();
		while(rs.next()) {
			UserDaoImpl userDao = new UserDaoImpl();
			OrganizationDao organizationDao = new OrganizationDao();
			DeanDao deanDao = new DeanDao();
			User user = userDao.load(rs.getInt("Puserid"));
			Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
			Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
			
			Platform platform = new Platform();
			platform.setId(rs.getInt("id"));
			platform.setUser(user);
			platform.setOrganization(organization);
			platform.setDean(dean);
			platform.setGrade(rs.getInt("Pgrade"));
			platform.setDistrict(rs.getString("Pdistrict"));
			platform.setForm(rs.getInt("Pform"));
			platform.setUnite(rs.getBoolean("Punite"));
			platform.setJjjgj(rs.getBoolean("Pjjjgj"));
			platform.setIndustry(rs.getString("Pindustry"));
			platform.setSubject(rs.getString("Psubject"));
			platform.setGjdw(rs.getString("Pgjdw").split(";"));
			platform.setSitename(rs.getString("Pwebsite"));
			platform.setWebsite(rs.getString("Pweburl"));
			platform.setAddr(rs.getString("Paddress"));
			platform.setPostcode(rs.getString("Ppostcode"));
			platform.setStatus(rs.getInt("status"));
			platforms.add(platform);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return platforms;
}	
public List<Platform> loadByMuti3(int grade,Date date,String name){
	
	Connection con = DBUtil.getConnection();
	
	String sql = "SELECT * FROM platform join users on platform.Puserid=users.id WHERE Pgrade=? AND approve_date=? AND platform_name LIKE ?";
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Platform> platforms = new ArrayList<Platform>();
	try {
		ps = con.prepareStatement(sql);
		ps.setInt(1, grade);
		ps.setDate(2, date);
		ps.setString(3, name + "%");
		rs = ps.executeQuery();
		while(rs.next()) {
			UserDaoImpl userDao = new UserDaoImpl();
			OrganizationDao organizationDao = new OrganizationDao();
			DeanDao deanDao = new DeanDao();
			User user = userDao.load(rs.getInt("Puserid"));
			Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
			Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
			
			Platform platform = new Platform();
			platform.setId(rs.getInt("id"));
			platform.setUser(user);
			platform.setOrganization(organization);
			platform.setDean(dean);
			platform.setGrade(rs.getInt("Pgrade"));
			platform.setDistrict(rs.getString("Pdistrict"));
			platform.setForm(rs.getInt("Pform"));
			platform.setUnite(rs.getBoolean("Punite"));
			platform.setJjjgj(rs.getBoolean("Pjjjgj"));
			platform.setIndustry(rs.getString("Pindustry"));
			platform.setSubject(rs.getString("Psubject"));
			platform.setGjdw(rs.getString("Pgjdw").split(";"));
			platform.setSitename(rs.getString("Pwebsite"));
			platform.setWebsite(rs.getString("Pweburl"));
			platform.setAddr(rs.getString("Paddress"));
			platform.setPostcode(rs.getString("Ppostcode"));
			platform.setStatus(rs.getInt("status"));
			platforms.add(platform);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return platforms;
}	
  
public void update_status(int id,int zhuangtai)
 
{
	   String sql = "update platform set status='"+zhuangtai+"' where id='"+id+"'";
		Connection conn = DBUtil.getConnection();
		System.out.println("llslala");
		Statement state=null;
		try 
		{
			state=conn.createStatement();
			state.executeUpdate(sql);
		} 
		   catch (Exception e) 
		   {
			e.printStackTrace();
		   } 
		    finally 
		   {
			DBUtil.close(conn);
		   }
    
		
 }


//
public List<Platform> loadJibie(String shouhang,int jibie){
	DeanDao deanDao = new DeanDao();
    OrganizationDao organizationDao = new OrganizationDao();
    Connection con = DBUtil.getConnection();
    String sql = "SELECT * FROM platform WHERE Pindustry like '"+shouhang+"%' and Pgrade=jibie ";
    PreparedStatement ps = null;
    List<Platform> platforms = new ArrayList<Platform>();
    ResultSet rs = null;
    try {
        Platform platform = new Platform();
        ps = con.prepareStatement(sql);
        //ps.setString(2,shouhang+"%" );
        rs = ps.executeQuery();
        while (rs.next()){
        	UserDaoImpl userDao = new UserDaoImpl();
			organizationDao = new OrganizationDao();
			deanDao = new DeanDao();
			User user = userDao.load(rs.getInt("Puserid"));
			Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
			Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
			
			platform = new Platform();
			platform.setId(rs.getInt("id"));
			platform.setUser(user);
			platform.setOrganization(organization);
			platform.setDean(dean);
			platform.setGrade(rs.getInt("Pgrade"));
			platform.setDistrict(rs.getString("Pdistrict"));
			platform.setForm(rs.getInt("Pform"));
			platform.setUnite(rs.getBoolean("Punite"));
			platform.setJjjgj(rs.getBoolean("Pjjjgj"));
			platform.setIndustry(rs.getString("Pindustry"));
			platform.setSubject(rs.getString("Psubject"));
			platform.setGjdw(rs.getString("Pgjdw").split(";"));
			platform.setSitename(rs.getString("Pwebsite"));
			platform.setWebsite(rs.getString("Pweburl"));
			platform.setAddr(rs.getString("Paddress"));
			platform.setPostcode(rs.getString("Ppostcode"));
			platform.setStatus(rs.getInt("status"));
			platforms.add(platform);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return platforms;
}

public List<Platform> loadJjgj(String shouhang,boolean jjgj){
	DeanDao deanDao = new DeanDao();
    OrganizationDao organizationDao = new OrganizationDao();
    Connection con = DBUtil.getConnection();
    String sql = "SELECT * FROM platform WHERE Pindustry like '"+shouhang+"%' and Pjjjgj=jjgj ";
    PreparedStatement ps = null;
    List<Platform> platforms = new ArrayList<Platform>();
    ResultSet rs = null;
    try {
        Platform platform = new Platform();
        ps = con.prepareStatement(sql);
        //ps.setString(2,shouhang+"%" );
        rs = ps.executeQuery();
        while (rs.next()){
        	UserDaoImpl userDao = new UserDaoImpl();
			organizationDao = new OrganizationDao();
			deanDao = new DeanDao();
			User user = userDao.load(rs.getInt("Puserid"));
			Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
			Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
			
			platform = new Platform();
			platform.setId(rs.getInt("id"));
			platform.setUser(user);
			platform.setOrganization(organization);
			platform.setDean(dean);
			platform.setGrade(rs.getInt("Pgrade"));
			platform.setDistrict(rs.getString("Pdistrict"));
			platform.setForm(rs.getInt("Pform"));
			platform.setUnite(rs.getBoolean("Punite"));
			platform.setJjjgj(rs.getBoolean("Pjjjgj"));
			platform.setIndustry(rs.getString("Pindustry"));
			platform.setSubject(rs.getString("Psubject"));
			platform.setGjdw(rs.getString("Pgjdw").split(";"));
			platform.setSitename(rs.getString("Pwebsite"));
			platform.setWebsite(rs.getString("Pweburl"));
			platform.setAddr(rs.getString("Paddress"));
			platform.setPostcode(rs.getString("Ppostcode"));
			platform.setStatus(rs.getInt("status"));
			platforms.add(platform);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return platforms;
}


public List<Platform> loadJibieandJJgj (String shouhang,int jibie,boolean jjgj){
	DeanDao deanDao = new DeanDao();
    OrganizationDao organizationDao = new OrganizationDao();
    Connection con = DBUtil.getConnection();
    String sql = "SELECT * FROM platform WHERE Pindustry like '"+shouhang+"%' and Pgrade=jibie and Pjjjgj=jjgj";
    PreparedStatement ps = null;
    List<Platform> platforms = new ArrayList<Platform>();
    ResultSet rs = null;
    try {
        Platform platform = new Platform();
        ps = con.prepareStatement(sql);
        //ps.setString(2,shouhang+"%" );
        rs = ps.executeQuery();
        while (rs.next()){
        	UserDaoImpl userDao = new UserDaoImpl();
			organizationDao = new OrganizationDao();
			deanDao = new DeanDao();
			User user = userDao.load(rs.getInt("Puserid"));
			Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
			Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
			
			platform = new Platform();
			platform.setId(rs.getInt("id"));
			platform.setUser(user);
			platform.setOrganization(organization);
			platform.setDean(dean);
			platform.setGrade(rs.getInt("Pgrade"));
			platform.setDistrict(rs.getString("Pdistrict"));
			platform.setForm(rs.getInt("Pform"));
			platform.setUnite(rs.getBoolean("Punite"));
			platform.setJjjgj(rs.getBoolean("Pjjjgj"));
			platform.setIndustry(rs.getString("Pindustry"));
			platform.setSubject(rs.getString("Psubject"));
			platform.setGjdw(rs.getString("Pgjdw").split(";"));
			platform.setSitename(rs.getString("Pwebsite"));
			platform.setWebsite(rs.getString("Pweburl"));
			platform.setAddr(rs.getString("Paddress"));
			platform.setPostcode(rs.getString("Ppostcode"));
			platform.setStatus(rs.getInt("status"));
			platforms.add(platform);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return platforms;
}


public List<Platform> loadJibie(int jibie){
	DeanDao deanDao = new DeanDao();
    OrganizationDao organizationDao = new OrganizationDao();
    Connection con = DBUtil.getConnection();
    String sql = "SELECT * FROM platform WHERE  Pgrade=jibie ";
    PreparedStatement ps = null;
    List<Platform> platforms = new ArrayList<Platform>();
    ResultSet rs = null;
    try {
        Platform platform = new Platform();
        ps = con.prepareStatement(sql);
        //ps.setString(2,shouhang+"%" );
        rs = ps.executeQuery();
        while (rs.next()){
        	UserDaoImpl userDao = new UserDaoImpl();
			organizationDao = new OrganizationDao();
			deanDao = new DeanDao();
			User user = userDao.load(rs.getInt("Puserid"));
			Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
			Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
			
			platform = new Platform();
			platform.setId(rs.getInt("id"));
			platform.setUser(user);
			platform.setOrganization(organization);
			platform.setDean(dean);
			platform.setGrade(rs.getInt("Pgrade"));
			platform.setDistrict(rs.getString("Pdistrict"));
			platform.setForm(rs.getInt("Pform"));
			platform.setUnite(rs.getBoolean("Punite"));
			platform.setJjjgj(rs.getBoolean("Pjjjgj"));
			platform.setIndustry(rs.getString("Pindustry"));
			platform.setSubject(rs.getString("Psubject"));
			platform.setGjdw(rs.getString("Pgjdw").split(";"));
			platform.setSitename(rs.getString("Pwebsite"));
			platform.setWebsite(rs.getString("Pweburl"));
			platform.setAddr(rs.getString("Paddress"));
			platform.setPostcode(rs.getString("Ppostcode"));
			platform.setStatus(rs.getInt("status"));
			platforms.add(platform);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return platforms;
}



public List<Platform> loadTiaojian(String shouhang,int jibie,boolean jjgj,String date,String name){
	DeanDao deanDao = new DeanDao();
    OrganizationDao organizationDao = new OrganizationDao();
    Connection con = DBUtil.getConnection();
    String sql = "SELECT * FROM platform WHERE Pindustry like '"+shouhang+"%' and Pgrade=jibie and Pjjjgj=jjgj or approve_date=date or platform_name like '%"+shouhang+"%'";
    PreparedStatement ps = null;
    List<Platform> platforms = new ArrayList<Platform>();
    ResultSet rs = null;
    try {
        Platform platform = new Platform();
        ps = con.prepareStatement(sql);
        //ps.setString(2,shouhang+"%" );
        rs = ps.executeQuery();
        while (rs.next()){
        	UserDaoImpl userDao = new UserDaoImpl();
			organizationDao = new OrganizationDao();
			deanDao = new DeanDao();
			User user = userDao.load(rs.getInt("Puserid"));
			Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
			Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
			
			platform = new Platform();
			platform.setId(rs.getInt("id"));
			platform.setUser(user);
			platform.setOrganization(organization);
			platform.setDean(dean);
			platform.setGrade(rs.getInt("Pgrade"));
			platform.setDistrict(rs.getString("Pdistrict"));
			platform.setForm(rs.getInt("Pform"));
			platform.setUnite(rs.getBoolean("Punite"));
			platform.setJjjgj(rs.getBoolean("Pjjjgj"));
			platform.setIndustry(rs.getString("Pindustry"));
			platform.setSubject(rs.getString("Psubject"));
			platform.setGjdw(rs.getString("Pgjdw").split(";"));
			platform.setSitename(rs.getString("Pwebsite"));
			platform.setWebsite(rs.getString("Pweburl"));
			platform.setAddr(rs.getString("Paddress"));
			platform.setPostcode(rs.getString("Ppostcode"));
			platform.setStatus(rs.getInt("status"));
			platforms.add(platform);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return platforms;
}


public List<Platform> loadTiaojian1(String shouhang,int jibie,String date,String name){
	DeanDao deanDao = new DeanDao();
    OrganizationDao organizationDao = new OrganizationDao();
    Connection con = DBUtil.getConnection();
    String sql = "SELECT * FROM platform join users on platform.Puserid=users.id WHERE Pindustry like '"+shouhang+"%' and ( Pgrade=jibie or approve_date=date or platform_name like '%"+name+"%' )";
    PreparedStatement ps = null;
    List<Platform> platforms = new ArrayList<Platform>();
    ResultSet rs = null;
    try {
        Platform platform = new Platform();
        ps = con.prepareStatement(sql);
        //ps.setString(2,shouhang+"%" );
        rs = ps.executeQuery();
        while (rs.next()){
        	UserDaoImpl userDao = new UserDaoImpl();
			organizationDao = new OrganizationDao();
			deanDao = new DeanDao();
			User user = userDao.load(rs.getInt("Puserid"));
			Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
			Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
			
			platform = new Platform();
			platform.setId(rs.getInt("id"));
			platform.setUser(user);
			platform.setOrganization(organization);
			platform.setDean(dean);
			platform.setGrade(rs.getInt("Pgrade"));
			platform.setDistrict(rs.getString("Pdistrict"));
			platform.setForm(rs.getInt("Pform"));
			platform.setUnite(rs.getBoolean("Punite"));
			platform.setJjjgj(rs.getBoolean("Pjjjgj"));
			platform.setIndustry(rs.getString("Pindustry"));
			platform.setSubject(rs.getString("Psubject"));
			platform.setGjdw(rs.getString("Pgjdw").split(";"));
			platform.setSitename(rs.getString("Pwebsite"));
			platform.setWebsite(rs.getString("Pweburl"));
			platform.setAddr(rs.getString("Paddress"));
			platform.setPostcode(rs.getString("Ppostcode"));
			platform.setStatus(rs.getInt("status"));
			platforms.add(platform);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return platforms;
}

//分类中的多条件查询

//1
public List<Platform> loadByTiaojian(int grade,int jjjgj,String name,Date date){
	
	Connection con = DBUtil.getConnection();
	
	String sql = "SELECT * FROM platform join users on platform.Puserid=users.id WHERE Pgrade=? and Pjjjgj=? and platform_name LIKE ? and approve_date = ?";
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Platform> platforms = new ArrayList<Platform>();
	try {
		ps = con.prepareStatement(sql);
		ps.setInt(1, grade);
		ps.setInt(2, jjjgj);
		ps.setString(3, "%"+name + "%");
		ps.setDate(4, date);
		rs = ps.executeQuery();
		while(rs.next()) {
			UserDaoImpl userDao = new UserDaoImpl();
			OrganizationDao organizationDao = new OrganizationDao();
			DeanDao deanDao = new DeanDao();
			User user = userDao.load(rs.getInt("Puserid"));
			Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
			Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
			
			Platform platform = new Platform();
			platform.setId(rs.getInt("id"));
			platform.setUser(user);
			platform.setOrganization(organization);
			platform.setDean(dean);
			platform.setGrade(rs.getInt("Pgrade"));
			platform.setDistrict(rs.getString("Pdistrict"));
			platform.setForm(rs.getInt("Pform"));
			platform.setUnite(rs.getBoolean("Punite"));
			platform.setJjjgj(rs.getBoolean("Pjjjgj"));
			platform.setIndustry(rs.getString("Pindustry"));
			platform.setSubject(rs.getString("Psubject"));
			platform.setGjdw(rs.getString("Pgjdw").split(";"));
			platform.setSitename(rs.getString("Pwebsite"));
			platform.setWebsite(rs.getString("Pweburl"));
			platform.setAddr(rs.getString("Paddress"));
			platform.setPostcode(rs.getString("Ppostcode"));
			platform.setStatus(rs.getInt("status"));
			platforms.add(platform);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return platforms;
}

//2

public List<Platform> loadByTiaojian1(int grade){
	
	Connection con = DBUtil.getConnection();
	
	String sql = "SELECT * FROM platform  WHERE Pgrade= ? ";
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Platform> platforms = new ArrayList<Platform>();
	try {
		ps = con.prepareStatement(sql);
		ps.setInt(1, grade);
		rs = ps.executeQuery();
		while(rs.next()) {
			UserDaoImpl userDao = new UserDaoImpl();
			OrganizationDao organizationDao = new OrganizationDao();
			DeanDao deanDao = new DeanDao();
			User user = userDao.load(rs.getInt("Puserid"));
			Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
			Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
			
			Platform platform = new Platform();
			platform.setId(rs.getInt("id"));
			platform.setUser(user);
			platform.setOrganization(organization);
			platform.setDean(dean);
			platform.setGrade(rs.getInt("Pgrade"));
			platform.setDistrict(rs.getString("Pdistrict"));
			platform.setForm(rs.getInt("Pform"));
			platform.setUnite(rs.getBoolean("Punite"));
			platform.setJjjgj(rs.getBoolean("Pjjjgj"));
			platform.setIndustry(rs.getString("Pindustry"));
			platform.setSubject(rs.getString("Psubject"));
			platform.setGjdw(rs.getString("Pgjdw").split(";"));
			platform.setSitename(rs.getString("Pwebsite"));
			platform.setWebsite(rs.getString("Pweburl"));
			platform.setAddr(rs.getString("Paddress"));
			platform.setPostcode(rs.getString("Ppostcode"));
			platform.setStatus(rs.getInt("status"));
			platforms.add(platform);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return platforms;
}	

public List<Platform> loadByTiaojian2(int jj){
	
	Connection con = DBUtil.getConnection();
	
	String sql = "SELECT * FROM platform  WHERE Pjjjgj=? ";
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Platform> platforms = new ArrayList<Platform>();
	try {
		ps = con.prepareStatement(sql);
		ps.setInt(1, jj);
		rs = ps.executeQuery();
		while(rs.next()) {
			UserDaoImpl userDao = new UserDaoImpl();
			OrganizationDao organizationDao = new OrganizationDao();
			DeanDao deanDao = new DeanDao();
			User user = userDao.load(rs.getInt("Puserid"));
			Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
			Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
			
			Platform platform = new Platform();
			platform.setId(rs.getInt("id"));
			platform.setUser(user);
			platform.setOrganization(organization);
			platform.setDean(dean);
			platform.setGrade(rs.getInt("Pgrade"));
			platform.setDistrict(rs.getString("Pdistrict"));
			platform.setForm(rs.getInt("Pform"));
			platform.setUnite(rs.getBoolean("Punite"));
			platform.setJjjgj(rs.getBoolean("Pjjjgj"));
			platform.setIndustry(rs.getString("Pindustry"));
			platform.setSubject(rs.getString("Psubject"));
			platform.setGjdw(rs.getString("Pgjdw").split(";"));
			platform.setSitename(rs.getString("Pwebsite"));
			platform.setWebsite(rs.getString("Pweburl"));
			platform.setAddr(rs.getString("Paddress"));
			platform.setPostcode(rs.getString("Ppostcode"));
			platform.setStatus(rs.getInt("status"));
			platforms.add(platform);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return platforms;
}	


public List<Platform> load(int userid){
	
	Connection con = DBUtil.getConnection();
	
	String sql = "SELECT * FROM platform  WHERE Puserid=? ";
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Platform> platforms = new ArrayList<Platform>();
	try {
		ps = con.prepareStatement(sql);
		ps.setInt(1, userid);
		rs = ps.executeQuery();
		while(rs.next()) {
			UserDaoImpl userDao = new UserDaoImpl();
			OrganizationDao organizationDao = new OrganizationDao();
			DeanDao deanDao = new DeanDao();
			User user = userDao.load(rs.getInt("Puserid"));
			Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
			Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
			
			Platform platform = new Platform();
			platform.setId(rs.getInt("id"));
			platform.setUser(user);
			platform.setOrganization(organization);
			platform.setDean(dean);
			platform.setGrade(rs.getInt("Pgrade"));
			platform.setDistrict(rs.getString("Pdistrict"));
			platform.setForm(rs.getInt("Pform"));
			platform.setUnite(rs.getBoolean("Punite"));
			platform.setJjjgj(rs.getBoolean("Pjjjgj"));
			platform.setIndustry(rs.getString("Pindustry"));
			platform.setSubject(rs.getString("Psubject"));
			platform.setGjdw(rs.getString("Pgjdw").split(";"));
			platform.setSitename(rs.getString("Pwebsite"));
			platform.setWebsite(rs.getString("Pweburl"));
			platform.setAddr(rs.getString("Paddress"));
			platform.setPostcode(rs.getString("Ppostcode"));
			platform.setStatus(rs.getInt("status"));
			platforms.add(platform);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return platforms;
}	

public List<Platform> loadByTiaojian3(String name){
	
	Connection con = DBUtil.getConnection();
	
	String sql = "SELECT * FROM platform join users on platform.Puserid=users.id WHERE platform_name like ?";
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Platform> platforms = new ArrayList<Platform>();
	try {
		ps = con.prepareStatement(sql);
		ps.setString(1, "%"+name+"%");
		rs = ps.executeQuery();
		while(rs.next()) {
			UserDaoImpl userDao = new UserDaoImpl();
			OrganizationDao organizationDao = new OrganizationDao();
			DeanDao deanDao = new DeanDao();
			User user = userDao.load(rs.getInt("Puserid"));
			Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
			Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
			
			Platform platform = new Platform();
			platform.setId(rs.getInt("id"));
			platform.setUser(user);
			platform.setOrganization(organization);
			platform.setDean(dean);
			platform.setGrade(rs.getInt("Pgrade"));
			platform.setDistrict(rs.getString("Pdistrict"));
			platform.setForm(rs.getInt("Pform"));
			platform.setUnite(rs.getBoolean("Punite"));
			platform.setJjjgj(rs.getBoolean("Pjjjgj"));
			platform.setIndustry(rs.getString("Pindustry"));
			platform.setSubject(rs.getString("Psubject"));
			platform.setGjdw(rs.getString("Pgjdw").split(";"));
			platform.setSitename(rs.getString("Pwebsite"));
			platform.setWebsite(rs.getString("Pweburl"));
			platform.setAddr(rs.getString("Paddress"));
			platform.setPostcode(rs.getString("Ppostcode"));
			platform.setStatus(rs.getInt("status"));
			platforms.add(platform);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return platforms;
}	

public List<Platform> loadByTiaojian4(int grade,int jjgj){
	
	Connection con = DBUtil.getConnection();
	
	String sql = "SELECT * FROM platform  WHERE Pgrade=? and Pjjjgj=?";
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Platform> platforms = new ArrayList<Platform>();
	try {
		ps = con.prepareStatement(sql);
		ps.setInt(1, grade);
		ps.setInt(2, jjgj);
		rs = ps.executeQuery();
		while(rs.next()) {
			UserDaoImpl userDao = new UserDaoImpl();
			OrganizationDao organizationDao = new OrganizationDao();
			DeanDao deanDao = new DeanDao();
			User user = userDao.load(rs.getInt("Puserid"));
			Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
			Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
			
			Platform platform = new Platform();
			platform.setId(rs.getInt("id"));
			platform.setUser(user);
			platform.setOrganization(organization);
			platform.setDean(dean);
			platform.setGrade(rs.getInt("Pgrade"));
			platform.setDistrict(rs.getString("Pdistrict"));
			platform.setForm(rs.getInt("Pform"));
			platform.setUnite(rs.getBoolean("Punite"));
			platform.setJjjgj(rs.getBoolean("Pjjjgj"));
			platform.setIndustry(rs.getString("Pindustry"));
			platform.setSubject(rs.getString("Psubject"));
			platform.setGjdw(rs.getString("Pgjdw").split(";"));
			platform.setSitename(rs.getString("Pwebsite"));
			platform.setWebsite(rs.getString("Pweburl"));
			platform.setAddr(rs.getString("Paddress"));
			platform.setPostcode(rs.getString("Ppostcode"));
			platform.setStatus(rs.getInt("status"));
			platforms.add(platform);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return platforms;
}	

public List<Platform> loadByTiaojian5(int grade,String name){
	
	Connection con = DBUtil.getConnection();
	
	String sql = "SELECT * FROM platform join users on platform.Puserid=users.id WHERE platform_name like ? and Pgrade=?";
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Platform> platforms = new ArrayList<Platform>();
	try {
		ps = con.prepareStatement(sql);
		ps.setString(1,"%"+ name+"%");
		ps.setInt(2, grade);
		rs = ps.executeQuery();
		while(rs.next()) {
			UserDaoImpl userDao = new UserDaoImpl();
			OrganizationDao organizationDao = new OrganizationDao();
			DeanDao deanDao = new DeanDao();
			User user = userDao.load(rs.getInt("Puserid"));
			Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
			Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
			
			Platform platform = new Platform();
			platform.setId(rs.getInt("id"));
			platform.setUser(user);
			platform.setOrganization(organization);
			platform.setDean(dean);
			platform.setGrade(rs.getInt("Pgrade"));
			platform.setDistrict(rs.getString("Pdistrict"));
			platform.setForm(rs.getInt("Pform"));
			platform.setUnite(rs.getBoolean("Punite"));
			platform.setJjjgj(rs.getBoolean("Pjjjgj"));
			platform.setIndustry(rs.getString("Pindustry"));
			platform.setSubject(rs.getString("Psubject"));
			platform.setGjdw(rs.getString("Pgjdw").split(";"));
			platform.setSitename(rs.getString("Pwebsite"));
			platform.setWebsite(rs.getString("Pweburl"));
			platform.setAddr(rs.getString("Paddress"));
			platform.setPostcode(rs.getString("Ppostcode"));
			platform.setStatus(rs.getInt("status"));
			platforms.add(platform);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return platforms;
}	


public List<Platform> loadByTiaojian6(int jjgj,String name){
	
	Connection con = DBUtil.getConnection();
	
	String sql = "SELECT * FROM platform join users on platform.Puserid=users.id WHERE Pjjjgj=? and platform_name like ? ";
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Platform> platforms = new ArrayList<Platform>();
	try {
		ps = con.prepareStatement(sql);
		ps.setInt(1, jjgj);
		ps.setString(2, "%"+name+"%");
		rs = ps.executeQuery();
		while(rs.next()) {
			UserDaoImpl userDao = new UserDaoImpl();
			OrganizationDao organizationDao = new OrganizationDao();
			DeanDao deanDao = new DeanDao();
			User user = userDao.load(rs.getInt("Puserid"));
			Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
			Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
			
			Platform platform = new Platform();
			platform.setId(rs.getInt("id"));
			platform.setUser(user);
			platform.setOrganization(organization);
			platform.setDean(dean);
			platform.setGrade(rs.getInt("Pgrade"));
			platform.setDistrict(rs.getString("Pdistrict"));
			platform.setForm(rs.getInt("Pform"));
			platform.setUnite(rs.getBoolean("Punite"));
			platform.setJjjgj(rs.getBoolean("Pjjjgj"));
			platform.setIndustry(rs.getString("Pindustry"));
			platform.setSubject(rs.getString("Psubject"));
			platform.setGjdw(rs.getString("Pgjdw").split(";"));
			platform.setSitename(rs.getString("Pwebsite"));
			platform.setWebsite(rs.getString("Pweburl"));
			platform.setAddr(rs.getString("Paddress"));
			platform.setPostcode(rs.getString("Ppostcode"));
			platform.setStatus(rs.getInt("status"));
			platforms.add(platform);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return platforms;
}	


public List<Platform> loadByTiaojian7(Date date){
	
	Connection con = DBUtil.getConnection();
	
	String sql = "SELECT * FROM platform join users on platform.Puserid=users.id WHERE approve_date=? ";
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Platform> platforms = new ArrayList<Platform>();
	try {
		ps = con.prepareStatement(sql);
		ps.setDate(1, date);
		rs = ps.executeQuery();
		while(rs.next()) {
			UserDaoImpl userDao = new UserDaoImpl();
			OrganizationDao organizationDao = new OrganizationDao();
			DeanDao deanDao = new DeanDao();
			User user = userDao.load(rs.getInt("Puserid"));
			Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
			Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
			
			Platform platform = new Platform();
			platform.setId(rs.getInt("id"));
			platform.setUser(user);
			platform.setOrganization(organization);
			platform.setDean(dean);
			platform.setGrade(rs.getInt("Pgrade"));
			platform.setDistrict(rs.getString("Pdistrict"));
			platform.setForm(rs.getInt("Pform"));
			platform.setUnite(rs.getBoolean("Punite"));
			platform.setJjjgj(rs.getBoolean("Pjjjgj"));
			platform.setIndustry(rs.getString("Pindustry"));
			platform.setSubject(rs.getString("Psubject"));
			platform.setGjdw(rs.getString("Pgjdw").split(";"));
			platform.setSitename(rs.getString("Pwebsite"));
			platform.setWebsite(rs.getString("Pweburl"));
			platform.setAddr(rs.getString("Paddress"));
			platform.setPostcode(rs.getString("Ppostcode"));
			platform.setStatus(rs.getInt("status"));
			platforms.add(platform);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return platforms;
}

public List<Platform> loadByTiaojian8(Date date,String name){
	
	Connection con = DBUtil.getConnection();
	
	String sql = "SELECT * FROM platform join users on platform.Puserid=users.id WHERE platform_name like ? and approve_date=?";
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Platform> platforms = new ArrayList<Platform>();
	try {
		ps = con.prepareStatement(sql);
		ps.setString(1,"%"+ name+"%");
		ps.setDate(2, date);
		rs = ps.executeQuery();
		while(rs.next()) {
			UserDaoImpl userDao = new UserDaoImpl();
			OrganizationDao organizationDao = new OrganizationDao();
			DeanDao deanDao = new DeanDao();
			User user = userDao.load(rs.getInt("Puserid"));
			Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
			Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
			
			Platform platform = new Platform();
			platform.setId(rs.getInt("id"));
			platform.setUser(user);
			platform.setOrganization(organization);
			platform.setDean(dean);
			platform.setGrade(rs.getInt("Pgrade"));
			platform.setDistrict(rs.getString("Pdistrict"));
			platform.setForm(rs.getInt("Pform"));
			platform.setUnite(rs.getBoolean("Punite"));
			platform.setJjjgj(rs.getBoolean("Pjjjgj"));
			platform.setIndustry(rs.getString("Pindustry"));
			platform.setSubject(rs.getString("Psubject"));
			platform.setGjdw(rs.getString("Pgjdw").split(";"));
			platform.setSitename(rs.getString("Pwebsite"));
			platform.setWebsite(rs.getString("Pweburl"));
			platform.setAddr(rs.getString("Paddress"));
			platform.setPostcode(rs.getString("Ppostcode"));
			platform.setStatus(rs.getInt("status"));
			platforms.add(platform);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return platforms;
}	
public List<Platform> loadByTiaojian9(Date date,int grade){
	
	Connection con = DBUtil.getConnection();
	
	String sql = "SELECT * FROM platform join users on platform.Puserid=users.id WHERE Pgrade=? and approve_date=?";
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Platform> platforms = new ArrayList<Platform>();
	try {
		ps = con.prepareStatement(sql);
		ps.setInt(1,grade);
		ps.setDate(2, date);
		rs = ps.executeQuery();
		while(rs.next()) {
			UserDaoImpl userDao = new UserDaoImpl();
			OrganizationDao organizationDao = new OrganizationDao();
			DeanDao deanDao = new DeanDao();
			User user = userDao.load(rs.getInt("Puserid"));
			Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
			Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
			
			Platform platform = new Platform();
			platform.setId(rs.getInt("id"));
			platform.setUser(user);
			platform.setOrganization(organization);
			platform.setDean(dean);
			platform.setGrade(rs.getInt("Pgrade"));
			platform.setDistrict(rs.getString("Pdistrict"));
			platform.setForm(rs.getInt("Pform"));
			platform.setUnite(rs.getBoolean("Punite"));
			platform.setJjjgj(rs.getBoolean("Pjjjgj"));
			platform.setIndustry(rs.getString("Pindustry"));
			platform.setSubject(rs.getString("Psubject"));
			platform.setGjdw(rs.getString("Pgjdw").split(";"));
			platform.setSitename(rs.getString("Pwebsite"));
			platform.setWebsite(rs.getString("Pweburl"));
			platform.setAddr(rs.getString("Paddress"));
			platform.setPostcode(rs.getString("Ppostcode"));
			platform.setStatus(rs.getInt("status"));
			platforms.add(platform);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return platforms;
}	
public List<Platform> loadByTiaojian10(Date date,int jjgj){
	
	Connection con = DBUtil.getConnection();
	
	String sql = "SELECT * FROM platform join users on platform.Puserid=users.id WHERE Pjjjgj=? and approve_date=?";
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Platform> platforms = new ArrayList<Platform>();
	try {
		ps = con.prepareStatement(sql);
		ps.setInt(1,jjgj);
		ps.setDate(2, date);
		rs = ps.executeQuery();
		while(rs.next()) {
			UserDaoImpl userDao = new UserDaoImpl();
			OrganizationDao organizationDao = new OrganizationDao();
			DeanDao deanDao = new DeanDao();
			User user = userDao.load(rs.getInt("Puserid"));
			Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
			Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
			
			Platform platform = new Platform();
			platform.setId(rs.getInt("id"));
			platform.setUser(user);
			platform.setOrganization(organization);
			platform.setDean(dean);
			platform.setGrade(rs.getInt("Pgrade"));
			platform.setDistrict(rs.getString("Pdistrict"));
			platform.setForm(rs.getInt("Pform"));
			platform.setUnite(rs.getBoolean("Punite"));
			platform.setJjjgj(rs.getBoolean("Pjjjgj"));
			platform.setIndustry(rs.getString("Pindustry"));
			platform.setSubject(rs.getString("Psubject"));
			platform.setGjdw(rs.getString("Pgjdw").split(";"));
			platform.setSitename(rs.getString("Pwebsite"));
			platform.setWebsite(rs.getString("Pweburl"));
			platform.setAddr(rs.getString("Paddress"));
			platform.setPostcode(rs.getString("Ppostcode"));
			platform.setStatus(rs.getInt("status"));
			platforms.add(platform);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return platforms;
}	


public List<Platform> loadByCatagory(String subject){
	
	Connection con = DBUtil.getConnection();
	
	String sql = "SELECT * FROM platform WHERE Psubject LIKE ?";
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Platform> platforms = new ArrayList<Platform>();
	try {
		ps = con.prepareStatement(sql);
		ps.setString(1, subject + "%");
		rs = ps.executeQuery();
		while(rs.next()) {
			
			UserDaoImpl userDao = new UserDaoImpl();
			OrganizationDao organizationDao = new OrganizationDao();
			DeanDao deanDao = new DeanDao();
			User user = userDao.load(rs.getInt("Puserid"));
			Organization organization = organizationDao.loadOrganization(rs.getInt("Porganizationid"));
			Dean dean = deanDao.loadDean(rs.getInt("Pdeanid"));
			
			Platform platform = new Platform();
			platform.setId(rs.getInt("id"));
			platform.setUser(user);
			platform.setOrganization(organization);
			platform.setDean(dean);
			platform.setGrade(rs.getInt("Pgrade"));
			platform.setDistrict(rs.getString("Pdistrict"));
			platform.setForm(rs.getInt("Pform"));
			platform.setUnite(rs.getBoolean("Punite"));
			platform.setJjjgj(rs.getBoolean("Pjjjgj"));
			platform.setIndustry(rs.getString("Pindustry"));
			platform.setSubject(rs.getString("Psubject"));
			platform.setGjdw(rs.getString("Pgjdw").split(";"));
			platform.setSitename(rs.getString("Pwebsite"));
			platform.setWebsite(rs.getString("Pweburl"));
			platform.setAddr(rs.getString("Paddress"));
			platform.setPostcode(rs.getString("Ppostcode"));
			platform.setStatus(rs.getInt("status"));
			platforms.add(platform);
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBUtil.close(rs);
        DBUtil.close(ps);
        DBUtil.close(con);
    }
	
	
	return platforms;
}

}

