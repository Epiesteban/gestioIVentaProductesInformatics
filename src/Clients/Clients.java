package Clients;
/**
 * Classe que implementa un client
 * 
 * @author Joel Panisello
 *
 */
public class Clients {
	
	//ATRIBUTS
	private String dni, correu, adresa;

	public Clients(String dni, String correu, String adresa) {
		this.dni = dni;
		this.correu = correu;
		this.adresa = adresa;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCorreu() {
		return correu;
	}

	public void setCorreu(String correu) {
		this.correu = correu;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	@Override
	public String toString() {
		return "Clients [dni=" + dni + ", correu=" + correu + ", adresa=" + adresa + "]";
	}
	
	
	
	
}
