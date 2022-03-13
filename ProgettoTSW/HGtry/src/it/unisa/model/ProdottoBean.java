//nel bean andiamo a considerare gli elementi che si trovano nella tabella Prodotto: 

package it.unisa.model;

import java.io.Serializable;

public class ProdottoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	int id_prodotto;
	String nome ;
	String descrizione ;
	float prezzo;
	int quantita;
	String categoria;
	int quantityCart;
	
	public ProdottoBean() {
		id_prodotto=-1;
		nome="" ;
		descrizione="" ;
		prezzo=0;
		quantita=0;
		quantityCart=0;
		
	}

	public int getId_prodotto() {
		return id_prodotto;
	}
	
	public int getQuantityCart() {return quantityCart;}
	public void setQuantityCart(int i) {quantityCart=i;}

	public void setId_prodotto(int id_prodotto) {
		this.id_prodotto = id_prodotto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}


	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public String toString() {
		return nome +"("+id_prodotto +")" +"info: " + descrizione + " "+ " Prezzo: "+ prezzo +"Quantità: " + quantita+ "Categoria: "+ categoria;
	}
	//implemento equals così da poter utilizzare .remove() nel carrello
	public boolean equals(Object o) {
		if(o == null) return false;
		if(getClass() != o.getClass()) return false;
		ProdottoBean prodotto = (ProdottoBean) o;
		return id_prodotto == prodotto.id_prodotto &&  nome.equals(prodotto.nome) &&
				descrizione.equals(prodotto.descrizione) && prezzo == prodotto.prezzo &&
				quantita == prodotto.quantita;
		
	}

	public boolean isEmpty() {
		
		return id_prodotto == -1;
	}
}
