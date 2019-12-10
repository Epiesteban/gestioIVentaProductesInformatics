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
	 * 2. Afegeix una comanda en la llista 
	 * @param v : on s'introdueix la comanda per poder-la guardar en la llista 
	 */
	public void afegirComanda(Comanda v) {
		if (nComanda < llista.length) {
			llista[nComanda] = v;
			nComanda++;
		}
	}

	/**
	 * 3. Elimina comanda en la llista
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
	 * 4. Elimina totes les comandes d'un mateix dni en la llista
	 * @param dni : on s'introdueix el dni per poder eliminar totes les comandes d'aquest
	 */

	public void eliminarComandes (String dni){
		int i = 0;

		while (i < llista.length) { /**Busquem la posicio i anem borrant comanda a comanda mentre el for va trobat comandes fetes amb el dni (i marca TRUE)**/
			for (int j = 0; j<nComanda; j++) {
				if (llista[j].getIdentificador().substring(0,9).equals(dni)) { /**Diem al programa que el dni va de la posició 0 a la 8 (exemple :12345678X hi ha 
																					9 elements a la String agafem de la posició 0 a la 8, que és com la 1 fins la 9)**/
					posBorrar = j;
					trobat = true;
				}
				if (trobat == true) {
					nComanda--;
					for (int k = posBorrar; k<nComanda; k++) {
						llista[k] = llista[k+1];
						nEspais++;
					}
				}
			}
		}
	}	

	/**
	 * 5. Busca una comanda en la llista
	 * @param v : on s'introdueix la comanda que estas buscant en la llista 
	 * @return --> retorna si s'ha trobat la comanda demanada (TRUE)
	 */
	public boolean buscarComanda (Comanda v) {
		for (int i=0; i<nComanda;i++) {
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
		if (nComanda==0) {
			return "";
		}else {
			String frase="";
			for (int i=0;i<nComanda;i++) {
				frase+=llista[i].toString()+"\n";
			}
			return frase;
		}
	}

}
