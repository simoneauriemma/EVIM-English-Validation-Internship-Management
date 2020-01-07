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

			<p id="titolo" class="text-center">Lista
				tirocini</p>
			<hr>

			<!-- se entrambi gli array di tirocini sono vuoti esce che non c'è nulla -->
			<c:if
				test="${registroQueryEsterno.size()==0 || registroQueryInterno.size()==0}">
				<p>Nessun tirocinio in corso!</p>
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
									<th scope="row"><c:out value="${esterno.ID_Tirocinio}" /></th>
									<td>#</td>
									<td><c:out value="${esterno.status}" /></td>
									<td><c:out value="${esterno.NumeroCFU}" /></td>
									<td><c:out value="${esterno.OreTotali}" /></td>
									<td>Esterno</td>
									
									<!-- REGISTRO -->
									<td class="form-inline"><a href="VisualizzaRegistroTirocinio"> <i id="registro"
											class="fas fa-book"></i>
									</a> <a href="#"> <i id="accettare" class="fas fa-check-square"></i>
									</a>
									</td>
									<!-- ...... -->
									
									
									<!-- OPERAZIONI -->
									<td>
										<div class="form-group">
											<select class="form-control" id="select"
												onchange="getTutors(this.value)" name="sel1">
												<option selected>--select an option--</option>
												
												<c:if test="${type=='tutoraziendale' }">
													<option value="#">Visualizza progetto</option>
													<option value="#">Compila
														relazione</option>
													<option value="#">Visualizza
														relazione</option>
													<option value="questionarioT.jsp">Compila
														questionario</option>
													<option value="#">Approva registro</option>
												</c:if>
												
												
												<c:if test="${type=='tutoraccademico' || type=='pdcd'}">
													<option value="#">Visualizza progetto</option>
													<option value="#">Compila
														relazione</option>
												</c:if>
											</select>
										</div>
									</td>
									<!-- ...... -->
									
								</tr>
							</tbody>
						</table>
					</c:forEach>
				</c:if>
			</c:if>
			<!-- fine TIROCINIO ESTERNO -->




			<!-- fine TIROCINIO INTERNO -->
			<!-- SE L'UTENTE LOGGATO E' UNO TUTOR ACCADEMICO O IL PDCD  -->
			<c:if test="${type == 'tutoraccademico' || type == 'pdcd'}">
				<c:forEach items="registroQueryInterno" var="interno">

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
								<th scope="col">Operazioni</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row"><c:out value="${interno.ID_Tirocinio}" /></th>
								<td>#</td>
								<td><c:out value="${interno.status}" /></td>
								<td><c:out value="${interno.NumeroCFU}" /></td>
								<td><c:out value="${interno.OreTotali}" /></td>
								<td>Interno</td>

								<!-- REGISTRO -->
								<td class="form-inline text-center">
									<form action="VisualizzaRegistroTirocinio">
									<input type="hidden" name="email" value="<c:out value="${studente.email}" />">
									<a href="VisualizzaRegistroTirocinio">
										<i id="registro" class="fas fa-book"></i>
									</a></form>
								
									<form id="accettare">
										<i class="fas fa-check-square"></i>
									</form>
								</td>
								<!-- ...... -->

								<!-- OPERAZIONI -->
								<td>
									<div class="form-group">
										<select class="form-control" id="select"
											onchange="getTutors(this.value)" name="sel1">
											<option selected>--select an option--</option>
											<option value="">Visualizza progetto</option>
											<option value="questionarioT.jsp">Compila
												questionario</option>
										</select>
									</div>
								</td>
								<!-- ........ -->

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

