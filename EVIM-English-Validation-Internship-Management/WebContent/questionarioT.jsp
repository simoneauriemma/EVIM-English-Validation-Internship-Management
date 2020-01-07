<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="WEB-INF/navbarBlu.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<style>
#radio1, #radio2, #radio3, #radio4 {
	margin-right: 5px;
}

#tabella1, #tabella2, #tabella3 {
	margin-left: 50px;
}

p {
	font-weight: bold;
}
</style>
<link rel="stylesheet" href="stiliCSS/stiliMenu.css">
<link rel="stylesheet" href="stiliCSS/stiliPropostaTirocinio.css">

<meta charset="ISO-8859-1">
<title>Questionario valutativo</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<jsp:include page="WEB-INF/menu.jsp"></jsp:include>
			</div>

			<div class="col-lg-9"
				style="border: 1px solid #d7d7d7; background-color: white;">
				<p id="titolo" class="text-center"
					style="font-size: 30px; color: #595959;">Compila questionario
					valutativo</p>
				<hr>

				<div id="accordion">


					<p>1. Valuta il progetto di tirocinio</p>
					<table id="tabella1">
						<tr>
							<td>A) La durata del tirocinio è adeguata agli obiettivi
								formativi:</td>
							<td style="padding-left: 50px;">
								<div>
									<div class="row">
										<div class="form-check form-check-inline">
											<input name="gruppo1" type="radio" id="radio1" value="1"
												checked> <label for="radio4">1</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo1" type="radio" id="radio2" value="2">
											<label for="radio5">2</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo1" type="radio" id="radio3" value="3">
											<label for="radio5">3</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo1" type="radio" id="radio4" value="4">
											<label for="radio5">4</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo1" type="radio" id="radio5" value="5">
											<label for="radio5">5</label>
										</div>
									</div>
								</div>
							</td>

						</tr>

						<tr>
							<td>B) Gli obiettivi formativi previsti sono stati:</td>
							<td style="padding-left: 50px;">
								<div>
									<div class="row">
										<div class="form-check form-check-inline">
											<input name="gruppo2" type="radio" id="radio1" value="1"
												checked> <label for="radio4">1</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo2" type="radio" id="radio2" value="2">
											<label for="radio5">2</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo2" type="radio" id="radio3" value="3">
											<label for="radio5">3</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo2" type="radio" id="radio4" value="4">
											<label for="radio5">4</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo2" type="radio" id="radio5" value="5">
											<label for="radio5">5</label>
										</div>
									</div>
								</div>
							</td>
						</tr>

						<tr>
							<td>C) La collaborazione con il tutor didattico è stata:</td>
							<td style="padding-left: 50px;">
								<div>
									<div class="row">
										<div class="form-check form-check-inline">
											<input name="gruppo3" type="radio" id="radio1" value="1"
												checked> <label for="radio4">1</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo3" type="radio" id="radio2" value="2">
											<label for="radio5">2</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo3" type="radio" id="radio3" value="3">
											<label for="radio5">3</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo3" type="radio" id="radio4" value="4">
											<label for="radio5">4</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo3" type="radio" id="radio5" value="5">
											<label for="radio5">5</label>
										</div>
									</div>
								</div>
							</td>
						</tr>

						<tr>
							<td>D) Il tirocinio formativo è utile all'Ente Ospitante:</td>
							<td style="padding-left: 50px;">
								<div>
									<div class="row">
										<div class="form-check form-check-inline">
											<input name="gruppo4" type="radio" id="radio1" value="1"
												checked> <label for="radio4">1</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo4" type="radio" id="radio2" value="2">
											<label for="radio5">2</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo4" type="radio" id="radio3" value="3">
											<label for="radio5">3</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo4" type="radio" id="radio4" value="4">
											<label for="radio5">4</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo4" type="radio" id="radio5" value="5">
											<label for="radio5">5</label>
										</div>
									</div>
								</div>
							</td>
						</tr>
					</table>
					<br> <br>
					<p>2. Valuta il Tirocinante</p>
					<table id="tabella2">
						<tr>
							<td>A) Competenze informatiche possedute in ingresso:</td>
							<td style="padding-left: 50px;">
								<div>
									<div class="row">
										<div class="form-check form-check-inline">
											<input name="gruppo5" type="radio" id="radio1" value="1"
												checked> <label for="radio4">1</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo5" type="radio" id="radio2" value="2">
											<label for="radio5">2</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo5" type="radio" id="radio3" value="3">
											<label for="radio5">3</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo5" type="radio" id="radio4" value="4">
											<label for="radio5">4</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo5" type="radio" id="radio5" value="5">
											<label for="radio5">5</label>
										</div>
									</div>
								</div>
							</td>

						</tr>

						<tr>
							<td>B) Competenze acquisite al termine nella specifica
								disciplina:</td>
							<td style="padding-left: 50px;">
								<div>
									<div class="row">
										<div class="form-check form-check-inline">
											<input name="gruppo6" type="radio" id="radio1" value="1"
												checked> <label for="radio4">1</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo6" type="radio" id="radio2" value="2">
											<label for="radio5">2</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo6" type="radio" id="radio3" value="3">
											<label for="radio5">3</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo6" type="radio" id="radio4" value="4">
											<label for="radio5">4</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo6" type="radio" id="radio5" value="5">
											<label for="radio5">5</label>
										</div>
									</div>
								</div>
							</td>
						</tr>



						<tr>
							<td>C) Motivazione ed interesse:</td>
							<td style="padding-left: 50px;">
								<div>
									<div class="row">
										<div class="form-check form-check-inline">
											<input name="gruppo8" type="radio" id="radio1" value="1"
												checked> <label for="radio4">1</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo8" type="radio" id="radio2" value="2">
											<label for="radio5">2</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo8" type="radio" id="radio3" value="3">
											<label for="radio5">3</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo8" type="radio" id="radio4" value="4">
											<label for="radio5">4</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo8" type="radio" id="radio5" value="5">
											<label for="radio5">5</label>
										</div>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td>D) Capacità di rapporti personali:</td>
							<td style="padding-left: 50px;">
								<div>
									<div class="row">
										<div class="form-check form-check-inline">
											<input name="gruppo9" type="radio" id="radio1" value="1"
												checked> <label for="radio4">1</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo9" type="radio" id="radio2" value="2">
											<label for="radio5">2</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo9" type="radio" id="radio3" value="3">
											<label for="radio5">3</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo9" type="radio" id="radio4" value="4">
											<label for="radio5">4</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo9" type="radio" id="radio5" value="5">
											<label for="radio5">5</label>
										</div>
									</div>
								</div>
							</td>
						</tr>


					</table>

					<br> <br>
					<p>3. Valuta le strutture universitarie addette alla gestione
						dei Tirocini</p>
					<table id="tabella3">
						<tr>
							<td>A) Hanno fornito informazioni chiare ed esaustive:</td>
							<td style="padding-left: 50px;">
								<div>
									<div class="row">
										<div class="form-check form-check-inline">
											<input name="gruppo10" type="radio" id="radio1" value="1"
												checked> <label for="radio4">1</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo10" type="radio" id="radio2" value="2">
											<label for="radio5">2</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo10" type="radio" id="radio3" value="3">
											<label for="radio5">3</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo10" type="radio" id="radio4" value="4">
											<label for="radio5">4</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo10" type="radio" id="radio5" value="5">
											<label for="radio5">5</label>
										</div>
									</div>
								</div>
							</td>

						</tr>

						<tr>
							<td>B) Hanno fornito assistenza e disponibilità:</td>
							<td style="padding-left: 50px;">
								<div>
									<div class="row">
										<div class="form-check form-check-inline">
											<input name="gruppo11" type="radio" id="radio1" value="1"
												checked> <label for="radio4">1</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo11" type="radio" id="radio2" value="2">
											<label for="radio5">2</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo11" type="radio" id="radio3" value="3">
											<label for="radio5">3</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo11" type="radio" id="radio4" value="4">
											<label for="radio5">4</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo11" type="radio" id="radio5" value="5">
											<label for="radio5">5</label>
										</div>
									</div>
								</div>
							</td>
						</tr>



						<tr>
							<td>C) I servizi forniti via Web sono esaustivi(se
								applicabile):</td>
							<td style="padding-left: 50px;">
								<div>
									<div class="row">
										<div class="form-check form-check-inline">
											<input name="gruppo12" type="radio" id="radio1" value="1"
												checked> <label for="radio4">1</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo12" type="radio" id="radio2" value="2">
											<label for="radio5">2</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo12" type="radio" id="radio3" value="3">
											<label for="radio5">3</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo12" type="radio" id="radio4" value="4">
											<label for="radio5">4</label>
										</div>
										<div class="form-check form-check-inline">
											<input name="gruppo12" type="radio" id="radio5" value="5">
											<label for="radio5">5</label>
										</div>
									</div>
								</div>
							</td>
						</tr>

					</table>
					<br> <br> <span class="text-right"
						style="margin-left: 350px;"><button type="submit"
							class="btn btn-primary" data-toggle="modal"
							data-target="#exampleModalCenter"
							style="background-color: #2C5278;">CONFERMA</button> </span> <br> <br>



				</div>
			</div>

		</div>

	</div>

	<br>
	<br>
	<jsp:include page="WEB-INF/footer.jsp"></jsp:include>
</body>
</html>