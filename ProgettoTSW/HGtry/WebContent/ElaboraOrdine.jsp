<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, it.unisa.model.*,  it.unisa.control.* "
    %>
     <% 
     
     	String error = (String)request.getAttribute("error");
 	
 		UtenteBean utente= (UtenteBean)request.getSession().getAttribute("utente"); 
 		Carrello<ProdottoBean> carrello =(Carrello<ProdottoBean>)request.getSession().getAttribute("carrello");
 		if(carrello == null && error == null) {
 			//inizialment la lista sarà vuota perciò la servlet gestira il suo riempimento
 			response.sendRedirect(response.encodeRedirectURL("./CarrelloControl"));
 			return;
 		}
 		if(utente == null && error == null) {
 			//inizialment la lista sarà vuota perciò la servlet gestira il suo riempimento
 			response.sendRedirect(response.encodeRedirectURL("./OrdinaServlet"));
 			return;
 		}
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="ISO-8859-1">
<title>CheckOut</title>

<link href="Style.css?ts=<?=time()?>&quot" type="text/css" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="Navbar.jsp"></jsp:include>
	<div id="riepilogoOrdine">
	<h3>Riepilogo Ordine</h3>
	<div id=RiepilogoTable>
			<table>
			<tr>
			<th></th>
    		<th>Prodotto </th>
    		<th>Prezzo</th>
    		<th>Quantità</th>
  			</tr>
	<%List<ProdottoBean> listaProdotti = carrello.getListaProdotti();
	Iterator<ProdottoBean> it= listaProdotti.iterator();
	float totale=0;
	while(it.hasNext()){
			ProdottoBean bean = (ProdottoBean)it.next();
			if(bean.getQuantityCart() >1){
				totale += (bean.getPrezzo()*bean.getQuantityCart());
			}
			else{
			totale+=bean.getPrezzo();
			}%>
			
			
  			<tr>
			<td><img src="./getPicture?id=<%=bean.getId_prodotto()%>" width="100"></td>
    		<td><%=bean.getNome() %></td>
    		<td>$<%=bean.getPrezzo() %> </td>
    		<td><%=bean.getQuantityCart() %></td>
  			</tr>

		<%}%>
		</table>
		</div>
		<p id="totale">Totale: $ <%=totale %></p>
		</div>
		
		<div id="formOrdine">
			<form name ="formIndirizzo" action="<%=response.encodeURL("OrdinaServlet")%>" method="POST">
				<div id="IndririzzoCheckOut"><h3>INDIRIZZO CONSEGNA </h3></div>
				<p class="pOrdine"><input type="radio" id="indirizzo1" onclick="enableTXT()" name ="radio" value ="indirizzo1" checked>
				<%= utente.getNome().toUpperCase() %>
				<%= utente.getCognome().toUpperCase() %>, <%= utente.getIndirizzo().toUpperCase() %></p><br>
				<input type="hidden" name="totale" value="<%= totale%>">
				<p class="pOrdine" style="cursor: pointer;" onClick="javascript:setFormIndVisible()"><input type="radio" onclick="enableTXT()" id="indirizzo2" name ="radio" value ="indirizzo2">
				Aggiungi nuovo indirizzo<i class="fas fa-angle-down"></i></p>
				<div id= "nuovoIndirizzo">
				<label for="nome">Nome e Cognome:</label><br>
 		  		<input type="text" id="nome" name="nome"  placeholder="Nome Cognome"><br>
				<label for="indirizzo">Indirizzo:</label><br>
 		  		<input type="text" id="indirizzo" name="indirizzo" placeholder="Via, Civico, Città(Provincia), CAP, Nazione"><br>
				</div>
				<div id="PagamentoCheckOut"><h3>MODALITÀ DI PAGAMENTO</h3></div>
				<label for="intestatario">Intestatario:</label><br>
 		  		<input type="text" id="intestatario" name="intestatario" required><br>
 		  		<label for="numeroCarta">Numero Carta:</label><br>
 		  		<input type="text" id="numeroCarta" name="numeroCarta" required><br>
 		  		<label for="cvvCarta">CVV:</label><br>
 		  		<input type="text" id="cvvCarta" name="cvvCarta" placeholder="XXX"required><br>
 		  		<label for="scadenzaCarta">Scadenza Carta:</label><br>
 		  		<input type="text" id="scadenzaCarta" name="scadenzaCarta" placeholder="MM-YYYY" required><br>
 		  		<input type="submit" value="Acquista" id="submit">
			</form>
		</div>
		<script>
		// rende visibile il form del nuovo indirizzo in ElaboraOrdine
		var l=0;
		function setFormIndVisible(){
			
			if(l==0){
				document.getElementById("nuovoIndirizzo").style.display="block";
				l=1;
			}else{
				document.getElementById("nuovoIndirizzo").style.display="none";
				l=0;
				}
		}
		
		// rende disabilitato o non disabilitato il campo text di indirizzo in ElaboraOrdine e
		//cambia il valore di radio button a seconda delle scelte fatte
		function enableTXT(){
			if(document.getElementById("indirizzo2").checked == true)
		    {
				document.getElementById("indirizzo1").value="0";
				document.getElementById("indirizzo2").value="indirizzo2";
		        document.getElementById("nome").disabled = false;
		        document.getElementById("indirizzo").disabled = false;
		        
		     }
			else if((document.getElementById("indirizzo1").checked == true)){
				document.getElementById("indirizzo1").value="indirizzo1";
				document.getElementById("indirizzo2").value="0";
				document.getElementById("nome").disabled = true;
		        document.getElementById("indirizzo").disabled = true;
			}
		}
		
		</script>
<jsp:include page="Footer.jsp"></jsp:include>	
</body>
</html>