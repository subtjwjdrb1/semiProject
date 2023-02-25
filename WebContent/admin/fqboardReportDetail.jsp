<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	function del(){
	    var sql ="delete from fqboard where fqno in('"+'${requestScope.vo.fqno }'+"')";
	    
	    location.href="report.do?cmd=fqboardReportDel&sql="+sql;
	}
</script>
<h4 class="truncate">자유게시판신고</h4>
<table border="1" width="600">
	<tr>
		<td>제목</td>
		<td>${requestScope.vo.fqtitle }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>${requestScope.vo.fqcontent }</td>
	</tr>
</table>
<a class="waves-effect waves-light btn" style="background-color: #ee6e73;" onclick="del()">삭제</a>