<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
<title>자유게시판 글 수정 - 허브몰</title>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/mainstyle.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/clear.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/formLayout.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/mystyle.css'/>" />

<script type="text/javascript">
	function send(form){		
		if(form.title.value==""){
			alert("제목을 입력하세요");
			form.title.focus();
			return false;
		}else if(form.name.value.length<1){
			alert("이름을 입력하세요");
			form.name.focus();
			return false;			
		}else if(!form.pwd.value){
			alert("비밀번호를 입력하세요");
			form.pwd.focus();
			return false;			
		}
		
		return true;		
	}
</script>

</head>
<body>
<div class="divForm">
<form name="frmEdit" method="post" 
action="<c:url value='/board/edit.do'/>"
	onsubmit="return send(this)"> 
	<!-- 수정 처리시 필요한 no를 hidden 필드에 넣는다 -->
	<input type="hidden" name="no" value="${param.no}">
	
    <fieldset>
	<legend>글수정</legend>
        <div class="firstDiv">
            <label for="title">제목</label>
            <input type="text" id="title" name="title" 
            	value="${vo.title}" />
        </div>
        <div>
            <label for="name">작성자</label>
            <input type="text" id="name" name="name" 
            	value="${vo.name}"/>
        </div>
        <div>
            <label for="pwd">비밀번호</label>
            <input type="password" id="pwd" name="pwd" />
        </div>
        <div>
            <label for="email">이메일</label>
            <input type="text" id="email" name="email"
            	value="${vo.email}" />
        </div>
        <div>  
        	<label for="content">내용</label>        
 			<textarea id="content" name="content" rows="12" cols="40">${vo.content}</textarea>
        </div>
        <div class="center">
            <input type = "submit" value="수정"/>
            <input type = "Button" value="글목록" 
            onclick
="location.href='<c:url value="/board/list.do"/>'" />         
        </div>
	</fieldset>
</form>    
</div>

</body>
</html>







