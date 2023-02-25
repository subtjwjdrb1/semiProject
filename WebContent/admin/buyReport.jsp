<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
function getDetail(bno){
    location.href="<%=request.getContextPath()%>/report.do?cmd=buyReportDetail&bno="+bno;
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
	    var sql ="delete from buy where bno in('";
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
	    location.href="report.do?cmd=buyReportDel&sql="+sql;
	}
</script>  
<h4 class="truncate">삽니다신고게시판</h4>  
 <table class="highlight">
        <thead>
          <tr>
              <th>
	              <p><input type="checkbox" id="checkAll" onclick="checkAll()"/>
			      <label for="checkAll"></label></p>
			  </th>
              <th>삽니다번호</th>
              <th>거래상태</th>
              <th>제목</th>
              <th>작성자</th>
              <th>작성날짜</th>
              <th>신고</th>
          </tr>
        </thead>
        <tbody>
        	<c:forEach var="vo" items="${requestScope.list }">
	          <tr>
	            <td>
		            <p><input type="checkbox" name="check" id="${vo.bno }" value="${vo.bno }" />
				    <label for="${vo.bno }"></label></p>
			    </td>
			    <td onclick="getDetail(${vo.bno })">${vo.bno }</td>
			    <c:choose>
				<c:when test="${vo.success==1 }">			
					<td>거래중</td>
				</c:when>
				<c:otherwise>
					<td>거래완료</td>
				</c:otherwise>
			</c:choose>
              <td onclick="getDetail(${vo.bno })">${vo.btitle }</td>
              <td onclick="getDetail(${vo.bno })">${vo.id }</td>
              <td onclick="getDetail(${vo.bno })">${vo.bdate }</td>
              <td onclick="getDetail(${vo.bno })">${vo.breport }</td>
	          </tr>
	        </c:forEach>  
        </tbody>
      </table>
     <div class="row">
      <div class="center">
   	<ul class="pagination">
   	<c:choose>
   		<c:when test="${pageNum>5}">
   			<li class="waves-effect"><a href="report.do?cmd=buyReport&pageNum=${startPage-1}"><i class="material-icons">chevron_left</i></a></li>
		</c:when>
		<c:otherwise>
			<li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
		</c:otherwise>
	</c:choose>    
	    <c:forEach var="i" begin="${requestScope.startPage }" end="${requestScope.endPage }">
		    <c:choose>
			    <c:when test="${pageNum==i }">
			    	<li class="active"><a href="report.do?cmd=buyReport&pageNum=${i }">${i }</a></li>
			    </c:when>
			    <c:otherwise>
			    	<li class="waves-effect"><a href="report.do?cmd=buyReport&pageNum=${i }">${i }</a></li>
			    </c:otherwise>
		    </c:choose>
	    </c:forEach>
	    <c:choose>
	    	<c:when test="${pageCount>endPage }">
	    		<li class="waves-effect"><a href="report.do?cmd=buyReport&pageNum=${endPage+1 }"><i class="material-icons">chevron_right</i></a></li>
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
