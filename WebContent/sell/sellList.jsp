<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
  $(document).ready(function() {
    $('select').material_select();
  });
  
  function getDetail(sno){
	  //console.log(sno);
	  location.href="sell.do?cmd=sdetail&sno="+sno;
  }
  
  var xhr=null;
  function getSql(){
	  var os=document.getElementsByName("os");  
	  var telecom=document.getElementsByName("telecom");  
	  var company=document.getElementsByName("company");  
	  var sql="";  
	  var chk=0;
	  
  	  var ocnt=0;
  	  var tcnt=0;
	  var ccnt=0;
	  
	  for(var i=0; i<os.length;i++){
		 if(os[i].checked==true){
			 chk++;
			 if(chk>1){
			 	sql += " or os=" + os[i].value;
			 	ocnt++;
			  }else if(chk==1){
				sql += "(os=" + os[i].value;
				ocnt++;
			  }
		 }
	  }
	  if(ocnt>0){
		  sql+=")";
	  }
	  
	  var tcount=0;
	  for(var i=0; i<telecom.length;i++){
		 if(telecom[i].checked==true){
			 chk++;
			 if(ocnt==0){
				 if(chk>1){
					 sql += " or telecom=" + telecom[i].value ;
					 tcnt++;
				 }else if(chk==1){
			     	  sql += "(telecom=" + telecom[i].value;
			     	  tcnt++;
				 }
			 }else{
				 if(tcount==0){
					 sql += " and (telecom=" + telecom[i].value;
					 tcnt++;
					 tcount++;
				 }else{
					 sql += " or telecom=" + telecom[i].value;
					 tcnt++;
				 }
			 }
		 }
	 }
	  if(tcnt>0){
		  sql+=")";
	  }
	  
	  var ccount=0;
	  for(var i=0; i<company.length;i++){
			 if(company[i].checked==true){
				 chk++;
				 if(tcnt==0 && ocnt==0){
					 if(chk>1){
						 sql += " or company=" + company[i].value;
						 ccnt++;
					 }else if(chk==1){
				     	  sql += "(company=" + company[i].value;
				     	 ccnt++;
					}
				 }else{
					 if(ccount==0){
						 sql += " and (company=" + company[i].value;
						 ccnt++;
						 ccount++;
					 }else{
				     	  sql += " or company=" + company[i].value;
				     	 ccnt++;
					}
				 }
			 }
		 }
	  if(ccnt>0){
		  sql+=")";
	  }	  
	  
	  if(chk<=0){
	  //alert(chk);
		sql="";
	  }
	  
	  console.log("sql 작성중 : " + sql);
	  return sql;
  }
  function getCheck(){
	  var sql=getSql();
	  getlist(sql);
  }
   function getlist(sql){
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=callback;
		xhr.open('get','sell.do?cmd=sellList&sql='+sql +'&type=ajax', true);
		xhr.send();
  }
  function callback(){
	  if(xhr.readyState==4 && xhr.status==200){
		  var result=xhr.responseText;
		  //alert(text);
		 var json=JSON.parse(result);
		 var div=document.getElementById("sellList");
		 
		 var html = "<table class='highlight'>"+
	        "<thead>"+
	         "<tr>"+
	         "<th>판매상태</th>"+
	         "<th>제목</th>"+
	         "<th>가격</th>"+
	         "<th>작성자</th>"+
	         " </tr>"+
	        " </thead>"+
	        "<tbody>";
		 
		 for(var i=0;i<json.list.length;i++){
			 html += "<tr onclick='getDetail("+json.list[i].sno+")'>";
				if(json.list[i].success==1){
					html += "<td>판매중</td>";
				}else{
					html+= "<td>판매완료</td>";
				}
				html += "<td><a href='sell.do?cmd=sdetail&sno="+json.list[i].sno+"'>"+json.list[i].title+"</a></td>"+
					"<td>"+json.list[i].price+"</td>"+
					"<td>"+json.list[i].id+"</td>"+
					"</tr>";
		 }
		 html += "</tbody></table>"+
		 "<div class='row'><br><ul class='pagination'>";
		 
		 if(json.startPage>5){
			 html += "<li class='disabled'><a href='sell.do?cmd=sellList&pageNum="+ json.startPage-1 +"'>"+
			 "<i class='material-icons'>chevron_left</i></a></li>";
		 }
		 for(var i=json.startPage;i<=json.endPage;i++){
			 if(json.pageNum==i){
				 html += "<li class='active'><a href='javascript:aa(" + i + ")'>"+i+"</a></li>";
			 }else{
				 html += "<li class='waves-effect'><a href='javascript:aa(" + i + ")'>"+i+"</a></li>";
			 }
		 }
		 if(json.pageCount>json.endPage ){
			 html += "<li class='waves-effect'><a href='sell.do?cmd=sellList&pageNum="+ json.endPage+1 +"'>"+
			 "<i class='material-icons'>chevron_right</i></a></li>";
		 }
		 div.innerHTML = html + "<a class='waves-effect waves-light btn' href='/semiProject/sell.do?cmd=insert' style='background-color:#993333;margin-left: 1200px;'>"+
		   "<i class='material-icons' >create</i></a></ul></div>";		 
	  }
  }
  
  function aa(pageNum){
	//  alert(pageNum)
	  var sql=getSql();
	  document.getElementById("pageNum").value=pageNum;
	  document.getElementById("sql").value=sql;
	  document.frm.submit();
	// alert("오긴옴");

  }
  
