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
			<c:if test="${type == 'tutoraziendale'}">
				<!-- Se la lista di attività esterne e interne è vuota esce che non c'è nulla -->
				<c:if test="${listaAttivitaEsterno.size() == 0">
					<p>Non è stato effettuato nessun Tirocinio!</p>
				</c:if>
				<div class="col-lg-9" id=""
					style="border: 1px solid #d7d7d7; background-color: white;">
					<p id="titolo" style="font-size: 30px; color: #595959;">
						Registro di tirocinio</p>
					<hr>
					<c:if test="${listaAttivitaEsterno.size() > 0}">
						<c:forEach items="${listaAttivitaEsterno}" var="esterno">
							<div>
								<p>
									Studente:
									<c:out value="${esterno.nomeStudente}" />
									<c:out value="${esterno.cognomeStudente}" />
								</p>
								<p>
									Ore svolte:
									<c:out value="${esterno.oreSvolte}" />
								</p>
								<!-- <p>Numero attività svolte: #</p> -->
							</div>
						</c:forEach>
					</c:if>
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
										<td>Firma responsabile</td>
									</tr>
									<tr>
										<td></td>
										<td><c:out value="${esterno.attivita}" /></td>
										<td><c:out value="${esterno.data}" /></td>
										<td><c:out value="${esterno.orarioIngresso}" /></td>
										<td><c:out value="${esterno.orarioUscita}" /></td>
										<td><c:out value="${esterno.orarioUscita}" /> - <c:out
												value="${esterno.orarioIngresso}" /></td>
										
										<td><td><form action="" id="valutare">
												<i class="fas fa-check-square"></i> <i
													class="far fa-times-circle"></i>
											</form></td></td>

									</tr>

								</c:forEach>

							</c:if>



						</tbody>
					</table>
					<br> <br>
				</div>
			</c:if>

			<!-- SE L'UTENTE LOGGATO E' UN TUTOR ACCADEMICO-->
			<c:if test="${type == 'tutoraccademico'}">
				<!-- Se la lista di attività esterne e interne è vuota esce che non c'è nulla -->
				<c:if test="${listaAttivitaInterno.size() == 0">
					<p>Non è stato effettuato nessun Tirocinio!</p>
				</c:if>
				<div class="col-lg-9" id=""
					style="border: 1px solid #d7d7d7; background-color: white;">
					<p id="titolo" style="font-size: 30px; color: #595959;">
						Registro di tirocinio</p>
					<hr>

					<c:if test="${listaAttivitaInterno.size() > 0}">
						<c:forEach items="${listaAttivitaInterno}" var="interno">
							<div>
								<p>
									Studente:
									<c:out value="${interno.nomeStudente}" />
									<c:out value="${interno.cognomeStudente}" />
								</p>
								<p>
									Ore svolte:
									<c:out value="${interno.oreSvolte}" />
								</p>
								<!-- <p>Numero attività svolte: #</p> -->
							</div>
						</c:forEach>
					</c:if>



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
										<td>Firma responsabile</td>
									</tr>
									<tr>
										<td></td>
										<td><c:out value="${interno.attivita}" /></td>
										<td><c:out value="${interno.data}" /></td>
										<td><c:out value="${interno.orarioIngresso}" /></td>
										<td><c:out value="${interno.orarioUscita}" /></td>
										<td><c:out
												value="${interno.orarioUscita - interno.orarioIngresso}" /></td>

										<td><form action="" id="valutare">
												<i class="fas fa-check-square"></i> <i
													class="far fa-times-circle"></i>
											</form></td>
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