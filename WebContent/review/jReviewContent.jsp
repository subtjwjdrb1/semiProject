<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
#rcomment{
     border-bottom: 1px solid #993333;
     box-shadow: 0 1px 0 0 #993333;
   }
</style>
<script>
<<<<<<< HEAD
 if('${param.result}'!=""){alert('${param.result}');}
=======
if('${param.result}'!=""){alert('${param.result}');}
>>>>>>> branch 'jg1' of https://github.com/SeoJeongGyu/semiproject.git
if('${result}' !=""){
	alert('${result}');
<<<<<<< HEAD
} 
=======
}
>>>>>>> branch 'jg1' of https://github.com/SeoJeongGyu/semiproject.git

function rupdate(){
	if('${sessionScope.id}' == '${vo.id}'){
	document.frm.submit();

	}else{
		alert("본인의 글만 수정할 수 있습니다.");
	}
}

function rdelete(){
if('${sessionScope.id}' == '${vo.id}'){
location.href="review.do?cmd=delete&rno=${vo.rno}&id=${vo.id}";
	
}else{
	alert("본인의 글만 삭제할 수 있습니다.");
}

}

function recommend(){
if('${sessionScope.id}' != ""){
	location.href="review.do?cmd=recommend&rno=${vo.rno}&id=${sessionScope.id}";
	}else{
		alert("추천기능은 로그인 된 상태에서만 가능합니다");
	}
}
function btnblock(rcno){
    var rcomment1 = document.getElementById(rcno);
    var btn1 = document.getElementById("btn1"+rcno);
    rcomment1.style.display="block";
    btn1.style.display="block";
    rcomment1.style.color="#993333";
    rcomment1.style.borderBottom="1px solid #993333";
    rcomment1.style.boxShadow="0 1px 0 0 #993333";
    btn1.style.backgroundColor="#993333";
    btn1.style.marginLeft="10px";
    btn1.style.width="150px";
}

function rcomment(rcno,rcref,rclev,rcstep,rcreport){
    if(rcno==null){
        var rcno=0;
        var rcref=0;
        var rclev=0;
        var rcstep=0;
        var rcreport=0;
    }
    if('${sessionScope.id}'==null||'${sessionScope.id}'==""){
        alert("로그인하세요.");
        return;
    }
    console.log("id : "+'${sessionScope.id}');
    var rcomment = document.getElementById("rcomment").value;
    var rcomment1=null;
    if(rcomment==""){
        rcomment1 = document.getElementById(rcno).value;
		location.href="review.do?cmd=rcomment&rcomment="+rcomment1+"&id=${sessionScope.id}&rno=${vo.rno}&rcno="+rcno+"&rcref="+rcref+"&rclev="+rclev+"&rcstep="+rcstep+"&rcreport="+rcreport;
    }else{
		location.href="review.do?cmd=rcomment&rcomment="+rcomment+"&id=${sessionScope.id}&rno=${vo.rno}&rcno="+rcno+"&rcref="+rcref+"&rclev="+rclev+"&rcstep="+rcstep+"&rcreport="+rcreport;
    }
}

function police(){
	if('${sessionScope.id}' != ""){
		location.href="review.do?cmd=police&rno=${vo.rno}&id=${sessionScope.id}";
		}else{
			alert("신고기능은 로그인 된 상태에서만 가능합니다");
		}
	}
	
	function scrap(){
		if('${sessionScope.id}' != ""){
		location.href="mypage.do?cmd=scrap&rno=${vo.rno}&id=${sessionScope.id}&rtitle=${vo.rtitle}";
		alert("스크랩하였습니다.");
		}else{
				alert("로그인 후 스크랩기능을 이용해주세요.");
			}
		
	}
</script>

