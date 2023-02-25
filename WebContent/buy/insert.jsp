<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
<script type="text/javascript">
  $(document).ready(function() {
    $('select').material_select();
  });
</script>
</head>
<body>
<br>
<div class="row">
   <form class="col s12" method="post" action="/semiProject/buy.do?cmd=insertOk">
   	  <div class="input-field col s12">
	    <select name="success" class="select">
	      <option value="success" disabled selected>선택하세요</option>
	      <option value="0">거래중</option>
	      <option value="1">거래완료</option>
	    </select>
	    <label>type</label>
	  </div>
   
      <div class="row">
        <div class="input-field col s12">
          <input name="btitle" type="text" class="validate">
          <label for="btitle">제목</label>
        </div>
      </div> 
      <div class="row">
        <div class="input-field col s12">
          <label for="bcontent">내용</label> <br><br>
       	 <jsp:include page="/home/summernote.jsp"></jsp:include>
        </div>
      </div>
      
      <button class="btn waves-effect waves-light" type="submit" style="background-color:#993333; margin-left:340px; width: 200px">
      	<i class="large material-icons">done</i></button>
   </form>
  </div>
</body>
</html>