<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
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
<title>Recupera password</title>
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
								<header>
									<h5 class="text-center">
										Sistema di Recupero<br>Credenziali di Autenticazione
										Univoche
									</h5>
								</header>
								<br />
								<div id="main-content">
									<form method="POST">
										<input type="hidden" name="auth-unisa-step" value="1" />
										<div class="form-group">
											<input type="password" class="form-control" id="auth-unisa-ps"
												name="auth-unisa-ps" aria-describedby="authCfHelp"
												placeholder="Inserisci nuova password" required value=""
												pattern="^[a-zA-Z0-9]{8,}+$" /> <br>
												 <input type="password"
												class="form-control" id="auth-unisa-ps1" name="auth-unisa-ps1"
												aria-describedby="authCfHelp"
												placeholder="Conferma nuova password" required value=""
												pattern="^[a-zA-Z0-9]{8,}+$" />
										</div>
										<button type="submit" class="btn btn-success btn-block">Invia</button>
									</form>
								</div>


							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="/spid/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="/spid/js/bootstrap.min.js"></script>

	<script type="text/javascript" src="/spid/js/check-password.js"></script>

	<script>
		$(document).ready(function() {
			if ($(".pass_show").length) {
				$('.pass_show').append('<span class="ptxt">Show</span>');

				$(document).on('click', '.pass_show .ptxt', function() {
					$(this).text($(this).text() == "Show" ? "Hide" : "Show");
					$(this).prev().attr('type', function(index, attr) {
						return attr == 'password' ? 'text' : 'password';
					});
				});

			}

		});
	</script>
	
</body>
</html>