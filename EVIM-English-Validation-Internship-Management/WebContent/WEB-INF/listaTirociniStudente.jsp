<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="navbarBlu.jsp"></jsp:include>

<head>
<link rel="stylesheet" href="stiliCSS/stiliListaTirocini.css">
<meta charset="ISO-8859-1">

<script src="jquery-3.4.1.min.js"></script>
<title>Lista tirocini</title>
</head>

<div class="container">
	<div class="row">

		<div class="col-lg-3">

			<jsp:include page="menu.jsp"></jsp:include>

		</div>
		<div class="col-lg-9" id="col-9">

			<p id="titolo" class="text-center">Lista tirocini</p>
			<hr>
			<br>

			<!-- SE L'UTENTE LOGGATO E' UNO STUDENTE-->
			<c:if test="${type == 'studente'}">
				<!-- Se la lista di tirocini esterni è vuota esce che non c'è nulla -->
				<c:if
					test="${registroQueryEsterno.size() == 0 && registroQueryInterno.size() == 0}">
					<p style="text-align: center;">Nessun tirocinio in corso!</p>
				</c:if>


				<table class="table table-striped" id="tabella">
					<!-- Lista tirocini ESTERNI -->
					<thead>
							<tr id="colonne" class="text-center">
								<th scope="col">ID Tirocinio</th>
								<th scope="col">Status</th>
								<th scope="col">CFU</th>
								<th scope="col">Ore Max</th>
								<th scope="col">Tipo tirocinio</th>
								<th scope="col">Registro tirocinio</th>
								<th scope="col">Operazione</th>
							</tr>
						</thead>
					
						<tbody>
						<c:if test="${registroQueryEsterno.size() > 0}">
							<c:forEach items="${registroQueryEsterno}" var="esterno">
								<tr>
									<th scope="row" id="id"><c:out
											value="${esterno.ID_Tirocinio}" /></th>
									<td id="status"><c:out value="${esterno.status}" /></td>
									<td id="cfu"><c:out value="${esterno.numeroCFU}" /></td>
									<td id="ore"><c:out value="${esterno.oreTotali}" /></td>
									<td id="tirocinio">Esterno</td>

									<!-- REGISTRO -->
									<td class="form-inline text-center" id="registro1">
										<form action="CompilaRegistro">
											<input type="hidden" name="IDRegistro"
												value="<c:out value="${esterno.ID_Registro}" />"> <a
												href="VisualizzaRegistroTirocinio"> <i id="registro"
												class="fas fa-book"></i>
											</a>
										</form> <!-- <a href="#"> <i id="accettare" class="fas fa-check-square"></i>
									</a> -->
									</td>

									<!-- OPERAZIONI -->
									<td id="operazioni"><c:if
											test="${esterno.ID_Questionario == 0 && esterno.registro_status == 'completato'}">
											<a href="QuestionarioS" id="op">Compila questionario</a>
										</c:if></td>
								</tr>
							</c:forEach>
							</c:if>
							<c:if test="${registroQueryInterno.size() > 0}">
								<c:forEach items="${registroQueryInterno}" var="interno">
									<tr>
										<th scope="row" id="id"><c:out
												value="${interno.ID_Tirocinio}" /></th>
										<td id="status"><c:out value="${interno.status}" /></td>
										<td id="cfu"><c:out value="${interno.numeroCFU}" /></td>
										<td id="ore"><c:out value="${interno.oreTotali}" /></td>
										<td id="tirocinio">Interno</td>
										<td class="form-inline text-center" id="registro1">
											<form action="CompilaRegistro">
												<input type="hidden" name="IDRegistro"
													value="<c:out value="${interno.ID_Registro}" />"> <a
													href="VisualizzaRegistroTirocinio"> <i id="registro"
													class="fas fa-book"></i>
												</a>
											</form> <!-- <a href="#"> <i id="accettare" class="fas fa-check-square"></i>
									</a> -->
										</td>
										<td>
										-
										</td>

									</tr>
								</c:forEach>
							</c:if>
						</tbody>

					</c:if>
					
				</table>
			
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


