package models;

public class Software extends Producte {
	//filla

	public enum SO {WINDOWS, MACOS, LINUX};
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
		return ( "Software=\t[Nom: "+super.getNom()+", Preu: "+super.getPreu()+ ", Estoc: "+super.getEstoc()+ ", Sistema operatiu: "+sistema_operatiu+ ", Identificador: "+super.getId()+"]");
	}
}
