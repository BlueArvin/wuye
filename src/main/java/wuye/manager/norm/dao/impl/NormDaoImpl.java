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
import wuye.manager.norm.bean.NormBean;
import wuye.manager.norm.bean.NormCategoryBean;
import wuye.manager.norm.bean.NormItemBean;
import wuye.manager.norm.bean.NormLevelBean;
import wuye.manager.norm.dao.NormDao;
import wuye.manager.user.bean.UserBean;
import wuye.manager.utils.PageUtil;

@Component("normDao")
public class NormDaoImpl extends DaoBasic implements NormDao {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public boolean addPianQu(NormBean normBean) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
            String sql = "insert into t_pianqu (pianqu_name,father_id,type) values (?,?,?)";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setString(1, normBean.getPianquName());
            pstmt.setInt(2, normBean.getFatherId());
            pstmt.setInt(3, normBean.getType());
            rs = pstmt.execute();
        }catch(Exception e){
       	 	doCatchException("addUser" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
        return rs;
	}

	@Override
	public List<NormBean> findAllPianQu(NormBean normBean, PageUtil page) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select id,pianqu_name,father_id,type from t_pianqu"
            		+ " order by id desc limit "+page.getStartIndex()+","+page.getPageSize();
            
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            List<NormBean> list = new ArrayList<NormBean>();
            while(rs.next()){
            	NormBean ub = new NormBean();
            	ub.setId(rs.getInt("id"));
            	ub.setPianquName(rs.getString("pianqu_name"));
            	ub.setFatherId(rs.getInt("father_id"));
            	ub.setType(rs.getInt("type"));
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
	public List<NormBean> findPianQuByFatherId(int fatherId) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select id,pianqu_name,father_id,type from t_pianqu where father_id=?";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setInt(1, fatherId);
            rs = pstmt.executeQuery();
            List<NormBean> list = new ArrayList<NormBean>();
            while(rs.next()){
            	NormBean ub = new NormBean();
            	ub.setId(rs.getInt("id"));
            	ub.setPianquName(rs.getString("pianqu_name"));
            	ub.setFatherId(rs.getInt("father_id"));
            	ub.setType(rs.getInt("type"));
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
	public NormBean getNormInfo(int id) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select id,pianqu_name,father_id,type from t_pianqu where id=?";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()){
            	NormBean ub = new NormBean();
            	ub.setId(rs.getInt("id"));
            	ub.setPianquName(rs.getString("pianqu_name"));
            	ub.setFatherId(rs.getInt("father_id"));
            	ub.setType(rs.getInt("type"));
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

	

	//--------------------------------考核级别设置--------------------------------
	
	public void addNormLevel(NormLevelBean normLevelBean){
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
            String sql = "insert into t_checklevel (level_name) values (?)";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setString(1, normLevelBean.getLevelName());
            rs = pstmt.execute();
        }catch(Exception e){
       	 	doCatchException("addNormLevel" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
	}
	
	public void updateNormLevel(NormLevelBean normLevelBean){
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
            String sql = "update t_checklevel set level_name = ? where level_id=? ";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setString(1, normLevelBean.getLevelName());
            pstmt.setInt(2, normLevelBean.getLevelNo());
            rs = pstmt.execute();
        }catch(Exception e){
       	 	doCatchException("deleteNormLevel" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
	}
	
	public void deleteNormLevel(int id){
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
            String sql = "delete from t_checklevel where level_id=? ";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setInt(1, id);
            rs = pstmt.execute();
        }catch(Exception e){
       	 	doCatchException("deleteNormLevel" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
	}
	
	public List<NormLevelBean> queryNormLevelList(){
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select level_id,level_name from t_checklevel";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            List<NormLevelBean> list = new ArrayList<NormLevelBean>();
            while(rs.next()){
            	NormLevelBean nb = new NormLevelBean();
            	nb.setLevelNo(rs.getInt("level_id"));
            	nb.setLevelName(rs.getString("level_name"));
            	list.add(nb);
            }
            return list;
        }catch(Exception e){
       	 	doCatchException("queryNormLevelList" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
        return null;
	}
	
	
	//--------------------------------考核类别设置--------------------------------
	
	public void addNormCategory(NormCategoryBean normCategoryBean){
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {

            String sql = "insert into t_checktitle (title_name,type) values (?,?)";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setString(1, normCategoryBean.getCategoryName());
            pstmt.setInt(2, normCategoryBean.getBusiness());
            rs = pstmt.execute();
        }catch(Exception e){
       	 	doCatchException("addNormCategory" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
	}
	
	public void updateNormCategory(NormCategoryBean normCategoryBean){
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
            String sql = "update t_checktitle set title_name=?,type=? where score_id=?";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setString(1, normCategoryBean.getCategoryName());
            pstmt.setInt(2, normCategoryBean.getBusiness());
            pstmt.setInt(3, normCategoryBean.getCategoryNo());
            rs = pstmt.execute();
        }catch(Exception e){
       	 	doCatchException("updateNormCategory" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
	}
	
	public void deleteNormCategory(int id){
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
            String sql = "delete from t_checktitle where score_id=?";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setInt(1, id);
            rs = pstmt.execute();
        }catch(Exception e){
       	 	doCatchException("deleteNormCategory" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
	}
	
	@Override
	public List<NormCategoryBean> queryNormCategoryList(PageUtil page){
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from t_checktitle order by score_id limit "+page.getStartIndex()+","+page.getPageSize();
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            List<NormCategoryBean> list = new ArrayList<NormCategoryBean>();
            while(rs.next()){
            	NormCategoryBean nb = new NormCategoryBean();
            	nb.setCategoryNo(rs.getInt("score_id"));
            	nb.setCategoryName(rs.getString("title_name"));
            	nb.setBusiness(rs.getInt("type"));
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
	public int getNormCategoryTotal(){
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select count(score_id) as cou from t_checktitle";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            if(rs.next()){
            	return rs.getInt("cou");
            }
        }catch(Exception e){
       	 	doCatchException("getNormCategoryTotal" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
        return 0;
	}
	
	//--------------------------------考核项目设置--------------------------------
	
	public void addNormItem(NormItemBean normItemBean){
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
            String sql = "insert into t_checksub (titleid,sub_name) values (?,?)";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setInt(1, normItemBean.getCategoryNo());
            pstmt.setString(2, normItemBean.getItemContent());
            rs = pstmt.execute();
        }catch(Exception e){
       	 	doCatchException("addNormCategory" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
	}
	
	public void updateNormItem(NormItemBean normItemBean){
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
            String sql = "update t_checksub set titleid=?,sub_name=? where subid=?";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setInt(1, normItemBean.getItemNo());
            pstmt.setString(2, normItemBean.getItemContent());
            pstmt.setInt(3, normItemBean.getCategoryNo());
            rs = pstmt.execute();
        }catch(Exception e){
       	 	doCatchException("updateNormItem" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
	}
	
	public void deleteNormItem(int id){
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
            String sql = "delete from t_checksub where subid=?";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setInt(1, id);
            rs = pstmt.execute();
        }catch(Exception e){
       	 	doCatchException("deleteNormItem" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
	}
	
	public List<NormItemBean> queryNormItemList(PageUtil page){
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select subid,titleid,sub_name from t_checksub order by subid limit "+page.getStartIndex()+","+page.getPageSize();
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            List<NormItemBean> list = new ArrayList<NormItemBean>();
            while(rs.next()){
            	NormItemBean nb = new NormItemBean();
            	nb.setItemNo(rs.getInt("subid"));
            	nb.setCategoryNo(rs.getInt("titleid"));
            	nb.setItemContent(rs.getString("sub_name"));
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
	public int getNormItemTotal(){
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select count(subid) as cou from t_checksub";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            if(rs.next()){
            	return rs.getInt("cou");
            }
        }catch(Exception e){
       	 	doCatchException("getNormItemTotal" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
        return 0;
	}
	
}
