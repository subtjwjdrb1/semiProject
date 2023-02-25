<%@page import="semi.vo.MemberVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(document).ready(function() {
	  $('select').material_select();
	});
	function checkAll(){
		var check=document.getElementsByName("check");
		var checkAll=document.getElementById("checkAll");
		if(checkAll.checked==true){
			for(var i=0; i<check.length;i++){
			    check[i].checked=true;
			}
		}else{
		    for(var i=0; i<check.length;i++){
			    check[i].checked=false;
			}
		}
	}
	function del(){
	    var chk = document.getElementsByName("check");
	    var len =0;
	    var sql ="delete from member where id in('";
	    for(var i=0;i<chk.length;i++){
	        if(chk[i].checked==true){
	            len++;
	            if(len>1){
	                sql+=",'"+chk[i].value+"'";
	            }else{
	                sql+=chk[i].value+"'";
	            }
	        }
	    }
	    sql+=")";
	    location.href="memberAdmin.do?cmd=delete&sql="+sql;
	}
	window.onload=function(){
	    var select = document.getElementsByName("select")[0];
	    console.log('${requestScope.select}');
	    select.selectedIndex='${requestScope.select}';
	    if('${requestScope.del}'!=""){
	        alert('${requestScope.del}');
	    }
	}
</script>    
 <h4 class="truncate">맴버 관리</h4>
 <table class="highlight">
        <thead>
          <tr>
              <th>
	              <p><input type="checkbox" id="checkAll" onclick="checkAll()"/>
			      <label for="checkAll"></label></p>
			  </th>
              <th>아이디</th>
              <th>이름</th>
              <th>닉네임</th>
              <th>폰번호</th>
              <th>이메일</th>
              <th>가입날짜</th>
          </tr>
        </thead>
        <tbody>
        	<c:forEach var="vo" items="${requestScope.list }">
	          <tr>
	            <td>
		            <p><input type="checkbox" name="check" id="${vo.id }" value="${vo.id }" />
				    <label for="${vo.id }"></label></p>
			    </td>
	            <td>${vo.id }</td>
	            <td>${vo.name }</td>
	            <td>${vo.nickname }</td>
	            <td>${vo.phone }</td>
	            <td>${vo.email }</td>
	            <td>${vo.regdate }</td>
	          </tr>
	        </c:forEach>  
        </tbody>
      </table>
     <div class="row">
      <div class="center">
   	<ul class="pagination">
   	<c:choose>
   		<c:when test="${pageNum>5}">
   			<li class="waves-effect"><a href="memberAdmin.do?cmd=list&pageNum=${startPage-1}&text=${requestScope.text}&select=${requestScope.select}"><i class="material-icons">chevron_left</i></a></li>
		</c:when>
		<c:otherwise>
			<li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
		</c:otherwise>
	</c:choose>    
	    <c:forEach var="i" begin="${requestScope.startPage }" end="${requestScope.endPage }">
		    <c:choose>
			    <c:when test="${pageNum==i }">
			    	<li class="active"><a href="memberAdmin.do?cmd=list&pageNum=${i }&text=${requestScope.text}&select=${requestScope.select}">${i }</a></li>
			    </c:when>
			    <c:otherwise>
			    	<li class="waves-effect"><a href="memberAdmin.do?cmd=list&pageNum=${i }&text=${requestScope.text}&select=${requestScope.select}">${i }</a></li>
			    </c:otherwise>
		    </c:choose>
	    </c:forEach>
	    <c:choose>
	    	<c:when test="${pageCount>endPage }">
	    		<li class="waves-effect"><a href="memberAdmin.do?cmd=list&pageNum=${endPage+1 }&text=${requestScope.text}&select=${requestScope.select}"><i class="material-icons">chevron_right</i></a></li>
	    	</c:when>
	    	<c:otherwise>
	    		<li class="disabled"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
	    	</c:otherwise>
	    </c:choose>
	    <!-- <li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li> -->
  	</ul>
  	</div>
  	<div style="margin-left: 1200px;"><a class="waves-effect waves-light btn" style="background-color: #ee6e73;" onclick="del()">회원삭제</a></div>
    <form class="col s12" method="post" action="<%=request.getContextPath()%>/memberAdmin.do?cmd=list">
    <div class="row" style="margin-left: 400px;">
    <div class="input-field col s2" >
	    <select name="select" >
		      <option value="0">아이디</option>
		      <option value="1">이름</option>
		      <option value="2">이메일</option>
	    </select>
  	</div>
        <div class="input-field col s3" >
          <input id="" name="text" type="text" class="validate" value="${requestScope.text}">
        </div>
      <button class="btn waves-effect waves-light" type="submit" name="action" style="margin-top: 25px; background-color: #ee6e73;">검색</button>
      </div>
    </form>
  </div>