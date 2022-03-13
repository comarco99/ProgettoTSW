package it.unisa.model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;







public class ProdottoModel implements DataModel<ProdottoBean>{
	public void  doUpdate(ProdottoBean data)throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL = "UPDATE prodotto SET nome=?, descrizione=?, prezzo=?, quantita=?,categoria=?"
				+ " WHERE id_prodotto=?";
				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(updateSQL);	
					preparedStatement.setString(1, data.getNome());
					preparedStatement.setString(2, data.getDescrizione());
					preparedStatement.setFloat(3, data.getPrezzo());
					preparedStatement.setInt(4, data.getQuantita());
					preparedStatement.setString(5, data.getCategoria());
					preparedStatement.setInt(6, data.getId_prodotto());
					System.out.println("doUpdate: "+ preparedStatement.toString());
					preparedStatement.executeUpdate();
					
					connection.commit();
		
				} finally {
					try {
						if(preparedStatement != null) 
							preparedStatement.close();
					} finally {
						DriverManagerConnectionPool.releaseConnection(connection);
					}
				}	
		}
	

	public ProdottoBean doRetriveByKey(String  id)  throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement= null;
		ProdottoBean prodotto= new ProdottoBean();
		String selectSQL="SELECT * FROM prodotto WHERE id_prodotto=?";
		try {
			//mi connetto al db 
			connection= DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, Integer.parseInt(id));
			
			//mi faccio stampare la richiesta sql giusto per capire come va 
			System.out.println("doRetriveByKey :" + preparedStatement.toString());
			ResultSet rs= preparedStatement.executeQuery();
			
			//utilizzo next perchè ho bisogno del primo output.in realtà, se pur ci fosse un altro untput noi prendiamo l'ultimo
			while(rs.next()) {
			prodotto.setId_prodotto(rs.getInt("id_prodotto"));
			prodotto.setNome(rs.getString("nome"));
			prodotto.setDescrizione(rs.getString("descrizione"));
			prodotto.setPrezzo(rs.getFloat("prezzo"));
			prodotto.setQuantita(rs.getInt("quantita"));
	
			prodotto.setCategoria(rs.getString("categoria"));
		
			}
		}
		
			finally {
				//vado a rilasciare le risorse
				try {
					//chiudo solo se preparedStatement esiste
					if(preparedStatement != null)
						preparedStatement.close();
				}
				finally {
					// è finta ma non chiude la connessione ma dà la possibilità alle altre richieste di poterla utilizzare
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		return prodotto;
	}
// è il metodo più importante perchè mi restituisce tutti gli elementi 
	public Collection<ProdottoBean> doRetriveAll(String order) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement= null;
		Collection<ProdottoBean> lista_prodotti = new LinkedList<ProdottoBean>();
		String selectSQL ="SELECT * FROM prodotto";
		
		 //se ho inserito un criterio per l'ordine allora lo utilizzo
		if(order != null && !order.equals("")) {
			 selectSQL +=  " ORDER BY " + order;
		}
		
		//ci potrebbero essere delle eccezioni.Queste non vengono gestite qui ma le voglio gestire all'eserno 
		try {
			//mi connetto al db 
			connection= DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			//mi faccio stamoare la richiesta sql giusto per capire come va 
			System.out.println("doRetriveAll:" + preparedStatement.toString());
			ResultSet rs= preparedStatement.executeQuery();
			//devo poi leggere una tupla alla volta. vado quindi così a ciclare
			while(rs.next()) {
				//la tupla è rappresentata da un oggetto ProdottoBean quindi 
				ProdottoBean prodotto= new ProdottoBean();
				prodotto.setId_prodotto(rs.getInt("id_prodotto"));
				prodotto.setNome(rs.getString("nome"));
				prodotto.setDescrizione(rs.getString("descrizione"));
				prodotto.setPrezzo(rs.getFloat("prezzo"));
				prodotto.setQuantita(rs.getInt("quantita"));
				//prodotto.setFoto(rs.getBytes("foto"));
				prodotto.setCategoria(rs.getString("categoria"));
				lista_prodotti.add(prodotto);
				}
			}
			finally {
				//vado a rilasciare le risorse
				try {
					//chiudo solo se preparedStatement esiste
					if(preparedStatement != null)
						preparedStatement.close();
				}
				finally {
					// è finta ma non chiude la connessione ma dà la possibilità alle altre richieste di poterla utilizzare
					DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return lista_prodotti;
	}
	
	
	public void doSave(ProdottoBean data)throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement= null;
		//File file = new File(data.getFoto());
		String insertSQL= "INSERT INTO prodotto(nome,descrizione, prezzo, quantita,categoria) values(?,?,?,?,?)";
		try {
			//mi connetto al db 
			connection= DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
		//	FileInputStream fileStream = new FileInputStream(file);
			preparedStatement.setString(1, data.getNome());
			preparedStatement.setString(2, data.getDescrizione());
			preparedStatement.setFloat(3, data.getPrezzo());
			preparedStatement.setInt(4, data.getQuantita());
			preparedStatement.setString(5, data.getCategoria());
			
			
			//mi faccio stampoare la richiesta sql giusto per capire come va 
			System.out.println("doSave:" + preparedStatement.toString());
			preparedStatement.executeUpdate();
			//uso commit per salvare le modifiche
			//N.B. se non inserisco connection.commit NON VENGONO APPORTATE LE MODIFICHE AL DB
			connection.commit();
			
		//} catch (FileNotFoundException e) {
		//	System.out.println(e);
		//} catch (IOException e) {
		//	System.out.println(e);
		}finally {
			//vado a rilasciare le risorse
			try {
				//chiudo solo se preparedStatement esiste
				if(preparedStatement != null)
					preparedStatement.close();
			}
			finally {
				// è finta ma non chiude la connessione ma dà la possibilità alle altre richieste di poterla utilizzare
				DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
			
		}
	
	
	
	
	public void  doUpdateNome(ProdottoBean data)throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL = "UPDATE prodotto SET nome=?"
				+ " WHERE id_prodotto=?";
				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(updateSQL);	
					preparedStatement.setString(1, data.getNome());
					preparedStatement.setInt(2, data.getId_prodotto());
					System.out.println("doUpdate: "+ preparedStatement.toString());
					preparedStatement.executeUpdate();
					
					connection.commit();
		
				} finally {
					try {
						if(preparedStatement != null) 
							preparedStatement.close();
					} finally {
						DriverManagerConnectionPool.releaseConnection(connection);
					}
				}	
		}
	
	public void  doUpdateDescrizione(ProdottoBean data)throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL = "UPDATE prodotto SET  descrizione=?"
				+ " WHERE id_prodotto=?";
				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(updateSQL);
					preparedStatement.setString(1, data.getDescrizione());
					preparedStatement.setInt(2, data.getId_prodotto());
					System.out.println("doUpdate: "+ preparedStatement.toString());
					preparedStatement.executeUpdate();
					
					connection.commit();
		
				} finally {
					try {
						if(preparedStatement != null) 
							preparedStatement.close();
					} finally {
						DriverManagerConnectionPool.releaseConnection(connection);
					}
				}	
		}
	
	public void  doUpdatePrezzo(ProdottoBean data)throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL = "UPDATE prodotto SET  prezzo=?"
				+ " WHERE id_prodotto=?";
				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(updateSQL);
					preparedStatement.setFloat(1, data.getPrezzo());
					preparedStatement.setInt(2, data.getId_prodotto());
					System.out.println("doUpdate: "+ preparedStatement.toString());
					preparedStatement.executeUpdate();
					
					connection.commit();
		
				} finally {
					try {
						if(preparedStatement != null) 
							preparedStatement.close();
					} finally {
						DriverManagerConnectionPool.releaseConnection(connection);
					}
				}	
		}
	public void  doUpdateCategoria(ProdottoBean data)throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL = "UPDATE prodotto SET  categoria=?"
				+ " WHERE id_prodotto=?";
				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(updateSQL);
					preparedStatement.setString(1, data.getCategoria());
					preparedStatement.setInt(2, data.getId_prodotto());
					System.out.println("doUpdate: "+ preparedStatement.toString());
					preparedStatement.executeUpdate();
					
					connection.commit();
		
				} finally {
					try {
						if(preparedStatement != null) 
							preparedStatement.close();
					} finally {
						DriverManagerConnectionPool.releaseConnection(connection);
					}
				}	
		}

