package models;

public class LlistaProductes {
	/**
	 * Declaracio de variables
	 */
	private final int mida = 500;
	public int nElem; //controlem elements correctes de la llista
	public Producte[] llista;
	
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
	 * afegri producte
	 */
	
	public void afegirProducte(Producte p) {
		llista[nElem]=p;
		nElem++;
	}
	
	/**
	 * llistat de tots els productes amb
	 *  alguna comanda mostrant dades del client
	 */
	public LlistaProductes algunaComanda (int comandes) {
		return null;
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
