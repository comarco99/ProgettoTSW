<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, it.unisa.model.*,  it.unisa.control.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.css">
<meta charset="ISO-8859-1">
<title>HogwartShop</title>
<script src="Validation.js"></script>
<link href="Style.css" type="text/css" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="Navbar.jsp"></jsp:include>
		<div id="accHeader">
			<h1 id = "HaccHeader">CREA UN ACCOUNT</h1>
		</div>
		<% 	String errore = (String)request.getAttribute("errore");
		if(errore != null){%>
			<div id="errore">
				<p>L'indirizzo email è già associato ad un'altro account</p>
			</div>
		<%} %>
		<!-- il form viene indirizzato alla servlet -->
		<div id="formDiv">
		<form name ="formRegistrazione" action="<%=response.encodeURL("NewAccount?azione=aggiungi")%>" method="POST">
  			<label for="nome">Nome:</label>
  			<input type="text" id="nome" name="nome" required autofocus><br>
  			<div class="prob"></div>
  			<label for="cognome">Cognome:</label>
 		  	<input type="text" id="cognome" name="cognome" required ><br>
 		  	<div class="prob"></div>
 		  	<label for="indirizzo">Indirizzo:</label>
 		  	<input type="text" id="indirizzo" name="indirizzo" placeholder= "Via, Civico, Città(CAP), Nazione" ><br>
 		  	<div class="prob"></div>
 		  	<label for="lnascita"> Luogo di Nascita:</label>
 		  	<input type="text" id="lnascita" name="lnascita"  ><br>
 		  	<div class="prob"></div>
 		  	<label for="dnascita">Data di Nascita:</label>
 		  	<input type="date" id="dnascita" name="dnascita" required placeholder= "yyyy-mm-dd" onblur="dataValidation()"><br>
 		  	<div id="dataProblems" class="prob"></div>
 		  	<label for="telefono">Telefono:</label>
 		  	<input type="text" id="telefono" name="telefono"
 		  	onchange="phoneValidation()" required><br>
 		  	<div id="telProblems" class="prob"></div>
 		  	<label for="email">Email:</label>
 		  	<input type="text" id="email" name="email"  required  ><br>
 		  	<div id="emailProblems" class="prob"></div>
 		  	<label for="password">Password:</label>
 		  	<input type="password" id="password" name="password" required onchange="passValidation()"><br>
 		 	<div id="passProblems" class="prob"></div>
 		  	  <input type="submit" value="Crea" id="submit" onclick="mailValidation()">
		</form>
		</div>

	<jsp:include page="Footer.jsp"></jsp:include> 
</body>
</html>