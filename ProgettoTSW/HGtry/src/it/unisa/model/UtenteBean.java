package it.unisa.model;

import java.io.Serializable;

public class UtenteBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	String email;
	String pass;
	String cognome;
	String nome;
	String d_nascita;
	String l_nascita;
	String telefono;
	String indirizzo;
	String tipo;
	
	public UtenteBean() {
		email="";
		pass="";
		cognome="";
		nome="";
		d_nascita="";
		l_nascita="";
		telefono="";
		indirizzo="";
		tipo="";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getD_nascita() {
		return d_nascita;
	}

	public void setD_nascita(String d_nascita) {
		this.d_nascita = d_nascita;
	}

	public String getL_nascita() {
		return l_nascita;
	}

	public void setL_nascita(String l_nascita) {
		this.l_nascita = l_nascita;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return email + " " + pass + " "+  nome +" "+ cognome + " "+ d_nascita+ " "+ l_nascita + " "+ indirizzo + " "+telefono + " "+ tipo;
	}
}
