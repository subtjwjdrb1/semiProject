<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
$(document).ready(function() {
	  $('select').material_select();
	});

function getDetail(fqno){
    location.href="<%=request.getContextPath()%>/report.do?cmd=fqboardReportDetail&fqno="+fqno;
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
	    var sql ="delete from fqboard where fqno in('";
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
	    location.href="report.do?cmd=fqboardReportDel&sql="+sql;
	}
</script>
<div class="main" id="sellList">
<h4 class="truncate">자유신고게시판</h4>
 <table class="highlight">
        <thead>
          <tr>
              <th><p><input type="checkbox" id="checkAll" onclick="checkAll()"/>
			  <label for="checkAll"></label></p></th>
              <th>종류</th>
              <th>제목</th>
              <th>작성자</th>
              <th>작성날짜</th>
              <th>신고수</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="vo" items="${requestScope.list }">
			<tr>
	            <td>
		            <p><input type="checkbox" name="check" id="${vo.fqno }" value="${vo.fqno }" />
				    <label for="${vo.fqno }"></label></p>
			    </td>
			<c:choose>
				<c:when test="${vo.fqtype==1 }">			
					<td onclick="getDetail(${vo.fqno })">일반</td>
				</c:when>
				<c:when test="${vo.fqtype==2 }">
					<td onclick="getDetail(${vo.fqno })">정보</td>
				</c:when>
				<c:otherwise>
					<td onclick="getDetail(${vo.fqno })">질문</td>
				</c:otherwise>
			</c:choose>
				<td onclick="getDetail(${vo.fqno })">${vo.fqtitle }</td>
				<td onclick="getDetail(${vo.fqno })">${vo.id }</td>
				<td onclick="getDetail(${vo.fqno })">${vo.fqdate }</td>
				<td onclick="getDetail(${vo.fqno })">${vo.fqreport }</td>
			</tr>
		</c:forEach>
       </tbody>
      </table>
 
  <div class="row">   
  <br>  
<ul class="pagination">
<c:if test="${startPage>5 }">
	<li class="disabled"><a href="report.do?cmd=fqboardDetail&pageNum=${startPage-1 }"><i class="material-icons">chevron_left</i></a></li>
    
</c:if>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${pageNum==i }">
				<li class="active"><a href="report.do?cmd=fqboardDetail&pageNum=${i}" >${i}</a></li>
				
			</c:when>
			<c:otherwise>
			<li class="waves-effect"><a href="report.do?cmd=fqboardDetail&pageNum=${i}">${i}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
<c:if test="${pageCount>endPage }">
<li class="waves-effect"><a href="report.do?cmd=fqboardDetail&pageNum=${endPage+1 }"><i class="material-icons">chevron_right</i></a></li>
</c:if>
</ul>
</div>
<div style="margin-left: 1100px;">&nbsp;&nbsp;&nbsp;<a class="waves-effect waves-light btn" style="background-color: #ee6e73;" onclick="del()">글삭제</a></div>
</div>

