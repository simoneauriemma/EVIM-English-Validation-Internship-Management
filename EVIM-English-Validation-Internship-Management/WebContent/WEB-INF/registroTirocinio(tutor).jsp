<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
#button {
	width: 200px;
	text-align: center;
}

#button-container {
	text-align: center;
}
</style>
<jsp:include page="navbarBlu.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<title>Registro tirocinio</title>
</head>
<body>
	<div class="container">
		<div class="row">

			<div class="col-lg-3">
				<jsp:include page="menu.jsp"></jsp:include>
			</div>
			<!-- SE L'UTENTE LOGGATO E' UN TUTOR AZIENDALE-->
			<c:if
				test="${utenteLoggato == 'TutorAziendale'}">
				<!-- Se la lista di attività esterne e interne è vuota esce che non c'è nulla -->
				<c:if
					test="${listaAttivitaEsterno.size() == 0">
					<p>Non è stato effettuato nessun Tirocinio!</p>
				</c:if>
				<div class="col-lg-9" id=""
					style="border: 1px solid #d7d7d7; background-color: white;">
					<p id="titolo" style="font-size: 30px; color: #595959;">
						Registro di tirocinio</p>
					<hr>

					<div>
						<p>Studente: <c:out value="${studente.name}" /><c:out value="${studente.surname}" /></p>
						<p>Ore svolte: #</p>
						<p>Numero attività svolte: #</p>
					</div>



					<br>

					<table class="table table-striped" style="border: 1px solid #ddd;">
						<tbody>
							<c:if test="${listaAttivitaEsterno.size() > 0}">
								<c:forEach items="${listaAttivitaEsterno}" var="esterno">
									<tr style="background-color: #2C5278; color: white;">
										<td class="icon"><i class="fas fa-sort-amount-down"></i></td>
										<td class="">Attività svolta</td>
										<td>Data</td>
										<td>Ora ingresso</td>
										<td>Ora uscita</td>
										<td>Ore tot.</td>
										<td>Firma Tirocinante</td>
										<td>Firma responsabile</td>
									</tr>
									<tr>
										<td></td>
										<td><c:out value="${esterno.attivita}" /></td>
										<td><c:out value="${esterno.data}" /></td>
										<td><c:out value="${esterno.orarioIngresso}" /></td>
										<td><c:out value="${esterno.orarioUscita}" /></td>
										<td>#</td>
										<td>#</td>
										<td><select class="form-control" id="sel1" onchange=""
											name="sel1">
												<option value="approva" selected>Approva</option>
												<option value="rifiuta">Rifiuta</option>
										</select></td>
									</tr>

								</c:forEach>

							</c:if>



						</tbody>
					</table>
					<br> <br>
				</div>
			</c:if>
			
				<!-- SE L'UTENTE LOGGATO E' UN TUTOR ACCADEMICO-->
			<c:if
				test="${utenteLoggato == 'TutorAccademico'}">
				<!-- Se la lista di attività esterne e interne è vuota esce che non c'è nulla -->
				<c:if
					test="${listaAttivitaInterno.size() == 0">
					<p>Non è stato effettuato nessun Tirocinio!</p>
				</c:if>
				<div class="col-lg-9" id=""
					style="border: 1px solid #d7d7d7; background-color: white;">
					<p id="titolo" style="font-size: 30px; color: #595959;">
						Registro di tirocinio</p>
					<hr>

					<div>
						<p>Studente: <c:out value="${studente.name}" /><c:out value="${studente.surname}" /></p>
						<p>Ore svolte: #</p>
						<p>Numero attività svolte: #</p>
					</div>



					<br>

					<table class="table table-striped" style="border: 1px solid #ddd;">
						<tbody>
							<c:if test="${listaAttivitaInterno.size() > 0}">
								<c:forEach items="${listaAttivitaInterno}" var="interno">
									<tr style="background-color: #2C5278; color: white;">
										<td class="icon"><i class="fas fa-sort-amount-down"></i></td>
										<td class="">Attività svolta</td>
										<td>Data</td>
										<td>Ora ingresso</td>
										<td>Ora uscita</td>
										<td>Ore tot.</td>
										<td>Firma Tirocinante</td>
										<td>Firma responsabile</td>
									</tr>
									<tr>
										<td></td>
										<td><c:out value="${interno.attivita}" /></td>
										<td><c:out value="${interno.data}" /></td>
										<td><c:out value="${interno.orarioIngresso}" /></td>
										<td><c:out value="${interno.orarioUscita}" /></td>
										<td>#</td>
										<td>#</td>
										<td><select class="form-control" id="sel1" onchange=""
											name="sel1">
												<option value="approva" selected>Approva</option>
												<option value="rifiuta">Rifiuta</option>
										</select></td>
									</tr>

								</c:forEach>

							</c:if>



						</tbody>
					</table>
					<br> <br>
				</div>
			</c:if>
			
		</div>
	</div>
	<br>

</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>