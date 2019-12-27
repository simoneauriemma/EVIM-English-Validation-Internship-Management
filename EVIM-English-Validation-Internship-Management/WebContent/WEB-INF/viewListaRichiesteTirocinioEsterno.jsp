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

		<div class="col-lg-9" id="col-9">

			<p id="titolo" class="text-center">Richieste tirocinio esterno</p>
			<hr>
			<%
				int n = 0;
			%>

			<c:forEach items="${arrayTirocinioEsterno}" var="esterno">
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
											<td class="text-center"><c:out value="${esterno.status}" />
												<i class="far fa-circle"></i></td>
										</tr>
										<tr>
											<td class="icon"><i class="far fa-calendar-alt"></i></td>
											<td class="">Data</td>
											<td class="text-center"><c:out value="${esterno.data}" /></td>
										</tr>
										<tr>
											<td class="icon"><i class="fas fa-clock"></i></td>
											<td class="">Ore totali</td>
											<td class="text-center"><c:out
													value="${esterno.oreTotali}" /></td>
										</tr>
										<tr>
											<td class="icon"><i class="fas fa-circle"></i></td>
											<td class="">Num. CFU</td>
											<td class="text-center">#<c:out value="${esterno.CFU}" /></td>
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
										<tr>
											<td class="icon"><i class="fas fa-file-signature"></i></td>
											<td class="">Accettazione PdCD</td>
											<td class="text-center"><c:out
													value="${esterno.firmaPdCD}" /></td>
										</tr>
										<tr>
											<td class="icon"><i class="fas fa-file-signature"></i></td>
											<td class="">Accettazione Azienda</td>
											<td class="text-center"><c:out
													value="${esterno.firmaAzienda}" /></td>
										</tr>
										<tr>
											<td class="icon"><i class="fas fa-file-signature"></i></td>
											<td class="">Accettazione Tutor Aziendale</td>
											<td class="text-center"><c:out
													value="${esterno.firmaTutorAziendale}" /></td>
										</tr>
										<tr>
											<td class="icon"><i class="fas fa-file-signature"></i></td>
											<td class="">Accettazione Tutor Accademico</td>
											<td class="text-center"><c:out
													value="${esterno.firmaTutorAccademico}" /></td>
										</tr>

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

		</div>
	</div>
</div>
<br>
<jsp:include page="footer.jsp"></jsp:include>

