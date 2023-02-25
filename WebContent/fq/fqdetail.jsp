<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
	#commlist{width:1350px;border:1px solid #aaa; padding:2px;margin-top: 3px;}
</style>
<script>

/* if('${result}' !=""){
	alert('${result}');
} */

function police(){
	if('${sessionScope.id}' != ""){
		location.href="fq.do?cmd=police&fqno=${vo.fqno}&id=${sessionScope.id}";
	}else{
		alert("신고기능은 로그인 된 상태에서만 가능합니다");
	}
}

function recommend(){
if('${sessionScope.id}' != ""){
	location.href="fq.do?cmd=recommend&fqno=${vo.fqno}&id=${sessionScope.id}";
	}else{
		alert("추천기능은 로그인 된 상태에서만 가능합니다");
	}
}

function fqdelete(){
	if('${sessionScope.id}' == '${vo.id}'){
	location.href="fq.do?cmd=delete&fqno=${vo.fqno}&id=${vo.id}";
	}else{
		alert("본인의 글만 삭제할 수 있습니다.");
	}
}

function fqupdate(){
	if('${sessionScope.id}' == '${vo.id}'){
	location.href="fq.do?cmd=update&fqno=${vo.fqno}";	
	}else{
		alert("본인의 글만 수정할 수 있습니다.");
	}
}


window.onload=getlist;
var xhr=null;
function getlist(){
	xhr=new XMLHttpRequest();
	xhr.onreadystatechange=list;
	xhr.open('get','fqcomment.do?cmd=list&fqno=${vo.fqno}', true);
	xhr.send();
}
function list(){
	if(xhr.readyState==4 && xhr.status==200){
		var list=xhr.responseText;
		//alert(list);
		var json=JSON.parse(list); //json객체로 변환하기(ie8이상)
		var div=document.getElementById("commlist");
		var d=document.createElement("div");
		var child=document.createElement("div");
		commlist.innerHTML="";
		for(var i=0;i<json.length;i++){
			//console.log(json[i].lev);
			if(json[i].lev>0){
				for(var j=0;j<json[i].lev;j++){
					//alert("요어어어어어");
					child.innerHTML += "	ㄴ ";
				}
			} 
			child.innerHTML += "작성자:"+json[i].id+"내용:"+json[i].comments+"<button onclick='recomm(event)'>답글</button>"+
			"<div style='display:none' ><textarea id='recomm"+i+"'></textarea><input type='button' value='등록' onclick='aaa("+i+",${vo.fqno},\""+ json[i].id +"\","+ json[i].ref+","+json[i].lev+","+json[i].step +","+json[i].fqcno+ ")'><br></div><br>";
			d.appendChild(child);
			div.appendChild(d);
			
		
		}
	}
}
var xhr2=null;
function aaa(i,fqno,id,ref,lev,step,fqcno){
	xhr2=new XMLHttpRequest();
	var aa= document.getElementById("recomm"+i).value;	
	xhr2.onreadystatechange=callback1;
	xhr2.open('post','fqcomment.do?cmd=insert',true);
	xhr2.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	var params="id="+id+"&fqcontent="+aa+"&fqcref="+ref+"&fqclev="+lev+"&fqcstep="+step+"&fqcno="+fqcno+"&fqno="+fqno;
	xhr2.send(params);
	
}
function callback1(){
	if(xhr2.readyState==4 && xhr2.status==200){
		var result=xhr2.responseText;
		//alert(result);
		var json=JSON.parse(result);
		if(json.result=="success"){
			document.getElementById("fqccontent").value="";
			getlist();
		}else{
			alert("댓글등록실패!");
		}		
	}
}
function recomm(event){
	var comm=event.target.nextSibling;
	var d=document.createElement("div");
	
	
	if(comm.style.display=="inline"){
		//alert(comm.style.display);
		comm.style.display="none";
	}else{
		//alert(comm.style.display);
		comm.style.border="2 solid black";
		comm.style.display="inline";
		
	}
	comm.appendChild(d);
}

	var xhr1=null;
	function addComm(){
		var comm=document.getElementById("fqccontent").value;
		xhr1=new XMLHttpRequest();
		xhr1.onreadystatechange=callback;
		xhr1.open('post','fqcomment.do?cmd=insert&fqcontent='+comm+'&fqno=${vo.fqno }',true);
		//post방식은경우 아래와 같이 Content-Type지정해야 함
		xhr1.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		var params="fqcontent=" + comm;
		//post방식으로 데이터를 보낼때는 send메소드를 이용한다.
		xhr1.send(params);
	}
	function callback(){
		if(xhr1.readyState==4 && xhr1.status==200){
			var result=xhr1.responseText;
			//alert(result);
			var json=JSON.parse(result);
			if(json.result=="success"){
				document.getElementById("fqccontent").value="";
				getlist();
			}else{
				alert("댓글등록실패!");
			}		
		}
	}

</script>
<table border="1" width="600" onload="getlist()">
	<tr>
		<td>작성자</td>
		<td>${vo.id }</td>
	</tr>
	<tr>
		<td>조회수</td>
		<td>${vo.fqhit }</td>
	</tr>
	<tr>
		<td>종류</td>
		<c:choose>
			<c:when test="${vo.fqtype==1 }">
				<td>일반</td>
			</c:when>
			<c:when test="${vo.fqtype==2 }">
				<td>정보</td>
			</c:when>
			<c:otherwise>
				<td>질문</td>
			</c:otherwise>
		</c:choose>
	</tr>
	<tr>
		<td>신고누적</td>
		<td>${police }</td>	
	</tr>
	<tr>
		<td>추천</td>
		<td>${recommend }</td>	
	</tr>
	<tr>
		<td>작성날짜</td>
		<td>${vo.fqdate }</td>
	</tr>
	<tr>
		<td>제목</td>
		<td>${vo.fqtitle }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>${vo.fqcontent }</td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="fq.do?cmd=fqList">목록으로</a>
		</td>
	</tr>
</table>
	<div class="fixed-action-btn horizontal">
    <a class="btn-floating btn-large red">
      <i class="large material-icons">menu</i>
    </a>
    <ul>
      <li><a class="btn-floating blue" onclick="fqupdate()"><i class="material-icons">border_color</i></a></li>
      <li><a class="btn-floating red"  onclick="fqdelete()" ><i class="material-icons">delete</i></a></li>
       <li><a class="btn-floating green" onclick="recommend()"><i class="material-icons">thumb_up</i></a></li>
      <li><a class="btn-floating purple" onclick="police()"><i class="material-icons">thumb_down</i></a></li>
    </ul>
  </div>
<div >
		<div id="commlist"></div>
		<div id="commAdd">
			<textarea rows="3" cols="30" id="fqccontent"></textarea>
			<input type="button" value="등록" onclick="addComm()">
		</div>
</div>