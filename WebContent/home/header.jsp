<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">

</style>
<%@ include file="api.jsp" %>
<script type="text/javascript">
$(".dropdown-button").dropdown();
</script>
<title>Insert title here</title>
</head>
<body>
 <nav class="nav-extended" >
    <div class="nav-wrapper" style="background-color:white; height: 100px;">
      <a href="<%=request.getContextPath()%>/start.jsp" class="brand-logo" style="margin-left: 300px;margin-top: 20px;"><img src="<%=request.getContextPath()%>/image/로고.png"></a>
      <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
    </div>
    <!-- Dropdown Structure 드롭박스 내용 -->
	<ul id="dropdown1" class="dropdown-content" style="background-color: #993333;">
	  <li><a href="/semiProject/sell.do?cmd=sellList" style="color: white;">팝니다</a></li>
	  <li class="divider"></li>
	  <li><a href="/semiProject/buy.do?cmd=buyList" style="color: white;">삽니다</a></li>
	</ul>
  </nav>
  <nav>
  <div class="nav-wrapper" style="background-color: #993333;">
    <ul class="left hide-on-med-and-down" style="margin-left: 300px;">
      <li><a href="<%=request.getContextPath()%>/notices.do?cmd=notices">공지사항</a></li>
      <li><a href="/semiProject/review.do?cmd=list" >리뷰게시판</a></li>
      <li><a href="/semiProject/fq.do?cmd=fqList">자유게시판</a></li>
      <!-- Dropdown Trigger -->
      <li><a class="dropdown-button" href="#!" data-activates="dropdown1">장터게시판<i class="material-icons right">arrow_drop_down</i></a></li>
    </ul>
    <ul id="nav-mobile" class="right hide-on-med-and-down" style="margin-right: 300px;">
        <c:choose>
			<c:when test="${empty sessionScope.id }">
				<li><a href="<%=request.getContextPath()%>/member.do?cmd=login">로그인</a></li>
        		<li><a href="<%=request.getContextPath()%>/member.do?cmd=join">회원가입</a></li>
			</c:when>
			<c:otherwise>
				<li>${sessionScope.id}님</li>
<<<<<<< HEAD
				<li><a href="<%=request.getContextPath()%>/member/logout.jsp">로그아웃</a></li>
=======
				<li><a href="<%=request.getContextPath()%>/member/logout.jsp">로그아웃</a></li>
>>>>>>> branch 'jg1' of https://github.com/SeoJeongGyu/semiproject.git
				<li><a href="<%=request.getContextPath()%>/member.do?cmd=mypage&id=${sessionScope.id}">마이페이지</a></li>

			</c:otherwise>
		</c:choose>
      </ul>
  </div>
</nav>
 
</body>
</html>