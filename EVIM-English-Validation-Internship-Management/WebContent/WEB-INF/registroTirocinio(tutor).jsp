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
					<!-- Se la lista di attività esterne e interne è vuota esce che non c'è nulla -->
					<c:if
						test="${listaAttivitaEsterno.size() == 0 && listaAttivitaInterno.size()==0}">
						<br>
						<p class="text-center">Non è stato effettuato nessun tirocinio!</p>
					</c:if>
				</c:if>

				<c:if test="${type == 'tutoraziendale' || type=='tutoraccademico'}">


					<c:if test="${listaAttivitaEsterno.size() > 0}">
						<c:set var="count" value="0" scope="session" />
						<c:set var="sumOre" value="0" scope="session" />
						<c:forEach items="${listaAttivitaEsterno}" var="esterno">
							<c:set var="count" value="${count + 1}" scope="session" />
							<c:if test="${count == 1}">
								<div>
									<span style="visibility: hidden;" id="idRegEst"><c:out
											value="${esterno.idRegistro}" /></span>
									<p>
										Studente:
										<c:out value="${esterno.nomeStudente}" />
										<c:out value="${esterno.cognomeStudente}" />
									</p>
								</div>
							</c:if>
							<c:if test="${esterno.firmaResponsabile == true}">
								<c:set var="sumOre" value="${sumOre + esterno.oreSvolte}"
									scope="session" />
							</c:if>

						</c:forEach>
						<p>
							Ore svolte:
							<c:out value="${sumOre}" />
						</p>
						<br>

						<table class="table table-striped" style="border: 1px solid #ddd;">
							<tbody>
								<tr style="background-color: #2C5278; color: white;">
									<td class="icon"><i class="fas fa-sort-amount-down"></i></td>
									<th class="text-center">Attività svolta</th>
									<th class="text-center">Data</th>
									<th class="text-center">Ora ingresso</th>
									<th class="text-center">Ora uscita</th>
									<th class="text-center">Ore tot.</th>

									<th class="text-center">Firma responsabile</th>
								</tr>
								<c:forEach items="${listaAttivitaEsterno}" var="esterno">
									<tr>
										<td></td>
										<td class="text-center"><c:out
												value="${esterno.descrizione}" /></td>
										<td class="text-center"><c:out value="${esterno.data}" /></td>
										<td class="text-center"><c:out
												value="${esterno.orarioIngresso}" />:00</td>
										<td class="text-center"><c:out
												value="${esterno.orarioUscita}" />:00</td>
										<td class="text-center"><c:out
												value="${esterno.orarioUscita - esterno.orarioIngresso}" />
										</td>
										<td class="text-center"><c:if
												test="${esterno.firmaResponsabile == false}">
												<form action="ApprovaAttivita" id="valutare">
													<input type="hidden" name="idAttivita"
														value="${esterno.idAttivita}">
													<button class="fas fa-check-square"
														style="background: trasparent; border: none;"
														name="modifica" value="approva"></button>
												</form>
											</c:if> <c:if test="${esterno.firmaResponsabile == true}">
												<i class="fas fa-check-square" style="color: green;"></i>
											</c:if></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

					</c:if>
					<br>
					<br>
				</c:if>



				<!-- SE L'UTENTE LOGGATO E' UN TUTOR ACCADEMICO-->
				<c:if test="${type == 'tutoraccademico'}">
					
					
					<c:set var="count" value="0" scope="session" />
					<c:set var="sumOre" value="0" scope="session" />
					<c:if test="${listaAttivitaInterno.size() > 0}">
						<c:forEach items="${listaAttivitaInterno}" var="interno">
							<c:set var="count" value="${count + 1}" scope="session" />
							<c:if test="${count == 1}">
								<div>
									<p>
										<b>Studente:</b>
										<c:out value="${interno.nomeStudente}" />
										<c:out value="${interno.cognomeStudente}" />
									</p>


								</div>
							</c:if>
							<c:if test="${interno.firmaResponsabile == true}">
								<c:set var="sumOre" value="${sumOre + interno.oreSvolte}"
									scope="session" />
							</c:if>
						</c:forEach>
						<p>
							<b>Ore svolte:</b>
							<c:out value="${sumOre}" />
						</p>
					</c:if>

					<br>

					<table class="table table-striped" style="border: 1px solid #ddd;">
						<tbody>
							<c:if test="${listaAttivitaInterno.size() > 0}">
								<tr style="background-color: #2C5278; color: white;">
									<th class="text-center" class="icon"><i
										class="fas fa-sort-amount-down"></i></th>
									<th class="text-center">Attività svolta</th>
									<th class="text-center">Data</th>
									<th class="text-center">Ora ingresso</th>
									<th class="text-center">Ora uscita</th>
									<th class="text-center">Ore tot.</th>
									<th class="text-center">Firma responsabile</th>
								</tr>
								<c:forEach items="${listaAttivitaInterno}" var="interno">
									<tr>
										<td></td>
										<td class="text-center"><c:out
												value="${interno.descrizione}" /></td>
										<td class="text-center"><c:out value="${interno.data}" /></td>
										<td class="text-center"><c:out
												value="${interno.orarioIngresso}" />:00</td>
										<td class="text-center"><c:out
												value="${interno.orarioUscita}" />:00</td>
										<td class="text-center"><c:out
												value="${interno.orarioUscita - interno.orarioIngresso}" /></td>

										<td class="text-center"><c:if
												test="${interno.firmaResponsabile == false}">
												<form action="ApprovaAttivita" id="valutare">
													<input type="hidden" name="idAttivita"
														value="${interno.idAttivita}">
													<button class="fas fa-check-square"
														style="background: trasparent; border: none;"
														name="modifica" value="approva"></button>
												</form>
											</c:if> <c:if test="${interno.firmaResponsabile == true}">
												<i class="fas fa-check-square" style="color: green;"></i>
											</c:if></td>
									</tr>
									<c:set var="count" value="${count + 1}" scope="session" />
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