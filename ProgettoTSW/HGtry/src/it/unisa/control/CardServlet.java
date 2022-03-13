package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.ProdottoModel;


@WebServlet("/CardServlet")
public class CardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ProdottoModel prodotto = new ProdottoModel();
   
    public CardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//creiamo l attributo products nella request che conterrà la lista dei prodotti nel db
			request.removeAttribute("prodotto");
			request.setAttribute("prodotto", prodotto.doRetriveAll(""));
			
			String nomeProdotto = request.getParameter("nomeProdotto");
			request.removeAttribute("nome");
			request.setAttribute("nome",nomeProdotto);
			//______________________________
		} catch(SQLException e) {
			System.out.println("Error: "+ e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/CardProdotto.jsp");
		dispatcher.forward(request, response);
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
