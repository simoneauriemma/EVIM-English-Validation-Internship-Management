<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="navbar.jsp"></jsp:include>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="stiliCSS/stiliRecuperaPassword.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="ISO-8859-1">
<style>
#main-card {
	width: 800px;
}

#name, #surname, #email, #password, #confermaPassword, #indirizzo,
	#telefono,#matricola{
	width: 300px;
	margin-left: 25px;
}

#name {
	margin-right: 60px;
}

#email, #boxcorso {
	margin-left: 25px;
}

#buttonReg {
	width: 700px;
	text-align: center;
	margin-left: 25px;
}

#date2 {
	width: 300px;
}
#matricola{
margin-left: 38px;
}

#comune{
margin-right: 5px;
}
</style>

<title>Registrazione</title>
</head>
<body style="background-image: url('foto/sfondo_login.jpg');">
	<div class="container py-5">
		<div class="row">
			<div class="col-md-10">
				<div class="row">
					<div class="col-md-8 mx-auto">
						<div class="card rounded-0" id="main-card">
							<div class="card-body">
								<div class="text-center">
									<img src="foto/icone/logoBianco.png"
										style="width: 100%; max-width: 240px" />
									<c:choose>

										<c:when test="${empty result}">

										</c:when>
										<c:when test="${result == false}">
											<div class="alert alert-danger" role="alert">Utente già
												registrato!</div>
										</c:when>
										<c:otherwise>

										</c:otherwise>
									</c:choose>


								</div>
								<hr />
								<div id="main-content">
									<form method="POST" id="form" action="Registrazione"
										onsubmit="return validate()">
										<table>
											<tr>
												<td></td>
												<td id="colonna1"><input type="text"
													class="form-control" id="name" name="nome"
													placeholder="Nome" autocomplete="off">
													<p id="resultRegexNome"></p></td>

												<td><input type="text" class="form-control"
													id="surname" name="cognome" placeholder="Cognome"
													autocomplete="off">
													<p id="resultRegexCognome"></p></td>


											</tr>
											<tr>
												<td></td>
												<td><input name="email" onblur="hiddenandshow()"
													type="email" class="form-control" id="email"
													placeholder="Email" autocomplete="off">
													<p id='resultRegexEmail'></p></td>
												<td><div style="margin-left: 29px;">
														Sesso : <input name="sesso" type="radio" id="radio1"
															checked> <label for="radio1">M</label> <input
															name="sesso" type="radio" id="radio2"> <label
															for="radio2">F</label>

													</div></td>
											<tr>
												<td></td>
												<td><input name="password" type="password"
													class="form-control" id="password" name="password"
													placeholder="Password">
													<p id='resultRegexPass'></p></td>
												<td><input type="password" class="form-control"
													name="cpassword" id="confermaPassword"
													name="confermaPassword" placeholder="Conferma Password">
													<p id='resultRegexCPass'></p></td>
											</tr>
										</table>

										<br>

										<div style="margin-left: 30px;" id="boxcorso">
											Tipo corso : <input name="corso" type="radio" id="radio1"
												value="triennale" checked> <label for="radio1">Triennale</label>
											<input name="corso" value="magistrale" type="radio"
												id="radio2"> <label for="radio2">Magistrale</label>
											<p id="resultRegexCorso"></p>

										
											<table>
											<tr>
												
												<td><p style="margin-right: 50px; margin-left: 30px;">Matricola
													</p>
													<p id="resultRegexMatricola"> </p>
													</td>
													<td>
													<input type="text" class="form-control"
													id="matricola" name="matricola"
													placeholder="Inserisci matricola" autocomplete="off">
												</td>
											</tr>
											</table><br>
										<table>

											<tr>
												<td>
													<p style="margin-right: 50px; margin-left: 30px;">Data
														di nascita</p>
												</td>
												<td>
													<div>
														<input name="data" class="form-control it-date-datepicker" id="date2"
															type="date">
													</div>

												</td>
											</tr>
										</table>
										<br>
										<table>
											<tr>
												<td>
													<p style="margin-right: 50px; margin-left: 30px;">Luogo
														di nascita</p>
												</td>
												<td>


													<div class="form-group">
														<input type="text" class="form-control"
													id="comune" name="comune"
													placeholder="Inserisci comune" autocomplete="off">
															
													</div>
												</td>
												<td></td>
												<td>
													<div class="form-group">
														 <select name="provinciaN"
															class="form-control" id="prov">
															<c:forEach items="${sigle}" var="sigla">
															<option><c:out value="${sigla}"></c:out></option>
															</c:forEach>
														</select>
														<p id="resultRegexLuogoNascita"> </p>
													</div>
												</td>
											</tr>

											<tr>
												<td>
													<p style="margin-right: 50px; margin-left: 30px;">Città
														di residenza</p>
												</td>
												<td>
													<div class="form-group">
														<input type="text" class="form-control"
													id="comune" name="comune"
													placeholder="Inserisci comune" autocomplete="off">
													</div>
												</td>
												<td></td>
												<td>
													<div class="form-group">
														 <select name="provinciaR"
															class="form-control" id="prov1">
															<c:forEach items="${sigle}" var="sigla">
															<option><c:out value="${sigla}"></c:out></option>
															</c:forEach>
														</select>
														<p id="resultRegexLuogoResidenza"> </p>
													</div>
												</td>
											</tr>
										</table>
										<br>
										<table>

											<tr>

												<td><p style="margin-right: 50px; margin-left: 30px;">Indirizzo
													</p> <input type="text" class="form-control" id="indirizzo"
													name="indirizzo" placeholder="Via, n° civico"
													autocomplete="off"><p id="resultRegexIndirizzo"></p></td>
												<td><p style="margin-right: 50px; margin-left: 30px;">Numero
														di telefono</p> <input type="tel" class="form-control"
													id="telefono" name="telefono"
													placeholder="Inserisci numero">
													<p id="resultRegexTelefono"> </p>
													</td>
											</tr>
											</table></div> <br>
										
										
										<br>
									

										<br>
										<button type="submit" class="btn btn-success btn-block"
											id="buttonReg">Registrati</button>

									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		function validate() {
			var r = true;
			var isStudent;
			//password
			var pass = $('#password');
			var resultRegexPassword = $('#resultRegexPass');
			var regex_password = new RegExp('^[A-Za-z0-9]{8,50}');
			var confirm_password = $('#confermaPassword');
			var resultRegexCPass = $('#resultRegexCPass');
			//nome
			var nome = $('#name');
			var regex_nome = new RegExp('^[A-Za-z]{1,50}');
			var resultRegexNome = $('#resultRegexNome');
			//cognome
			var cognome = $('#surname');
			var regex_cognome = new RegExp('^[A-Za-z]{1,50}');
			var resultRegexCognome = $('#resultRegexCognome');

			//email
			var email = $('#email');
			var resultRegexEmail = $('#resultRegexEmail');
			var regex_email = new RegExp(
					'[a-z0-9\.]+@studenti\.unisa\.it|[a-z0-9.]+@unisa.it');
			var regex_email_studente = new RegExp(
					'[a-z0-9\.]+@studenti\.unisa\.it');

			//corso
			var corso1 = $('#radio1');
			var corso2 = $('#radio2');
			var resultRegexCorso = $('#resultRegexCorso');
			var divcorso = $('#boxcorso');
			
			//matricola
			
			var matricola= $('#matricola');
			var resultRegexMatricola = $('#resultRegexMatricola');
			var regex_matricola = new RegExp('^[0-9]{10,10}');
			
			//telefono
			var telefono =$('#telefono');
			var resultRegexTelefono = $('#resultRegexTelefono');
			var regex_telefono= new RegExp('^[0-9]{8,16}');
			//indirizzo
			var indirizzo=$('#indirizzo');
			var resultRegexIndirizzo = $('#resultRegexIndirizzo');
			var regex_indirizzo= new RegExp('^[a-z A-Z]+.[1-9]+{1,100}');
			//luogonascita
			var luogonascita= $('#luogonascita');
			var regex_luogonascita=  new RegExp('^[A-Za-z]{1,50}');
			var resultRegexLuogoNascita= ${'resultRegexLuogoNascita'};
			//luogoresidenza
				var luogoresidenza= $('#luogoresidenza');
			var regex_luogoresidenza=  new RegExp('^[A-Za-z]{1,50}');
			var resultRegexLuogoResidenza= ${'resultRegexLuogoResidenza'};
	
			
			
			
			if (!regex_email.test(email.val())) {

				resultRegexEmail.text(email.val()
						+ " non valida! Si accettano solo domini unisa");
				resultRegexEmail.css("color", "red");
				email.css("color", "red");
				r = false;
			}

			if (!regex_password.test(pass.val()) || pass.val() == '') {

				resultRegexPassword.text(pass.val() + " non valida");
				resultRegexPassword.css("color", "red");
				pass.css("color", "red");

				r = false;

			}

			if (!regex_cognome.test(cognome.val()) || cognome.val() == '') {

				resultRegexCognome.text(cognome.val() + " non valida");
				resultRegexCognome.css("color", "red");
				cognome.css("color", "red");

				r = false;

			}

			if (!regex_nome.test(nome.val()) || nome.val() == '') {

				resultRegexNome.text(nome.val() + " non valida");
				resultRegexNome.css("color", "red");
				nome.css("color", "red");

				r = false;

			}

			if (confirm_password.val() != pass.val()) {
				resultRegexCPass
						.text("Conferma password è diverso dal campo password");
				resultRegexCPass.css("color", "red");
				confirm_password.css("color", "red");

				r = false;
			}

			if (radio1.checked != true && radio2.checked != true
					&& regex_email_studente.test(email.val())) {
				corso1.css("color", "red");
				corso1.css("color", "red");
				resultRegexCorso.text("Inserire un tipo di corso triennale o magistrale");
				resultRegexCorso.css("color", "red");
				r = false;
			}
			
			if(regex_matricola.test(matricola.val()) && regex_email_studente.test(email.val())){
				matricola.css("color", "red");
				resultRegexMatricola.text("Inserire una matricola valida");
				resultRegexMatricola.css("color", "red");
				r=false;
				
			}
			
			if(!regex_telefono.test(telefono.val())&& regex_email_studente.test(email.val())){
				telefono.css("color", "red");
				resultRegexTelefono.text("inserire un numero di telefono valido");
				resultRegexIndirizzo.css("color", "red");
				r=false;
			}
			
			if(!regex_indirizzo.test(indirizzo.val())&& regex_email_studente.test(email.val())){
				telefono.css("color", "red");
				resultRegexTelefono.text("inserire un numero di indirizzo valido");
				resultRegexTelefono.css("color", "red");
				r=false;
			}
			
			if(!regex_luogonascita.test(luogonascita.val())&& regex_email_studente.test(email.val())){
				luogonascita.css("color", "red");
				resultRegexLuogoNascita.text("inserire un comune valido");
				resultRegexLuogoNascita.css("color", "red");
				r=false;
			}
			
			if(!regex_luogoresidenza.test(luogoresidenza.val())&& regex_email_studente.test(email.val())){
				luogoresidenza.css("color", "red");
				resultRegexLuogoResidenza.text("inserire un comune valido");
				resultRegexLuogoResidenza.css("color", "red");
				r=false;
			}
			
		
			

			return r;

		}

		function hiddenandshow() {
			var email = $('#email');
			var divcorso = $('#boxcorso');
			var regex_email_studente = new RegExp(
					'[a-z0-9\.]+@studenti\.unisa\.it');
			if (regex_email_studente.test(email.val())) {
				divcorso.show();
			} else {
				divcorso.hide();
			}
		}
	</script>
</body>

</html>