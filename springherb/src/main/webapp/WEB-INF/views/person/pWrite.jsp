<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>pWrite.jsp</title>
</head>
<body>
<h1>전화번호 등록</h1>
<form name="frmWrite" method="post" 
	action="<c:url value='/person/pWrite.do'/>">
	<div>
		이름 : <input type="text" name="name">
	</div>
	<div>
		전화번호 : <input type="text" name="tel">
	</div>
	<div>
		<input type="submit" value="등록">
	</div>
</form>
<hr>
<a href="<c:url value='/person/pList.do'/>">
	전화번호목록</a>

</body>
</html>








