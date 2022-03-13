package it.unisa.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Carrello<T> {
	List<T> listaProdotti;
	

	public Carrello() {
		listaProdotti= new ArrayList<T>();
	}

	public void  aggiungiProdotto(T prodotto) {
		listaProdotti.add(prodotto);
	}
	
	public void  aggiornaQuantita(T prodotto) {
		ProdottoBean prodottoB = (ProdottoBean)prodotto;
		if(listaProdotti != null && listaProdotti.size()> 0) {
			java.util.Iterator<T> it = listaProdotti.iterator();
			while(it.hasNext()) {
				ProdottoBean bean = (ProdottoBean)it.next();	
				if(bean.getId_prodotto() == prodottoB.getId_prodotto()) {
					bean.setQuantityCart(prodottoB.getQuantityCart());
				}
			}
		}
	}

	public void deleteProdotto(T prodotto) {
		ProdottoBean bean = new ProdottoBean();
		ProdottoBean prodottoB = (ProdottoBean)prodotto;
		int i=0;
		if(listaProdotti != null && listaProdotti.size()> 0) {
			java.util.Iterator<T> it = listaProdotti.iterator();
			while(it.hasNext()) {
				bean = (ProdottoBean)it.next();	
				if(bean.getId_prodotto() == prodottoB.getId_prodotto()) {
					i++;
					break;
				}
			}
				if(i>0) {
					if(bean.getQuantityCart() <= 1) {
						listaProdotti.remove(prodotto);
					}else {
					bean.setQuantityCart(bean.getQuantityCart()-1);
					}
				}
			if(i==0) {
				listaProdotti.remove(prodotto);
			}
		}
		
	}

	public List<T> getListaProdotti(){
		return listaProdotti;
	}

	public void deleteListaPodotti() {
		listaProdotti.clear();
	}
	
	
	}

