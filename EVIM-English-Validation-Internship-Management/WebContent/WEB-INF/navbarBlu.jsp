<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="navbar.jsp" />
<head>
<style>
#user-logo {
	width: 30px;
	height: 30px;
	margin-right: 5px;
}
</style>
<link rel="stylesheet" href="stiliCSS/stiliNavbar.css">
<meta charset="ISO-8859-1">
</head>
<body>
	<nav id="nav2" class="img-responsive"
		style="background-image: url('foto/corona.png')">
		<div class="text-center">
			<img class="img-responsive" src="foto/icone/logoBianco.png"
				id="logobianco">
		</div>
	</nav>

	<nav id="nav3">
		
		<div class="navbar-header text-right" style="padding-right: 62px;">



			<div class="float-sm-left text-white align-middle"
				style="margin-top: 10px;">
				<c:choose>
					<c:when test="${type eq 'studente'}">
					 <i class="fas fa-user-circle"></i>
						Ciao <c:out value="${utenteLoggato.name}"></c:out>!
					</c:when>
					<c:when test="${type eq 'pdcd'}">
					<i class="fas fa-user-circle"></i>
						Ciao <c:out value="${utenteLoggato.name}"></c:out>!
					</c:when>
					<c:when test="${type eq 'tutoraziendale'}">
					<i class="fas fa-user-circle"></i>
						Ciao <c:out value="${utenteLoggato.nome}"></c:out>!
					</c:when>
					<c:when test="${type eq 'tutoraccademico'}">
					<i class="fas fa-user-circle"></i>
						Ciao <c:out value="${utenteLoggato.nome}"></c:out>!
					</c:when>
					<c:when test="${type eq 'azienda'}">
					<i class="fas fa-user-circle"></i>
						Ciao <c:out value="${utenteLoggato.nome}"></c:out>!
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</div>


			<c:choose>
				<c:when test="${empty utenteLoggato}">
					<a href="Login">
						<button id="bott" style="border: 3px solid #2a4d71; outline: none;">LOGIN</button>
					</a>
				</c:when>
				<c:otherwise>
					<a href="Logout">
						<button id="bott" style="border: 3px solid #2a4d71; outline: none;">LOGOUT</button>
					</a>
				</c:otherwise>
			</c:choose>

		</div>
	</nav>
	<br>
</body>