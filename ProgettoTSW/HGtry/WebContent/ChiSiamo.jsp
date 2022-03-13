<%@ page language="java" contentType="text/html; charset=UTF-8"
   %>
<!DOCTYPE html>
<html>
<head>
<link href="Style.css" type="text/css" rel="stylesheet">
<meta charset="UTF-8">
<title>CHI SIAMO</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="Navbar.jsp"></jsp:include>
<div id="contenitoreChiSiamo">
	<div id="biografia">
		<h2>COS'E' HOGWARTSHOP?</h2>
		<p>E' un sito creato per soddisfare i bisogni del vasto pubblico della saga Harry Potter.<br> La saga della nota scrittrice J.K.Rowling ha infatti ottenuto, nell’ultimo ventennio, un enorme successo con i suoi libri per ragazzi, ottenendo milioni di incassi in libreria e nelle sale, raggiungendo grande notorietà anche nel pubblico adulto.<br>
			Il nostro store ha quindi lo scopo di offrire la vendita online di una vasta gamma di prodotti a tema, dal classico abbigliameto a tema Hogwarts, alla vendita di gadget tipici dei romanzi, ma anche di approfondire la conoscenza del fantastico mondo di Harry Potter con varie curoiostà legate ai prodotti venduti.
		</p>
	</div>
	
	
	<div id="idee">
		<h2>NON SAI COSA REGALARE?</h2>
		<p>Tantissime persone sono fan di Harry Potter… 
		Qui da HogwartShop offriamo ai nostri clienti una vasta scelta di articoli Fandom per accontentare anche i “palati” più difficili!</p>
	</div>
	<div id="supporto">
		<h2>SUPPORTO CLIENTI</h2>
		<p>Se hai dubbi o domande da fare ai nostri esperti consulta la pagina <a href="faq.jsp">FAQ</a> e non esitare a
		 contattarci sulle nostre pagine social e ad iscriverti alla nostra newsletter per restare sempre aggiornato. </p>
	</div>
	<div id="sicurezza">
		<h2>CHECKOUT SICURO</h2>
		<p>Con il protocollo di sicurezza, 
		tutti i tuoi acquisti saranno sicuri al 100% per tutti i metodi di pagamento proposti.</p>
	</div>
	
	
	<div id="subContenitoreChiSiamo">
		<div id="spedizioni">
			<h4>SPEDIZIONE GRATUITA</h4>
			<p>Per tutti gli ordini superiori a € 50</p>	
		</div>
		<div id="recesso">
			<h4>DIRITTO DI RECESSO</h4>
			<p>Entro 14 giorni dal ricevimento della merce</p>
		</div>
		<div id="rapidita">
			<h4>SPEDIZIONE RAPIDE IN 24/72H</h4>
			<p>Tutti gli ordini effettuati dal Lunedì al Venerdì entro le ore 10.00 <br> saranno spediti il giorno stesso</p>
		</div>
		<div id="pagamenti">
			<h4>PAGAMENTI SICURI AL 100%</h4>
			<p>PayPal / MasterCard / Visa / Bonifico / Pagamento alla consegna (+5€)</p>
		</div>
    </div>
</div>
<br><br>
<jsp:include page="Footer.jsp"></jsp:include>

</body>
</html>