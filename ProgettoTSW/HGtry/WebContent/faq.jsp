<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, it.unisa.model.*,  it.unisa.control.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="ISO-8859-1">
<title>FAQ</title>
<link href="Style.css" type="text/css" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="Navbar.jsp"></jsp:include>
	<div id="introFAQ" >
	<h1>FREQUENTLY ASKED QUESTIONS</h1>
	<h4>Hai qualche domanda riguardante il nostro shop?</h4>
	 <p>Siamo felici di aiutarti ma possiamo rispondere solo a domande riguardanti il nostro Shop.<br>
	Se qui non trovi una risposta ad una specifica domanda, non esitare a contattarci! La nostra mail è: <span>hogwartshop@gmail.com</span> 
	<br>Cercheremo di risponderti il prima possibile!</p>
	</div>
	
	
	<div id="listFAQ">
		<h3>ORDINE</h3>
		<p class="domanda">Q: Posso cancellare o cambiare gli elementi nel mio ordine?</p>
		<p class="risposta">R: Dal momento che ci impegniamo a spedire i tuoi ordini il più rapidamente possibile, possiamo
		 solo annullare o modificare gli ordini in stato di arretrato o pre-ordine e che non sono stati preparati dal 
		 nostro magazzino per la spedizione.
		  Se cambi idea su un ordine e non siamo in grado di annullarlo per te, puoi rifiutare il pacco al tentativo
		   di consegna o restituire il pacco al nostro magazzino. 
		   Vi preghiamo di contattarci per richiedere la cancellazione di un ordine arretrato o pre-ordine.</p>
			
		   <p class="domanda">Q:Posso cambiare o restituire il mio acquisto?</p>
		   <p class="risposta">R:Contattaci via mail per avere tutte le info a riguardo.</p>
		   
		   <p class="domanda">D: Uno degli articoli che ho ordinato è in backorder,
		    ma perché il resto dei miei articoli non è stato spedito?</p>
		   <p class="risposta">R:Gli ordini che includono un articolo in backorder vengono trattenuti
		    in attesa dell'arrivo dell'articolo in backorder.
		   Gli ordini non vengono suddivisi per la spedizione a causa di maggiori tasse, dazi, commissioni e tariffe di spedizione.</p>
		   <br>
		<h3>SPEDIZIONE</h3>
		 <p class="domanda">Q:In quanto tempo arriva il mio ordine?</p>
		 <p class="risposta">R: La maggior parte degli ordini in magazzino viene spedita entro tre giorni lavorativi. 
			Riceverai un'email con un numero di tracciamento la mattina dopo la spedizione del pacco.</p>
		 <p class="domanda">Q:Cosa si intende per 'giorno lavorativo'?</p>
		 <p class="risposta">R:I giorni lavorativi vanno da lunedì a venerdì. Gli ordini non vengono spediti il sabato,la 
		 domenica e i giorni festivi (in Italia)</p>
		 <p class="domanda">Q:Perché la mia consegna richiede una firma?</p>
		 <p class="risposta">R:Per ordini superiori a $ 250 potrebbe essere necessaria una firma per la consegna.</p>
		 
		 <br>
		 <h3>ALTRO</h3>
		 <p class="domanda">Q:Posso avere un catalogo stampato di tutti gli articoli nel negozio?</p>
		 <p class="risposta">R:Sfortunatamente non abbiamo un catalogo cartaceo.Nel nostro shop online sono presenti
		  tutti gli articoli interessanti che abbiamo in vendita.</p>
		 <p class="domanda">Q:Ho acquistato la mia merce da un altro negozio o rivenditore online, 
		 mi potete aiutare a restituire il mio articolo?</p>
		 <p class="risposta">R:Purtroppo anche noi siamo rivenditori e possiamo gestire solo articoli solo del nostro negozio. 
		 Contatta direttamente il negozio da cui hai acquistato e dovrebbero essere in grado di aiutarti.</p>
		   
	</div>
		<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>