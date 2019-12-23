<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:include page="WEB-INF/navbarBlu.jsp"></jsp:include>

<head>
<link rel="stylesheet" href="stiliCSS/stiliRichiesteTirocinio.css">
<meta charset="ISO-8859-1">

<script src="jquery-3.4.1.min.js"></script>
<title>Visualizza richieste tirocinio esterno</title>
</head>

<div class="container">
	<div class="row">

		<div class="col-lg-3">

			<jsp:include page="WEB-INF/menu.jsp"></jsp:include>

		</div>

		<div class="col-lg-9" id="col-9">

			<p id="titolo">Visualizza lista richieste tirocinio esterno</p>
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
							<table class="table table-striped" id="tabella">
								<tbody>
									<tr>
										<td class="icon"><i class="fas fa-list-ol"></i></td>
										<td class="">ID proposta</td>
										<td class="text-center">#</td>
									</tr>
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
										<td class="text-center"><i class="far fa-circle"></i></td>
									</tr>
									<tr>
										<td class="icon"><i class="far fa-calendar-alt"></i></td>
										<td class="">Data</td>
										<td class="text-center">#</td>
									</tr>
									<tr>
										<td class="icon"><i class="fas fa-clock"></i></td>
										<td class="">Ore totali</td>
										<td class="text-center">#</td>
									</tr>
									<tr>
										<td class="icon"><i class="fas fa-circle"></i></td>
										<td class="">Num. CFU</td>
										<td class="text-center">#</td>
									</tr>
									<tr>
										<td class="icon"><i class="far fa-user"></i></td>
										<td class="">Tutor Accademico</td>
										<td class="text-center">#</td>
									</tr>
									<tr>
										<td class="icon"><i class="fas fa-user"></i></td>
										<td class="">Tutor Aziendale</td>
										<td class="text-center">#</td>
									</tr>
									<tr>
										<td class="icon"><i class="fas fa-file-signature"></i></td>
										<td class="">Accettazione Azienda</td>
										<td class="text-center">#</td>
									</tr>
									<tr>
										<td class="icon"><i class="fas fa-file-signature"></i></td>
										<td class="">Accettazione Tutor Aziendale</td>
										<td class="text-center">#</td>
									</tr>
									<tr>
										<td class="icon"><i class="fas fa-file-signature"></i></td>
										<td class="">Accettazione Tutor Accademico</td>
										<td class="text-center">#</td>
									</tr>
									
									<tr>
										<td class="icon"><i class="fas fa-signature"></i></td>
										<td class="">Valutazione richiesta</td>
										<td class="text-center">
											<button class="bottone" onclick="ValuatareRichiesta?confermato=si&id=&azienda=azienda" id="accetta">									
													<i class="fas fa-check-square"></i>
												</button>
												<button class="bottone" onclick="ValuatareRichiesta?confermato=no&id=&azienda=azienda" id="rifiuta">
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
<jsp:include page="WEB-INF/footer.jsp"></jsp:include>

