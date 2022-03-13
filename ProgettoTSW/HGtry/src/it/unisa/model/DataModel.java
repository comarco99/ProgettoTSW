//quest'intefaccia sintetizza tutti i metodi necessari per interrogare il database.
//implemento così le operazioni CRUD: creazione,eliminazione, salvataggio e update;
package it.unisa.model;

import java.sql.SQLException;

import java.util.Collection;
// DAO.devo implementarla per ogni singola tabella del mio db dato che ovviamente gli elementi di ogni tabella sono differenti;

public interface DataModel<T>{
	// il metodo mi permette di cercare un bean partendo dal suo id.Il metodo può dare eccezioni quindi uso SQLException
	//metto id come string in modo tale da avere poi la possibilità di usare sia int sia string.
	public T doRetriveByKey(String  id)  throws SQLException;
	
	//ci premette di visualizzare la lista di tutti i bean.posso attivare un ordinamento su un particolare parmetro (io uso il parametro order )
	public Collection<T> doRetriveAll(String order) throws SQLException;
	
	//salvare l'elemento all'interno del nostro DB
	public void doSave(T data)throws SQLException;
	
	
	//Fa l'aggiornamento o aggiunge una tupla
	public void  doUpdate(T data)throws SQLException;

	
	//mi va ad eliminare un elemento
	public void doDelete(T data)throws SQLException;

	
	
	
}
