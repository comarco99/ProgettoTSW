package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.*;


@WebServlet("/getPicture")
public class ProductPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ProdottoModel model = new ProdottoModel();
	
   
    public ProductPictureServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id2 = (String)(request.getParameter("id"));
		int id=Integer.parseInt(id2);
		System.out.println("sono nella servlet foto"+id);
		if (id != -1)
		{
			byte[] bt=null;
			try {
				bt = model.load(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			ServletOutputStream out = response.getOutputStream();
			if(bt != null)
			{
				out.write(bt);
				response.setContentType("image/jpeg");			
			}
			out.close();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
