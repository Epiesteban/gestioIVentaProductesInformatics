package models;

public class configuracio extends productes {
	//classe filla de productes
	
	/**
	 * DEclaració de variables
	 */
	
	
	public software[] softwares;
	public hardware[] hardwares;
	
	/*Constructor buit*/
	public configuracio (String nom, float preu, int estoc, int mida) {
		super(nom, preu, estoc);
		softwares=new software[mida];
		softwares=new software[mida];
	}
	
	/*Constructor*/
	public configuracio (String nom, float preu, int estoc, software[] softwares, hardware[] hardwares) {
		super(nom, preu, estoc);
		this.hardwares=hardwares;
		this.softwares=softwares;
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
