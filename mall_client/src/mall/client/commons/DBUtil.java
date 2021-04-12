package mall.client.commons;

import java.sql.*;

public class DBUtil {
	// 1. DB연결
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/mall","root","java1004");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	// 2. DB자원(conn, stmt, rs) 해제
	public void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
		try {
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}