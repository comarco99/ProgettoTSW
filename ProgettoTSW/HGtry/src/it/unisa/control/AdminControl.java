package it.unisa.control;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;


import it.unisa.model.OrdineBean;
import it.unisa.model.OrdineModel;
import it.unisa.model.ProdottoBean;
import it.unisa.model.ProdottoModel;
import it.unisa.model.UtenteBean;
import it.unisa.model.UtenteModel;

/**
 * Servlet implementation class AdminControl
 */
@WebServlet("/AdminControl")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
     static OrdineModel omodel= new OrdineModel();
     static ProdottoModel pmodel= new ProdottoModel();
    

    public AdminControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// quando accediamo al pannello amministratore dall'area utente si passa per questa servlet
		// controlliamo tutte le possibili situazioni che determinerebbero un accesso illecito alla sezione
		//in tal caso rimandiamo a una pagina che segnala l'accesso non consentito (error.jsp)
		//altrimenti rimandiamo corretamente l'amministratore alla sua home page
		 Boolean admin= (Boolean)request.getSession().getAttribute("isAdmin");
		   Boolean logged=(Boolean) request.getSession().getAttribute("isLogged");
		   if(admin==false || !admin || logged==false || !logged ||admin==null || logged==null)
		   {
			    RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
				return;
		   }
		   else {
			   
			   String azione=(String) request.getParameter("azione");
			   if(azione==null) {
				   RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/admin/AdminHome.jsp");
					dispatcher.forward(request, response);
			   }
			   if(azione!=null) {
				  
					   if((azione.equals("visualizzaOrdini"))) {
						   Collection<OrdineBean> lista_ordini = new LinkedList<OrdineBean>();
						   String criterio= request.getParameter("criterio");
						   System.out.println(criterio);
						  try {
							   lista_ordini= omodel.doRetriveAll("importo "+criterio);
						  } catch (SQLException e) {
							   e.printStackTrace();
						  }
						  request.removeAttribute("listaOrdiniAdmin");
						  request.setAttribute("listaOrdiniAdmin", lista_ordini);
						   
					   }
					   if(azione.equals("aggiungi")) {
						  
						   String nome = request.getParameter("nome");
						   String descrizione = request.getParameter("descrizione");
						   float prezzo= Float.parseFloat(request.getParameter("prezzo"));
						   int quantita= Integer.parseInt(request.getParameter("quantita"));
						   String categoria = request.getParameter("categoria");
						   ProdottoBean prodotto = new ProdottoBean();
						   prodotto.setNome(nome);
						   prodotto.setCategoria(categoria);
						   prodotto.setPrezzo(prezzo);
						   prodotto.setDescrizione(descrizione);
						   prodotto.setQuantita(quantita);
						   try {
							   int i=0;
						   Collection<?> prodotti = pmodel.doRetriveAll("");
							if(prodotti != null && prodotti.size() > 0) {
								Iterator<?> it  = prodotti.iterator();
								while(it.hasNext()) {
									ProdottoBean bean = (ProdottoBean)it.next();	
									if(bean.getNome().equals(nome) && bean.getDescrizione().equals(descrizione) && bean.getCategoria().equals(categoria)) {
										//controlla la quantità che non aggiorna bene
										prodotto.setQuantita(bean.getQuantita()+quantita);
										prodotto.setId_prodotto(bean.getId_prodotto());
										pmodel.doUpdateQuantita(prodotto);
										i++;
									}
			
								}
								
								if(i == 0) {
							
										pmodel.doSave(prodotto);
										String SAVE_DIR = "/uploadTemp";
										String appPath = request.getServletContext().getRealPath("");
										String savePath = appPath + File.separator + SAVE_DIR;

										File fileSaveDir = new File(savePath);
										if (!fileSaveDir.exists()) {
											fileSaveDir.mkdir();
										}

										
											String fileName = extractFileName(request.getPart("fotoup"));
											if (fileName != null && !fileName.equals("")) {
												request.getPart("fotoup").write(savePath + File.separator + fileName);
												try {
													ProdottoModel.updatePhoto(nome, savePath + File.separator + fileName);
												} catch (SQLException sqlException) {
													System.out.println(sqlException);
												}
											}
										}
								   }
										
									}
									 
						
								
							
					   	
									
						   catch(SQLException | NumberFormatException e) {
							System.out.println("Error: "+ e.getMessage());
							request.setAttribute("error", e.getMessage());			
						}
					   }
					   
					   if(azione.equals("aggiornaFoto")) {
						   ProdottoBean bean = new ProdottoBean();
							String SAVE_DIR = "/uploadTemp";
							 String id = request.getParameter("id");
							   Collection<?> prodotti;
							try {
								prodotti = pmodel.doRetriveAll("");
				
								if(prodotti != null && prodotti.size() > 0) {
									Iterator<?> it  = prodotti.iterator();
									while(it.hasNext()) {
										bean =(ProdottoBean) it.next();
										if(bean.getId_prodotto() == Integer.parseInt(id))
											break;
									}}}
								 catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	
							String appPath = request.getServletContext().getRealPath("");
							String savePath = appPath + File.separator + SAVE_DIR;

							File fileSaveDir = new File(savePath);
							if (!fileSaveDir.exists()) {
								fileSaveDir.mkdir();
							}

							for (Part part : request.getParts()) {
								String fileName = extractFileName(part);
								if (fileName != null && !fileName.equals("")) {
									part.write(savePath + File.separator + fileName);
									try {
										ProdottoModel.updatePhoto(bean.getNome(), savePath + File.separator + fileName);
									} catch (SQLException sqlException) {
										System.out.println(sqlException);
									}
								}
							}
					   }
					   }
					 if(azione.equals("aggiornaNome")) {
						   String nome = request.getParameter("nome");
						   String id = request.getParameter("id");
						   ProdottoBean prodotto = new ProdottoBean();
						   ProdottoBean bean = new ProdottoBean();
						   
						   Collection<?> prodotti;
						try {
							prodotti = pmodel.doRetriveAll("");
						
							if(prodotti != null && prodotti.size() > 0) {
								Iterator<?> it  = prodotti.iterator();
								while(it.hasNext()) {
									bean = (ProdottoBean)it.next();	
									if(bean.getId_prodotto() == Integer.parseInt(id)) {
										break;
									}else {
										request.setAttribute("message", "PRODOTTO NON PRESENTE");
										   RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/admin/AdminHome.jsp");
											dispatcher.forward(request, response);
									}
									   prodotto.setId_prodotto(Integer.parseInt(id));
									   prodotto.setNome(nome);
									   
									   try {
										pmodel.doUpdateNome(prodotto);
										
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								}
							}
								
							 catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					 }
					 if(azione.equals("aggiornaDescrizione")) {
						   String descrizione = request.getParameter("descrizione");
						   String id = request.getParameter("id");
						   ProdottoBean prodotto = new ProdottoBean();
						   ProdottoBean bean = new ProdottoBean();
						   
						   Collection<?> prodotti;
						try {
							prodotti = pmodel.doRetriveAll("");
						
							if(prodotti != null && prodotti.size() > 0) {
								Iterator<?> it  = prodotti.iterator();
								while(it.hasNext()) {
									bean = (ProdottoBean)it.next();	
									if(bean.getId_prodotto() == Integer.parseInt(id)) {
										break;
									}else {
										request.setAttribute("message", "PRODOTTO NON PRESENTE");
										   RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/admin/AdminHome.jsp");
											dispatcher.forward(request, response);
									}
									   prodotto.setId_prodotto(Integer.parseInt(id));
									   prodotto.setDescrizione(descrizione);
									   
									   try {
										pmodel.doUpdateDescrizione(prodotto);
										
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
							}
								
							 catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					 }
					 if(azione.equals("aggiornaPrezzo")) {
						  Float prezzo = Float.parseFloat(request.getParameter("prezzo"));
						   String id = request.getParameter("id");
						   ProdottoBean prodotto = new ProdottoBean();
						   ProdottoBean bean = new ProdottoBean();
						   
						   Collection<?> prodotti;
						try {
							prodotti = pmodel.doRetriveAll("");
						
							if(prodotti != null && prodotti.size() > 0) {
								Iterator<?> it  = prodotti.iterator();
								while(it.hasNext()) {
									bean = (ProdottoBean)it.next();	
									if(bean.getId_prodotto() == Integer.parseInt(id)) {
										break;
									}else {
										request.setAttribute("message", "PRODOTTO NON PRESENTE");
										   RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/admin/AdminHome.jsp");
											dispatcher.forward(request, response);
									}
									   prodotto.setId_prodotto(Integer.parseInt(id));
									   prodotto.setPrezzo(prezzo);
									   
									   try {
										pmodel.doUpdatePrezzo(prodotto);
										
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
							}
						
								
							 catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					 }
					 if(azione.equals("aggiornaCategoria")) {
						   String categoria = request.getParameter("categoria");
						   String id = request.getParameter("id");
						   ProdottoBean prodotto = new ProdottoBean();
						   ProdottoBean bean = new ProdottoBean();
						   
						   Collection<?> prodotti;
						try {
							prodotti = pmodel.doRetriveAll("");
						
							if(prodotti != null && prodotti.size() > 0) {
								Iterator<?> it  = prodotti.iterator();
								while(it.hasNext()) {
									bean = (ProdottoBean)it.next();	
									if(bean.getId_prodotto() == Integer.parseInt(id)) {
										break;
									}else {
										request.setAttribute("message", "PRODOTTO NON PRESENTE");
										   RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/admin/AdminHome.jsp");
											dispatcher.forward(request, response);
									}
									   prodotto.setId_prodotto(Integer.parseInt(id));
									   prodotto.setCategoria(categoria);
									   
									   try {
										pmodel.doUpdateCategoria(prodotto);
										
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
							}
								
							 catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					 }
					 if(azione.equals("aggiornaQuantita")) {
						   int quantita = Integer.parseInt(request.getParameter("quantita"));
						   String id = request.getParameter("id");
						   ProdottoBean prodotto = new ProdottoBean();
						   ProdottoBean bean = new ProdottoBean();
						   
						   Collection<?> prodotti;
						try {
							prodotti = pmodel.doRetriveAll("");
						
							if(prodotti != null && prodotti.size() > 0) {
								Iterator<?> it  = prodotti.iterator();
								while(it.hasNext()) {
									bean = (ProdottoBean)it.next();	
									if(bean.getId_prodotto() == Integer.parseInt(id)) {
										break;
									}else {
										request.setAttribute("message", "PRODOTTO NON PRESENTE");
										   RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/admin/AdminHome.jsp");
											dispatcher.forward(request, response);
									}
									   prodotto.setId_prodotto(Integer.parseInt(id));
									   prodotto.setQuantita(quantita);
									   
									   try {
										pmodel.doUpdateQuantita(prodotto);
										
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
							}
								
							 catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					 }
						   
					 if(azione.equals("rimuovi")) {
						   int id= Integer.parseInt(request.getParameter("id"));
						   ProdottoBean prodotto = new ProdottoBean();
						   prodotto.setId_prodotto(id);
						   try {
						   Collection<?> prodotti = pmodel.doRetriveAll("");
						   if(prodotti != null && prodotti.size() > 0) {
								Iterator<?> it  = prodotti.iterator();
								while(it.hasNext()) {
									ProdottoBean bean = (ProdottoBean)it.next();	
									if(bean.getId_prodotto() == id) {
										break;
									}else {
										request.setAttribute("message", "PRODOTTO NON PRESENTE");
										   RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/admin/AdminHome.jsp");
											dispatcher.forward(request, response);
									}
									 pmodel.doDelete(prodotto);
								}
							}
									
					   }catch(SQLException | NumberFormatException e) {
							System.out.println("Error: "+ e.getMessage());
							request.setAttribute("error", e.getMessage());			
						}
					 }}
					
			   RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/admin/AdminHome.jsp");
				dispatcher.forward(request, response);
}
		   	   

	
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
	

}
