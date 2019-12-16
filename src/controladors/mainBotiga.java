package controladors;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import models.*;

import java.awt.peer.ScrollbarPeer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.xml.transform.Source;

public class mainBotiga {

	static Scanner teclat=new Scanner(System.in);


	public static void main(String[] args) throws FileNotFoundException {
		LlistaClients llista_clients = new LlistaClients();
		LlistaProductes llista_productes = new LlistaProductes();
		LlistaComandes llista_comandes = new LlistaComandes();
		llegirFitxerClients(llista_clients);
		llegirFitxerProductes(llista_productes);
		int op=0;
		do {
			menu();
			op = teclat.nextInt();

			switch (op) {
			case 1:{

			}break;
			case 2:{
			}break;
			case 3:{
				afegirConfiguracio(llista_productes);
			}break;
			case 4:{
				System.out.println("\nHas escollit: donar d'alta un client");
				altaClient(llista_clients);
			}break;
			case 5:{
			}break;
			case 6:{
			}break;
			case 7:{
			}break;
			case 8:{
			}break;
			case 9:{
			}break;
			case 10:{
			}break;
			case 11:{
			}break;
			case 12:{
				System.out.println("Has decidit sortir del programa. \nAdeu i fins aviat!");
			}break;
			default:{
				System.out.println("Vigila! Has introduit un numero erròni");
			}
			}
		}
		while (op!=12);

		teclat.close();
	}

	/**
	 * GUARDAR FITXERS
	 * @param llista_clients
	 * @param llista_productes
	 * @param llista_comandes
	 */
	private static void guardarFitxers(LlistaClients llista_clients, LlistaProductes llista_productes, LlistaComandes llista_comandes) {

	}
	/**
	 * FUNCIONS PER LLEGIR I ESCRIURE FITXERS 
	 */
	/**
	 * 
	 * @param llista
	 * @throws FileNotFoundException
	 */

	private static void llegirFitxerClients(LlistaClients llista) throws FileNotFoundException {
		String result="";
		Scanner f=new Scanner(new File("clients.txt"));

		while (f.hasNextLine()) {
			result= f.nextLine();
			String[] separador = result.split("\\*");
			llista.afegirClient(new Client(separador[0], separador[1], separador[2]));
		}
		f.close();
	}

