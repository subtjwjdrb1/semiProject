<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	function del(){
	    var sql ="delete from review where rno in('"+'${requestScope.vo.rno }'+"')";
	    location.href="boardlist.do?cmd=reviewdelete&sql="+sql;
	}
</script>
<h4 class="truncate">리뷰신고게시판</h4>
<table border="1" width="600">
	<tr>
		<td>제목</td>
		<td>${requestScope.vo.rtitle }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>${requestScope.vo.rcontent }</td>
	</tr>
</table>
<a class="waves-effect waves-light btn" style="background-color: #ee6e73;" onclick="del()">삭제</a>