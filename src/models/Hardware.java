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
	public String getTipusHardwareString () {
		String aux;
		if (tipus==Tipus_hardware.CPU) {
			aux="CPU";
		}else if (tipus==Tipus_hardware.PERIFERIC) {
			aux="Periferic";
		}else if (tipus==Tipus_hardware.HDD) {
			aux="HDD";
		}else if (tipus==Tipus_hardware.MB) {
			aux="MB";
		}else if (tipus==Tipus_hardware.GPU) {
			aux="GPU";
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
		return ("Hardware=\t[Nom: "+super.getNom()+ ", Preu: "+super.getPreu()+ ", Estoc: "+ super.getEstoc()+", Tipus de hardware: "+tipus+", Identificador: "+super.getId()+ "]");
	}
}
