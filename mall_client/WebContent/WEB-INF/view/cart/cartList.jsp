<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import= "mall.client.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	List<Map<String, Object>> cartList = (List<Map<String, Object>>)request.getAttribute("cartList");
%>
	<!-- 메인메뉴 -->
	<div>
		<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	</div>
	<!-- cartList -->
	<h1>CartList</h1>
	<table border="1">
		<tr>
			<td>cartNo</td>
			<td>ebookNo</td>
			<td>ebookTitle</td>
			<td>cartDate</td>
		</tr>
		<%
			for(Map<String, Object> map: cartList){
				int cartNo = (int)map.get("cartNo");
				String ebookTitle = (String)map.get("ebookTitle");
				int ebookNo = (int)map.get("ebookNo");
				String cartDate = (String)map.get("cartDate");
		%>
			<tr>
				<td><%=cartNo %></td>
				<td><%=ebookNo %></td>
				<td><%=ebookTitle %></td>
				<td><%=cartDate.substring(0,11) %></td>
			</tr>
		<%
			}
		%>
	</table>
</body>
</html> 