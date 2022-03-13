<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, it.unisa.model.*,  it.unisa.control.*"%>
    
 <% 
 
 //come prima cosa controlliamo sempre che l'utente nella sessione sia effetivamente un amministratore 
 //se cosi non fosse si tenterebbe un accesso illecito da evitare
 
 	UtenteBean utente= (UtenteBean) session.getAttribute("utente");
   Boolean log= (Boolean)session.getAttribute("isLogged");
    if(log==null){
    	response.sendRedirect("./error.jsp");
		return;
    }
    	
	if((utente.getTipo().equals("utente"))||(log==false)){
		response.sendRedirect("./error.jsp");
		return;
		}  	   

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="ISO-8859-1">
<title>HogwartShop</title>
<link href="Style.css?ts=<?=time()?>&quot" type="text/css" rel="stylesheet">
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<jsp:include page="../Navbar.jsp"></jsp:include>
<div id="contFrase">
<h4 id="fraseAdmin"> I hope you are not using  the cloak of Invisibility to be here!</h4></div>
<div id="cercaOrdini">
	<h3 onClick="javascript:mostraNascondiCercaOrdini()" style="cursor: pointer;">CERCA ORDINI <i class="fas fa-angle-down"></i> </h3>


<div id="formCercaOrdini">
		<h4>SCEGLI IL CRITERIO DI VISUALIZZAZIONE</h4><br>
		<form action="<%=response.encodeURL("AdminControl?azione=visualizzaOrdini")%>" method="post">
			<input type="radio" id="criterio" name="criterio" value="asc">
			<label>importo crescente</label><br>
			<input type="radio" id="criterio" name="criterio" value="desc">
			<label>importo decrescente</label><br>		
			<button type="submit">CERCA</button>
		</form>
		
		<%
					Collection<?> orders = (Collection<?>) request.getAttribute("listaOrdiniAdmin");
					if(orders != null && orders.size() > 0) {
						Iterator<?> it  = orders.iterator();
						while(it.hasNext()) {
							OrdineBean or= (OrdineBean)it.next();
		%>
							<table>
								<th>ID</th>
								<th>DATA</th>
								<th>IMPORTO</th>
								
								<tr>
									<td><%=or.getId_ordine()%></td>
									<td><%=or.getData_ordine()%></td>
									<td><%=or.getImporto() %></td>
								</tr>
							</table>
		<%               }
		            } %>
</div>
</div>
<script>
var a=0;
function mostraNascondiCercaOrdini() {
	if(a==0){
		document.getElementById("formCercaOrdini").style.display="block";
		a=1;
	}else{
		document.getElementById("formCercaOrdini").style.display="none";
		a=0;
		}
	}
</script>

<br>
<div id="insert">
	<h3 onClick="javascript:mostraNascondiInsert()"  style="cursor: pointer;">INSERISCI PRODOTTO <i class="fas fa-angle-down"></i> </h3>
		<div id="formInsert">
		<form name ="formInsert" enctype="multipart/form-data" action="<%=response.encodeURL("AdminControl")%>" method="POST">
			<input type="hidden" name="azione" value="aggiungi">
  			<label for="nome">Nome:</label><br>
  			<input type="text" id="nome" name="nome" required autofocus><br>
  			<label for="descrizione">Descrizione:</label><br>
 		  	<input type="text" id="descrizione" name="descrizione" required ><br>
 		  	<label for="prezzo">Prezzo:</label><br>
 		  	<input type="text" id="prezzo" name="prezzo" required><br>
 		  	<label for="quantita">Quantit&agrave:</label><br>
 		  	<input type="text" id="quantita" name="quantita" required><br>
 		  	<label for="categoria">Categoria:</label><br>
 		  	<input type="text" id="categoria" name="categoria" required><br>
 		  	<label for="fotoup">Foto Prodotto:</label><br>
 		  	<input type="file" id="fotoup" name="fotoup" value ="" ><br>
 		  	  <input type="submit" value="Aggiungi" id="submit">
 		  
		</form>
		</div>
	</div>


<script>
var j=0;
function mostraNascondiInsert() {
	if(j==0){
		document.getElementById("formInsert").style.display="block";
		j=1;
	}else{
		document.getElementById("formInsert").style.display="none";
		j=0;
		}
	}
</script>

