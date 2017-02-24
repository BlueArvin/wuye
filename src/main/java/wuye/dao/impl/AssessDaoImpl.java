package wuye.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

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
            String sql = "insert into t_assess(intime, streetid, areaid, wuyeid, assessid,assessidtop, score, userid, img1, img2, img3, aseid) "
            		+ " value(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            conn = dataSource.getConnection();
            pstmt = prepareStatement(conn, sql);
            pstmt.setTimestamp(1, new Timestamp(data.getTime().getTime()));
            pstmt.setString(2, data.getStreetid());
            pstmt.setInt(3, data.getAreaid());
            pstmt.setInt(4, data.getWuyeid());
            pstmt.setString(5, data.getAssessid());
            pstmt.setInt(6, data.getAssessidtop());
            pstmt.setInt(7, data.getScore());
            pstmt.setInt(8, data.getUserid());
            pstmt.setString(9, data.getImg1());
            pstmt.setString(10, data.getImg2());
            pstmt.setString(11, data.getImg3());
            pstmt.setString(12, data.getId());
            rs = pstmt.execute();
            
            return 0;
        }catch(Exception e){
       	 	doCatchException("getBlockList" ,e);
            return -1;
        } finally {
            closeConnection(conn, pstmt, null);
        }
	}

}
