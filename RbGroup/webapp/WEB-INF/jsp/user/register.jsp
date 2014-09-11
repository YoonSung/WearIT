<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="SpringForm" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SLiPP :: 회원가입</title>

<%@ include file="../commons/_header.jspf"%>

</head>
<body>
	<%@ include file="../commons/_top.jspf"%>

	<div class="container">
		<div class="row">
			<div class="span12">
				<section id="typography">
				<div class="page-header">
					<c:choose>
						<c:when test="${not empty sessionScope.userId }">
							<c:set var="title" value="개인정보 수정" />
							<c:set var="method" value="put" />
						</c:when>
						<c:otherwise>
							<c:set var="title" value="회원가입" />
							<c:set var="method" value="post" />
						</c:otherwise>
					</c:choose>
					<h1><c:out value="${title}"/></h1>
				</div>
			
				
				<SpringForm:form commandName="user" cssClass="form-horizontal" action="/user/register" method="${method}">
					<div class="control-group">
						<label class="control-label" for="userId">사용자 아이디</label>
						<div class="controls">
							<c:choose>
								<c:when test="${empty sessionScope.userId} }">
									<SpringForm:input path="userId"/>
								</c:when>
								<c:otherwise>
									<SpringForm:hidden path="userId"/>
									<div>${sessionScope.userId}</div>
								</c:otherwise>
							</c:choose>
							<SpringForm:errors path="userId" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">비밀번호</label>
						<div class="controls">
							<SpringForm:password id="password" path="password"/>
							<SpringForm:errors id="password" path="password"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="name">이름</label>
						<div class="controls">
							<SpringForm:input id="name" path="name"/>
							<SpringForm:errors id="name" path="name"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="email">이메일</label>
						<div class="controls">
							<SpringForm:input id="email" path="email" />
							<SpringForm:errors id="email" path="email" />
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn btn-primary">회원가입</button>
						</div>
					</div>
				</SpringForm:form>
			</div>
		</div>
	</div>
</body>
</html>