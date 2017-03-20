package wuye.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import wuye.bean.AssessDataBean;
import wuye.dao.AssessDao;
import wuye.dao.DaoBasic;

public class AssessDaoImpl extends DaoBasic implements AssessDao {

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

	@Override
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

}
