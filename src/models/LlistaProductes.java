package models;

public class LlistaProductes {
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
	 * afegri producte
	 */
	
	public void afegirProducte(Producte p) {
		//comprobar repetidos
		llista[nElem]=p;
		nElem++;
	}
	
	/**
	 * buscar algun producte per el seu id i retorna posicio 
	 */
	public int buscarProductes(int id) {
		for(int i = 0;i < nElem;i++) {
			if(llista[i].getId()== id) return i;
		}
		return -1;
	}
	
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
