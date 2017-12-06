<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>자료실 글 삭제 - 허브몰</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/mainstyle.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/clear.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/formLayout.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/mystyle.css'/>" />

<style type="text/css">
	body{
		padding:5px;
		margin:5px;
	 }
	.divForm{
		width:650px;
		border:1px solid #ddd;		
	}
	/* .divForm form{
		width:650px;
	} */
	.divForm div	{
		/* clear: both; */
		border:none;
		padding: 7px 0;
		margin: 0;
		overflow: auto;
	}	
	.sp{
		font-size:0.9em;
		color:#0056AC;			
	}
	.divForm fieldset	{
		border:0;
	}
</style>
<script type="text/javascript">
	function send(form){
		if(form.pwd.value==""){
			alert("비밀번호를 입력하세요");
			form.pwd.focus();
			return false;
		}
		
		if(!confirm("삭제하시겠습니까?")){
			return false;
		}else{
			return true;
		}
		
	}
</script>
</head>
<body>
<div class="divForm">
<form name="frmDelete" method="post" 
action="<c:url value='/reBoard/delete.do'/>" 
	onsubmit="return send(this)">
		<!-- 삭제 처리시 no가 필요하므로 hidden field에 넣어서 넘긴다 -->
		<input type="text" name="no" value="${param.no}">
		<input type="text" name="groupNo" value="${param.groupNo}">
		<input type="text" name="step" value="${param.step}">
		<input type="text" name="fileName" value="${param.fileName}">
		
		<fieldset>
		<legend>글 삭제</legend>
	        <div>           
	        	<span class="sp">${param.no}번 글을 삭제하시겠습니까?</span>                        
	        </div>
	        <div>           
	            <label for="pwd">비밀번호</label>
	            <input type="password" id="pwd" name="pwd" />   
	        </div>
	        <div class="center">
	            <input type ="submit"  value="삭제" />
	            <input type = "Button" value="글목록" 
                	OnClick
   ="location.href='<c:url value="/reBoard/list.do"/>'" />
	        </div>
	    </fieldset>
    </form>
</div>

</body>
</html>