package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import it.unisa.model.OrdineBean;
import it.unisa.model.OrdineModel;
import it.unisa.model.ProdottoModel;

/**
 * Servlet implementation class OrdiniControl
 */
@WebServlet("/OrdiniControl")
public class OrdiniControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
      static OrdineModel model= new OrdineModel(); 
      
      static ProdottoModel pmodel= new ProdottoModel();  
    public OrdiniControl() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//nella request poniamo un attributo che contiene la lista degli ordini caricat dal database dell'utente
			request.removeAttribute("ordini");
			request.setAttribute("ordini", model.doRetriveAll(""));
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/AreaPersonale.jsp");
		dispatcher.forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
