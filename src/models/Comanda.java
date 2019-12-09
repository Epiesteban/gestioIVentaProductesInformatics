package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * CLASSE COMANDA 
 * @author Xènia Fuentes Font
 *
 */
public class Comanda {
	private Date data;
	private LlistaProductes llista_p;
	private int nComandes;
	private String identificador;
	
	private static int numCorrelatiu = 1;
	

/**
 * Constructor 
 * @param producte : producte de la comanda
 * @param nComandes : numero de comandes 
 * @param dni : dni del client que realitza una comanda 
 */
	public Comanda (String dni){ 
		this.llista_p= new LlistaProductes();
		this.data =  Calendar.getInstance().getTime();
		this.nComandes = 0;
		this.identificador = dni + numCorrelatiu;
		numCorrelatiu++;
		
	}
	
	public Comanda copia () {
		return (new Comanda (identificador)); //no estará bien
}
/**
 * GETTERS
 */
	/**
	 * Getter data
	 * @return --> retorna la data en que s'ha fet la comanda  (dia/mes/any)
	 */
	public Date getData () {
		return data;
	}
	
	/**
	 * Getter producte
	 * @return --> 
	 */
	public LlistaProductes getLlistaProductes() {
		return llista_p;
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
	public void setData (Date data) {
		this.data = data;
	}
	/**
	 * Setter producte
	 * @return
	 */
	public void setLlistaProductes(LlistaProductes llista_p) {
		this.llista_p = llista_p;
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
	DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		return "\nData de la reserva: " +dateformat.format(data)+ 
				//"\nProducte: " + producte.toString() +
				"\nComanda: " + nComandes + 
				"\nIdentificador: " + identificador;
	}
	
}
