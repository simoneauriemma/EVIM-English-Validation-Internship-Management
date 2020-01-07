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

<!-- <script>
	$(function() {
		$('[data-toggle="popover"]').popover()
	})
	
	$(function () {
  $('[data-toggle="popover"]').popover({
    container: 'body'
  })
})
</script> -->
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
			<form action="CompilaModuloRiconoscimento" method="post"
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
						<h6 class="text-center">Inserisci i CFU da convalidare</h6>
						<br>

						<!-- 		<button type="button" class="btn btn-lg btn-danger" id="pop"
							data-toggle="popover" title="Popover title"
							data-content="And here's some amazing content. It's very engaging. Right?"> * </button> -->
						<p>
							Hai da poter convalidare ancora: <b> # CFU</b>
							
							<!-- popover  -->
							<button type="button" id="asterisco"
								data-container="body" data-toggle="popover"
								data-placement="right"
								data-content="Vivamus sagittis lacus vel augue laoreet rutrum faucibus.">
								<i class="fas fa-asterisk"></i></button>
							<br> <br>
							
							
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
								<label for="sel3"><i class="fas fa-briefcase"></i> N°CFU
									accompagnamento al lavoro </label> <select class="form-control"
									id="sel3" name="CFUAccompagnamento"
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
									aria-describedby="inputGroup-sizing-default" id="oreSvolte"
									name="oreSvolte" required disabled value=0>
							</div>
						</div>
						<!-- FINE gestione cfu -->
					</div>
					<!-- FINE lato sinistro -->


					<!-- INIZIO lato destro -->
					<div class="col-lg-6">
						<h6 class="text-center">Dati dell'azienda</h6>
						<br>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Ente/Azienda</span>
							</div>
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="enteAzienda"
								required>
						</div>
						<br>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Indirizzo sede</span>
							</div>
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default"
								name="indirizzoSede" required>
						</div>
						<br>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Profilo</span>
							</div>
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="profilo"
								required>
						</div>
						<br>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Tipo Contratto</span>
							</div>
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default"
								name="tipoContratto" required>
						</div>
						<br>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Periodo</span>
							</div>
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="periodo"
								required>
						</div>
						<br>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Ore svolte</span>
							</div>
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="oreSvolte"
								required>
						</div>

						<br> <br> <br>

						<!-- inserimento file -->
						<h6 class="text-center">Inserisci i file PDF qui</h6>
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
											onchange="readURL1(this);" accept=".pdf" />
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
											onchange="readURL2(this);" accept=".pdf" multiple="multiple" />
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
											onchange="readURL3(this);" accept=".pdf" multiple="multiple" />
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
					<button type="submit" class="btn btn-outline-secondary">APPROVA</button>
				</div>
			</form>
		</div>
	</div>
</div>
<br>
<br>

<jsp:include page="footer.jsp"></jsp:include>


<script>
	$('[data-toggle="popover"]').popover();

	var cfuEsterno = 0;
	var cfuObbligatorio = 0;
	var cfuAccompagnamento = 0;
	var totCFUdisponibili = 12;
	6
	function aggiornoCFUObbligatorio(numeroCFU) {
		var nodoCFU = document.getElementById("oreSvolte");

		var numeroCFUObbligatorio = parseInt(nodoCFU.value);
		var numeroCFUTMP = parseInt(numeroCFU);

		nodoCFU.value = numeroCFUTMP + cfuEsterno + cfuAccompagnamento;
		cfuObbligatorio = parseInt(numeroCFU);
	}

	function aggiornoCFUEsterno(numeroCFU) {
		var nodoCFU = document.getElementById("oreSvolte");

		var numeroCFUEsterno = parseInt(nodoCFU.value);
		var numeroCFUTMP = parseInt(numeroCFU);

		nodoCFU.value = cfuObbligatorio + numeroCFUTMP + cfuAccompagnamento;
		cfuEsterno = parseInt(numeroCFU);
	}

	function aggiornoCFUAccompagnamento(numeroCFU) {
		var nodoCFU = document.getElementById("oreSvolte");

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
			removeUpload();
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
			removeUpload();
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
			removeUpload();
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
</script>