package models;

public class Hardware extends Producte{
	//filla
	private Tipus_hardware tipus;
	
	public Hardware (String nom, float preu, int estoc,Tipus_hardware tipus) {
		super(nom, preu, estoc);
		this.tipus= tipus;
		
		
		
	}
	/**
	 * Getters i setters
	 */
	

	public Tipus_hardware getTipus() {
		return tipus;
	}
	
	public String getTipusHardwareString () {
		String aux;
		if (tipus==Tipus_hardware.CPU) {
			aux="CPU";
		}else if (tipus==Tipus_hardware.Case) {
			aux="Case";
		}else if (tipus==Tipus_hardware.HDD) {
			aux="HDD";
		}else if (tipus==Tipus_hardware.MoBo) {
			aux="MoBo";
		}else if (tipus==Tipus_hardware.PSU) {
			aux="PSU";
		}else {
			aux="RAM";
		}
		return aux;
	}

	public void setTipus(Tipus_hardware tipus) {
		this.tipus = tipus;
	}
	
	/**
	 * ToString
	 */
	public String toString() {
		return ("nom:"+nom+ "\nPreu:"+preu+ "\nEstoc:"+ estoc+ "\nTipus de hardware:"+tipus+ "\n");
	}
}
