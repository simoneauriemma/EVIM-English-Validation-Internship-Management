<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<jsp:include page="navbarBlu.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<script src="http://getbootstrap.com/assets/js/docs.min.js"></script>
<script src="jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="stiliCSS/stiliRicoscimentoAttivita.css">

<title>Compilazione modulo riconoscimento attività</title>
<style>

input.er{
	border:1px solid red;
	}
	
.error{
	color:red;
}
</style>
</head>

<div class="container">
	<div class="row">

		<div class="col-lg-3">
			<jsp:include page="menu.jsp"></jsp:include>
		</div>

		<div class="col-lg-9">
			<p id="titolo" class="text-center">Modulo domanda di
				riconoscimento dei crediti formativi previsti per il tirocinio</p>
			<hr>
			<form id="formCompilaModuloRiconoscimento" action="CompilaModuloRiconoscimento" method="post"
				enctype="multipart/form-data">

				<div class="row">
					<div class="col-lg-6">
						<!-- INIZIO lato sinistro -->
						<h6 class="text-center">Dati dello studente</h6>
						<br>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Nome</span>
							</div>
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="nome"
								value="<c:out value="${studente.name}"/>" disabled>
						</div>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Cognome</span>
							</div>
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="cognome"
								value="<c:out value="${studente.surname}"/>" disabled>
						</div>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Matricola</span>
							</div>
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="matricola"
								value="<c:out value="${studente.matricola}"/>" disabled>
						</div>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Corso di laurea</span>
							</div>
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="corso"
								value="<c:out value="${studente.corso}"/>" disabled>
						</div>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Data di nascita</span>
							</div>
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="ndataome"
								value="<c:out value="${studente.dataDiNascita}"/>" disabled>
						</div>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Luogo di nascita</span>
							</div>
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="luogo"
								value="<c:out value="${studente.luogoDiNascita}"/>" disabled>
						</div>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Residenza</span>
							</div>
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="residenza"
								value="<c:out value="${studente.residente}"/>" disabled>
						</div>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Via</span>
							</div>
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="via"
								value="<c:out value="${studente.via}"/>" disabled>
						</div>

						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Telefono</span>
							</div>
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="telefono"
								value="<c:out value="${studente.telefono}"/>" disabled>
						</div>
						<br>

						<!-- INIZIO gestione cfu -->
						<%
							int n = 12;
						%>
						<h6 class="text-center">Inserisci i CFU da convalidare</h6>

					<span id="errore7" class=error>CFU superati</span>
						<!-- cfu rimanenti -->
						<br> Hai ancora <b> <c:choose>
								<c:when test="${CFUInglese == 0}"> 
						12 CFU
						</c:when>
								<c:otherwise>
									<c:out value="${12-CFUInglese}" />
								</c:otherwise>

							</c:choose>
						</b> da poter convalidare.



						<!-- popover  -->
						<a href="#" data-toggle="tooltip"
							title="Agli studenti non possono essere riconosciuti più di 12 CFU tra laurea triennale e magistrale
