package models;

/**
 * Classe que implementa un client
 * 
 * @author Joel Panisello
 *
 */
public class Client {
	
	//ATRIBUTS
	private String dni, correu, adresa;
	//private LlistaComandes llistaComandes;

	/**
	 * Metode constructor classe Client
	 * @param dni
	 * @param correu
	 * @param adresa
	 */
	public Client(String dni, String correu, String adresa) {
		this.dni = dni;
		this.correu = correu;
		this.adresa = adresa;
	}

	/**
	 * Getter dni
	 * @return dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * setter dni
	 * @param dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * getter correu
	 * @return
	 */
	public String getCorreu() {
		return correu;
	}

	/**
	 * setter correu
	 * @param correu
	 */
	public void setCorreu(String correu) {
		this.correu = correu;
	}

	/**
	 * getter adreça
	 * @return
	 */
	public String getAdresa() {
		return adresa;
	}

	/**
	 * setter adreça
	 * @param adresa
	 */
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	/**
	 * Metode que crea una copia de la instancia.
	 * @return new Clients(dni, correu, adresa)
	 */
	public Client copia() {
		return  new Client(dni, correu, adresa);
	}
	
	/**
	 * metode toString de Client
	 */
	@Override
	public String toString() {
		return "Client [dni=" + dni + ", correu=" + correu + ", adresa=" + adresa + "]";
	}
	
}
