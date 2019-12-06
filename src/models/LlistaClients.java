package models;
/**
 * Classe que implementa una llista de Clients
 * @author JOEL
 *
 */

public class LlistaClients {

	private int nClients;
	private Client [] llista;
	private final int mida = 500;
	
	/**
	 * Metode constructor classe LlistaClients
	 */
	public LlistaClients() {
		nClients = 0;
		llista = new Client [mida];
	}

	/**
	 * getter nClients
	 * @return nClients
	 */
	public int getnClients() {
		return nClients;
	}

	/**
	 * setter nClients
	 * @param nClients
	 */
	public void setnClients(int nClients) {
		this.nClients = nClients;
	}

	/**
	 * getter llista
	 * @return llista
	 */
	public Client[] getLlista() {
		return llista;
	}

	/**
	 * setter llista
	 * @param llista
	 */
	public void setLlista(Client[] llista) {
		nClients = 0;
		for (int i = 0; (i < this.llista.length) && (i < llista.length) && (llista[i] != null); i++) {
			this.afegirClient(llista[i]); 
		}
	}

	/**
	 * getter mida
	 * @return mida
	 */
	public int getMida() {
		return mida;
	}
	
	/**
	 * Metode que retorna totes les dades de cada client emmagatzemat a la LlistaClients.
	 * @return frase de tipus String.
	 */
	public String toString() {
		if (nClients == 0) {
			return "";
		}else {
			String frase="numero de clients = "+nClients+"\n";
			for (int i=0;i<nClients;i++) {
				frase+=llista[i].toString()+"\n";
			}
			return frase;
		}
	}
	
	/**
	 * Metode que retorna un client de la llista a partir del DNI
	 * Si no el troba retorna (null)
	 * @param dni
	 * @return llista[i]
	 */
	public Client buscarClient(String dni ) {
		int i = buscarClient_id(dni);
		if (i != -1) return llista[i];
		else return null;
	}
	
	/**
	 * Metode que afegeix un client a la llista(Comprova duplicats)
	 * @param client
	 */
	public void afegirClient(Client client) {
/*		boolean trobat = false;
		int i = 0;
		if(mida>nClients) {
			while (( i<nClients) &&(!trobat)) {
				if(llista[i].getDni() != client.getDni()) trobat = true;
				i++;
			}
			if (trobat) {
			*/llista[nClients] = client.copia();
			nClients++;
			//}
		//}
	}
	
	/**
	 * Metode que elimina un Client de la LlistaClients a partir del DNI
	 * @param dni
	 */
	public void eliminarClient(String dni) {
		int aux = buscarClient_id(dni);
		if (aux != -1) {
			for(int i=aux;i<nClients-1;i++) {
				llista[i]=llista[i+1];
			}
			nClients--;
		}
	}
	
	/**
	 * Metode que retorna la posicio 'i' de la LlistaClients d'un client a partir del DNI.
	 * Si no el troba retorna (-1)
	 * @param dni
	 * @return
	 */
	private int buscarClient_id(String dni) {
		for(int i = 0;i < nClients;i++) {
			if(dni.equals(llista[i].getDni())) return i;
		}
		return -1;
	}
}
