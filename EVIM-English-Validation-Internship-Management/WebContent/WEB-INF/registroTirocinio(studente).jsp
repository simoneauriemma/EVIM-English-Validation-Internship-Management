<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
#button {
	width: 200px;
}

#button-container, #button-container1 {
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
			<!-- SE L'UTENTE LOGGATO E' UNO STUDENTEe-->
			<c:if test="${type == 'studente' }">
				<!-- Se la lista di attività esterne e interne è vuota esce che non c'è nulla -->
				<c:if
					test="${listaAttivitaEsterno.size() == 0 && listaAttivitaInterno.size()==0}">
					<p>Non è stato effettuato nessun Tirocinio!</p>
				</c:if>

				<div class="col-lg-9"
					style="border: 1px solid #d7d7d7; background-color: white;">
					<p id="titolo" style="font-size: 30px; color: #595959;">
						Registro di tirocinio</p>
					<hr>
					<%
						int n = 0;
					%>
					<c:if test="${listaAttivitaEsterno.size() > 0}">
						<c:forEach items="${listaAttivitaEsterno}" var="esterno">
							<div>

								<p>
									Tirocinio presso:
									<c:out value="${esterno.nomeAzienda}" />
								</p>

								<p>
									Ore svolte:
									<c:out value="${esterno.oreSvolte}" />
								</p>
							</div>
						</c:forEach>
					</c:if>

					<c:if test="${listaAttivitaInterno.size() > 0}">
						<c:forEach items="${listaAttivitaInterno}" var="interno">
							<div>
								<p>
									Tutor ospitante:
									<c:out value="${interno.nomeTutorAcc}" />
									<c:out value="${interno.cognomeTutorAcc}" />
								</p>

								<p>
									Ore svolte:
									<c:out value="${interno.oreSvolte}" />
								</p>
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
										<td><c:out value="${esterno.descrizione}" /></td>
										<td><c:out value="${esterno.data}" /></td>
										<td><c:out value="${esterno.orarioIngresso}" /></td>
										<td><c:out value="${esterno.orarioUscita}" /></td>
										<td><c:out
												value="${esterno.orarioUscita - esterno.orarioIngresso}" />
										</td>
										<td><a href="#"><i id="accettare"
												class="fas fa-check-square"></i></a></td>
									</tr>

								</c:forEach>

							</c:if>
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
										<td><c:out value="${interno.descrizione}" /></td>
										<td><c:out value="${interno.data}" /></td>
										<td id="ingresso"><c:out
												value="${interno.orarioIngresso}" /></td>
										<td><c:out value="${interno.orarioUscita}" /></td>
										<td><c:out
												value="${interno.orarioUscita - interno.orarioIngresso}" /></td>

										<td><a href="#"><i id="accettare"
												class="fas fa-check-square"></i></a></td>
									</tr>


								</c:forEach>
							</c:if>
						</tbody>
					</table>
					<br>
					<div id="button-container">
						<button type="submit" class="btn btn-secondary" id="button"
							data-toggle="modal" data-target="#exampleModalLong<%=n%>">Aggiungi
							attività</button>
					</div>
					<br>

					<div class="modal fade" id="exampleModalLong<%=n%>" tabindex="-1"
						role="dialog" aria-labelledby="exampleModalLongTitle"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLongTitle">Nuova
										attività</h5>
									<button type="button" class="close" data-dismiss="modal"
										id="chiudere" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<table>
										<tr>
											<td>Attività svolta:</td>
											<td><textarea rows="1" cols="30"></textarea></td>
										</tr>
										<tr>
											<td>Data:</td>
											<td><input type="date"></td>
										</tr>
										<tr>
											<td>Ora ingresso:</td>
											<td><input type="time"></td>
										</tr>
										<tr>
											<td>Ora uscita:</td>
											<td><input type="time"></td>
										</tr>
										<tr>
											<td>Ore totali:</td>
											<td><input type="number" style="width: 77px;"></td>
										</tr>
									</table>
									<div id="button-container1">
										<br> <br>
										<form action="VisualizzaRegistroTirocinio">
										<button type="submit" class="btn btn-secondary" id="button1"  name="approva"
										onclick="location.href='VisualizzaRegistroTirocinio'">APPROVA</button>
										
										</form>
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:if>
		</div>
	</div>
	<br>

</body>
<jsp:include page="footer.jsp"></jsp:include>

<script>
	function myFunction() {
		alert("I am an alert box!");
	}

	<!--
	function calculateHours() {
		var start = $('#ingresso').html();
		var end = $('#uscita').html();
		var total = end - start;
		var result = $('#tdOre');
		result.val(total);

	}
	-->
</script>
</html>
