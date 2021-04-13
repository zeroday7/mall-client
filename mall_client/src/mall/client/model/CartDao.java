package mall.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mall.client.commons.DBUtil;
import mall.client.vo.Client;

public class CartDao {
	private DBUtil dbUtil;
	public List<Map<String, Object>> selectCartList(Client client){
		List<Map<String, Object>> list = new ArrayList<>();
		/*
		 * sql = "SELECT c.cart_no, e.ebook_no, e.ebook_title, c.cart_date FROM cart c INNER JOIN ebook e ON c.ebook_no = e.ebook_no
		 */
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = this.dbUtil.getConnection();
			String sql = "SELECT c.cart_no cartNo, e.ebook_no ebookNo, e.ebook_title ebookTitle, c.cart_date cartDate FROM cart c INNER JOIN ebook e ON c.ebook_no = e.ebook_no  WHERE client_mail=? ORDER BY cart_date DESC";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getClientMail());
			System.out.printf("stmt: %s<cartDao.selectCartList>\n", stmt);

			rs = stmt.executeQuery();

			while(rs.next()){
				Map<String, Object> map = new HashMap<>();
				map.put("cartNo", rs.getInt("cartNo"));
				map.put("ebookNo", rs.getInt("ebookNo"));
				map.put("ebookTitle", rs.getString("ebookTitle"));
				map.put("cartDate", rs.getString("cartDate"));
				list.add(map);
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(rs, stmt, conn);
		}


		return list;
	}
}
