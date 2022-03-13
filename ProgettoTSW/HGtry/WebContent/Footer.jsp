<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    %>
<!DOCTYPE html>
<html>
<head>
<link href="Style.css" type="text/css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>HogwartShop</title>
</head>
<body>
	<div id="footer">
		<div id="info" >
			<h4 class="title">HOGWARTSHOP.IT</h4>
			<p class="descr" style="font-size:100%">Lorem ipsum dolor sit amet, consectetur adipiscing elit. <br>Sed sit amet lobortis leo, non molestie libero. </p>
			<p style="font-size:100%">facebook instagram ecc</p>
		</div>
		
		<div id="contatti">
			<h4 class="title">CONTATTI</h4>
			<br>
			<i class="indirizzo"></i> Via della speranza, Milano <br>
			<i class="telefono"></i> +39 123 345 6789 <br>
			<i class="mail"></i> info@hogwartshop.it<br>
		</div>
		
		<div id="newsletter">
			<h4 class="title">NEWSLETTER</h4>
			<p class="descr">Non perdere le nostre novita!</p>
			<br>
			<form action="" method="post" class="news">
				<input type="text" name="email" placeholder="email" class="newsletterForm"><br><br>
				<input type="submit" value="invia" class="newsletterButton">
			</form>
		</div>
	</div>
	<script src="./js/bjquery-3.5.1.min.js" type="text/javascript"></script>
	<script src="./js/function.js" type="text/javascript"></script>
	<script src="./js/Validation.js" type="text/javascript"></script>
</body>
</html>