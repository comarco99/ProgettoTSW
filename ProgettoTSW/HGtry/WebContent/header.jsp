<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="Style.css" type="text/css" rel="stylesheet">
    
    <title>HGshop</title>
  </head>
  <body>
    <header>
    	
    
	     <div class="hitems">
          <ul>
            <li class="headerItems">
            <!-- al click su login si apre una finestra gestita dal metodo js showLoginForm -->
            	<a href="#" onClick="showLoginFormJquery(<%=session.getAttribute("isLogged")%>);">utente</a>
            
            	
		<!-- minilog conterrra sia la finestra di login in caso l utente non sia ancra loggato(notlogged)
		    sia la finestra che si apre nel momento in cui l'utente sia gia loggato(logged)-->	
            	<div class="miniLog" id="miniLog">
					<div id="notLogged">
						<br>
						<h3>LOGIN</h3>
						<br>
							<form action="./LoginControl" method="post" id="miniformLog">
								<input type="text" name="username" placeholder="user@xxx.yy" id="logUser">
								<input type="password" name="password" placeholder="password" id="logPass">
								<input type="submit" id="miniLogSub" value="Accedi" onclick="checkLogin();">
								<!-- checLogin controlla la correttezza dei campi del form -->
							</form>
						<p>Sei un nuovo cliente?</p>
						<p><a href="./Registrazione.jsp">REGISTRATI</a></p>
					</div>
					 <div id="logged">
						<a href="./AreaPersonale.jsp">Area Personale</a>
						<br>
						<a href="<%=response.encodeURL("LogoutControl?")%>">Esci</a>
					</div> 
				</div>

            
            </li>
            <li class="headerItems"><a href="<%=response.encodeURL("CarrelloControl?action")%>">carrello</a></li>
          </ul>
       </div>

       <div class="logo">
          <a href="Home.jsp"><img src="./img/logo.png" alt="logo"></a>
        </div>
      
        
      </header>




  </body>
</html>