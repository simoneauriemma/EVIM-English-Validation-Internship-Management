<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<!-- Collegamenti esterni -->

<link rel="stylesheet" href="stiliCSS/stiliMenu.css">

<!-- Collegamenti Bootstrap -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">

<!-- Script Bootstrap-->
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

<!-- jQuery library -->
<script src="jquery-3.4.1.min.js"></script>
<script src="Script/jquery.min.js" type="text/javascript"></script>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="ISO-8859-1">


</head>
<body>

	<div class="text-center" style="padding: 0 8px" id="foto">
		<img id="logoEvim" src="foto/icone/EVIM-LOGO.png"
			class="img-responsive lazy  hidden-xs" alt="Logo Evim"
			style="display: inline;">
	</div>


	<div id="side-menu" class="list-group" style="color: black">

		<a class="item" style="background-color: #2C5278; color: white;">Interniship
			Management</a> <a class="item" class="active" href=".">Home</a>

		<!-- nel caso in cui è l'utente ospite -->
		<c:if
			test="${type!= 'studente' && type!= 'azienda' && type!='tutoraccademico' && type!='tutoraziendale' && type!='pdcd'}">
			<a class="item" href="VisualizzaAziende"> Lista Aziende
				convenzionate </a>
			<a class="item" href="VisualizzaTutorAccademici"> Elenco Tutor
				accademici </a>
		</c:if>


		<!-- il caso in cui NON è l'utente ospite -->
		<c:if
			test="${ type== 'studente' || type== 'azienda' || type=='tutoraccademico' || type=='tutoraziendale' || type=='pdcd'}">

			<!--INIZIO GESTIONE TIROCINIO -->

			<a data-toggle="collapse" href="#collapse-3" class="item"
				class="folder collapsed" aria-expanded="false">Gestione
				tirocinio <i id="icon-max" class="fa pull-right fa-plus-square"></i>
			</a>

			<ul id="collapse-3" class="collapse" aria-expanded="false"
				style="height: 0px;">
				<c:if test="${type == 'studente'}">
					<li><a href="VisualizzaAziende"><i
							class="fas fa-angle-right "></i> Lista Aziende convenzionate </a></li>
					<li><a href="VisualizzaTutorAccademici"><i
							class="fas fa-angle-right "></i> Elenco Tutor accademici </a></li>
					<li><a href="#"><i class="fas fa-angle-right "></i>
							Compila questionario valutativo </a></li>
				</c:if>

				<c:if test="${type == 'tutoraccademico'}">
					<!-- Tutor accademico -->
					<li><a href="ListaTirocini"><i class="fas fa-angle-right "></i> Elenco
							tirocinanti attivi </a></li>
					<li><a href="compilaRelazione.jsp"><i class="fas fa-angle-right "></i>
							Compila relazione tirocinio </a></li>
					<li><a href="#"><i class="fas fa-angle-right "></i>
							Annulla svolgimento tirocinio </a></li>

				</c:if>
				<c:if test="${type == 'tutoraziendale'}">
					<!-- Tutor aziendale -->
					<li><a href="ListaTirocini"><i class="fas fa-angle-right "></i> Elenco
							tirocinanti </a></li>
					<li><a href="questionarioValutativo.jsp"><i class="fas fa-angle-right "></i>
							Compila questionario valutativo </a></li>
					<li><a href="compilaRelazione.jsp"><i class="fas fa-angle-right "></i>
							Compila relazione tirocinio </a></li>

				</c:if>
				<c:if test="${type == 'azienda'}">
					<!-- Azienda -->
					<li><a href="ListaTirocini"><i class="fas fa-angle-right "></i> Elenco
							tirocinanti </a></li>
					<li><a href="questionarioValutativo.jsp"><i class="fas fa-angle-right "></i>
							Compila questionario valutativo </a></li>

				</c:if>
			</ul>
			<!-- FINE GESTIONE TIROCINIO -->










			<!-- INIZIO Gestione proposta tirocinio -->
			<c:if
				test="${type=='azienda' || type=='tutoraccademico' || type=='tutoraziendale' }">
				<a data-toggle="collapse" href="#collapse-2" class="item"
					class="folder collapsed" aria-expanded="false">Gestione
					proposta tirocinio <i id="icon-max"
					class="fa pull-right fa-plus-square"></i>
				</a>

				<ul id="collapse-2" class="collapse" aria-expanded="false"
					style="height: 0px;">


					<c:if test="${type=='tutoraziendale'}">
						<li><a href="VisualizzaProposte"><i
								class="fas fa-angle-right "></i> Visualizza proposte </a></li>
					</c:if>
					<c:if test="${type=='tutoraccademico'}">
						<li><a href="visualizzaCreaProposta"><i
								class="fas fa-angle-right "></i> Crea proposta tirocinio interno
						</a></li>
						<li><a href="VisualizzaProposte"><i
								class="fas fa-angle-right "></i> Visualizza proposte </a></li>
					</c:if>
					<c:if test="${type=='azienda'}">
						<li><a href="visualizzaCreaProposta"><i
								class="fas fa-angle-right "></i> Crea proposta tirocinio esterno
						</a></li>

						<li><a href="VisualizzaProposte"><i
								class="fas fa-angle-right "></i> Visualizza proposte </a></li>
					</c:if>


				</ul>
			</c:if>
			<!--FINE Gestione proposta tirocinio -->











			<!-- INIZIO Gestione richiesta tirocinio -->
			<a data-toggle="collapse" href="#collapse-0" class="item"
				class="folder collapsed" aria-expanded="false">Gestione
				richiesta tirocinio <i id="icon-max"
				class="fa pull-right fa-plus-square"></i>
			</a>

			<ul id="collapse-0" class="collapse" aria-expanded="false"
				style="height: 0px;">
				<c:if test="${type == 'studente'}">
					<li><a href="creazioneRichiesta.jsp"><i
							class="fas fa-angle-right "></i> Crea richiesta tirocinio </a></li>
					<li><a href="VisualizzaRichieste"><i
							class="fas fa-angle-right "></i> Visualizza richieste di
							tirocinio </a></li>
					<li><a href="progettoFormativo.jsp"><i class="fas fa-angle-right "></i>
							Visualizza progetto formativo </a></li>
				</c:if>

				<c:if test="${type == 'pdcd'}">
					<li><a href="VisualizzaRichieste"><i class="fas fa-angle-right "></i>
							Visualizza richieste tirocinio interno</a></li>
					<li><a href="VisualizzaRichieste"><i class="fas fa-angle-right "></i>
							Visualizza richieste tirocinio esterno</a></li>
				</c:if>


				<!-- da vedere se queste cose sono vere -->
				<c:if test="${type =='tutoraccademico'}">
					<li><a href="VisualizzaRichieste"><i
							class="fas fa-angle-right "></i> Visualizza e valuta richieste
							tirocinio</a></li>

				</c:if>
				<c:if test="${type =='tutoraziendale'}">
					<li><a href="VisualizzaRichieste"><i
							class="fas fa-angle-right "></i> Visualizza richieste tirocinio</a></li>

				</c:if>
				<c:if test="${type =='azienda'}">
					<li><a href="VisualizzaRichieste"><i
							class="fas fa-angle-right "></i> Valuta richiesta tirocinio</a></li>

				</c:if>

				<c:if
					test="${type =='azienda' || type =='tutoraziendale' || type =='tutoraccademico'}">
					<li><a href="progettoFormativo.jsp"><i class="fas fa-angle-right "></i> Valuta
							progetto formativo </a></li>
				</c:if>
			</ul>








			<!-- INIZIO Gestione rigistro tirociniooo -->

			<a data-toggle="collapse" href="#collapse-5" class="item"
				class="folder collapsed" aria-expanded="false">Gestione registro
				tirocinio <i id="icon-max" class="fa pull-right fa-plus-square"></i>
			</a>

			<ul id="collapse-5" class="collapse" aria-expanded="false"
				style="height: 0px;">
				<c:if test="${type =='studente'}">
					<li><a href="#"><i class="fas fa-angle-right"></i> Compila
							registro tirocinio </a></li>
				</c:if>
				<c:if test="${type=='pdcd'}">
					<li><a href="#"><i class="fas fa-angle-right"></i>
							Visualizza e valuta registro tirocinio </a></li>
				</c:if>

				<c:if test="${type=='tutoraccademico'}">
					<li><a href="#"><i class="fas fa-angle-right"></i>
							Visualizza e valuta registro tirocinio </a></li>
					<li><a href="#"><i class="fas fa-angle-right"></i> Valuta
							attività registro tirocinio</a></li>
				</c:if>
				<c:if test="${type=='tutoraziendale'}">
					<li><a href="#"><i class="fas fa-angle-right"></i> Valuta
							attività registro tirocinio </a></li>
				</c:if>
				<c:if test="${type=='azienda'}">
					<li><a href="#"><i class="fas fa-angle-right"></i>
							Visualizza e valuta registro tirocinio </a></li>
				</c:if>
			</ul>
			<!-- FINE Gestione rigistro tirocinio -->







			<!-- INIZIO Gestione riconoscimento attività -->
			<c:if test="${type=='studente' || type=='pdcd' }">
				<a data-toggle="collapse" href="#collapse-1" class="item"
					class="folder collapsed" aria-expanded="false">Gestione
					riconoscimento attività <i id="icon-max"
					class="fa pull-right fa-plus-square"></i>
				</a>

				<ul id="collapse-1" class="collapse" aria-expanded="false"
					style="height: 0px;">
					<c:if test="${type =='studente'}">
						<li><a href="#"><i class="fas fa-angle-right"></i>
								Compila modulo riconoscimento </a></li>
						<li><a href="#"><i class="fas fa-angle-right"></i>
								Visualizza richieste riconoscimento </a></li>
						<li><a href="#"><i class="fas fa-angle-right"></i>
								Modifica richieste riconoscimento</a></li>
					</c:if>
					<c:if test="${type=='pdcd'}">
						<li><a href="#"><i class="fas fa-angle-right"></i>
								Visualizza e valuta lista richieste riconoscimento </a></li>
					</c:if>
				</ul>
			</c:if>

			<!-- INIZIO gestione account tutor -->
			<c:if test="${type == 'azienda' }">
				<a data-toggle="collapse" href="#collapse-7" class="item"
					class="folder collapsed" aria-expanded="false">Gestione account
					tutor <i id="icon-max" class="fa pull-right fa-plus-square"></i>
				</a>

				<ul id="collapse-7" class="collapse" aria-expanded="false"
					style="height: 0px;">
					<c:if test="${type =='azienda'}">
						<li><a href="gestioneAccount.jsp"><i class="fas fa-angle-right"></i> Crea
								account tutor aziendale </a></li>
						<li><a href="#"><i class="fas fa-angle-right"></i>
								Modifica dati personali </a></li>

					</c:if>
				</ul>
			</c:if>
			<!-- FINE gestione account tutor -->
		</c:if>



		<!-- english validation -->

		<a class="item" style="background-color: #2C5278; color: white;">English
			Validation</a>

	</div>

	<br>
	<br>
</body>

<script>
	$(".item")
			.click(
					function() {

						if ($(this).find("i").hasClass(
								"fa pull-right fa-plus-square")) {
							$(this).find("i").removeClass(
									"fa pull-right fa-plus-square").addClass(
									"fa pull-right fa-minus-square");
						} else
							$(this).find("i").removeClass(
									"fa pull-right fa-minus-square").addClass(
									"fa pull-right fa-plus-square");

					});
</script>
</html>
