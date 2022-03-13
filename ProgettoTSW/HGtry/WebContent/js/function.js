function showLoginFormJquery(isLogged){
	var log= $("#notLogged");
	console.log(log);
	var container=$("#miniLog");
	var logged= $("#logged"); //$() equivale a getElementById
	
	if(!isLogged)
	{
		//classlist.contains serve a stabilire se all interno dell elemento continer ce la clausola active
		//se cosi non fosse settiamo il tipo di display dell'elelemento
	
		if(!container.hasClass("active"))
			{
				//L'elemento notlogged viene visualizzato come elemento a livello di blocco
				log.css("display","block");
				container.addClass("active");
				
				console.log(container.hasClass("active"));
			}
		else{
			log.css("display","none");//l elemento viene nascosto
			container.removeClass("active");
			}
	}else{
			if(!container.hasClass("active"))//l elemento logged viene visualizzato o nascosto a seconda del fatto che sia o meno attivo
			{
				logged.css("display","block");
				container.addClass("active");
					
			}else{
				logged.css("display","none");
				container.removeClass("active");
			}
	}
}

function showLittleLoginFormJQuery(isLogged) //gestisce il minilogin in header rendendolo visibile al click e nascondendolo al secondo click
{
	var log= $("#notLogged2");
	var container=$("#miniLog2");
	var logged=$("#logged2");
	console.log("sono nella piccola fiestra");
	if(!isLogged)
	{
		//classlist.contains serve a stabilire se all interno dell elemento continer ce la clausola active
		//se cosi non fosse settiamo il tipo di display dell'elelemento
		if(!container.hasClass("active"))
			{
				log.css("display","block");//L'elemento notlogged viene visualizzato come elemento a livello di blocco
				container.addClass("active");
			}
		else{
			log.css("display","none");//l elemento viene nascosto
			container.removeClass("active");
			}
	}else{
			if(!container.hasClass("active"))//l elemento logged viene visualizzato o nascosto a seconda del fatto che sia o meno attivo
			{
				logged.css("display","block");
				container.addClass("active");
					
			}else{
				logged.css("display","none");
				container.remove("active");
			}
	}
	
}

/* VERSIONE JAVASCRIPT
function showLoginForm(isLogged) //gestisce il minilogin in header rendendolo visibile al click e nascondendolo al secondo click
		{
			var log= document.getElementById("notLogged");
			var container=document.getElementById("miniLog");
			var logged= document.getElementById("logged");
			
			if(!isLogged)
			{
				//classlist.contains serve a stabilire se all interno dell elemento continer ce la clausola active
				//se cosi non fosse settiamo il tipo di display dell'elelemento
				if(!container.classList.contains("active"))
					{
						log.style.display="block";//L'elemento notlogged viene visualizzato come elemento a livello di blocco
						container.classList.add("active");
					}
				else{
					log.style.display="none";//l elemento viene nascosto
					container.classList.remove("active");
					}
			}else{
					if(!container.classList.contains("active"))//l elemento logged viene visualizzato o nascosto a seconda del fatto che sia o meno attivo
					{
						logged.style.display="block";
						container.classList.add("active");
							
					}else{
						logged.style.display="none";
						container.classList.remove("active");
					}
			}
			
		}
*/
 function checkLogin(){
	 var uemail=document.getElementById("logUser");
	 var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	 if(uemail.value.match(mailformat))
	 {
		 return true;
	 }
	 else
	 {
		 alert("FORMATO E-MAIL ERRATO!");
		 event.preventDefault();
		 uemail.focus();
		 return false;
	 } 
	 
 }
 
 function checkLogin(){
	 var uemail=document.getElementById("loginCart");
	 var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	 if(uemail.value.match(mailformat))
	 {
		 return true;
	 }
	 else
	 {
		 alert("FORMATO E-MAIL ERRATO!");
		 event.preventDefault();
		 uemail.focus();
		 return false;
	 } 
	 
 }