per certificazioni di competenze acquisite. Pertanto se uno studente ha ottenuto alla triennale il
riconoscimento di 6 CFU per la lingua inglese e 6 CFU per tirocinio (sulla base del riconoscimento di
attività lavorativa), non potrà ottenere ulteriori riconoscimenti né alla triennale né alla magistrale "><i
							class="fas fa-question-circle"></i></a> <br> <br>
						<div class="scelta-drop">
							<div class="form-group">
								<label for="sel2"><i class="fas fa-briefcase"></i> N°CFU
									tirocinio interno</label> <select class="form-control" id="sel2"
									name="CFUObbligatorio"
									onchange="aggiornoCFUObbligatorio(this.value)">
									<option value="0" selected>0</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="11">11</option>
								</select>
							</div>
						</div>

						<div class="scelta-drop">
							<div class="form-group">
								<label for="sel3"><i class="fas fa-briefcase"></i> N°CFU
									tirocinio esterno</label> <select class="form-control" id="sel3"
									name="CFUEsterno" onchange="aggiornoCFUEsterno(this.value)">
									<option value="0" selected>0</option>
									<option value="6">6</option>
									<option value="12">12</option>
								</select>
							</div>
						</div>

						<div class="scelta-drop">
							<div class="form-group">
								<label for="sel4"><i class="fas fa-briefcase"></i> N°CFU
									accompagnamento al lavoro </label> <select class="form-control"
									id="sel4" name="CFUAccompagnamento"
									onchange="aggiornoCFUAccompagnamento(this.value)">
									<option value="0" selected>0</option>
									<option value="1">1</option>
								</select>
							</div>
						</div>

						<!-- cfu totali -->
						<div class="scelta-drop">
							<div class="form-group">
								<label for="sel1"><i class="fas fa-briefcase"></i> N°CFU
									totali da riconoscere </label> <input type="text" class="form-control"
									aria-label="Default"
									aria-describedby="inputGroup-sizing-default" id="cfuTotali"
									name="oreSvolte"  disabled value=0>
							</div>
						</div>
						<!-- FINE gestione cfu -->
					</div>
					<!-- FINE lato sinistro -->


					<!-- INIZIO lato destro -->
					<div class="col-lg-6">
						<h6 class="text-center">Dati dell'azienda</h6>
						<br>
						
						<span id="errore1" class="error">Ente/Azienda deve essere compreso tra 5 e 200 caratteri</span>
							<br>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Ente/Azienda</span>
							</div>
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="enteAzienda"
								id="enteAzienda" required>
						</div>
						
						<br>
						
						<span id="errore2" class="error">Indirizzo sede deve essere compreso tra 5 e 200 caratteri</span>
							<br>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Indirizzo sede</span>
							</div>
							
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default"
								name="indirizzoSede" id="indSede" required>
						</div>
						<br>
						
						<span id="errore3" class="error">Profilo deve essere compreso tra 5 e 200 caratteri</span>
							<br>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Profilo</span>
							</div>
							
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="profilo"
								id="profilo" required>
						</div>
						<br>
						
						<span id="errore4" class="error">Tipo contratto deve essere compreso tra 5 e 200 caratteri</span>
							<br>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Tipo Contratto</span>
							</div>
							
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default"
								name="tipoContratto" id="tipoContratto" required>
						</div> 
						<br>
						
						<span id="errore5" class="error">Periodo deve essere compreso tra 5 e 200 caratteri</span>
							<br>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Periodo</span>
							</div>
							
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="periodo" id="periodo"
								required>
						</div>
						<br>
						
						<span id="errore6" class="error">Ore svolte deve avere un numero positivo e maggiore di 0</span>
							<br>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Ore svolte</span>
							</div>
							
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="oreSvolte"
								id="oreSvolte" required>
						</div>

						<br> <br> <br>

						<!-- inserimento file -->

						<h6 class="text-center">
							Inserisci i file PDF qui <a href="#" id="tt2"
								data-toggle="tooltip"
								title="Va allegata l' attestazione del responsabile aziendale su carta intestata dell'azienda con firma leggibile che descrive nel dettaglio le attività realizzate, copia del documento del responsabile e del contratto di lavoro. "><i
								class="fas fa-question-circle"></i></a>
						</h6>
						<!-- popover  -->

						<br>
						<div class="row">
							<div class="col-lg-4">

								<!-- inserimento primo pdf  -->
								<div class="file-upload1">
									<button class="file-upload-btn1" type="button"
										onclick="$('.file-upload-input1').trigger( 'click' )">Add
										I PDF</button>
									<div class="image-upload-wrap1">
										<input class="file-upload-input1" type='file' name="file1[]"
											onchange="readURL1(this);" accept=".pdf"  required/>
										<div class="drag-text1">
											<h5>
												<i class="fas fa-plus"></i>
											</h5>
										</div>
									</div>
									<div class="file-upload-content1">
										<img class="file-upload-image1" src="#" alt="firstPDF" />
										<div class="image-title-wrap1">
											<button type="button" onclick="removeUpload1()"
												class="remove-image1">
												<i class="fas fa-trash"></i>
											</button>
										</div>
									</div>
								</div>
							</div>

							<!-- inserimento secondo pdf -->
							<div class="col-lg-4">
								<div class="file-upload2">
									<button class="file-upload-btn2" type="button"
										onclick="$('.file-upload-input2').trigger( 'click' )">Add
										II PDF</button>

									<div class="image-upload-wrap2">
										<input class="file-upload-input2" type='file' name="file2[]"
											onchange="readURL2(this);" accept=".pdf" multiple="multiple"
											 required/>
										<div class="drag-text2">
											<h5>
												<i class="fas fa-plus"></i>
											</h5>
										</div>
									</div>
									<div class="file-upload-content2">
										<img class="file-upload-image2" src="#" alt="secondPDF" />
										<div class="image-title-wrap2">
											<button type="button" onclick="removeUpload2()"
												class="remove-image2">
												<i class="fas fa-trash"></i>
											</button>
										</div>
									</div>
								</div>
							</div>

							<!-- inserimento terzo pdf -->
							<div class="col-lg-4">
								<div class="file-upload3">
									<button class="file-upload-btn3" type="button"
										onclick="$('.file-upload-input3').trigger( 'click' )">Add
										III PDF</button>

									<div class="image-upload-wrap3">
										<input class="file-upload-input3" type='file' name="file3[]"
											onchange="readURL3(this);" accept=".pdf" multiple="multiple"
											 required/>
										<div class="drag-text3">
											<h5>
												<i class="fas fa-plus"></i>
											</h5>
										</div>
									</div>
									<div class="file-upload-content3">
										<img class="file-upload-image3" src="#" alt="thirdPDF" />
										<div class="image-title-wrap3">
											<button type="button" onclick="removeUpload3()"
												class="remove-image3">
												<i class="fas fa-trash"></i>
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<br>

				<div class="text-center">
					<input type="submit" id="bott-approva"
						class="btn btn-outline-secondary" value="Approva"/>
				</div>
			</form>
		</div>
	</div>
