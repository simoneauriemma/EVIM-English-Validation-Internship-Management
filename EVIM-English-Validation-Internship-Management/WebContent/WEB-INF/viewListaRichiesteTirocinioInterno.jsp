<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:include page="navbarBlu.jsp"></jsp:include>

<head>
<link rel="stylesheet" href="stiliCSS/stiliRichiesteTirocinio.css">
<meta charset="ISO-8859-1">

<script src="jquery-3.4.1.min.js"></script>
<title>Visualizza richieste tirocinio interno</title>
</head>

<div class="container">
	<div class="row">

		<div class="col-lg-3">

			<jsp:include page="menu.jsp"></jsp:include>

		</div>

		<div class="col-lg-9" id="col-9">

			<p id="titolo">Richieste tirocinio interno</p>
			<div class="accordion">
				<div class="card">

					<div id="headingOne" style="background-color: #2C5278">
						<h6 class="mb-0">
							<button data-toggle="collapse" id="nome-studente"
								data-target="#collapseOne" aria-expanded="true"
								aria-controls="collapseOne">Nome cognome</button>
						</h6>
					</div>

					<div id="collapseOne" class="collapse" aria-labelledby="headingOne"
						data-parent="#accordion">
						<div class="card-body">
							<table class="table table-striped">
								<tbody>
									<tr>
										<td class="icon"><i class="fa fa-user"></i></td>
										<td class="">Numero proposta</td>
										<td class="text-center"># #</td>
									</tr>
									<tr>
										<td class="icon"><i class="fas fa-at"></i></td>
										<td class="">E-mail</td>
										<td class="text-center">#</td>
									</tr>
									<tr>
										<td class="icon"><i class="fas fa-toggle-on"></i></td>
										<td class="">Status</td>
										<td class="text-center"><i id="status"
											class="far fa-circle"></i></td>
									</tr>
									<tr>
										<td class="icon"><i class="fas fa-circle"></i></td>
										<td class="">Num. CFU</td>
										<td class="text-center">#</td>
									</tr>
									<tr>
										<td class="icon"><i class="fas fa-file-signature"></i></td>
										<td class="">Firma Tutor Accademico</td>
										<td class="text-center">#</td>
									</tr>
									<tr>
										<td class="icon"><i class="fas fa-signature"></i></td>
										<td class="">Valutazione richiesta</td>
										<td class="text-center">											
												<button class="bottone" onclick="ValuatareRichiesta?confermato=si&id=" id="accetta">									
													<i class="fas fa-check-square"></i>
												</button>
												<button class="bottone" onclick="ValuatareRichiesta?confermato=no&id=" id="rifiuta">
													<i class="far fa-times-circle"></i>
												</button>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<br>
<jsp:include page="footer.jsp"></jsp:include>

<script>
	$(document).ready(function() {
		$("#accetta").click(function() {
			$(this).css("background-color", "green");
			$(this).css("color", "white");
			$(this).css("outline", "none");
			$(this).val("selezionato");
			$("#rifiuta").attr("disabled", "true");

		});
		$("#rifiuta").click(function() {
			$(this).css("background-color", "red");
			$(this).css("color", "white");
			$(this).css("outline", "none");
			$(this).val("selezionato")
			$("#accetta").attr("disabled", "true");
		});
	});
</script>
