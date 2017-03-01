package wuye.manager.user.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wuye.dao.DaoBasic;
import wuye.manager.menu.bean.MenuBean;
import wuye.manager.user.bean.UserBean;
import wuye.manager.user.dao.ManagerUserDao;
import wuye.manager.utils.PageUtil;

@Component("managerUserDao")
public class ManagerUserDaoImpl extends DaoBasic implements ManagerUserDao{

	@Autowired
	private DataSource dataSource;
	
	@Override
	public boolean updateUserPasswd(String cn,String passwd){
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
            String sql = "update t_user set passwd = ? where cn = ?";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setString(1, passwd);
            pstmt.setString(2, cn);
            rs = pstmt.execute();
        }catch(Exception e){
       	 	doCatchException("checkUser" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
        return rs;
	}
	
	
	@Override
	public boolean addUser(UserBean userBean){
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
            String sql = "insert into t_user (cn,passwd,userName,role,webRole) values (?,?,?,?,?)";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setString(1, userBean.getCn());
            pstmt.setString(2, userBean.getPasswd());
            pstmt.setString(3, userBean.getUserName());
            pstmt.setInt(4, userBean.getRole());
            pstmt.setString(5, userBean.getWebRole());
            rs = pstmt.execute();
        }catch(Exception e){
       	 	doCatchException("addUser" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
        return rs;
	}


	@Override
	public List<UserBean> findAllUser(UserBean userBean, PageUtil page) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select id,cn,token,status,role,webRole,userName from t_user where 1=1";
            
            if(userBean.getCn()!=null){
            	sql+=" and cn='"+userBean.getCn()+"'"; 
            }
            if(userBean.getUserName()!=null){
            	sql+=" and userName like '%"+userBean.getUserName() +"%'";
            }
            sql +=" order by id desc limit "+page.getStartIndex()+","+page.getPageSize();
            
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            List<UserBean> list = new ArrayList<UserBean>();
            while(rs.next()){
            	UserBean ub = new UserBean();
            	ub.setId(rs.getInt("id"));
            	ub.setCn(rs.getString("cn"));
            	ub.setUserName(rs.getString("userName"));
            	ub.setStatus(rs.getInt("status"));
            	ub.setCn(rs.getString("cn"));
            	ub.setRole(rs.getInt("role"));
            	ub.setWebRole(rs.getString("webRole"));
            	list.add(ub);
            }
            return list;
        }catch(Exception e){
       	 	doCatchException("findAllUser" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
		return null;
	}


	@Override
	public void updateUserInfo(UserBean userBean) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
            String sql = "update t_user set ";//passwd = ? where cn = ?";
            
            
            if(userBean.getUserName()!=null){
            	sql += "userName="+userBean.getUserName().trim()+",";
            }
            
            if(userBean.getUserName()!=null){
            	sql += "passwd="+userBean.getPasswd().trim()+",";
            }
            
            if(userBean.getRole()!=0){
            	sql += "role="+userBean.getRole()+",";
            }
            
            if(userBean.getWebRole()!=null){
            	sql += "webRole="+userBean.getWebRole().trim()+",";
            }
            
            if(userBean.getStatus()!=null){
            	sql += "status="+userBean.getStatus()+",";
            }
            
            if(userBean.getCn()!=null){
            	sql += "cn="+userBean.getCn().trim()+",";
            }
            
            sql += "id="+userBean.getId()+" where id="+userBean.getId();
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.execute();
        }catch(Exception e){
       	 	doCatchException("checkUser" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
	}


	@Override
	public int getUserTotal(UserBean userBean) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select count(id) as con from t_user where 1=1";
            
            if(userBean.getCn()!=null){
            	sql+=" and cn='"+userBean.getCn()+"'"; 
            }
            if(userBean.getUserName()!=null){
            	sql+=" and userName like '%"+userBean.getUserName() +"%'";
            }
            
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            List<UserBean> list = new ArrayList<UserBean>();
            if(rs.next()){
            	return rs.getInt("con");
            }
            return 0;
        }catch(Exception e){
       	 	doCatchException("getUserTotal" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
		return 0;
	}
	
	public UserBean getUserInfo(int id){
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from t_user where id=?";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()){
            	UserBean ub = new UserBean();
            	ub.setId(rs.getInt("id"));
            	ub.setStatus(rs.getInt("status"));
            	ub.setUserName(rs.getString("userName"));
            	ub.setCn(rs.getString("cn"));
            	ub.setPasswd(rs.getString("passwd"));
            	ub.setRole(rs.getInt("role"));
            	ub.setWebRole(rs.getString("webRole"));
            	return ub;
            }
            return null;
        }catch(Exception e){
       	 	doCatchException("getUserInfo" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
		return null;
	
	}
	
}
