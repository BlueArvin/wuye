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
import wuye.manager.menu.bean.MenuBean;
import wuye.manager.menu.dao.LoginDao;
import wuye.manager.menu.dao.MenuDao;
import wuye.manager.user.bean.UserBean;

@Component("menuDao")
public class MenuDaoImpl extends DaoBasic implements MenuDao{

	@Autowired
	private DataSource dataSource;
	
	public List<MenuBean> findAllMenu(List<String> menuCodes) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select menu_code,menu_name,url from t_menu where menu_code in (0";
            
            for(int i=0;i<menuCodes.size();i++){
            	sql+=","+menuCodes.get(i);
            }
            sql+=") order by ordr";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            List<MenuBean> list = new ArrayList<MenuBean>();
            while(rs.next()){
            	MenuBean ub = new MenuBean();
            	String menuCode = rs.getString("menu_code");
            	ub.setMenuCode(menuCode);
            	ub.setMenuName(rs.getString("menu_name"));
            	ub.setUrl(rs.getString("url"));
            	list.add(ub);
            }
            return list;
        }catch(Exception e){
       	 	doCatchException("checkUser" ,e);
            return null;
        } finally {
            closeConnection(conn, pstmt, null);
        }
	}
	
	@Override
	public void findMenuByParentCode(MenuBean menuBean) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            
            String sql = "select menu_code,menu_name,url from t_menu where parent_code = ? order by ordr";
            
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setString(1, menuBean.getMenuCode());
            rs = pstmt.executeQuery();
            List<MenuBean> list = new ArrayList<MenuBean>();
            while(rs.next()){
            	MenuBean ub = new MenuBean();
            	String menuCode = rs.getString("menu_code");
            	ub.setMenuCode(menuCode);
            	ub.setMenuName(rs.getString("menu_name"));
            	ub.setUrl(rs.getString("url"));
            	list.add(ub);
            }
            menuBean.setChild(list);
        }catch(Exception e){
       	 	doCatchException("checkUser" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
	}

	
	
	

}
