package it.unisa.model;

import java.io.Serializable;

public class CartaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	int cod_carta;
	String intestatario;
	String scadenza;
	int cvv;
	
	public CartaBean() {
		cod_carta = 0;
		intestatario="";
		scadenza="";
		cvv=0;
	}

	public int getCod_carta() {
		return cod_carta;
	}

	public void setCod_carta(int cod_carta) {
		this.cod_carta = cod_carta;
	}

	public String getInestatario() {
		return intestatario;
	}

	public void setInestatario(String intestatario) {
		this.intestatario = intestatario;
	}

	public String getScadenza() {
		return scadenza;
	}

	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	
	@Override
	public String toString() {
		return cod_carta + "Instastata a: "+ intestatario + " Scade il: " + scadenza + "CVV: " + cvv; 
	}
}
