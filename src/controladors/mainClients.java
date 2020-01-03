package controladors;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;
import InterficeGrafica.*;
import javax.swing.*;

import models.Client;
import models.Configuracio;
import models.Hardware;
import models.LlistaClients;
import models.LlistaComandes;
import models.LlistaProductes;
import models.Software;

public class mainClients {
	public static LlistaClients llista_clients = new LlistaClients();
	public static LlistaProductes llista_productes = new LlistaProductes();
	public static LlistaComandes llista_comandes = new LlistaComandes();
	
	public static void main(String[] args) {
		llegirFitxerClients();
		llegirFitxerProductes();
		llegirDataSerialitzable();
		new Missatges();
		new FinestraMenuClient().setVisible(true);
	}
	
	/**
	 * Llegir fitxer clients
	 *
	 */
	private static void llegirFitxerClients() {
		String result="";
		try {
			Scanner f=new Scanner(new File("clients.txt"));
			while (f.hasNextLine()) {
				result= f.nextLine();
				String[] separador = result.split("\\*");
				llista_clients.afegirClient(new Client(separador[0], separador[1], separador[2]));
			}
			f.close();
		}catch (FileNotFoundException e) {
			System.out.println("No existeix el fitxer.");		
		}
		catch(Exception e) {
			System.out.println("Hi ha hagut algun error en la lectura de l'arxiu o al afegir els elements a la llista.\n");
		}
	}
	
	/**
	 * Metode per a llegir les dades d'un fitxer de text i guardar les dades dins la llista_productes
	 * @throws FileNotFoundException
	 */
	private static void llegirFitxerProductes() {
		String result="";
		try {
			Scanner f=new Scanner(new File("productes.txt"));
			while (f.hasNextLine()) {
				result=f.nextLine();
				String[] separador = result.split("\\*");
				if(separador[0].equalsIgnoreCase("S")) {
					String nom=separador[1];
					float preu=Float.parseFloat(separador[2]);
					int estoc=Integer.parseInt(separador[3]);
					String sist=separador[4];
					Software aux_s=new Software(nom, preu, estoc, sist);
					llista_productes.afegirProducte(aux_s);
				}else if (separador[0].equalsIgnoreCase("H")) {
					String nom=separador[1];
					float preu=Float.parseFloat(separador[2]);
					int estoc=Integer.parseInt(separador[3]);
					String tipus=separador[4];
					Hardware aux_h=new Hardware(nom, preu, estoc, tipus);
					llista_productes.afegirProducte(aux_h);
				}else {
					String nom=separador[1];
					float preu=0;// El precio se calcula de los productos de la configuracion
					int estoc=Integer.parseInt(separador[2]);
					Hardware[] llista_h= new Hardware[100];
					int cont_h =0, cont_s = 0, cont_aux_h = 0, cont_aux_s=0; //contador llistes arrays
					Software[] llista_s=new Software[100];
					Integer[] llista_auxIntegers_h = new Integer[100];
					Integer[] llista_auxIntegers_s = new Integer[100];
					int posicio = 3;
					if(separador[posicio].equalsIgnoreCase("H")) {
						posicio++;
						String aux_r= separador[posicio];
						while (!aux_r.equals("S")) {
							llista_auxIntegers_h[cont_aux_h] = Integer.parseInt(aux_r);
							cont_aux_h++;
							posicio++;
							aux_r= separador[posicio];
						}
						posicio++;
						while (posicio<separador.length) {
							aux_r= separador[posicio];
							llista_auxIntegers_s[cont_aux_s] = Integer.parseInt(aux_r);
							cont_aux_s++;
							posicio++;
						}
						int cont_aux_ultra2 = 0;
						boolean trobat = false;

						while (!trobat) {
							if (llista_auxIntegers_s[cont_aux_ultra2] == null) {
								trobat =true;
							}
							else {
								cont_aux_ultra2++;
							}
						}
						for (int i = 0; i < cont_aux_ultra2; i++) {
							llista_s[cont_s] = (Software)llista_productes.buscarProducte(llista_auxIntegers_s[i]);
							preu+=llista_s[cont_s].getPreu();
							cont_s++;
						}
						trobat=false;
						cont_aux_ultra2=0;
						while (!trobat) {
							if (llista_auxIntegers_h[cont_aux_ultra2] == null) {
								trobat =true;
							}
							else {
								cont_aux_ultra2++;
							}
						}
						for (int i = 0; i < cont_aux_ultra2; i++) {
							llista_h[cont_h] = (Hardware)llista_productes.buscarProducte(llista_auxIntegers_h[i]);
							preu+=llista_h[cont_h].getPreu();
							cont_h++;
						}
						Configuracio aux_c=new Configuracio(nom, preu, estoc, llista_s, llista_h);
						llista_productes.afegirProducte(aux_c);
					}
				}
			}
			f.close();
		}catch (FileNotFoundException e) {
			System.out.println("No existeix el fitxer.");		
		}
		catch(Exception e) {
			System.out.println("Hi ha hagut algun error en la lectura de l'arxiu o al afegir els elements a la llista.\n");
			e.printStackTrace();
		}
	}
	
	/**
	 * Funci� per a llegir una llista que esta guardada en format serialitzable
	 */
	private static void llegirDataSerialitzable () {
		ObjectInputStream lfitxer;
		try {
			lfitxer = new ObjectInputStream (new FileInputStream("comandes.ser"));
			llista_comandes=(LlistaComandes)lfitxer.readObject();
			lfitxer.close();
		} catch (IOException e) {
			System.out.println ("Error a l'hora de llegir l'arxiu");
		} catch (ClassNotFoundException e) {
			System.out.println ("Error a l'hora de buscar la llista de Comandes");
		} catch (ClassCastException e) {
			System.out.println ("Error, el format de l'arxiu no �s correcte per poder-lo obrir i llegir-lo");	
		}

	}
	
}


