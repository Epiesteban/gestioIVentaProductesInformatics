package models;

public class Software extends Producte {
	//filla

	public enum SO {Windows, MacOS, Linux};
	private SO sistema_operatiu;

	public Software (String nom, float preu, int estoc, String sistema_operatiu) {
		super(nom, preu, estoc);
		this.sistema_operatiu = SO.valueOf(sistema_operatiu) ;
	}
	/**Setters i getters
	 * 
	 */

	public SO getSistema_operatiu() {
		return sistema_operatiu;
	}


	public void setSistema_operatiu(SO sistema_operatiu) {
		this.sistema_operatiu = sistema_operatiu;
	}
	/**
	 * To string
	 */
	public String toString() {
		return ( "nom:"+super.getNom()+"\nPreu:"+super.getPreu()+ "\nEstoc:"+super.getEstoc()+ "\nSistema operatiu:"+sistema_operatiu+ "\n");
	}
}
