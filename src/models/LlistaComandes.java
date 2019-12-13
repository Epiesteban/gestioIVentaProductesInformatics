package models;

/**
 * LLISTA COMANDA 
 * @author Xènia Fuentes Font 
 *
 */

public class LlistaComandes {

	private Comanda [] llista; 
	private int nComanda; 
	private final int mida = 500;


	public LlistaComandes() {
		this.llista = new Comanda[mida];
		this.nComanda = 0;
	}

	/**
	 * SETTERS
	 */
	/**
	 * Setter nElem
	 * @param numElem --> per poder canviar el numero d'elements que es guarden a la llista 
	 */
	public void setNElem (int nComanda) {
		this.nComanda = nComanda;
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
	public int getnComanda () {
		return nComanda;
	}

	/**
	 * MÈTODES
	 */
	/**

	/**
	 * 1. Afegeix una comanda en la llista 
	 * @param v : on s'introdueix la comanda per poder-la guardar en la llista 
	 */
	public void afegirComanda(Comanda v) {
		if (nComanda < llista.length) {
			llista[nComanda] = v;
			nComanda++;
		}
	}

	/**
	 * 2. Elimina comanda en la llista
	 * @param identificador : on s'introdueix l'identificador per poder eliminar la comanda 
	 */

	public void eliminaComanda (String Identificador) {

		for (int i = 0 ; i<nComanda; i++) {
			if (llista[i].getIdentificador().equals(Identificador)) {
				llista[i].getLlistaProductes().buidarLlista();
				nComanda--;
				for (int j = i;j< nComanda;j++) {
					llista[i] = llista[i+1]; /**Quan l'eliminem, adelantem les posicions del darrere a la posicio que hem borrat per a no tenir espais buits**/
				}
			}
		}
	}	

	/**
	 * 3. Elimina totes les comandes d'un mateix dni en la llista
	 * @param dni : on s'introdueix el dni per poder eliminar totes les comandes d'aquest
	 */

	public void eliminarComandes (String dni){
		for (int i = 0; i < nComanda; i++) {
			if (llista[i].getClient().getDni().equalsIgnoreCase(dni)) {
				eliminaComanda(llista[i].getIdentificador());
			}
		}
	}	

	/**
	 * TOSTRING!!!
	 */
	public String toString() {
		if (nComanda==0) {
			return "No hi ha elements a la llista de Comandes";
		}else {
			String frase="";
			for (int i=0;i<nComanda;i++) {
				frase+=llista[i].toString()+"\n";
			}
			return frase;
		}
	}

}
