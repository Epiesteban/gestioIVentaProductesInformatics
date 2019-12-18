package models;

import java.io.Serializable;

public class LlistaProductes implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Declaracio de variables
	 */
	private final int mida = 500;
	private int nElem; //controlem elements correctes de la llista
	private Producte[] llista;

	/**
	 * constructor
	 */
	public LlistaProductes () {
		nElem=0;
		llista= new Producte [mida];
	}

	/**
	 * Setter i getters
	 */
	public int getnElem() {
		return nElem;
	}

	public void setnElem(int nElem) {
		this.nElem = nElem;
	}

	public Producte[] getLlista() {
		return llista;
	}

	public void setLlista(Producte[] llista) {
		this.llista = llista;
	}

	/**
	 * afegir producte no Repetits
	 */
	/*public void afegirProducteNoRepetit(Producte p) {
		//comprobar repetidos
		boolean trobat = false;
		int i = 0;
		if(nElem <  mida) {
			while (i < nElem && !trobat) {
				if(llista[i].getId()==p.getId()) {
					llista[i].setEstoc(llista[i].getEstoc()+p.getEstoc());
					trobat = true;
				}
				i++;
			}
			if (!trobat) {
				llista[nElem] = p;
				nElem++;
			}
			
		}
	}
*/
	/**
	 * Afegir producte a comanda
	 */
	public void afegirProducte(Producte p) {
		if(nElem <  mida) {
			llista[nElem] = p;
			nElem++;
		}
	}
	
	public Producte buscarProducte (int id) {
		for(int i = 0;i < nElem;i++) {
			if(llista[i].getId()== id) return llista[i];
		}
		return null;
	}

	/**
	 * buscar algun producte per el seu id i retorna posicio 
	 */
	public int buscarProducte_id(int id) {
		for(int i = 0;i < nElem;i++) {
			if(llista[i].getId()== id) return i;
		}
		return -1;
	}
	
	/**
	 * metode per buidar la llista de productes d'una comanda
	 */
	public void buidarLlista() {
		for(int i =0; i < nElem;i++) {
			llista[i].setEstoc(llista[i].getEstoc()+1);
		}
	}
	/**ToString
	 * 
	 */
	public String toString() {
		if (nElem==0) {
			return ("No hi ha elements a la llista de productes.\n");
		}else {
			String frase= "";
			for (int i=0; i<nElem; i++) {
				frase += llista[i].toString()+ "\n";
			}
			return frase;
		}
	}
}
