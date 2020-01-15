<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="navbarBlu.jsp"></jsp:include>

<head>
<link rel="stylesheet" href="stiliCSS/stiliListaTirocini.css">
<meta charset="ISO-8859-1">
<style>
body {
	padding-right: 0px !important;
	padding: 0px !important;
}
</style>
<script src="jquery-3.4.1.min.js"></script>
<title>Lista Tirocini</title>
</head>

<div class="container">
	<div class="row">

		<div class="col-lg-3">

			<jsp:include page="menu.jsp"></jsp:include>

		</div>
		<%
			int n = 0;
		%>
		<div class="col-lg-9" id="col-9">

			<p id="titolo" class="text-center">Lista tirocini</p>
			<hr>

			<!-- se entrambi gli array di tirocini sono vuoti esce che non c'è nulla -->
			<c:if
				test="${registroQueryEsterno.size()==0 && registroQueryInterno.size()==0}">
				<p style="text-align: center;">Nessun tirocinio in corso!</p>
			</c:if>


			<!-- inizio TIROCINIO ESTERNO -->
			<c:if
				test="${type == 'tutoraziendale' || type== 'tutoraccademico' || type=='azienda' || type=='pdcd'}">



				<table class="table table-striped" id="tabella">
					<thead>
						<tr id="colonne" class="text-center">
							<th scope="col">ID Tirocinio</th>
							<th scope="col">Tirocinante</th>
							<th scope="col">Status</th>
							<th scope="col">CFU</th>
							<th scope="col">Ore Max</th>
							<th scope="col">Tipo tirocinio</th>
							<th scope="col">Registro tirocinio</th>

							<c:if test="${type!='pdcd'}">
								<th scope="col">Operazione</th>
							</c:if>
						</tr>
					</thead>

					<tbody>
						<c:if test="${registroQueryEsterno.size() > 0}">
							<c:set var="count" value="0" scope="session" />
							<c:forEach items="${registroQueryEsterno}" var="esterno">
								<c:set var="count" value="${count + 1}" scope="session" />
								<tr>
									<th scope="row" id="id"><c:out
											value="${esterno.ID_Tirocinio}" /></th>
									<td id="responsabile"><c:out
											value="${esterno.nomeStudente}" /> <c:out
											value="${esterno.cognomeStudente}" /></td>
									<td id="status"><c:out value="${esterno.status}" /></td>
									<td id="cfu"><c:out value="${esterno.numeroCFU}" /></td>
									<td id="ore"><c:out value="${esterno.oreTotali}" /></td>
									<td id="tirocinio">Esterno</td>

									<!-- REGISTRO -->
									<td class="form-inline text-center" id="registro1">
										<form action="VisualizzaRegistroTirocinio" method="get">
											<input type="hidden" name="EMAIL"
												id="emailStudenteEsterno${count}"
												value="<c:out value="${esterno.emailStudente}" />">
											<button id="registroI" class="fas fa-book" type="submit"
												style="border: none; background-color: transparent;"></button>

										</form> <c:if
											test="${esterno.firmaResponsabile == false && type == 'tutoraziendale'}">
											<a
												href="ApprovaRegistro?idregistro=${esterno.ID_Registro}&tipotirocinio=esterno&idtirocinio=${esterno.ID_Tirocinio}"><i
												class="fas fa-check-square" id="accettare"></i></a>
										</c:if> <c:if
											test="${esterno.firmaResponsabile == true && type == 'tutoraziendale'}">
											<i class="fas fa-check-square" style="color: green;"></i>
										</c:if> 
										
										
										
										<c:if
											test="${esterno.firmaResponsabile == false && type == 'tutoraccademico'}">
											<a
												href="ApprovaRegistro?idregistro=${esterno.ID_Registro}&tipotirocinio=esterno&idtirocinio=${esterno.ID_Tirocinio}"><i
												class="fas fa-check-square" id="accettare"></i></a>
										</c:if> <c:if
											test="${ type == 'tutoraccademico'}">
											<i class="fas fa-check-square" style="color: green;"></i>
										</c:if>




									</td>
									<!-- ...... -->


									<!-- OPERAZIONI -->



									<td class="text-center"><c:if
											test="${type=='tutoraziendale'}">
											<%
												n = n + 1;
											%>
											<div class="panel-group" style="width: 120px;">
												<div class="panel panel-default">
													<div class="panel-heading" class="text-center">
														<h6 class="panel-title">
															<a data-toggle="collapse" href="#collapse<%=n%>">
																Scegli </a>
														</h6>
													</div>
													<div id="collapse<%=n%>" class="panel-collapse collapse">
														<ul class="list-group">
															<li class="list-group-item"><a class="list-item"
																data-toggle="modal" data-target="#ciaoo"
																onclick='setEmail(${count})' style="color: #007bff;">Compila
																	relazione</a></li>
															<li class="list-group-item"><a class="list-item"
																href="VisualizzaRelazione">Visualizza relazione</a></li>
															<li class="list-group-item"><a class="list-item"
																href="QuestionarioT">Compila questionario</a></li>
														</ul>

													</div>
												</div>
											</div>
										</c:if> <!-- inizio tutor accademico operazioni --> <c:choose>
											<c:when
												test="${type=='tutoraccademico' && (esterno.ID_Relazione!=0 && esterno.registro_status=='completato')}">

												<!-- Button trigger modal -->
												<button type="button" class="btn btn-primary"
													data-toggle="modal" data-target="#exampleModalCenter"
													onclick="vediRelazione('${esterno.ID_Relazione}')">
													Visualizza relazione</button>

												<!-- Modal -->
												<form action="ValutaRelazione" id="formapprova">
													<div class="modal fade" id="exampleModalCenter"
														tabindex="-1" role="dialog"
														aria-labelledby="exampleModalCenterTitle"
														aria-hidden="true">
														<div class="modal-dialog modal-dialog-centered"
															role="document">
															<div class="modal-content">
																<div class="modal-header">
																	<input id="hiddenid" type="hidden" name="idrelazione"></input>
																	<h5 class="modal-title" id="exampleModalLongTitle">
																		Approva relazione</h5>
																	<button type="button" class="close"
																		data-dismiss="modal" aria-label="Close">
																		<span aria-hidden="true">&times;</span>
																	</button>
																</div>
																<div class="modal-body" id="descrizioneVisualizza">
																	<!-- aggiungere la relazione che ha compilato il tutor aziendale -->


																</div>
																<div class="modal-footer">
																	<button type="submit" class="btn btn-secondary"
																		form="formapprova" value="1" name="approva">Approva</button>
																	<button type="submit" class="btn btn-secondary"
																		form="formapprova" value="0" name="approva">Rifiuta</button>
																</div>
															</div>
														</div>
													</div>
												</form>
											</c:when>
											<c:when
												test="${type=='tutoraccademico' && esterno.ID_Relazione!=0 && esterno.registro_status!='completato'}">
												<p>Nessuna operazione da effettuare</p>

											</c:when>
										</c:choose></td>



									<!-- ...... -->

								</tr>
							</c:forEach>
							<!-- INTEGRAZIONE PROVVISORIA -->
						</c:if>
						<c:forEach items="${registroQueryInterno}" var="interno">
							<tr>
								<th scope="row" id="idI"><c:out
										value="${interno.ID_Tirocinio}" /></th>
								<td id="responsabileI"><c:out
										value="${interno.nomeStudente}" /> <c:out
										value="${interno.cognomeStudente}" /></td>
								<td id="statusI"><c:out value="${interno.status}" /></td>
								<td id="cfuI"><c:out value="${interno.numeroCFU}" /></td>
								<td id="oreI"><c:out value="${interno.oreTotali}" /></td>
								<td id="tirocinioI">Interno</td>

								<!-- REGISTRO -->
								<td class="form-inline text-center">
									<form action="VisualizzaRegistroTirocinio" method="get">
										<input type="hidden" name="EMAIL"
											value=<c:out value="${interno.emailStudente}" />>
										<button id="registroI" class="fas fa-book"
											style="border: none; background-color: transparent;"></button>

									</form> <c:if test="${interno.firmaResponsabile == false}">
										<a
											href="ApprovaRegistro?idregistro=${interno.ID_Registro}&tipotirocinio=interno&idtirocinio=${interno.ID_Tirocinio}">

											<i class="fas fa-check-square" id="accettare"></i>
										</a>
									</c:if> <c:if test="${interno.firmaResponsabile == true}">
										<i class="fas fa-check-square" style="color: green;"></i>
									</c:if>

								</td>
								<td>-</td>



							</tr>
						</c:forEach>

					</tbody>

				</table>


				<div class="modal fade" id="ciaoo" tabindex="-1" role="dialog"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Compila
									relazione</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<form action="CreaRelazione" method="post">
								<div class="modal-body">

									<input type="hidden" id="emailS" name="emailstudente" value="">
									<textarea name="descrizione" class="form-control"
										id="descrizione" placeholder="Compila verbale tirocinio..."
										rows="3"></textarea>



								</div>

								<div class="modal-footer">
									<button type="submit" class="btn btn-primary">INVIA</button>
								</div>
							</form>
						</div>
					</div>
				</div>



			</c:if>
			<!-- fine TIROCINIO ESTERNO -->


		</div>

	</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
<script>
function setEmail(i){
	alert(document.getElementById("emailStudenteEsterno"+i).value);
	document.getElementById("emailS").value=document.getElementById("emailStudenteEsterno"+i).value;
}

function vediRelazione(idr){
	$.get( "VisualizzaRelazione?idrelazione="+idr , function( data ) {
		
		var relazione= JSON.parse(data);
		var hiddenid= $("#hiddenid");
		var descrizione= $("#descrizioneVisualizza");
		
		hiddenid.val(relazione.id);
		
	 	descrizione.html(relazione.descrizione);

	});
}
</script>
<!-- <script>
	$(document).ready(function() {
		$("#accetta").click(function() {
			$(this).css("background-color", "green");
			$(this).css("color", "white");
			$(this).css("outline", "none");
			$(this).val("selezionato");
			$("#rifiuta").attr("disabled", "true");

		});
	});
</script> -->

