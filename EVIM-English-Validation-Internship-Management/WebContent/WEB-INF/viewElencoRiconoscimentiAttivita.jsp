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

			<p id="titolo1" class="text-center">Richieste di riconoscimento
				attività lavorative</p>

			<hr>
			<c:if test="${elencoRiconoscimento.size() == 0 }">
				<p>Non ci sono richieste di riconoscimento attività!</p>
			</c:if>


			<c:if test="${elencoRiconoscimento.size() > 0 }">
				<!-- inizio lista richieste riconoscimento da parte dello STUDENTE -->
				<c:if test="${type== 'studente' }">
					<table class="table table-striped" id="tabella">
						<thead>
							<tr id="colonne" class="text-center">
								<th scope="col">Numero richiesta</th>
								<th scope="col">Status</th>
								<th scope="col">CFU totali</th>
								<th scope="col">PDF</th>
								<!-- 	<th scope="col">Status</th> -->
							</tr>
						</thead>
						<c:if test="${elencoRiconoscimento.size() > 0 }">
							<c:forEach items="${elencoRiconoscimento}" var="ricon"
								varStatus="conto">
								<tbody>
									<tr class="text-center">
										<th><c:out value="${ricon.idRiconoscimento}" /></th>

										<th><i id="${conto.count}" class="far fa-circle"
											title="<c:out value="${ricon.stato}"/>"></i></th>

										<td><c:out
												value="${ricon.CFUTirocinioEsterno + ricon.CFUAccompagnamentoLavoro + ricon.CFUTirocinioObbligatorio}" /></td>
										<td><button
												onclick="location.href='VisualizzaModuloRiconoscimento?idRiconoscimento=${ricon.idRiconoscimento}'">
												<i class="fas fa-file-pdf"></i>
											</button></td>
									</tr>
								</tbody>
							</c:forEach>
						</c:if>
					</table>
				</c:if>
			</c:if>
			<!-- fine parte STUDENTEeee -->



			<!--  inizio lista richieste riconoscimento da parte dello PDCD e UFF-CARR -->
			<c:if test="${elencoRiconoscimento.size() > 0 }">
				<c:if test="${type=='pdcd'}">
					<!-- da agiungere l'uff carr -->
					<table class="table table-striped" id="tabella">
						<thead>
							<tr id="colonne" class="text-center">
								<th scope="col">Numero richiesta</th>
								<th scope="col">Studente</th>
								<th scope="col">Matricola</th>
								<th scope="col">Status</th>
								<!--<th scope="col">CFU totali</th> -->
								<th scope="col">Allegati</th>
								<!-- 		<th scope="col">Info richiesta</th> -->
								<th scope="col">Valuta</th>
							</tr>
						</thead>
						<!-- PARTIRE DA QUI CON IN C:FOR -->
						<c:forEach items="${elencoRiconoscimento}" var="ricon"
							varStatus="conto">
							<tbody>
								<tr class="text-center">
									<th><c:out value="${ricon.idRiconoscimento}" /></th>
									<th><c:out
											value="${ricon.nomeStudente} ${ricon.cognomeStudente}" /></th>
									<th><c:out value="${ricon.matricolaStudente}"></c:out></th>
									<th><i id="${conto.count}" class="far fa-circle"
										title="<c:out value="${ricon.stato}"/>"></i></th>
									<td><button>
											<i class="fas fa-file-pdf"></i>
										</button></td>

									<!-- il bottone è visibile solo se lo status è il valutazione -->
									<c:if test="${ricon.stato eq 'V'}">

										<td>
											<button class="bottone" onclick=approvaModulo(
												<c:out value="${ricon.idRiconoscimento}"/>,<c:out value="${conto.count}"/>
												) id="accetta">
												<i class="fas fa-check-square"></i>
											</button>

											<button class="bottone" onclick=rifiutaModulo(
												<c:out value="${ricon.idRiconoscimento}"/>,<c:out value="${conto.count}"/>
												) id="rifiuta">
												<i class="far fa-times-circle"></i>
											</button>
										</td>
										<!--  se il la richiesta è stata approvata o rifiutata -->
									</c:if>
									<c:if test="${ricon.stato != 'V'}">
										<td>Richiesta valutata</td>
									</c:if>

								</tr>
							</tbody>
						</c:forEach>
					</table>
				</c:if>
			</c:if>


		</div>
	</div>
</div>
<br>
<jsp:include page="footer.jsp"></jsp:include>

<script>
	/* status: riferito allo status di riconoscimento attività */
	$(document).ready(function() {
		$(".far").each(function(){
		if ($(this).attr("title")=="V") {
			$(this).attr("title","in valutazione");
			$(this).css("background-color", "yellow");
			$(this).css("color", "black");
			$(this).css("border-radius", "22px");
		} else if ($(this).attr("title")=="A") {
			$(this).attr("title","approvato");
			$(this).css("background-color", "green");
			$(this).css("color", "black");
			$(this).css("border-radius", "22px");
		} else if ($(this).attr("title")=="R") {
			$(this).attr("title","rifiutato");
			$(this).css("background-color", "red");
			$(this).css("color", "black");
			$(this).css("border-radius", "22px");
		}
		});
	});
	
	/* cambio dei colori dei bottoni alla valutazione*/
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
	
	
	
	
	function approvaModulo(id,idStatus){
		var xmlHttp=new XMLHttpRequest();
  		xmlHttp.responseType="json";
  		xmlHttp.onreadystatechange=function(){
  			if(xmlHttp.readyState==4 && xmlHttp.status==200)
  				{
  					
  					$("#"+idStatus).css("background-color", "");
					$("#"+idStatus).css("color", "");
					$("#"+idStatus).css("border-radius", "");	
  				
  					$("#"+idStatus).css("background-color", "green");
  					$("#"+idStatus).css("color", "black");
  					$("#"+idStatus).css("border-radius", "22px");
  				}
  		}
  		xmlHttp.open("GET","ApprovaRifiutaModuloRiconoscimento?modifica=approva&idRiconoscimento="+id,true);
  		xmlHttp.send();
	}
	
	function rifiutaModulo(id,idStatus){
		var xmlHttp=new XMLHttpRequest();
  		xmlHttp.responseType="json";
  		xmlHttp.onreadystatechange=function(){
  			if(xmlHttp.readyState==4 && xmlHttp.status==200)
  				{
  					$("#"+idStatus).css("background-color", "");
					$("#"+idStatus).css("color", "");
					$("#"+idStatus).css("border-radius", "");	
  				
					$("#"+idStatus).css("background-color", "red");
					$("#"+idStatus).css("color", "black");
					$("#"+idStatus).css("border-radius", "22px");
  				}
  		}
  		xmlHttp.open("GET","ApprovaRifiutaModuloRiconoscimento?modifica=approva&idRiconoscimento="+id,true);
  		xmlHttp.send();
	}
</script>




