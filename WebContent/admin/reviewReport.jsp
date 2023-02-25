<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
function getDetail(rno){
    location.href="<%=request.getContextPath()%>/report.do?cmd=reviewReportDetail&rno="+rno;
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
    var sql ="delete from review where rno in('";
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
    location.href="report.do?cmd=reviewReportDel&sql="+sql;
}

</script>
<h4 class="truncate">리뷰신고게시판</h4>
 <table class="highlight">
        <thead>
          <tr>
          	  <th>
	          <p><input type="checkbox" id="checkAll" onclick="checkAll()"/>
			  <label for="checkAll"></label></p>
			  </th>
              <th>리뷰번호</th>
              <th>제목</th>
              <th>작성일</th>
              <th>조회수</th>
              <th>신고수</th>
          </tr>
        </thead>
        <tbody>
        	<c:forEach var="vo" items="${requestScope.list }">
	          <tr>
	            <td>
		            <p><input type="checkbox" name="check" id="${vo.rno }" value="${vo.rno }" />
				    <label for="${vo.rno }"></label></p>
			    </td>
	            <td onclick="getDetail(${vo.rno })">${vo.rno }</td>
	            <td onclick="getDetail(${vo.rno })">${vo.rtitle }</td>
	            <td onclick="getDetail(${vo.rno })">${vo.rdate }</td>
	            <td onclick="getDetail(${vo.rno })">${vo.rhit }</td>
	            <td onclick="getDetail(${vo.rno })">${vo.rreport }</td>
	          </tr>
	        </c:forEach>  
        </tbody>
      </table>
     <div class="row">
      <div class="center">
   	<ul class="pagination">
   	<c:choose>
   		<c:when test="${pageNum>5}">
   			<li class="waves-effect"><a href="report.do?cmd=reviewReport&pageNum=${startPage-1}"><i class="material-icons">chevron_left</i></a></li>
		</c:when>
		<c:otherwise>
			<li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
		</c:otherwise>
	</c:choose>    
	    <c:forEach var="i" begin="${requestScope.startPage }" end="${requestScope.endPage }">
		    <c:choose>
			    <c:when test="${pageNum==i }">
			    	<li class="active"><a href="report.do?cmd=reviewReport&pageNum=${i }">${i }</a></li>
			    </c:when>
			    <c:otherwise>
			    	<li class="waves-effect"><a href="report.do?cmd=reviewReport&pageNum=${i }">${i }</a></li>
			    </c:otherwise>
		    </c:choose>
	    </c:forEach>
	    <c:choose>
	    	<c:when test="${pageCount>endPage }">
	    		<li class="waves-effect"><a href="report.do?cmd=reviewReport&pageNum=${endPage+1 }"><i class="material-icons">chevron_right</i></a></li>
	    	</c:when>
	    	<c:otherwise>
	    		<li class="disabled"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
	    	</c:otherwise>
	    </c:choose>
	    <!-- <li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li> -->
  	</ul>
  	</div>
  	<div style="margin-left: 1100px;">&nbsp;&nbsp;&nbsp;<a class="waves-effect waves-light btn" style="background-color: #ee6e73;" onclick="del()">글삭제</a></div>
  </div>