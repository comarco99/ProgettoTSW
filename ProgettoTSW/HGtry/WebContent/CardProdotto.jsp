<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, it.unisa.model.*,  it.unisa.control.*"%>
 
 <% 
 
 	Collection<?> products = (Collection<?>) request.getAttribute("prodotto");
 
	String error = (String)request.getAttribute("error");
	
	if(products == null && error == null) {
		//inizialment la lista sarà vuota perciò la servlet gestira il suo riempimento
		response.sendRedirect(response.encodeRedirectURL("./CardServlet"));
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
<link href="Style.css" type="text/css" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="Navbar.jsp"></jsp:include>
		<%
		int i=0;
	if(products != null && products.size() > 0) {	
			Iterator<?> it  = products.iterator();
			while(it.hasNext()) {
				ProdottoBean bean = (ProdottoBean)it.next();	
				String nomeP=(String)request.getAttribute("nome");
				//operiamo in modulo 4 ogni qualvolta l'indice dell iterazione ci da resto 0 creiamo una nuova riga nella quale caricare le card dei prodotti
				//controllando che la categoria sia quella deisderata
				if(bean.getNome().equals(nomeP)){
					i++;
		%>
	<div id="cardProdotto">
	
		
		<div id="fotoProdotto">
			<img src="./getPicture?id=<%=bean.getId_prodotto()%>">
			</div>
			
			<div id="infoProdotto">
				<h1><%=bean.getNome()%></h1>
			    <h2>$<%=bean.getPrezzo()%></h2>
 					
 					 <div id="addCart">
 					 	<div id="cnt">
 					 	
						<a href="<%=response.encodeURL("CarrelloControl?action=aggiungi&id="+bean.getId_prodotto())%>">AGGIUNGI AL CARRELLO</a>
						</div>
					</div>
	
				<div id="dettagliSpedizione">
				 <p>Questo prodotto può essere spedito in Canada,US,Regno Unito,Australia,Europa.
			    <br>Il prodotto verrà spedito entro 3 giorni lavorativi.</p>
				</div>
				
				<div id="dettagliProdotto">
						<h3>DETTAGLI PRODOTTO</h3>
					
					<div id="descrizione"></div>
				 	<p><%=bean.getDescrizione()%></p>
				</div>
			  
		
			</div>
	<%}}} if(i == 0) {%>
		<h1>IL PRODOTTO NON ESISTE</h1>
	<%}%>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>