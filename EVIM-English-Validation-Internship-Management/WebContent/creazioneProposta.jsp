<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
#button-container{
text-align: center;
}
</style>
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
					proposta di tirocinio curriculare</p> <hr>


<form action="creaProposta" id="formCreaProposta">
				
			<!--  	<i class="fa fa-user" style="margin-right: 5px;"></i>Tutor
					accademico
				 <input type="text"
							class="form-control" id="usr"> <br>-->
			
				<c:if test="${type eq 'azienda'}">
					<div class="scelta-drop">
						<div class="form-group" style="width: 200px;">
							<label for="sel1"><i class="fas fa-briefcase"></i>
								Tutor Aziendale</label> 
								<select class="form-control"
								id="tutoraziendale" name="tutorAziendale">
								<c:forEach items="${elencoTutorAziendali}" var="tutor">
									<option value='<c:out value="${tutor.id}"/>'>
									<c:out value="${tutor.nome}"/> <c:out value="${tutor.cognome}"></c:out>
									</option>
								</c:forEach>
								
							</select>
						</div>
					</div>
				</c:if>
				
					<div class="form-group">
					<label for="exampleFormControlTextarea1"><i
						class="fas fa-coins" style="margin-right: 5px;"></i>Indicare le competenze da acquisire</label>
					<textarea class="form-control" id="exampleFormControlTextarea1"
						placeholder="Descrivi le competenze da acquisire.." rows="3" name="sede"></textarea>
				</div>
					
					
				
				<br>

				<div class="form-group">
					<label for="exampleFormControlTextarea1"><i
						class="fas fa-coins" style="margin-right: 5px;"></i>Indicare le attivita formative previste</label>
					<textarea class="form-control" id="exampleFormControlTextarea1"
						placeholder="Descrivi le attivita formative previste.." rows="3" name="tema_ambito"></textarea>
				</div>

				<div class="form-group">
					<label for="exampleFormControlTextarea1"><i
						class="fas fa-info-circle" style="margin-right: 5px;"></i>Indicare gli obiettivi</label>
					<textarea class="form-control" id="exampleFormControlTextarea1"
						placeholder="Descrivi gli obiettivi.." rows="3" name="obiettivo"></textarea>
				</div>

				<div class="form-group">
					<label for="exampleFormControlTextarea1"><i
						class="fas fa-box-open" style="margin-right: 5px;"></i>Indicare la modalita di svolgimento del tirocinio</label>
					<textarea class="form-control" id="exampleFormControlTextarea1"
						placeholder="Descrivi la modalita di svolgimento del tirocinio.." rows="3" name="materiale_risorse"></textarea>
				</div>
				
				<div id="button-container">
					<button type="submit" class="btn btn-secondary" id="button">CONFERMA
					</button>
				</div> <br>
			</form>

			</div>
		</div>
	</div>
	<br>
	<br>
	<jsp:include page="WEB-INF/footer.jsp"></jsp:include>
</body>
<script>
	$(document).ready(function(){
		alert("nascondo");
		$(".error".hide();
		
		$("formCreaProposta").find("textarea").each(function(){
			if(!validate($(this).attr("id"))){
				$(this).addClass("er").next().show();
				res=false;
			}
			else if($(this).hasClass("er"))
				$(this).removeClass("er").next().hide();
		});
		return res;
	});
		
		function validate(fieldId){
			alert("id"+fieldId);
			var lunghezzaStringa=document.getElementById(fieldId).val().length;
			if(lunghezzaStringa>200)
				return false;
			return true;
		}
</script>

</html>