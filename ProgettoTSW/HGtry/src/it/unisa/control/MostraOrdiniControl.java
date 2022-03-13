package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.model.ProdottoBean;
import it.unisa.model.ProdottoModel;


@WebServlet("/MostraOrdiniControl")
public class MostraOrdiniControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
      static ProdottoModel pmodel= new ProdottoModel();
   
    public MostraOrdiniControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	//CARICHIAMO NELLA LISTA DI ORDINI GLI ORDINI OTTENUTI DALLA FUNZIONE 'restituisciProdottoOrdine' CONTENUTA IN 'ProdottoModel.java'
	//LA FUNZIONE EFFETTUA UNA SEMPLICE SELECT DI TUTTI I PRODOTTI REALATIVI ALL' ORDINE E L'UTENTE CONSIDERATO
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Collection<ProdottoBean> listaOrdini = new LinkedList<ProdottoBean>();
		String cliente= request.getParameter("cliente");
		int idOr=Integer.parseInt(request.getParameter("idOr"));
		try {
			
			listaOrdini=pmodel.restituisciProdottoOrdine(cliente,idOr);
			
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		
	
		//ISTANZIAMO UN OGGETTO GSON (abbiamo gia caricato la libreria google)
		//SETTIAMO IL TIPO DI CONTENUTO E EFFETTUIAMO IL PARSE DELL OGGETTO IN COSIDERAZIONE A OGGETTO JSON
		//VERRA' POI GESTITO NELLA FUNZIONE 'MostraOrdini' CON AJAX
		
		
		Gson g= new Gson();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(g.toJson(listaOrdini));
		
	}

}
