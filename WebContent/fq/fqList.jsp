<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
$(document).ready(function() {
	  $('select').material_select();
	});

function getDetail(fqno){
	  //console.log(sno);
	  location.href="fq.do?cmd=fqdetail&fqno="+fqno;
}
</script>
<div class="main" id="sellList">
 <table class="highlight">
        <thead>
          <tr>
              <th>종류</th>
              <th>제목</th>
              <th>작성자</th>
              <th>작성날짜</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="fq" items="${requestScope.list }">
			<tr onclick="getDetail(${fq.fqno })">
			<c:choose>
				<c:when test="${fq.fqgrade==1 }">
					<td>인기글</td>
				</c:when>
				<c:when test="${fq.fqgrade==2 }">
					<td>공지글</td>
				</c:when>
				<c:when test="${fq.fqtype==1 }">			
					<td>일반</td>
				</c:when>
				<c:when test="${fq.fqtype==2 }">
					<td>정보</td>
				</c:when>
				<c:otherwise>
					<td>질문</td>
				</c:otherwise>
			</c:choose>
				<td><a href="fq.do?cmd=fqdetail&fqno=${fq.fqno }">${fq.fqtitle }</a></td>
				<td>${fq.id }</td>
				<td>${fq.fqdate }</td>
			</tr>
		</c:forEach>
       </tbody>
      </table>
 
  <div class="row">   
  <br>  
<ul class="pagination">
<c:if test="${startPage>5 }">
	<li class="disabled"><a href="fq.do?cmd=fqList&pageNum=${startPage-1 }"><i class="material-icons">chevron_left</i></a></li>
    
</c:if>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${pageNum==i }">
				<li class="active"><a href="fq.do?cmd=fqList&pageNum=${i}" style="background-color:#993333;">${i}</a></li>
				
			</c:when>
			<c:otherwise>
			<li class="waves-effect"><a href="fq.do?cmd=fqList&pageNum=${i}">${i}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
<c:if test="${pageCount>endPage }">
<li class="waves-effect"><a href="fq.do?cmd=fqList&pageNum=${endPage+1 }"><i class="material-icons">chevron_right</i></a></li>
</c:if>
</ul>
</div>
</div>


    <a class="waves-effect waves-light btn" href="/semiProject/fq.do?cmd=insert" style="background-color:#993333;margin-left: 1200px;">
    <i class="material-icons" >create</i></a>
    
 
<form class="col s12" method="post" action="/semiProject/fq.do?cmd=search">
    <div class="row" style="margin-left: 400px;">
    <div class="input-field col s2" >
	    <select name="select" >
		      <option value="0">제목</option>
		      <option value="1">내용</option>
		      <option value="2">아이디</option>
	    </select>
  	</div>
        <div class="input-field col s3" >
          <input id="" name="text" type="text" class="validate" value="">
        </div>
      <button class="btn waves-effect waves-light" type="submit" name="action" style="margin-top: 25px; background-color: #993333;">검색</button>
      </div>
    </form>
