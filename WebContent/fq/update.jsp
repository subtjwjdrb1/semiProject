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
   <form class="col s12" method="post" action="/semiProject/fq.do?cmd=updateOk&fqno=${vo.fqno }">
	<div class="input-field col s12">
	    <select name="fqtype" class="select" id="aa">
		      <option value="fqtype" disabled selected>선택하세요</option>
		      <option value="1">일반</option>
		      <option value="2">정보</option>
		      <option value="3">질문</option>
	    </select>
	    <label>종류</label>
	</div>
    <div class="row">
        <div class="input-field col s12">
          <input name="fqtitle" type="text" class="validate" value="${vo.fqtitle }">
          <label for="fqtitle">제목</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12">
          <label for="fqcontent">내용</label> <br><br>
          <textarea name="fqcontent" id="materialnote">${vo.fqcontent }</textarea>
        </div>
    </div>
      
      <button class="btn waves-effect waves-light" type="submit" onclick="fqupdate()" style="background-color:#993333; margin-left:340px; width: 200px">
      	<i class="large material-icons">done</i></button>
   </form>
  </div>
<script type="text/javascript">

function fqupdate(){
	if('${result}' == 'fail'){
		alert("수정사항이 올바르지 않습니다");
	}
}

    var fqtype = document.getElementsByName("fqtype")[0];
    fqtype.selectedIndex = '${vo.fqtype }';
    
    
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
