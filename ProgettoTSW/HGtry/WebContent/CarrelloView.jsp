<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, it.unisa.model.*,  it.unisa.control.* "
    %>
     <% 
     
     	String error = (String)request.getAttribute("error");
 		Carrello<ProdottoBean> carrello =(Carrello<ProdottoBean>)request.getSession().getAttribute("carrello");
 		if(carrello == null && error == null) {
 			//inizialment la lista sarà vuota perciò la servlet gestira il suo riempimento
 			response.sendRedirect(response.encodeRedirectURL("./CarrelloControl"));
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
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="Navbar.jsp"></jsp:include>
	<div id="cartHeader">
	
	
	
	<h1>IL TUO CARRELLO</h1>
	<%List<ProdottoBean> listaProdotti = carrello.getListaProdotti();
	Iterator<ProdottoBean> it= listaProdotti.iterator();
	float totale=0;
	if(listaProdotti.size() ==0){%>
		<h3> Il tuo carrello è vuoto</h3>
		<p><a href="Home.jsp">Torna allo Shopping</a></p>
		</div>
		<div id="cartButtons">
		

	</div>
	<% }
	if(listaProdotti.size() >0){%>
	
		<div id=cartTable>
		<table>
		<tr>
		<th></th>
    	<th>Prodotto </th>
    	<th>Prezzo</th>
    	<th>Quantità</th>
    	<th>Rimuovi</th>
  		</tr>

	<%
		
		while(it.hasNext()){
			ProdottoBean bean = (ProdottoBean)it.next();
			if(bean.getQuantityCart() >1){
				totale += (bean.getPrezzo()*bean.getQuantityCart());
			}
			else{
			totale+=bean.getPrezzo();
			}
			
	%>
	
	<tr>
		<td><img src="./getPicture?id=<%=bean.getId_prodotto()%>" width="100"></td>
    	<td><%=bean.getNome() %></td>
    	<td>$<%=bean.getPrezzo() %> </td>
    	<td><%=bean.getQuantityCart() %></td>
    	<td><a href="<%=response.encodeURL("CarrelloControl?action=elimina&id="+bean.getId_prodotto())%>"><i class="far fa-trash-alt"></i></a></td>
  		</tr>

		<%}%>
	
		
		</table>
		</div>
		<script>
			
		</script>
		<div id="totalePrezzo">
			<p>Totale $ <%= Math.round(totale * Math.pow( 10,2) )/Math.pow( 10,2) %></p>
		</div>
	<div id="cartButtons">
	<div id="svuota"><a href="<%=response.encodeURL("CarrelloControl?action=pulisci")%>">>SVUOTA CARRELLO</a></div>
	<div id="shopping"><a href="Home.jsp">>HOME</a></div>
	<% UtenteBean utente = (UtenteBean) request.getSession().getAttribute("utente");
	if(utente == null){%>
	<div id="checkout"><a href="<%=response.encodeURL("LoginPage.jsp")%>">>PROCEDI ALL'ORDINE</a></div>
	</div>
	
	<%} else{%>
	<div id="checkout"><a href="<%=response.encodeURL("ElaboraOrdine.jsp")%>">>PROCEDI ALL'ORDINE</a></div>
	</div>
	<%}} %>
			
	
	
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
