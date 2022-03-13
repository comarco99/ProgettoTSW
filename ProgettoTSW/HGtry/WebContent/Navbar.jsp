<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.css">
   <link rel="stylesheet" href="Style.css">
         		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
 
<nav>

	
	<div id= "spanToggle"><span class="toggle"><i class="fa fa-bars"></i></span></div>
	<div id= "spanClose"><span class="toggleClose"><i class="fas fa-times"></i></span></div>
	
      			<ul id="topNavMin"> 
      				<li><a href="Home.jsp">HOME</a></li>
      				<li class="menuMin"><a href="#">PRODOTTI <i class="fas fa-chevron-down"></i></a>
                  <ul class="submenuMin">
                    <li> <a class ="link1Min" href="<%=response.encodeURL("ProductControl?tipo=bacchette")%>">BACCHETTE</a></li>
                    <li><a class ="link1Min" href="<%=response.encodeURL("ProductControl?tipo=abbigliamento")%>">ABITI</a></li>
                    <li><a class ="link1Min" href="<%=response.encodeURL("ProductControl?tipo=gadgets")%>">GADGET</a></li>
                    <li><a class ="link1Min" href="<%=response.encodeURL("ProductControl?tipo=libri")%>">LIBRI</a></li>
                    <li><a class ="link1Min" href="<%=response.encodeURL("ProductControl?tipo=repliche")%>">RIPRODUZIONI</a></li>
                  </ul>
                </li>
      				<li><a href="ChiSiamo.jsp">ABOUT</a></li>
              	<li><a href="faq.jsp">FAQ</a></li>
              	
      			</ul>
	<!-- ----------------------------------------------------- -->
	
      			<ul id="topNav"> 
      				<li><a href="Home.jsp">HOME</a></li>
      				<li class="menu"><a href="#">PRODOTTI <i class="fas fa-chevron-down"></i></a>
                  <ul class="submenu">
                    <li> <a class ="link1" href="<%=response.encodeURL("ProductControl?tipo=bacchette")%>">BACCHETTE</a></li>
                    <li><a class ="link1" href="<%=response.encodeURL("ProductControl?tipo=abbigliamento")%>">ABITI</a></li>
                    <li><a class ="link1" href="<%=response.encodeURL("ProductControl?tipo=gadgets")%>">GADGET</a></li>
                    <li><a class ="link1" href="<%=response.encodeURL("ProductControl?tipo=libri")%>">LIBRI</a></li>
                    <li><a class ="link1" href="<%=response.encodeURL("ProductControl?tipo=repliche")%>">RIPRODUZIONI</a></li>
                  </ul>
                </li>
      				<li><a href="ChiSiamo.jsp">ABOUT</a></li>
              	<li><a href="faq.jsp">FAQ</a></li>
              	
      			</ul>
      			
      			 <div id="nitems">
          			<ul>
          			
          			 <li><a class="NavItems" href="#" ><i class="fa fa-user" aria-hidden="true" onClick="showLittleLoginFormJQuery(<%=session.getAttribute("isLogged")%>);"></i></a></li>
				            	
						     
				            	<div class="miniLog2" id="miniLog2">
									<div id="notLogged2">
										<h3>LOGIN</h3>
										<br>
											<form action="./LoginControl" method="post" id="miniformLog2">
												<input type="text" name="username" placeholder="user@xxx.yy" id="logUser2">
												<input type="password" name="password" placeholder="password" id="logPass2">
												<input type="submit" id="miniLogSub" value="Accedi" onclick="checkLittleLogin();">
												<!-- checLogin controlla la correttezza dei campi del form -->
											</form>
										<p>Sei un nuovo cliente?</p>
										<p><a href="./Registrazione.jsp">REGISTRATI</a></p>
									</div>
									 <div id="logged2">
										<a href="./AreaPersonale.jsp">Area Personale</a>
										<br>
										<a href="<%=response.encodeURL("LogoutControl?")%>">Esci</a>
									</div> 
								</div>
          			 	
          			 
            		<li><a class="NavItems" href="<%=response.encodeURL("CarrelloControl?action")%>"><i class="fa fa-shopping-cart" aria-hidden="true"></i></a></li> 
         		 </ul>
       			</div>
      		</nav>

      		<script >
      		
  				$('.toggle').click(function(){
					$('#spanToggle').css("display", "none");
  					$('#topNavMin').css("display", "block");
  					
  					$('.NavItems').css("display", "none");
  					$('.toggleClose').css("display", "block");
  					
  					
  				
  					
  				});
  				
  				$('.toggleClose').click(function(){
  					$('.toggleClose').css("display", "none");
  					$('#topNavMin').css("display", "none");
  					$('#spanToggle').css("display", "block");
  					$('.NavItems').css("display", "block");
  					
  				});
  			
  				$('.menuMin').click(function(){
  					if(!($('.submenuMin').is(':visible'))){
  					$('.menuMin').children('.submenuMin').slideDown();
  					$('.menuMin').css("height", "400px");
  					$('#topNavMin').css("height", "800px");
  					$('.menuMin:hover').css("background-color", "gray");
  					flag=1;
  					}
  					else{
  						$('.menuMin').css("height", "22px");
  						$('#topNavMin').css("height", "600px");
  						$('.menuMin').children('.submenuMin').slideUp();
  					}	
  				});
  				
  				$(window).on('resize', function(){
  			      var win = $(this); //this = window
  			      if (win.height() >= 820) { $('#topNavMin').css("display", "none") ;
  			    $('.toggleClose').css("display", "none");
  			    $('#spanToggle').css("display", "block");}
  			      if (win.width() >= 1280) { /* ... */ }
  			});
  				
      		</script>
      		
      		
      		
</html>