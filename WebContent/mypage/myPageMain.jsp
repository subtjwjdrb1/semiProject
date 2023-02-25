<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/home/api.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

</script>
<ul id="nav-mobile" class="side-nav fixed">
    	<li class="bold"><a href="<%=request.getContextPath()%>/member.do?cmd=mypage&id=${sessionScope.id}" class="waves-effect waves-teal">마이 페이지</a></li>
        <li class="bold"><a href="<%=request.getContextPath()%>/member.do?cmd=mypage&id=${sessionScope.id}"class="waves-effect waves-teal"><i class="material-icons">edit</i>개인정보 수정</a></li>
        <li class="bold"><a href="<%=request.getContextPath()%>/mypage.do?cmd=list&id=${sessionScope.id}" class="waves-effect waves-teal"><i class="material-icons">star_border</i>북마크</a></li>
 </ul>