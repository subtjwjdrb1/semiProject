<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	*{margin: 0px; padding: 0px; margin: auto;}
	#notices{position: relative; float: left;}
	#fqboard{position: relative; float: left;}
	#review{position: relative; float: left;}
	#event{position: relative; float: left; width: 250px;}
	#content{margin-left:50px; height:100%; position: relative; float: left;}
	#footer{width:100%; position: relative; float: left; margin-top: 20px;}
</style>
<script type="text/javascript">
	function imgsamsung(){
	    location.href="http://www.samsung.com/sec/galaxys9/preorder/";
	}
	function imglg(){
	    location.href="http://www.lge.co.kr/lgekor/product/mobile/smart-phone/productDetail.do?cateId=0210&prdId=EPRD.318023";
	}
</script>
<body>
<div id="wrap">
	<div id="header">
		<jsp:include page="/home/header.jsp"></jsp:include>
	</div>
	<table id="event" >

		<tr><td><img id="imgsamsung" alt="" src="image\samsung.jpg" style="width: 250px;" onclick="imgsamsung()"></td></tr>
		<tr><td><img id="imglg" alt="" src="image\LG.jpg" style="width: 250px;" onclick="imglg()"></td></tr>

	</table>
	<div id="content" style="width: 1350px;">
		<c:choose>
			<c:when test="${requestScope.page==null}">
			<div id="notices" style="margin-top: 30px; width: 630px; height: 350px;">
			        	<div class="collection" style="margin-top: 0px; padding: 0px;">
						    <a href="http://localhost:8081/semiProject/notices.do?cmd=notices" style="color: #993333;" class="collection-item"><h4>공지사항</h4></a>
			        	<c:forEach var="notices" items="${requestScope.noticesList }">
						    <a href="http://localhost:8081/semiProject/notices.do?cmd=detail&num=${notices.num}" style="color: #993333;" class="collection-item">${notices.title}</a>
				        </c:forEach>  
						  </div>
			</div>

			<div id="fqboard" style="margin-top: 30px; margin-left : 40px;  width: 630px; height: 350px;">
				<div class="collection" style="margin-top: 0px; padding: 0px;">
						    <a href="http://localhost:8081/semiProject/fq.do?cmd=fqList" style="color: #993333;" class="collection-item"><h4>자유게시판</h4></a>
			        	<c:forEach var="fq" items="${requestScope.fqmain }">
						    <a href="http://localhost:8081/semiProject/fq.do?cmd=fqdetail&fqno=${fq.fqno}" style="color: #993333;" class="collection-item">${fq.fqtitle}</a>
				        </c:forEach>  
				</div>

			</div>
		<!-- 	<div id="review" style="margin-top: 30px; width: 1300px; height: 400px; background-color: gray;"></div>
			<div id="fqboard" style="margin-top: 30px; margin-left : 40px;  width: 630px; height: 350px; background-color: yellow;"></div>
			 -->
			
			
			
			
			

			</div>

			<br>
			
			<div id="review" style="margin-top: 30px; width: 1300px; height: 500px; background-color: white;  overflow:hidden;text-overflow:ellipsis;">
				<c:forEach var="review" items="${requestScope.reviewMain }">
				<div style="margin-left:10px; margin-top:30px; float:left; ">
				<div style="clear:both;overflow:hidden;">				<div style="float:left;width:390px;overflow:hidden;text-align:center; margin-right:30px; text-overflow:ellipsis;">
												<div style="width:310px;height:220px;text-align:center;overflow:hidden;">
								<a href="/semiProject/review.do?cmd=content&rno=${review.rno}"><!--  --><img src="image/${review.savefilename}" style="border:1px solid #F0F0F0; width:310px;height:220px;" ></a>
							</div>
										
					<div style="clear:both;overflow:hidden;text-align:left;margin-top:20px;" onclick="location.href='/semiProject/review.do?cmd=content&rno=${review.rno}'">		
						<div style="margin-left:50px;width:100%;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;padding-bottom:2px;"><h5>${review.rtitle }</h5></div>

						<div style="margin-top:10px; white-space:nowrap; text-overflow:ellipsis;width:300px;">
							<span></span>
						</div>
					</div>
				</div>
			</div>
				</div>
				</c:forEach>
			</div>
			</c:when>
			<c:otherwise>
				<jsp:include page="${requestScope.page}"></jsp:include>
			</c:otherwise>
		</c:choose>
	</div>
	<div id="footer">
		<jsp:include page="/home/footer.jsp"></jsp:include>
	</div>
</div>
</body>
</html>