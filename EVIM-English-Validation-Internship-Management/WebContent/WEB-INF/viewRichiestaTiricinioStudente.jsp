<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<jsp:include page="navbarBlu.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="stiliCSS/stiliRichiesteTirocinio.css">

<title>Viesualizza richiesta</title>
</head>
<div class="container">
	<div class="row">

		<div class="col-lg-3">
			<jsp:include page="menu.jsp"></jsp:include>
		</div>

		<div class="col-lg-9"
			style="border: 1px solid #d7d7d7; background-color: white;">
			<p id="titolo" class="text-center">Richiesta di tirocinio
				curriculare</p>
			<hr>

			<c:if
				test="${arrayTirocinioEsterno.size() == 0 && arrayTirocinioIntero.size() == 0 }">
				<p>Non ci sono richieste di tirocinio!</p>
			</c:if>

			<table class="table table-striped" id="tabella">
				<!-- richieste di tirocinio ESTERNO di tale studente  -->
				<c:if test="${arrayTirocinioEsterno.size() > 0}">
					<c:forEach items="${arrayTirocinioEsterno}" var="esterno">
						<tbody>
							<tr id="numero">
								<td class="icon"><i class="fas fa-book-open"></i></td>
								<td class="">Tipo di tirocinio</td>
								<td class="text-center">Esterno</td>

							</tr>
							<tr>
								<td><i class="fas fa-sort-amount-down"></i></td>
								<td>Numero proposta</td>
								<td class="text-center"><c:out
										value="${esterno.ID_Proposta}"></c:out></td>
							</tr>
							<tr>
								<td class="icon"><i class="far fa-user"></i></td>
								<td class="">Tutor accademico</td>
								<td class="text-center"><c:out
										value="${esterno.ID_TutorAccademico}" /></td>
							</tr>
							<tr>
								<td class="icon"><i class="fa fa-user"></i></td>
								<td class="">Tutor aziendale</td>
								<td class="text-center"><c:out
										value="${esterno.ID_TutorAziendale}" /></td>
							</tr>
							<tr>
								<td class="icon"><i class="fas fa-toggle-on"></i></td>
								<td class="">Status</td>
								<td class="text-center"><i id="status"
									class="far fa-circle"
									title="<c:out value="${esterno.status}"/>"></i></td>
							</tr>
							<tr>
								<td class="icon"><i class="fas fa-circle"></i></td>
								<td class="">Numero CFU</td>
								<td class="text-center"><c:out value="${esterno.CFU}"></c:out></td>
							</tr>
							<tr>
								<td class="icon"><i class="fas fa-clock"></i></td>
								<td class="">Ore totali</td>
								<td class="text-center"><c:out value="${esterno.oreTotali}" /></td>
							</tr>
							<tr style="border-bottom: 4px solid #ddd;">
								<td class="icon"><i class="fas fa-paperclip"></i></td>
								<td><a href="#"><button type="button">Progetto
											formativo</button></a></td>
								<td></td>
							</tr>
							<tr style="background-color: white; border-color: transparent;">
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</c:forEach>
				</c:if>


				<!-- richieste di tirocinio INTERNO di tale studente  -->
				<c:if test="${arrayTirocinioInterno.size() > 0}">
					<c:forEach items="${arrayTirocinioInterno}" var="interno">
						<tbody>
							<tr id="numero">
								<td class="icon"><i class="fas fa-book-open"></i></td>
								<td class="">Tipo di tirocinio</td>
								<td class="text-center">Interno</td>
							</tr>
							<tr>
								<td class="icon"><i class="fas fa-sort-amount-down"></i></td>
								<td class="">Numero proposta</td>
								<td class="text-center"><c:out
										value="${interno.ID_Proposta}"></c:out></td>
							</tr>
							<tr>
								<td class="icon"><i class="fa fa-user"></i></td>
								<td class="">Tutor accademico</td>
								<td class="text-center"><c:out
										value="${interno.ID_tutorAccademico}" /></td>
							</tr>
							<tr>
								<td class="icon"><i class="fas fa-toggle-on"></i></td>
								<td class="">Status</td>
								<td class="text-center"><i id="status1"
									class="far fa-circle"
									title="<c:out value="${interno.status}"/>"></i></td>
							</tr>
							<tr>
								<td class="icon"><i class="fas fa-circle"></i></td>
								<td class="">Numero CFU</td>
								<td class="text-center"><c:out value="${interno.numeroCFU}"/></td>
							</tr>
							<tr>
								<td class="icon"><i class="fas fa-clock"></i></td>
								<td class="">Ore totali</td>
								<td class="text-center"><c:out value="${interno.oreTotali}" /></td>
							</tr>

							<tr>
								<td class="icon"><i class="fas fa-paperclip"></i></td>
								<td><a href="#"><button id="progettoForm" type="button">Progetto
											formativo</button></a></td>
								<td></td>
							</tr>
						</tbody>
					</c:forEach>
				</c:if>
			</table>
			<br>
		</div>
	</div>
</div>
<br>
<br>
<jsp:include page="footer.jsp"></jsp:include>


<script>
	/* status: riferito allo status di tirocinio esterno
	   status1: riferito allo status di tirocinio interno */
	$(document).ready(function() {
		if ($('i:contains(in approvazione)')) {
			$("#status, #status1").css("background-color", "yellow");
			$("#status, #status1").css("color", "black");
			$("#status, #status1").css("border-radius", "22px");
		} else if ($('i:contains(approvato)')) {
			$("#status, #status1").css("background-color", "green");
			$("#status, #status1").css("color", "black");
			$("#status, #status1").css("border-radius", "22px");
		} else if ($('i:contains(non approvato)')) {
			$("#status, #status1").css("background-color", "red");
			$("#status, #status1").css("color", "black");
			$("#status, #status1").css("border-radius", "22px");
		} else if ($('i:contains(proposto)')) {
			$("#status, #status1").css("background-color", "blue");
			$("#status, #status1").css("color", "black");
			$("#status, #status1").css("border-radius", "22px");
		}

	});
</script>