package controladors;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import controladors.mainBotiga;
import models.*;


public class mainClients {

	private static LlistaProductes llista_productes = new LlistaProductes();
	private static LlistaComandes llista_comandes = new LlistaComandes();
	private static LlistaClients llista_clients= new LlistaClients();


	public static void main(String[] args) {

		// RECUPERAR DADES DE FITXERS

	llegirFitxerClients();
	llegirFitxerProductes();
	llegirDataSerialitzable();

	
		//Cridem al programa principal
	//	new ProgramaPrincipal (ll_productes, ll_comandes, ll_clients);
	}
	
	/*
	 * FUNCIONS PER LLEGIR I ESCRIURE FITXERS 
	 */

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
	 * Funcio per guardar Fitxer Clients
	 * @throws IOException 
	 */
	private static void guardarFitxerClients() throws IOException  {
		BufferedWriter cl=new BufferedWriter(new FileWriter("clients.txt"));
		try {
			String frase = "";
			Client aux;
			for (int i = 0; i < llista_clients.getnClient();i++) {
				aux =  llista_clients.getLlista()[i];
				frase =aux.getDni()+"*"+aux.getCorreu()+"*"+aux.getAdresa()+"\n";
				cl.write(frase);
			}
			cl.close();
		} catch (Exception e) {
			System.out.println("Hi ha hagut un problema a l'escriure al fitxer!");
		}
	}

	/**
	 * Mètode per a llegir les dades d'un fitxer de text i guardar les dades dins la llista_productes
	 * @throws FileNotFoundException
	 */
	private static void llegirFitxerProductes() throws FileNotFoundException {
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
	 * Funcio per guardar fitxer productes
	 */
	private static void guardarFitxerProductes() {
		try {
			BufferedWriter bw= new BufferedWriter(new FileWriter("productes.txt"));
			int i=0;
			String res="";
			for (i=0;i<llista_productes.getnElem();i++) {
				if (llista_productes.getLlista()[i] instanceof Hardware) {
					res+="H*";
					res+=llista_productes.getLlista()[i].getNom()+"*";
					res+=String.valueOf(llista_productes.getLlista()[i].getPreu())+"*";
					res+=String.valueOf(llista_productes.getLlista()[i].getEstoc())+"*";
					res+=String.valueOf(((Hardware)llista_productes.getLlista()[i]).getTipusHardwareString())+"\n";
				}
			}
			for (i=0;i<llista_productes.getnElem();i++) {
				if (llista_productes.getLlista()[i] instanceof Software) {
					res+="S*";
					res+=llista_productes.getLlista()[i].getNom()+"*";
					res+=String.valueOf(llista_productes.getLlista()[i].getPreu())+"*";
					res+=String.valueOf(llista_productes.getLlista()[i].getEstoc())+"*";
					res+=String.valueOf(((Software)llista_productes.getLlista()[i]).getSOString())+"\n";
				}
			}
			for (i = 0; i < llista_productes.getnElem(); i++) {
				if (llista_productes.getLlista()[i] instanceof Configuracio) {
					res+="C*";
					res+=String.valueOf(llista_productes.getLlista()[i].getNom())+"*";
					res+=String.valueOf(llista_productes.getLlista()[i].getEstoc())+"*";
					res+="H";
					int numElem = ((Configuracio)llista_productes.getLlista()[i]).numElementsHardware();
					int j=0;
					for (j=0;j<numElem;j++) {
						res+="*"+((Configuracio)llista_productes.getLlista()[i]).getHardwares()[j].getId();
					}
					res+="*S";
					numElem = ((Configuracio)llista_productes.getLlista()[i]).numElementsSoftware();
					for (j=0;j<numElem;j++) {
						res+="*"+((Configuracio)llista_productes.getLlista()[i]).getSoftwares()[j].getId();
					}
					res+="\n";
				}
			}
			bw.write(res);
			bw.close();
		} catch (Exception e) {
			System.out.println("Hi ha hagut un problema a l'escriure al fitxer!");
		}
	}


	/**
	 * Funció per a escriure en una llista en format serialitzable 	
	 */
	private static void guardarDataSerialitzable () {
		ObjectOutputStream gfitxer;
		try {
			gfitxer = new ObjectOutputStream (new FileOutputStream("comandes.ser"));
			gfitxer.writeObject(llista_comandes);
			gfitxer.close();
		} catch (IOException e){
			System.out.println("Error a l'hora d'escriure al fitxer");
		}
	}

	/**
	 * Funció per a llegir una llista que esta guardada en format serialitzable
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
			System.out.println ("Error, el format de l'arxiu no és correcte per poder-lo obrir i llegir-lo");	
		}

	}
}