package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
import it.unisa.model.CartaBean;
import it.unisa.model.CartaModel;
import it.unisa.model.IncludeBean;
import it.unisa.model.IncludeModel;
import it.unisa.model.OrdineBean;
import it.unisa.model.OrdineModel;
import it.unisa.model.ProdottoBean;
import it.unisa.model.UtenteBean;

/**
 * Servlet implementation class OrdinaServlet
 */
@WebServlet("/OrdinaServlet")
public class OrdinaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static CartaModel cartaModel= new CartaModel();  
	static OrdineModel ordineModel= new OrdineModel(); 
    static IncludeModel includeModel=new IncludeModel();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdinaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		UtenteBean utente= (UtenteBean) session.getAttribute("utente");
		CartaBean carta= new CartaBean();
		OrdineBean ordine = new OrdineBean();
		IncludeBean include= new IncludeBean();
		ProdottoBean prod= new ProdottoBean();
		
		String intestatario= request.getParameter("intestatario");
		String numeroCarta=request.getParameter("numeroCarta");
		String cvvCarta= request.getParameter("cvvCarta");
		String scadenzaCarta = request.getParameter("scadenzaCarta");
		Float totale = Float.parseFloat(request.getParameter("totale"));
		String radio= request.getParameter("radio");
		Carrello carrello=(Carrello) session.getAttribute("carrello");
		
		if(radio.equals("indirizzo1")) {
			ordine.setInd_consegna(utente.getIndirizzo());
		}else if(radio.equals("indirizzo2")) {
			String indirizzo =request.getParameter("indirizzo");
			ordine.setInd_consegna(indirizzo);
		}
		CartaBean cartaVer;
		try {
			cartaVer = cartaModel.doRetriveByKey(numeroCarta);
			if(cartaVer != null) {
				cartaModel.doDelete(cartaVer);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		carta.setCod_carta(Integer.parseInt( request.getParameter("numeroCarta")));
		carta.setCvv(Integer.parseInt(cvvCarta));
		carta.setScadenza(scadenzaCarta);
		carta.setInestatario(intestatario);
		
		ordine.setCod_carta(Integer.parseInt( request.getParameter("numeroCarta")));
		ordine.setEmail(utente.getEmail());
		ordine.setImporto(totale);
		
		String data=(new java.util.Date().toString());
		ordine.setData_ordine(data);
		try {
		
			cartaModel.doSave(carta);
			ordineModel.doSave(ordine);
			
			int trovato=0;
			Collection<OrdineBean> ListaOr= ordineModel.doRetriveAll("");
			Iterator<?> it  = ListaOr.iterator();
			System.out.println(ListaOr.toString());
			while(it.hasNext()) {
				OrdineBean o=(OrdineBean)it.next();
					
					if((o.getEmail().equals(utente.getEmail()))&&(o.getCod_carta()==(carta.getCod_carta()))&&(o.getData_ordine().equals(data))) {
						trovato= o.getId_ordine();
						
						break;
					}
			}	
			System.out.println("trovato"+trovato);
			Collection<ProdottoBean> prodottiCarrello=(Collection<ProdottoBean>)carrello.getListaProdotti();
			Iterator<?> it2=prodottiCarrello.iterator();
			while(it2.hasNext()) {
				ProdottoBean p=(ProdottoBean)it2.next();
				include.setEmail(utente.getEmail());
				include.setId_ordine(trovato);
				include.setId_prodotto(p.getId_prodotto());
				includeModel.doSave(include);
			}
			
			
	
			carrello.deleteListaPodotti();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		  RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/OrdineEffettuato.jsp");
			dispatcher.forward(request, response);
	}
	}


