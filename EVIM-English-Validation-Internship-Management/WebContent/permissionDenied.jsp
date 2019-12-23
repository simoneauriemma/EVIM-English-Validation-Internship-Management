<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="stiliCSS/stiliRichiesteTirocinio.css">
<jsp:include page="WEB-INF/navbarBlu.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<title>Errore</title>
</head>
<body>
	<div class="container">
		<div class="row">

			<div class="col-lg-3">

				<jsp:include page="WEB-INF/menu.jsp"></jsp:include>

			</div>



			<div class="col-lg-9" id="col-9">

				<p id="titolo">Attenzione! Permessi non consentiti.</p>
			</div>
		</div>
	</div> 
	<br>
	<jsp:include page="WEB-INF/footer.jsp"></jsp:include>
	
</body>
</html>