	/**
	 * Mètode per a llegir les dades d'un fitxer de text i guardar les dades dins la llista_productes
	 * @param llista
	 * @throws FileNotFoundException
	 */
	private static void llegirFitxerProductes(LlistaProductes llista) throws FileNotFoundException {
		String result="";
		Scanner f=new Scanner(new File("productes.txt"));
		while (f.hasNextLine()) {
			result=f.next();
			String[] separador = result.split("\\*");
			if(separador[0].equalsIgnoreCase("S")) {
				String nom=separador[1];
				float preu=Float.parseFloat(separador[2]);
				int estoc=Integer.parseInt(separador[3]);
				SO sist=mirarSO(separador[4]);
				Software aux_s=new Software(nom, preu, estoc, sist);
				llista.afegirProducte(aux_s);//Al afegir producte, no hem de posar instanceof per a que ho guardi depenent el tipus que sigui?
			}else if (separador[0].equalsIgnoreCase("H")) {
				String nom=separador[1];
				float preu=Float.parseFloat(separador[2]);
				int estoc=Integer.parseInt(separador[3]);
				Tipus_hardware tipus=mirarTipusHardware(separador[4]);
				Hardware aux_h=new Hardware(nom, preu, estoc, tipus);
				llista.afegirProducte(aux_h);
			}else {
				String nom=separador[1];
				float preu=Float.parseFloat(separador[2]);
				int estoc=Integer.parseInt(separador[3]);
				Hardware[] llista_h= new Hardware[100];
				Software[] llista_s=new Software[100];
				int aux=Integer.parseInt(separador[4]);
				int loc, posicio=5;
				for (int i=0;i<aux;i++) {
					loc=llista.buscarProductes(Integer.parseInt(separador[posicio+i]));
					llista_h[i]=(Hardware)llista.getLlista()[loc];
				}
				posicio=posicio+aux;
				aux=Integer.parseInt(separador[posicio]);
				posicio=posicio+1;
				for(int i=0;i<aux;i++) {
					loc=llista.buscarProductes(Integer.parseInt(separador[posicio+i]));
					llista_s[i]=(Software)llista.getLlista()[loc];
				}
				Configuracio aux_c=new Configuracio(nom, preu, estoc, llista_s, llista_h);
				llista.afegirProducte(aux_c);
			}
		}
		f.close();
	}

	
	/**
	 * Mètode per a guardar les dades de la llista de productes dins un fitxer de text
	 * @param llista
	 */
	private static void guardarFitxerProductes(LlistaProductes llista) {
		try {
			BufferedWriter bw= new BufferedWriter(new FileWriter("productes.txt"));
			String nom;
			float preu;
			int estoc;
			SO sist;
			Tipus_hardware tipus;
			
			for (int i=0;i<llista.getnElem();i++) {
				if (llista.getLlista()[i] instanceof Software) {
					bw.write("S*");
					bw.write(llista.getLlista()[i].getNom()+"*");;
					bw.write(llista.getLlista()[i].getPreu()+"*");
					bw.write(llista.getLlista()[i].getEstoc()+"*");
					bw.write(((Software)llista.getLlista()[i]).getSOString());
				}else if (llista.getLlista()[i] instanceof Hardware) {
					bw.write("H*");
					bw.write(llista.getLlista()[i].getNom()+"*");;
					bw.write(llista.getLlista()[i].getPreu()+"*");
					bw.write(llista.getLlista()[i].getEstoc()+"*");
					bw.write(((Hardware)llista.getLlista()[i]).getTipusHardwareString());
				}else {
					bw.write("C*");
					bw.write(llista.getLlista()[i].getNom()+"*");;
					bw.write(llista.getLlista()[i].getPreu()+"*");
					bw.write(llista.getLlista()[i].getEstoc()+"*");
					int numElem=(((Configuracio)llista.getLlista()[i]).numElementsHardware());
					System.out.println(numElem);
					bw.write(numElem+"*");
					for (int j=0;j<numElem;j++) {
							bw.write(((Configuracio)llista.getLlista()[i]).getHardwares()[j].getId()+"*");
					}
					numElem=(((Configuracio)llista.getLlista()[i]).numElementsSoftware());
					bw.write(numElem+"*");
					for (int j=0;j<numElem;j++) {
						if (j+1==numElem) {
							bw.write(""+((Configuracio)llista.getLlista()[i]).getSoftwares()[j].getId());
						}else {
							bw.write(((Configuracio)llista.getLlista()[i]).getSoftwares()[j].getId()+"*");
						}
					}
				}
				if (i+1!=llista.getnElem()) {
					bw.write("\n");
				}
			}
			bw.close();
		} catch (Exception e) {
			System.out.println("Hi ha hagut un problema a l'escriure al fitxer!");
		}
	}
	/**
	 * FUNCIONS PER LLEGIR/ESCRIURE FITXERS DE LLISTACOMANDES
	 */
	/**
	 * Funció per a escriure en una llista en format serialitzable 	
	 * @param llista
	 */
	private static void guardarDataSerialitzable (LlistaComandes llista) {
		ObjectOutputStream gfitxer;
		try {
			gfitxer = new ObjectOutputStream (new FileOutputStream("nomfitxer.txt"));
			gfitxer.writeObject(llista);
			gfitxer.close();
		} catch (IOException e){
			System.out.println("Error a l'hora d'escriure al fitxer");
		}
	}

