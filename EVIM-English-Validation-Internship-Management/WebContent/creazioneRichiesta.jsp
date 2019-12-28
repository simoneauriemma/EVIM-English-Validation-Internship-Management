<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<jsp:include page="WEB-INF/navbarBlu.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="stiliCSS/stiliCreazioneRichiesta.css">
<title>Creazione richiesta</title>
</head>

<div class="container">
	<form action="CreaRichiestaTirocinio" method="post">
		<div class="row">
			<div class="col-lg-3">
				<jsp:include page="WEB-INF/menu.jsp"></jsp:include>
			</div>

			<div class="col-lg-9" id="col-9">
				<p class="text-center" id="titolo">Crea richiesta di tirocinio
					curriculare</p>
				<hr>

				<div class="row">
					<div class="col-lg-5" id="col-6">
						<div class="scelte">
							<div class="scelta-drop">
								<i class="fas fa-edit"></i> Selezionare il numero di CFU <br>
								<br> <input name="cfu" type="radio" id="radio1" value="6"
									checked> <label id="radio1" for="radio1">6 CFU</label>
								<br> <input name="cfu" type="radio" id="radio2" value="11">
								<label for="radio2" id="radio2">11 CFU</label> <br> <input
									name="cfu" type="radio" id="radio3" value="17"> <label
									for="radio3" id="radio3">17 CFU</label> <br> <input
									name="cfu" type="radio" id="radio4" value="23"> <label
									for="radio4" id="radio4">23 CFU</label>
							</div>
						</div>
					</div>
					<hr>

					<div class="col-lg-6">
						<div class="scelta-drop">
							<div class="form-group">
								<label for="sel1"><i class="fas fa-briefcase"></i>
									Seleziona tirocinio</label> <select class="form-control" id="sel1"
									onchange="getTutors(this.value)" name="sel1">
									<option value="tirocinio1" selected>Tirocinio esterno</option>
									<option value="tirocinio2">Tirocinio interno</option>
								</select>
							</div>
						</div>

						<div class="scelta-drop">
							<div class="form-group">
								<label for="sel2"><i class="fas fa-building"></i>
									Seleziona azienda</label> <select class="form-control" id="sel2"
									name="sel2" onchange="getProposteAzienda(this.value)">
									<!-- <option value="azienda1">x</option>
								<option value="azienda2">y</option> -->
								</select>
							</div>
						</div>
						<div class="scelta-drop">
							<div class="form-group">
								<label for="sel3"><i class="fas fa-edit"></i> Seleziona
									proposta </label> <select class="form-control" id="sel3" name="sel3">
									<!-- <option value="proposta1">x</option>
								<option value="proposta2">y</option> -->
								</select>
							</div>
						</div>
						<div class="scelta-drop">
							<div class="form-group">
								<label for="sel4"><i class="fas fa-user"></i> Seleziona
									Tutor Accademico</label> <select class="form-control" id="sel4"
									name="sel4" onchange="getProposteAccademici(this.value)">
									<option disabled selected value>-- select an option --
									</option>
									<!--  <option value="tutor1">tizio</option>
								<option value="tutor2">caio</option>-->
								</select>
							</div>
						</div>


						<br>
					</div>
					<br>
				</div>
				<span class="text-right" style="margin-left: 350px;"><button
						type="submit" class="btn btn-primary" data-toggle="modal"
						data-target="#exampleModalCenter"
						style="background-color: #2C5278;">CONFERMA</button></span> <br> <br>

				<div class="modal fade" id="exampleModalCenter" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalCenterTitle"
					aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
							<div class="modal-header">
								
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">Richiesta effettuata con successo!</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</form>
</div>

<br>
<br>
<jsp:include page="WEB-INF/footer.jsp"></jsp:include>

