<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/home/api.jsp"></jsp:include>

<style>
	#id,#pwd,#rpwd,#name,#nickname,#phone,#email{
		border-bottom:1px solid #993333; 
		box-shadow:0 1px 0 0 #993333;
	}
</style>
<script type="text/javascript">
	var reg_pw = /^[a-z0-9_-]{6,20}$/;
	var reg_email = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	var reg_name = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{2,}/;
	var reg_nickname = /[0-9a-zA-Zㄱ-ㅎ|ㅏ-ㅣ|가-힣]{2,10}/;
	var reg_phone = /^\d{3}\d{3,4}\d{4}$/;
	var birthday = /^\d{2}\/\d{2}\/\d{2}$/;
	if('${requestScope.result}'!=""){
		alert('${requestScope.result}');
	}
	function checkJoin(){
	    console.log("안녕");
	    var pwd = document.getElementById("pwd");
	    var rpwd = document.getElementById("rpwd");
	    var name = document.getElementById("name");
	    var nickname = document.getElementById("nickname");
	    var phone = document.getElementById("phone");
	    var email = document.getElementById("email");
	  	if (pwd.value.length<5 || pwd.value.length>20) {
			alert("비밀번호는 6자이상 20자 이하로  입력하세요.");
		} else if (pwd.value != rpwd.value) {
			alert("비밀번호와 비밀번호확인이 일치하지 않습니다.");
		} else if (!reg_name.test(name.value)) {
			alert("이름을 한글로 2자 이상 입력해 주세요");

		} else if (!reg_nickname.test(nickname.value)) {
			alert("닉네임을 2자이상 10자이하 입력해 주세요");

		} else if (!reg_phone.test(phone.value)) {
			alert("폰넘버를 확인하세요. \n ex)01012345678");

		} else if (!reg_email.test(email.value)) {
			alert("이메일 주소가 유효하지 않습니다 \n ex)ppumppo@hta.com");

		} else {
			 document.frm.submit();
		}
	}
	
</script>
   	<div id="mypage">
		<jsp:include page="/mypage/myPageMain.jsp"></jsp:include>
	</div>
	
<div class="row" style="margin-left: 420px;">
<c:forEach var="member" items="${requestScope.list}">
    <form class="col s12" method="post" action="<%=request.getContextPath()%>/member.do?cmd=update" name="frm">
      <div class="row">
        <div class="input-field col s3" style="margin-top: 50px;">  
          <input disabled id="id" name="idd" type="text" class="validate" data-length="15" onkeyup="checkId()"><div id="checkId" style="color:#993333;"></div>
          <label for="id" style="font-size: 20px; color:#993333; ">아이디 : ${sessionScope.id}</label>
          <input type="hidden"  name="id"  value="${sessionScope.id}">
        </div>
       </div>
       <div class="row">
          <div class="input-field col s3">
          <input id="pwd"  name="pwd" type="password" class="validate" data-length="20">
          <label for="pwd" style="font-size: 20px; color:#993333; ">패스워드</label>
        </div>
      </div>
       <div class="row">
          <div class="input-field col s3">
          <input id="rpwd" type="password" class="validate" data-length="20">
          <label for="rpwd" style="font-size: 20px; color: #993333;" >패스워드확인</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s3">
          <input id="name" name="name" type="text" class="validate" value="${member.name}">
          <label for="name" style="font-size: 20px; color:#993333;">이름</label>
        </div>
        <div class="input-field col s3">
          <input id="nickname" name="nickname" type="text" class="validate" data-length="10" value="${member.nickname}">
          <label for="nickname" style="font-size: 20px; color:#993333;">닉네임</label>
        </div>
       </div>
      <div class="row">
        <div class="input-field col s3">
          <input id="phone" name="phone" type="text" placeholder="ex)01012345678" class="validate" data-length="11" value="${member.phone}">
          <label for="phone" style="font-size: 20px; color:#993333;">폰번호</label>
        </div>
       </div>
      <div class="row">
        <div class="col s6">
          <div class="input-field inline">
            <input id="email" name="email" placeholder="ex)ppumppo@hta.com" type="email" class="validate" style="width: 450px; font-size: 20px;" value="${member.email}">
            <label for="email" style="font-size: 20px; color:#993333;" data-error="올바르지 않은 이메일 형식입니다." data-success="사용할 수 있는 이메일 입니다.">Email</label>
          </div>
        </div>
      </div>
      <a class="waves-effect waves-light btn" onclick="checkJoin()" style="margin-left:20px; background-color:#993333; color: white;">수정하기</a>
    </form>
    </c:forEach>
  </div>