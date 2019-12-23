<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="stiliCSS/stiliMenu.css">
<link rel="stylesheet" href="stiliCSS/stiliPropostaTirocinio.css">
<jsp:include page="WEB-INF/navbarBlu.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<title>Crea proposta</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<jsp:include page="WEB-INF/menu.jsp"></jsp:include>
			</div>

			<div class="col-lg-9"
				style="border: 1px solid #d7d7d7; background-color: white;">
				<p id="titolo" style="font-size: 30px; color: #595959;">Crea
					proposta di tirocinio curriculare</p>



				<div>
				<i class="fa fa-user" style="margin-right: 5px;"></i>Tutor
					accademico
				 <input type="text"
							class="form-control" id="usr"> <br>
					
					<i class="fas fa-building" style="margin-right: 5px;"></i> Sede <input type="text"
							class="form-control" id="sede"> 
					</div>		
				
				<br>

				<div class="form-group">
					<label for="exampleFormControlTextarea1"><i
						class="fas fa-coins" style="margin-right: 5px;"></i>Tema/Ambito</label>
					<textarea class="form-control" id="exampleFormControlTextarea1"
						placeholder="Descrivi tema/ambito..." rows="3"></textarea>
				</div>

				<div class="form-group">
					<label for="exampleFormControlTextarea1"><i
						class="fas fa-info-circle" style="margin-right: 5px;"></i>Obiettivo
						formativo</label>
					<textarea class="form-control" id="exampleFormControlTextarea1"
						placeholder="Descrivi obiettivo formativo..." rows="3"></textarea>
				</div>

				<div class="form-group">
					<label for="exampleFormControlTextarea1"><i
						class="fas fa-box-open" style="margin-right: 5px;"></i>Materiale/Risorse</label>
					<textarea class="form-control" id="exampleFormControlTextarea1"
						placeholder="Descrivi materiale/risorse..." rows="3"></textarea>
				</div>

			</div>
		</div>
	</div>
	<br>
	<br>
	<jsp:include page="WEB-INF/footer.jsp"></jsp:include>
</body>
</html>