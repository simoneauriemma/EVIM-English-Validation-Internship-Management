<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="navbarBlu.jsp"></jsp:include>

<head>
<link rel="stylesheet" href="stiliCSS/stiliRichiesteTirocinio.css">
<meta charset="ISO-8859-1">

<script src="jquery-3.4.1.min.js"></script>
<title>Visualizza richieste tirocinio interno</title>
</head>

<div class="container">
	<div class="row">

		<div class="col-lg-3">

			<jsp:include page="menu.jsp"></jsp:include>

		</div>

		<div class="col-lg-9" id="col-9">

			<p id="titolo" class="text-center">Richieste tirocinio interne ed
				esterne</p>
			<hr>
			<%
				int n = 0;
			%>
			<c:if test="${arrayTirocinioInterno.size() > 0}">

				<c:forEach items="${arrayTirocinioInterno}" var="interno">
					<%
						n = n + 1;
					%>
					<div class="accordion">
						<div class="card">

							<div id="heading<%=n%>" style="background-color: #2C5278">
								<h6 class="mb-0">
									<button data-toggle="collapse" id="nome-studente"
										data-target="#collapse<%=n%>" aria-expanded="true"
										aria-controls="collapse<%=n%>" style="color: white;">
										<c:out value="${interno.EMAIL}" />
									</button>
								</h6>
							</div>

							<div id="collapse<%=n%>" class="collapse"
								aria-labelledby="heading<%=n%>" data-parent="#accordion">
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
												<td class="icon"><i class="fas fa-toggle-on"></i></td>
												<td class="">Status</td>
												<td class="text-center"><c:out
														value="${interno.status}" /><i id="status"
													class="far fa-circle"></i></td>
											</tr>
											<tr>
												<td class="icon"><i class="fas fa-circle"></i></td>
												<td class="">Num. CFU</td>
												<td class="text-center"><c:out
														value="${interno.numeroCFU}" /></td>
											</tr>
											<tr>
												<td class="icon"><i class="fas fa-file-signature"></i></td>
												<td class="">Firma Tutor Accademico</td>
												<td class="text-center"><c:out
														value="${interno.firmaTutorAccademico}" /></td>
											</tr>
											<tr>
												<td class="icon"><i class="fas fa-file-signature"></i></td>
												<td class="">Firma PdCD</td>
												<td class="text-center"><c:out
														value="${interno.firmaPdCD}" /></td>
											</tr>
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
	$(document).ready(function() {
		$("#accetta").click(function() {
			$(this).css("background-color", "green");
			$(this).css("color", "white");
			$(this).css("outline", "none");
			$(this).val("selezionato");
			$("#rifiuta").attr("disabled", "true");

		});
		$("#rifiuta").click(function() {
			$(this).css("background-color", "red");
			$(this).css("color", "white");
			$(this).css("outline", "none");
			$(this).val("selezionato")
			$("#accetta").attr("disabled", "true");
		});
	});
</script>
