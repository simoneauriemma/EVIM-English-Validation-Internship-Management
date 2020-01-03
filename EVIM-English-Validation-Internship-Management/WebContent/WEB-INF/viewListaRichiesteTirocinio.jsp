<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="navbarBlu.jsp"></jsp:include>

<head>
<link rel="stylesheet" href="stiliCSS/stiliRichiesteTirocinio.css">
<meta charset="ISO-8859-1">

<script src="jquery-3.4.1.min.js"></script>
<title>Visualizza richieste tirocinio</title>
</head>


<div class="container">
	<div class="row">

		<div class="col-lg-3">

			<jsp:include page="menu.jsp"></jsp:include>

		</div>

		<!-- se l'utente è il tutor aziendale allora vede solo le richiete esterne -->
		<div class="col-lg-9" id="col-9">
			<p id="titolo" class="text-center">Elenco richieste di tirocinio</p>
			<hr>
			<%
				int n = 0;
				int y = 0;
			%>

			<!-- tutor accademico, tutor aziendale, azieda e presidente del consiglio didattico possono vedere le proprie richieste di tirocinio esterno -->
			<c:if
				test="${type== 'tutoraziendale' || type=='tutoraccademico' || type== 'pdcd' || type=='azienda'}">

				<c:forEach items="${arrayTirocinioEsterno}" var="esterno">

					<!-- se l'array di tirocini esterno è vuoto allora informa l'uetnte che non ci sono richieste -->
					<c:if test="${arrayTirocinioEsterno.size()==0 }">
						<p>Non ci sono richieste di tirocinio</p>
					</c:if>
					<%
						n = n + 1;
					%>

					<div class="accordion">
						<div class="card">

							<div id="heading<%=n%>" style="background-color: #2C5278">
								<h6 class="mb-0">
									<button data-toggle="collapse" id="nome-studente"
										data-target="#collapse<%=n%>" aria-expanded="true"
										aria-controls="collapse<%=n%>">
										<c:out value="${esterno.EMAIL}" />
									</button>
								</h6>
							</div>

							<div id="collapse<%=n%>" class="collapse"
								aria-labelledby="heading<%=n%>" data-parent="#accordion">
								<div class="card-body">
									<table class="table table-striped" id="tabella">
										<tbody>
											<tr>
												<td class="icon"><i class="fas fa-list-ol"></i></td>
												<td class="">Numero richiesta</td>
												<td class="text-center"><c:out
														value="${esterno.ID_TirocinioEsterno}" /></td>
											</tr>

											<!-- solo il tutor accademico e il pdcd sono interessati a sapere che questo è un tirocinio esterno. Per tutor aziendale e azienda è scontato  -->
											<c:if test="${type=='tutoraccademico' || type=='pdcd'}">
												<tr>
													<td class="icon"><i class="fas fa-book"></i></td>
													<td class="">Tipo tirocinio</td>
													<td class="text-center">Esterno</td>
												</tr>
											</c:if>
											<tr>
												<td class="icon"><i class="fa fa-user"></i></td>
												<td class="">Numero proposta</td>
												<td class="text-center"><c:out
														value="${esterno.ID_Proposta}" /></td>
											</tr>
											<tr>
												<td class="icon"><i class="fas fa-at"></i></td>
												<td class="">E-mail</td>
												<td class="text-center"><c:out value="${esterno.EMAIL}" /></td>
											</tr>
											<tr>
												<td class="icon"><i class="fas fa-toggle-on"></i></td>
												<td class="">Status</td>
												<td class="text-center"><i id="status"
													class="far fa-circle"
													title="<c:out value="${esterno.status}"/>"></i></td>
											</tr>
											<tr>
												<td class="icon"><i class="far fa-calendar-alt"></i></td>
												<td class="">Data</td>
												<td class="text-center"><c:out value="${esterno.data}" /></td>
											</tr>
											<tr>
												<td class="icon"><i class="fas fa-circle"></i></td>
												<td class="">Num. CFU</td>
												<td class="text-center"><c:out value="${esterno.CFU}" /></td>
											</tr>
											<tr>
												<td class="icon"><i class="fas fa-clock"></i></td>
												<td class="">Ore totali</td>
												<td class="text-center"><c:out
														value="${esterno.oreTotali}" /></td>
											</tr>
											<tr>
												<td class="icon"><i class="far fa-user"></i></td>
												<td class="">Tutor Accademico</td>
												<td class="text-center"><c:out
														value="${esterno.ID_TutorAccademico}" /></td>
											</tr>
											<tr>
												<td class="icon"><i class="fas fa-user"></i></td>
												<td class="">Tutor Aziendale</td>
												<td class="text-center"><c:out
														value="${esterno.ID_TutorAziendale}" /></td>
											</tr>

											<!-- se è loggato il pdcd non vede questa riga -->
											<c:if
												test="${type== 'tutoraziendale' || type=='tutoraccademico' || type=='azienda'}">
												<tr>
													<td class="icon"><i class="fas fa-file-signature"></i></td>
													<td class="">Accettazione PdCD</td>
													<td class="text-center"><c:out
															value="${esterno.firmaPdCD}" /></td>
												</tr>
											</c:if>

											<!-- se è loggato l'azienda non vede questa riga -->
											<c:if
												test="${type== 'tutoraziendale' || type=='tutoraccademico' || type== 'pdcd'}">
												<tr>
													<td class="icon"><i class="fas fa-file-signature"></i></td>
													<td class="">Accettazione Azienda</td>
													<td class="text-center"><c:out
															value="${esterno.firmaAzienda}" /></td>
												</tr>
											</c:if>

											<!-- se è loggato il tutor aziendale non vede questa riga -->
											<c:if
												test="${type=='tutoraccademico' || type== 'pdcd' || type=='azienda'}">
												<tr>
													<td class="icon"><i class="fas fa-file-signature"></i></td>
													<td class="">Accettazione Tutor Aziendale</td>
													<td class="text-center"><c:out
															value="${esterno.firmaTutorAziendale}" /></td>
												</tr>
											</c:if>

											<!-- se è loggato il tutor accademico non vede questa riga -->
											<c:if
												test="${type== 'tutoraziendale' || type== 'pdcd' || type=='azienda'}">
												<tr>
													<td class="icon"><i class="fas fa-file-signature"></i></td>
													<td class="">Accettazione Tutor Accademico</td>
													<td class="text-center"><c:out
															value="${esterno.firmaTutorAccademico}" /></td>
												</tr>
											</c:if>

											<tr>
												<td class="icon"><i class="fas fa-signature"></i></td>
												<td class="">Valutazione richiesta</td>
												<td class="text-center">
													<button class="bottone"
														onclick="ValuatareRichiesta?confermato=si&id=&azienda=azienda"
														id="accetta">
														<i class="fas fa-check-square"></i>
													</button>
													<button class="bottone"
														onclick="ValuatareRichiesta?confermato=no&id=&azienda=azienda"
														id="rifiuta">
														<i class="far fa-times-circle"></i>
													</button>
												</td>
											</tr>

										</tbody>
									</table>

								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>


			<!-- solo il presidente del consiglio didattico e il tutor accademico possono vedere le richieste di tirocnio interno -->
			<c:if test="${type=='tutoraccademico' || type=='pdcd' }">
				<c:forEach items="${arrayTirocinioInterno}" var="interno">

					<!-- se l'array gli array sono vuoti informa l'utente -->
					<c:if
						test="${arrayTirocinioInterno.size()==0 && arrayTirocinioEsterno.size()==0 }">
						<p>Non ci sono richieste di tirocinio</p>
					</c:if>

					<%
						y = y + n + 1;
					%>
					<div class="accordion">
						<div class="card">

							<div id="heading<%=y%>" style="background-color: #2C5278">
								<h6 class="mb-0">
									<button data-toggle="collapse" id="nome-studente"
										data-target="#collapse<%=y%>" aria-expanded="true"
										aria-controls="collapse<%=y%>" style="color: white;">
										<c:out value="${interno.EMAIL}" />
									</button>
								</h6>
							</div>

							<div id="collapse<%=y%>" class="collapse"
								aria-labelledby="heading<%=y%>" data-parent="#accordion">
								<div class="card-body">
									<table class="table table-striped">
										<tbody>
											<tr>
												<td class="icon"><i class="fa fa-user"></i></td>
												<td class="">Numero proposta</td>
												<td class="text-center"><c:out
														value="${interno.ID_Proposta}" /></td>
											</tr>
											<tr>
												<td class="icon"><i class="fa fa-user"></i></td>
												<td class="">Tipo tirocinio</td>
												<td class="text-center">Interno</td>
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
												<td class="">Num. CFU</td>
												<td class="text-center"><c:out
														value="${interno.numeroCFU}" /></td>
											</tr>
											<tr>
												<td class="icon"><i class="fas fa-clock"></i></td>
												<td class="">Ore totali</td>
												<td class="text-center"><c:out
														value="${interno.oreTotali}" /></td>
											</tr>
											<c:if test="${type == 'pdcd' }">
												<tr>
													<td class="icon"><i class="fas fa-file-signature"></i></td>
													<td class="">Firma Tutor Accademico</td>
													<td class="text-center"><c:out
															value="${interno.firmaTutorAccademico}" /></td>
												</tr>
											</c:if>
											<c:if test="${type == 'tutoraccademico' }">
												<tr>
													<td class="icon"><i class="fas fa-file-signature"></i></td>
													<td class="">Firma PdCD</td>
													<td class="text-center"><c:out
															value="${interno.firmaPdCD}" /></td>
												</tr>
											</c:if>
											<tr>
												<td class="icon"><i class="fas fa-signature"></i></td>
												<td class="">Valutazione richiesta</td>

												<td class="text-center">
													<button class="bottone"
														onclick="ValuatareRichiesta?confermato=si&id=<>"
														id="accetta">
														<i class="fas fa-check-square"></i>
													</button>
													<button class="bottone"
														onclick="ValuatareRichiesta?confermato=no&id=<>"
														id="rifiuta">
														<i class="far fa-times-circle"></i>
													</button>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>

		</div>
	</div>
</div>
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