</script>
<form method="post" action="<%=request.getContextPath() %>/sell.do?cmd=sellList&type=form" name="frm">
<input type="hidden" id="pageNum" name="pageNum" >
<input type="hidden" id="sql" name="sql" >
<table border="1" style="width:650px;margin-left:0px ">
		<br>
		<tr>
		<th>운영체제 </th>
		<td>	
		<c:choose>
			<c:when test="${requestScope.os1==1}">
					<input type="checkbox" id="ios" name="os" value="1" onclick="getCheck()" checked="checked">
			</c:when>
			<c:otherwise>
				<input type="checkbox" id="ios" name="os" value="1" onclick="getCheck()">
			</c:otherwise>
		</c:choose>
		<label for="ios">ios</label>
		</td>
		
		<td style="width:150px">
		<c:choose>
			<c:when test="${requestScope.os2==2 }">
				<input type="checkbox" id="android" name="os" value="2" onclick="getCheck()" checked="checked" >
			</c:when>
			<c:otherwise>
				<input type="checkbox" id="android" name="os" value="2" onclick="getCheck()" >
			</c:otherwise>
		</c:choose>
		<label for="android">안드로이드</label></td>
	 	</tr>	
	 	<tr>
	 	<th>통신사 </th>
		<td>
		
		<c:choose>
			<c:when test="${requestScope.telecom1==1 }">
				<input type="checkbox" id="skt" name="telecom" value="1" onclick="getCheck()" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input type="checkbox" id="skt" name="telecom" value="1" onclick="getCheck()"/>
			</c:otherwise>
		</c:choose>
		<label for="skt">SKT</label></td>
		<td>
		<c:choose>
			<c:when test="${requestScope.telecom2==2 }">
				<input type="checkbox" id="kt" name="telecom" value="2" onclick="getCheck()" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input type="checkbox" id="kt" name="telecom" value="2" onclick="getCheck()"/>
			</c:otherwise>
		</c:choose>
		<label for="kt">KT</label></td>
		<td>
		<c:choose>
			<c:when test="${requestScope.telecom3==3 }">
				<input type="checkbox" id="lgu+" name="telecom" value="3" onclick="getCheck()" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input type="checkbox" id="lgu+" name="telecom" value="3" onclick="getCheck()"/>
			</c:otherwise>
		</c:choose>
		<label for="lgu+">LGU+</label></td>
		<td>
		<c:choose>
			<c:when test="${requestScope.telecom4==4 }">
				<input type="checkbox" id="tetc" name="telecom" value="4" onclick="getCheck()" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input type="checkbox" id="tetc" name="telecom" value="4" onclick="getCheck()"/>
			</c:otherwise>
		</c:choose>
		<label for="tetc">기타</label></td>
	 	</tr>
	 	<tr>
	 	<th>브랜드 </th>
		<td>
		<c:choose>
			<c:when test="${requestScope.company1==1 }">
				<input type="checkbox" id="samsung" name="company" value="1" onclick="getCheck()" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input type="checkbox" id="samsung" name="company" value="1" onclick="getCheck()"/>
			</c:otherwise>
		</c:choose>
		<label for="samsung">삼성</label></td>
		<td>
		<c:choose>
			<c:when test="${requestScope.company2==2 }">
				<input type="checkbox" id="lg" name="company" value="2" onclick="getCheck()" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input type="checkbox" id="lg" name="company" value="2" onclick="getCheck()"/>
			</c:otherwise>
		</c:choose>
		<label for="lg">LG</label></td>
		<td>
		<c:choose>
			<c:when test="${requestScope.company3==3 }">
				<input type="checkbox" id="apple" name="company" value="3" onclick="getCheck()" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input type="checkbox" id="apple" name="company" value="3" onclick="getCheck()"/>
			</c:otherwise>
		</c:choose>
		<label for="apple">애플</label></td>
		<td>
		<c:choose>
			<c:when test="${requestScope.company4==4 }">
				<input type="checkbox" id="cetc" name="company" value="4" onclick="getCheck()" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input type="checkbox" id="cetc" name="company" value="4" onclick="getCheck()"/>
			</c:otherwise>
		</c:choose>
		<label for="cetc">기타</label></td>
	 	</tr>
