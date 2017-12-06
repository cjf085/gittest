<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>자유게시판 글 목록 - 허브몰</title>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/mainstyle.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/clear.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/formLayout.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/mystyle.css'/>" />

<script type="text/javascript">	
	function pageFunc(curPage){
		document.frmPage.currentPage.value=curPage;
		frmPage.submit();
	}
</script>
<style type="text/css">
	body{
		padding:5px;
		margin:5px;
	 }	
</style>	
</head>	
<body>
<h2>자유게시판</h2>
<!-- 페이징 처리에 필요한 form 태그 -->
<form name="frmPage" method="post" 
action="<c:url value='/board/list.do'/>">
	<input type="text" name="searchCondition" 
		value="${param.searchCondition }">
	<input type="text" name="searchKeyword" 
		value="${param.searchKeyword }">
	<input type="text" name="currentPage">
</form>

<c:if test="${!empty param.searchKeyword }">
	<!-- 검색의 경우 -->
	<p>검색어 : ${searchVO.searchKeyword}, ${pagingInfo.totalRecord }건 
		검색되었습니다.</p>
</c:if>
<c:if test="${empty param.searchKeyword }">
	<!-- 전체 조회의 경우 -->
	<p>전체 조회 결과, ${pagingInfo.totalRecord }건 조회되었습니다.</p>
</c:if>

<div class="divList">
<table class="box2"
	 	summary="기본 게시판에 관한 표로써, 번호, 제목, 작성자, 작성일, 조회수에 대한 정보를 제공합니다.">
	<caption>기본 게시판</caption>
	<colgroup>
		<col style="width:10%;" />
		<col style="width:50%;" />
		<col style="width:15%;" />
		<col style="width:15%;" />
		<col style="width:10%;" />		
	</colgroup>
	<thead>
	  <tr>
	    <th scope="col">번호</th>
	    <th scope="col">제목</th>
	    <th scope="col">작성자</th>
	    <th scope="col">작성일</th>
	    <th scope="col">조회수</th>
	  </tr>
	</thead> 
	<tbody>
		<c:if test="${empty list}">		
			<tr>
				<td colspan="5">해당하는 데이터가 없습니다.</td>
			</tr>
		</c:if>	
		<c:if test="${!empty list}">		
		  	<!--게시판 내용 반복문 시작  -->	
		  	<c:forEach var="vo" items="${list }">
				<tr  style="text-align:center">
					<td>${vo.no}</td>
					<td style="text-align:left">
						<a href
="<c:url value='/board/countUpdate.do?no=${vo.no}'/>">
						${vo.title}</a>
					</td>
					<td>${vo.name}</td>
					<td><fmt:formatDate value="${vo.regdate}" 
						pattern="yyyy-MM-dd" /> </td>
					<td>${vo.readcount}</td>		
				</tr> 
		  	</c:forEach>
		  	<!--반복처리 끝  -->
	  	</c:if>
	  </tbody>
</table>	   
</div>
<div class="divPage">
	<!-- 페이지 번호 추가 -->		
	<!-- 이전 블럭으로 이동 ◀ -->
	<c:if test="${pagingInfo.firstPage>1 }">
		<a href="#" onclick="pageFunc(${pagingInfo.firstPage-1})">		
			<img src="<c:url value='/images/first.JPG'/>">
		</a>	
	</c:if>	
	
	<!-- [1][2][3][4][5][6][7][8][9][10] -->
	<c:forEach var="i" begin="${pagingInfo.firstPage}" 
		end="${pagingInfo.lastPage}">
		<c:if test="${i==pagingInfo.currentPage}">
			<span style="font-weight:bold;color:blue">${i }</span>
		</c:if>
		<c:if test="${i!=pagingInfo.currentPage}">
			<a href="#" onclick="pageFunc(${i })">
			[${i }]</a>
 		</c:if>				
	</c:forEach>
	
	<!-- 다음 블럭으로 이동 ▶ -->
	<c:if test="${pagingInfo.lastPage < pagingInfo.totalPage}">
		<a href="#" onclick="pageFunc(${pagingInfo.lastPage+1})">	
			<img src="<c:url value='/images/last.JPG'/>">
		</a>	
	</c:if>
	
	<!--  페이지 번호 끝 -->
</div>
<div class="divSearch">
   	<form name="frmSearch" method="post" 
   	action="<c:url value='/board/list.do'/>">
        <select name="searchCondition">
            <option value="title"
            	<c:if test="${param.searchCondition=='title' }"> 
            		selected
            	</c:if>
            	>제목</option>
            <option value="content"
            	<c:if test="${param.searchCondition=='content' }"> 
            		selected
            	</c:if>    
            >내용</option>
            <option value="name" 
            	<c:if test="${param.searchCondition=='name' }"> 
            		selected
            	</c:if>
            >작성자</option>
        </select>   
        <input type="text" name="searchKeyword" title="검색어 입력"
        	value="${param.searchKeyword }">   
		<input type="submit" value="검색">
    </form>
</div>

<div class="divBtn">
    <a href="<c:url value='/board/write.do'/>">글쓰기</a>
</div>

</body>
</html>

