package wuye.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

public class DaoBasic {

	public static PreparedStatement prepareStatement(Connection con, String sql) throws SQLException {
		PreparedStatement st = con.prepareStatement(sql);
		st.setQueryTimeout(2300);
		return st;
	}
	
	public static PreparedStatement prepareStatement(Connection con, String sql, int statemethod) throws SQLException {
		PreparedStatement st = con.prepareStatement(sql, statemethod);
		st.setQueryTimeout(2300);
		return st;
	}
	
	public static CallableStatement prepareCStatement(Connection con, String sql) throws SQLException {
		CallableStatement st = con.prepareCall(sql);
		st.setQueryTimeout(2300);
		return st;
	}
	
	public static void doCatchException(String name, Exception e) {
		if (e instanceof SQLTimeoutException) {
			//IssueLogFactory.ALERT_LOG.info(name + e.getMessage());
		}
		e.printStackTrace();
	}
	
	public static void closeConnection(Connection con, Statement st, ResultSet rs) {
        
        if(rs != null){
            try {
               rs.close();
           } catch (SQLException e) {
           }
            
        }
         
         if(st != null){
             try {
               st.close();
           } catch (SQLException e) {
           }
         }
           
         if(con!=null){
             try {
               con.close();
           } catch (SQLException e) {
           }
         }
   }
}