</table>
    <br> 
<div class="main" id="sellList">
 <table class="highlight">
        <thead>
          <tr>
              <th>판매상태</th>
              <th>제목</th>
              <th>가격</th>
              <th>작성자</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="sell" items="${requestScope.slist }">
			<tr onclick="getDetail(${sell.sno })">
			<c:choose>
				<c:when test="${sell.sgrade==2 }">			
					<td>공지글</td>
				</c:when>
				<c:when test="${sell.success==1 }">			
					<td>판매중</td>
				</c:when>
				<c:otherwise>
					<td>판매완료</td>
				</c:otherwise>
			</c:choose>
				<td><a href="sell.do?cmd=sdetail&sno=${sell.sno }">${sell.stitle }</a></td>
				<td>${sell.price }</td>
				<td>${sell.id }</td>
			</tr>
		</c:forEach>
        </tbody>
      </table> 
  <div class="row">   
  <br>  
<ul class="pagination">
<c:if test="${startPage>5 }">
	<li class="disabled"><a href="sell.do?cmd=sellList&pageNum=${startPage-1 }"><i class="material-icons">chevron_left</i></a></li>
</c:if>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${pageNum==i }">
				<li class="active"><a href="javascript:aa('${i}')" style="background-color:#993333;">${i}</a></li>
			</c:when>
			<c:otherwise>
			<li class="waves-effect"><a href="javascript:aa('${i}')">${i}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
<c:if test="${pageCount>endPage }">
<li class="waves-effect"><a href="sell.do?cmd=sellList&pageNum=${endPage+1 }"><i class="material-icons">chevron_right</i></a></li>
</c:if>
</ul>
</div>
    <a class="waves-effect waves-light btn" href="/semiProject/sell.do?cmd=insert" style="background-color:#993333;margin-left: 1200px;">
    <i class="material-icons" >mode_edit</i></a>
    <br>
  </div>
  <br>
  </form>