<div id="wrap">

	<form id="frm"  name="frm" action="review.do?cmd=update&rno=${vo.rno}" method="post">
		<!--  상단 작성자, 작성일, 조회수 -->
		<div
			style="overflow: hidden; padding-bottom: 5px; border-bottom: 2px solid #4B4C51">
			<br>
		</div>
		<div style="overflow: hidden; padding: 10px 0px 10px 0px;">
			<div style="margin-left: 15px; width: 12%; float: left; overflow: hidden; padding: 5px;">
				<img src="image/ico_point.gif"><span>작성자&nbsp; <span>${vo.id }</span></span>
			</div>
			<div style="width: 33%; float: left; overflow: hidden; padding: 5px">
				<span><img src="image/ico_point.gif">등록일&nbsp;
					<span>${vo.rdate }</span><span>&nbsp;&nbsp;</span><img src="image/ico_point.gif">&nbsp;조회수 &nbsp;<span>${vo.rhit }</span>
					<span>&nbsp;&nbsp;</span><img src="image/ico_point.gif">&nbsp;추천수 &nbsp;<span>${recommend }</span>
					<span>&nbsp;&nbsp;</span><img src="image/ico_point.gif">&nbsp;신고누적:<span>${police}</span>
					</span>
			</div>
			
		</div>
		<div style="overflow: hidden; padding-bottom: 5px; border-bottom: 2px solid #4B4C51"></div>

<!--  제목 보여주기 -->
<div id="title">
<h3 align="center">${vo.rtitle }</h3>
</div>

		<!-- 내용보여주기  -->
<div id="content" style="margin-left: 250px; ">${vo.rcontent }</div>

		<!--  수정, 삭제, 추천-->
<div class="fixed-action-btn horizontal">
    <a class="btn-floating btn-large red">
      <i class="large material-icons">menu</i>
    </a>
    <ul>
      <li><a class="btn-floating blue" onclick="rupdate()"><i class="material-icons">border_color</i></a></li>
      <li><a class="btn-floating red"  onclick="rdelete()" ><i class="material-icons">delete</i></a></li>
      <li><a class="btn-floating green" onclick="recommend()"><i class="material-icons">thumb_up</i></a></li>
      <li><a class="btn-floating purple" onclick="police()"><i class="material-icons">thumb_down</i></a></li>
       <li><a class="btn-floating yellow" onclick="scrap()"><i class="material-icons">star</i></a></li>
    </ul>
  </div>
		<!-- 댓글기능  -->
	
		<div id="bottom"></div>
	<%-- <input type="hidden" value="${vo.rtitle }" name="rtitle">
	<input type="hidden" value="${vo.rcontent }" name="rcontent"> --%>
	<br>
	<br>
	<br>

	</form>
	 <div class="divider"></div>
	 <br>
	<br>
	<br>
	<br>
	<br>
      <div class="row">
        <div class="input-field col s6" >
          <textarea id="rcomment" class="materialize-textarea" style="color: #993333;"></textarea>
          <label for="rcomment" style="color: #993333;">댓글</label>
        </div>
          <a class="waves-effect waves-light btn" style="margin-top: 60px; background-color: #993333;" href="javascript:rcomment()">댓글등록</a>
      </div>
	      <ul class="collection with-header">
      <c:forEach var="list" items="${requestScope.rclist }">
      		<c:choose>
      			<c:when test="${list.rclev==0}">
	        <li class="collection-item"><div>작성자 : ${list.id} <br> 내용 : ${list.rccontent }<a href="javascript:btnblock(${list.rcno})" class="secondary-content"><i class="material-icons" style="color: #993333;"">send</i></a></div></li>
	        	</c:when>
	        	<c:otherwise>
	        	<li class="collection-item" style="margin-left: 100px;"><div>작성자 : ${list.id} <br> 내용 : ${list.rccontent }</div></li>
	        	</c:otherwise>
      		</c:choose>
      	<div class="row">
        <div class="input-field col s6" >
          <textarea id="${list.rcno}" class="materialize-textarea" style=" display: none;"></textarea>
        </div>
          <a id="btn1${list.rcno}" class="waves-effect waves-light btn" style="display:none;" href="javascript:rcomment(${list.rcno },${list.rcref },${list.rclev },${list.rcstep },${list.rcreport })">댓글등록</a>
      </div>
      </c:forEach>
	      </ul>
</div>
