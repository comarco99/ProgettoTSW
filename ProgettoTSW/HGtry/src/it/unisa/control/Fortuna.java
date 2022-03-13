package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.model.ProdottoBean;
import it.unisa.model.ProdottoModel;

/**
 * Servlet implementation class Fortuna
 */
@WebServlet("/Fortuna")
public class Fortuna extends HttpServlet {
	private static final long serialVersionUID = 1L;
    static ProdottoModel pmodel=new ProdottoModel();
    
    public Fortuna() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProdottoBean casuale= new ProdottoBean();
		int casualenumero = (int)(Math.random()*25);
		
		
		try {
			System.out.println(String.valueOf(casualenumero).toString());
			if(casualenumero==0) { casualenumero=1; System.out.println("CHE CAZZO");}
			casuale=pmodel.doRetriveByKey(String.valueOf(casualenumero).toString());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Gson g= new Gson();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(g.toJson(casuale));
	}

}
