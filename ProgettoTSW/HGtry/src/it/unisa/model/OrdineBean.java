package it.unisa.model;

import java.io.Serializable;

public class OrdineBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	int id_ordine;
	float importo;
	String data_ordine;
	String ind_consegna;
	String email;
	int cod_carta;
	
	public OrdineBean() {
		id_ordine=0;
		importo=0;
		data_ordine="";
		ind_consegna="";
		email="";
		cod_carta=0;
	}

	public int getId_ordine() {
		return id_ordine;
	}

	public void setId_ordine(int id_ordine) {
		this.id_ordine = id_ordine;
	}

	public float getImporto() {
		return importo;
	}

	public void setImporto(float importo) {
		this.importo = importo;
	}

	public String getData_ordine() {
		return data_ordine;
	}

	public void setData_ordine(String data_ordine) {
		this.data_ordine = data_ordine;
	}

	public String getInd_consegna() {
		return ind_consegna;
	}

	public void setInd_consegna(String ind_consegna) {
		this.ind_consegna = ind_consegna;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCod_carta() {
		return cod_carta;
	}

	public void setCod_carta(int cod_carta) {
		this.cod_carta = cod_carta;
	}
	
	@Override
	public String toString() {
		return id_ordine + " "+ importo + " " + data_ordine +" "+ ind_consegna + " "+ email + " "+ cod_carta;
	}
}
