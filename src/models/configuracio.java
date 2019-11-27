package models;

public class configuracio extends productes {
	//classe filla de productes
	
	/**
	 * DEclaració de variables
	 */
	
	
	public software[] softwares;
	public hardware[] hardwares;
	
	
	public configuracio (String nom, float preu, int estoc,software[] softwares,hardware[] hardwares, int id) {
		super(nom, preu, estoc, id);
		this.softwares= softwares;
		this.hardwares= hardwares;
	}


	public software[] getSoftwares() {
		return softwares;
	}


	public void setSoftwares(software[] softwares) {
		this.softwares = softwares;
	}


	public hardware[] getHardwares() {
		return hardwares;
	}


	public void setHardwares(hardware[] hardwares) {
		this.hardwares = hardwares;
	}
	
	/**
	 * ToSTring
	 */
	public String toString() {
		return ("nom:"+nom+ "\nPreu:"+preu+ "\nEstoc:"+ estoc+ "\nId:"+id+ "\nSoftware: "+softwares+ "\nHadware:"+ hardwares+ "\n");
	}
}