	/**
	 * Funció per a llegir una llista que esta guardada en format serialitzable
	 * @param llista
	 */
	private static void llegirDataSerialitzable (LlistaComandes llista) {
		ObjectInputStream lfitxer;
		try {
			lfitxer = new ObjectInputStream (new FileInputStream("nomfitxer.ser"));
			for (int i=0; i<llista.getnumElem(); i++) {
				llista=(LlistaComandes)lfitxer.readObject();
			}
			lfitxer.close();
		} catch (IOException e) {
			System.out.println ("Error a l'hora de llegir l'arxiu");
		} catch (ClassNotFoundException e) {
			System.out.println ("Error a l'hora de buscar la llista de Comandes");
		} catch (ClassCastException e) {
			System.out.println ("Error, el format de l'arxiu no és correcte per poder-lo obrir i llegir-lo");	
		}

	}
	/**
	 * Metode auxiliar per determinar mitjançant un String quin tipus de hardware
	 * és aquest String
	 * @param x
	 * @return
	 */
	private static Tipus_hardware mirarTipusHardware (String x) {
		Tipus_hardware aux=Tipus_hardware.CPU;
		if (x.equalsIgnoreCase("CPU")) {
			aux=Tipus_hardware.CPU;
		}else if (x.equalsIgnoreCase("Case")) {
			aux=Tipus_hardware.Case;
		}else if (x.equalsIgnoreCase("Hdd")){
			aux=Tipus_hardware.HDD;
		}else if (x.equalsIgnoreCase("MoBo")) {
			aux=Tipus_hardware.MoBo;
		}else if (x.equalsIgnoreCase("Psu")) {
			aux=Tipus_hardware.PSU;
		}else {
			aux=Tipus_hardware.RAM;
		}
		return aux;
	}


	/**
	 * Metode auxiliar per determinar mitjançant un String quin sistema operaitu
	 * és aquest String
	 * @param x
	 * @return
	 */
	private static SO mirarSO(String x) {
		SO aux=SO.Linux;
		if (x.equalsIgnoreCase("Windows")) {
			aux=SO.Windows;
		}else if (x.equalsIgnoreCase("Linux")) {
			aux=SO.Linux;
		}else {
			aux=SO.MacOS;
		}
		return aux;
	}
	

	/**
	 * MENU		
	 */
	private static void menu () {
		System.out.println("\nBENVINGUT A LA BOTIGA! QUE VOLS FER?\n");
		System.out.println("1. Afegir un producte de software");
		System.out.println("2. Afegir un producte de hardware");
		System.out.println("3. Afegir una configuració completa");
		System.out.println("4. Donar d'alta un client");
		System.out.println("5. Donar de baixa un client");
		System.out.println("6. Visualitzar els productes que tenen alguna comanda");
		System.out.println("7. Modificar l'estoc");
		System.out.println("8. Visualitzar els productes que estan en estoc");
		System.out.println("9. Visualitzar els productes que formen part d'una configuració");
		System.out.println("10. Mostrar el producte amb més comandes"); 
		System.out.println("11. Consultar tots els elements d'una llista");
		System.out.println("12. Sortir");
	}	

	/**
	 * FUNCIONS DEL MENU	
	 */
	/**
	 * CASE 1
	 * @return --> 
	 */
	private static Software afegirSoftware () {
		String nom;
		float preu;
		int estoc, op=2;
		SO sist=SO.Windows;//S'HA D'INICIALITZAR AMB QUALSEVOL ENUM?


		System.out.println("Introdueix el nom:");
		nom=teclat.next();
		System.out.println("Introdueix el preu:");
		preu=teclat.nextFloat();
		System.out.println("Introdueix l'estoc:");
		estoc=teclat.nextInt();
		while (op<=1 && op>=3) {
			System.out.println("Selecciona el sistema operatiu:");
			System.out.println("1- Windows,  2-MacOS,  3-Linux");
			op=teclat.nextInt();
			switch (op) {
			case 1:{
				sist=SO.Windows;
			}break;
			case 2:{
				sist=SO.MacOS;
			}break;
			case 3:{
				sist=SO.Linux;
			}break;

			default:
				System.out.println("Has introduit un nombre erroni! Torna a provar");
			}
		}
		teclat.close();
		return(new Software(nom, preu, estoc, sist));
	}


