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
		<div class="page-header clearfix">
			<h1 class="pull-left">Hello Spring</h1>
			<div style="margin-top: 30px;" class="pull-right">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">선택하기
						<span class="caret"></span></button>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/professor/prof_list.do">교수목록</a></li>
						<li><a href="${pageContext.request.contextPath}/department/dept_list.do">학과목록</a></li>
						<li><a href="${pageContext.request.contextPath}/student/stud_list.do">학생목록</a></li>
					</ul>
				</div>
			</div>
		</div>
		
	</div>
</body>
</html>