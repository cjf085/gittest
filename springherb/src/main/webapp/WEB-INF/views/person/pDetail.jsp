<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	/* PersonDTO dto=(PersonDTO)request.getAttribute("dto");

	String no = request.getParameter("no"); */
	//=> Controller에서 jsp로 forward하므로
	//=> Controller와 jsp(뷰페이지)는 request를 공유한다.
%>    

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>pDetail.jsp</title>
<script type="text/javascript">
	function del(no){
		if(confirm("삭제하시겠습니까?")){
			location.href
			="<c:url value='/person/pDelete.do?no="+no +"'/>";
		}
	}
<%-- 	function del(){
		if(confirm("삭제하시겠습니까?")){
			location.href
			="<c:url value='pDelete.jsp?no=${param.no}'/>";
		}
	} --%>
</script>
</head>
<body>
	<h1>전화번호 상세보기</h1>
	<p>${param.no}를 클릭하였습니다.</p>
	<p>번호 : ${param.no}</p>	
	<p>이름 : ${dto.name}</p>	
	<p>전화번호 : ${dto.tel}</p>	
	<p>등록일 : ${dto.regdate}</p>
	<p>
		<a href="<c:url value='/person/pList.do'/>">
			목록</a> | 
		<a href
="<c:url value='/person/pEdit.do?no=${param.no}'/>">수정</a> | 
		<a href="#" onclick="del(${param.no})">삭제</a>
		<!-- <a href="#" onclick="del()">삭제</a> -->
	</p>	
</body>
</html>











