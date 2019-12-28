<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="WEB-INF/navbarBlu.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="stiliCSS/stiliMenu.css">
<link rel="stylesheet" href="stiliCSS/stiliPropostaTirocinio.css">

<meta charset="ISO-8859-1">
<title>Relazione Tirocinio</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<jsp:include page="WEB-INF/menu.jsp"></jsp:include>
			</div>

			<div class="col-lg-9"
				style="border: 1px solid #d7d7d7; background-color: white;">
				<p id="titolo" style="font-size: 30px; color: #595959;">Compila
					relazione di tirocinio</p>
				<hr>
				<h6>Clicca sul nome del Tirocinante per poter compilare la relazione di tirocinio</h6>
				<div id="accordion">


					<!-- <div class="card">
						<div id="headingOne>" style="background-color: #2C5278"> -->

					<div id="collapseDiv1" class="collapse-div" role="tablist">
						<div class="collapse-header" id="heading1"
							style="background-color: #2C5278">
							<button data-toggle="collapse" data-target="#collapse1"
								aria-expanded="true" aria-controls="collapse1" id="nome-tutor">
								Nome e cognome(studente)</button>
						</div>

						<div id="collapse1" class="collapse" role="tabpanel"
							aria-labelledby="heading1">
							<div class="collapse-body">

								
								<div class="form-group">
									
									<textarea class="form-control" id="exampleFormControlTextarea1" placeholder="Compila verbale tirocinio..."
										rows="3"></textarea>
								</div>

								<span class="text-right" style="margin-left: 350px;"><button
										type="submit" class="btn btn-primary"
										style="background-color: #2C5278;">INVIA</button></span>
										<hr>

							</div>
						</div>

					</div>


				</div>
			</div>
		</div>

	</div>

	<br>
	<br>
	<jsp:include page="WEB-INF/footer.jsp"></jsp:include>
</body>
</html>