<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

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
	width: 500px;
	margin-left: 100px;
}
</style>

<title>Registrazione</title>
</head>
<body style="background-image: url('foto/sfondo_login.jpg');">
	<div class="container py-5">
		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-8 mx-auto">
						<div class="card rounded-0" id="main-card">
							<div class="card-body">
								<div class="text-center">
									<img src="foto/icone/logoBianco.png"
										style="width: 100%; max-width: 240px" />
								</div>
								<hr />
								<div id="main-content">
									<form method="POST" id="form" onsubmit="return validate()">
										<input type="text" class="form-control" id="name" name="nome"
											placeholder="Nome" autocomplete="off"> <br> <input
											type="text" class="form-control" id="surname" name="cognome"
											placeholder="Cognome" autocomplete="off"> <br> <input name="email"
											type="email" class="form-control" id="email"
											placeholder="Email" autocomplete="off">
											<p id='resultRegexEmail'></p>
											 <br> <input name="password" 
											type="password" class="form-control" id="password"
											name="password" placeholder="Password">
											<p id='resultRegexPass'></p>
										<br> <input type="password" class="form-control" name="cpassword"
											id="confermaPassword" name="confermaPassword"
											placeholder="Conferma Password">
											<p id='resultRegexCPass'></p>
											 <br>
										<div style="margin-left: 5px;">
											Sesso : <input name="sesso" type="radio" id="radio1" checked>
											<label for="radio1">M</label> <input name="sesso"
												type="radio" id="radio2"> <label for="radio2">F</label>

										</div>

										<br>
										<button type="submit"  class="btn btn-success btn-block">Registrati</button>
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
		
			
		function validate(){
			
			var pass= $('#password');
			var resultRegexPassword= $('#resultRegexPass');
			var regex_password= new RegExp('^[A-Za-z0-9]{8,}');
			var regex_nome= new RegExp('^[A-Za-z]{8,}');
			var regex_email= new RegExp('[a-z0-9\.]+@studenti\.unisa\.it|[a-z0-9.]+@unisa.it');
			var resultRegexCPass= $('#resultRegexCPass');
			var email= $('#email');
			var resultRegexEmail= $('#resultRegexEmail');
			var confirm_password=$('#confermaPassword');
			var uno= email.val().split("@");
			
		if(!regex_email.test(email.val())){
			
				resultRegexEmail.text(email.val() + " non valida");
				resultRegexEmail.css("color", "red");
				email.css("color", "red");
				return false;
			}
		
			if(!regex_password.test(pass.val())){
				
				resultRegexPassword.text(pass.val() + " non valida");
				resultRegexPassword.css("color", "red");
				pass.css("color", "red");
				console.log("falsetipo");
				return false;
				
			}
		
			
			if(confirm_password.val()!=pass.val()){
				resultRegexCPass.text("Conferma password è diverso dal campo password");
				resultRegexCPass.css("color","red");
				confirm_password.css("color", "red");
				
				return false;
			}
			
			
			return true;
			
		}
		
	</script>
</body>

</html>