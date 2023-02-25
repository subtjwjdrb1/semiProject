<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(document).ready(function() {
	  $('select').material_select();
	});
	
	window.onload=function(){
	    var select = document.getElementsByName("select")[0];
	    console.log('${requestScope.select}');
	    select.selectedIndex='${requestScope.select}';
	}
</script> 

<div id="wrap" >
<div style="display: none;">
	<table>
	<tr><td><ul style="overflow:hidden;border:1px solid #D4D4D4; width:362px;">
	<li style="float: left; overflow: hidden; width: 89px; border-right: 1px solid rgb(212, 212, 212);">	</li>
	<li id="apple" style="float: left; overflow: hidden; width: 89px; border-right: 1px solid rgb(212, 212, 212); text-align: center; padding: 15px 0px; cursor: pointer; background-color: rgb(255, 255, 255);"><input type="checkbox" name="m[12]" id="m12" value="12" style="display:none"><img src="http://image3.cetizen.com/2007_cetizen/market/2015/icon/apple.png" id="market_tap4">
			<span style="display: block; margin-top: 5px; color: rgb(75, 76, 81);">애플</span>
		</li>
		<li id="samsung" style="float: left; overflow: hidden; width: 93px; border-right: 1px solid rgb(212, 212, 212); text-align: center; padding: 35px 0px 15px; cursor: pointer; background-color: rgb(255, 255, 255);"><input type="checkbox" name="m[1]" id="m1" value="1" style="display:none"><img src="http://image3.cetizen.com/2007_cetizen/market/2015/icon/samsung.png" id="market_tap5">
			<span style="display: block; margin-top: 17px; color: rgb(75, 76, 81);">삼성</span>
		</li>
		<li id="lg" style="float: left; overflow: hidden; width: 89px; border-right: 1px solid rgb(212, 212, 212); text-align: center; padding: 15px 0px; cursor: pointer; background-color: rgb(255, 255, 255);"><input type="checkbox" name="m[2]" id="m2" value="2" style="display:none"><img src="http://image3.cetizen.com/2007_cetizen/market/2015/icon/lg.png" id="market_tap6">
			<span style="display: block; margin-top: 5px; color: rgb(75, 76, 81);">LG</span>
		</li>
		<li id="etc" style="float: left; overflow: hidden; width: 89px; border-right: 1px solid rgb(212, 212, 212); text-align: center; padding: 15px 0px; cursor: pointer; background-color: rgb(255, 255, 255);"><img src="http://image3.cetizen.com/2007_cetizen/market/2015/icon/2g.png" id="market_tap7">
			<span style="display: block; margin-top: 5px; color: rgb(75, 76, 81);">기타</span>
		</li>
		
	</ul>
	</td>
	</tr>
	</table>
</div>

<br><br><br>



<!--  리스트 이미지와 내용 보여주는 div-->
 <div style="width:1350px; height:1000px;">
 
<c:forEach var="review" items="${requestScope.rlist}">

<div style="clear:both;float:left;margin-top:15px;height:140px;text-align:center;">

<img src="image/${review.savefilename}" style="width:210px; border:1px solid #F0F0F0" >
</div>
<div style="float:left;overflow:hidden;width:585px; height:200px;margin-top:20px;margin-left:10px;">
	<div style="width:98%;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;padding-bottom:2px;"><a href="/semiProject/review.do?cmd=content&rno=${review.rno}"><span class="p16 clr100 b"> ${review.rtitle}</span></a></div>
	<div style="width:98%;margin-top:15px;height:50px; height:110px;overflow:hidden;" onclick="location.href='/semiProject/review.do?cmd=content&rno=${review.rno}'">
		<span>${review.rcontent}</span>
		</div>
		<br>
	<div style="margin-top:10px;overflow:hidden;padding-bottom:2px;" class="p12 clr02">
	등록 : ${review.rdate}&nbsp;&nbsp;<span class="clr06">| 조회수:</span>&nbsp;&nbsp;${review.rhit}	</div>
<br>

<div style="margin-top:5px;height:1px;background-color:#E7E7E7;width:999%;"></div> 
			</div>

</c:forEach>

</div>
<br>

<div class="center" style="clear:both" style="margin-left: 250px;">
   	<ul class="pagination">
   	<c:choose>
	<c:when test="${startPage>5 }">
	<li class="waves-effect"><a href="<%=request.getContextPath() %>/review.do?cmd=list&pageNum=${startPage-1 }&text=${requestScope.text}&select=${requestScope.select}"><i class="material-icons">chevron_left</i></a></li>
	</c:when>
	<c:otherwise>
	    <li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
	    	</c:otherwise>
</c:choose>

<c:forEach var="i" begin="${startPage }"  end="${endPage }">
<c:choose>
			<c:when test="${pageNum==i }">
	    <li class="active"><a href="<%=request.getContextPath()%>/review.do?cmd=list&pageNum=${i}&text=${requestScope.text}&select=${requestScope.select}" style="background-color: #993333;">${i}</a></li>
	 	</c:when>
	 <c:otherwise>
			<li class="waves-effect"><a href="<%=request.getContextPath()%>/review.do?cmd=list&pageNum=${i}&text=${requestScope.text}&select=${requestScope.select}">${i }</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<c:choose>
	<c:when test="${endPage<pageCount }">
	    <li class="waves-effect"><a href="<%=request.getContextPath() %>/review.do?cmd=list&pageNum=${endPage+1 }&text=${requestScope.text}&select=${requestScope.select}"><i class="material-icons">chevron_right</i></a></li>
  	</c:when>
	<c:otherwise>
	<li class="disabled"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
  	</c:otherwise>
</c:choose>
  	</ul>
 </div>
 
     <a class="btn-floating btn-large red" href="<%=request.getContextPath() %>/review.do?cmd=write" style="margin-left:1200px;" >
      <i class="large material-icons">mode_edit</i>
    </a>
	<form class="cols12" method="post" action="<%=request.getContextPath()%>/review.do?cmd=list">
      <div class="row" style=" clear:both; margin-left: 500px;">
      <div class="input-field col s12" style="width: 100px; margin-left: 10px;">
        <select name="select">
      <option value="0">제목</option>
      <option value="1">내용</option>
      <option value="2">아이디</option>
    </select>
      </div>
        <div class="input-field col s3">
          <input id="text" name="text" type="text" class="validate" value="${requestScope.txt }">
        </div>
      <button class="btn waves-effect waves-light" type="submit" name="action" style="margin-top: 25px; background-color: #993333">검색</button>
     
      </div>
      </form>
      </div>
     