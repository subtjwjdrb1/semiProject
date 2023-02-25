<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	window.onload=function(){
       		var materialnote = document.getElementById("materialnote");
       		materialnote.value='${requestScope.vo.content }';
	}
</script>
<div class="row">
   <form class="col s12" method="post" action="<%=request.getContextPath()%>/notices.do?cmd=update" name="frm">
        <div id="check" style="margin-left: 30px;">
        <input type="checkbox" id="notices" name="chk" value="notices" checked="checked" />
		<label for="fqboard">공지게시판&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input type="checkbox" id="fqboard" name="chk" value="fqboard" />
		<label for="fqboard">자유질문게시판&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input type="checkbox" id="sell" name="chk" value="sell" />
		<label for="sell">팝니다게시판&nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input type="checkbox" id="buy" name="chk" value="buy" />
		<label for="buy">삽니다게시판&nbsp;&nbsp;&nbsp;&nbsp;</label>
        </div>
        <input type="hidden" name="num" value="${requestScope.vo.num }">
      <div class="row">
        <div class="input-field col s6" style="margin-left: 15px">
          <input name="title" type="text" class="validate" value="${requestScope.vo.title }">
          <label for="title">제목</label>
        </div>
        <div class="input-field col s12" style="margin-left:10px; width:900px;">
          <label for="content">내용</label> <br><br><br>
       	 <jsp:include page="/home/summernote.jsp"></jsp:include>
        </div>
      </div>
      <button class="btn waves-effect waves-light" type="submit" style="margin-left:30px; background-color:#ee6e73; width: 200px">
      	<i class="large material-icons">done</i></button>
   </form>
  </div>