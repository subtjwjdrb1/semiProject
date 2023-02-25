<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	function getDetail(num){
	    location.href="<%=request.getContextPath()%>/notices.do?cmd=detail&admin=admin&num="+num;
	}
	function noticesOk(){
	    location.href="<%=request.getContextPath()%>/boardlist.do?cmd=noticesInsert";
	}
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
	    var sql ="delete from notices where num in('";
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
	    location.href="<%=request.getContextPath()%>/notices.do?cmd=noticesdelete&sql="+sql;
	}
</script>
<h4 class="truncate">공지사항</h4>
 <table class="highlight">
        <thead>
          <tr>
          	  <th>
	          <p><input type="checkbox" id="checkAll" onclick="checkAll()"/>
			  <label for="checkAll"></label></p>
			  </th>
              <th>작성번호</th>
              <th>제목</th>
              <th>작성일</th>
              <th>조회수</th>
          </tr>
        </thead>
        <tbody>
        	<c:forEach var="vo" items="${requestScope.list }">
	          <tr>
	            <td>
		            <p><input type="checkbox" name="check" id="${vo.num }" value="${vo.num }" />
				    <label for="${vo.num }"></label></p>
			    </td>
	            <td onclick="getDetail(${vo.num })">${vo.num }</td>
	            <td onclick="getDetail(${vo.num })">${vo.title }</td>
	            <td onclick="getDetail(${vo.num })">${vo.ndate }</td>
	            <td onclick="getDetail(${vo.num })">${vo.hit }</td>
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
			    	<li class="active"><a href="notices.do?cmd=notices&pageNum=${i }&text=${requestScope.text}">${i }</a></li>
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
  	<div style="margin-left: 1100px;"><a class="waves-effect waves-light btn" style="background-color: #ee6e73;" onclick="noticesOk()">글쓰기</a>&nbsp;&nbsp;&nbsp;<a class="waves-effect waves-light btn" style="background-color: #ee6e73;" onclick="del()">공지삭제</a></div>
  	
    <form class="col s12" method="post" action="<%=request.getContextPath()%>/boardlist.do?cmd=notices">
    <div class="row" style="margin-left: 400px;">
        <div class="input-field col s3" >
          <input id="" name="text" type="text" class="validate" value="${requestScope.text}">
        </div>
      <button class="btn waves-effect waves-light" type="submit" name="action" style="margin-top: 25px; background-color: #ee6e73;">검색</button>
      </div>
    </form>
  </div>