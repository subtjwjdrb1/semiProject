<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(document).ready(function() {
	  $('select').material_select();
	});
	function getDetail(num){
	    location.href="<%=request.getContextPath()%>/notices.do?cmd=detail&num="+num;
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
 <h4 class="truncate">공지사항</h4>
 <table class="highlight">
        <thead>
          <tr>
              <th>작성번호</th>
              <th>제목</th>
              <th>작성일</th>
              <th>조회수</th>
          </tr>
        </thead>
        <tbody>
        	<c:forEach var="vo" items="${requestScope.list }">
	          <tr onclick="getDetail(${vo.num })">
	            <td>${vo.num }</td>
	            <td>${vo.title }</td>
	            <td>${vo.ndate }</td>
	            <td>${vo.hit }</td>
	          </tr>
	        </c:forEach>  
        </tbody>
      </table>
     <div class="row">
      <div class="center">
   	<ul class="pagination">
   	<c:choose>
   		<c:when test="${pageNum>5}">
   			<li class="waves-effect"><a href="notices.do?cmd=notices&pageNum=${startPage-1}&text=${requestScope.text}"><i class="material-icons">chevron_left</i></a></li>
		</c:when>
		<c:otherwise>
			<li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
		</c:otherwise>
	</c:choose>    
	    <c:forEach var="i" begin="${requestScope.startPage }" end="${requestScope.endPage }">
		    <c:choose>
			    <c:when test="${pageNum==i }">
			    	<li class="active" ><a style="background-color: #993333;" href="notices.do?cmd=notices&pageNum=${i }&text=${requestScope.text}">${i }</a></li>
			    </c:when>
			    <c:otherwise>
			    	<li class="waves-effect"><a href="notices.do?cmd=notices&pageNum=${i }&text=${requestScope.text}">${i }</a></li>
			    </c:otherwise>
		    </c:choose>
	    </c:forEach>
	    <c:choose>
	    	<c:when test="${pageCount>endPage }">
	    		<li class="waves-effect"><a href="notices.do?cmd=notices&pageNum=${endPage+1 }&text=${requestScope.text}"><i class="material-icons">chevron_right</i></a></li>
	    	</c:when>
	    	<c:otherwise>
	    		<li class="disabled"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
	    	</c:otherwise>
	    </c:choose>
	    <!-- <li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li> -->
  	</ul>
  	</div>
    <form class="col s12" method="post" action="<%=request.getContextPath()%>/notices.do?cmd=notices">
    <div class="row" style="margin-left: 400px;">
        <div class="input-field col s3" >
          <input id="" name="text" type="text" class="validate" value="${requestScope.text}">
        </div>	
      <button class="btn waves-effect waves-light" type="submit" name="action" style="margin-top: 25px; background-color: #993333;">검색</button>
      </div>
    </form>
  </div>