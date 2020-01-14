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
					<c:set var="count" value="0" scope="session" />
					<c:set var="sumOreE" value="0" scope="session" />

					<c:if test="${listaAttivitaEsterno.size() > 0}">
						<c:forEach items="${listaAttivitaEsterno}" var="esterno">
							<c:set var="count" value="${count + 1}" scope="session" />
							<div>
								<c:if test="${count == 1}">
									<span style="visibility: hidden;" id="idRegEst"><c:out
											value="${esterno.idRegistro}" /></span>
									<p>
										Tirocinio presso:
										<c:out value="${esterno.nomeAzienda}" />
									</p>
								</c:if>
							</div>
							<c:if test="${esterno.firmaResponsabile == true}">
								<c:set var="sumOreE" value="${sumOreE + esterno.oreSvolte}"
									scope="session" />
							</c:if>
						</c:forEach>
						<p>
							Ore svolte convalidate:
							<c:out value="${sumOreE}" />
						</p>
					</c:if>


					<c:set var="count" value="0" scope="session" />
					<c:set var="sumOre" value="0" scope="session" />
					<c:if test="${listaAttivitaInterno.size() > 0}">
						<c:forEach items="${listaAttivitaInterno}" var="interno">
							<c:set var="count" value="${count + 1}" scope="session" />
							<c:if test="${count == 1}">
								<div>
									<span style="visibility: hidden;" id="idRegInt"><c:out
											value="${interno.idRegistro}" /></span>
									<p>
										Tutor ospitante:
										<c:out value="${interno.nomeTutorAcc}" />
										<c:out value="${interno.cognomeTutorAcc}" />
									</p>
								</div>
							</c:if>
							<c:if test="${interno.firmaResponsabile == true}">
								<c:set var="sumOre" value="${sumOre + interno.oreSvolte}"
									scope="session" />
							</c:if>
						</c:forEach>
						<p>
							Ore svolte convalidate:
							<c:out value="${sumOre}" />
						</p>

						<c:set var="count" value="0" scope="session" />
					</c:if>
					<br>

					<table class="table table-striped" style="border: 1px solid #ddd;">
					
						<tbody>
						<tr style="background-color: #2C5278; color: white;">
										<td class="icon"><i class="fas fa-sort-amount-down"></i></td>
										<td class="">Attività svolta</td>
										<td>Data</td>
										<td>Ora ingresso</td>
										<td>Ora uscita</td>
										<td>Ore tot.</td>
										<td>Firma responsabile</td>
									</tr>
							<c:if test="${listaAttivitaEsterno.size() > 0}">
								<c:forEach items="${listaAttivitaEsterno}" var="esterno">

									
									<tr>
										<td></td>
										<td><c:out value="${esterno.descrizione}" /></td>
										<td><c:out value="${esterno.data}" /></td>
										<td><c:out value="${esterno.orarioIngresso}" />:00</td>
										<td><c:out value="${esterno.orarioUscita}" />:00</td>
										<td><c:out
												value="${esterno.orarioUscita - esterno.orarioIngresso}" /></td>
										<td><c:if test="${esterno.firmaResponsabile == true}">
												<i id="accettare" class="fas fa-check-square"
													style="color: green;"></i>
											</c:if> <c:if test="${esterno.firmaResponsabile == false}">
												In Valutazione
											</c:if></td>
									</tr>

								</c:forEach>

							</c:if>
							<c:if test="${listaAttivitaInterno.size() > 0}">

								<c:forEach items="${listaAttivitaInterno}" var="interno">
									<tr>
										<td></td>
										<td><c:out value="${interno.descrizione}" /></td>
										<td><c:out value="${interno.data}" /></td>
										<td id="ingresso"><c:out
												value="${interno.orarioIngresso}" />:00</td>
										<td><c:out value="${interno.orarioUscita}" />:00</td>
										<td><c:out
												value="${interno.orarioUscita - interno.orarioIngresso}" /></td>

										<td><c:if test="${interno.firmaResponsabile == true}">
												<i id="accettare" class="fas fa-check-square"
													style="color: green;"></i>

											</c:if>
										
										<c:if test="${interno.firmaResponsabile == false}">
												In Valutazione
											</c:if></td>

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
									<form action="CompilaRegistro" method="post">
										<input type="hidden" id="IDRegistro" name="IDRegistro"
											value="">
										<table>
											<tr>
												<td>Attività svolta:</td>
												<td><textarea name="attivita" rows="4" cols="45"
														required></textarea></td>
											</tr>
											<tr>
												<td>Data:</td>
												<td><input type="date" name="data" required></td>
											</tr>
											<tr>
												<td>Ora ingresso:</td>
												<td><input type="number" id="oraIngresso"
													name="orarioIngresso" min="9" max="18" step=1 required></td>
											</tr>
											<tr>
												<td>Ora uscita:</td>
												<td><input type="number" id="oraUscita"
													name="orarioUscita" min="9" max="18" step=1 required></td>
											</tr>
										</table>
										<div id="button-container1">
											<br> <br>
											<!-- location.href='VisualizzaRegistroTirocinio' -->
											<button type="submit" class="btn btn-secondary" id="button1"
												name="approva" onclick="">APPROVA</button>
										</div>
									</form>
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

	if (document.getElementById("idRegInt") == null)
		document.getElementById("IDRegistro").value = document.getElementById("idRegEst").innerHTML;
	else
		document.getElementById("IDRegistro").value = document.getElementById("idRegInt").innerHTML;



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
