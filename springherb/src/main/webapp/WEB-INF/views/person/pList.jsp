<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>pList.jsp</title>
</head>
<body>
<h1>전화번호 목록</h1>
<table border="1" style="width:500px">
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>전화번호</th>
		<th>등록일</th>
	</tr>
	<c:if test="${empty alist }">	
		<tr>
			<td colspan="4">데이터가 없습니다.</td>
		</tr>
	</c:if>
	<c:if test="${!empty alist }">	
		<!-- 반복 시작 -->
		<c:forEach var="dto" items="${alist }">
			<tr>
				<td>${dto.no}</td>
				<td><a href
="<c:url value='/person/pDetail.do?no=${dto.no}'/>">
				${dto.name}</a></td>
				<td>${dto.tel}</td>
				<td><fmt:formatDate value="${dto.regdate}"
					pattern="yyyy-MM-dd" /></td>
			</tr>
		</c:forEach>
	</c:if>
	<!-- 반복 끝 -->	
</table>
<br>
<!-- 검색 -->
<form name="frmSearch" method="post" 
	action="<c:url value='/person/pList.do'/>">
	이름 <input type="text" name="name" value="${param.name }">
	<input type="submit" value="검색">
</form>

<hr>
<a href="<c:url value='/person/pWrite.do'/>">
	전화번호 등록으로 이동</a>

</body>
</html>















