package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.UtenteBean;
import it.unisa.model.UtenteModel;

/**
 * Servlet implementation class NewAccount
 */
@WebServlet("/NewAccount")
public class NewAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
      UtenteModel model=new UtenteModel();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				
				
				
				 
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String indirizzo = request.getParameter("indirizzo");
			String lnascita=request.getParameter("lnascita");
			String dnascita=request.getParameter("dnascita");
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			String telefono=request.getParameter("telefono");
			UtenteBean utente= new UtenteBean();
			utente.setNome(nome);
			utente.setCognome(cognome);
			utente.setIndirizzo(indirizzo);
			utente.setD_nascita(dnascita);
			utente.setL_nascita(lnascita);
			utente.setEmail(email);
			utente.setTelefono(telefono);
			utente.setPass(password);
			utente.setTipo("utente");
			/*controllo se l'email � gi� presente. Se � gi� presente nel db imposto l'attributo errore != null.
			 * con jsp poi verifico nella pagina Registrazione.jsp se il valore � nullo. Quindi se 
			 * errore != null stampo "L'indirizzo email � gi� associato ad un'altro account"
			 */
			Collection<?> utenti = model.doRetriveAll("");
			if(utenti != null && utenti.size() > 0) {
				Iterator<?> it  = utenti.iterator();
				while(it.hasNext()) {
					UtenteBean bean = (UtenteBean)it.next();	
					if(bean.getEmail().equals(email)) {
						request.setAttribute("errore", "Utente già esistente");
						request.getRequestDispatcher("Registrazione.jsp").forward(request, response);
						return;
					}
				}
			}
					model.doSave(utente);
			request.setAttribute("message", "Utente "+ utente.getNome()+" added");
		}catch(SQLException | NumberFormatException e) {
		System.out.println("Error: "+ e.getMessage());
		request.setAttribute("error", e.getMessage());			
	}
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/RegEffettuata.jsp");
			dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
