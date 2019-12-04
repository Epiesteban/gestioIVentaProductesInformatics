package models;

public class LlistaProductes {
	/**
	 * Declaracio de variables
	 */
	
	public int nElem, midaLlista=200; //controlem elements correctes de la llista
	public productes[] llista;
	
	/**
	 * constructor
	 */
	public LlistaProductes () {
		nElem=0;
		llista= new productes [midaLlista];
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

	public productes[] getLlista() {
		return llista;
	}

	public void setLlista(productes[] llista) {
		this.llista = llista;
	}
	/**
	 * afegri producte
	 */
	
	public void afegirproducte(productes p) {
		llista[nElem]=p;
		nElem++;
	}
	
	/**
	 * afegir producte software
	 */
	public void afegirSoftware (software s) {
		llista[nElem]= s;
		nElem++;
	}
	
	public void afegirSoftware (String n, int p, int e, SO s) {
		llista[nElem]=new software(n, p, e,s);
		nElem++;
	}
	
	/**
	 * afegir producte hardware
	 */
	
	public void afegirHardware (hardware h) {
		llista[nElem]=h;
		nElem++;
	}
	
	
	public void afegirHardware (String n, int p, int e, tipus_hardware t) {
		llista[nElem]=new hardware(n, p, e, t);
		nElem++;
	}
	/**
	 * afegir configuracio
	 */
	public void afegirConfiguracio (configuracio c) {
		llista [nElem]= c;
		nElem++;
	}
	/**
	 * llistat de tots els productes amb
	 *  alguna comanda mostrant dades del client
	 */
	public LlistaProductes algunaComanda (int comandes) {
		
	}
	/**
	 * buscar algun producte per el seu id i retorna posicio 
	 */
	public int buscarProductes(int id) {
		int i=0, j=0;
		boolean trobat= false;
		while (i<nElem && !trobat) {
			if(llista[i].getId()== id) {
				j=i;
				trobat= true;
			}
		}
		return j;
	}
	
	
	/*Modificar estoc*/
	public boolean modificarEstoc (int i, int e) {
		boolean realitzat=false;
		if (e>=0) {
			llista[i].setEstoc(e);
			realitzat=true;
		}
		return realitzat;
	}
	
	/**ToString
	 * 
	 */
	public String toString() {
		if (nElem==0) {
			return ("");
		
		}else {
			String frase= "";
			for (int i=0; i<nElem; i++) {
				frase= i+"-"+frase+llista[i].toString()+ "\n";
			}
			return frase;
		}
	}
}
