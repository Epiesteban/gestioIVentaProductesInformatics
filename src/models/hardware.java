package models;

public class hardware extends productes{
	//filla
	private tipus_hardware tipus;
	
	public hardware (String nom, float preu, int estoc,tipus_hardware tipus) {
		super(nom, preu, estoc);
		this.tipus= tipus;
		
		
		
	}
	/**
	 * Getters i setters
	 */
	

	public tipus_hardware getTipus() {
		return tipus;
	}

	public void setTipus(tipus_hardware tipus) {
		this.tipus = tipus;
	}
	
	/**
	 * ToString
	 */
	public String toString() {
		return ("nom:"+nom+ "\nPreu:"+preu+ "\nEstoc:"+ estoc+ "\nTipus de hardware:"+tipus+ "\n");
	}
}
