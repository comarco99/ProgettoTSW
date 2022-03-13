<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="Style.css" type="text/css" rel="stylesheet">
    
    <title>ACCEDI</title>
  </head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="Navbar.jsp"></jsp:include>

		<div id="divLogin">
				<h3 id="hLogin">ACCEDI</h3>
				<br>
				<form action="./LoginControl" method="post" id="miniformLog">
				<input type="text" name="username" placeholder="user@xxx.yy" id="logUser">
				<input type="password" name="password" placeholder="password" id="logPass"><br>
				<input type="submit" id="loginCart" value="Accedi" onclick="checkLogin2();">
								<!-- checLogin controlla la correttezza dei campi del form -->
				</form>
				<p id="nCliente">Sei un nuovo cliente?</p>
				<p><a href="./Registrazione.jsp">REGISTRATI</a></p>
		</div>
		
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>