public void  doUpdateQuantita(ProdottoBean data)throws SQLException{
Connection connection = null;
PreparedStatement preparedStatement = null;
String updateSQL = "UPDATE prodotto SET  quantita=?"
		+ " WHERE id_prodotto=?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setInt(1, data.getQuantita());
			preparedStatement.setInt(2, data.getId_prodotto());
			System.out.println("doUpdate: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			connection.commit();

		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}	
}
	
	
	public void doDelete(ProdottoBean prodotto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM prodotto WHERE id_prodotto = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1,prodotto.getId_prodotto());
			
			System.out.println("doDelete: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			connection.commit();
			
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	public synchronized byte[]load(int id) throws SQLException
	{
		Connection connection=null;
		PreparedStatement stat=null;
		ResultSet rs=null;
		
		String sql="SELECT foto FROM prodotto where id_prodotto= ?";
		
		byte[] foto=null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			stat=connection.prepareStatement(sql);
			stat.setInt(1, id);
			
			rs=stat.executeQuery();
			
			if(rs.next())
			{
				foto=rs.getBytes("foto");
			}
			
		} finally {
			try {
				if (stat != null)
					stat.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
		return foto;
	}
	
	//caricare foto di un prodotto 
	public synchronized static void updatePhoto(String idA, String photo) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = DriverManagerConnectionPool.getConnection();

			stmt = con.prepareStatement("UPDATE prodotto SET foto = ? WHERE nome = ?");
			
			File file = new File(photo);
			try {
				FileInputStream fis = new FileInputStream(file);
				stmt.setBinaryStream(1, fis, fis.available());
				stmt.setString(2, idA);
				
				stmt.executeUpdate();
				con.commit();
			} catch (FileNotFoundException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} finally {
				if (con != null)
					DriverManagerConnectionPool.releaseConnection(con);
			}
		}
	}	
	
	
	
	
	
	
	/*+++++++++++++++++++++++++++++SELEZIONARE I PRODOTTI DEGLI ORDINI+++++++++++++++++++*/
	public Collection<ProdottoBean> restituisciProdottoOrdine(String  idUtente, int idordine)  throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement= null;
		
		String selectSQL="SELECT * "
				+ "FROM prodotto as p, ordine as o, carta as c, utente as u, include as i "
				+ "WHERE i.id_ordine=o.id_ordine and p.id_prodotto=i.id_prodotto and o.email=u.email and c.cod_carta=o.cod_carta and u.email=? and o.id_ordine=?";
		Collection<ProdottoBean> lista_prodotti = new LinkedList<ProdottoBean>();
		try {
			//mi connetto al db 
			connection= DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, idUtente);
			preparedStatement.setInt(2, idordine);
			
			//mi faccio stampare la richiesta sql giusto per capire come va 
			System.out.println("doRetriveByKey :" + preparedStatement.toString());
			ResultSet rs= preparedStatement.executeQuery();
			
			//utilizzo next perchè ho bisogno del primo output.in realtà, se pur ci fosse un altro untput noi prendiamo l'ultimo
			while(rs.next()) {
				ProdottoBean prodotto= new ProdottoBean();
				prodotto.setId_prodotto(rs.getInt("id_prodotto"));
				prodotto.setNome(rs.getString("nome"));
				prodotto.setDescrizione(rs.getString("descrizione"));
				prodotto.setPrezzo(rs.getFloat("prezzo"));
				prodotto.setQuantita(rs.getInt("quantita"));
				prodotto.setCategoria(rs.getString("categoria"));
				
				System.out.println("ciao: "+prodotto.getNome()+"\n");
				lista_prodotti.add(prodotto);
				
			}
			
		}
		
			finally {
				//vado a rilasciare le risorse
				try {
					//chiudo solo se preparedStatement esiste
					if(preparedStatement != null)
						preparedStatement.close();
				}
				finally {
					// è finta ma non chiude la connessione ma dà la possibilità alle altre richieste di poterla utilizzare
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		System.out.println("MODELLLL: \n"+lista_prodotti.toString());
		return  lista_prodotti;
	}

	

}
