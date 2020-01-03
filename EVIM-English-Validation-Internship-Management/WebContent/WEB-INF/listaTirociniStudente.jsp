<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="navbarBlu.jsp"></jsp:include>

<head>
<link rel="stylesheet" href="stiliCSS/stiliListaTirocini.css">
<meta charset="ISO-8859-1">

<script src="jquery-3.4.1.min.js"></script>
<title>Lista tirocini </title>
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
			<br> <br>

			<!-- SE L'UTENTE LOGGATO E' UNO STUDENTE-->
			<c:if test="${type == 'studente' }">
				<!-- Se la lista di tirocini esterni è vuota esce che non c'è nulla -->
				<c:if
					test="${registroQueryEsterno.size() == 0 && registroQueryInterno.size() == 0}">
					<p>Nessun tirocinio in corso!</p>
				</c:if>


				<table class="table table-striped" id="tabella">
				<!-- Lista tirocini ESTERNI -->
					<c:if test="${registroQueryEsterno.size() > 0}">
						<c:forEach items="${registroQueryEsterno}" var="esterno">
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
									<td class="form-inline"><a href="#"> <i id="registro"
											class="fas fa-book"></i>
									</a> <a href="#"> <i id="accettare" class="fas fa-check-square"></i>
									</a></td>

									<!-- OPERAZIONI -->
									<td>
										<div class="form-group">
											<select class="form-control" id="select"
												onchange="getTutors(this.value)" name="sel1">
												<option selected>--select an option--</option>
												<option value="#">Visualizza progetto</option>
												<option value="questionarioS.jsp">Compila
													questionario</option>
											</select>
										</div>
									</td>
								</tr>
							</tbody>
						</c:forEach>
					</c:if>
					<!-- fine Lista tirocini esterni -->

					<!-- Lista tirocini INTERNO -->
					<c:if test="${registroQueryInterno.size() > 0}">
						<c:forEach items="${registroQueryInterno}" var="interno">
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
									<th scope="row"><c:out value="${interno.ID_Tirocinio}" /></th>
									<td>#</td>
									<td><c:out value="${interno.status}" /></td>
									<td><c:out value="${interno.NumeroCFU}" /></td>
									<td><c:out value="${interno.OreTotali}" /></td>
									<td>Interno</td>
									<td class="form-inline"><a href="#"> <i id="registro"
											class="fas fa-book"></i>
									</a> <a href="#"> <i id="accettare" class="fas fa-check-square"></i>
									</a></td>

									<td>
										<div class="form-group">
											<select class="form-control" id="select"
												onchange="getTutors(this.value)" name="sel1">
												<option selected>--select an option--</option>
												<option value="#">Visualizza progetto</option>
												<option value="questionarioS.jsp">Compila
													questionario</option>
											</select>
										</div>
									</td>
								</tr>
							</tbody>
						</c:forEach>
					</c:if>
				</table>
			</c:if>
		</div>
	</div>
</div>
<br>
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

