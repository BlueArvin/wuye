package wuye.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

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
            	return rs.getString("version");
            } else {
            	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            	return df.format(new Date());
            }
        }catch(Exception e){
       	 	doCatchException("getBlockList" ,e);
	       	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
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
		return data;
	}
	
	private List<ConfigData.Pair> getState() {
		List<ConfigData.Pair> ret =new ArrayList<ConfigData.Pair>();
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select id,statename from t_state";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	ret.add(new ConfigData.Pair(rs.getInt("id"),rs.getString("statename")));
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
            String sql = "select street_id,street_name from t_street";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	ret.add(new ConfigData.Pair(rs.getInt("street_id"),rs.getString("street_name")));
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
		ret.add(new ConfigData.Pair(1,"A"));
		ret.add(new ConfigData.Pair(2,"B"));
		return ret;
	}
	
	private List<ConfigData.Pair> getWuyeCorp() {
		List<ConfigData.Pair> ret =new ArrayList<ConfigData.Pair>();
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select id,company_name from t_managecompany";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	ret.add(new ConfigData.Pair(rs.getInt("id"),rs.getString("company_name")));
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
