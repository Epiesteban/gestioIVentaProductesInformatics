package Clients;

public class LlistaClients {

	private int nClients;
	private Clients [] llista;
	private LlistaComandes obj;
	private final int mida = 50;
	
	public LlistaClients(LlistaComandes obj) {
		nClients = 0;
		llista = new Clients [mida];
		this.obj = obj;
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

	public LlistaComandes getObj() {
		return obj;
	}

	public void setObj(LlistaComandes obj) {
		this.obj = obj;
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
	
	
	
	
}
