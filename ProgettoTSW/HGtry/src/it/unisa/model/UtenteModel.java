package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class UtenteModel implements DataModel<UtenteBean> {

	@Override
	public UtenteBean doRetriveByKey(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement= null;
		
		UtenteBean utente= new UtenteBean();
		String selectSQL ="SELECT * FROM utente WHERE email=?";
		
		try {
			//mi connetto al db 
			connection= DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, id);
			
			//mi faccio stampare la richiesta sql giusto per capire come va 
			System.out.println("doRetriveByKey :" + preparedStatement.toString());
			ResultSet rs= preparedStatement.executeQuery();
			
			//utilizzo next perchè ho bisogno del primo output.in realtà, se pur ci fosse un altro untput noi prendiamo l'ultimo
			while(rs.next()) { 
				utente.setCognome(rs.getString("cognome"));
				utente.setD_nascita(rs.getString("d_nascita"));
				utente.setEmail(rs.getString("email"));
				utente.setIndirizzo(rs.getString("indirizzo"));
				utente.setL_nascita(rs.getString("l_nascita"));
				utente.setNome(rs.getString("nome"));
				utente.setPass(rs.getString("pass"));
				utente.setTelefono(rs.getString("telefono"));
				utente.setTipo(rs.getString("tipo"));
				
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
		return utente ;
	}
	
	// è il metodo più importante perchè mi restituisce tutti gli elementi 
		public Collection<UtenteBean> doRetriveAll(String order) throws SQLException{
			Connection connection = null;
			PreparedStatement preparedStatement= null;
			Collection<UtenteBean> lista_utente = new LinkedList<UtenteBean>();
			String selectSQL ="SELECT * FROM utente";
			
			 //se ho inserito un criterio per l'ordine allora lo utilizzo
			if(order != null && !order.equals("")) {
				 selectSQL += "ORDER BY" + order;
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
					UtenteBean utente= new UtenteBean();
					utente.setCognome(rs.getString("cognome"));
					utente.setD_nascita(rs.getString("d_nascita"));
					utente.setEmail(rs.getString("email"));
					utente.setIndirizzo(rs.getString("indirizzo"));
					utente.setL_nascita(rs.getString("l_nascita"));
					utente.setNome(rs.getString("nome"));
					utente.setPass(rs.getString("pass"));
					utente.setTelefono(rs.getString("telefono"));
					utente.setTipo(rs.getString("tipo"));
					
					lista_utente.add(utente);
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
			return lista_utente;
		}
		
		
		public void doSave(UtenteBean data)throws SQLException{
			Connection connection = null;
			PreparedStatement preparedStatement= null;
			String insertSQL="INSERT INTO utente(email,pass,cognome,nome,d_nascita,l_nascita,telefono,indirizzo,tipo)"+
			"VALUES(?,?,?,?,?,?,?,?,?)";
			
			try {
				//mi connetto al db 
				connection= DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				
				preparedStatement.setString(1, data.getEmail());
				preparedStatement.setString(2, data.getPass());
				preparedStatement.setString(3, data.getCognome());
				preparedStatement.setString(4, data.getNome());
				preparedStatement.setString(5, data.getD_nascita());
				preparedStatement.setString(6, data.getL_nascita());
				preparedStatement.setString(7, data.getTelefono());
				preparedStatement.setString(8, data.getIndirizzo());
				preparedStatement.setString(9, data.getTipo());
				
				preparedStatement.executeUpdate();
				connection.commit();
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
		}
		
		
		
		
		
		
		public void doDelete(UtenteBean utente)throws SQLException{
			Connection connection = null;
			PreparedStatement preparedStatement= null;
			String deleteSQL="DELETE FROM utente WHERE email=?";
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setString(1, utente.getEmail());
				
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



	



		@Override
		public void doUpdate(UtenteBean  data) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String updateSQL = "UPDATE utente SET  pass=?,cognome=?, nome=?, d_nascita=?, l_nascita=?" + 
			"telefono=?, indirizzo=?, tipo=? WHERE email=?";
					try {
						connection = DriverManagerConnectionPool.getConnection();
						preparedStatement = connection.prepareStatement(updateSQL);	
						
						
						preparedStatement.setString(1, data.getPass());
						preparedStatement.setString(2, data.getCognome());
						preparedStatement.setString(3, data.getNome());
						preparedStatement.setString(4, data.getD_nascita());
						preparedStatement.setString(5, data.getL_nascita());
						preparedStatement.setString(6, data.getTelefono());
						preparedStatement.setString(7, data.getIndirizzo());
						preparedStatement.setString(8, data.getTipo());
						preparedStatement.setString(9, data.getEmail());
						
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
			
		
		public boolean doValidate(String username, String password) throws SQLException  //se corrispondono true, false se utente non esiste e se la password non � giusta
		{
			Connection connection=null;
			PreparedStatement preparedStatement=null;
			
			String sql="SELECT pass FROM utente WHERE email=?";
			
				try {
					connection=DriverManagerConnectionPool.getConnection();
					preparedStatement=connection.prepareStatement(sql);
					
					preparedStatement.setString(1, username);
					
					ResultSet rs=preparedStatement.executeQuery();
					
					if(rs.next())
					{
						if(rs.getString("pass").equals(password))
							return true;
					}
					
					
				}
				finally {
					try {
						if (preparedStatement != null)
							preparedStatement.close();
					} finally {
						if(connection!=null)
							connection.close();
					}
				}
				
				return false;
		}
		
	}