</div>
<br>
<br>

<jsp:include page="footer.jsp"></jsp:include>


<script>
	var cfuEsterno = 0;
	var cfuObbligatorio = 0;
	var cfuAccompagnamento = 0;

	
	function aggiornoCFUObbligatorio(numeroCFU) {
		var nodoCFU = document.getElementById("cfuTotali");

		var numeroCFUObbligatorio = parseInt(nodoCFU.value);
		var numeroCFUTMP = parseInt(numeroCFU);

		nodoCFU.value = numeroCFUTMP + cfuEsterno + cfuAccompagnamento;
		cfuObbligatorio = parseInt(numeroCFU);
	}

	function aggiornoCFUEsterno(numeroCFU) {
		var nodoCFU = document.getElementById("cfuTotali");

		var numeroCFUEsterno = parseInt(nodoCFU.value);
		var numeroCFUTMP = parseInt(numeroCFU);

		nodoCFU.value = cfuObbligatorio + numeroCFUTMP + cfuAccompagnamento;
		cfuEsterno = parseInt(numeroCFU);
	}

	function aggiornoCFUAccompagnamento(numeroCFU) {
		var nodoCFU = document.getElementById("cfuTotali");

		var numeroCFUOAccompagnamento = parseInt(nodoCFU.value);
		var numeroCFUTMP = parseInt(numeroCFU);

		nodoCFU.value = cfuObbligatorio + cfuEsterno + parseInt(numeroCFU);

		cfuAccompagnamento = parseInt(numeroCFU);
	}
	
	

	/* upload file */
	function readURL1(input) { //prima sezione per inserire il file
		if (input.files && input.files[0]) {

			var reader = new FileReader();

			reader.onload = function(e) {
				$('.image-upload-wrap1').hide(); //nasconde la sezione per inserire i file

				$('.file-upload-image1').attr('src', e.target.result);

				$('.file-upload-content1').show();

			};

			reader.readAsDataURL(input.files[0]);

		} else {
			removeUpload1();
		}
	}

	function readURL2(input) { //seconda sezione per inserire il file
		if (input.files && input.files[0]) {

			var reader = new FileReader();

			reader.onload = function(e) {

				$('.image-upload-wrap2').hide();

				$('.file-upload-image2').attr('src', e.target.result);

				$('.file-upload-content2').show();

			};

			reader.readAsDataURL(input.files[0]);

		} else {
			removeUpload2();
		}

	}

	function readURL3(input) { //terza sezione per inserire il file
		if (input.files && input.files[0]) {

			var reader = new FileReader();

			reader.onload = function(e) {

				$('.image-upload-wrap3').hide();

				$('.file-upload-image3').attr('src', e.target.result);

				$('.file-upload-content3').show();

			};

			reader.readAsDataURL(input.files[0]);

		} else {
			removeUpload3();
		}

	}

	function removeUpload1() {
		$('.file-upload-input1').replaceWith($('.file-upload-input1').clone());
		$('.file-upload-content1').hide();
		$('.image-upload-wrap1').show();
	}
	function removeUpload2() {
		$('.file-upload-input2').replaceWith($('.file-upload-input2').clone());
		$('.file-upload-content2').hide();
		$('.image-upload-wrap2').show();
	}
	function removeUpload3() {
		$('.file-upload-input3').replaceWith($('.file-upload-input3').clone());
		$('.file-upload-content3').hide();
		$('.image-upload-wrap3').show();
	}

	$('.image-upload-wrap1').bind('dragover', function() {
		$('.image-upload-wrap1').addClass('image-dropping1');
	});
	$('.image-upload-wrap2').bind('dragover', function() {
		$('.image-upload-wrap2').addClass('image-dropping2');
	});
	$('.image-upload-wrap3').bind('dragover', function() {
		$('.image-upload-wrap3').addClass('image-dropping3');
	});

	$('.image-upload-wrap1').bind('dragleave', function() {
		$('.image-upload-wrap1').removeClass('image-dropping1');
	});
	$('.image-upload-wrap2').bind('dragleave', function() {
		$('.image-upload-wrap2').removeClass('image-dropping2');
	});
	$('.image-upload-wrap3').bind('dragleave', function() {
		$('.image-upload-wrap3').removeClass('image-dropping3');
	});
	
	/* fine gestione file */
	
	
	
	
	

	$(".error").hide();	

	$("#formCompilaModuloRiconoscimento").submit(function(){
		var res=true;
		/*
        var ente= $("#enteAzienda")
        var sede= $("#indSede").length;
        var profilo= $("#profilo").length;
        var totcfu= $("#cfuTotali").length;*/
      
        if(!validate("enteAzienda")){
        	$("#enteAzienda").addClass("er");
        	$("#errore1").show();
        	res=false;
        }
        else if($("#enteAzienda").hasClass("er")){
        	$("#enteAzienda").removeClass("er");
        	$("#errore1").hide();
        }
        
        if(!validate("indSede")){
        	$("#indSede").addClass("er");
        	$("#errore2").show();
        	res=false;
        }
        else if($("#indSede").hasClass("er")){
        	$("#indSede").removeClass("er");
        	$("#errore2").hide();
        }
        
        if(!validate("profilo")){
        	$("#profilo").addClass("er");
        	$("#errore3").show();
        	res=false;
        }
        else if($("#profilo").hasClass("er")){
        	$("#profilo").removeClass("er");
        	$("#errore3").hide();
        }
        
        if(!validate("tipoContratto")){
        	$("#tipoContratto").addClass("er");
        	$("#errore4").show();
        	res=false;
        }
        else if($("#tipoContratto").hasClass("er")){
        	$("#tipoContratto").removeClass("er");
        	$("#errore4").hide();
        }
        
        if(!validate("periodo")){
        	$("#periodo").addClass("er");
        	$("#errore5").show();
        	res=false;
        }
        else if($("#periodo").hasClass("er")){
        	$("#periodo").removeClass("er");
        	$("#errore5").hide();
        }
        
        if(!validateOre("oreSvolte")){
        	$("#oreSvolte").addClass("er");
        	$("#errore6").show();
        	res=false;
        }
        else if($("#oreSvolte").hasClass("er")){
        	$("#oreSvolte").removeClass("er");
        	$("#errore6").hide();
        }
        
        if(!validateCFU("cfuTotali")){
        	$("#cfuTotali").addClass("er");
        	$("#errore7").show();
        	res=false;
        }
        else if($("#cfuTotali").hasClass("er")){
        	$("#cfuTotali").removeClass("er");
        	$("#errore7").hide();
        }
        
       return res; 
	});

	
	function validate(fieldID){
		var lunghezzaStringa=document.getElementById(fieldID).value.length;
		if(lunghezzaStringa<5 || lunghezzaStringa>200)
			return false;
		return true;
	}
	
	function validateOre(fieldID){
		var oreSvolte=parseInt(document.getElementById(fieldID).value);
		if(oreSvolte > 0)
			return true;
		return false;
	}
	
	function validateCFU(fieldID){
		var cfuTotali=parseInt(document.getElementById(fieldID).value)
			if(cfuTotali>12 || cfuTotali==0)
				return false
			return true;
	}
        

	
</script>