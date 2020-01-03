<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="navbarBlu.jsp"></jsp:include>

<head>
<link rel="stylesheet" href="stiliCSS/stiliRicoscimentoAttivita.css">
<meta charset="ISO-8859-1">

<script src="jquery-3.4.1.min.js"></script>
<title>Visualizza richieste riconoscimento attività lavorative</title>
</head>

<div class="container">
	<div class="row">

		<div class="col-lg-3">

			<jsp:include page="menu.jsp"></jsp:include>

		</div>
		<div class="col-lg-9" id="col-9">

			<p id="titolo1" class="text-center">Visualizza lista richieste
				riconoscimento attività lavorative</p>
			<hr>
			<c:if test="${elencoRiconoscimento.size() == 0 }">
				<p>Non ci sono richieste di riconoscimento attività!</p>
			</c:if>

			<!-- inizio lista richieste riconoscimento da parte dello STUDENTE -->
			<c:if test="${type== 'studente' }">
				<table class="table table-striped" id="tabella">
					<thead>
						<tr id="colonne" class="text-center">
							<th scope="col">Numero richiesta</th>
							<th scope="col">CFU totali</th>
							<th scope="col">PDF</th>
							<!-- 	<th scope="col">Status</th> -->
						</tr>
					</thead>
					<c:if test="${elencoRiconoscimento.size() > 0 }">
						<c:forEach items="${elencoRiconoscimento}" var="ricon">
							<tbody>
								<tr class="text-center">
									<th scope="row"><c:out value="${ricon.idRiconoscimento}" /></th>
									<td><c:out
											value="${ricon.CFUTirocinioEsterno + ricon.CFUAccompagnamentoLavoro + ricon.CFUTirocinioObbligatorio}" /></td>
									<td><button>
										<i class="fas fa-file-pdf"></i>
									</button></td>
								</tr>
							</tbody>
						</c:forEach>
					</c:if>
				</table>
			</c:if>
			<!-- fine parte STUDENTE -->



			<!--  inizio lista richieste riconoscimento da parte dello PDCD e UFF-CARR -->
			<c:if test="${type=='pdcd'}">
				<!-- da agiungere l'uff carr -->
				<table class="table table-striped" id="tabella">
					<thead>
						<tr id="colonne" class="text-center">
							<th scope="col">Numero richiesta</th>
							<th scope="col">CFU totali</th>
							<th scope="col">PDF</th>
							<th scope="col">Status</th>

							<th scope="col">Info richiesta</th>
							<th scope="col">Valuta</th>
						</tr>
					</thead>
					<!-- PARTIRE DA QUI CON IN C:FOR -->
					<c:forEach items="${elencoRiconoscimento}" var="ricon">
						<tbody>
							<tr class="text-center">
								<th scope="row">#</th>
								<td>#</td>
								<td><button>
										<i class="fas fa-file-pdf"></i>
									</button></td>
								<td>#</td>
								<td>
									<button class="bottone" onclick="#" id="accetta">
										<i class="fas fa-info-circle"></i>
									</button>
								</td>

								<c:if test="${type=='pdcd'}">
									<td>
										<button class="bottone" onclick="#" id="accetta">
											<i class="fas fa-check-square"></i>
										</button>
										<button class="bottone" onclick="#" id="rifiuta">
											<i class="far fa-times-circle"></i>
										</button>
									</td>
								</c:if>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</c:if>


		</div>
	</div>
</div>
<br>
<jsp:include page="footer.jsp"></jsp:include>





