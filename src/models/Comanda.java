package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import models.Client;
/**
 * CLASSE COMANDA 
 * @author Xènia Fuentes Font
 *
 */
public class Comanda {
	private Date data;
	private LlistaProductes llista_p;
	private String identificador;
	private Client client;
	private float preuComanda;
	private static int numCorrelatiu = 1;

	/**
	 * Constructor 
	 * @param producte : producte de la comanda
	 * @param dni : dni del client que realitza una comanda 
	 */
	public Comanda (Client client){ 
		this.client = client;
		this.preuComanda = 0;
		this.llista_p= new LlistaProductes();
		this.data =  Calendar.getInstance().getTime();
		this.identificador = client.getDni() + numCorrelatiu;
		numCorrelatiu++;

	}

	public Comanda copia () {
		return (new Comanda (client)); //no estará bien
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
	 * GETTER CLIENT
	 * @return client
	 */
	public Client getClient() {
		return client;
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
	 * Getter numero correlatiu
	 * @return
	 */
	public static int getNumCorrelatiu () {
		return numCorrelatiu;
	}
	/**
	 * 	Getter preuComanda
	 * @return preuComanda
	 */
	public float getPreuComanda() {
		return preuComanda;
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
	 * Setter numero correlatiu
	 * @return
	 */
	public static void setNumCorrelatiu (int numCorrelatiu) {
		Comanda.numCorrelatiu = numCorrelatiu;
	}
	/**
	 * Setter preuComanda
	 * @param preuComanda
	 */
	public void setPreuComanda(float preuComanda) {
		this.preuComanda = preuComanda;
	}
	
	//METODES
	/**
	 * Funcio per afegir un producte a la llista de productes de la comanda
	 * @param producte
	 */
	public void afegirProducteComanda (Producte producte) {
		llista_p.afegirProducte(producte);
		producte.setEstoc(producte.getEstoc()-1);
	}
	
	/**
	 *	Funcio per comprovar si existeix un producte a una comanda
	 * @param producte
	 * @return existeix
	 */
	public boolean existeixProducte(Producte producte) {
		for (int i = 0; i < llista_p.getnElem(); i++) {
			if(llista_p.getLlista()[i].getId()==producte.getId()) {
				return true;
			}
		}
		return false;
	}
	
	public float calculaPreuComanda() {
		preuComanda=0;
		for (int i = 0; i < llista_p.getnElem(); i++) {
			preuComanda += llista_p.getLlista()[i].getPreu()*llista_p.getLlista()[i].getEstoc();
		}
		return preuComanda;
	}

	@Override
	public String toString() {
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		return "\nData de la reserva: " +dateformat.format(data)+ 
				"\nProductes: " + llista_p.toString() +
				"\nIdentificador: " + identificador+
				"\nPreuComanda: "+preuComanda;
	}

}
