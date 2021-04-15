<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "mall.client.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Ebook ebook = (Ebook)request.getAttribute("ebook");
%>
	<div>
		<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	</div>

	<h1>ebookOne</h1>
	<table border="1">
		<tr>
			<td>ebookTitle</td>
			<td><%=ebook.getEbookTitle() %></td>
		</tr>
		<tr>
			<td>카테고리</td>
			<td><%=ebook.getCategoryName() %></td>
		</tr>
		<tr>
			<td>작가</td>
			<td><%=ebook.getEbookAuthor() %></td>
		</tr>
		<tr>
			<td>출판사</td>
			<td><%=ebook.getEbookCompany() %></td>
		</tr>
		<tr>
			<td>ISBN</td>
			<td><%=ebook.getEbookISBN() %></td>
		</tr>
		<tr>
			<td>판매날짜</td>
			<td><%=ebook.getEbookDate().substring(0,11) %></td>
		</tr>
		<tr>
			<td>가격</td>
			<td><%=ebook.getEbookPrice() %></td>
		</tr>
		<tr>
			<td>페이지 수</td>
			<td><%=ebook.getEbookPageCount() %></td>
		</tr>
		<tr>
			<td>이미지</td>
			<td><img src="<%=request.getContextPath()%>/img/default.jpg"></td>
		</tr>
		<tr>
			<td>소개</td>
			<td><%=ebook.getEbookSummary() %></td>
		</tr>
		<tr>
			<td>상태</td>
			<td><%=ebook.getEbookState() %></td>
		</tr>
	</table>
	<!-- InsertCartController?ebookNo - cartDao.insertCart() -> redirect:CartListController -->
	<!-- 로그인 중이거나 판매중이 아니면 버튼을 누를 수 없음 -->
	<a href="<%=request.getContextPath()%>/InsertCartController?ebookNo=<%=ebook.getEbookNo()%>">
		<%
			if(session.getAttribute("loginClient") == null || !ebook.getEbookState().equals("판매중")){
		%>
				<button type="submit" disabled="disabled">장바구니추가</button>
		<%
			} else {
		%>
				<button type="submit">장바구니추가</button>
		<%
			}
		%>	
	</a>
	<!-- 바로구매...? -->
	<!-- 리뷰 -->
	<!-- 별점...? -->
</body>
</html>