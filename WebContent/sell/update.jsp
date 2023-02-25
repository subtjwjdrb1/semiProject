<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
	/* label color */
   .input-field label {
     color: #993333;
   }
   /* label focus color */
   .input-field input[type=text]:focus + label {
     color: #993333;
   }
   
   .row{width: 860px;}

</style>
<br>
<div class="row">
   <form class="col s12" method="post" action="/semiProject/sell.do?cmd=updateOk&sno=${vo.sno }">
	<div class="input-field col s12">
	    <select name="os" class="select" id="aa">
	    <option value="os" disabled selected>선택하세요</option>
	      <option value="1">ios</option>
	      <option value="2">안드로이드</option>
	    </select>
	    <label>OS</label>
	</div>
	<div class="input-field col s12" class="select">
	    <select name="telecom" >
	      <option value="telecom" disabled selected>선택하세요</option>
	      <option value="1">SKT</option>
	      <option value="2">KT</option>
	      <option value="3">LG U+</option>
	      <option value="4">기타</option>
	    </select>
	    <label>통신사</label>
	</div>
	<div class="input-field col s12">
	    <select name="company" class="select" >
	      <option value="company" disabled selected>선택하세요</option>
	      <option value="1">삼성</option>
	      <option value="2">LG</option>
	      <option value="3">애플</option>
	      <option value="4">기타</option>
	    </select>
	    <label>제조사</label>
	</div>
	<div class="input-field col s12">
	    <select name="success" class="select" >
	      <option value="success" disabled selected>선택하세요</option>
	      <option value="1">판매중</option>
	      <option value="2">판매완료</option>
	    </select>
	    <label>거래상태</label>
	</div>
    <div class="row">
        <div class="input-field col s12">
          <input name="loc" type="text" class="validate" value="${vo.loc }">
          <label for="loc">거래장소</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12">
          <input name="price" type="text" class="validate" value="${vo.price }">
          <label for="price">가격</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12">
          <input name="stitle" type="text" class="validate" value="${vo.stitle }">
          <label for="stitle">제목</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12">
          <label for="scontent">내용</label> <br><br>
          <textarea name="scontent" id="materialnote">${vo.scontent }</textarea>
        </div>
    </div>
      
      <button class="btn waves-effect waves-light" type="submit" onclick="supdate()" style="background-color:#993333; margin-left:340px; width: 200px">
      	<i class="large material-icons">done</i></button>
   </form>
  </div>
<script type="text/javascript">

function supdate(){
	if('${result}' == 'fail'){
		alert("수정사항이 올바르지 않습니다");
	}
}

    //var os = document.getElementsByName("os")[0];
    var os = document.getElementsByName("os")[0];
    os.selectedIndex = '${vo.os }';
    
    var telecom=document.getElementsByName("telecom")[0];
    telecom.selectedIndex='${vo.telecom }';
    
    var company=document.getElementsByName("company")[0];
    company.selectedIndex='${vo.company }';
    
    var success=document.getElementsByName("success")[0];
    success.selectedIndex='${vo.success }';
    
    
	$(document).ready(function() {
	    $('#materialnote').materialnote({
	        width: 860,
	        height: 300
	    });
	});
	$(document).ready(function() {
	$('#materialnote').materialnote();
	});
	$(document).ready(function() {
		    $('select').material_select();
	});
</script>
