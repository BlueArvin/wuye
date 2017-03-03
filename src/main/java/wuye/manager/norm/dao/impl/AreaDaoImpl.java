package wuye.manager.norm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wuye.dao.DaoBasic;
import wuye.manager.norm.bean.AreaBean;
import wuye.manager.norm.bean.NormCategoryBean;
import wuye.manager.norm.dao.AreaDao;
import wuye.manager.utils.PageUtil;

@Component("areaDao")
public class AreaDaoImpl extends DaoBasic implements AreaDao{

	@Autowired
	private DataSource dataSource;
	
	@Override
	public boolean addState(AreaBean areaBean) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
            String sql = "insert into t_state (statename) values (?)";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setString(1, areaBean.getName());
            rs = pstmt.execute();
        }catch(Exception e){
       	 	doCatchException("addState" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
        return rs;
	}

	@Override
	public boolean addStreet(AreaBean areaBean) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
            String sql = "insert into t_street (street_name,area_id) values (?,?)";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setString(1, areaBean.getName());
            pstmt.setInt(1, areaBean.getParentId());
            rs = pstmt.execute();
        }catch(Exception e){
       	 	doCatchException("addStreet" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
        return rs;
	}

	@Override
	public boolean addPianqu(AreaBean areaBean) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
            String sql = "insert into t_pianqu (pianqu_name,father_id) values (?,?)";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setString(1, areaBean.getName());
            pstmt.setInt(1, areaBean.getParentId());
            rs = pstmt.execute();
        }catch(Exception e){
       	 	doCatchException("areaBeans" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
        return rs;
	}

	@Override
	public boolean addHutong(AreaBean areaBean) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
            String sql = "insert into t_hutong (hutong_name,father_id) values (?,?)";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setString(1, areaBean.getName());
            pstmt.setInt(1, areaBean.getParentId());
            rs = pstmt.execute();
        }catch(Exception e){
       	 	doCatchException("areaBeans" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
        return rs;
	}

	@Override
	public boolean addCompany(AreaBean areaBean) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
            String sql = "insert into t_hutong (company_name) values (?)";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setString(1, areaBean.getName());
            rs = pstmt.execute();
        }catch(Exception e){
       	 	doCatchException("areaBeans" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
        return rs;
	}

	@Override
	public List<AreaBean> queryStateList(PageUtil page) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from t_state order by id desc limit "+page.getStartIndex()+","+page.getPageSize();
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            List<AreaBean> list = new ArrayList<AreaBean>();
            while(rs.next()){
            	AreaBean nb = new AreaBean();
            	nb.setId(rs.getInt("id"));
            	nb.setName(rs.getString("statename"));
            	list.add(nb);
            }
            return list;
        }catch(Exception e){
       	 	doCatchException("queryNormCategoryList" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
		return null;
	}

	@Override
	public int queryAreaTotal(String tableName) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select count(id) as con from "+tableName;
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            List<AreaBean> list = new ArrayList<AreaBean>();
            if(rs.next()){
            	return rs.getInt("con");
            }
        }catch(Exception e){
       	 	doCatchException("queryNormCategoryList" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
		return 0;
	}

	@Override
	public List<AreaBean> queryStreetList(PageUtil page) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select a.id,a.street_name,a.area_id,b.statename from t_street left a join t_state b on b.id=a.area_id order by a.id desc limit "+page.getStartIndex()+","+page.getPageSize();
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            List<AreaBean> list = new ArrayList<AreaBean>();
            while(rs.next()){
            	AreaBean nb = new AreaBean();
            	nb.setId(rs.getInt("id"));
            	nb.setName(rs.getString("street_name"));
            	nb.setParentId(rs.getInt("area_id"));
            	nb.setParentName(rs.getString("statename"));
            	list.add(nb);
            }
            return list;
        }catch(Exception e){
       	 	doCatchException("queryStreetList" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
		return null;
	}

	@Override
	public int queryStreetTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AreaBean> queryPianquList(PageUtil page) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select a.id,b.street_name,a.pianqu_name,a.father_id from t_pianqu left a join t_street b on b.id=a.father_id order by a.id desc limit "+page.getStartIndex()+","+page.getPageSize();
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            List<AreaBean> list = new ArrayList<AreaBean>();
            while(rs.next()){
            	AreaBean nb = new AreaBean();
            	nb.setId(rs.getInt("id"));
            	nb.setName(rs.getString("street_name"));
            	nb.setParentId(rs.getInt("father_id"));
            	nb.setParentName(rs.getString("street_name"));
            	list.add(nb);
            }
            return list;
        }catch(Exception e){
       	 	doCatchException("queryPianquList" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
		return null;
	}

	@Override
	public int queryPianquTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AreaBean> queryHutongList(PageUtil page) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select a.id,a.hutong_name,b.pianqu_name,a.father_id from t_hutong left a join t_pianqu b on b.id=a.father_id order by a.id desc limit "+page.getStartIndex()+","+page.getPageSize();
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            List<AreaBean> list = new ArrayList<AreaBean>();
            while(rs.next()){
            	AreaBean nb = new AreaBean();
            	nb.setId(rs.getInt("id"));
            	nb.setName(rs.getString("hutong_name"));
            	nb.setParentId(rs.getInt("father_id"));
            	nb.setParentName(rs.getString("pianqu_name"));
            	list.add(nb);
            }
            return list;
        }catch(Exception e){
       	 	doCatchException("queryHutongList" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
		return null;
	}

	@Override
	public int queryHutongTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateState(AreaBean stateBean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateStreet(AreaBean streetBean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePianqu(AreaBean pianquBean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateHutong(AreaBean hutongBean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCompany(AreaBean companyBean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<AreaBean> queryCompanyList(PageUtil page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int queryCompanyTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addState(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addStreet(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPianqu(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addHutong(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCompany(int id) {
		// TODO Auto-generated method stub
		
	}

}
