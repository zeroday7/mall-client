<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clientOne</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	<!-- 메뉴1 -->

	<h1>고객정보</h1>
	<table border="1">
		<tr>
			<td>clientNo</td>
			<td>${client.clientNo}</td>
		</tr>

		<tr>
			<td>clientMail</td>
			<td>${client.clientMail}</td>
		</tr>

		<tr>
			<td>clientDate</td>
			<td>${client.clientDate}</td>
		</tr>
	</table>

	<a href="${pageContext.request.contextPath}/UpdateClientPwController"><button type="button">비밀번호수정</button></a>
	
	<a href="${pageContext.request.contextPath}/DeleteClientController"><button type="button">회원탈퇴</button></a>
</body>
</html>



 