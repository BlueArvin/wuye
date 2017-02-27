package wuye.manager.menu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import wuye.dao.DaoBasic;
import wuye.manager.menu.dao.LoginDao;
import wuye.manager.user.bean.UserBean;

@Component("loginDao")
public class LoginDaoImpl extends DaoBasic implements LoginDao{

	@Autowired
	private DataSource dataSource;
	
	public UserBean checkUser(String loginName, String passwd) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from t_user where status = 0 and cn = ? and passwd = ?";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setString(1, loginName);
            pstmt.setString(2, passwd);
            rs = pstmt.executeQuery();
            if(rs.next()){
            	UserBean ub = new UserBean();
            	String cn = rs.getString("cn");
            	ub.setCn(cn);
            	ub.setUserName(rs.getString("userName"));
            	
            	sql = "select menu_code from t_role where cn = ?";
            	conn = dataSource.getConnection();
                pstmt = prepareStatement(conn, sql);
                pstmt.setString(1, cn);
                rs = pstmt.executeQuery();
                List<String> menus = new ArrayList<String>();
                while(rs.next()){
                	menus.add(rs.getString("menu_code"));
                }
                ub.setRoleList(menus);
            	return ub;
            }
            return null;
        }catch(Exception e){
       	 	doCatchException("checkUser" ,e);
            return null;
        } finally {
            closeConnection(conn, pstmt, null);
        }
	}

}
