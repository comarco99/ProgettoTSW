package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.*;

/** 
 * Servlet implementation class ProductControl
 */
@WebServlet("/ProductControl")
public class ProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//creiamo una variabile di tipo prodotto model
	static ProdottoModel model = new ProdottoModel();
	
    public ProductControl() {
    
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//creiamo l attributo products nella request che conterrà la lista dei prodotti nel db
			request.removeAttribute("products");
			request.setAttribute("products", model.doRetriveAll(""));
			
			//creiamo l 'attributo catgeogria con il valore definito nel paramentro tipo
			//il paramentro tipo è definito dalla scelta del menu nell'header
			String tipo = request.getParameter("tipo");
			request.removeAttribute("categoria");
			request.setAttribute("categoria",tipo);
			
			
		} catch(SQLException e) {
			System.out.println("Error: "+ e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Prodotti.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
