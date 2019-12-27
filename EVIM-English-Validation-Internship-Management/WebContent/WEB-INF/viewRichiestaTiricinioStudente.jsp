<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="navbarBlu.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="stiliCSS/stiliRichiesteTirocinio.css">

<title>View richiesta studente</title>
</head>
<body>
	<div class="container">
		<div class="row">

			<div class="col-lg-3">
				<jsp:include page="menu.jsp"></jsp:include>
			</div>

			<div class="col-lg-9" id=""
				style="border: 1px solid #d7d7d7; background-color: white;">
				<p id="titolo" style="font-size: 30px; color: #595959;">
					Richiesta di tirocinio curriculare</p>
					
				<c:if
					test="${arrayTirocinioEsterno.size() == 0 || arrayTirocinioIntero.size() == 0 }">
					<p>Non ci sono richieste!</p>
				</c:if>

				<table class="table table-striped" id="tabella">

					<c:if test="${arrayTirocinioEsterno.size() > 0}">

						<c:forEach items="${arrayTirocinioEsterno}" var="tirocinioe">

							<tbody>

								<tr id="numero">
									<td class="icon"><i class="fas fa-sort-amount-down"></i></td>
									<td class="">Numero proposta</td>
									<td class="text-center"><c:out
											value="${tirocinioe.ID_Proposta}"></c:out></td>
								</tr>
								<tr>
									<td class="icon"><i class="fa fa-user"></i></td>
									<td class="">Nome e cognome</td>
									<td class="text-center"><c:out
											value="${utenteLoggato.name}"></c:out> <c:out
											value="${utenteLoggato.surname}"></c:out></td>
								</tr>
								<tr>
									<td class="icon"><i class="fas fa-at"></i></td>
									<td class="">E-mail</td>
									<td class="text-center"><c:out
											value="${utenteLoggato.email}"></c:out></td>
								</tr>
								<tr>
									<td class="icon"><i class="fas fa-toggle-on"></i></td>
									<td class="">Status</td>
									<td class="text-center"><c:out
											value="${tirocinioe.status}"></c:out><i class="far fa-circle"></i></td>
								</tr>
								<tr>
									<td class="icon"><i class="fas fa-circle"></i></td>
									<td class="">Num. CFU</td>
									<td class="text-center"><c:out value="${tirocinioe.CFU}"></c:out></td>
								</tr>
								<tr>
									<td class="icon"><i class="fas fa-circle"></i></td>
									<td class="">Tipo di tirocinio</td>
									<td class="text-center">Esterno</td>
								</tr>
							</tbody>
						</c:forEach>
					</c:if>

					<c:if test="${arrayTirocinioInterno.size() > 0}">
						<c:forEach items="${arrayTirocinioInterno}" var="tirocinioi">
							<tbody>
								<tr id="numero">
									<td class="icon"><i class="fas fa-sort-amount-down"></i></td>
									<td class="">Numero proposta</td>
									<td class="text-center"><c:out
											value="${tirocinioi.ID_Proposta}"></c:out></td>
								</tr>
								<tr>
									<td class="icon"><i class="fa fa-user"></i></td>
									<td class="">Nome e cognome</td>
									<td class="text-center"><c:out
											value="${utenteLoggato.name}"></c:out> <c:out
											value="${utenteLoggato.surname}"></c:out></td>
								</tr>
								<tr>
									<td class="icon"><i class="fas fa-at"></i></td>
									<td class="">E-mail</td>
									<td class="text-center"><c:out
											value="${utenteLoggato.email}"></c:out></td>
								</tr>
								<tr>
									<td class="icon"><i class="fas fa-toggle-on"></i></td>
									<td class="">Status</td>
									<td class="text-center"><c:out
											value="${tirocinioi.status}"></c:out><i class="far fa-circle"></i></td>
								</tr>
								<tr>
									<td class="icon"><i class="fas fa-circle"></i></td>
									<td class="">Num. CFU</td>
									<td class="text-center"><c:out
											value="${tirocinioi.NumeroCFU}"></c:out></td>
								</tr>
								<tr>
									<td class="icon"><i class="fas fa-circle"></i></td>
									<td class="">Tipo di tirocinio</td>
									<td class="text-center">Interno</td>
								</tr>
							</tbody>
						</c:forEach>
					</c:if>

				</table>
			</div>
		</div>
	</div>
	<br>
	<br>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>