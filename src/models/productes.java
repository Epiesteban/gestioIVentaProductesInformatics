package models;

public class productes {
// pare
	/** declaracio de variables*/
	
	public String nom;
	public float preu;
	public int estoc;
	public int id;
	
	
	/** constructor
	 * 
	 */
	public productes (String nom, float preu,int estoc, int id) {
		this.nom= nom;
		this.preu= preu;
		this.estoc= estoc;
		this.id=id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public float getPreu() {
		return preu;
	}

	public void setPreu(float preu) {
		this.preu = preu;
	}

	public int getEstoc() {
		return estoc;
	}

	public void setEstoc(int estoc) {
		this.estoc = estoc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	/**To string
	 * 
	 */
	public String toString() {
		return ("nom= "+nom+ ", preu=" +preu+ ", estoc= "+ estoc+ ", id= "+id);
	}
}
