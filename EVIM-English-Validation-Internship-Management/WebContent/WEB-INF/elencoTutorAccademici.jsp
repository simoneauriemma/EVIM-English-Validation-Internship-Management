<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="navbarBlu.jsp"></jsp:include>


<html>
<head>
<!-- Collegamenti esterni -->

<link rel="stylesheet" href="stiliCSS/stiliPropostaTirocinio.css">
<!-- hanno gli stessi stili -->

<meta charset="ISO-8859-1">

<title>Elenco Tutor accademici</title>
</head>
<body>
	<div class="container">
		<div class="row">

			<div class="col-lg-3">
				<jsp:include page="menu.jsp"></jsp:include>
			</div>

			<div class="col-lg-9" id="col-9">
				<p id="titolo">Elenco Tutor accademici</p>
				<hr>
				<div class="input-group">
					<span class="input-group-addon">Filtro</span><input id="filter"
						type="text" class="form-control" placeholder="Cerca ...">
				</div>
				<br>
				<%
					int n = 0;
				%>
				<div id="accordion">
					<c:forEach items="${tutors}" var="tutor">
						<%
							n = n + 1;
						%>
						<div class="card">
							<div id="heading<%=n%>" style="background-color: #2C5278">
								<h6 class="mb-0">
									<button data-toggle="collapse" id="nome-tutor"
										data-target="#collapse<%=n%>" aria-expanded="true"
										aria-controls="collapse<%=n%>">
										<c:out value="${tutor.nome}" />
										<c:out value="${tutor.cognome}" />
									</button>
								</h6>
							</div>

							<div id="collapse<%=n%>" class="collapse"
								aria-labelledby="heading<%=n%>" data-parent="#accordion">
								<div class="card-body">
									<div class="text-left">
										<h4>
											<c:out value="${tutor.nome}" />
										</h4>
										<h6>ID Tutor accademico: <c:out value="${tutor.idTutorAccademico}" /></h6>
										<br>
										<h6>E-Mail:</h6>
										<c:out value="${tutor.email}" />
										 <br>
									</div>
									<br>
									<h6>Lista proposte</h6>
									<table class="table table-striped" id="tabella-lista-proposte">
									<c:forEach items="${tutor.listeProposte}" var="proposta">
										<tbody>
											<tr style="background-color: #ccc; font-weight: bold;">
												<td class="icon"><i class="fas fa-sort-amount-down"></i></td>
												<td class="">Numero proposta</td>
												<td>
												<c:out value="${proposta.ID_Proposta}" />
												</td>
											</tr>
											<tr>
												<td class="icon"><i class="fas fa-bullseye"></i></td>
												<td class="">Obiettivi</td>
												<td>
												<c:out value="${proposta.obiettivi}" />
												</td>
											</tr>
											<tr>
												<td class="icon"><i class="fas fa-briefcase"></i></td>
												<td class="">Sede</td>
												<td><c:out value="${proposta.sede}" /></td>
											</tr>
											<tr>
												<td class="icon"><i class="fas fa-chart-line"></i></td>
												<td class="">Tema/Ambito</td>
												<td><c:out value="${proposta.temaAmbito}" /></td>
											</tr>
											<tr>
												<td class="icon"><i class="fas fa-chalkboard-teacher"></i></td>
												<td class="">Materiale/Risorse</td>
												<td><c:out value="${proposta.materialeRisorse}" /></td>
											</tr>
											<tr>
												
											</tr>

										</tbody>
										</c:forEach>
									</table>
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

</html>