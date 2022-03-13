package it.unisa.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.model.UtenteBean;
import it.unisa.model.UtenteModel;

/**
 * Servlet implementation class LoginControl
 */
@WebServlet("/LoginControl")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
     static UtenteModel model= new UtenteModel();  

    public LoginControl() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*creiamo la sessione e un nuovo utente */
		UtenteBean utente=new UtenteBean();
		HttpSession session= request.getSession();
		
		/*prendiamo dal form di login la mail e la password*/
		String mailUser= request.getParameter("username");
		String passUser=request.getParameter("password");
		
		/*nella sessione definiamo a false i controlli per distinguere amministratore e utente*/
		session.setAttribute("isLogged", false);
		session.setAttribute("isAdmin", false);
		
		try {
			//doValidate e' un metodo di userModel per fare un checking della mail e password passati dalla request con quelli del db
			if(model.doValidate(mailUser, passUser)==true) {
				
				/*estriamo dal db l'utente identificato dalla sua chiave mail*/
				utente=model.doRetriveByKey(mailUser);
					
					/*controlliamo se sia amministratore per settare l'eventuale check nella sessione*/
					if(utente.getTipo().equals("amministratore")) {
						session.removeAttribute("isAdmin");
						session.setAttribute("isAdmin", true);
					}
					
					/*definiamo nella sessione un parametro che ci definisce il fatto che l'utente sia loggato
					 * si rilevera utile nel mostrare la finestra di login con javascrit*/
					session.removeAttribute("isLogged");
					session.setAttribute("isLogged", true);
					
					//si rivela utile per mostrare il nome dell'utente loggato nelle sue pagine
					session.setAttribute("nome", utente.getNome());
					
					session.removeAttribute("utente");
					session.setAttribute("utente", utente);
					
					/*rimandiamo il controllo*/
					RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/AreaPersonale.jsp");
					dispatcher.forward(request, response);
				}
				else {
					//se le credenziali non coincidono con quelle nel db si manda una pagina di credenziali errate
					RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/CredenzialiErrate.jsp");
					dispatcher.forward(request, response);
				}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
	}

}
