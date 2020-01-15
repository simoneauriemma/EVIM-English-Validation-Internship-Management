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
		<a href="."> <img id="logoEvim" src="foto/icone/EVIM-LOGO.png"
			class="img-responsive lazy  hidden-xs" alt="Logo Evim"
			style="display: inline;">
		</a>
	</div>


	<div id="side-menu" class="list-group" style="color: black">
		<a class="item" class="active" href="."> Home </a> <a class="item"
			style="background-color: #2C5278; color: white;">Internship
			Management</a>

		<!-- nel caso in cui è l'utente ospite -->
		<c:if
			test="${type!= 'studente' && type!= 'azienda' && type!='tutoraccademico' && type!='tutoraziendale' && type!='pdcd' && type!='segreteria'}">
			<a class="item" href="VisualizzaAziende"> Lista aziende
				convenzionate </a>
			<a class="item" href="VisualizzaTutorAccademici"
				style="border-bottom: 1px solid #ddd;"> Elenco Tutor accademici
			</a>
		</c:if>


		<!-- il caso in cui NON è l'utente ospite -->
		<c:if
			test="${ type== 'studente' || type== 'azienda' || type=='tutoraccademico' || type=='tutoraziendale' || type=='pdcd' || type=='segreteria'}">

			<!--INIZIO GESTIONE TIROCINIO -->

			<a data-toggle="collapse" href="#collapse-3" class="item"
				class="folder collapsed" aria-expanded="false"
				style="border-bottom: 1px solid #ddd;">Gestione tirocinio <i
				id="icon-max" class="fa pull-right fa-plus-square"></i>
			</a>

			<ul id="collapse-3" class="collapse" aria-expanded="false"
				style="height: 0px;">

				<c:if
					test="${type == 'azienda' || type=='pdcd' || type=='segreteria' || type == 'tutoraccademico' || type == 'tutoraziendale'}">
					<li><a href="VisualizzaAziende"><i
							class="fas fa-angle-right "></i> Elenco aziende convenzionate </a></li>
					<li><a href="VisualizzaTutorAccademici"><i
							class="fas fa-angle-right "></i> Elenco tutor accademici </a></li>
					<li><a href="ListaTirocini"><i class="fas fa-angle-right "></i>
							Elenco tirocini </a></li>
				</c:if>
				<c:if test="${type == 'studente'}">
					<li><a href="ListaTirocini"><i class="fas fa-angle-right "></i>
							I miei tirocini </a></li>
				</c:if>
			</ul>
			<!-- FINE GESTIONE TIROCINIO -->










			<!-- INIZIO Gestione proposta tirocinio -->
			<c:if
				test="${type=='azienda' || type=='tutoraccademico' || type=='tutoraziendale'}">
				<a data-toggle="collapse" href="#collapse-2" class="item"
					class="folder collapsed" aria-expanded="false"
					style="border-bottom: 1px solid #ddd;">Gestione proposta
					tirocinio <i id="icon-max" class="fa pull-right fa-plus-square"></i>
				</a>

				<ul id="collapse-2" class="collapse" aria-expanded="false"
					style="height: 0px;">


					<c:if
						test="${type=='tutoraziendale' || type=='tutoraccademico' || type=='azienda'}">
						<li><a href="VisualizzaProposte"><i
								class="fas fa-angle-right "></i> Lista proposte </a></li>
					</c:if>

					<c:if test="${type=='azienda' || type=='tutoraccademico'}">
						<li><a href="visualizzaCreaProposta"><i
								class="fas fa-angle-right "></i> Crea proposta tirocinio </a></li>

					</c:if>

				</ul>
			</c:if>
			<!--FINE Gestione proposta tirocinio -->





			<!-- INIZIO Gestione richiesta tirocinio -->

			<a data-toggle="collapse" href="#collapse-0" class="item"
				class="folder collapsed" aria-expanded="false"
				style="border-bottom: 1px solid #ddd;">Gestione richiesta
				tirocinio <i id="icon-max" class="fa pull-right fa-plus-square"></i>
			</a>

			<ul id="collapse-0" class="collapse" aria-expanded="false"
				style="height: 0px;">
				<c:if test="${type == 'studente'}">
					<li><a href="VisualizzaCreaRichiesta"><i
							class="fas fa-angle-right "></i> Crea richiesta tirocinio </a></li>
				</c:if>

				<li><a href="VisualizzaRichieste"><i
						class="fas fa-angle-right "></i> Lista richieste di tirocinio </a></li>

			</ul>

			<!-- FINE Gestione richiesta tirocinio -->




			<!-- INIZIO Gestione riconoscimento attività -->
			<c:if
				test="${type=='studente' || type=='pdcd' || type=='segreteria' }">
				<a data-toggle="collapse" href="#collapse-1" class="item"
					class="folder collapsed" aria-expanded="false">Gestione
					riconoscimento attività <i id="icon-max"
					class="fa pull-right fa-plus-square"></i>
				</a>

				<ul id="collapse-1" class="collapse" aria-expanded="false"
					style="height: 0px;">
					<c:if test="${type =='studente'}">
						<li><a href="VisualizzaCompilaModuloRiconoscimento"><i
								class="fas fa-angle-right"></i> Compila modulo riconoscimento </a></li>
					</c:if>
					<c:if
						test="${type=='pdcd' || type=='segreteria' || type =='studente'}">
						<li><a href="VisualizzaElencoModuliRiconoscimento"><i
								class="fas fa-angle-right"></i> Lista richieste riconoscimento </a></li>
					</c:if>

				</ul>
			</c:if>
			<!-- FINE Gestione riconoscimeto attivtà -->


			<!-- INIZIO gestione account tutor -->
			<c:if test="${type == 'azienda' }">
				<a data-toggle="collapse" href="#collapse-7" class="item"
					class="folder collapsed" aria-expanded="false"
					style="border-bottom: 1px solid #ddd;">Gestione account tutor <i
					id="icon-max" class="fa pull-right fa-plus-square"></i>
				</a>

				<ul id="collapse-7" class="collapse" aria-expanded="false"
					style="height: 0px;">
					<c:if test="${type =='azienda'}">
						<li><a href="gestioneAccount.jsp"><i
								class="fas fa-angle-right"></i> Crea account tutor aziendale </a></li>
					</c:if>
				</ul>
			</c:if>
			<!-- FINE gestione account tutor -->
		</c:if>




		<!-- english validation -->
		<c:if
			test="${type == 'studente'  || type=='pdcd' || type=='segreteria' }">
			<a class="item" style="background-color: #2C5278; color: white;">English
				Validation</a>

			<c:if test="${type =='studente'}">
				<a class="item" class="active" id="ev1" href="FirstForm">Compila
					modulo riconoscimento</a>
				<a class="item" class="active" id="ev2" href="ViewRequest">Lista
					richieste riconoscimento</a>
				<a class="item" class="active" id="ev3" href="UploadAttached"
					style="border-bottom: 1px solid #ddd;">Carica Allegati</a>
			</c:if>

			<c:if test="${type =='pdcd'}">
				<a class="item" class="active" id="ev4" href="ViewRequestPc">Lista
					richieste riconoscimento</a>
			</c:if>

			<c:if test="${type =='segreteria'}">
				<a class="item" class="active" id="ev5" href="ViewRequestSeg"
					style="border-bottom: 1px solid #ddd;">Lista richieste
					riconoscimento</a>
			</c:if>

		</c:if>

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
