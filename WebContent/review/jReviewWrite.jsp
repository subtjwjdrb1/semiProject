<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<center>
<script>
$(document).ready(function() {
    Materialize.updateTextFields();
  });</script>
  
  <script>
  function fileUpload(){
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = fileupload;
	xhr.open('get','<%=request.getContextPath() %>/review/jReviewWrite.jsp',true);
	xhr.send();
  }
  function fileupload(){
	  if(xhr.readyState ==4 && xhr.status ==200){
		 var div=document.getElementById("file");
		 div.style.display="block";
	  }
  }
  
  $(document).ready(function() {
	    $('select').material_select();
	  });
  
  
  </script>
<!-- 전체를 감싸는 div -->
<div id="write" style="width: 1000px; height: 800px; margin-top: 100px;">

    <!-- 사진 등록 AJAX div  -->
      <div align="left">
   <label for="id" style=" font-size: 20px;">대표&nbsp;사진등록</label>  <a id="scale-demo" class="btn-floating blue pulse" onclick="fileUpload()">
    <i class="material-icons">add_a_photo</i>
  </a>				<!-- Scaled in -->

      </div>

    <br>
   
    <!-- 전체 form  -->
    <form class="cols12" action="<%=request.getContextPath() %>/review.do?cmd=writeOk" method="post" enctype="multipart/form-data">
    
    <!-- 파일첨부하는 div  -->
     <div id="file"  style="display:none;">
      <div class="file-field input-field">
      <div class="btn" style="width:100px;height:40px;">
        <span>첨부</span>
 		<input type="file" name="file">
 		
      </div>
      <div class="file-path-wrapper">
        <input class="file-path validate" type="text"  style="font-size: 30px; width:500px;margin-right:500px;">
      </div>
    </div>
    
    </div>
  
	      <div class="row">
	  <div class="input-field col s2" class="select">
	    <select name="company"  style=" width: 400px; height: 60px; ">
	      <option value="company" disabled selected>선택하세요</option>
	      <option value="1">애플</option>
	      <option value="2">삼성</option>
	      <option value="3">LG</option>
	      <option value="4">기타</option>
	    </select>
	    <label style="font-size: 15px;">제조사</label>
	  </div>
      </div>
      <div class="row">
        <div class="input-field col  s6">
          <input name ="title" type="text" class="validate" style=" width: 460px; font-size:30px; height: 60px;">
          <label for="title" style="font-size: 15px; ">제목</label>
        </div>
      </div>
 	 <jsp:include page="/home/summernote.jsp"></jsp:include>
 	<br>
  	<button class="btn waves-effect waves-light" type="submit" style=" width:140px; height:50px;">전송
    <i class="material-icons right">send</i>
  </button>
  
    </form>
  </div>
  
  
  </center>

