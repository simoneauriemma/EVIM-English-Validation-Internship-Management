<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="navbarBlu.jsp"></jsp:include>


<html>
<head>
<!-- Collegamenti esterni -->

<link rel="stylesheet" href="stiliCSS/stiliElencoAziende.css">

<meta charset="ISO-8859-1">

<title>Aziende convenzionate</title>

</head>
<body>
	<div class="container">
		<div class="row">

			<div class="col-lg-3">
				<jsp:include page="menu.jsp"></jsp:include>
			</div>

			<div class="col-lg-9"
				style="border: 1px solid #d7d7d7; background-color: white;">
				<p id="titolo">Aziende Convenzionate</p>
				<div class="input-group">
					<span class="input-group-addon">Filtro</span><input id="filter"
						type="text" class="form-control" placeholder="Cerca ...">
				</div>
				<br>

				<%
					int n = 0;
				%>
				<div id="accordion">
					<c:forEach items="${aziende}" var="azienda">
						<%
							n = n + 1;
						%>
						<div class="card">
							<div id="heading<%=n%>" style="background-color: #2C5278">
								<h6 class="mb-0">
									<button data-toggle="collapse" id="azienda"
										data-target="#collapse<%=n%>" aria-expanded="true"
										aria-controls="collapse<%=n%>">
										<c:out value="${azienda.nome}" />
									</button>
								</h6>
							</div>

							<div id="collapse<%=n%>" class="collapse"
								aria-labelledby="heading<%=n%>" data-parent="#accordion">
								<div class="card-body">
									<div class="text-left">
										<h4>
											<c:out value="${azienda.nome}" />
										</h4>
										<h6>Indirizzo</h6>
										<c:out value="${azienda.indirizzo}" />
										<br>
										<h6>C.F.</h6>
										<c:out value="${azienda.CF}" />
										<br>
										<h6>Descrizione</h6>
										<c:out value="${azienda.descrizione}" />
									</div>
									<br>
									<table class="table table-striped">
										<tbody>
											<tr>
												<td class="icon"><i class="fa fa-link"></i></td>
												<td class="">Sito Web</td>
												<td><a href="${azienda.sitoWeb}"><c:out
															value="${azienda.sitoWeb}" /></a></td>
											</tr>
											<tr>
												<td class="icon"><i class="fa fa-envelope"></i></td>
												<td class="">Email</td>
												<td><a href="${azienda.email}"><c:out
															value="${azienda.email}" /></a></td>
											</tr>

											<tr>
												<td class="icon"><i class="fas fa-user"></i></td>
												<td class="">Referente aziendale</td>
												<td>#</td>
											</tr>
											<tr>
												<td class="icon"><i class="fas fa-mobile-alt"></i></td>
												<td class="">Numero cellulare</td>
												<td>#</td>
											</tr>

											<tr>
												<td class="icon"><i class="fas fa-paperclip"></i></td>
												<td class=""><button type="button" data-toggle="modal"
														data-target="#exampleModal<%=n%>">Proposte</button></td>
												<td></td>
											</tr>
										</tbody>
									</table>
									<div class="modal fade" id="exampleModal<%=n%>" tabindex="-1"
										role="dialog" aria-labelledby="exampleModalLabel"
										aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel">
														Proposte di</h5>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body">
													<table class="table table-striped">
														<c:forEach items="${azienda.proposte}" var="proposta">
															<tbody>
																<tr style="background-color: #ccc; font-weight: bold;">
																	<td class="icon"><i
																		class="fas fa-sort-amount-down"></i></td>
																	<td class="">Numero proposta</td>
																	<td><c:out value="${proposta.ID_Proposta}" /></td>
																</tr>
																<tr>
																	<td class="icon"><i class="fas fa-bullseye"></i></td>
																	<td class="">Obiettivi</td>
																	<td><c:out value="${proposta.obiettivi}" /></td>
																</tr>
																<tr>
																	<td class="icon"><i class="fas fa-briefcase"></i></td>
																	<td class="">Competenze</td>
																	<td><c:out value="${proposta.competenze}" /></td>
																</tr>
																<tr>
																	<td class="icon"><i class="fas fa-chart-line"></i></td>
																	<td class="">Attività</td>
																	<td><c:out value="${proposta.attivita}" /></td>
																</tr>
																<tr>
																	<td class="icon"><i
																		class="fas fa-chalkboard-teacher"></i></td>
																	<td class="">Modalità</td>
																	<td><c:out value="${proposta.modalita}" /></td>
																</tr>
																<tr>

																</tr>

															</tbody>
														</c:forEach>
													</table>






												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-dismiss="modal">Close</button>
													<button type="button" class="btn btn-primary">Save
														changes</button>
												</div>
											</div>
										</div>
									</div>







								</div>
							</div>
						</div>
					</c:forEach>
				</div>


			</div>
		</div>
	</div>
	<br>
	<br>
</body>

<jsp:include page="footer.jsp"></jsp:include>

<script>
	$(document).ready(function() {
		$("#chiudere").click(function() {
			$('body').css("padding", "0px");
		});
	});
</script>
</html>
