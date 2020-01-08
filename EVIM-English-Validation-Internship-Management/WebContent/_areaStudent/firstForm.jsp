<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"
	import="controller.CheckSession, model.SystemAttribute,java.text.SimpleDateFormat,java.time.*,controller.DbConnection, java.sql.Connection, java.sql.ResultSet, java.sql.Statement"%>



<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">



<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="/stiliCSS/stiliMenu.css">
<jsp:include page="/WEB-INF/navbarBlu.jsp"></jsp:include>
</head>

<body onLoad="">

	<div class="container">
	<br><br>
		<div class="row">

			<div class="col-lg-3">
				<jsp:include page="/WEB-INF/menu.jsp"></jsp:include>
			</div>
			<div class="col-lg-9" id="" style="border: 1px solid #d7d7d7; background-color: white;">
			<div class="panel">
										<h2 class="text-center">Richiesta</h2>
										<p class="text-center">Compila tutti i campi per
											effettuare la richiesta</p>
			</div>
									<form id="firstForm">

										<div class="form-group">
											<label for="immatricolazione">Anno di immatricolazione:</label> <select class="form-control"
												id="immatricolazione" required>
												<%
											    	Integer range = Integer.parseInt(new SystemAttribute().getValueByKey("request-matriculation-year-range"));
											    	for(int i = (range*-1); i <= 0; i++){
											    	  LocalDate year = LocalDate.now().plusYears(i);
											    	  LocalDate nextYear = LocalDate.now().plusYears(i+1);
											    	  %>
												<option value="<%= year.getYear() %>"><%= year.getYear() %>/<%= nextYear.getYear() %></option>
												<%
											    	}
											    %>
											</select>
										</div>

										<div class="form-group">
											<!-- NUMERO DI MATRICOLA -->
											<label for="matricola">Matricola</label> <input
												class="form-control" type="number" value="512104999"
												id="matricola" required>
										</div>

										<div class="form-group">
											<label for="ente">Ente di rilascio:</label> <select
												class="form-control" id="ente" required>
												<%
												    Connection conn = new DbConnection().getInstance().getConn();
												    if (conn != null) {
	
												      try {
												        Statement stmt = conn.createStatement();
												        ResultSet r = stmt.executeQuery("SELECT id_ente, name FROM ente WHERE stato = 1");
												        while (r.next()) {
												    	  %>
												<option value="<%= r.getInt("id_ente") %>"><%= r.getString("name") %></option>
												<%												          
												        }
												      } catch (Exception e) {
												        System.out.println(e.getMessage());
												      }      
												      
												    } 											    													    
											    %>
											</select>
										</div>

										<div class="form-group">
											<label for="datarilascio" class="col-2 col-form-label">Data
												di rilascio dell'attestato</label> <input class="form-control"
												type="date" value="2011-08-19" id="datarilascio">
										</div>

										<div class="form-group">
											<label for="datascadenza" class="col-2 col-form-label">Data
												di scadenza dell'attestato</label> <input class="form-control"
												type="date" value="2011-08-19" id="datascadenza">
										</div>

										<div class="form-group">
											<!-- SERIALE CERTIFICATO  -->
											<label for="seriale">Seriale del certificato</label> <input
												class="form-control" type="text" id="seriale" required>
										</div>


										<div class="form-group">
											<label for="lvlcefr">livello CEFR:</label> <select
												class="form-control" id="lvlcefr" required>
												<option>A1</option>
												<option>A2</option>
												<option>B1</option>
												<option>B2</option>
												<option>C1</option>
												<option>C2</option>
											</select>
										</div>

										<div class="form-group">
											<label for="cfu">CFU da Conseguire:</label> <select
												class="form-control" id="cfu" required>
												<option>3</option>
												<option>6</option>
											</select>
										</div>

										<div class="form-group">
											<button type="submit" class="btn btn-primary btn-submit">Invia</button>
										</div>

										<div class="clearfix"></div>
									</form>
			</div>
		</div>
	</div>
	
	<script src="<%= request.getContextPath() %>/js/pages/scripts.js"></script>
		
	<script src="<%= request.getContextPath() %>/js/pages/scripts_firstForm.js"></script>

</body>
<jsp:include page="/WEB-INF/footer.jsp"></jsp:include>
</html>
