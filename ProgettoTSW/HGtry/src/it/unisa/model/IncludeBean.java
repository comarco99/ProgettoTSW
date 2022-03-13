package it.unisa.model;

import java.io.Serializable;

public class IncludeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	int id_ordine;
	int id_prodotto;
	String email;
	
	public IncludeBean() {
		id_ordine=0;
		id_prodotto=0;
		email="";
	}

	public int getId_ordine() {
		return id_ordine;
	}

	public void setId_ordine(int id_ordine) {
		this.id_ordine = id_ordine;
	}

	public int getId_prodotto() {
		return id_prodotto;
	}

	public void setId_prodotto(int id_prodotto) {
		this.id_prodotto = id_prodotto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return id_ordine + " "+ id_prodotto + " "+ email;
	}
	
	
}