/* VERSIONE JAVASCRIPT
 function showLittleLoginForm(isLogged) //gestisce il minilogin in header rendendolo visibile al click e nascondendolo al secondo click
	{
		var log= document.getElementById("notLogged2");
		var container=document.getElementById("miniLog2");
		var logged= document.getElementById("logged2");
		console.log("sono nella piccola fiestra");
		if(!isLogged)
		{
			//classlist.contains serve a stabilire se all interno dell elemento continer ce la clausola active
			//se cosi non fosse settiamo il tipo di display dell'elelemento
			if(!container.classList.contains("active"))
				{
					log.style.display="block";//L'elemento notlogged viene visualizzato come elemento a livello di blocco
					container.classList.add("active");
				}
			else{
				log.style.display="none";//l elemento viene nascosto
				container.classList.remove("active");
				}
		}else{
				if(!container.classList.contains("active"))//l elemento logged viene visualizzato o nascosto a seconda del fatto che sia o meno attivo
				{
					logged.style.display="block";
					container.classList.add("active");
						
				}else{
					logged.style.display="none";
					container.classList.remove("active");
				}
		}
		
	}

*/
 function checkLittleLogin(){
	 var uemail=document.getElementById("logUser2");
	 var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	 if(uemail.value.match(mailformat))
	 {
		 return true;
	 }
	 else
	 {
		 alert("FORMATO E-MAIL ERRATO!");
		 event.preventDefault();
		 uemail.focus();
		 return false;
	 } 
	 
 }
 
 
 //PASSIAMO ALLA FUNZIONE L'UTENTE, L'ORDINE E C CHE RAPPRESENTA IL NUMERO DEL DIV (visualizzatore ordine) DOVE VISUALIZZARE LE INFO
 //PER USARE AJAX COMBINATA CON JSON ABBIAMO IL .RAR NELLA CARTELLA LIB E NELLA CARTELLA JS IL FILE JQUERY CON L'INIZIALIZZAZIONE
 //GLI SCRIPT JQUERY E JS SONO NEL FOOTER PER PERMETTERE ALLA PAGINA DI CARICARSI INTERAMENTE
 //LA FUNZIONE IN 'URL' CONTIENE LA SERVLET CHE INTERAGISCE COL DB USANDO I DATI('DATA')PASSATI E IL METODO (POST)
 //IN CASO DI SUCCESSO NELLA RESPONSE AVREMO UN OGGETTO JSON SUL QUALE OPERARE CON LE FUNZIONALITA DI JAVSCRIP
 //UTILIZZIAMO LA SELEZIONE VELOCE OFFERTA DA JQUERY PER SELEZIONARE IL DIV IN QUESTIONE E VISUALIZZARE IN ESSO IL PRODOTTO RELATIVO
 function mostraOrdini(utente,IdOrdine,c){
 	$.ajax({
 		url: "./MostraOrdiniControl",
 		method: "POST",
 		data:{
 			cliente: utente,
 			idOr:IdOrdine,
 		},
 		success:function(response){	
 			var div="#VisualizzatoreOrdine"+c;
 		
 			console.log(response);
 		
 			response.map(el=>{$("#dettagli"+c).append("<h3>"+el.nome+"</h3>"+"<br>"+el.descrizione);});
 			
 			$("#dettagli"+c).css("width","50%");
 			$("#dettagli"+c).css("text-align","center");
 			$("#dettagli"+c).css("margin","0 auto");
 			
 			if(!$("#dettagli"+c).hasClass("active"))
				{
 				$("#dettagli"+c).show();
 				
					$("#dettagli"+c).addClass("active");
				}
				else{
					$("#dettagli"+c).empty();
					$("#dettagli"+c).removeClass("active");
				}
 			
 		},
 		error:function(){
 			alert("ERRORE CHIAMATA ASINCRONA");
 		}
 	});	
 }

 function fortunato(){
	 	$.ajax({
	 		url: "./Fortuna",
	 		method: "POST",
	 		
	 		success:function(response){
	 			var nome=response.nome;
	 			var prezzo=response.prezzo;
	 			console.log(nome);
	 			console.log(response);
	 			
	 			var link='"./getPicture?id='+response.id_prodotto+'"';
	 			var link2='"./CardServlet?nomeProdotto='+response.nome+'"';
	 			
	 			var link3='"./CarrelloControl?action=aggiungi&id='+response.id_prodotto+'"';
	 				
	 				
	 			console.log(link);
	 			console.log(link2);
	 		
	 			$("#fortunatoDescrizione").css("border-style","solid");
	 			$("#fortunatoDescrizione").css("border-color","#f3f3f3");
	 			$("#fortunatoDescrizione").css("text-align","center");
	 			$("#fortunatoDescrizione").css("height","300px");
	 			$("#fortunatoDescrizione").css("margin","0 auto");
	 			$("#fortunatoDescrizione").css("position","relative");
	 			$("#fortunatoDescrizione").css("width","75%");
	 			$("#fortunatoDescrizione").css("border-radius","10%");
	 			$("#fortunatoDescrizione").css("box-shadow","5px 5px 5px gray");
	 			$("#fortunatoDescrizione img").css("margin-left","20.5%");
	 			$("#fortunatoDescrizione img").css("margin-top","10%");
	 			$("#fortunatoDescrizione img").css("width","60%");
	 			$("#fortunatoDescrizione img").css("height","60%");
	 			$("#fortunatoDescrizione img").css("opacity","0.7");
	 	
	
	 			if(!$("#fortunatoDescrizione").hasClass("active"))
				{
	 				$("#fortunatoDescrizione").css("display","block");
	 				
	 				$("#fortunatoDescrizione").append("<br><img src="+link+" width='150'"+" class='surpriseImg'>");
	 				$("#fortunatoDescrizione").append("<h3>"+nome+"</h3>"+"<p>"+prezzo+"$</p>");
		 			$("#fortunatoDescrizione").append("<br><a href="+link3+"><i class='fa fa-shopping-cart' aria-hidden='true'</i></a>");
	 				
		 			
					$("#fortunatoDescrizione").addClass("active");
				}
				else{
					$("#fortunatoDescrizione").empty();
					$("#fortunatoDescrizione").css("display","none");
					$("#fortunatoDescrizione").removeClass("active");
				}
	 			
	 				
	 		},
	 		error:function(){
	 			alert("ERRORE CHIAMATA ASINCRONA");
	 		}
	 	});	
	 }

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 // rende visibile il form del nuovo indirizzo in ElaboraOrdine

	function setFormIndVisible(){
		var i=0;
		if(i==0){
			document.getElementById("nuovoIndirizzo").style.display="block";
			i=1;
		}else if(i != 0){
			document.getElementById("nuovoIndirizzo").style.display="none";
			i=0;
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
 
 
 