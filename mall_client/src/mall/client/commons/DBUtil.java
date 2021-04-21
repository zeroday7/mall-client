package mall.client.commons;

import java.sql.*;

public class DBUtil {
	// 1. DB연결
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/mall","root","java1004");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	// 2. DB자원(conn, stmt, rs) 해제
	public void close(ResultSet rs,PreparedStatement stmt,  Connection conn) {
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
	}
}
