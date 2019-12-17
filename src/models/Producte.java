package models;

import java.io.Serializable;

public class Producte implements Serializable {

	private static final long serialVersionUID = 1L;
// pare
	/** declaracio de variables*/
	
	private String nom;
	private float preu;
	private int estoc;
	private int id;
	private static int cont_id=1;
	
	/** constructor
	 * 
	 */
	public Producte (String nom, float preu,int estoc) {
		this.nom= nom;
		this.preu= preu;
		this.estoc= estoc;
		this.id=cont_id++;
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
		if (estoc >= 0) this.estoc = estoc;
	}

	public int getId() {
		return id;
	}

	/**
	 * To string 
	 */
	@Override
	public String toString() {
		return "Producte [Nom= " + nom + ", Preu= " + preu + ", Estoc= " + estoc + ", Identificador= " + id + "]";
	}
}
