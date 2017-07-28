package wuye.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import wuye.bean.AssessDataBean;
import wuye.bean.BadCheckList;
import wuye.bean.CheckDayItem;
import wuye.bean.CheckTitle;
import wuye.bean.JisuanBean;
import wuye.bean.JisuanSortBean;
import wuye.bean.PianquData;
import wuye.bean.PianquRelationHead;
import wuye.bean.PianquSortListBean;
import wuye.bean.PicBean;
import wuye.bean.SortBean;
import wuye.bean.WuyeSortListBean;
import wuye.dao.AssessDao;
import wuye.dao.DaoBasic;
import wuye.manager.assess.bean.ManAssessBean;
import wuye.manager.norm.logic.AreaLogic;
import wuye.manager.utils.StringUtil;

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
        	int score = data.getScore();
        	if(score == -1000) {  //正常扣分
	            String sql = "insert into t_assess(intime, streetid, areaid, pianquid, hutongid, wuyeid, assessid,assessidtop,"
	            		+ " yeneiid, jibieid, "
	            		+ " score, userid, img1, img2, img3, img4, aseid, loc, msg) "
	            		+ " value(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, getCurScore(?,?), ?, ?, ?, ?,?, ? ,?,?) ";
	            conn = dataSource.getConnection();
	            pstmt = prepareStatement(conn, sql);
	            pstmt.setTimestamp(1, new Timestamp(new Date().getTime()));
	            pstmt.setInt(2, data.getStreetid());
	            pstmt.setInt(3, data.getAreaid());
	            pstmt.setInt(4, data.getPianquid());
	            pstmt.setInt(5, data.getHutongid());
	            pstmt.setInt(6, data.getWuyeid());
	            pstmt.setInt(7, data.getAssessid());
	            pstmt.setInt(8, data.getAssessidtop());
	            pstmt.setInt(9, data.getYeneiid());
	            pstmt.setInt(10, data.getJibieid());
	            pstmt.setInt(11, data.getAssessid());
	            pstmt.setInt(12, data.getPianquid());
	            pstmt.setInt(13, data.getUserid());
	            pstmt.setString(14, data.getImg1());
	            pstmt.setString(15, data.getImg2());
	            pstmt.setString(16, data.getImg3());
	            pstmt.setString(17, data.getImg4());
	            pstmt.setString(18, data.getSerailID());
	            pstmt.setString(19, data.getLoc());
	            pstmt.setString(20, data.getMsg());
	            rs = pstmt.execute();
        	}else if(score == -2000) { // 扣完
        		String sql = "insert into t_assess(intime, streetid, areaid, pianquid, hutongid, wuyeid, assessid,assessidtop,"
	            		+ " yeneiid, jibieid, "
	            		+ " score, userid, img1, img2, img3, img4, aseid, loc, msg) "
	            		+ " value(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, getMaxScore(?), ?, ?, ?, ?,?, ? ,?,?) ";
	            conn = dataSource.getConnection();
	            pstmt = prepareStatement(conn, sql);
	            pstmt.setTimestamp(1, new Timestamp(new Date().getTime()));
	            pstmt.setInt(2, data.getStreetid());
	            pstmt.setInt(3, data.getAreaid());
	            pstmt.setInt(4, data.getPianquid());
	            pstmt.setInt(5, data.getHutongid());
	            pstmt.setInt(6, data.getWuyeid());
	            pstmt.setInt(7, data.getAssessid());
	            pstmt.setInt(8, data.getAssessidtop());
	            pstmt.setInt(9, data.getYeneiid());
	            pstmt.setInt(10, data.getJibieid());
	            pstmt.setInt(11, data.getAssessid());
	            pstmt.setInt(12, data.getUserid());
	            pstmt.setString(13, data.getImg1());
	            pstmt.setString(14, data.getImg2());
	            pstmt.setString(15, data.getImg3());
	            pstmt.setString(16, data.getImg4());
	            pstmt.setString(17, data.getSerailID());
	            pstmt.setString(18, data.getLoc());
	            pstmt.setString(19, data.getMsg());
	            rs = pstmt.execute();
        	} else {     // 分数扣分
        		String sql = "insert into t_assess(intime, streetid, areaid, pianquid, hutongid, wuyeid, assessid,assessidtop,"
	            		+ " yeneiid, jibieid, "
	            		+ " score, userid, img1, img2, img3, img4, aseid, loc, msg) "
	            		+ " value(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ? ,?,?) ";
	            conn = dataSource.getConnection();
	            pstmt = prepareStatement(conn, sql);
	            pstmt.setTimestamp(1, new Timestamp(new Date().getTime()));
	            pstmt.setInt(2, data.getStreetid());
	            pstmt.setInt(3, data.getAreaid());
	            pstmt.setInt(4, data.getPianquid());
	            pstmt.setInt(5, data.getHutongid());
	            pstmt.setInt(6, data.getWuyeid());
	            pstmt.setInt(7, data.getAssessid());
	            pstmt.setInt(8, data.getAssessidtop());
	            pstmt.setInt(9, data.getYeneiid());
	            pstmt.setInt(10, data.getJibieid());
	            pstmt.setDouble(11, ((double)data.getScore())/10);
	            pstmt.setInt(12, data.getUserid());
	            pstmt.setString(13, data.getImg1());
	            pstmt.setString(14, data.getImg2());
	            pstmt.setString(15, data.getImg3());
	            pstmt.setString(16, data.getImg4());
	            pstmt.setString(17, data.getSerailID());
	            pstmt.setString(18, data.getLoc());
	            pstmt.setString(19, data.getMsg());
	            rs = pstmt.execute();
        	}
            
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


	public List<String> getPoint(int street,int pianqu,String time) {
		List<String> ret = new ArrayList<String>();
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select loc,assessidtop from t_assess where streetid=? and pianquid=? and date_format(intime,'%Y-%m-%d') = ?";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setInt(1, street);
            pstmt.setInt(2, pianqu);
            pstmt.setString(3, time);
            rs = pstmt.executeQuery();
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	String loc = rs.getString("loc");
            	int level = rs.getInt("assessidtop");
            	if(loc==null){
            		continue;
            	}
            	ret.add(rs.getString("loc")+","+(level%9));
            }
        }catch(Exception e){
       	 	doCatchException("getBlockList" ,e);
	       	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//璁剧疆鏃ユ湡鏍煎紡
	       	return ret;
        } finally {
            closeConnection(conn, pstmt, null);
        }
		return ret;
	}
	
	@Override
	public List<CheckDayItem> getCheckDayList(Date dStart, Date dEnd,
			String pianquid, String yenei, int page) {
		List<CheckDayItem> list = new ArrayList<>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		String sql = " SELECT hutongid,date_format(intime, '%Y%m%d') as timedup FROM t_assess "
				+ " where yeneiid = "+yenei+" and  pianquid = " + pianquid.substring(1)
				+ " and TIMESTAMPDIFF(SECOND, intime,'" + df.format(dStart) + "')<0 and TIMESTAMPDIFF(SECOND, intime,'" + df.format(dEnd) + "')>0 "
				+ " GROUP BY timedup,hutongid  ";
		int curindex = (page-1)*20;
        sql += " limit "+ curindex +", 20";
        
		try {
			conn = dataSource.getConnection();
	        pstmt = prepareStatement(conn, sql);
	        rs = pstmt.executeQuery();
	        
	        while(rs.next()) {
	        	CheckDayItem bean = new CheckDayItem();
	        	bean.setDay(rs.getString("timedup"));
	        	bean.setHutong(rs.getInt("hutongid"));
	        	bean.setHutongName(areaLogic.getAreaName(rs.getInt("hutongid"), 4));
	        	list.add(bean);
	        }
		}catch(Exception e) {
        	e.printStackTrace();
        }finally {
            closeConnection(conn, pstmt, rs);
        }
		
		return list;
	}

	@Override
	public List<AssessDataBean> getDetailitem(Date dStart, String hutongid, String checkyenei, int page) {
		// TODO Auto-generated method stub
		List<AssessDataBean> list = new ArrayList<AssessDataBean>();
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            String sql = "select t.aseid,t.id,t.score,t.areaid,t.streetid,t.pianquid,t.hutongid,t.userid,t.wuyeid,"
            		+ "t.assessidtop,t.assessid,t.intime,t.img1,t.img2,t.img3, t.img4, t.yeneiid,t.msg ,u.userName, ct.title_name, cb.sub_name "
            		+ " from t_assess t LEFT JOIN t_user u ON u.id = t.userid LEFT JOIN t_checktitle ct ON  ct.score_id = t.assessidtop"
            		+ " LEFT JOIN t_checksub cb ON cb.subid = t.assessid  where t.del = 0 ";
            
            sql += " and date_format(intime, '%Y%m%d') = " + df.format(dStart);
            
            if(!checkyenei.equals("0")) {
            	sql += " and yeneiid=" + checkyenei + " ";
            }
            
            sql += " and hutongid=" + hutongid.substring(1) + " ";
            
            
            int curindex = (page-1)*20;
            sql += " limit "+ curindex +", 20";
            
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
            	AssessDataBean ad = new AssessDataBean();
            	ad.setScore(rs.getInt("score"));
            	ad.setYeneiid(rs.getInt("yeneiid"));
            	
            	ad.setImg1(StringUtil.getImg(rs.getString("img1")));
            	ad.setImg2(StringUtil.getImg(rs.getString("img2")));
            	ad.setImg3(StringUtil.getImg(rs.getString("img3")));
            	ad.setImg4(StringUtil.getImg(rs.getString("img4")));
            	
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
            String sql = "SELECT wuyeid,SUM(subjectscore) from tb_weekassesspianqu ";
            
            sql += "  where TIMESTAMPDIFF(SECOND, intime,'" + df.format(new Date(time)) + "')<0 and TIMESTAMPDIFF(SECOND, intime,'" + df.format(new Date()) + "')>0 "
//            		+ " and yeneiid = " + yenei 
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
            String sql = "replace into tb_weekwuye(wuyeid, timedup, score, paiming) value(?,?,?,?)";
            
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            pstmt = prepareStatement(conn, sql);
            
            int length = list.size();
            for(int i = 0; i< length;i++) {
	            pstmt.setInt(1, list.get(i).getDisId());
	            pstmt.setInt(2, Integer.parseInt(df2.format(list.get(i).getTime())));
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
	public int weekSumPianqu(int yenei) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        long time = new Date().getTime() - 7*24*3600*1000;
        
        List<SortBean> list = new ArrayList<SortBean>();
        
        try {
            String sql = "SELECT pianquid,SUM(score) as score FROM t_assess ";
            
            sql += " where TIMESTAMPDIFF(SECOND, intime,'" + df.format(new Date(time)) + "')<0 and TIMESTAMPDIFF(SECOND, intime,'" + df.format(new Date()) + "')>0 "
            		+ " and yeneiid = " + yenei 
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
            String sql = "replace into tb_weekpianqu(pianquid, atime, score, paiming, yenei) value(?,?,?,?,?)";
            
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            pstmt = prepareStatement(conn, sql);
            
            int length = list.size();
            for(int i = 0; i< length;i++) {
	            pstmt.setInt(1, list.get(i).getDisId());
	            pstmt.setString(2, df2.format(list.get(i).getTime()));
	            pstmt.setInt(4, list.get(i).getPaiming());
	            pstmt.setFloat(3, list.get(i).getScore());
	            pstmt.setFloat(5, yenei);
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
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        int datebegin = Integer.parseInt(df.format(m));
        int dateend   = Integer.parseInt(df.format(new Date()));
        Date now = new Date();
        
        List<SortBean> list = new ArrayList<SortBean>();
        
        try {
            String sql = "SELECT wuyeid,SUM(subjectscore) from tb_weekassesspianqu ";
            
            sql += "  where TIMESTAMPDIFF(SECOND, intime,'" + df.format(m) + "')<0 and TIMESTAMPDIFF(SECOND, intime,'" + df.format(new Date()) + "')>0 "
//            		+ " and yeneiid = " + yenei 
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
            	bean.setTime(m);
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
            String sql = "replace into tb_monthwuye(wuyeid, timedup, score, paiming) value(?,?,?,?)";
            
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            pstmt = prepareStatement(conn, sql);
            
            int length = list.size();
            for(int i = 0; i< length;i++) {
	            pstmt.setInt(1, list.get(i).getDisId());
	            pstmt.setInt(2, Integer.parseInt(df2.format(list.get(i).getTime())));
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

	// 鏈堣绠楃墖鍖烘帓鍚嶅拰骞冲潎鍒嗭紝鍚屾椂鍒ゆ柇鏄惁鏄搴﹁繕鏄勾浠斤紝杩涜鐩稿叧鐨勮绠楀拰瀵煎叆琛�
	@Override
	public int monthSumPianqu(int yenei) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        int datebegin = Integer.parseInt(df.format(m));
        int dateend   = Integer.parseInt(df.format(new Date()));
        Date now = new Date();
        
        List<JisuanSortBean> list = new ArrayList<JisuanSortBean>();
        
        try {
            String sql = "select stateid,streetid,pianquid,AVG(waiscore) as waiscore,AVG(neiscore) as neiscore,AVG(allscore) as allscore from tb_weekpianqu ";
            
            sql += " where timedup > "+ datebegin +" and timedup < " + dateend
            		+ " GROUP BY pianquid ORDER BY allscore desc";
            
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            
            int paiming = 1;
            
            double score = 0;
            
            int index = 0;
            while(rs.next()){
            	index++;
            
            	JisuanSortBean bean = new JisuanSortBean();
            	bean.setPianquid(rs.getInt("pianquid"));
            	bean.setStateid(rs.getInt("stateid"));
            	bean.setStreetid(rs.getInt("streetid"));
            	bean.addYewai(rs.getDouble("waiscore"));
            	bean.addYenei(rs.getDouble("neiscore"));
            	bean.setAllscore(rs.getDouble("allscore"));
            	float ss = rs.getFloat("allscore");
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
        	e.printStackTrace();
        }finally {
            closeConnection(conn, pstmt, rs);
        }
        
        if(list.size() ==0 ) {return 0; } 
        SimpleDateFormat df2 = new SimpleDateFormat("yyyyMM");
        try {
            String sql = "replace into tb_monthpianqu(stateid,streetid,pianquid, timedup, waiscore,neiscore,allscore, paiming) value(?,?,?,?,?,?,?,?)";
            
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            pstmt = prepareStatement(conn, sql);
            
            int length = list.size();
            for(int i = 0; i< length;i++) {
	            pstmt.setInt(1, list.get(i).getStateid());
	            pstmt.setInt(2, list.get(i).getStreetid());
	            pstmt.setInt(3, list.get(i).getPianquid());
	            pstmt.setInt(4, Integer.parseInt(df2.format(m)));
	            pstmt.setDouble(5, list.get(i).getYewai());
	            pstmt.setDouble(6, list.get(i).getYenei());
	            pstmt.setDouble(7, list.get(i).getAllscore());
	            pstmt.setInt(8, list.get(i).getPaiming());
	            pstmt.execute();
            }
            conn.commit();
            
        } catch(Exception e) {
        	e.printStackTrace();
        }finally {
            closeConnection(conn, pstmt, rs);
        }
        
        // 濡傛灉鏄搴︼紝鏈堜唤涓�1鏈堬紝4鏈堬紝7鏈堬紝10鏈�
        if(now.getMonth() == 0 || now.getMonth() == 3 || now.getMonth() == 6 || now.getMonth() == 9) {
        	seasonPianqu(now);
        }
        
        // 濡傛灉鏄勾搴�, 鏈堜唤涓轰竴鏈堜唤
        if(now.getMonth() == 0) {
        	yearPianqu(now);
        }
        
		return 0;
	}
	
	private void seasonPianqu(Date date) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		
		
		Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -3);  //寰�鍓嶈拷婧�3涓湀
        Date datebegin = c.getTime();
        Date dateend = new Date();
        
        List<JisuanSortBean> list = new ArrayList<JisuanSortBean>();
        
        try {
            String sql = "select stateid,streetid,pianquid,AVG(waiscore) as waiscore,AVG(neiscore) as neiscore,AVG(allscore) as allscore from tb_weekpianqu ";
            
            sql += " where timedup > "+ Integer.parseInt(df.format(datebegin)) +" and timedup < " + Integer.parseInt(df.format(dateend))
            		+ " GROUP BY pianquid ORDER BY -allscore";
            
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            
            int paiming = 1;
            
            double score = 0;
            
            int index = 0;
            while(rs.next()){
            	index++;
            
            	JisuanSortBean bean = new JisuanSortBean();
            	bean.setPianquid(rs.getInt("pianquid"));
            	bean.setStateid(rs.getInt("stateid"));
            	bean.setStreetid(rs.getInt("streetid"));
            	bean.addYewai(rs.getDouble("waiscore"));
            	bean.addYenei(rs.getDouble("neiscore"));
            	bean.setAllscore(rs.getDouble("allscore"));
            	float ss = rs.getFloat("allscore");
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
        	e.printStackTrace();
        }finally {
            closeConnection(conn, pstmt, rs);
        }
        
        if(list.size() ==0 ) { return ; } 
        SimpleDateFormat df2 = new SimpleDateFormat("yyyyMM");
        try {
            String sql = "replace into tb_seasonpianqu(stateid,streetid,pianquid, timedup, waiscore,neiscore,allscore, paiming) value(?,?,?,?,?,?,?,?)";
            
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            pstmt = prepareStatement(conn, sql);
            
            int length = list.size();
            for(int i = 0; i< length;i++) {
	            pstmt.setInt(1, list.get(i).getStateid());
	            pstmt.setInt(2, list.get(i).getStreetid());
	            pstmt.setInt(3, list.get(i).getPianquid());
	            pstmt.setInt(4, Integer.parseInt(df2.format(datebegin)));
	            pstmt.setDouble(5, list.get(i).getYewai());
	            pstmt.setDouble(6, list.get(i).getYenei());
	            pstmt.setDouble(7, list.get(i).getAllscore());
	            pstmt.setInt(8, list.get(i).getPaiming());
	            pstmt.execute();
            }
            conn.commit();
            
        } catch(Exception e) {
        	e.printStackTrace();
        }finally {
            closeConnection(conn, pstmt, rs);
        }
	}
	
	private void yearPianqu(Date date) {
		
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        
		Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -12);  //寰�鍓嶈拷婧�12涓湀
        Date datebegin = c.getTime();
        Date dateend = new Date();
        
        
        List<JisuanSortBean> list = new ArrayList<JisuanSortBean>();
        
        try {
            String sql = "select stateid,streetid,pianquid,AVG(waiscore) as waiscore,AVG(neiscore) as neiscore,AVG(allscore) as allscore from tb_weekpianqu ";
            
            sql += " where timedup > "+ Integer.parseInt(df.format(datebegin)) +" and timedup < " + Integer.parseInt(df.format(dateend))
            		+ " GROUP BY pianquid ORDER BY -allscore";
            
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();
            
            int paiming = 1;
            
            double score = 0;
            
            int index = 0;
            while(rs.next()){
            	index++;
            
            	JisuanSortBean bean = new JisuanSortBean();
            	bean.setPianquid(rs.getInt("pianquid"));
            	bean.setStateid(rs.getInt("stateid"));
            	bean.setStreetid(rs.getInt("streetid"));
            	bean.addYewai(rs.getDouble("waiscore"));
            	bean.addYenei(rs.getDouble("neiscore"));
            	bean.setAllscore(rs.getDouble("allscore"));
            	float ss = rs.getFloat("allscore");
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
        	e.printStackTrace();
        }finally {
            closeConnection(conn, pstmt, rs);
        }
        
        if(list.size() ==0 ) { return ; } 
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy");
        try {
            String sql = "replace into tb_yearpianqu(stateid,streetid,pianquid, timedup, waiscore,neiscore,allscore, paiming) value(?,?,?,?,?,?,?,?)";
            
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            pstmt = prepareStatement(conn, sql);
            
            int length = list.size();
            for(int i = 0; i< length;i++) {
	            pstmt.setInt(1, list.get(i).getStateid());
	            pstmt.setInt(2, list.get(i).getStreetid());
	            pstmt.setInt(3, list.get(i).getPianquid());
	            pstmt.setInt(4, Integer.parseInt(df2.format(datebegin)));
	            pstmt.setDouble(5, list.get(i).getYewai());
	            pstmt.setDouble(6, list.get(i).getYenei());
	            pstmt.setDouble(7, list.get(i).getAllscore());
	            pstmt.setInt(8, list.get(i).getPaiming());
	            pstmt.execute();
            }
            conn.commit();
            
        } catch(Exception e) {
        	e.printStackTrace();
        }finally {
            closeConnection(conn, pstmt, rs);
        }
        
	}
	
	@Override
	public List<PianquSortListBean> getPianquSortList(char type, String date, String pianquid) {
		List<PianquSortListBean> list = new ArrayList<>();
		
		String strwhere = " pianquid=" + pianquid.substring(1) + " ";
		String strtable = "";
		
		switch(type) {
		case 'w':
			strtable = " tb_weekpianqu ";
			strwhere += "and timedup<=" + date + " ";
			break;
		case 'm':
			strtable = " tb_monthpianqu ";
			strwhere += "and timedup<=" + date + " ";
			break;
		case 's':
			strtable = " tb_seasonpianqu ";
			strwhere += "and timedup<=" + date + " ";
			break;
		case 'y':
			strtable = " tb_yearpianqu ";
			strwhere += "and timedup<=" + date + " ";
			break;
		default:
			return list;
		}
		
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		String sql = " select stateid,streetid,pianquid,waiscore,neiscore,allscore,paiming,timedup from" + strtable + "where" + strwhere + " limit 4 ";
		try {
			conn = dataSource.getConnection();
	        pstmt = prepareStatement(conn, sql);
	        rs = pstmt.executeQuery();
	        
	        while(rs.next()){
	        	PianquSortListBean bean = new PianquSortListBean();
	        	bean.setDate(rs.getString("timedup"));
	        	bean.setPaiming(rs.getInt("paiming"));
	        	bean.setScore(rs.getDouble("allscore"));
	        	bean.setPianquid(rs.getInt("pianquid"));
	        	bean.setWaiscore(100-rs.getDouble("waiscore"));
	        	bean.setNeiscore(100-rs.getDouble("neiscore"));
	        	list.add(bean);
	        }
		}catch(Exception e) {
        	e.printStackTrace();
        }finally {
            closeConnection(conn, pstmt, rs);
        }
		
		return list;
	}

	// 鐗囧尯缁熻鏌ヨ
	@Override
	public List<PianquData> getPianquSortData(char type, String date, int jibie, String areaid) {
		List<PianquData> list = new ArrayList<>();
		
		String strwhere = "";
		String strtable = "";
		switch(areaid.charAt(0)) {
		case 'z': // all
			strwhere = " 1=1 ";
			break;
		case 's': // 澶у尯 
			strwhere = " stateid=" + areaid.substring(1) + " ";
			break;
		case 't': // 琛楅亾
			strwhere = " streetid=" + areaid.substring(1) + " ";
			break;
		case 'p': // 鐗囧尯
			strwhere = " pianquid=" + areaid.substring(1) + " ";
			break;
		default:
			return list;
		}
		
		switch(type) {
		case 'w':
			strtable = " tb_weekpianqu ";
			strwhere += "and timedup=" + date + " ";
			break;
		case 'm':
			strtable = " tb_monthpianqu ";
			strwhere += "and timedup=" + date + " ";
			break;
		case 's':
			strtable = " tb_seasonpianqu ";
			strwhere += "and timedup=" + date + " ";
			break;
		case 'y':
			strtable = " tb_yearpianqu ";
			strwhere += "and timedup=" + date + " ";
			break;
		default:
			return list;
		}
		
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		String sql = " select stateid,streetid,pianquid,waiscore,neiscore,allscore,paiming from" + strtable + "where" + strwhere;
		try {
			conn = dataSource.getConnection();
	        pstmt = prepareStatement(conn, sql);
	        rs = pstmt.executeQuery();
	        
	        while(rs.next()){
	        	PianquData bean = new PianquData();
	        	bean.setAreaid(rs.getInt("stateid"));
	        	bean.setAreaName(areaLogic.getAreaName(rs.getInt("stateid"), 1));
	        	bean.setStreetid(rs.getInt("streetid"));
	        	bean.setStreetName(areaLogic.getAreaName(rs.getInt("streetid"), 2));
	        	bean.setPaiming(rs.getInt("paiming"));
	        	bean.setScore(rs.getDouble("allscore"));
	        	bean.setPianquid(rs.getInt("pianquid"));
	        	bean.setPianquName(areaLogic.getAreaName(rs.getInt("pianquid"), 3));
	        	bean.setWaiscore(100-rs.getDouble("waiscore"));
	        	bean.setNeiscore(100-rs.getDouble("neiscore"));
	        	list.add(bean);
	        }
		}catch(Exception e) {
        	e.printStackTrace();
        }finally {
            closeConnection(conn, pstmt, rs);
        }
		
		return list;
	}

	public List<WuyeSortListBean> getWuyeSortList(char type, String date, String wuyeid) {
		List<WuyeSortListBean> list = new ArrayList<>();
		
		String strwhere = " wuyeid=" + wuyeid.substring(2) + " ";
		String strtable = "";
		
		switch(type) {
		case 'w':
			strtable = " tb_weekwuye ";
			strwhere += "and timedup<=" + date + " ";
			break;
		case 'm':
			strtable = " tb_monthwuye ";
			strwhere += "and timedup<=" + date + " ";
			break;
		default:
			return list;
		}
		
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		String sql = " select wuyeid,score,paiming,timedup from" + strtable + "where" + strwhere + " limit 4 ";
		try {
			conn = dataSource.getConnection();
	        pstmt = prepareStatement(conn, sql);
	        rs = pstmt.executeQuery();
	        
	        while(rs.next()){
	        	WuyeSortListBean bean = new WuyeSortListBean();
	        	bean.setPaiming(rs.getInt("paiming"));
	        	bean.setScore(rs.getDouble("score"));
	        	bean.setWuyeid(rs.getInt("wuyeid"));
	        	bean.setTime(rs.getInt("timedup")+ "");
	        	list.add(bean);
	        }
		}catch(Exception e) {
        	e.printStackTrace();
        }finally {
            closeConnection(conn, pstmt, rs);
        }
		
		return list;
	}
	
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int weekjisuanpianqu() {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dfday = new SimpleDateFormat("yyyy-MM-dd");
        
        long end;
		try {
			end = dfday.parse(dfday.format(new Date())).getTime();
		} catch (ParseException e1) {
			return -1;
		}
        
        long begin = end - 7*24*3600*1000;
        
//        long time = new Date().getTime() - 7*24*3600*1000;
        
        List<JisuanBean> list = new ArrayList<JisuanBean>();
        
        List<JisuanSortBean> sortlist = new ArrayList<>();
        
        try {
            String sql = "SELECT userid,streetid,areaid,pianquid,yeneiid,assessidtop,assessid,hutongid," +
					"wuyeid,SUM(score)  as score,getMaxScore(assessid) as sss  FROM t_assess ";
            
            sql += " where TIMESTAMPDIFF(SECOND, intime,'" + df.format(new Date(begin)) + "')<0 " +
					"and TIMESTAMPDIFF(SECOND, intime,'" + df.format(new Date(end)) + "')>0 "
            		+ " and del=0 "
            		+ " GROUP BY pianquid,assessid ";
            
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            rs = pstmt.executeQuery();

            int mpianquid = 0;
            JisuanSortBean sortbean = null;
            while(rs.next()){
            	
            	int pianqu = rs.getInt("pianquid");
            	JisuanBean bean = new JisuanBean();
            	bean.setAreaid(rs.getInt("areaid"));
            	bean.setStreetid(rs.getInt("streetid"));
            	bean.setHutongid(rs.getInt("hutongid"));
            	bean.setPianquid(pianqu);
            	bean.setYeneiid(rs.getInt("yeneiid"));
            	bean.setAssessidtop(rs.getInt("assessidtop"));
            	bean.setAssessid(rs.getInt("assessid"));
            	bean.setWuyeid(rs.getInt("wuyeid"));
            	bean.setTime(new Date(end - 12*3600*1000));
            	bean.setUserid(rs.getInt("userid"));
            	double score  = rs.getDouble("score");
            	double sss  = rs.getDouble("sss");
            	score = (score > sss)? sss: score;
            	bean.setScore((double)score);
            	bean.setBaifenbi((double)score*100.0/sss);
            	list.add(bean);
            	
            	if(pianqu != mpianquid) {
            		if(sortbean != null) {
            			sortbean.setall();
            			sortlist.add(sortbean);
            		}
            		
            		mpianquid = pianqu;
            		sortbean = new JisuanSortBean();
            		sortbean.setPianquid(pianqu);
            		sortbean.setStateid(rs.getInt("areaid"));
            		sortbean.setStreetid(rs.getInt("streetid"));
            		sortbean.setHutongid(rs.getInt("hutongid"));
            		sortbean.setWuyeid(rs.getInt("wuyeid"));
            		sortbean.setUserid(rs.getInt("userid"));
            	}
            	
            	switch(rs.getInt("yeneiid")) {
    			case 2:
    				sortbean.addYewai(score);
    				break;
    			case 1:
    				sortbean.addYenei(score);
    				break;
        		}
            }
            
            if(sortbean !=null) {
            	sortbean.setall();
            	sortlist.add(sortbean);
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }finally {
            closeConnection(conn, pstmt, rs);
        }
        
        
        
        if(list.size() ==0 ) {return 0; } 
        SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMdd");
        try {
            String sql = "replace into tb_weekassesspianqu(intime, timedup,areaid, streetid, yeneiid, pianquid, assessid, "
            		+ " assessidtop, subjectscore, weekbaifenbi, wuyeid, hutongid) value(?,?,?,?,?,?,?,?,?,?,?,?)";
            
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            pstmt = prepareStatement(conn, sql);
            
            int length = list.size();
            for(int i = 0; i< length;i++) {
	            pstmt.setString(1, df2.format(list.get(i).getTime()));
	            pstmt.setString(2, df2.format(list.get(i).getTime()));
	            pstmt.setInt(3, list.get(i).getAreaid());
	            pstmt.setInt(4, list.get(i).getStreetid());
	            pstmt.setInt(5, list.get(i).getYeneiid());
	            pstmt.setInt(6, list.get(i).getPianquid());
	            pstmt.setInt(7, list.get(i).getAssessid());
	            pstmt.setInt(8, list.get(i).getAssessidtop());
	            pstmt.setDouble(9, list.get(i).getScore());
	            pstmt.setDouble(10, list.get(i).getBaifenbi());
	            pstmt.setInt(11, list.get(i).getWuyeid());
	            pstmt.setInt(12, list.get(i).getHutongid());
	            pstmt.execute();
            }
            conn.commit();
            
        } catch(Exception e) {
        	e.printStackTrace();
        }finally {
            closeConnection(conn, pstmt, rs);
        }
        
        if(sortlist.size() ==0 ) {return 0; } 
        Collections.sort(sortlist);
        try {
            String sql = "replace into tb_weekpianqu(timedup, pianquid, waiscore, neiscore, allscore, paiming, stateid, streetid, wuyeid,hutongid,userid) "
            		+ "  value(?,?,?,?,?,?, ?,?,?,?,?)";
            
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            pstmt = prepareStatement(conn, sql);
            
            int paiming = 0;
            int score = 0;
            int index = 1;
            
            int length = sortlist.size();
            for(int i = 0; i< length;i++) {
            	
            	double temp = sortlist.get(i).getAllscore();
            	
            	if((int)(temp*10) != score ) {
            		paiming = index;
            	}
            	
	            pstmt.setString(1, df2.format(new Date(end - 12*3600*1000)));
	            pstmt.setInt(2, sortlist.get(i).getPianquid());
	            pstmt.setDouble(3, sortlist.get(i).getYewai());
	            pstmt.setDouble(4, sortlist.get(i).getYenei());
	            pstmt.setDouble(5, sortlist.get(i).getAllscore());
	            pstmt.setInt(6, paiming);
	            pstmt.setInt(7, sortlist.get(i).getStateid());
	            pstmt.setInt(8, sortlist.get(i).getStreetid());
	            pstmt.setInt(9, sortlist.get(i).getWuyeid());
	            pstmt.setInt(10, sortlist.get(i).getHutongid());
	            pstmt.setInt(11, sortlist.get(i).getUserid());
	            pstmt.execute();
	            index++;
            }
            conn.commit();
            
        } catch(Exception e) {
        	e.printStackTrace();
        }finally {
            closeConnection(conn, pstmt, rs);
        }
        
		return 0;
	}

	
	@Override
	public BadCheckList getBadCheck(char type, String date) {
		
		BadCheckList ret = new BadCheckList();
		String strwhere = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat dfm = new SimpleDateFormat("yyyyMM");
		Date recvDate;
		Calendar c = Calendar.getInstance();
		
		switch(type) {
		case 'w': // week
			try {
				recvDate = df.parse(date);
			} catch (ParseException e) {
				return ret;
			}
			
			Date begin = new Date(recvDate.getTime() - 24*3600*1000);
			Date end = new Date(recvDate.getTime() + 6*24*3600*1000);
			
			int ibegin = Integer.parseInt(df.format(begin));
			int iend = Integer.parseInt(df.format(end));
			
			strwhere += " timedup>" + ibegin + " and timedup<=" + iend + " ";
			break;
		case 'm': // month
			try {
				recvDate = dfm.parse(date);
			} catch (ParseException e) {
				return ret;
			}
			
	        c.setTime(recvDate);
	        c.add(Calendar.MONTH, 1);
	        Date m = c.getTime();
	        int datebegin = Integer.parseInt(df.format(recvDate));
	        int dateend   = Integer.parseInt(df.format(m));
			
	        strwhere += " timedup>=" + datebegin + " and timedup<" + dateend + " ";
			break;
		case 's': // season
			try {
				recvDate = dfm.parse(date);
			} catch (ParseException e) {
				return ret;
			}
			
	        c.setTime(recvDate);
	        c.add(Calendar.MONTH, 3);
	        Date m2 = c.getTime();
	        int datebegin2 = Integer.parseInt(df.format(recvDate));
	        int dateend2   = Integer.parseInt(df.format(m2));
	        
	        strwhere += " timedup>=" + datebegin2 + " and timedup<" + dateend2 + " ";
			break;
		case 'y': // year
			int year = Integer.parseInt(date);
			
			strwhere += " timedup>=" + year*100 + " and timedup<" + (year+1)*100 + " ";
			break;
		default:
			return ret;
		}
		
		String sql = "SELECT assessid,baifenbi,b.sub_name FROM (SELECT  assessid, AVG(weekbaifenbi) as baifenbi FROM tb_weekassesspianqu where "
				+ strwhere +"  GROUP BY assessid ) "
				+ "as ss LEFT JOIN t_checksub b ON ss.assessid = b.subid  WHERE ss.baifenbi>20 ORDER BY ss.baifenbi desc";
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
	        pstmt = prepareStatement(conn, sql);
	        rs = pstmt.executeQuery();
	        int index= 1;
	        while(rs.next()){
	        	BadCheckList.basic bean = new BadCheckList.basic();
	        	bean.setBaifenbi(rs.getDouble("baifenbi"));
	        	bean.setChecksub(rs.getInt("assessid"));
	        	bean.setChecksunName(rs.getString("sub_name"));
	        	bean.setIndex(index);
	        	ret.basiclist.add(bean);
	        	index++;
	        }
		}catch(Exception e) {
        	e.printStackTrace();
        }finally {
            closeConnection(conn, pstmt, rs);
        }
		
		// 鏌ヨ鍏蜂綋鍐呭
		int count = ret.basiclist.size();
		if(count<=0) {return ret;}
		
		
        
		for(int i=0;i<count;i++) {
			BadCheckList.basic bean = ret.basiclist.get(i);
			String sql2 = "SELECT streetid,pianquid,baifenbi FROM (SELECT streetid,pianquid,AVG(weekbaifenbi) as baifenbi FROM tb_weekassesspianqu WHERE assessid = "
					+ bean.getChecksub() +" and "
					+ strwhere 
					+ " GROUP BY pianquid ) "
					+ " as ss  WHERE ss.baifenbi>20 ORDER BY ss.baifenbi desc";
			
			try {
				
				conn = dataSource.getConnection();
		        pstmt = prepareStatement(conn, sql2);
		        rs = pstmt.executeQuery();
		        int index= 1;
		        while(rs.next()){
		        	BadCheckList.pianqu bean2 = new BadCheckList.pianqu();
		        	bean2.setIndex(index);
		        	bean2.setPianquid(rs.getInt("pianquid"));
		        	bean2.setPianquName(areaLogic.getAreaName(rs.getInt("pianquid"), 3));
		        	bean2.setStreetid(rs.getInt("streetid"));
		        	bean2.setStreetName(areaLogic.getAreaName(rs.getInt("streetid"), 2));
		        	bean2.setBaifenbi(rs.getDouble("baifenbi"));
		        	bean.pianqulist.add(bean2);
		        	index++;
		        }
			}catch(Exception e) {
	        	e.printStackTrace();
	        }finally {
	            closeConnection(conn, pstmt, rs);
	        }
		}
		
		return ret;
	}

	@Override
	public List<PianquData> getPianquWeekData(int data) {
		List<PianquData> list=new ArrayList<PianquData>();
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		String sql = "  select stateid,streetid, pianquid,hutongid, waiscore,neiscore,allscore,getWuyeName(wuyeid) as wuye, getLevelName(b.level_id) as level, c.userName as name from tb_weekpianqu a  LEFT JOIN t_pianqu b ON a.pianquid = b.id left join t_user c on a.userid=c.id where a.timedup = " + data;
		try {
			conn = dataSource.getConnection();
	        pstmt = prepareStatement(conn, sql);
	        rs = pstmt.executeQuery();
	        
	        while(rs.next()){
	        	PianquData bean = new PianquData();
	        	bean.setAreaid(rs.getInt("stateid"));
	        	bean.setAreaName(areaLogic.getAreaName(rs.getInt("stateid"), 1));
	        	bean.setStreetid(rs.getInt("streetid"));
	        	bean.setStreetName(areaLogic.getAreaName(rs.getInt("streetid"), 2));
	        	bean.setScore(rs.getDouble("allscore"));
	        	bean.setPianquid(rs.getInt("pianquid"));
	        	bean.setPianquName(areaLogic.getAreaName(rs.getInt("pianquid"), 3));
	        	bean.setHutongid(rs.getInt("hutongid"));
	        	bean.setHutongName(areaLogic.getAreaName(rs.getInt("hutongid"), 4));
	        	bean.setWuyeName(rs.getString("wuye"));
	        	bean.setLevelName(rs.getString("level"));
	        	bean.setNeiscore(rs.getDouble("neiscore"));
	        	bean.setWaiscore(rs.getDouble("waiscore"));
	        	bean.setUsername(rs.getString("name"));
	        	list.add(bean);
	        }
		}catch(Exception e) {
        	e.printStackTrace();
        }finally {
            closeConnection(conn, pstmt, rs);
        }
		
		return list;
	}

	@Override
	public List<CheckTitle> getCheckTitle(int type) {
		List<CheckTitle> list=new ArrayList<CheckTitle>();
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		String sql = " SELECT a.subid, a.titleid, a.sub_name,a.score, b.title_name FROM t_checksub a LEFT JOIN t_checktitle b ON a.titleid = b.score_id WHERE b.type = "+type+" AND a.del = 0 ORDER BY titleid";
		try {
			conn = dataSource.getConnection();
	        pstmt = prepareStatement(conn, sql);
	        rs = pstmt.executeQuery();
	        int k = 0;
	        CheckTitle item = null;
	        while(rs.next()){
	        	int id = rs.getInt("titleid");
	        	if(id != k) {
	        		if(item != null) {
	        			list.add(item);
	        		}
	        		item = new CheckTitle(id, rs.getString("title_name"));
	        		k = id;
	        	}
	        	item.addSub(rs.getInt("subid"), rs.getString("sub_name"), rs.getDouble("score"));
	        }
	        if(item!=null) list.add(item);
		}catch(Exception e) {
        	e.printStackTrace();
        }finally {
            closeConnection(conn, pstmt, rs);
        }
		
		return list;
	}

	@Override
	public List<PianquRelationHead> getRelationPainqu() {
		List<PianquRelationHead> list=new ArrayList<PianquRelationHead>();
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		String sql = "SELECT a.id, a.pianqu_name, a.father_id, b.street_name FROM t_pianqu a LEFT JOIN t_street b ON a.father_id = b.id WHERE a.del = 0 ORDER BY father_id";
		try {
			conn = dataSource.getConnection();
	        pstmt = prepareStatement(conn, sql);
	        rs = pstmt.executeQuery();
	        int k = 0;
	        PianquRelationHead item = null;
	        while(rs.next()){
	        	int id = rs.getInt("father_id");
	        	if(id != k) {
	        		if(item != null) {
	        			list.add(item);
	        		}
	        		item = new PianquRelationHead(id, rs.getString("street_name"));
	        		k = id;
	        	}
	        	item.addSub(rs.getInt("id"), rs.getString("pianqu_name"));
	        }
	        if(item!=null) list.add(item);
		}catch(Exception e) {
        	e.printStackTrace();
        }finally {
            closeConnection(conn, pstmt, rs);
        }
		
		return list;
	}

	@Override
	public Map<String, Float> getScore(int date, int yenei) {
		Map<String, Float> list=new HashMap<>();
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		String sql = "SELECT pianquid,assessid,subjectscore FROM tb_weekassesspianqu WHERE timedup = " + date + " and yeneiid = "+ yenei+";";
		try {
			conn = dataSource.getConnection();
	        pstmt = prepareStatement(conn, sql);
	        rs = pstmt.executeQuery();
	        while(rs.next()){
	        	list.put(rs.getInt("pianquid") + "|" + rs.getInt("assessid"), rs.getFloat("subjectscore"));
	        }
	        
		}catch(Exception e) {
        	e.printStackTrace();
        }finally {
            closeConnection(conn, pstmt, rs);
        }
		
		return list;
	}

	@Override
	public int delAssess(String serialid) {
		Connection conn = null;
        PreparedStatement pstmt = null;
//        ResultSet rs = null;
		String sql = "delete from t_assess where aseid = " + serialid;
		try {
			conn = dataSource.getConnection();
	        pstmt = prepareStatement(conn, sql);
	        boolean ret = pstmt.execute();
	        return 0;
	        
		}catch(Exception e) {
        	e.printStackTrace();
        }finally {
            closeConnection(conn, pstmt, null);
        }
		return 0;
	}
	
	public static String getName(String ss) {
		if(ss==null) {
			return "";
		}
		String[] sp = ss.split("/");
		if(sp.length == 5) {
			return sp[4];
		}
		return ss;
	}

	@Override
	public List<PicBean> getPicList(int pianqu, int date) {
		List<PicBean> ret = new ArrayList<>(30);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date timeend = new Date();
		try {
			timeend = df.parse(String.valueOf(date));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        
	    long time = timeend.getTime() - 7*24*3600*1000;

		
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		String sql = "SELECT hutongid,streetid,pianquid,assessid,msg,score,img1,img2,img3,img4 FROM t_assess WHERE pianquid = "+ pianqu +
				" and  TIMESTAMPDIFF(SECOND, intime,'" + df.format(new Date(time)) + "')<0 "
				+ "and TIMESTAMPDIFF(SECOND, intime,'" + df.format(timeend) + "')>0   ORDER BY assessidtop,assessid ";
		try {
			conn = dataSource.getConnection();
	        pstmt = prepareStatement(conn, sql);
	        rs = pstmt.executeQuery();
	        PicBean bean = null;
	        int assessidtemp = 0;
	        while(rs.next()){
	        	int hutongid = rs.getInt("hutongid");
	        	int streetid = rs.getInt("streetid");
	        	int pianquid = rs.getInt("pianquid");
	        	int assessid = rs.getInt("assessid");
	        	String msg = rs.getString("msg");
	        	double score = rs.getDouble("score");
	        	String img1 = rs.getString("img1");
	        	String img2 = rs.getString("img2");
	        	String img3 = rs.getString("img3");
	        	String img4 = rs.getString("img4");
	        	
	        	if(assessidtemp != assessid) {
	        		if(bean != null) {
	        			ret.add(bean);
	        		}
	        		bean = new PicBean();
	        		assessidtemp = assessid;
	        	}
	        	bean.setAssessid(assessid);
	        	bean.addScore(score);
	        	bean.addCount();
	        	bean.addMsg(msg);
	        	bean.setHutong(hutongid);
	        	bean.setHutongName(areaLogic.getAreaName(hutongid, 4));
	        	if(img1 != null && img1.length() >0) {
	        		bean.addList(getName(img1));
	        	}
	        	if(img2 != null && img2.length() >0) {
	        		bean.addList(getName(img2));
	        	}
	        	if(img3 != null && img3.length() >0) {
	        		bean.addList(getName(img3));
	        	}
	        	if(img4 != null && img4.length() >0) {
	        		bean.addList(getName(img4));
	        	}
	        }
	        if(bean != null) 
	        	ret.add(bean);
	        
		}catch(Exception e) {
        	e.printStackTrace();
        }finally {
            closeConnection(conn, pstmt, rs);
        }
		
		return ret;
	}

	@Override
	public Map<Integer, String> getAssessid() {
		Map<Integer, String> map = new HashMap<>();
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		String sql = "SELECT subid,sub_name from t_checksub WHERE del = 0 ";
		try {
			conn = dataSource.getConnection();
	        pstmt = prepareStatement(conn, sql);
	        rs = pstmt.executeQuery();
	        while(rs.next()){
	        	
	        	map.put(rs.getInt("subid"), rs.getString("sub_name"));
	        }
	        
		}catch(Exception e) {
        	e.printStackTrace();
        }finally {
            closeConnection(conn, pstmt, rs);
        }
		return map;
	}

}
