<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, it.unisa.model.*,  it.unisa.control.*"%>

<!DOCTYPE html>

<%  //dalla sessione carichiamo l'utente loggato, se cosi non fosse lo mandiamo alla pagina do registrazione

	UtenteBean utente= (UtenteBean) session.getAttribute("utente");
	if(utente==null){
		response.sendRedirect("./Registrazione.jsp");
		return;
		}
	
	//Carichiamo una collezione di ordini dell'utente loggato utilizzando OrdiniMODEL
	
	Collection<?> orders = (Collection<?>) request.getAttribute("ordini");
	   
  	String error = (String)request.getAttribute("error");
  	
  	if(orders == null && error == null) {
  		//inizialimente orders sarà vuota allora mandiamo il controllo alla servlet OrdiniControl per riempirla
  		response.sendRedirect(response.encodeRedirectURL("./OrdiniControl"));
  		return;
  	} 
	  	   
%>

<html>
<head>
<meta charset="ISO-8859-1">
<link href="Style.css" type="text/css" rel="stylesheet">
<title>Account</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="Navbar.jsp"></jsp:include>



<div id="AreaPersonaleContenitore">
	<h2 id="benvenuto"><%=utente.getNome().toUpperCase()%> benvenuto nella tua area personale </h2>
	<br>
	<div id="DatiUtente">
		<h2>I TUOI DATI</h2>
		<h4><%=utente.getEmail()%></h4>
		<br>
		<table class="tabellaUtente">
			<tr>
				<th>COGNOME</th>
				<th>NOME</th>
				<th>DATA NASCITA</th>
				<th>LUOGO NASCITA</th>
				<th>INDIRIZZO</th>
				<th>TELEFONO</th>
				</tr>
				<tr>
					<td><%=utente.getCognome()%></td>
					<td><%=utente.getNome()%></td>
					<td><%=utente.getD_nascita()%></td>
					<td><%=utente.getL_nascita()%></td>
					<td><%=utente.getIndirizzo()%></td>
					<td><%=utente.getTelefono()%></td>
				</tr>
			
		</table>
		<br>
	</div>
	<br>
	
	<!-- carichiamo dalla sessione l'attributo 'isLogged' e 'isAdmin' per accertarci che l'utente sia loggato e non sia un amministratore -->
	
	<%if(((Boolean) session.getAttribute("isLogged"))&&(((Boolean) session.getAttribute("isAdmin"))==false)){%>
		
		<h3>I MIEI ORDINI</h3>
		<a href="<%=response.encodeURL("OrdiniControl?")%>"></a>
		
		<!-- iteriamo sulla lista degli ordini ottenuta nella servlet  -->
		
		<% if(orders != null && orders.size() > 0) {
			int c=0;
			Iterator<?> it  = orders.iterator();
			while(it.hasNext()) {
				c++;
				OrdineBean bean = (OrdineBean)it.next();
				if(bean.getEmail().equals(utente.getEmail())){
		%>
		   
	<!-- All interno dell'area utente dinamicamente creiamo un div VisualizzatoreOrdini con i dati di tutti gli ordini fatti dall'utente -->
		<hr>
		<div id="VisualizzatoreOrdine<%=c%>" style="width:90%; border-radius: 8px; margin: 0 auto; text-align: center; padding-bottom: 25px;padding-top: 25px;">
			<table id="tabellaOrdini">
			<tr>
				<th>ID</th>
				<th>CARTA</th>
				<th>IMPORTO</th>
				<th>DATA</th>
				<th>DETTAGLI</th>
			</tr>
				<tr>
					<td><%=bean.getId_ordine()%></td>
					<td><%=bean.getCod_carta() %></td>
					<td><%=bean.getImporto()%>$</td>
					<td><%=bean.getData_ordine()%></td>
					
					<!-- AGGIUNGIAMO UN BOTTONE COLLEAGO A UNA FUNZIONE JS CHE UTILIZZA AJAX PER MOSTRARE I PRODOTTI RELATIVI ALL'ORDINE -->
					
					<td><button type="button" onclick="mostraOrdini('<%=utente.getEmail()%>','<%=bean.getId_ordine()%>','<%=c%>');">visualizza</button></td>
					
			
				</tr>
				
			</table>
			<div id="dettagli<%=c%>" class="dettagli"></div>
		      <% } %>
				
			</div>
			<br>
		<%} } %>
		
		
<% } %>
	
	<!-- controlliamo dalla sessione se l'utente è amministratore in tal caso SOLO a tali utenti verrà mostrato
	     il bottone per l area amministratore -->
	     
	<%if((Boolean) session.getAttribute("isAdmin")) {%>
		<hr><br>
		<form action="./AdminControl" method="post" id="formPannello">
			<input type="submit" value="PANNELLO AMMINISTRATORE">
		</form>
		
	<%} %>
	
</div>





<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>