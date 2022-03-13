package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class IncludeModel implements DataModel<IncludeBean> {
	@Override
	public IncludeBean doRetriveByKey(String id) throws SQLException {
		return null;
	}
	
	// è il metodo più importante perchè mi restituisce tutti gli elementi 
		public Collection<IncludeBean> doRetriveAll(String order) throws SQLException{
			Connection connection = null;
			PreparedStatement preparedStatement= null;
			Collection<IncludeBean> lista_inclusione = new LinkedList<IncludeBean>();
			String selectSQL ="SELECT * FROM include";
			
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
					IncludeBean include= new IncludeBean();
					include.setEmail(rs.getString("email"));
					include.setId_ordine(rs.getInt("id_ordine"));
					include.setId_prodotto(rs.getInt("id_prodotto"));
					lista_inclusione.add(include);
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
			return lista_inclusione;
		}
		
	
		
		
		
		
		
		
		public void doDelete(IncludeBean data)throws SQLException{
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			String deleteSQL = "DELETE FROM include  WHERE id_ordine=?, id_prodotto=?, email=?";
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setInt(1, data.getId_ordine());
				preparedStatement.setInt(2, data.getId_prodotto());
				preparedStatement.setString(3, data.getEmail());
				
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
		public void doUpdate(IncludeBean data) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String updateSQL = "UPDATE include SET id_ordine=?, id_prodotto=?,  WHERE email=?";
					try {
						connection = DriverManagerConnectionPool.getConnection();
						preparedStatement = connection.prepareStatement(updateSQL);	
						
						preparedStatement.setInt(1, data.getId_ordine());;
						preparedStatement.setInt(2, data.getId_prodotto());
						preparedStatement.setString(3,data.getEmail());
						
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
			
		

		@Override
		public void doSave(IncludeBean data) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			String insertSQL = "INSERT INTO include(id_prodotto,id_ordine,email) VALUES(?,?,?)";
			try {
				//mi connetto al db 
				connection= DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				
				preparedStatement.setInt(1,data.getId_prodotto());
				preparedStatement.setInt(2, data.getId_ordine());
				preparedStatement.setString(3, data.getEmail());
				
				
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

	}

