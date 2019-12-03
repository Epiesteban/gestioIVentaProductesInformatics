package classes;


public class LlistaComanda {
		private Comanda [] llista; 
		private int numElem; 
		public  int nEspais = 1000; //lliures
		private int posBorrar; 
		private boolean trobat; 
	


/**
 * Constructor per defecte
 */
	
	public LlistaComanda() {
		this.llista = new Comanda [1000];
		this.numElem = 0;
		this.nEspais = 1000; 
		this.posBorrar = 0;
		this.trobat = false;
		
	}
/**
 * SETTERS
 */
	/**
	 * Setter nElem
	 * @param numElem
	 */
	public void setNElem (int numElem) {
		this.numElem = numElem;
	}
	public void setLlista (Comanda[] llista) {
		this.llista = llista;
	}
/**
 * GETTERS
 */
	/**
	 * Getter llista
	 * @return
	 */
	public Comanda[] getLlista () {
		return llista;
	}
	
	public int getnumElem () {
		return numElem;
	}
	
/**
 * MÈTODES
 */
	/**
	 * 1.
	 * @param index
	 * @return
	 */
	public Comanda i (int index) { //Retorna l'element i de la llista i així podem accedir als elements d'aquesta
		return llista[index];
	}
	

	/**
	 * 2.
	 * @param v
	 */
	public void afegirComanda(Comanda v) {
		llista[numElem] = v;
		numElem++;
		nEspais--; 
	}

	/**
	 * 3.
	 * @param identificador
	 */
	public void eliminarComanda (String identificador) {
		for (int i=0 ; i<numElem; i++) {
			if (llista[i].getIdentificador().equals(identificador)) {
				posBorrar=i;
				trobat=true; 
			}
		}
		if (trobat==true) {
			numElem--;  
			for (int i=posBorrar; i<numElem; i++) {
				llista[i]=llista[i+1]; ///CAMBIO DE POSICIï¿½N
				nEspais++; 
			}
		}
	}

	/**
	 * 4.
	 * @param v
	 * @return
	 */
	public boolean buscarComanda (Comanda v) {
		for (int i=0; i<numElem;i++) {
			if (llista[i].getIdentificador().equals(v.getIdentificador())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * TOSTRING!!!
	 */
	public String toString() {
		if (numElem==0) {
			return "";
		}else {
			String frase="";
			for (int i=0;i<numElem;i++) {
				frase+=llista[i].toString()+"\n";
			}
			return frase;
		}
	}
	
}
