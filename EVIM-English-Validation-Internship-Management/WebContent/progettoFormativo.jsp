<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="WEB-INF/navbarBlu.jsp"></jsp:include>


<html>
<head>
<!-- Collegamenti esterni -->

<link rel="stylesheet" href="stiliCSS/stiliProgettoFormativo.css">

<meta charset="ISO-8859-1">

<title>Progetto formativo</title>

</head>
<body>
	<div class="container">
		<div class="row">

			<div class="col-lg-3">
				<jsp:include page="WEB-INF/menu.jsp"></jsp:include>
			</div>

			<div class="col-lg-9"
				style="border: 1px solid #d7d7d7; background-color: white;">
				<p id="titolo">Lista progetti formativi</p>
				<br>
				<div id="accordion">
					<div class="card">
						<div id="headingOne" style="background-color: #2C5278">
							<h6 class="mb-0">
								<button data-toggle="collapse" data-target="#collapseOne"
									aria-expanded="true" aria-controls="collapseOne" id="id-prog">1.
									Num. Progetto formativo #</button>
							</h6>
						</div>

						<div id="collapseOne" class="collapse"
							aria-labelledby="headingOne" data-parent="#accordion">
							<div class="card-body">
								<table class="table table-striped"
									style="border: 1px solid #ddd">
									<tbody>
										<tr>
											<td class="icon"><i class="fas fa-file-pdf"></i></td>
											<td><button>Visualizza progetto formativo</button></td>
											<td class=""><a href="#"></a></td>
										</tr>
										<tr>
											<td class="icon"><i class="fas fa-book-open"></i></td>
											<td>Tipo di tirocinio</td>
											<td class="text-center">#</td>
										</tr>
										<tr>
											<td class="icon"><i class="fas fa-signature"></i></td>
											<td class="">Valuta progetto formativo</td>
											<td class="text-center">

												<form action="valutaRichiesta" method="post">
													<button class="bottone" id="accetta" value="nonSelezionato">
														<i class="fas fa-check-square"></i>
													</button>
												</form>
												<form action="valutaRichiesta" method="post">
													<button class="bottone" id="rifiuta" value="nonSelezionato">
														<i class="far fa-times-circle"></i>
													</button>
												</form>


											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>


			</div>
		</div>
	</div>
	<br>
	<br>
</body>

<jsp:include page="WEB-INF/footer.jsp"></jsp:include>

<script>
	$(document).ready(function() {
		$("#accetta").click(function() {
			$(this).css("background-color", "green");
			$(this).css("color", "white");
			$(this).css("outline", "none");
			$(this).val("selezionato");
			$("#rifiuta").attr("disabled", "true");

		});
		$("#rifiuta").click(function() {
			$(this).css("background-color", "red");
			$(this).css("color", "white");
			$(this).css("outline", "none");
			$(this).val("selezionato")
			$("#accetta").attr("disabled", "true");
		});
	});
</script>