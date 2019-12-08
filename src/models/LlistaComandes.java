package models;

import models.*;

/**
 * LLISTA COMANDA 
 * @author Xènia Fuentes Font 
 *
 */
public class LlistaComandes {

		private Comanda [] llista; 
		private int numElem; 
		public  int nEspais = 1000; //lliures
		private int posBorrar; 
		private boolean trobat;
		 
	


/**
 * Constructor per defecte
 */
	
	public LlistaComandes() {
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
	 * @param numElem --> per poder canviar el numero d'elements que es guarden a la llista 
	 */
	public void setNElem (int numElem) {
		this.numElem = numElem;
	}
	/**
	 * Setter llista
	 * @param llista --> per poder canviar els elements de la llista 
	 */
	public void setLlista (Comanda[] llista) {
		this.llista = llista;
	}
/**
 * GETTERS
 */
	/**
	 * Getter llista
	 * @return --> per poder aconseguir els elements que hi ha guardats a la llista
	 */
	public Comanda[] getLlista () {
		return llista;
	}
	/**
	 * Getter nElem
	 * @return --> per poder aconseguir el numero d'elements que hi ha guardats a la llista
	 */
	public int getnumElem () {
		return numElem;
	}
	
/**
 * MÈTODES
 */
	/**
	 * 1. Retorna l'element i de la llista 
	 * @param index: on s'introdueix per paràmetre la posició per cercar l'element i de la llista 
	 * @return --> l'element que hi ha a la posició index
	 */
	public Comanda buscarElement (int index) { 
		return llista[index];
	}
	

	/**
	 * 2. Afegeix una comanda en la llista 
	 * @param v : on s'introdueix la comanda per poder-la guardar en la llista 
	 */
	public void afegirComanda(Comanda v) {
		llista[numElem] = v;
		numElem++;
		nEspais--; 
	}

	/**
	 * 3. Elimina comanda en la llista
	 * @param identificador : on s'introdueix l'identificador per poder eliminar la comanda 
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
				llista[i]=llista[i+1]; //Canvi de posició
				nEspais++; 
			}
		}
	}
	
	/**
	 * 4. Elimina TOTES les comandes
	 * @param identificador :  on s'introdueix l'identificador per poder eliminar totes les comandes que té
	 * @return --> elimina totes les comandes de un identificador específic
	 */
	public LlistaComandes eliminarComanda2 (String identificador){
		LlistaComandes llistaAux=new LlistaComandes();
		for (int i=0; i<numElem; i++) {
			if (llista[i].getIdentificador().substring(0,9).equals(identificador)){
				llistaAux.afegirComanda(llista[i]);;
			}
		}
		return llistaAux;
	}

	/**
	 * 5. Busca una comanda en la llista
	 * @param v : on s'introdueix la comanda que estas buscant en la llista 
	 * @return --> retorna si s'ha trobat la comanda demanada (TRUE)
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