<br>
<div id="update">
	<h3 onClick="javascript:mostraNascondiUpdate();" style="cursor: pointer;" >MODIFICA PRODOTTO <i class="fas fa-angle-down"></i></h3>
	<div id="formUpdate">
	<form name ="formUpdateNome"  action="<%=response.encodeURL("AdminControl")%>" method="post">
			<div class="aggiornah4"><h4 >Aggiorna nome:</h4></div>
			<input type="hidden" name="azione" value="aggiornaNome">
			<label for="id">ID:</label><br>
  			<input type="text" id="id" name="id" required autofocus><br>
  			<label for="nome">Nome:</label><br>
  			<input type="text" id="nome" name="nome" value ="" ><br>
  			 <input type="submit" value="Aggiorna" id="submit">
  			</form>
  		<div class="aggiornah4"><h4 >Aggiorna descrizione:</h4></div>
  			<form name ="formUpdateDescrizione" action="<%=response.encodeURL("AdminControl")%>" method="post">
			<input type="hidden" name="azione" value="aggiornaDescrizione">
			<label for="id">ID:</label><br>
  			<input type="text" id="id" name="id" required autofocus><br>
  			<label for="descrizione">Descrizione:</label><br>
 		  	<input type="text" id="descrizione" name="descrizione" value ="" ><br>
  			 <input type="submit" value="Aggiorna" id="submit">
  			</form>
  			<div class="aggiornah4"><h4 >Aggiorna prezzo:</h4></div>
  			<form name ="formUpdatePrezzo" action="<%=response.encodeURL("AdminControl")%>" method="post">
			<input type="hidden" name="azione" value="aggiornaPrezzo">
			<label for="id">ID:</label><br>
  			<input type="text" id="id" name="id" required autofocus><br>
  			<label for="prezzo">Prezzo:</label><br>
 		  	<input type="text" id="prezzo" name="prezzo" value ="" ><br>
  			 <input type="submit" value="Aggiorna" id="submit">
  			</form>
  			<div class="aggiornah4"><h4 >Aggiorna quantit&agrave;:</h4></div>
 		  <form name ="formUpdateQuantita" action="<%=response.encodeURL("AdminControl")%>" method="post">
			<input type="hidden" name="azione" value="aggiornaQuantita">
			<label for="id">ID:</label><br>
  			<input type="text" id="id" name="id" required autofocus><br>
  			<label for="quantita">Quantit&agrave;:</label><br>
 		  	<input type="text" id="quantita" name="quantita" value ="" ><br>
  			 <input type="submit" value="Aggiorna" id="submit">
  			</form>
  			<div class="aggiornah4" ><h4 >Aggiorna categoria:</h4></div>
  			  <form name ="formUpdateCategoria" action="<%=response.encodeURL("AdminControl")%>" method="post">
			<input type="hidden" name="azione" value="aggiornaCategoria">
			<label for="id">ID:</label><br>
  			<input type="text" id="id" name="id" required autofocus><br>
  			<label for="categoria">Categoria:</label><br>
 		  	<input type="text" id="categoria" name="categoria" value ="" ><br>
  			 <input type="submit" value="Aggiorna" id="submit">
  			</form>
  			<div class="aggiornah4" ><h4 >Aggiorna foto:</h4></div>
  			  <form name ="formUpdateFoto"  enctype="multipart/form-data"  action="<%=response.encodeURL("AdminControl")%>" method="post">
			<input type="hidden" name="azione" value="aggiornaFoto">
			<label for="id">ID:</label><br>
  			<input type="text" id="id" name="id" required autofocus><br>
  			<label for="foto">Foto Prodotto:</label><br>
 		  	<input type="file" id="foto" name="foto" value ="" ><br>
  			 <input type="submit" value="Aggiorna" id="submit">
  			</form>
  			
 		  	
	</div>
	</div>

<script>
var k=0;
function mostraNascondiUpdate() {
	if(k==0){
		document.getElementById("formUpdate").style.display="block";
		k=1;
	}else{
		document.getElementById("formUpdate").style.display="none";
		k=0;
		}
	}
	

</script>
<br>

<div id="remove">
	<h3 onClick="javascript:mostraNascondiRemove()" onmouseover="" style="cursor: pointer;">RIMUOVI PRODOTTO <i class="fas fa-angle-down"></i></h3>	
	<div id="formRemove">
	<form name ="formRemove" action="<%=response.encodeURL("AdminControl")%>" method="post">
			<input type="hidden" name="azione" value="rimuovi">
			<label for="id">ID:</label><br>
  			<input type="text" id="id" name="id" required autofocus><br>
			<input type="submit" value="Rimuovi" id="submit">
		</form>
	</div>
</div>
<script>
var i=0;
function mostraNascondiRemove() {
	if(i==0){
		document.getElementById("formRemove").style.display="block";
		i=1;
	}else{
		document.getElementById("formRemove").style.display="none";
		i=0;
		}
	}
	
	
</script>

<jsp:include page="../Footer.jsp"></jsp:include>

</body>
</html>