<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="WEB-INF/navbarBlu.jsp"></jsp:include>

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
				<jsp:include page="WEB-INF/menu.jsp"></jsp:include>
			</div>

			<div class="col-lg-9"
				style="border: 1px solid #d7d7d7; background-color: white;">
				<p id="titolo">Proposta di tirocinio curriculare interno</p>
				<div class="input-group">
					<span class="input-group-addon">Filtro</span><input id="filter"
						type="text" class="form-control" placeholder="Cerca in base al nome del Tutor interno...">
				</div>
				<br>
				
				<table class="table table-striped" id="tabella">
					<tbody>
						<tr id="numero">
							<td class="icon"><i class="fas fa-sort-amount-down"></i></td>
							<td class="">Numero proposta</td>
							<td>1</td>
						</tr>
						<tr>
							<td class="icon"><i class="fa fa-user"></i></td>
							<td class="">Tutor Interno</td>
							<td>nome cognome</td>
						</tr>
						<tr>
							<td class="icon"><i class="fas fa-building"></i></td>
							<td class="">Sede</td>
							<td>#</td>
						</tr>
						<tr>
							<td class="icon"><i class="fas fa-coins"></i></td>
							<td class="">Tema/Ambito</td>
							<td>#</td>
						</tr>
						<tr>
							<td class="icon"><i class="fas fa-box-open"></i></td>
							<td class="">Materiale/Risorse</td>
							<td>#</td>
						</tr>
						<tr>
							<td class="icon"><i class="fas fa-info-circle"></i></td>
							<td>Ulteriori informazioni</td>
							<td>#</td>
						</tr>

					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<br>
	<br>
	<jsp:include page="WEB-INF/footer.jsp"></jsp:include>
</body>

</html>