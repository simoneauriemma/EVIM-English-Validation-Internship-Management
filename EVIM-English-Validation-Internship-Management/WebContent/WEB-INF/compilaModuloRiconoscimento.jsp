<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<jsp:include page="navbarBlu.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="stiliCSS/stiliRicoscimentoAttivita.css">

<title>Compilazione modulo riconoscimento attività</title>
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
			<div class="row">
				<div class="col-lg-6">
					<h6 class="text-center">Dati dello studente</h6>
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
							value="<c:out value="${studente.surname}"/>"  disabled>
					</div>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">Matricola</span>
						</div>
						<input type="text" class="form-control" aria-label="Default"
							aria-describedby="inputGroup-sizing-default" name="matricola"
							value="<c:out value="${studente.matricola}"/>"  disabled>
					</div>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">Data di nascita</span>
						</div>
						<input type="text" class="form-control" aria-label="Default"
							aria-describedby="inputGroup-sizing-default" name="ndataome"
							disabled>
					</div>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">Luogo di nascita</span>
						</div>
						<input type="text" class="form-control" aria-label="Default"
							aria-describedby="inputGroup-sizing-default" name="luogo"
							disabled>
					</div>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">Residenza</span>
						</div>
						<input type="text" class="form-control" aria-label="Default"
							aria-describedby="inputGroup-sizing-default" name="residenza"
							disabled>
					</div>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">Via</span>
						</div>
						<input type="text" class="form-control" aria-label="Default"
							aria-describedby="inputGroup-sizing-default" name="via" disabled>
					</div>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">Corso di laurea</span>
						</div>
						<input type="text" class="form-control" aria-label="Default"
							aria-describedby="inputGroup-sizing-default" name="laurea"
							disabled>
					</div>
					<br> <br>
					<!-- INIZIO gestione cfu -->
					<div class="scelta-drop">
						<div class="form-group">
							<label for="sel1"><i class="fas fa-briefcase"></i> N°CFU
								totali da riconoscere </label> <input type="text" class="form-control"
								aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="oreSvolte"
								required>
						</div>
					</div>

					<div class="scelta-drop">
						<div class="form-group">
							<label for="sel2"><i class="fas fa-briefcase"></i> N°CFU
								tirocinio interno</label> <select class="form-control" id="sel2"
								name="cfuInterno">
								<option value="sei" selected>6</option>
								<option value="undici">11</option>
							</select>
						</div>
					</div>

					<div class="scelta-drop">
						<div class="form-group">
							<label for="sel3"><i class="fas fa-briefcase"></i> N°CFU
								tirocinio esterno</label> <select class="form-control" id="sel3"
								name="cfuEsterno">
								<option value="sei" selected>6</option>
								<option value="undici">11</option>
								<option value="diciasette">17</option>
								<option value="ventitre">23</option>
							</select>
						</div>
					</div>

					<div class="scelta-drop">
						<div class="form-group">
							<label for="sel3"><i class="fas fa-briefcase"></i> N°CFU
								accompagnamento al lavoro </label> <select class="form-control"
								id="sel3" name="cfuEsterno">
								<option value="zero" selected>0</option>
								<option value="uno">1</option>
							</select>
						</div>
					</div>
					<!-- FINE gestione cfu -->
				</div>
				<!-- FINE lato sinistro -->


				<!-- INIZIO lato destro -->
				<div class="col-lg-6">
					<h6 class="text-center">Dati dell'azienda</h6>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">Ente/Azienda</span>
						</div>
						<input type="text" class="form-control" aria-label="Default"
							aria-describedby="inputGroup-sizing-default" name="azienda"
							required>
					</div>
					<br>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">Indirizzo sede</span>
						</div>
						<input type="text" class="form-control" aria-label="Default"
							aria-describedby="inputGroup-sizing-default"
							name="indirizzoAzienda" required>
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
					
				</div>
			</div>
			<br>

			<div class="text-center">
				<button type="button" class="btn btn-outline-secondary">APPROVA</button>
			</div>
		</div>
	</div>
</div>
<br>
<br>
<jsp:include page="footer.jsp"></jsp:include>


<script>
	
</script>