	/**
	 * CASE 2
	 * @return -->
	 */
	private static Hardware afegirHardware () {
		String nom;
		float preu;
		int estoc, op=2;
		Tipus_hardware tipus=Tipus_hardware.CPU;//S'HA D'INICIALITZAR AMB QUALSEVOL ENUM?


		System.out.println("Introdueix el nom:");
		nom=teclat.next();
		System.out.println("Introdueix el preu:");
		preu=teclat.nextFloat();
		System.out.println("Introdueix l'estoc:");
		estoc=teclat.nextInt();
		while (op<=1 && op>=6) {
			System.out.println("Selecciona el tipus de hardware:");
			System.out.println("1- Case,  2-CPU,  3-HDD, 4-MoBo, 5-PSU, 6-RAM");
			op=teclat.nextInt();
			switch (op) {
			case 1:{
				tipus=Tipus_hardware.Case;
			}break;
			case 2:{
				tipus=Tipus_hardware.CPU;
			}break;
			case 3:{
				tipus=Tipus_hardware.HDD;
			}break;
			case 4:{
				tipus=Tipus_hardware.MoBo;
			}break;
			case 5:{
				tipus=Tipus_hardware.PSU;
			}break;
			case 6:{
				tipus=Tipus_hardware.RAM;
			}break;

			default:
				System.out.println("Has introduit un nombre erroni! Torna a provar");
			}
		}
		teclat.close();
		return(new Hardware(nom, preu, estoc, tipus));
	}

