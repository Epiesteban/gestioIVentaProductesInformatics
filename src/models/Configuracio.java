package models;

public class Configuracio extends Producte {
	//classe filla de productes
	/**
	 * Declaració de variables
	 */
	
	private final int mida = 500;
	private Software softwares[];
	private Hardware hardwares[];
	
	/*Constructor buit*/
	public Configuracio (String nom, float preu, int estoc) {
		super(nom, preu, estoc);
		softwares=new Software[mida];
		softwares=new Software[mida];
	}
	
	/*Constructor*/
	public Configuracio (String nom, float preu, int estoc, Software[] softwares, Hardware[] hardwares) {
		super(nom, preu, estoc);
		this.hardwares=hardwares;
		this.softwares=softwares;
	}

	public Software[] getSoftwares() {
		return softwares;
	}


	public void setSoftwares(Software[] softwares) {
		this.softwares = softwares;
	}


	public Hardware[] getHardwares() {
		return hardwares;
	}


	public void setHardwares(Hardware[] hardwares) {
		this.hardwares = hardwares;
	}
	public int numElementsHardware () {
		int i=0;
		while (hardwares[i]!=null) {
			i++;
		}
		return i;
	}


	public int numElementsSoftware () {
		int i=0;
		while (softwares[i]!=null) {
			i++;
		}
		return i;
	}
	
	/**
	 * ToSTring
	 */
	public String toString() {
		return ("Configuracio=\t[nom: "+super.getNom()+ ", Preu: "+super.getPreu()+ ", Estoc: "+ super.getEstoc()+ ", Id: "+super.getId()+ ", Software: "+softwares+ ", Hadware: "+ hardwares+ "]");
	}
}
