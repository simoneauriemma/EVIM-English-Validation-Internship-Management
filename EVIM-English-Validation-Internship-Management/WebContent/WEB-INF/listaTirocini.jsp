<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="navbarBlu.jsp"></jsp:include>

<head>
<link rel="stylesheet" href="stiliCSS/stiliListaTirocini.css">
<meta charset="ISO-8859-1">

<script src="jquery-3.4.1.min.js"></script>
<title>Lista Tirocini</title>
</head>

<div class="container">
	<div class="row">

		<div class="col-lg-3">

			<jsp:include page="menu.jsp"></jsp:include>

		</div>
		<div class="col-lg-9" id="col-9">

			<p id="titolo" class="text-center">Lista tirocini</p>
			<hr>

			<!-- se entrambi gli array di tirocini sono vuoti esce che non c'è nulla -->
			<c:if
				test="${registroQueryEsterno.size()==0 && registroQueryInterno.size()==0}">
				<p style="text-align: center;">Nessun tirocinio in corso!</p>
			</c:if>


			<!-- inizio TIROCINIO ESTERNO -->
			<c:if
				test="${type == 'tutoraziendale' || type== 'tutoraccademico' || type=='azienda' || type=='pdcd'}">

				<c:if test="${registroQueryEsterno.size() > 0}">

					<c:forEach items="${registroQueryEsterno}" var="esterno">

						<table class="table table-striped" id="tabella">
							<thead>
								<tr id="colonne" class="text-center">
									<th scope="col">ID Tirocinio</th>
									<th scope="col">Responsabile</th>
									<th scope="col">Status</th>
									<th scope="col">CFU</th>
									<th scope="col">Ore Max</th>
									<th scope="col">Tipo tirocinio</th>
									<th scope="col">Registro tirocinio</th>
									<th scope="col">Operazioni</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th scope="row" id="id"><c:out value="${esterno.ID_Tirocinio}" /></th>
									<td id="responsabile"><c:out
											value="${esterno.nome_responsabile}" /> <c:out
											value="${esterno.cognome_responsabile}" /></td>
									<td id="status"><c:out value="${esterno.status}" /></td>
									<td id="cfu"><c:out value="${esterno.numeroCFU}" /></td>
									<td id="ore"><c:out value="${esterno.oreTotali}" /></td>
									<td id="tirocinio">Esterno</td>

									<!-- REGISTRO -->
									<td class="form-inline text-center" id="registro1">
										<form action="VisualizzaRegistroTirocinio">
											<input type="hidden" name="EMAIL"
												value="<c:out value="${studente.email}" />"> <a
												href="VisualizzaRegistroTirocinio"> <i id="registro"
												class="fas fa-book"></i>
											</a>
										</form>

										<form id="accettare">
											<i class="fas fa-check-square"></i>
										</form>
									</td>
									<!-- ...... -->


									<!-- OPERAZIONI -->
									<td><c:if test="${type=='tutoraziendale' }">

											<div class="panel-group">
												<div class="panel panel-default">
													<div class="panel-heading">
														<h6 class="panel-title">
															<a data-toggle="collapse" href="#collapse1">Scegli
																un'operazione </a>
														</h6>
													</div>
													<div id="collapse1" class="panel-collapse collapse">
														<ul class="list-group">
															<li class="list-group-item"><a class="list-item"
																href="CreaRelazione">Compila relazione</a></li>
															<li class="list-group-item"><a class="list-item"
																href="VisualizzaRelazione">Visualizza relazione</a></li>
															<li class="list-group-item"><a class="list-item"
																href="QuestionarioT">Compila questionario</a></li>
														</ul>

													</div>
												</div>
											</div>

										</c:if> <c:if test="${type=='tutoraccademico'}">

											<a href="ValutaRelazione" id="rel">Valuta relazione</a>



										</c:if> <c:if test="${type=='pdcd'}">

											<a href="">Approva registro</a>



										</c:if></td>
									<!-- ...... -->

								</tr>
							</tbody>
						</table>
					</c:forEach>
				</c:if>
			</c:if>
			<!-- fine TIROCINIO ESTERNO -->




			<!-- inizio TIROCINIO INTERNO -->
			<!-- SE L'UTENTE LOGGATO E' UNO TUTOR ACCADEMICO O IL PDCD  -->
			<c:if test="${type == 'tutoraccademico' || type == 'pdcd'}">
				<c:forEach items="${registroQueryInterno}" var="interno">

					<table class="table table-striped" id="tabella">
						<thead>
							<tr id="colonne">
								<th scope="col">ID Tirocinio</th>
								<th scope="col">Responsabile</th>
								<th scope="col">Status</th>
								<th scope="col">CFU</th>
								<th scope="col">Ore Max</th>
								<th scope="col">Tipo tirocinio</th>
								<th scope="col">Registro tirocinio</th>
								
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row" id="id"><c:out value="${interno.ID_Tirocinio}" /></th>
								<td id="responsabile"><c:out
										value="${interno.nome_responsabile}" /> <c:out
										value="${interno.cognome_responsabile}" /></td>
								<td id="status"><c:out value="${interno.status}" /></td>
								<td id="cfu"><c:out value="${interno.numeroCFU}" /></td>
								<td id="ore"><c:out value="${interno.oreTotali}" /></td>
								<td id="tirocinio">Interno</td>

								<!-- REGISTRO -->
								<td class="form-inline text-center">
									<form action="VisualizzaRegistroTirocinio">
										<input type="hidden" name="EMAIL"
											value="<c:out value="${studente.email}" />"> <a
											href="VisualizzaRegistroTirocinio"> <i id="registro"
											class="fas fa-book"></i>
										</a>
									</form>

									<form id="accettare">
										<i class="fas fa-check-square"></i>
									</form>
								</td>

								<!-- fine TIROCINIO INTERNO -->

							</tr>

						</tbody>
					</table>
				</c:forEach>
			</c:if>
		</div>

	</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>



<!-- <script>
	$(document).ready(function() {
		$("#accetta").click(function() {
			$(this).css("background-color", "green");
			$(this).css("color", "white");
			$(this).css("outline", "none");
			$(this).val("selezionato");
			$("#rifiuta").attr("disabled", "true");

		});
	});
</script> -->

