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
			<h1 class="pull-left">학생목록</h1>
			<!-- 검색폼 + 추가버튼 -->
			<div style="margin-top: 30px;" class="pull-right">
				<form method="get" action="${pageContext.request.contextPath}/student/stud_list.do"
					style="width: 300px;">
					<div class="input-group">
						<input type="text" name="keyword" class="form-control" 
							placeholder="학생이름 검색" value="${keyword}"/>
						<span class="input-group-btn">
							<button class="btn btn-success" type="submit">
								<i class="glyphicon glyphicon-search"></i>
							</button>
							<a href="${pageContext.request.contextPath}/student/stud_add.do"
								class="btn btn-primary">학생추가</a>
						</span>
					</div>
				</form>
				
				<br/>
				
				<div class="dropdown pull-right">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">선택하기
						<span class="caret"></span></button>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/professor/prof_list.do">교수목록</a></li>
						<li><a href="${pageContext.request.contextPath}/department/dept_list.do">학과목록</a></li>
						<li><a href="${pageContext.request.contextPath}/student/stud_list.do">학생목록</a></li>
						<li class="divider"></li>
						<li><a href="${pageContext.request.contextPath}">메인으로</a>
					</ul>
				</div>
			</div>
		</div>
		
		<!-- 조회결과를 출력하기 위한 표 -->
		<table class="table">
			<thead>
				<tr>
					<th class="info text-center">학생번호</th>
					<th class="info text-center">이름</th>
					<th class="info text-center">아이디</th>
					<th class="info text-center">학년</th>
					<th class="info text-center">주민번호</th>
					<th class="info text-center">생년월일</th>
					<th class="info text-center">전화번호</th>
					<th class="info text-center">키</th>
					<th class="info text-center">몸무게</th>
					<th class="info text-center">학과</th>
					<th class="info text-center">담당교수</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(list) > 0}">
						<c:forEach var="item" items="${list}">
							<tr>
								<td class="text-center">${item.studno}</td>
								<td>
									<c:url var="readUrl" value="/student/stud_view.do">
										<c:param name="studno" value="${item.studno}"/>
									</c:url>
									<a href="${readUrl}">${item.name}</a>
								</td>
								<td class="text-center">${item.userid}</td>
								<td class="text-center">${item.grade}</td>
								<td class="text-center">${item.idnum}</td>
								<td class="text-center">${item.birthdate}</td>
								<td class="text-center">${item.tel}</td>
								<td class="text-center">${item.height}</td>
								<td class="text-center">${item.weight}</td>
								<td class="text-center">${item.dname}</td>
								<td class="text-center">${item.pname}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="8" class="text-center" style="line-height: 100px;">
								조회된 데이터가 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		
		<!-- 페이지 번호 -->
		<nav class="text-center">
			<ul class="pagination">
				<!-- 이전 그룹으로 이동 -->
				<c:choose>
					<c:when test="${page.prevPage > 0}">
						<!-- 이전 그룹에 대한 페이지 번호가 존재한다면? -->
						<!-- 이전 그룹으로 이동하기 위한 URL을 생성해서 "prevUrl"에 저장 -->
						<c:url var="prevUrl" value="/student/stud_list.do">
							<c:param name="keyword" value="${keyword}"></c:param>
							<c:param name="page" value="${page.prevPage}"></c:param>
						</c:url>
						
						<li><a href="${prevUrl}">&laquo;</a></li>
					</c:when>
					
					<c:otherwise>
						<!-- 이전 그룹에 대한 페이지 번호가 존재하지 않는다면? -->
						<li class="disabled"><a href="#">&laquo;</a></li>
					</c:otherwise>
				</c:choose>
				
				<!-- 페이지 번호 -->
				<!-- 현재 그룹의 시작페이지~끝페이지 사이를 1씩 증가하면서 반복 -->
				<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}" step="1">
					<!-- 각 페이지 번호로 이동할 수 있는 URL을 생성하여 page_url에 저장 -->
					<c:url var="pageUrl" value="/student/stud_list.do">
						<c:param name="keyword" value="${keyword}"></c:param>
						<c:param name="page" value="${i}"></c:param>
					</c:url>
					
					<!-- 반복중의 페이지 번호와 현재 위치한 페이지 번호가 같은 경우에 대한 분기 -->
					<c:choose>
						<c:when test="${page.page == i}">
							<li class="active"><a href="#">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageUrl}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<!-- 다음 그룹으로 이동 -->
				<c:choose>
					<c:when test="${page.nextPage > 0}">
						<!-- 다음 그룹에 대한 페이지 번호가 존재한다면? -->
						<!-- 다음 그룹으로 이동하기 위한 URL을 생성해서 "nextUrl"에 저장 -->
						<c:url var="nextUrl" value="/student/stud_list.do">
							<c:param name="keyword" value="${keyword}"></c:param>
							<c:param name="page" value="${page.nextPage}"></c:param>
						</c:url>
						
						<li><a href="${nextUrl}">&raquo;</a></li>
					</c:when>
					
					<c:otherwise>
						<!-- 이전 그룹에 대한 페이지 번호가 존재하지 않는다면? -->
						<li class="disabled"><a href="#">&raquo;</a></li>
					</c:otherwise>
		    	</c:choose>
			</ul>
		</nav>
	</div>
</body>
</html>