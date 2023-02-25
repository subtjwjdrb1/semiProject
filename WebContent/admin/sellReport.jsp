<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
function getDetail(sno){
    location.href="<%=request.getContextPath()%>/report.do?cmd=sellReportDetail&sno="+sno;
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
	    location.href="report.do?cmd=sellReportDel&sql="+sql;
	}
</script>  
<h4 class="truncate">팝니다신고게시판</h4>  
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
   			<li class="waves-effect"><a href="report.do?cmd=sellReport&pageNum=${startPage-1}"><i class="material-icons">chevron_left</i></a></li>
		</c:when>
		<c:otherwise>
			<li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
		</c:otherwise>
	</c:choose>    
	    <c:forEach var="i" begin="${requestScope.startPage }" end="${requestScope.endPage }">
		    <c:choose>
			    <c:when test="${pageNum==i }">
			    	<li class="active"><a href="report.do?cmd=sellReport&pageNum=${i }">${i }</a></li>
			    </c:when>
			    <c:otherwise>
			    	<li class="waves-effect"><a href="report.do?cmd=sellReport&pageNum=${i }">${i }</a></li>
			    </c:otherwise>
		    </c:choose>
	    </c:forEach>
	    <c:choose>
	    	<c:when test="${pageCount>endPage }">
	    		<li class="waves-effect"><a href="report.do?cmd=sellReport&pageNum=${endPage+1 }"><i class="material-icons">chevron_right</i></a></li>
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
