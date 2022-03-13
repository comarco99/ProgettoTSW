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
import javax.servlet.http.HttpSession;

import it.unisa.model.Carrello;
import it.unisa.model.ProdottoBean;
import it.unisa.model.ProdottoModel;

/**
 * Servlet implementation class CarrelloControl
 */
@WebServlet("/CarrelloControl")
public class CarrelloControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ProdottoModel model = new ProdottoModel();
    public CarrelloControl() {
        super();
   
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//prendiamo il carrello  dalla sessione(se la sessione non esiste, viene creata).
		HttpSession session= request.getSession();
		Carrello<ProdottoBean> carrello = (Carrello<ProdottoBean>) session.getAttribute("carrello");
		String action = request.getParameter("action");
		//controllo se il carrello esiste e , se non esiste, viene  creato.
		if(carrello == null) {
			carrello=new Carrello<ProdottoBean>();
			session.setAttribute("carrello", carrello);
		}
		//per mostrare l'intero carrello non passo nulla al paramentro action.
		try {
			if(action.equals("aggiungi")) {
				int i=0,j=0;
			String id = request.getParameter("id");
			ProdottoBean bean = new ProdottoBean();
			ProdottoBean prodotto = model.doRetriveByKey(id);
			if(prodotto != null && !prodotto.isEmpty()) {
				Collection<?> carrelloList = carrello.getListaProdotti();
				//controllo se ho almeno un elemento nel carrello
				if(carrelloList != null && carrelloList.size()> 0) {
					Iterator<?> it  = carrelloList.iterator();
				//controllo se l'elemento che voglio aggiungere è già presente
					while(it.hasNext()) {
						 bean = (ProdottoBean)it.next();	
						if(bean.getId_prodotto() == prodotto.getId_prodotto()) {
							//se trovo elemento incremento j
								j++;
								break;
								}
						}
					//se l'elemento era già presente, ne aggiorno la quantità
					if(j >0) {
							prodotto.setQuantityCart(bean.getQuantityCart()+1);	
							carrello.aggiornaQuantita(prodotto);
						
						//se il prodotto non è presente, lo aggiunto settando quantità =1;
					}else {
							prodotto.setQuantityCart(1);
							carrello.aggiungiProdotto(prodotto);

				
					}
				}
				//se invece non ho elementi nel carrello, aggiungo il primo elemento
				else{
					prodotto.setQuantityCart(1);
					carrello.aggiungiProdotto(prodotto);
				}
			}
			
				
				request.setAttribute("message", "Prodotto "+ prodotto.getNome()+" aggiunto al carrello");

				
			  }
			
			
	
			
			else if(action.equals("elimina")) {
					String id = request.getParameter("id");
					ProdottoBean prodotto = model.doRetriveByKey(id);
					if(prodotto != null && !prodotto.isEmpty()) {
						carrello.deleteProdotto(prodotto);;
						request.setAttribute("message", "Prodotto "+ prodotto.getNome()+" rimosso dal carrello");
					
			}
			}
					else if(action.equals("pulisci")) {
						carrello.deleteListaPodotti();
						request.setAttribute("message", "Carrello Pulito");
					}
		}catch (SQLException e) {
			System.out.println("Error: "+ e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/CarrelloView.jsp");
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