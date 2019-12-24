<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="navbarBlu.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<!-- collegamenti esterni -->
<link rel="stylesheet" href="stiliCSS/stiliPropostaTirocinio.css">

<meta charset="ISO-8859-1">
<title>Proposta di tirocinio</title>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<jsp:include page="menu.jsp"></jsp:include>
			</div>

			<div class="col-lg-9"
				style="border: 1px solid #d7d7d7; background-color: white;">
				<p id="titolo">Proposta di tirocinio curriculare</p>
				<c:if test="${type == 'azienda'}">
				<div class="input-group">
				
					<span class="input-group-addon">Filtro</span><input id="filter"
						type="text" class="form-control"
						placeholder="Cerca in base al nome del Tutor aziendale...">
				
				</div>
				</c:if>
				
				<c:if test="${type == 'tutoraccademico'}">
				<div class="input-group">
				
					<span class="input-group-addon">Filtro</span><input id="filter"
						type="text" class="form-control"
						placeholder="Cerca in base al nome del Tutor interno...">
				
				</div>
				</c:if>
				<br>

				<table class="table table-striped" id="tabella">

					<c:forEach items="${proposte}" var="proposta">
						<tbody>
							<tr id="numero">
								<td class="icon"><i class="fas fa-sort-amount-down"></i></td>
								<td class="">Numero proposta</td>
								<td><c:out value="${proposta.ID_Proposta}" /></td>
							</tr>
							<c:if test="${type eq 'azienda'}">
								<tr>
									<td class="icon"><i class="fas fa-building"></i></td>
									<td class="">Tutor Aziendale</td>
									<td><c:out value="${proposta.nomeTutorAziendale}" /> <c:out
											value="${proposta.cognomeTutorAziendale}" /></td>
								</tr>
							</c:if>
							<tr>
								<td class="icon"><i class="fas fa-info-circle"></i></td>
								<td class="">Obiettivi</td>
								<td><c:out value="${proposta.obiettivi}" /></td>
							</tr>
							<tr>
								<td class="icon"><i class="fas fa-building"></i></td>
								<td class="">Sede</td>
								<td><c:out value="${proposta.sede}" /></td>
							</tr>
							<tr>
								<td class="icon"><i class="fas fa-coins"></i></td>
								<td class="">Tema/Ambito</td>
								<td><c:out value="${proposta.temaAmbito}" /></td>
							</tr>
							<tr>
								<td class="icon"><i class="fas fa-box-open"></i></td>
								<td class="">Materiale/Risorse</td>
								<td><c:out value="${proposta.materialeRisorse}" /></td>
							</tr>
							<tr>
								<td></td>
								<td></td>

								<td style="text-align: right;"><a href="modificaProposta.jsp"><button
											class="btn btn-secondary">Modifica</button></a>
									<button class="btn btn-secondary" style="margin-left: 10px;">Rimuovi</button></td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>

	<br>
	<br>
	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>
