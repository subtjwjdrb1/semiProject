<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	function del(){
	    var sql ="delete from sell where sno in('"+'${requestScope.vo.sno }'+"')";
	    location.href="boardlist.do?cmd=selldelete&sql="+sql;
	}
</script>
<h4 class="truncate">팝니다게시판</h4>
<table border="1" width="600">
	<tr>
		<td>제목</td>
		<td>${requestScope.vo.stitle }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>${requestScope.vo.scontent }</td>
	</tr>
</table>
<a class="waves-effect waves-light btn" style="background-color: #ee6e73;" onclick="del()">삭제</a>