package wuye.manager.norm.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wuye.dao.DaoBasic;
import wuye.manager.norm.bean.NormCategoryBean;
import wuye.manager.norm.bean.NormItemBean;
import wuye.manager.norm.bean.NormLevelBean;
import wuye.manager.norm.dao.NormDao;
import wuye.manager.utils.PageUtil;

@Component("normDao")
public class NormDaoImpl extends DaoBasic implements NormDao {

	@Autowired
	private DataSource dataSource;

	//--------------------------------考核级别设置--------------------------------
	@Override
	public boolean addNormLevel(NormLevelBean normLevelBean){
		Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "insert into t_checklevel (level_name) values (?)";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setString(1, normLevelBean.getLevelName());
            int rs = pstmt.executeUpdate();
            if(rs>0){
            	return true;
            }
        }catch(Exception e){
       	 	doCatchException("addNormLevel" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
        return false;
	}
	
	@Override
	public boolean updateNormLevel(NormLevelBean normLevelBean){
		Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "update t_checklevel set level_name = ? where level_id=? ";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setString(1, normLevelBean.getLevelName());
            pstmt.setInt(2, normLevelBean.getLevelNo());
            int rs = pstmt.executeUpdate();
            if(rs>0){
            	return true;
            }
        }catch(Exception e){
       	 	doCatchException("updateNormLevel" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
        return false;
	}
	
	public void deleteNormLevel(int id){
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
        	String sql = "update t_checklevel set del = 1 where level_id=? ";
//            String sql = "delete from t_checklevel where level_id=? ";
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
            String sql = "select level_id,level_name from t_checklevel where del = 0";
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
	
	public boolean addNormCategory(NormCategoryBean normCategoryBean){
		Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "insert into t_checktitle (title_name,type) values (?,?)";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setString(1, normCategoryBean.getCategoryName());
            pstmt.setInt(2, normCategoryBean.getBusiness());
            int rs = pstmt.executeUpdate();
            if(rs>0){
            	return true;
            }
        }catch(Exception e){
       	 	doCatchException("addNormCategory" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
        return false;
	}
	
	public boolean updateNormCategory(NormCategoryBean normCategoryBean){
		Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "update t_checktitle set title_name=?,type=? where score_id=?";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setString(1, normCategoryBean.getCategoryName());
            pstmt.setInt(2, normCategoryBean.getBusiness());
            pstmt.setInt(3, normCategoryBean.getCategoryNo());
            int rs = pstmt.executeUpdate();
            if(rs>0){
            	return true;
            }
        }catch(Exception e){
       	 	doCatchException("updateNormCategory" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
        return false;
	}
	
	public void deleteNormCategory(int id){
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
        	String sql = "update t_checktitle set del = 1 where score_id=? ";
//            String sql = "delete from t_checktitle where score_id=?";
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
            String sql = "select * from t_checktitle where del = 0 order by score_id limit "+page.getStartIndex()+","+page.getPageSize();
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
	public List<NormCategoryBean> queryNormCategoryList(int type){
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from t_checktitle  where del = 0";
            if(type>0){
            	sql+=" and type="+type;
            }
            sql+=" order by score_id";
            
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            List<NormCategoryBean> list = new ArrayList<NormCategoryBean>();
            while(rs.next()){
            	NormCategoryBean nb = new NormCategoryBean();
            	nb.setCategoryNo(rs.getInt("score_id"));
            	nb.setCategoryName(rs.getString("title_name"));
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
            String sql = "select count(score_id) as cou from t_checktitle  where del = 0";
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
	
	public boolean addNormItem(NormItemBean normItemBean){
		Serializable ret = null;
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet idRs=null;
        boolean rs=false;
        try {
            String sql = "insert into t_checksub (titleid,sub_name,score) values (?,?,?)";
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, normItemBean.getCategoryNo());
            pstmt.setString(2, normItemBean.getItemContent());
            pstmt.setString(3, normItemBean.getScoreCount());
            pstmt.executeUpdate();
            idRs = pstmt.getGeneratedKeys();
            if (idRs.next()) {
                ret = (Serializable) idRs.getObject(1);
                rs = true;
            }
            
            List<NormItemBean.NormScoreBean> list = normItemBean.getScoreList();
            if(list!=null&&list.size()>0){
            	sql = "insert into t_checkscore (levelid,subid,score) values ";//(?,?,?)";
            	for(int i=0;i<list.size();i++){
            		sql+="("+list.get(i).getLevelNo()+","+ret+","+list.get(i).getScore()+")";
            		if((i+1)<list.size()){
            			sql+=",";
            		}
            	}
            	pstmt = prepareStatement(conn, sql);
            	pstmt.execute();
            }
            
        }catch(Exception e){
       	 	doCatchException("addNormCategory" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
        return rs;
	}
	
	public boolean updateNormItem(NormItemBean normItemBean){
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
            String sql = "update t_checksub set titleid=?,sub_name=?,score=? where subid=?";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setInt(1, normItemBean.getCategoryNo());
            pstmt.setString(2, normItemBean.getItemContent());
            pstmt.setString(3, normItemBean.getScoreCount());
            pstmt.setInt(4, normItemBean.getItemNo());
            int ret = pstmt.executeUpdate();
            if(ret>0){
            	rs = true;
            }
            
            //先删除后添加
            sql = "delete from t_checkscore where subid=?";
            pstmt = prepareStatement(conn, sql);
            pstmt.setInt(1, normItemBean.getItemNo());
            pstmt.executeUpdate();
            
            //添加
            List<NormItemBean.NormScoreBean> list = normItemBean.getScoreList();
            if(list!=null&&list.size()>0){
            	sql = "insert into t_checkscore (levelid,subid,score) values ";//(?,?,?)";
            	for(int i=0;i<list.size();i++){
            		sql+="("+list.get(i).getLevelNo()+","+normItemBean.getItemNo()+","+list.get(i).getScore()+")";
            		if((i+1)<list.size()){
            			sql+=",";
            		}
            	}
            	pstmt = prepareStatement(conn, sql);
            	pstmt.execute();
            }
            
        }catch(Exception e){
       	 	doCatchException("updateNormItem" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
        return rs;
	}
	
	public void deleteNormItem(int id){
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
        	//先删除子表
        	String sql = "update t_checkscore set del = 1 where subid=?";
//        	String sql = "delete from t_checkscore where subid=?";
        	conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        	
            //删除主表
            sql = "update t_checksub set del = 1 where subid=?";
//            sql = "delete from t_checksub where subid=?";
            
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
            String sql = "select subid,a.titleid,b.title_name,sub_name,score from t_checksub a left join t_checktitle b on a.titleid = b.score_id  where a.del = 0 order by subid limit "+page.getStartIndex()+","+page.getPageSize();
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            List<NormItemBean> list = new ArrayList<NormItemBean>();
            String subids = "(";
            
            Map<Integer,Integer> map = new HashMap<Integer,Integer>();
            int i = 0;
            while(rs.next()){
            	NormItemBean nb = new NormItemBean();
            	int subid = rs.getInt("subid");
            	nb.setItemNo(subid);
            	subids+=subid+",";
            	nb.setCategoryNo(rs.getInt("titleid"));
            	nb.setItemContent(rs.getString("sub_name"));
            	nb.setCategoryName(rs.getString("title_name"));
            	nb.setScoreCount(rs.getString("score"));
            	list.add(nb);
            	//临时保存--对象在list中的位置
            	map.put(subid, i);
            	i++;
            }
            subids+="0)";
            
            sql="select levelid,b.level_name,subid,score from t_checkscore a left join t_checklevel b on a.levelid = b.level_id where a.del = 0 and a.subid in "+subids+" order by a.levelid";
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
            	NormItemBean.NormScoreBean nsb = new NormItemBean.NormScoreBean(); 
            	nsb.setLevelNo(rs.getInt("levelid"));
            	nsb.setLevelName(rs.getString("level_name"));
            	nsb.setScore(rs.getString("score"));
            	list.get(map.get(rs.getInt("subid"))).addScoreBeanToList(nsb);
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
            String sql = "select count(subid) as cou from t_checksub where del = 0";
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
