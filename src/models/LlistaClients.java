package models;

public class LlistaClients {

	private int nClients;
	private Clients [] llista;
	private final int mida = 50;
	
	public LlistaClients() {
		nClients = 0;
		llista = new Clients [mida];
	}

	public int getnClients() {
		return nClients;
	}

	public void setnClients(int nClients) {
		this.nClients = nClients;
	}

	public Clients[] getLlista() {
		return llista;
	}

	public void setLlista(Clients[] llista) {
		nClients = 0;
		for (int i = 0; (i < this.llista.length) && (i < llista.length) && (llista[i] != null); i++) {
			this.afegirClient(llista[i]); 
		}
	}

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
	
	public Clients buscarClient(String dni ) {
		int i = buscarClient_id(dni);
		if (i != -1) return llista[i];
		else return null;
	}
	
	public void afegirClient(Clients client) {
		boolean trobat = false;
		int i = 0;
		if(mida>nClients) {
			while (( i<nClients) &&(!trobat)) {
				if(llista[i].getDni()!=client.getDni()) trobat = true;
				i++;
			}
			if (trobat) {
			llista[nClients] = client;
			nClients++;
			}
		}
	}
	
	public void eliminarClient(String dni) {
		int aux = buscarClient_id(dni);
		if (aux != -1) {
			llista[aux].eliminarComandes();
			for(int i=aux;i<nClients-1;i++) {
				llista[i]=llista[i+1];
			}
			nClients--;
		}
	}
	private int buscarClient_id(String dni) {
		for(int i = 0;i < nClients;i++) {
			if(dni.equals(llista[i].getDni())) return i;
		}
		return -1;
	}
}
