<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="navbarBlu.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<style>
#foto1, #foto2, #foto3 {
	width: 800px;
	height: 400px;
}
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="ISO-8859-1">

<title>Home</title>
</head>
<body>
	<div class="container">
		<div class="row">

			<div class="col-lg-3">
				<jsp:include page="menu.jsp"></jsp:include>
			</div>
			<div class="col-lg-9" id=""
				style="border: 1px solid #d7d7d7; background-color: white;">
				<br>
				<c:choose>

					<c:when test="${empty result}">

					</c:when>

					<c:when test="${result == true}">
						<div class="alert alert-success" role="alert">Registrazione
							avvenuta con successo</div>
					</c:when>
					
					
					<c:otherwise>
						
					</c:otherwise>
				</c:choose>
				
				<c:if test="${risultatoInserimentoProposta == true}">
						<div class="alert alert-success" role="alert">
						  Inserimento effettuato con successo
						</div>
					</c:if>
					
					<c:if test="${risultatoInserimentoProposta == false}">
						<div class="alert alert-danger" role="alert">
						  Inserimento non effettuato con successo
						</div>
					</c:if>
					
				<c:if test="${modifica==true}">
				 <div class="alert alert-success" role="alert">
						  Modifica effettuato con successo
						</div>
				</c:if>
				
				<c:if test="${modifica == false}">
						<div class="alert alert-danger" role="alert">
						  Modifica non effettuato con successo
						</div>
					</c:if>
					
				<c:if test="${rimuovi==true}">
					<div class="alert alert-success" role="alert">
						  Rimosso con successo
						</div>
				</c:if>
				
				<c:if test="${rimuovi==false}">
					<div class="alert alert-danger" role="alert">
						  Non rimosso con successo
						</div>
				</c:if>
				
				
				<div id="carouselExampleControls" class="carousel slide"
					data-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img class="d-block w-100" src="foto/images.jpg"
								alt="First slide" id="foto1">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" src="foto/images_1.jpg"
								alt="Second slide" id="foto2">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" src="foto/images_2.png"
								alt="Third slide" id="foto3">
						</div>
					</div>
					<a class="carousel-control-prev" href="#carouselExampleControls"
						role="button" data-slide="prev"> <span
						class="carousel-control-prev-icon" aria-hidden="true"></span> <span
						class="sr-only">Previous</span>
					</a> <a class="carousel-control-next" href="#carouselExampleControls"
						role="button" data-slide="next"> <span
						class="carousel-control-next-icon" aria-hidden="true"></span> <span
						class="sr-only">Next</span>
					</a>
				</div>
				<br> <br>
				<p>
					Il Sistema <span style="color: #2C5278; font-style: italic;">Internship
						Management</span> è integrato con la piattaforma <span
						style="color: #2C5278; font-style: italic;"> English
						Validation</span>. Si pone come obiettivo principale la digitalizzazione
					di tutte le pratiche necessarie per lo svolgimento del Tirocinio
					formativo o il riconoscimento di attività lavorativa svolta, in
					modo da superare definitivamente i costi e le inefficienze della
					gestione cartacea del processo, garantendo una gestione
					decentralizzataed efficace, così da avere ogni documento
					disponibile in rete ed accessibile alle parti interessate da
					qualsiasi luogo ed in qualsiasi momento.
				</p>
				<br>
			</div>
		</div>
	</div>

</body>
<br>
<script>
	$('.carousel').carousel({
		interval : 3000
	})
	
	window.setTimeout(function() {
    $(".alert").fadeTo(500, 0).slideUp(500, function(){
        $(this).remove(); 
    });
}, 2000);
</script>
<jsp:include page="footer.jsp"></jsp:include>
</html>