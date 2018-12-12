<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
	<!-- Twitter Bootstrap3 & jQuery -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1 class="page-header">교수수정</h1>
		
		<!-- 수정을 위한 HTML 폼 -->
		<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/department/dept_edit_ok.do">
		
			<!-- 상태유지를 위한 일련번호 값의 처리 -->
			<input type="hidden" name="deptno" value="${item.deptno}">
			
			<div class="form-group">
				<label for="dname" class="col-sm-2 control-label">학과이름</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="dname" name="dname" value="${item.dname}">
				</div>
			</div>
			
			<div class="form-group">
				<label for="loc" class="col-sm-2 control-label">강의동</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="loc" name="loc" value="${item.loc}">
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">저장하기</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>