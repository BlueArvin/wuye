package wuye.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import deer.milu.freejava.basic.MString;
import wuye.bean.LoginUserBean;
import wuye.dao.DaoBasic;
import wuye.dao.UserDao;

public class UserDaoImpl extends DaoBasic implements UserDao{
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private DataSource dataSource;

	public int login(LoginUserBean user) {
		String random = MString.getRandomString(32);
		int id = 0;
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select id,passwd,token from t_user where cn=? limit 1";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setString(1, user.getAccount());
            rs = pstmt.executeQuery();
            if (rs.next()) {
            	id = rs.getInt("id");
            	String passwd = rs.getString("passwd");
            	String token = rs.getString("token");
            	if(user.getType() == 0) {
            		if(passwd.equals(user.getPwd())) {
            			user.setRet(LoginUserBean.OK);  // ��¼�ɹ�����Ҫ����cookie
            			user.setUpdateCookie(random);
            		}else {
            			user.setRet(LoginUserBean.PWDERROR);
            		}
            	} else {  // token ��֤
            		if(token.equals(user.getCookie())) {
            			user.setRet(LoginUserBean.OK);  // ��¼�ɹ�����Ҫ����cookie
            			user.setUpdateCookie(random);
            		}else {
            			user.setRet(LoginUserBean.COOKIEFAIL);
            		}
            	}
            }else {
            	user.setRet(LoginUserBean.NOUSER);
            }
            return 0;
        }catch(Exception e){
       	 	doCatchException("getBlockList" ,e);
            return -1;
        } finally {
            closeConnection(conn, pstmt, rs);
            
            if(user.getRet() == LoginUserBean.OK) {   // ����cookie
            	updateUserToken(id, random);
            }
        }
	}
	
	private int updateUserToken(int id, String cookie) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
            String sql = "update t_user set token=? where id=? ";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setString(1, cookie);
            pstmt.setInt(2, id);
            rs = pstmt.execute();
            return 0;
        }catch(Exception e){
       	 	doCatchException("getBlockList" ,e);
            return -1;
        } finally {
            closeConnection(conn, pstmt, null);
        }
		
	}
	
}
