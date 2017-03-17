package wuye.manager.assess.dao.impl;

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
import wuye.manager.assess.bean.ManAssessBean;
import wuye.manager.assess.dao.ManAssessDao;
import wuye.manager.menu.dao.LoginDao;
import wuye.manager.norm.bean.AreaBean;
import wuye.manager.norm.logic.AreaLogic;
import wuye.manager.user.bean.UserBean;
import wuye.manager.utils.PageUtil;

@Component("manAssessDao")
public class ManAssessDaoImpl extends DaoBasic implements ManAssessDao{

	@Autowired
	private DataSource dataSource;

	@Autowired
	private AreaLogic areaLogic;
	
	@Override
	public List<ManAssessBean> queryList(ManAssessBean manAssessBean,
			PageUtil page) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select t.id,t.score,t.areaid,t.streetid,t.pianquid,t.hutongid,t.userid,t.wuyeid,t.assessidtop,t.assessid,t.intime from t_assess t where 1=1 ";
            
            if(manAssessBean.getAreaid()!=0){
            	sql+=" and t.areaid = "+ manAssessBean.getAreaid();
            }
            
            if(manAssessBean.getStreetid()!=0){
            	sql+=" and t.streetid = "+ manAssessBean.getStreetid();
            }
            
            if(manAssessBean.getUserid()!=0){
            	sql+=" and t.userid = "+ manAssessBean.getUserid();
            }

            if(manAssessBean.getTime()!=null){
            	sql+=" and DATEDIFF(t.intime,"+manAssessBean.getTime()+")=0 ";
            }
            
            sql+=" limit "+page.getStartIndex()+","+page.getPageSize();
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            List<ManAssessBean> list = new ArrayList<ManAssessBean>();
            while(rs.next()){
            	ManAssessBean nb = new ManAssessBean();
            	nb.setId(String.valueOf(rs.getInt("id")));
            	
            	int areaid = rs.getInt("areaid");
            	nb.setAreaid(areaid);
            	nb.setAreaName(areaLogic.getAreaName(areaid, 1));
            	
            	int steetid = rs.getInt("streetid");
            	nb.setStreetid(steetid);
            	nb.setStreetName(areaLogic.getAreaName(steetid, 2));
            	
            	int pianquid = rs.getInt("pianquid");
            	nb.setPianquid(pianquid);
            	nb.setPianquName(areaLogic.getAreaName(pianquid,3));
            	
            	int hutongid = rs.getInt("hutongid");
            	nb.setHutongid(hutongid);
            	nb.setHutongName(areaLogic.getAreaName(hutongid,4));
            	
            	int wuyeid = rs.getInt("wuyeid");
            	nb.setWuyeid(wuyeid);
            	nb.setWuyeName(areaLogic.getAreaName(wuyeid,5));
            	
            	nb.setUserid(rs.getInt("userid"));
            	nb.setAssessid(rs.getInt("assessid"));
            	nb.setAssessidtop(rs.getInt("assessidtop"));
            	nb.setScore(rs.getInt("score"));
            	nb.setTime(rs.getDate("intime"));
            	list.add(nb);
            }
            return list;
        }catch(Exception e){
       	 	doCatchException("queryStateList" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
		return null;
	}

	@Override
	public int getTotal(ManAssessBean manAssessBean) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select count(id) as con from t_assess t where 1=1 ";
            
            if(manAssessBean.getAreaid()!=0){
            	sql+=" and t.areaid = "+ manAssessBean.getAreaid();
            }
            
            if(manAssessBean.getStreetid()!=0){
            	sql+=" and t.streetid = "+ manAssessBean.getStreetid();
            }
            
            if(manAssessBean.getUserid()!=0){
            	sql+=" and t.userid = "+ manAssessBean.getUserid();
            }

            if(manAssessBean.getTime()!=null){
            	sql+=" and DATEDIFF(t.intime,"+manAssessBean.getTime()+")=0 ";
            }
            
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            if(rs.next()){
            	return rs.getInt("con");
            }
        }catch(Exception e){
       	 	doCatchException("queryStateList" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
		return 0;
	}

	@Override
	public ManAssessBean getAssessInfo(ManAssessBean manAssessBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateAssessInfo(ManAssessBean manAssessBean) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        int rs = 0;
        try {
            String sql = "update t_assess set score = ? where id=? ";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            
            pstmt.setInt(1, manAssessBean.getScore());
            pstmt.setString(2, manAssessBean.getId());
            rs = pstmt.executeUpdate();
            if(rs>0){
            	return true;
            }
        }catch(Exception e){
       	 	doCatchException("queryStateList" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
		return false;
	}

	@Override
	public boolean delAssessInfo(ManAssessBean manAssessBean) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        int rs = 0;
        try {
            String sql = "delete from t_assess where id=? ";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setString(1, manAssessBean.getId());
            rs = pstmt.executeUpdate();
            if(rs>0){
            	return true;
            }
        }catch(Exception e){
       	 	doCatchException("queryStateList" ,e);
        } finally {
            closeConnection(conn, pstmt, null);
        }
		return false;
	}

	
	
}
