<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
#button {
	width: 200px;
	text-align: center;
}

#button-container{
text-align: center;
}
</style>
<jsp:include page="WEB-INF/navbarBlu.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<title>Registro tirocinioooo</title>
</head>
<body>
	<div class="container">
		<div class="row">

			<div class="col-lg-3">
				<jsp:include page="WEB-INF/menu.jsp"></jsp:include>
			</div>

			<div class="col-lg-9" id=""
				style="border: 1px solid #d7d7d7; background-color: white;">
				<p id="titolo" style="font-size: 30px; color: #595959;">
					Registro di tirocinio</p>
				<hr>

				<div>
					<p>Studente: #</p>
					<p>Ore svolte: #</p>
					<p>Numero attività svolte: #</p>
				</div>
				
					
				
				<br>

				<table class="table table-striped" style="border: 1px solid #ddd;">
					<tbody>
						<tr style="background-color: #2C5278; color: white;">
							<td class="icon"><i class="fas fa-sort-amount-down"></i></td>
							<td class="">Attività svolta</td>
							<td>Data</td>
							<td>Ora ingresso</td>
							<td>Ora uscita</td>
							<td>Ore tot.</td>
							<td>Firma Tirocinante</td>
							<td>Firma responsabile</td>
						</tr>
						<tr>
							<td></td>
							<td>Attività1</td>
							<td>#</td>
							<td>#</td>
							<td>#</td>
							<td>#</td>
							<td>#</td>
							<td><select class="form-control"
								id="sel1" onchange="" name="sel1">
								<option value="approva" selected>Approva</option>
								<option value="rifiuta">Rifiuta</option>
							</select></td>
						</tr>
						<tr>
							<td></td>
							<td>Attività2</td>
							<td>#</td>
							<td>#</td>
							<td>#</td>
							<td>#</td>
							<td>#</td>
							<td><select class="form-control"
								id="sel1" onchange="" name="sel1">
								<option value="approva" selected>Approva</option>
								<option value="rifiuta">Rifiuta</option>
							</select></td>
						</tr>
						<tr>
							<td></td>
							<td>Attività3</td>
							<td>#</td>
							<td>#</td>
							<td>#</td>
							<td>#</td>
							<td>#</td>
							<td><select class="form-control"
								id="sel1" onchange="" name="sel1">
								<option value="approva" selected>Approva</option>
								<option value="rifiuta">Rifiuta</option>
							</select></td>
						</tr>
						<tr>
							<td></td>
							<td>Attività4</td>
							<td>#</td>
							<td>#</td>
							<td>#</td>
							<td>#</td>
							<td>#</td>
							<td><select class="form-control"
								id="sel1" onchange="" name="sel1">
								<option value="approva" selected>Approva</option>
								<option value="rifiuta">Rifiuta</option>
							</select></td>
						</tr>


					</tbody>
				</table>
				<br>
				 <br>
			</div>
		</div>
	</div>
	<br>

</body>
<jsp:include page="WEB-INF/footer.jsp"></jsp:include>
</html>