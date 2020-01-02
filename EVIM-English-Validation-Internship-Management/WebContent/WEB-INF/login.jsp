<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="navbar.jsp" />
<head>
<link rel="stylesheet" href="stiliCSS/stiliLogin.css">
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body style="background-image: url('foto/sfondo_login.jpg');">
	<div class="container">
		<div class="card card-container">


			<img id="logo-img" class="logo-img img-responsive"
				src="foto/icone/logoBianco.png">

			<c:if test="${logged==false}">
				<div class="alert alert-warning" role="alert">email e/o password
					errati</div>
			</c:if>
			<form class="form-signin" action="Login" method="post"
				name="loginform" id="login_form">
				<div style="padding-bottom: 20px;">
					<input type="text" id="username" name="email" class="form-control"
						placeholder="indirizzo email"> <input type="password"
						id="password" name="password" class="form-control"
						placeholder="password" required>
				</div>

				<button style="margin-bottom: 25px;"
					class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Login</button>
			</form>
			<div class="help">
				<p class="text-center">
					Non sei registrato? <a href="RedirectToRegistration">Registrati</a><br />
					Password dimenticata? <a href="recuperaPassword.jsp">Recupera
						password</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>