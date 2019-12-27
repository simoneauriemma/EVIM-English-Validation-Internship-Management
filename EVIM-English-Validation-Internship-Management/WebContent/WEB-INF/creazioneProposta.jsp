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
textarea.er{
	border:1px solid red;
	}
	
.error{
	color:red;
}
</style>
<link rel="stylesheet" href="stiliCSS/stiliMenu.css">
<link rel="stylesheet" href="stiliCSS/stiliPropostaTirocinio.css">
<jsp:include page="navbarBlu.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<title>Crea proposta</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<jsp:include page="menu.jsp"></jsp:include>
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
								<span class="error" id="spanTutor">Seleziona un tutor</span> 
								<select class="form-control"
								id="tutoraziendale" name="tutorAziendale">
								<option disabled selected value>Seleziona un'opzione</option>
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
						<br>
						<span class="error">I caratteri devono essere tra 10 e 200</span>
					<textarea class="form-control" id="exampleFormControlTextarea1"
						placeholder="Descrivi le competenze da acquisire.." rows="3" name="competenze"></textarea>
				</div>
					
					
				
				<br>

				<div class="form-group">
					<label for="exampleFormControlTextarea1"><i
						class="fas fa-coins" style="margin-right: 5px;"></i>Indicare le attivita formative previste</label>
						<br>
						<span class="error">I caratteri devono essere tra 10 e 200</span>
					<textarea class="form-control" id="exampleFormControlTextarea2"
						placeholder="Descrivi le attivita formative previste.." rows="3" name="attivita"></textarea>
				</div>

				<div class="form-group">
					<label for="exampleFormControlTextarea1"><i
						class="fas fa-info-circle" style="margin-right: 5px;"></i>Indicare gli obiettivi</label>
						<br>
						<span class="error">I caratteri devono essere tra 10 e 200</span>
					<textarea class="form-control" id="exampleFormControlTextarea3"
						placeholder="Descrivi gli obiettivi.." rows="3" name="obiettivo"></textarea>
				</div>

				<div class="form-group">
					<label for="exampleFormControlTextarea1"><i
						class="fas fa-box-open" style="margin-right: 5px;"></i>Indicare la modalita di svolgimento del tirocinio</label>
						<br>
						<span class="error">I caratteri devono essere tra 10 e 400</span>
					<textarea class="form-control" id="exampleFormControlTextarea4"
						placeholder="Descrivi la modalita di svolgimento del tirocinio.." rows="3" name="modalita"></textarea>
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
	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script>

	$(document).ready(function(){
		$(".error").hide();
		
		
		$("#formCreaProposta").submit(function(){
			var res=true;
			$("#formCreaProposta").find("textarea").each(function(){
				if(!validate($(this).attr("id"))){
					$(this).addClass("er").prev().show();
					res=false;
				}
				else if($(this).hasClass("er")){
					$(this).removeClass("er").prev().hide();
				}
				
			});
			if($("#tutoraziendale option:selected").text()=="Seleziona un'opzione"){
				$("#tutoraziendale").css("border","1px solid red");
				$("#spanTutor").show();
				res=false;
			}
			else{
				$("#tutoraziendale").css("border","");
				$("#spanTutor").hide();
			}
			return res;
		});
	
		
		function validate(fieldId){
			var lunghezzaStringa=document.getElementById(fieldId).value.length;
			if(fieldId!="exampleFormControlTextarea4"){
				if(lunghezzaStringa<10 || lunghezzaStringa>200)
						return false;
				return true;
			}
			else{
				if(lunghezzaStringa<10 || lunghezzaStringa>400)
					return false;
				return true;
			}
			
		}
	});
</script>

</html>