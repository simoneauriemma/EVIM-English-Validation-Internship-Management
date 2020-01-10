<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<jsp:include page="WEB-INF/navbarBlu.jsp"></jsp:include>
<html>
<head>
<style>
#bottone {
	width: 200px;
}
</style>
<meta charset="ISO-8859-1">
<title>Crea account tutor aziendale</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<jsp:include page="WEB-INF/menu.jsp"></jsp:include>
			</div>

			<div class="col-lg-9"
				style="border: 1px solid #d7d7d7; background-color: white;">
				<p id="titolo" style="font-size: 30px; color: #595959; text-align: center;">Crea
					account Tutor Aziendale</p>
					<hr>
					<c:if test="${utenteCreato == true}">
						<div class="alert alert-success" role="alert">
						  Account creato con successo
						</div>
					</c:if>

<!-- FORM -->


				<form method="POST" id="form" action="CreaAccount" onsubmit="return validate()">
					<input type="text" class="form-control" id="name" name="nome"
						placeholder="Nome" autocomplete="off"> <br>
					<p></p>
					<input type="text" class="form-control" id="surname" name="cognome"
						placeholder="Cognome" autocomplete="off"> <br>
					<p></p>
					<input type="email" class="form-control" id="emaila" name="emaila"
						placeholder="Email" autocomplete="off">
					<p id='resultRegexEmail'></p>
					<br> <input type="tel" class="form-control" id="telefono" name="telefono"
						name="telefono" placeholder="Numero di telefono">
					<p id="resultRegexTelefono"></p>
					<br> <input type="password" class="form-control" id="passworda" name="passworda"
						name="password" placeholder="Password">
					<p id='resultRegexPass'></p>
					<br> <input type="password" class="form-control" name="confermaPassword"
						id="confermaPassword" name="confermaPassword"
						placeholder="Conferma Password">
					<p id='resultRegexCPass'></p>
					<br>

					<div class="row justify-content-center">
						<button type="submit" class="btn btn-primary" id="bottone">CREA
							ACCOUNT</button>
					</div>
					<br>
				</form>
			</div>
		</div>

	</div>
	<br>
	<br>
	<jsp:include page="WEB-INF/footer.jsp"></jsp:include>

	<script>
	
	window.setTimeout(function() {
	    $(".alert").fadeTo(500, 0).slideUp(500, function(){
	        $(this).remove(); 
	    });
	}, 2000);
	
		function validate() {

			var pass = $('#passworda');
			var email = $('#emaila');
			var telefono = $('#telefono');
			
			var resultRegexPassword = $('#resultRegexPass');
			var resultRegexEmail = $('#resultRegexEmail');
			var resultRegexTelefono = $('#resultRegexTelefono');
			
			var regex_email = new RegExp(
					'[A-z0-9\.\+_-]+@[A-z0-9\._-]+\.[A-z]{2,6}');
			var regex_telefono = new RegExp('[0-9]{8,16}');
			var regex_password = new RegExp('[A-Za-z0-9]{8,50}');
			var resultRegexCPass = $('#resultRegexCPass');
			var confirm_password = $('#confermaPassword');

			if (!regex_email.test(email.val())) {
				resultRegexEmail.text(email.val() + " non rispetta il formato!");
				resultRegexEmail.css("color", "red");
				email.css("color", "red");
				return false;
			}

			if (!regex_telefono.test(telefono.val())) {
				telefono.css("color", "red");
				resultRegexTelefono
						.text("inserire un numero di telefono valido");
				resultRegexTelefono.css("color", "red");

			}

			if (!regex_password.test(pass.val())) {

				resultRegexPassword.text(pass.val() + " non valida!");
				resultRegexPassword.css("color", "red");
				pass.css("color", "red");
				console.log("falsetipo");
				return false;

			}

			if (confirm_password.val() != pass.val()) {
				resultRegexCPass
						.text("Conferma password Ë diverso dal campo 'Password'");
				resultRegexCPass.css("color", "red");
				confirm_password.css("color", "red");

				return false;
			}

			return true;

		}
	</script>
</body>
</html>