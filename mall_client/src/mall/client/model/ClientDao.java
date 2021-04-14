package mall.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import mall.client.commons.DBUtil;
import mall.client.vo.Client;

public class ClientDao {
	private DBUtil dbUtil;
	
	// 회원정보
	public Client selectClientOne(String clientMail) {
		this.dbUtil = new DBUtil();
		Client client = new Client();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.dbUtil.getConnection();
			String sql = "SELECT client_no clientNo, client_mail clientMail, client_date clientDate FROM client WHERE client_mail = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, clientMail);
			System.out.println(stmt+"고객 정보 출력 메서드");//디버깅코드
			rs = stmt.executeQuery();
			if(rs.next()) {
				client.setClientNo(rs.getInt("clientNo"));
				client.setClientMail(rs.getString("clientMail"));
				client.setClientDate(rs.getString("clientDate"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbUtil.close(rs, stmt, conn);
		}
		return client;
	}
	
	// 회원가입
	public int insertClient(Client client) {
		this.dbUtil = new DBUtil();
		int rowCnt = 0;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = this.dbUtil.getConnection();
			String sql = "INSERT INTO client(client_mail, client_pw, client_date) VALUES (?,PASSWORD(?),NOW())";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getClientMail());
			stmt.setString(2, client.getClientPw());
			stmt.executeUpdate();

		} catch(Exception e){
			e.printStackTrace();
		} finally {
			this.dbUtil.close(null, stmt, conn);
		}

		return rowCnt;
	}
	// 메일 중복검사
	public String selectClientMail(String clientMail) {
		this.dbUtil = new DBUtil();
		String returnClientMail = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = this.dbUtil.getConnection();
			String sql = "SELECT client_mail FROM client WHERE client_mail=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, clientMail);
			rs = stmt.executeQuery();

			if(rs.next()) {
				returnClientMail = rs.getString("client_mail");
			}

		} catch(Exception e){
			e.printStackTrace();
		} finally {
			this.dbUtil.close(rs, stmt, conn);
		}
		return returnClientMail;
	}
	
	
	public Client login(Client client) {
		this.dbUtil = new DBUtil();
		Client returnClient = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.dbUtil.getConnection();
			String sql = "SELECT client_mail clientMail FROM client WHERE client_mail=? AND client_pw=PASSWORD(?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getClientMail());
			stmt.setString(2, client.getClientPw());
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnClient = new Client();
				returnClient.setClientMail(rs.getString("clientMail"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(rs, stmt, conn);
		}
		return returnClient;
	}
}
