<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h4 class="truncate">공지사항</h4>
<table border="1" width="600" onload="getlist()">
	<tr>
		<td>제목</td>
		<td>${requestScope.vo.title }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>${requestScope.vo.content }</td>
	</tr>
</table>