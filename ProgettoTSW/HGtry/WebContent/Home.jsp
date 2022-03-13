<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, it.unisa.model.*,  it.unisa.control.*"%>
     
    
 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="ISO-8859-1">
<title>HogwartShop</title>
<link href="Style.css" type="text/css" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="Navbar.jsp"></jsp:include>
	
	<div class="cover cent">
		<div class="coverSlide">
			
			<input type="radio" name="rb" id="slide1" checked>
			<input type="radio" name="rb" id="slide2" >
			<input type="radio" name="rb" id="slide3" >
		
		<div class="slides s1">
		<a href="<%=response.encodeURL("ProductControl?tipo=bacchette")%>"><img src="./img/bacchetta_draco.png"></a>
		</div>
		<div class="slides">
			<a href="<%=response.encodeURL("ProductControl?tipo=abbigliamento")%>"><img src="./img/sciarpaSerpeverde.png"></a>
		</div>
		<div class="slides">
			<a href="<%=response.encodeURL("ProductControl?tipo=repliche")%>"><img src="./img/doni_della_morte.png"></a>
		</div>
		
		
		</div>
		<div id="labelRadio">
				<label for="slide1" class="label"></label>
				<label for="slide2" class="label"></label>
				<label for="slide3" class="label"></label>
			</div>
	</div>
	
			
		<div id="fortunato">
				<button class="bottoneFortuna" type="button" onclick="fortunato();">MI SENTO FORTUNATO</button>
				<br>	
				<div id="fortunatoDescrizione" class="fortunatoDescrizione">
				<br>
				</div>
		</div>
	
		<div id="twoSquare">
		<div id="squareOne">
			<div id="squareOneText">
			<h1>Hai bisogno di un <br>breve riassunto?</h1>
			<p>Clicca subito play!</p>
			</div>
			<div id="squareOneVideo">
				<iframe allowfullscreen="allowfullscreen" src="https://www.youtube.com/embed/4wAJejqTFGQ?controls=1&autohide=1" >
					</iframe>	
			</div>
			
			
				
			</div>
			
	
			<div id="squareTwo">
				<div id="smistamento">
				<h1>Discover your<br> Hogwarts house</h1>
				<p>Clicca sul cappello per iniziare lo smistamento</p>
				<a href="https://www.potterheads.net/tests/?test=casa"><i class="fas fa-hat-wizard"></i></a>
				</div>
			
			</div>
		</div>
			
			<div id="cardText"><h1>i più venduti</h1></div>
			<div id="cardVendite">
					
					<div id="cardOne">
						<a href="<%=response.encodeURL("CardServlet?nomeProdotto=Mappa+del+Malandrino")%>"><img src="./img/mappa_malandrino.png"></a>
						<div class="cardDescrizione">
						<h3>Mappa del Malandrino</h3>
						<p>$30.0</p>
						</div>
					</div>
					<div id="cardTwo">
						<a href="<%=response.encodeURL("CardServlet?nomeProdotto=Sciarpa+Grifondoro")%>"><img src="./img/sciarpaGrifondoro.png"></a>
						<div class="cardDescrizione">
						<h3>Sciarpa Grifondoro</h3>
						<p>$29.95</p>
						</div>
					</div>
					<div id="cardThree">
						<a href="<%=response.encodeURL("CardServlet?nomeProdotto=Giratempo")%>"><img src="./img/giratempo.png"></a>
					    <div class="cardDescrizione">
						<h3>Giratempo</h3>
						<p>$59.50</p>
					
						</div>
					</div>
					<div id="cardFour">
						<a href="<%=response.encodeURL("CardServlet?nomeProdotto=T-Shirt+Dobby")%>"><img src="./img/magliaDobby.png"></a>
						<div class="cardDescrizione">
						<h3>T-Shirt Dobby</h3>
						<p>$24.95</p>

						</div>
					</div>
			</div>
			<jsp:include page="Footer.jsp"></jsp:include>
</body>
	
