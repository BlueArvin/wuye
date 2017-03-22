package wuye.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import wuye.bean.CheckConfigBean;
import wuye.bean.ConfigData;
import wuye.bean.LoginUserBean;
import wuye.dao.ConfigDao;
import wuye.dao.DaoBasic;

public class ConfigDaoImpl extends DaoBasic implements ConfigDao {

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private DataSource dataSource;
	
	public String getVersion() {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select version from t_sys";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);

            rs = pstmt.executeQuery();
            if (rs.next()) {
            	long data = Long.parseLong(rs.getString("version"));
            	Calendar cal = Calendar.getInstance();  
                cal.setTimeInMillis(data);
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            	return df.format(cal.getTime());
            } else {
            	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            	return df.format(new Date());
            }
        }catch(Exception e){
       	 	doCatchException("getBlockList" ,e);
	       	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
	       	return df.format(new Date());
        } finally {
            closeConnection(conn, pstmt, null);
        }
	}
	
	public String getCheckVersion() {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select checkversion from t_sys";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);

            rs = pstmt.executeQuery();
            if (rs.next()) {
            	long data = Long.parseLong(rs.getString("checkversion"));
            	Calendar cal = Calendar.getInstance();  
                cal.setTimeInMillis(data);
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            	return df.format(cal.getTime());
            } else {
            	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            	return df.format(new Date());
            }
        }catch(Exception e){
       	 	doCatchException("getBlockList" ,e);
	       	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
	       	return df.format(new Date());
        } finally {
            closeConnection(conn, pstmt, null);
        }
	}
	

	public ConfigData getConfigData() {
		
		ConfigData data = new ConfigData();
		data.state = getState();
		data.street = getStreet();
		data.check = getCheckLevel();
		data.wuye = getWuyeCorp();
		data.pianqu = getPianqu();
		data.hutong = getHutong();
		return data;
	}
	
	private List<ConfigData.Pair> getState() {
		List<ConfigData.Pair> ret =new ArrayList<ConfigData.Pair>();
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select id,statename from t_state where del = 0 ";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	ret.add(new ConfigData.Pair("s" + rs.getInt("id") + "", 
            			rs.getString("statename")));
            }
            return ret;
        }catch(Exception e){
       	 	doCatchException("getBlockList" ,e);
       	 return ret;
        } finally {
            closeConnection(conn, pstmt, null);
            
        }
	}
	
	private List<ConfigData.Pair> getStreet() {
		List<ConfigData.Pair> ret =new ArrayList<ConfigData.Pair>();
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select id,street_name,area_id from t_street where del = 0 ";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	ret.add(new ConfigData.Pair("t" + rs.getInt("id"), 
            			rs.getString("street_name"),
            			"s" + rs.getString("area_id")));
            }
            return ret;
        }catch(Exception e){
       	 	doCatchException("getBlockList" ,e);
       	 return ret;
        } finally {
            closeConnection(conn, pstmt, null);
            
        }
	}
	
	private List<ConfigData.Pair> getPianqu() {
		List<ConfigData.Pair> ret =new ArrayList<ConfigData.Pair>();
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select id,pianqu_name,father_id from t_pianqu where del = 0 ";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	ret.add(new ConfigData.Pair("p"+ rs.getInt("id") , 
            			rs.getString("pianqu_name"),
            			"t" + rs.getString("father_id")));
            }
            return ret;
        }catch(Exception e){
       	 	doCatchException("getBlockList" ,e);
       	 return ret;
        } finally {
            closeConnection(conn, pstmt, null);
            
        }
	}
	
	private List<ConfigData.Pair> getHutong() {
		List<ConfigData.Pair> ret =new ArrayList<ConfigData.Pair>();
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select id,hutong_name,father_id from t_hutong where del = 0 ";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	ret.add(new ConfigData.Pair("h" + rs.getInt("id") , 
            			rs.getString("hutong_name"),
            			"p"  + rs.getString("father_id")));
            }
            return ret;
        }catch(Exception e){
       	 	doCatchException("getBlockList" ,e);
       	 return ret;
        } finally {
            closeConnection(conn, pstmt, null);
            
        }
	}
	
	private List<ConfigData.Pair> getCheckLevel() {
		List<ConfigData.Pair> ret =new ArrayList<ConfigData.Pair>();
		
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select level_id,level_name from t_checklevel where del = 0 ";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	ret.add(new ConfigData.Pair("" + rs.getInt("level_id") ,rs.getString("level_name")));
            }
            return ret;
        }catch(Exception e){
       	 	doCatchException("getCheckLevel" ,e);
       	 	return ret;
        } finally {
            closeConnection(conn, pstmt, null);
            
        }
	}
	
	private List<ConfigData.Pair> getWuyeCorp() {
		List<ConfigData.Pair> ret =new ArrayList<ConfigData.Pair>();
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select id,company_name from t_managecompany where del = 0 ";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	ret.add(new ConfigData.Pair("wu" + rs.getInt("id") ,rs.getString("company_name")));
            }
            return ret;
        }catch(Exception e){
       	 	doCatchException("getBlockList" ,e);
       	 return ret;
        } finally {
            closeConnection(conn, pstmt, null);
            
        }
	}

	public CheckConfigBean getCheckConfigData() {
		CheckConfigBean data = new CheckConfigBean();
		data.checktitle = getCheckTitle();
		data.checksub = getCheckSub();
		return data;
	}
	
	private List<ConfigData.Pair> getCheckTitle() {
		List<ConfigData.Pair> ret =new ArrayList<ConfigData.Pair>();
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select score_id,title_name,type from t_checktitle where del = 0 ";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	ret.add(new ConfigData.Pair("st" + rs.getInt("score_id") + "", rs.getString("title_name"), rs.getInt("type")));
            }
            return ret;
        }catch(Exception e){
       	 	doCatchException("getBlockList" ,e);
       	 return ret;
        } finally {
            closeConnection(conn, pstmt, null);
            
        }
	}
	
	private List<ConfigData.Pair> getCheckSub() {
		List<ConfigData.Pair> ret =new ArrayList<ConfigData.Pair>();
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select subid,sub_name,titleid from t_checksub where del = 0 ";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	ret.add(new ConfigData.Pair("ss" + rs.getInt("subid"),rs.getString("sub_name"),
            			"st" + rs.getInt("titleid") ));
            }
            return ret;
        }catch(Exception e){
       	 	doCatchException("getBlockList" ,e);
       	 return ret;
        } finally {
            closeConnection(conn, pstmt, null);
            
        }
	}

}
