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
			<form action="CompilaModuloRiconoscimento" method="post">
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
							<span class="input-group-text">Corso di laurea</span>
						</div>
						<input type="text" class="form-control" aria-label="Default"
							aria-describedby="inputGroup-sizing-default" name="corso"
							value="<c:out value="${studente.corso}"/>"
							disabled>
					</div>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">Data di nascita</span>
						</div>
						<input type="text" class="form-control" aria-label="Default"
							aria-describedby="inputGroup-sizing-default" name="ndataome"
							value="<c:out value="${studente.dataDiNascita}"/>"
							disabled>
					</div>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">Luogo di nascita</span>
						</div>
						<input type="text" class="form-control" aria-label="Default"
							aria-describedby="inputGroup-sizing-default" name="luogo"
							value="<c:out value="${studente.luogoDiNascita}"/>"
							disabled>
					</div>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">Residenza</span>
						</div>
						<input type="text" class="form-control" aria-label="Default"
							aria-describedby="inputGroup-sizing-default" name="residenza"
							value="<c:out value="${studente.residente}"/>"
							disabled>
					</div>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">Via</span>
						</div>
						<input type="text" class="form-control" aria-label="Default"
							aria-describedby="inputGroup-sizing-default" name="via" 
							value="<c:out value="${studente.via}"/>"
							disabled>
					</div>
					
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">Telefono</span>
						</div>
						<input type="text" class="form-control" aria-label="Default"
							aria-describedby="inputGroup-sizing-default" name="telefono"
							value="<c:out value="${studente.telefono}"/>"
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
								tirocinio interno</label> 
								<select class="form-control" id="sel2"
								name="CFUObbligatorio">
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
								name="CFUEsterno">
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
								id="sel3" name="CFUAccompagnamento">
								<option value="0" selected>0</option>
								<option value="1">1</option>
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
							aria-describedby="inputGroup-sizing-default" name="tipoContratto"
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
	
</script>