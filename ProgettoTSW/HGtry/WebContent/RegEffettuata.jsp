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
		<div id="effettuata">
			<div id="heffettuata">
				<h1>Registrazione Effettuata</h1>
			</div>
			<div id="peffettuata">
				<p>Clicca qui per tornare alla home</p>
			</div>
			<div id="aeffettuata">
				<a href="Home.jsp"><i class="fab fa-fort-awesome"></i></a>
			</div>
		</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>