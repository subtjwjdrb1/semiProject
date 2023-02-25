<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/home/api.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
function btn(){
//SIDEBAR
  $('.button-collapse').sideNav({
      menuWidth: 300, // Default is 300
      edge: 'left', // Choose the horizontal origin
      closeOnClick: false, // Closes side-nav on <a> clicks, useful for Angular/Meteor
      draggable: true // Choose whether you can drag to open on touch screens
    }
  );
  $(".dropdown-button").dropdown();
  // START OPEN
  $('.button-collapse').sideNav('show');
}
</script>
<title>Insert title here</title>
</head>
<body>
<ul id="dropdown1" class="dropdown-content" >
	  <li><a href="<%=request.getContextPath()%>/report.do?cmd=reviewReport">리뷰신고게시판</a></li>
	  <li class="divider"></li>
	  <li><a href="<%=request.getContextPath()%>/report.do?cmd=fqboardReport">자유신고게시판</a></li>
	  <li class="divider"></li>
	  <li><a href="<%=request.getContextPath()%>/report.do?cmd=sellReport">팝니다신고게시판</a></li>
	  <li class="divider"></li>
	  <li><a href="<%=request.getContextPath()%>/report.do?cmd=buyReport">삽니다신고게시판</a></li>
	</ul>
   <ul id="slide-out" class="side-nav">
    <li><a href="<%=request.getContextPath()%>/memberAdmin.do?cmd=list">회원관리</a></li>
    <li><div class="divider"></div></li>
    <li><a href="<%=request.getContextPath()%>/boardlist.do?cmd=notices">게시물관리</a></li>
    <li><div class="divider"></div></li>
    <li><a class="dropdown-button" href="#!" data-activates="dropdown1">신고게시판<i class="material-icons right">arrow_drop_down</i></a></li>
    <li><a href="<%=request.getContextPath()%>/member/logout.jsp">로그아웃</a></li>
    <li><div class="divider"></div></li>
  </ul>
  <a href="javascript:btn()" data-activates="slide-out" class="button-collapse" ><i class="material-icons" style="font-size: 70px; color: #ee6e73;" >menu</i></a>
</body>
</html>