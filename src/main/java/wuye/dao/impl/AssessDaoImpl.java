package wuye.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import wuye.bean.AssessDataBean;
import wuye.bean.SortBean;
import wuye.dao.AssessDao;
import wuye.dao.DaoBasic;
import wuye.manager.assess.bean.ManAssessBean;
import wuye.manager.norm.logic.AreaLogic;

public class AssessDaoImpl extends DaoBasic implements AssessDao {

	@Autowired
	private AreaLogic areaLogic;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private DataSource dataSource;
	
	public int submit(AssessDataBean data) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        boolean rs = false;
        try {
            String sql = "insert into t_assess(intime, streetid, areaid, pianquid, hutongid, wuyeid, assessid,assessidtop,"
            		+ " yeneiid, jibieid, "
            		+ " score, userid, img1, img2, img3, img4, aseid, loc, msg) "
            		+ " value(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ? ,?,?) ";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setTimestamp(1, new Timestamp(data.getTime().getTime()));
            pstmt.setInt(2, data.getStreetid());
            pstmt.setInt(3, data.getAreaid());
            pstmt.setInt(4, data.getPianquid());
            pstmt.setInt(5, data.getHutongid());
            pstmt.setInt(6, data.getWuyeid());
            pstmt.setInt(7, data.getAssessid());
            pstmt.setInt(8, data.getAssessidtop());
            pstmt.setInt(9, data.getYeneiid());
            pstmt.setInt(10, data.getJibieid());
            pstmt.setInt(11, data.getScore());
            pstmt.setInt(12, data.getUserid());
            pstmt.setString(13, data.getImg1());
            pstmt.setString(14, data.getImg2());
            pstmt.setString(15, data.getImg3());
            pstmt.setString(16, data.getImg4());
            pstmt.setString(17, data.getSerailID());
            pstmt.setString(18, data.getLoc());
            pstmt.setString(19, data.getMsg());
            rs = pstmt.execute();
            
            return 0;
        }catch(SQLException e) {
        	if(e.getErrorCode() == 1062) {
        		return 0;
        	}
        	return -1;
        }catch(Exception e){
       	 	doCatchException("getBlockList" ,e);
            return -1;
        } finally {
            closeConnection(conn, pstmt, null);
        }
	}


	public List<String> getPoint() {
		List<String> ret = new ArrayList<String>();
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select loc from t_assess";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	ret.add(rs.getString("loc"));
            }
        }catch(Exception e){
       	 	doCatchException("getBlockList" ,e);
	       	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	       	return ret;
        } finally {
            closeConnection(conn, pstmt, null);
        }
		return ret;
	}

	@Override
	public List<AssessDataBean> getDetailitem(Date dStart, Date dEnd, String areaid, String checkyewai,
			String checktitle, int page) {
		// TODO Auto-generated method stub
		List<AssessDataBean> list = new ArrayList<AssessDataBean>();
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String sql = "select t.aseid,t.id,t.score,t.areaid,t.streetid,t.pianquid,t.hutongid,t.userid,t.wuyeid,"
            		+ "t.assessidtop,t.assessid,t.intime,t.img1,t.img2,t.img3, t.img4, t.yeneiid,t.msg ,u.userName, ct.title_name, cb.sub_name "
            		+ " from t_assess t LEFT JOIN t_user u ON u.id = t.userid LEFT JOIN t_checktitle ct ON  ct.score_id = t.assessidtop"
            		+ " LEFT JOIN t_checksub cb ON cb.subid = t.assessid  where t.del = 0 ";
            
            sql += " and TIMESTAMPDIFF(SECOND, intime,'" + df.format(dStart) + "')<0 and TIMESTAMPDIFF(SECOND, intime,'" + df.format(dEnd) + "')>0 ";
            
            if(!checkyewai.equals("0")) {
            	sql += " and yeneiid=" + checkyewai + " ";
            }
            
            if(!checktitle.equals("0")) {
            	sql += " and assessidtop=" + checktitle + " ";
            }
            
            switch(areaid.charAt(0)) {
            case 's':
            	sql += " and areaid=" + areaid.substring(1) + " ";
            case 't':
            	sql += " and streetid=" + areaid.substring(1) + " ";
            case 'p':
            	sql += " and pianquid=" + areaid.substring(1) + " ";
            case 'h':
            	sql += " and hutongid=" + areaid.substring(1) + " ";
            }
            
            int curindex = (page-1)*20;
            sql += " limit "+ curindex +", 20";
            
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
            	AssessDataBean ad = new AssessDataBean();
            	ad.setScore(rs.getInt("score"));
            	ad.setYeneiid(rs.getInt("yeneiid"));
            	
            	ad.setImg1("" + rs.getString("img1"));
            	ad.setImg2("" + rs.getString("img2"));
            	ad.setImg3("" + rs.getString("img3"));
            	ad.setImg4("" + rs.getString("img4"));
            	ad.setSerailID(rs.getString("aseid"));
            	
            	
            	int dbareaid = rs.getInt("areaid");
            	ad.setAreaid(dbareaid);
            	ad.setAreaName(areaLogic.getAreaName(dbareaid, 1));
            	
            	int dbsteetid = rs.getInt("streetid");
            	ad.setStreetid(dbsteetid);
            	ad.setStreetName(areaLogic.getAreaName(dbsteetid, 2));
            	
            	int dbpianquid = rs.getInt("pianquid");
            	ad.setPianquid(dbpianquid);
            	ad.setPianquName(areaLogic.getAreaName(dbpianquid,3));
            	
            	int dbhutongid = rs.getInt("hutongid");
            	ad.setHutongid(dbhutongid);
            	ad.setHutongName(areaLogic.getAreaName(dbhutongid,4));
            	
            	int dbwuyeid = rs.getInt("wuyeid");
            	ad.setWuyeid(dbwuyeid);
            	ad.setWuyeName(areaLogic.getAreaName(dbwuyeid,5));
            	
            	ad.setUserid(rs.getInt("userid"));
            	ad.setAssessid(rs.getInt("assessid"));
            	ad.setAssessidtop(rs.getInt("assessidtop"));
            	ad.setTime(rs.getDate("intime"));
            	ad.setMsg(rs.getString("msg"));
            	ad.setChecktopName(rs.getString("title_name"));
            	ad.setChecksubName(rs.getString("sub_name"));
            	ad.setCheckerName(rs.getString("userName"));
            	list.add(ad);
            }
            return list;
        }catch(Exception e) {
        	e.printStackTrace();
        	return list;
        }finally {
            closeConnection(conn, pstmt, rs);
        }
	}

	@Override
	public int weekSumWuye() {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        long time = new Date().getTime() - 7*24*3600*1000;
        
        List<SortBean> list = new ArrayList<SortBean>();
        
        try {
            String sql = "SELECT wuyeid,SUM(score) as score FROM t_assess ";
            
            sql += "  where TIMESTAMPDIFF(SECOND, intime,'" + df.format(new Date(time)) + "')<0 and TIMESTAMPDIFF(SECOND, intime,'" + df.format(new Date()) + "')>0 "
            		+ "GROUP BY wuyeid ORDER BY SUM(score) ";
            
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            
            int paiming = 1;
            
            float score = 0;
            
            int index = 0;
            while(rs.next()){
            	index++;
            
            	SortBean bean = new SortBean();
            	bean.setDisId(rs.getInt("wuyeid"));
            	bean.setTime(new Date(time - 5*24*3600*1000) );
            	bean.setScore(rs.getFloat("score"));
            	float ss = rs.getFloat("score");
            	if(ss > score) {
            		score = ss;
            		paiming ++;
            		bean.setPaiming(index);
            	} else {
            		bean.setPaiming(paiming);
            	}
            	list.add(bean);
            }
        } catch(Exception e) {
        	
        }finally {
            closeConnection(conn, pstmt, rs);
        }
        
        if(list.size() ==0 ) {return 0; } 
        SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMdd");
        try {
            String sql = "replace into tb_weekwuye(wuyeid, atime, score, paiming) value(?,?,?,?)";
            
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            pstmt = prepareStatement(conn, sql);
            
            int length = list.size();
            for(int i = 0; i< length;i++) {
	            pstmt.setInt(1, list.get(i).getDisId());
	            pstmt.setString(2, df2.format(list.get(i).getTime()));
	            pstmt.setInt(4, list.get(i).getPaiming());
	            pstmt.setFloat(3, list.get(i).getScore());
	            pstmt.execute();
            }
            conn.commit();
            
        } catch(Exception e) {
        	
        }finally {
            closeConnection(conn, pstmt, rs);
        }
        
		return list.size();
	}

	@Override
	public int weekSumPianqu() {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        long time = new Date().getTime() - 7*24*3600*1000;
        
        List<SortBean> list = new ArrayList<SortBean>();
        
        try {
            String sql = "SELECT pianquid,SUM(score) as score FROM t_assess ";
            
            sql += " where TIMESTAMPDIFF(SECOND, intime,'" + df.format(new Date(time)) + "')<0 and TIMESTAMPDIFF(SECOND, intime,'" + df.format(new Date()) + "')>0 "
            		+ " GROUP BY pianquid ORDER BY SUM(score) ";
            
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            
            int paiming = 1;
            
            float score = 0;
            
            int index = 0;
            while(rs.next()){
            	index++;
            
            	SortBean bean = new SortBean();
            	bean.setDisId(rs.getInt("pianquid"));
            	bean.setTime(new Date(time - 5*24*3600*1000) );
            	bean.setScore(rs.getFloat("score"));
            	float ss = rs.getFloat("score");
            	if(ss > score) {
            		score = ss;
            		paiming ++;
            		bean.setPaiming(index);
            	} else {
            		bean.setPaiming(paiming);
            	}
            	list.add(bean);
            }
        } catch(Exception e) {
        	
        }finally {
            closeConnection(conn, pstmt, rs);
        }
        
        if(list.size() ==0 ) {return 0; } 
        SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMdd");
        try {
            String sql = "replace into tb_weekpianqu(pianquid, atime, score, paiming) value(?,?,?,?)";
            
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            pstmt = prepareStatement(conn, sql);
            
            int length = list.size();
            for(int i = 0; i< length;i++) {
	            pstmt.setInt(1, list.get(i).getDisId());
	            pstmt.setString(2, df2.format(list.get(i).getTime()));
	            pstmt.setInt(4, list.get(i).getPaiming());
	            pstmt.setFloat(3, list.get(i).getScore());
	            pstmt.execute();
            }
            conn.commit();
            
        } catch(Exception e) {
        	
        }finally {
            closeConnection(conn, pstmt, rs);
        }
        
		return 0;
	}

	@Override
	public int monthSumWuye() {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        
        List<SortBean> list = new ArrayList<SortBean>();
        
        try {
            String sql = "SELECT wuyeid,SUM(score) as score FROM t_assess ";
            
            sql += "  where TIMESTAMPDIFF(SECOND, intime,'" + df.format(m) + "')<0 and TIMESTAMPDIFF(SECOND, intime,'" + df.format(new Date()) + "')>0 "
            		+ "GROUP BY wuyeid ORDER BY SUM(score) ";
            
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            
            int paiming = 1;
            
            float score = 0;
            
            int index = 0;
            while(rs.next()){
            	index++;
            
            	SortBean bean = new SortBean();
            	bean.setDisId(rs.getInt("wuyeid"));
            	bean.setTime(new Date());
            	bean.setScore(rs.getFloat("score"));
            	float ss = rs.getFloat("score");
            	if(ss > score) {
            		score = ss;
            		paiming ++;
            		bean.setPaiming(index);
            	} else {
            		bean.setPaiming(paiming);
            	}
            	list.add(bean);
            }
        } catch(Exception e) {
        	
        }finally {
            closeConnection(conn, pstmt, rs);
        }
        
        if(list.size() ==0 ) {return 0; } 
        SimpleDateFormat df2 = new SimpleDateFormat("yyyyMM");
        try {
            String sql = "replace into tb_monthwuye(wuyeid, atime, score, paiming) value(?,?,?,?)";
            
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            pstmt = prepareStatement(conn, sql);
            
            int length = list.size();
            for(int i = 0; i< length;i++) {
	            pstmt.setInt(1, list.get(i).getDisId());
	            pstmt.setString(2, df2.format(list.get(i).getTime()));
	            pstmt.setInt(4, list.get(i).getPaiming());
	            pstmt.setFloat(3, list.get(i).getScore());
	            pstmt.execute();
            }
            conn.commit();
            
        } catch(Exception e) {
        	
        }finally {
            closeConnection(conn, pstmt, rs);
        }
        
		return list.size();
	}

	@Override
	public int monthSumPianqu() {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        
        List<SortBean> list = new ArrayList<SortBean>();
        
        try {
            String sql = "SELECT pianquid,SUM(score) as score FROM t_assess ";
            
            sql += " where TIMESTAMPDIFF(SECOND, intime,'" + df.format(m) + "')<0 and TIMESTAMPDIFF(SECOND, intime,'" + df.format(new Date()) + "')>0 "
            		+ " GROUP BY pianquid ORDER BY SUM(score) ";
            
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            
            int paiming = 1;
            
            float score = 0;
            
            int index = 0;
            while(rs.next()){
            	index++;
            
            	SortBean bean = new SortBean();
            	bean.setDisId(rs.getInt("pianquid"));
            	bean.setTime(new Date() );
            	bean.setScore(rs.getFloat("score"));
            	float ss = rs.getFloat("score");
            	if(ss > score) {
            		score = ss;
            		paiming ++;
            		bean.setPaiming(index);
            	} else {
            		bean.setPaiming(paiming);
            	}
            	list.add(bean);
            }
        } catch(Exception e) {
        	
        }finally {
            closeConnection(conn, pstmt, rs);
        }
        
        if(list.size() ==0 ) {return 0; } 
        SimpleDateFormat df2 = new SimpleDateFormat("yyyyMM");
        try {
            String sql = "replace into tb_monthpianqu(pianquid, atime, score, paiming) value(?,?,?,?)";
            
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            pstmt = prepareStatement(conn, sql);
            
            int length = list.size();
            for(int i = 0; i< length;i++) {
	            pstmt.setInt(1, list.get(i).getDisId());
	            pstmt.setString(2, df2.format(list.get(i).getTime()));
	            pstmt.setInt(4, list.get(i).getPaiming());
	            pstmt.setFloat(3, list.get(i).getScore());
	            pstmt.execute();
            }
            conn.commit();
            
        } catch(Exception e) {
        	
        }finally {
            closeConnection(conn, pstmt, rs);
        }
        
		return 0;
	}

}
