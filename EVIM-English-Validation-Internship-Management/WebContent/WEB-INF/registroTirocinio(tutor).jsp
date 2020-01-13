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
			<!-- SE L'UTENTE LOGGATO E' UN TUTOR AZIENDALEe-->

			<div class="col-lg-9"
				style="border: 1px solid #d7d7d7; background-color: white;">
				<p id="titolo" style="font-size: 30px; color: #595959;"
					class="text-center">Registro di tirocinio</p>
				<hr>

				<c:if test="${type=='tutoraccademico'}">
					<c:if
						test="${listaAttivitaEsterno.size() == 0 && listaAttivitaInterno.size()==0}">
						<p>Non è stato effettuato nessun Tirocinio!</p>
					</c:if>
				</c:if>

				<c:if test="${type == 'tutoraziendale' || type=='tutoraccademico'}">
					<!-- Se la lista di attività esterne e interne è vuota esce che non c'è nulla -->

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
							</div>

							<br>

							<table class="table table-striped"
								style="border: 1px solid #ddd;">
								<tbody>
									<tr style="background-color: #2C5278; color: white;">
										<td class="icon"><i class="fas fa-sort-amount-down"></i></td>
										<td class="">Attività svolta</td>
										<td>Data</td>
										<td>Ora ingresso</td>
										<td>Ora uscita</td>
										<td>Ore tot.</td>
									</tr>
									<tr>
										<td></td>
										<td><c:out value="${esterno.descrizione}" /></td>
										<td><c:out value="${esterno.data}" /></td>
										<td><c:out value="${esterno.orarioIngresso}" /></td>
										<td><c:out value="${esterno.orarioUscita}" /></td>
										<td><c:out value="${esterno.orarioUscita}" /> - <c:out
												value="${esterno.orarioIngresso}" /></td>

									</tr>

								</tbody>
							</table>
						</c:forEach>
					</c:if>
					<br>
					<br>
				</c:if>



				<!-- SE L'UTENTE LOGGATO E' UN TUTOR ACCADEMICO-->
				<c:if test="${type == 'tutoraccademico'}">
					<!-- Se la lista di attività esterne e interne è vuota esce che non c'è nulla -->
					<c:if
						test="${listaAttivitaInterno.size() == 0 && listaAttivitaEsterno.size()==0}">
						<p>Non è stato effettuato nessun Tirocinio!</p>
					</c:if>

					<c:if test="${listaAttivitaInterno.size() > 0}">
						<c:forEach items="${listaAttivitaInterno}" var="interno">
							<div>
								<p>
									<b>Studente:</b>
									<c:out value="${interno.nomeStudente}" />
									<c:out value="${interno.cognomeStudente}" />
								</p>
								<p>
									<b>Ore svolte:</b>
									<c:out value="${interno.oreSvolte}" />
								</p>

							</div>
						</c:forEach>
					</c:if>

					<br>

					<table class="table table-striped" style="border: 1px solid #ddd;">
						<tbody>
							<c:if test="${listaAttivitaInterno.size() > 0}">
								<c:forEach items="${listaAttivitaInterno}" var="interno">
									<tr style="background-color: #2C5278; color: white;">
										<th class="text-center" class="icon"><i class="fas fa-sort-amount-down"></i></th>
										<th class="text-center">Attività svolta</th>
										<th class="text-center">Data</th>
										<th class="text-center">Ora ingresso</th>
										<th class="text-center">Ora uscita</th>
										<th class="text-center">Ore tot.</th>
										<th class="text-center">Firma responsabile</th>
									</tr>
									<tr>
										<td></td>
										<td class="text-center"><c:out value="${interno.descrizione}" /></td>
										<td class="text-center"><c:out value="${interno.data}" /></td>
										<td class="text-center"><c:out value="${interno.orarioIngresso}" /></td>
										<td class="text-center"><c:out value="${interno.orarioUscita}" /></td>
										<td class="text-center"><c:out
												value="${interno.orarioUscita - interno.orarioIngresso}" /></td>

										<td class="text-center">
										<form action="" id="valutare">
												<i class="fas fa-check-square"></i> <i
													class="far fa-times-circle"></i>
										</form></td>
									</tr>

								</c:forEach>

							</c:if>



						</tbody>
					</table>
					<br>
					<br>
				</c:if>
			</div>
		</div>
	</div>
	<br>

</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>