<script>
	/* se il radio button "11" è selezionato allora fa scegliere 
	           tra tirocinio interno ed esterno */
	$(document).ready(function() {
		/*	$("#radio2").click(function() {
				$("#sel1").prop('disabled', false);
			});

			$("#radio1").click(function() {
				$("#sel1").prop('disabled', true);
			});

			$("#radio3").click(function() {
				$("#sel1").prop('disabled', true);
			});
			$("#radio4").click(function() {
				$("#sel1").prop('disabled', true);
			});
			
			$("#sel1").click(function() {
				if($(this).val()=='tirocinio2'){
					$("#sel2").prop('disabled', true);
				}
				else if($(this).val()=='tirocinio1'){
					$("#sel2").prop('disabled', false);
				}
			});	*/

		/* se il tirocinio non è interno, allora automaticamente mi mette 
		"tirocinio esterno" e mi abilita la section "seleziona azienda" */
		$("#radio1").click(function() {
			$("#sel1").val('tirocinio1');
			$("#sel2").prop('disabled', false);

		});

		$("#radio2").click(function() {
			$("#sel1").val('tirocinio1');
			$("#sel2").prop('disabled', false);
		});

		$("#radio3").click(function() {
			$("#sel1").val('tirocinio1');
			$("#sel2").prop('disabled', false);
		});

		$("#radio4").click(function() {
			$("#sel1").val('tirocinio1');
			$("#sel2").prop('disabled', false);
		});
	});

	var xmlHttp = new XMLHttpRequest();
	xmlHttp.responseType = "json";
	var idTirocinio = document.getElementById("sel1");
	var stringaTirocinio = idTirocinio.options[idTirocinio.selectedIndex].value;
	if (stringaTirocinio == "tirocinio1") {

		var nodoTutorAziendale = document.getElementById("sel2");
		while (nodoTutorAziendale.hasChildNodes()) {
			nodoTutorAziendale.removeChild(nodoTutorAziendale.firstChild);
		}

		var nodoTutorAccademici = document.getElementById("sel4");
		while (nodoTutorAccademici.hasChildNodes()) {
			nodoTutorAccademici.removeChild(nodoTutorAccademici.firstChild);
		}

		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var lista = xmlHttp.response;

				var nodoTutorAziendale = document.getElementById("sel2");

				var figlioOptionDefaultSel2 = document.createElement("option");
				var attrDisabled = document.createAttribute("disabled");
				figlioOptionDefaultSel2.setAttributeNode(attrDisabled);

				var attrSelect = document.createAttribute("selected");
				figlioOptionDefaultSel2.setAttributeNode(attrSelect);

				var attrValue = document.createAttribute("value");
				figlioOptionDefaultSel2.setAttributeNode(attrValue);

				var textOptionDefaultSel2 = document
						.createTextNode("Seleziona un'opzione");
				figlioOptionDefaultSel2.appendChild(textOptionDefaultSel2);

				nodoTutorAziendale.appendChild(figlioOptionDefaultSel2);

				var nodoTutorAccademici = document.getElementById("sel4");

				var figlioOptionDefaultSel4 = document.createElement("option");
				var attrDisabled = document.createAttribute("disabled");
				figlioOptionDefaultSel4.setAttributeNode(attrDisabled);

				var attrSelect = document.createAttribute("selected");
				figlioOptionDefaultSel4.setAttributeNode(attrSelect);

				var attrValue = document.createAttribute("value");
				figlioOptionDefaultSel4.setAttributeNode(attrValue);

				var textOptionDefaultSel4 = document
						.createTextNode("Seleziona un'opzione");
				figlioOptionDefaultSel4.appendChild(textOptionDefaultSel4);

				nodoTutorAccademici.appendChild(figlioOptionDefaultSel4)

				for (var z = 0; z < lista[0].TutorAziendali.length; z++) {

					var figlioOption = document.createElement("option");

					var attrValue = document.createAttribute("value");
					attrValue.value = lista[0].TutorAziendali[z].ID;
					figlioOption.setAttributeNode(attrValue);

					var textOption = document
							.createTextNode(lista[0].TutorAziendali[z].Nome);
					figlioOption.appendChild(textOption);
					nodoTutorAziendale.appendChild(figlioOption);
				}
				for (var y = 0; y < lista[1].TutorAccademici.length; y++) {

					var figlioOption = document.createElement("option");

					var attrValue = document.createAttribute("value");
					attrValue.value = lista[1].TutorAccademici[y].ID;
					figlioOption.setAttributeNode(attrValue);

					var textOption = document
							.createTextNode(lista[1].TutorAccademici[y].Nome);
					figlioOption.appendChild(textOption);
					nodoTutorAccademici.appendChild(figlioOption);
				}

			}
		}

		xmlHttp.open("GET", "RicercaTutor?tirocinio=tirocinio1", true);
		xmlHttp.send();
	}
