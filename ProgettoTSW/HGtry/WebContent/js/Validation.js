/**
 * 
 */
/*questa funzione verifica se la password inserita soddisfa le condizioni prestabilite */
function passValidation(){
	var password=document.formRegistrazione.password; 
	var pattern=/^(?=.*[A-Z])(?=.*\d)[a-zA-Z\d@$!%*?&]{8,20}$/;
	
/*verifico se esiste il <p> con id par. se esiste lo elimino perchè voglio che sia visibile solo se non sono soddisfatte
 le condizioni */
	if(document.getElementById('parPassword')){
		var div=document.getElementById("passProblems");
		div.removeChild(div.childNodes[0]);
		document.getElementById("password").style.color="black";
	
		
	}
/*se la password non corrisponde alle caratteristiche date allora creo un elemento <p> all'interno dell'
 * HTML e informo l'utente dell'errore.
 */
	
	if(!password.value.match(pattern)){
		var p=document.createElement("p");
		p.setAttribute("id", "parPassword");
		p.innerHTML="La password deve contenere:<br>"+"- Da 8 a 20 caratteri;<br> "+
		"- Una lettera maiuscola(A-Z);<br>- Un numero(0-9);<br>";
		document.getElementById("passProblems").appendChild(p);
		document.getElementById("password").style.color="red";
	
	

	}
}


function mailValidation(){
	
	var uemail=document.formRegistrazione.email; 
	console.log(uemail);
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



function phoneValidation(){
	var telefono=document.formRegistrazione.telefono; 
	var pattern=/^\d{10}$/;
	

	if(document.getElementById('parTelefono')){
		var div=document.getElementById("telProblems");
		div.removeChild(div.childNodes[0]);
		document.getElementById("telefono").style.color="black";
		
	}

	
	if(!telefono.value.match(pattern)){
		var p=document.createElement("p");
		p.setAttribute("id", "parTelefono");
		p.innerHTML="Numero non valido";
		document.getElementById("telProblems").appendChild(p);
		document.getElementById("telefono").style.color="red";
	
	

	}
}

function dataValidation(){
	var dnascita=document.formRegistrazione.dnascita; 
	var pattern=/^\d{4}-\d\d-\d\d$/;
	

	if(document.getElementById('parData')){
		var div=document.getElementById("dataProblems");
		div.removeChild(div.childNodes[0]);
		document.getElementById("dnascita").style.color="black";
		
	}

	
	if(!dnascita.value.match(pattern)){
		var p=document.createElement("p");
		p.setAttribute("id", "parData");
		p.innerHTML="Formato data non valido";
		document.getElementById("dataProblems").appendChild(p);
		document.getElementById("dnascita").style.color="red";

	}
	

}

