<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
function getDetail(fqno){
    location.href="<%=request.getContextPath()%>/boardlist.do?cmd=fqboardDetail&fqno="+fqno;
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
	    location.href="boardlist.do?cmd=fqboarddelete&sql="+sql;
	}
	function popular(){
	    var chk = document.getElementsByName("check");
	    var len =0;
	    var sql ="update fqboard set fqgrade=1 where fqno in('";
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
	    location.href="boardlist.do?cmd=fqboarddelete&sql="+sql;
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
              <th>번호</th>
              <th>타입</th>
              <th>제목</th>
              <th>작성자</th>
              <th>작성날짜</th>
              <th>조회수</th>
              <th>추천수</th>
              <th>글등급</th>
          </tr>
        </thead>
        <tbody>
        	<c:forEach var="vo" items="${requestScope.list }">
	          <tr>
	            <td>
		            <p><input type="checkbox" name="check" id="${vo.fqno }" value="${vo.fqno }" />
				    <label for="${vo.fqno }"></label></p>
			    </td>
			    <td onclick="getDetail(${vo.fqno })">${vo.fqno }</td>
			    <c:choose>
				<c:when test="${vo.fqgrade==1 }">	
				<td onclick="getDetail(${vo.fqno })">인기글</td>
				</c:when>
				<c:when test="${vo.fqgrade==2 }">	
				<td onclick="getDetail(${vo.fqno })">공지글</td>
				</c:when>
				<c:when test="${vo.fqtype==1 }">
				<td onclick="getDetail(${vo.fqno })">일반</td>			
				</c:when>
				<c:when test="${vo.fqtype==2 }">
				<td onclick="getDetail(${vo.fqno })">정보</td>			
				</c:when>
				<c:when test="${vo.fqtype==3 }">	
				<td onclick="getDetail(${vo.fqno })">질문</td>
				</c:when>
			</c:choose>
              <td onclick="getDetail(${vo.fqno })">${vo.fqtitle }</td>
              <td onclick="getDetail(${vo.fqno })">${vo.id }</td>
              <td onclick="getDetail(${vo.fqno })">${vo.fqdate }</td>
              <td onclick="getDetail(${vo.fqno })">${vo.fqhit }</td>
              <td onclick="getDetail(${vo.fqno })">${vo.recommend}</td>
              <td onclick="getDetail(${vo.fqno })">${vo.fqgrade}</td>
	          </tr>
	        </c:forEach>  
        </tbody>
      </table>
   <div class="row">
      <div class="center">
   	<ul class="pagination">
   	<c:choose>
   		<c:when test="${pageNum>5}">
   			<li class="waves-effect"><a href="boardlist.do?cmd=fqboard&pageNum=${startPage-1}&text=${requestScope.text}&select=${requestScope.select}"><i class="material-icons">chevron_left</i></a></li>
		</c:when>
		<c:otherwise>
			<li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
		</c:otherwise>
	</c:choose>    
	    <c:forEach var="i" begin="${requestScope.startPage }" end="${requestScope.endPage }">
		    <c:choose>
			    <c:when test="${pageNum==i }">
			    	<li class="active"><a href="boardlist.do?cmd=fqboard&pageNum=${i }&text=${requestScope.text}&select=${requestScope.select}">${i }</a></li>
			    </c:when>
			    <c:otherwise>
			    	<li class="waves-effect"><a href="boardlist.do?cmd=fqboard&pageNum=${i }&text=${requestScope.text}&select=${requestScope.select}">${i }</a></li>
			    </c:otherwise>
		    </c:choose>
	    </c:forEach>
	    <c:choose>
	    	<c:when test="${pageCount>endPage }">
	    		<li class="waves-effect"><a href="boardlist.do?cmd=fqboard&pageNum=${endPage+1 }&text=${requestScope.text}&select=${requestScope.select}"><i class="material-icons">chevron_right</i></a></li>
	    	</c:when>
	    	<c:otherwise>
	    		<li class="disabled"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
	    	</c:otherwise>
	    </c:choose>
	    <!-- <li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li> -->
  	</ul>
  	</div>
	  	<div style="margin-left: 1000px;"><a class="waves-effect waves-light btn" style="background-color: #ee6e73;" onclick="popular()">인기글등록</a>
	  	<a class="waves-effect waves-light btn" style="background-color: #ee6e73;" onclick="del()">게시물삭제</a></div>
	    <form class="col s12" method="post" action="<%=request.getContextPath()%>/boardlist.do?cmd=fqboard">
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