	/**
	 * CASE 3
	 * @return -->
	 */
	private static void afegirConfiguracio(LlistaProductes llista) {
		Hardware[] llista_h=new Hardware[50];
		Software[] llista_s=new Software[50];
		System.out.println("Introdueix el nom de la configuracio:");
		String nom=teclat.next();
		System.out.println("Introdueix el preu de la configuracio:");
		float preu=teclat.nextFloat();
		System.out.println("Introdueix l'estoc del producte:");
		int estoc=teclat.nextInt();
		System.out.println("A continuacio es mostraran els components de Hardware,");
		System.out.println("Escull un minim de 1 de cada tipus:");
		System.out.println("Escull un component HDD:");
		int op=0, j=0;
		while (op!=2) {
			for (int i=0; i<llista.getnElem();i++) {
				if ((llista.getLlista()[i])instanceof Hardware) {
					if ((((Hardware)llista.getLlista()[i]).getTipusHardwareString()).equalsIgnoreCase("HDD")) {
						System.out.println(i+"-"+((Hardware)llista.getLlista()[i]).toString());
					}
				}
			}
			System.out.println("Intordueix el id del HDD que vols:");
			int pos=teclat.nextInt();
			System.out.println(((Hardware)llista.getLlista()[pos]).getTipus().toString());
			llista_h[j]=((Hardware)llista.getLlista()[pos]);
			j++;
			System.out.println("Vols afegir un altre compontent HDD?");
			System.out.println("1- Si    2- No");
			op=teclat.nextInt();
		}
		System.out.println("Escull un component Case:");
		op=0;
		while (op!=2) {
			for (int i=0; i<llista.getnElem();i++) {
				if ((llista.getLlista()[i])instanceof Hardware) {
					if ((((Hardware)llista.getLlista()[i]).getTipusHardwareString()).equalsIgnoreCase("Case")) {
						System.out.println(i+"-"+((Hardware)llista.getLlista()[i]).toString());
					}
				}
			}
			System.out.println("Intordueix el id del Case que vols:");
			int pos=teclat.nextInt();
			llista_h[j]=((Hardware)llista.getLlista()[pos]);
			j++;
			System.out.println("Vols afegir un altre compontent Case?");
			System.out.println("1- Si    2- No");
			op=teclat.nextInt();
		}
		System.out.println("Escull un component RAM:");
		op=0;
		while (op!=2) {
			for (int i=0; i<llista.getnElem();i++) {
				if ((llista.getLlista()[i])instanceof Hardware) {
					if ((((Hardware)llista.getLlista()[i]).getTipusHardwareString()).equalsIgnoreCase("RAM")) {
						System.out.println(i+"-"+((Hardware)llista.getLlista()[i]).toString());
					}
				}
			}
			System.out.println("Intordueix el id del RAM que vols:");
			int pos=teclat.nextInt();
			llista_h[j]=((Hardware)llista.getLlista()[pos]);
			j++;
			System.out.println("Vols afegir un altre compontent RAM?");
			System.out.println("1- Si    2- No");
			op=teclat.nextInt();
		}
		System.out.println("Escull un component MoBo:");
		op=0;
		while (op!=2) {
			for (int i=0; i<llista.getnElem();i++) {
				if ((llista.getLlista()[i])instanceof Hardware) {
					if ((((Hardware)llista.getLlista()[i]).getTipusHardwareString()).equalsIgnoreCase("MoBo")) {
						System.out.println(i+"-"+((Hardware)llista.getLlista()[i]).toString());
					}
				}
			}
			System.out.println("Intordueix el id del MoBo que vols:");
			int pos=teclat.nextInt();
			llista_h[j]=((Hardware)llista.getLlista()[pos]);
			j++;
			System.out.println("Vols afegir un altre compontent MoBo?");
			System.out.println("1- Si    2- No");
			op=teclat.nextInt();
		}
		System.out.println("Escull un component CPU:");
		op=0;
		while (op!=2) {
			for (int i=0; i<llista.getnElem();i++) {
				if ((llista.getLlista()[i])instanceof Hardware) {
					if ((((Hardware)llista.getLlista()[i]).getTipusHardwareString()).equalsIgnoreCase("CPU")) {
						System.out.println(i+"-"+((Hardware)llista.getLlista()[i]).toString());
					}
				}
			}
			System.out.println("Intordueix el id del CPU que vols:");
			int pos=teclat.nextInt();
			llista_h[j]=((Hardware)llista.getLlista()[pos]);
			j++;
			System.out.println("Vols afegir un altre compontent CPU?");
			System.out.println("1- Si    2- No");
			op=teclat.nextInt();
		}
		System.out.println("Escull un component PSU:");
		op=0;
		while (op!=2) {
			for (int i=0; i<llista.getnElem();i++) {
				if ((llista.getLlista()[i])instanceof Hardware) {
					if ((((Hardware)llista.getLlista()[i]).getTipusHardwareString()).equalsIgnoreCase("PSU")) {
						System.out.println(i+"-"+((Hardware)llista.getLlista()[i]).toString());
					}
				}
			}
			System.out.println("Intordueix el id del PSU que vols:");
			int pos=teclat.nextInt();
			llista_h[j]=((Hardware)llista.getLlista()[pos]);
			j++;
			System.out.println("Vols afegir un altre compontent PSU?");
			System.out.println("1- Si    2- No");
			op=teclat.nextInt();
		}
		op=0;j=0;
		while (op!=2) {
			for (int i=0; i<llista.getnElem();i++) {
				if ((llista.getLlista()[i])instanceof Software) {
					System.out.println(i+"-"+((Software)llista.getLlista()[i]).toString());
				}
			}
			System.out.println("Introdueix el id del SO que vols:");
			int pos=teclat.nextInt();
			llista_s[j]=((Software)llista.getLlista()[pos]);
			j++;
			System.out.println("Vols afegir un altre SO?");
			System.out.println("1- Si    2- No");
			op=teclat.nextInt();
		}
		llista.afegirProducte(new Configuracio(nom, preu, estoc, llista_s, llista_h));
	}

	/**
	 * CASE 4
	 */
	private static void altaClient (LlistaClients llista_cl) {

		System.out.println("Introdueix el dni del client:");
		teclat.nextLine();
		String dni = teclat.nextLine();
		System.out.println("Introdueix el correu electronic del client:");
		String correu = teclat.nextLine();
		System.out.println("\nIntrodueix la direccio del client:");
		String direccio = teclat.nextLine();
		llista_cl.afegirClient(new Client (dni, correu, direccio));	
		System.out.println(llista_cl);
	}

	/**
	 * CASE 5
	 */
	private static void baixaClient () {
		String dni;
		LlistaClients  llista_cl = new LlistaClients();
		LlistaComandes llista_c = new LlistaComandes();

		System.out.println("\nIntrodueix el dni del client que es vol donar de baixa:");
		dni = teclat.nextLine();
		//llista_c.eliminarComandes(dni);
		llista_cl.eliminarClient(dni);
	}

