<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, it.unisa.model.*,  it.unisa.control.*"%>
 
 <% 
 
 	Collection<?> products = (Collection<?>) request.getAttribute("products");
 
	String error = (String)request.getAttribute("error");
	
	if(products == null && error == null) {
		//inizialment la lista sarà vuota perciò la servlet gestira il suo riempimento
		response.sendRedirect(response.encodeRedirectURL("./ProductControl"));
		return;
	}
 
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="ISO-8859-1">
<title>HogwarShop</title>
<link href="Style.css" type="text/css" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="Navbar.jsp"></jsp:include>

	
	
	
	<div id="container">
	<!-- dalla request prendo l attributo catgeoria ( nella servlet si prendera il paramentro 'tipo' dall'url definito dall scelta della 
	     categroia di prodotto scelto dal menu nell header) questo definirà il tipo di prodotti da visualizzare dinamicamento sulla pagina -->
<!-- <h2>PRODOTTI:<%=request.getAttribute("categoria") %></h2>-->	
	<%
		int i=0;
		if(products != null && products.size() > 0) {
			
			Iterator<?> it  = products.iterator();
			while(it.hasNext()) {
				
				ProdottoBean bean = (ProdottoBean)it.next();	
				String categoria=(String)request.getAttribute("categoria");
				//operiamo in modulo 4 ogni qualvolta l'indice dell iterazione ci da resto 0 creiamo una nuova riga nella quale caricare le card dei prodotti
				//controllando che la categoria sia quella deisderata
				if((i%4!=0) && (bean.getCategoria().equals(categoria))){
	%>						<div class="Singolo"><a href="<%=response.encodeURL("CardServlet?nomeProdotto="+bean.getNome())%>">
						<div class="ListaProdotti">
							<!-- per il caricamento dell'immagine rimandiamo il controllo alla servlet getPicture
							     che carichera la foto con il metodo load definito tra le operazioni crud del prodotto -->
							<img src="./getPicture?id=<%=bean.getId_prodotto()%>" width="100">
							<div class="nomePrezzo">
							<h4><%=bean.getNome()%></h4>
							<p><%=bean.getPrezzo()%>$</p>
							</div>
						</div>
						</a></div>
					<%}else if((i%4==0) && (bean.getCategoria().equals(categoria))){%>
					<div id="RigaProdotto">
						<div class="Singolo"><a href="<%=response.encodeURL("CardServlet?nomeProdotto="+bean.getNome())%>" >
						<div class="ListaProdotti">
							<img src="./getPicture?id=<%=bean.getId_prodotto()%>" width="100">
							<div class="nomePrezzo">
							<h4><%=bean.getNome()%></h4>
							<p><%=bean.getPrezzo()%>$</p>
							</div>
						</div></a>
						</div>
					</div>
				<%}%>
	
			<%i++; }
		} %>
	</div>
	
	<br><br>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>