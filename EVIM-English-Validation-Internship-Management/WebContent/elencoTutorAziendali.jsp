<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="WEB-INF/navbarBlu.jsp"></jsp:include>


<html>
<head>
<!-- Collegamenti esterni -->

<link rel="stylesheet" href="stiliCSS/stiliPropostaTirocinio.css">
<!-- hanno gli stessi stili -->

<meta charset="ISO-8859-1">

<title>Elenco Tutor aziendali</title>
</head>
<body>
	<div class="container">
		<div class="row">

			<div class="col-lg-3">
				<jsp:include page="WEB-INF/menu.jsp"></jsp:include>
			</div>

			<div class="col-lg-9" id="col-9">
				<p id="titolo">Elenco Tutor aziendali</p>
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
										<h6>ID Tutor accademico</h6>
										<c:out value="${tutor.idTutorAccademico}" />
										<br>
										<h6>E-Mail</h6>
										<c:out value="${tutor.email}" />
										 <br>
									</div>
									
									<h6>Lista proposte</h6>
									<table class="table table-striped" id="tabella-lista-proposte">
										<tbody>
											<tr style="background-color: #ccc; font-weight: bold;">
												<td class="icon"><i class="fas fa-sort-amount-down"></i></td>
												<td class="">Numero proposta</td>
												<td>1</td>
											</tr>
											<tr>
												<td class="icon"><i class="fas fa-bullseye"></i></td>
												<td class="">Obiettivi</td>
												<td>#</td>
											</tr>
											<tr>
												<td class="icon"><i class="fas fa-briefcase"></i></td>
												<td class="">Competenze</td>
												<td><a href="#"></a></td>
											</tr>
											<tr>
												<td class="icon"><i class="fas fa-chart-line"></i></td>
												<td class="">Attività</td>
												<td>#</td>
											</tr>
											<tr>
												<td class="icon"><i class="fas fa-chalkboard-teacher"></i></td>
												<td class="">Modalità</td>
												<td>#</td>
											</tr>
											<tr>
												 <td class="icon"><i class="fas fa-building"></i></td>
												<td class="">Azienda associata</td>
												<td><a href="#">#</a></td> 
											</tr>

										</tbody>
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

<jsp:include page="WEB-INF/footer.jsp"></jsp:include>

</html>
