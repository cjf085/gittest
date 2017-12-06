<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	/* PersonDTO dto=(PersonDTO)request.getAttribute("dto");
	
	String no = request.getParameter("no"); */
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>pEdit.jsp</title>
</head>
<body>
<h1>전화번호 수정</h1>
<form name="frmEdit" method="post" 
	action="<c:url value='/person/pEdit.do'/>">
	<!-- pEdit_ok.jsp에서 수정시 필요한 no를 hidden 필드에 넣어서
	넘겨준다-->
	<input type="hidden" name="no" value="${param.no}">
	
	<div>
		이름 : <input type="text" name="name" 
			value="${dto.name}">
	</div>
	<div>
		전화번호 : <input type="text" name="tel"
			value="${dto.tel}">
	</div>
	<div>
		<input type="submit" value="수정">
	</div>
</form>
<hr>
<a href="<c:url value='/person/pList.do'/>">전화번호목록</a>

</body>
</html>