	/**
	 * CASE 6
	 */
	private static void prodComanda () {
		boolean comandafeta = false;
		int posicio = -1;
		int j = 0;
		LlistaProductes llista_p =new LlistaProductes();
		LlistaComandes llista_c = new LlistaComandes();
		LlistaClients llista_cl = new LlistaClients();


		for (int i=0;i<llista_p.getnElem();i++) {
			for (j=0;j<llista_c.getnumElem();j++) {
				if (llista_p.getLlista()[i].equals(llista_c.getLlista()[j].getProducte())) {
					if (!comandafeta) {
						comandafeta = false;
						System.out.println("No hi ha cap comanda feta en ");	
					}else {
						comandafeta = true;  
						System.out.println("-"+ llista_cl.getLlista()[posicio].toString());
					}
				}
			}
		}
	}

	/**
	 * CASE 7
	 * @param l --> 
	 */
	private static void modificarEstoc (LlistaProductes l) {
		int i, nouEstoc;
		System.out.println(l.toString());
		System.out.println("Introdueix el numero del producte del qual vols modificar l'estoc:");
		i=teclat.nextInt();
		System.out.println("Quin es el nou estoc d'aquest producte?");
		nouEstoc=teclat.nextInt();
		if (l.modificarEstoc(i, nouEstoc)==true) {
			System.out.println("S'ha modificat correctament l'estoc a:"+nouEstoc);
		}else {
			System.out.println("No s'ha realitzat l'operacio");
		}
	}

	/**
	 * CASE 8
	 * @param l :
	 * @return-->
	 */
	private static String productesEstoc(LlistaProductes l) {
		String aux="";
		for (int i=0; i<l.getnElem();i++) {
			if (!(l.getLlista()[i] instanceof Configuracio)) {
				aux=aux+l.getLlista()[i].toString();
			}
		}
		return aux;
	}

	/**
	 * CASE 9
	 * @param l :
	 * @return-->
	 */
	private static String productesConfiguracio(LlistaProductes l) {
		String aux="";
		for (int i=0;i<l.getnElem();i++) {
			if (l.getLlista()[i] instanceof Configuracio) {
				aux=aux+l.getLlista()[i].toString();
			}
			i++;
		}
		return aux;
	}


	/**
	 * CASE 1O
	 */
	private static void mesComandes () {
		int posicio = -1;
		int nComandes = 0;
		int max = 0;
		LlistaProductes llista_p =new LlistaProductes();
		LlistaComandes llista_c = new LlistaComandes();

		for (int i=0;i<llista_p.getnElem();i++) {
			for (int j=0;j<llista_c.getnumElem();j++) {
				if (llista_p.getLlista()[i].equals(llista_c.getLlista()[j].getProducte())){
					nComandes++;
				}
			}

			if (max < nComandes) {
				posicio = i;
				max = nComandes;
			}
		}
	}


	/**
	 * CASE 11
	 */
	private static void consultarLlistes (LlistaProductes llista_p, LlistaComandes llista_c, LlistaClients llista_cl) {
		int op;
		System.out.println("\n1. Lista de productes");
		System.out.println("\n2. Lista de clients");
		System.out.println("\n3. Lista de comandes");
		System.out.println("\n4. Tornar al menu principal");
		System.out.println("\nQuina opcio vols escollir?:");
		op = teclat.nextInt();

		switch (op) {
		case 1:{
			System.out.println("\nLlISTA DE PORDUCTES:");
			System.out.println("\n"+llista_p.toString());
		}break;
		case 2:{
			System.out.println("\nLlISTA DE CLIENTS:");
			System.out.println("\n"+llista_cl.toString());
		}break;
		case 3:{
			System.out.println("\nLlISTA DE COMANDES:");
			System.out.println("\n"+llista_c.toString());
		}break;
		case 4:{
			System.out.println("\nHas escollit tornar al menu principal.");
			menu();
		}break;
		default:
			System.out.println("\nError! Introdueix un numero correcte.");
			break;
		}
	}

}



