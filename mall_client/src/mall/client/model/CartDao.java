package mall.client.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mall.client.commons.DBUtil;

public class CartDao {
	private DBUtil dbUtil;
	public List<Map<String, Object>> selectCartList() {
		List<Map<String, Object>> list = new ArrayList<>();
		/*
 		SELECT 
			c.cart_no, 
			e.ebook_no, 
			e.ebook_title, 
			c.cart_date 
		FROM cart c INNER JOIN ebook e
		ON c.ebook_no = e.ebook_no
		
		while(rs.next()) {
			Map<String, Object> map = new HashMap<>();
			map.put("cartNo", rs.getInt("cartNo"));
			map.put("ebookNo", rs.getInt("ebookNo"));
			map.put("ebookTitle", rs.getString("ebookTitle"));
			map.put("cartDate", rs.getString("cateDate"));
			list.add(map);
		}
		*/
		return list; 
	}
}
