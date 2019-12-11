package models;

public class Hardware extends Producte{
	//filla
	public enum Tipus_hardware {CPU, MB, HDD, RAM, GPU, PERIFERIC};
	private Tipus_hardware tipus;

	public Hardware (String nom, float preu, int estoc, String tipus) {
		super(nom, preu, estoc);
		this.tipus= Tipus_hardware.valueOf(tipus);
	}
	/**
	 * Getters i setters
	 */
	public Tipus_hardware getTipus() {
		return tipus;
	}

	public void setTipus(Tipus_hardware tipus) {
		this.tipus = tipus;
	}

	/**
	 * ToString
	 */
	public String toString() {
		return ("nom:"+super.getNom()+ "\nPreu:"+super.getPreu()+ "\nEstoc:"+ super.getEstoc()+"\nTipus de hardware:"+tipus+"\nId: "+super.getId()+ "\n");
	}
}