</script>

<script>
	function getTutors(stringaTirocinio) {
		var xmlHttp = new XMLHttpRequest();
		xmlHttp.responseType = "json";
		if (stringaTirocinio == "tirocinio1") {

			var nodoTutorAziendale = document.getElementById("sel2");
			while (nodoTutorAziendale.hasChildNodes()) {
				nodoTutorAziendale.removeChild(nodoTutorAziendale.firstChild);
			}

			var disabledAttr = nodoTutorAziendale.getAttributeNode("disabled");
			nodoTutorAziendale.removeAttributeNode(disabledAttr);

			var nodoTutorAccademici = document.getElementById("sel4");
			while (nodoTutorAccademici.hasChildNodes()) {
				nodoTutorAccademici.removeChild(nodoTutorAccademici.firstChild);
			}

			xmlHttp.onreadystatechange = function() {
				if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
					var lista = xmlHttp.response;

					var nodoTutorAziendale = document.getElementById("sel2");

					var figlioOptionDefaultSel2 = document
							.createElement("option");
					var attrDisabled = document.createAttribute("disabled");
					figlioOptionDefaultSel2.setAttributeNode(attrDisabled);

					var attrSelect = document.createAttribute("selected");
					figlioOptionDefaultSel2.setAttributeNode(attrSelect);

					var attrValue = document.createAttribute("value");
					figlioOptionDefaultSel2.setAttributeNode(attrValue);

					var textOptionDefaultSel2 = document
							.createTextNode("Seleziona un'opzione");
					figlioOptionDefaultSel2.appendChild(textOptionDefaultSel2);

					nodoTutorAziendale.appendChild(figlioOptionDefaultSel2);

					var nodoTutorAccademici = document.getElementById("sel4");

					var figlioOptionDefaultSel4 = document
							.createElement("option");
					var attrDisabled = document.createAttribute("disabled");
					figlioOptionDefaultSel4.setAttributeNode(attrDisabled);

					var attrSelect = document.createAttribute("selected");
					figlioOptionDefaultSel4.setAttributeNode(attrSelect);

					var attrValue = document.createAttribute("value");
					figlioOptionDefaultSel4.setAttributeNode(attrValue);

					var textOptionDefaultSel4 = document
							.createTextNode("Seleziona un'opzione");
					figlioOptionDefaultSel4.appendChild(textOptionDefaultSel4);

					nodoTutorAccademici.appendChild(figlioOptionDefaultSel4)

					for (var z = 0; z < lista[0].TutorAziendali.length; z++) {

						var figlioOption = document.createElement("option");

						var attrValue = document.createAttribute("value");
						attrValue.value = lista[0].TutorAziendali[z].ID;
						figlioOption.setAttributeNode(attrValue);

						var textOption = document
								.createTextNode(lista[0].TutorAziendali[z].Nome);
						figlioOption.appendChild(textOption);
						nodoTutorAziendale.appendChild(figlioOption);
					}
					for (var y = 0; y < lista[1].TutorAccademici.length; y++) {

						var figlioOption = document.createElement("option");

						var attrValue = document.createAttribute("value");
						attrValue.value = lista[1].TutorAccademici[y].ID;
						figlioOption.setAttributeNode(attrValue);

						var textOption = document
								.createTextNode(lista[1].TutorAccademici[y].Nome);
						figlioOption.appendChild(textOption);
						nodoTutorAccademici.appendChild(figlioOption);
					}

				}
			}

			xmlHttp.open("GET", "RicercaTutor?tirocinio=tirocinio1", true);
			xmlHttp.send();
		} else if (stringaTirocinio == "tirocinio2") {

			var nodoTutorAziendale = document.getElementById("sel2");
			while (nodoTutorAziendale.hasChildNodes()) {
				nodoTutorAziendale.removeChild(nodoTutorAziendale.firstChild);
			}

			var attrDisabled = document.createAttribute("disabled");
			nodoTutorAziendale.setAttributeNode(attrDisabled);

			var nodoTutorAccademici = document.getElementById("sel4");
			while (nodoTutorAccademici.hasChildNodes()) {
				nodoTutorAccademici.removeChild(nodoTutorAccademici.firstChild);
			}

			xmlHttp.onreadystatechange = function() {
				if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {

					var lista = xmlHttp.response;

					var nodoTutorAccademici = document.getElementById("sel4");

					var figlioOptionDefaultSel4 = document
							.createElement("option");
					attrDisabled = document.createAttribute("disabled");
					figlioOptionDefaultSel4.setAttributeNode(attrDisabled);

					attrSelect = document.createAttribute("selected");
					figlioOptionDefaultSel4.setAttributeNode(attrSelect);

					attrValue = document.createAttribute("value");
					figlioOptionDefaultSel4.setAttributeNode(attrValue);

					var textOptionDefaultSel4 = document
							.createTextNode("Seleziona un'opzione");
					figlioOptionDefaultSel4.appendChild(textOptionDefaultSel4);

					nodoTutorAccademici.appendChild(figlioOptionDefaultSel4)

					for (var y = 0; y < lista[0].TutorAccademici.length; y++) {

						var figlioOption = document.createElement("option");

						var attrValue = document.createAttribute("value");
						attrValue.value = lista[0].TutorAccademici[y].ID;
						figlioOption.setAttributeNode(attrValue);

						var textOption = document
								.createTextNode(lista[0].TutorAccademici[y].Nome);
						figlioOption.appendChild(textOption);
						nodoTutorAccademici.appendChild(figlioOption);
					}
				}
			}
			xmlHttp.open("GET", "RicercaTutor?tirocinio=tirocinio2", true);
			xmlHttp.send();
		}
	}

	function getProposteAzienda(idProposta) {

		var stringaTirocinio = document.getElementById("sel1").value;

		if (stringaTirocinio == "tirocinio1") {
			var xmlHttp = new XMLHttpRequest();
			xmlHttp.responseType = "json";
			var nodoProposte = document.getElementById("sel3");

			while (nodoProposte.hasChildNodes()) {
				nodoProposte.removeChild(nodoProposte.firstChild);
			}

			xmlHttp.onreadystatechange = function() {
				if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
					var lista = xmlHttp.response;

					for ( var i in lista.Proposte) {
						var figlioOption = document.createElement("option");

						var attrValue = document.createAttribute("value");
						attrValue.value = lista.Proposte[i].ID;
						figlioOption.setAttributeNode(attrValue);

						var textOption = document
								.createTextNode(lista.Proposte[i].Obiettivo);
						figlioOption.appendChild(textOption);

						nodoProposte.appendChild(figlioOption);
					}
				}
			}
			xmlHttp.open("GET", "RicercaPropostaAjax?tutor=Azienda&id="
					+ idProposta, true);
			xmlHttp.send();
		}
	}

	function getProposteAccademici(idProposta) {

		var stringaTirocinio = document.getElementById("sel1").value;
		if (stringaTirocinio == "tirocinio2") {
			var xmlHttp = new XMLHttpRequest();
			xmlHttp.responseType = "json";
			var nodoProposte = document.getElementById("sel3");

			while (nodoProposte.hasChildNodes()) {
				nodoProposte.removeChild(nodoProposte.firstChild);
			}

			xmlHttp.onreadystatechange = function() {
				if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
					var lista = xmlHttp.response;

					for ( var i in lista.Proposte) {
						var figlioOption = document.createElement("option");

						var attrValue = document.createAttribute("value");
						attrValue.value = lista.Proposte[i].ID;
						figlioOption.setAttributeNode(attrValue);

						var textOption = document
								.createTextNode(lista.Proposte[i].Obiettivo);
						figlioOption.appendChild(textOption);

						nodoProposte.appendChild(figlioOption);
					}
				}
			}
			xmlHttp.open("GET", "RicercaPropostaAjax?tutor=Accademico&id="
					+ idProposta, true);
			xmlHttp.send();
		}

	}
</script>