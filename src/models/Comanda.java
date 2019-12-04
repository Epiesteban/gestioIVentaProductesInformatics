package models;

import java.util.Calendar;

public class Comanda {
	private Calendar data= Calendar.getInstance();
	private int dia, mes, any;
	private Producte producte;
	private int nComandes;
	private String identificador;
	
	private static int numCorrelatiu = 0;
	

/**
 * Constructor 
 * @param producte
 * @param nComandes
 * @param dni
 */
	public Comanda (Producte producte, int nComandes, String dni){ //??????
		this.dia=data.get(Calendar.DATE);
		this.mes=data.get(Calendar.MONTH);
		this.any=data.get(Calendar.YEAR);
		this.producte = producte;
		this.nComandes = nComandes;
		this.identificador = dni + numCorrelatiu;
		numCorrelatiu++;
	}
/**
 * Constructor buit	
 */
	public Comanda () {
		
	}
	
	public Comanda copia () {
		return (new Comanda (producte, nComandes, identificador));
}
/**
 * GETTERS
 */
	/**
	 * Getter data
	 * @return
	 */
	public Calendar getData () {
		return data;
	}
	/**
	 * Getter dia
	 * @return
	 */
	public int dia () {
		return dia;
	}
	/**
	 * Getter mes
	 * @return
	 */
	public int mes () {
		return mes;
	}
	/**
	 * Getter any
	 * @return
	 */
	public int any() {
		return any;
	}
	/**
	 * Getter producte
	 * @return
	 */
	public Producte getProducte() {
		return producte;
	}
	/**
	 * Getter identificador
	 * @return
	 */
	public String getIdentificador() {
		return identificador;
	}
	/**
	 * Getter nComandes
	 * @return
	 */
	public int getNComandes() {
		return nComandes;
	}
	/**
	 * Getter numero correlatiu
	 * @return
	 */
	public static int getNumCorrelatiu () {
		return numCorrelatiu;
	}
/**
 * SETTERS
 */
	/**
	 * Setter data 
	 * @return
	 */
	public void setData (Calendar data) {
		this.data = data;
	}
	/**
	 * Setter dia
	 * @return
	 */
	public void setDia (int dia) {
		this.dia = dia;
	}
	/**
	 * Setter mes
	 * @return
	 */
	public void setMes (int mes) {
		this.mes = mes;
	}
	/**
	 * Setter any
	 * @return
	 */
	public void setAny(int any) {
		this.any = any;
	}
	/**
	 * Setter producte
	 * @return
	 */
	public void setProducte(Producte producte) {
		this.producte = producte;
	}
	/**
	 * Setter identificador
	 * @return
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	/**
	 * Setter nComandes
	 * @return
	 */
	public void setNComandes(int nComandes) {
		this.nComandes = nComandes;
	}
	/**
	 * Setter numero correlatiu
	 * @return
	 */
	public static void setNumCorrelatiu (int numCorrelatiu) {
		Comanda.numCorrelatiu = numCorrelatiu;
	}
	
	@Override
	public String toString() {
		return "\nData de la reserva: " +dia+"/"+mes+"/"+any+ 
				"\nProducte: " + producte.toString() +
				"\nComanda: " + nComandes + 
				"\nIdentificador: " + identificador;
	}
	
}
