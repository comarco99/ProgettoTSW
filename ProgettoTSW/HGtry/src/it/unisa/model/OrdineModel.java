package it.unisa.model;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

public class OrdineModel implements DataModel<OrdineBean> {

	
	
	@Override
	public OrdineBean doRetriveByKey(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement= null;
		OrdineBean ordine= new OrdineBean();
		String selectSQL ="SELECT * FROM ordine WHERE id_ordine=?";
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
				ordine.setCod_carta(rs.getInt("cod_carta"));
				ordine.setData_ordine(rs.getString("data_ordine"));
				ordine.setEmail(rs.getString("email"));
				ordine.setId_ordine(rs.getInt("id_ordine"));
				ordine.setImporto(rs.getFloat("importo"));
				ordine.setInd_consegna(rs.getString("ind_consegna"));
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
		return ordine;
		}
	
	
	// è il metodo più importante perchè mi restituisce tutti gli elementi 
		public Collection<OrdineBean> doRetriveAll(String order) throws SQLException{
			Connection connection = null;
			PreparedStatement preparedStatement= null;
			Collection<OrdineBean> lista_ordini = new LinkedList<OrdineBean>();
			String selectSQL ="SELECT * FROM ordine";
			
			 //se ho inserito un criterio per l'ordine allora lo utilizzo
			if(order != null && !order.equals("")) {
				 selectSQL += " ORDER BY " + order;
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
					OrdineBean ordine= new OrdineBean();
					ordine.setCod_carta(rs.getInt("cod_carta"));
					ordine.setData_ordine(rs.getString("data_ordine"));
					ordine.setEmail(rs.getString("email"));
					ordine.setId_ordine(rs.getInt("id_ordine"));
					ordine.setImporto(rs.getFloat("importo"));
					ordine.setInd_consegna(rs.getString("ind_consegna"));
					
					lista_ordini.add(ordine);
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
			return lista_ordini;
		}
		
		
	
		
		
		
		
		
		public void doDelete(OrdineBean data)throws SQLException{
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			String deleteSQL = "DELETE FROM ordine WHERE id_ordine = ?";
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setInt(1, data.getId_ordine());
				
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
		public void doSave(OrdineBean data) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			String insertSQL = "INSERT INTO ordine(id_ordine,importo,data_ordine,ind_consegna,email,cod_carta)"
					+ "VALUES(?,?,?,?,?,?)";
			
			
			try {
				//mi connetto al db 
				connection= DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				
				preparedStatement.setInt(1, data.getId_ordine());
				preparedStatement.setFloat(2, data.getImporto());
				preparedStatement.setString(3, data.getData_ordine());
				preparedStatement.setString(4, data.getInd_consegna());
				preparedStatement.setString(5, data.getEmail());
				preparedStatement.setInt(6, data.getCod_carta());
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


		@Override
		public void doUpdate(OrdineBean data) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String updateSQL = "UPDATE ordine SET importo=?, data_ordine=?, ind_consegna=? email=? cod_carta=? WHERE id_ordine=?";
					try {
						connection = DriverManagerConnectionPool.getConnection();
						preparedStatement = connection.prepareStatement(updateSQL);	
						
						preparedStatement.setFloat(1, data.getImporto());
						preparedStatement.setString(2, data.getData_ordine());
						preparedStatement.setString(3, data.getInd_consegna());
						preparedStatement.setString(4, data.getEmail());
						preparedStatement.setInt(5, data.getCod_carta());
						preparedStatement.setInt(6, data.getId_ordine());
						
						
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

	
