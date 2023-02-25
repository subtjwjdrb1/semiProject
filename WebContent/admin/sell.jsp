<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
function getDetail(sno){
    location.href="<%=request.getContextPath()%>/boardlist.do?cmd=sellDetail&sno="+sno;
}
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
	    var sql ="delete from sell where sno in('";
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
	    location.href="boardlist.do?cmd=selldelete&sql="+sql;
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
 <table class="highlight">
        <thead>
          <tr>
              <th>
	              <p><input type="checkbox" id="checkAll" onclick="checkAll()"/>
			      <label for="checkAll"></label></p>
			  </th>
              <th>게시물번호</th>
              <th>OS</th>
              <th>통신사</th>
              <th>회사</th>
              <th>가격</th>
              <th>제목</th>
              <th>작성일</th>
              <th>글등급</th>
              <th>신고수</th>
              <th>작성아이디</th>
          </tr>
        </thead>
        <tbody>
        	<c:forEach var="vo" items="${requestScope.list }">
	          <tr>
	            <td>
		            <p><input type="checkbox" name="check" id="${vo.sno }" value="${vo.sno }" />
				    <label for="${vo.sno }"></label></p>
			    </td>
			    <td onclick="getDetail(${vo.sno })">${vo.sno }</td>
              <td onclick="getDetail(${vo.sno })">${vo.os }</td>
              <td onclick="getDetail(${vo.sno })">${vo.telecom }</td>
              <td onclick="getDetail(${vo.sno })">${vo.company }</td>
              <td onclick="getDetail(${vo.sno })">${vo.price }</td>
              <td onclick="getDetail(${vo.sno })">${vo.stitle }</td>
              <td onclick="getDetail(${vo.sno })">${vo.sdate }</td>
              <td onclick="getDetail(${vo.sno })">${vo.sgrade }</td>
              <td onclick="getDetail(${vo.sno })">${vo.sreport }</td>
              <td onclick="getDetail(${vo.sno })">${vo.id }</td>
	          </tr>
	        </c:forEach>  
        </tbody>
      </table>
     <div class="row">
      <div class="center">
   	<ul class="pagination">
   	<c:choose>
   		<c:when test="${pageNum>5}">
   			<li class="waves-effect"><a href="boardlist.do?cmd=sell&pageNum=${startPage-1}&text=${requestScope.text}&select=${requestScope.select}"><i class="material-icons">chevron_left</i></a></li>
		</c:when>
		<c:otherwise>
			<li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
		</c:otherwise>
	</c:choose>    
	    <c:forEach var="i" begin="${requestScope.startPage }" end="${requestScope.endPage }">
		    <c:choose>
			    <c:when test="${pageNum==i }">
			    	<li class="active"><a href="boardlist.do?cmd=sell&pageNum=${i }&text=${requestScope.text}&select=${requestScope.select}">${i }</a></li>
			    </c:when>
			    <c:otherwise>
			    	<li class="waves-effect"><a href="boardlist.do?cmd=sell&pageNum=${i }&text=${requestScope.text}&select=${requestScope.select}">${i }</a></li>
			    </c:otherwise>
		    </c:choose>
	    </c:forEach>
	    <c:choose>
	    	<c:when test="${pageCount>endPage }">
	    		<li class="waves-effect"><a href="boardlist.do?cmd=sell&pageNum=${endPage+1 }&text=${requestScope.text}&select=${requestScope.select}"><i class="material-icons">chevron_right</i></a></li>
	    	</c:when>
	    	<c:otherwise>
	    		<li class="disabled"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
	    	</c:otherwise>
	    </c:choose>
	    <!-- <li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li> -->
  	</ul>
  	</div>
	  	<div style="margin-left: 1200px;"><a class="waves-effect waves-light btn" style="background-color: #ee6e73;" onclick="del()">게시물삭제</a></div>
	    <form class="col s12" method="post" action="<%=request.getContextPath()%>/boardlist.do?cmd=sell">
	    <div class="row" style="margin-left: 400px;">
	    <div class="input-field col s2" >
		    <select name="select" >
			      <option value="0">제목</option>
			      <option value="1">내용</option>
			      <option value="2">아이디</option>
		    </select>
	  	</div>
	        <div class="input-field col s3" >
	          <input id="text" name="text" type="text" class="validate" value="${requestScope.text}">
	        </div>
	      <button class="btn waves-effect waves-light" type="submit" name="action" style="margin-top: 25px; background-color: #ee6e73;">검색</button>
	      </div>
	    </form>
	  </div>
