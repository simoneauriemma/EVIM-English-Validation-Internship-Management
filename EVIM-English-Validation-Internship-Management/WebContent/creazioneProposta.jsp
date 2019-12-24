<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


<form action="creaProposta">
				<div>
				
			<!--  	<i class="fa fa-user" style="margin-right: 5px;"></i>Tutor
					accademico
				 <input type="text"
							class="form-control" id="usr"> <br>-->
			
				<c:if test="${type eq 'azienda'}">
					<div class="scelta-drop">
						<div class="form-group">
							<label for="sel1"><i class="fas fa-briefcase"></i>
								Tutor Aziendale</label> 
								<select class="form-control"
								id="tutoraccademico" name="tutorAziendale">
								<c:forEach items="${elencoTutorAziendali}" var="tutor">
									<option value='<c:out value="${tutor.id}"/>'>
									<c:out value="${tutor.nome}"/> <c:out value="${tutor.cognome}"></c:out>
									</option>
								</c:forEach>
								
							</select>
						</div>
					</div>
				</c:if>
				
					
					<i class="fas fa-building" style="margin-right: 5px;"></i> Sede <input type="text"
							class="form-control" id="sede" name="sede"> 
					</div>		
				
				<br>

				<div class="form-group">
					<label for="exampleFormControlTextarea1"><i
						class="fas fa-coins" style="margin-right: 5px;"></i>Tema/Ambito</label>
					<textarea class="form-control" id="exampleFormControlTextarea1"
						placeholder="Descrivi tema/ambito..." rows="3" name="tema_ambito"></textarea>
				</div>

				<div class="form-group">
					<label for="exampleFormControlTextarea1"><i
						class="fas fa-info-circle" style="margin-right: 5px;"></i>Obiettivo
						formativo</label>
					<textarea class="form-control" id="exampleFormControlTextarea1"
						placeholder="Descrivi obiettivo formativo..." rows="3" name="obiettivo"></textarea>
				</div>

				<div class="form-group">
					<label for="exampleFormControlTextarea1"><i
						class="fas fa-box-open" style="margin-right: 5px;"></i>Materiale/Risorse</label>
					<textarea class="form-control" id="exampleFormControlTextarea1"
						placeholder="Descrivi materiale/risorse..." rows="3" name="materiale_risorse"></textarea>
				</div>
				
				<input type="submit" value="Conferma"/>
			</form>

			</div>
		</div>
	</div>
	<br>
	<br>
	<jsp:include page="WEB-INF/footer.jsp"></jsp:include>
</body>
</html>