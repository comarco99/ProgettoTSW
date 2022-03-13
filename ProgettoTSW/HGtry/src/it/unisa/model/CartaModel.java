package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class CartaModel implements DataModel<CartaBean> {


	@Override
	public CartaBean doRetriveByKey(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement= null;
		
		CartaBean carta= new CartaBean();
		String selectSQL ="SELECT * FROM carta WHERE cod_carta=?";
		
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
				carta.setCod_carta(rs.getInt("cod_carta"));
				carta.setCvv(rs.getInt("cvv"));
				carta.setInestatario(rs.getString("intestatario"));
				carta.setScadenza(rs.getString("scadenza"));
				
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
		return carta;
	}
	
	// è il metodo più importante perchè mi restituisce tutti gli elementi 
		public Collection<CartaBean> doRetriveAll(String order) throws SQLException{
			Connection connection = null;
			PreparedStatement preparedStatement= null;
			Collection<CartaBean> lista_carte = new LinkedList<CartaBean>();
			String selectSQL ="SELECT * FROM carta";
			
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
					CartaBean carta= new CartaBean();
					carta.setCod_carta(rs.getInt("cod_carta"));
					carta.setCvv(rs.getInt("cvv"));
					carta.setInestatario(rs.getString("intestatario"));
					carta.setScadenza(rs.getString("scadenza"));
					lista_carte.add(carta);
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
			return lista_carte;
		}
		
		
		public void doSave(CartaBean data)throws SQLException{
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			String insertSQL = "INSERT INTO carta(cod_carta,intestatario,scadenza,cvv) VALUES(?,?,?,?)";
			try {
				//mi connetto al db 
				connection= DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				
				preparedStatement.setInt(1, data.getCod_carta());
				preparedStatement.setString(2, data.getInestatario());
				preparedStatement.setString(3, data.getScadenza());
				preparedStatement.setInt(4, data.getCvv());
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
		
		
		
		
		
		
		public void doDelete(CartaBean carta)throws SQLException{
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			String deleteSQL = "DELETE FROM carta WHERE cod_carta = ?";
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setInt(1,carta.getCod_carta());
				
				System.out.println("doDelete: "+ preparedStatement.toString());
				 preparedStatement.executeUpdate();
				
				connection.commit();
			}
			finally {
					try {
						if(preparedStatement != null) 
							preparedStatement.close();
					} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			
			
		}



	



		@Override
		public void doUpdate(CartaBean data) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String updateSQL = "UPDATE carta SET intestatario=?, scadenza=?, cvv=? WHERE cod_carta=?";
					try {
						connection = DriverManagerConnectionPool.getConnection();
						preparedStatement = connection.prepareStatement(updateSQL);	
						
						preparedStatement.setString(1, data.getInestatario());
						preparedStatement.setString(2, data.getScadenza());
						preparedStatement.setInt(3, data.getCvv());
						preparedStatement.setInt(4, data.getCod_carta());
						
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

	}

