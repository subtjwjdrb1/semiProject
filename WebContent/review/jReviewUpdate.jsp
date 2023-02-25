<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
  <script>
  $(document).ready(function() {
	    $('select').material_select();
var note=document.getElementById("materialnote");
note.value='${vo.rcontent}';
	 });
  
  </script>
  <script type="text/javascript">

  function handleFileSelect() 
  {
      var files = document.getElementById('up_files').files[0]; //파일 객체

      var reader = new FileReader();

           
      reader.onload = (function(theFile) 
      {
          return function(e) 
          {
              var img_view = ['<img src=" ', e.target.result, ' " title=" ', escape(theFile.name), ' " style="width:210px;height:140px;"/>'].join('');                
              document.getElementById('list').innerHTML = img_view;
          };
      })(files);
      reader.readAsDataURL(files);    
  }

</script>
<center>

<!-- 전체를 감싸는 div -->
<div id="write" style="width: 1000px; height: 1000px; margin-top: 100px;">
  <!-- 전체 form  -->
    <form class="cols12" action="<%=request.getContextPath() %>/review.do?cmd=updateOk&rno=${vo.rno}" method="post" enctype="multipart/form-data">
<div class="row">
<h5>대표사진변경</h5>
    <!-- 사진 등록 AJAX div  -->
<div id="list" style="clear:both;float:left;margin-top:15px; overflow:hidden; width:210px;height:140px;text-align:center;">
<img src="image/${vo.savefilename}"style="width:210px; border:1px solid #F0F0F0" >
</div>
<br><br><br><br><br>
<div id="file" >
      <div class="file-field input-field">
      <div class="btn" style="width:100px;height:40px;">
        <span>첨부</span>
 		<input type="file" id="up_files" name="up_files" onchange="handleFileSelect()">
 		
      </div>
      <div class="file-path-wrapper">
        <input class="file-path validate" type="text"  style="font-size: 30px; width:400px;margin-right:500px;">
      </div>
    </div>
    
    </div>
</div>
    <br>
   
  
    
  
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
          <input name ="title" type="text" class="validate" value="${vo.rtitle }"style=" width: 460px; font-size:30px; height: 60px;">
          <label for="title" style="font-size: 15px; ">제목</label>
        </div>
      </div>

 	 <jsp:include page="/home/summernote.jsp"></jsp:include>
 	<br>
  	<button class="btn waves-effect waves-light" type="submit" style=" width:140px; height:50px;">수정
    <i class="material-icons right">send</i>
  </button>
  
    </form>
  </div>
  
  
  </center>

