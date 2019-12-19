package controladors;
import java.io.*;
import models.*;
import models.Hardware.Tipus_hardware;
import models.Software.SO;

import java.util.InputMismatchException;
import java.util.Scanner;

public class mainBotiga {

	static Scanner teclat=new Scanner(System.in);
	static LlistaClients llista_clients = new LlistaClients();
	static LlistaProductes llista_productes = new LlistaProductes();
	static LlistaComandes llista_comandes = new LlistaComandes();
	public static void main(String[] args) throws IOException {


		llegirFitxerClients();
		llegirFitxerProductes();
		llegirDataSerialitzable();


		// Creacio d'instancies per comprovar que els metodes funcionen correctament.
		//		llista_productes.afegirProducte(new Software("hola", 65, 26, "WINDOWS"));
		//		llista_productes.afegirProducte(new Software("adios", 65, 26, "LINUX"));
		//		llista_productes.afegirProducte(new Software("met", 65, 26, "MACOS"));
		//		
		//		llista_productes.afegirProducte(new Hardware("hola2", 25, 26, "PERIFERIC"));
		//		llista_productes.afegirProducte(new Hardware("adios2", 86, 26, "CPU"));
		//		llista_productes.afegirProducte(new Hardware("met2", 15, 26, "RAM"));
		//		llista_productes.afegirProducte(new Hardware("hola3", 25, 26, "HDD"));
		//		llista_productes.afegirProducte(new Hardware("adios3", 85, 26, "GPU"));
		//		llista_productes.afegirProducte(new Hardware("met3", 16, 26, "MB"));
		//		llista_productes.afegirProducte(new Hardware("orde", 25, 26, "PERIFERIC"));
		//		llista_productes.afegirProducte(new Hardware("ades2", 8, 26, "CPU"));
		//		llista_productes.afegirProducte(new Hardware("joel", 18, 26, "RAM"));
		//		llista_productes.afegirProducte(new Hardware("roser3", 5, 26, "HDD"));
		//		llista_productes.afegirProducte(new Hardware("xenia3", 95, 26, "GPU"));
		//		llista_productes.afegirProducte(new Hardware("johnny", 15, 26, "MB"));


		//				llista_comandes.afegirComanda(new Comanda(llista_clients.getLlista()[0]));
		//				llista_comandes.getLlista()[0].afegirProducteComanda(llista_productes.getLlista()[1]);
		//				llista_comandes.getLlista()[0].afegirProducteComanda(llista_productes.getLlista()[0]);
		//				llista_comandes.getLlista()[0].afegirProducteComanda(llista_productes.getLlista()[1]);
		//				llista_comandes.getLlista()[0].afegirProducteComanda(llista_productes.getLlista()[1]);
		//		
		//				llista_comandes.afegirComanda(new Comanda(llista_clients.getLlista()[1]));
		//				llista_comandes.getLlista()[1].afegirProducteComanda(llista_productes.getLlista()[2]);
		//				llista_comandes.getLlista()[1].afegirProducteComanda(llista_productes.getLlista()[2]);
		//				llista_comandes.getLlista()[1].afegirProducteComanda(llista_productes.getLlista()[2]);
		//				llista_comandes.getLlista()[1].afegirProducteComanda(llista_productes.getLlista()[0]);

		int op=0;
		do {
			menu();
			try {
				op = teclat.nextInt();
			} catch (InputMismatchException e) {
				op = -1;				// nou valor enter de op per impedir entrar al bucle
				teclat.nextLine();		//limpiar buffer
			}

			switch (op) {
			case 1:{
				System.out.println("\nHas escollit: afegir un producte de software ");
				afegirSoftware();
			}break;
			case 2:{
				System.out.println("\nHas escollit: afegir un producte de hardware");
				afegirHardware();
			}break;
			case 3:{
				System.out.println("\nHas escollit: afegir una configuració completa");
				afegirConfiguracio();
			}break;
			case 4:{
				System.out.println("\nHas escollit: donar d'alta un client");
				altaClient();
			}break;
			case 5:{
				System.out.println("\nHas escollit: donar de baixa a un client");
				baixaClient();
			}break;
			case 6:{
				System.out.println("\nHas escollit: treure un llistat de tots els productes que tenen alguna comanda (amb les dades del client) ");
				prodComanda();
			}break;
			case 7:{
				System.out.println("\nHas escollit: modificar l'estoc de qualsevol dels productes que s'han donat d'alta a partir del seu identificador");
				modificarEstoc();
			}break;
			case 8:{
				System.out.println("\nHas escollit: treure un llistat de tots els productes que tenen estic >= 1, indicant el seu estoc");
				if (!productesEstoc().equals("")) System.out.println(productesEstoc());
				else System.out.println("No hi ha cap producte amb estoc >= 1.");
			}break;
			case 9:{
				System.out.println("\nHas escollit: treure un llistat de tots els productes que formen part d'alguna configuracio");
				if (!productesConfiguracio().equals("")) { 
					System.out.println(productesConfiguracio());
				}
				else {
					System.out.println("No hi ha cap producte que formi part d'una configuració");	
				}
			}break;
			case 10:{
				System.out.println("\nHas escollit: mostrar el producte del qual s'han fet més comandes i indicar el numero d'aquestes");
				mesComandes();
			}break;
			case 11:{
				System.out.println("\nHas escollit: consultar tots els elements de qualsevol llista que tingueu definida");
				consultarLlistes();


			}break;
			case 12:{
				System.out.println("\nHas decidit sortir del programa.");
			}break;
			default: System.out.println("Escriu una opcio valida.");
			}
		} while (op != 12);

		do {
			System.out.println("\nVols guardar tota la informacio als fitxers?(0 = NO	1 = SI)");
			try {
				op = teclat.nextInt();
			} catch (InputMismatchException e) {
				op = -1;
				teclat.nextLine();	//limpiar buffer
			}

		}while(op != 0 && op != 1);

		if (op == 1) {
			guardarFitxerClients();
			// guardarFitxerProductes(llista_productes);
			guardarDataSerialitzable();
		}
		System.out.println("\nAdeu, fins aviat!");
		teclat.close();
		System.exit(0);
	}


	/*
	 * FUNCIONS PER LLEGIR I ESCRIURE FITXERS 
	 */

	/**
	 * Llegir fitxer clients
	 * @param llista
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
	 * @param llista_clients
	 * @throws IOException 
	 */
	private static void guardarFitxerClients() throws IOException  {
		BufferedWriter cl=new BufferedWriter(new FileWriter("clients.txt"));
		String frase = "";
		Client aux;
		for (int i = 0; i < llista_clients.getnClient();i++) {
			aux =  llista_clients.getLlista()[i];
			frase =aux.getDni()+"*"+aux.getCorreu()+"*"+aux.getAdresa()+"\n";
			cl.write(frase);
		}
		cl.close();
	}

	/**
	 * Mètode per a llegir les dades d'un fitxer de text i guardar les dades dins la llista_productes
	 * @param llista
	 * @throws FileNotFoundException
	 */
	private static void llegirFitxerProductes() throws FileNotFoundException {
		String result="";
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
	}

	/**
	 * Funcio per guardar fitxer productes
	 * @param llista_p
	 */
	private static void guardarFitxerProductes() {
		try {
			BufferedWriter bw= new BufferedWriter(new FileWriter("productes.txt"));
			String nom;
			float preu;
			int estoc;
			SO sist;
			Tipus_hardware tipus;

			for (int i=0;i<llista_productes.getnElem();i++) {
				if (llista_productes.getLlista()[i] instanceof Software) {
					bw.write("S*");
					bw.write(llista_productes.getLlista()[i].getNom()+"*");;
					bw.write(llista_productes.getLlista()[i].getPreu()+"*");
					bw.write(llista_productes.getLlista()[i].getEstoc()+"*");
					bw.write(((Software)llista_productes.getLlista()[i]).getSOString());
				}else if (llista_productes.getLlista()[i] instanceof Hardware) {
					bw.write("H*");
					bw.write(llista_productes.getLlista()[i].getNom()+"*");;
					bw.write(llista_productes.getLlista()[i].getPreu()+"*");
					bw.write(llista_productes.getLlista()[i].getEstoc()+"*");
					bw.write(((Hardware)llista_productes.getLlista()[i]).getTipusHardwareString());
				}else {
					bw.write("C*");
					bw.write(llista_productes.getLlista()[i].getNom()+"*");;
					bw.write(llista_productes.getLlista()[i].getPreu()+"*");
					bw.write(llista_productes.getLlista()[i].getEstoc()+"*");
					int numElem=(((Configuracio)llista_productes.getLlista()[i]).numElementsHardware());
					System.out.println(numElem);
					bw.write(numElem+"*");
					for (int j=0;j<numElem;j++) {
						bw.write(((Configuracio)llista_productes.getLlista()[i]).getHardwares()[j].getId()+"*");
					}
					numElem=(((Configuracio)llista_productes.getLlista()[i]).numElementsSoftware());
					bw.write(numElem+"*");
					for (int j=0;j<numElem;j++) {
						if (j+1==numElem) {
							bw.write(""+((Configuracio)llista_productes.getLlista()[i]).getSoftwares()[j].getId());
						}else {
							bw.write(((Configuracio)llista_productes.getLlista()[i]).getSoftwares()[j].getId()+"*");
						}
					}
				}
				if (i+1!=llista_productes.getnElem()) {
					bw.write("\n");
				}
			}
			bw.close();
		} catch (Exception e) {
			System.out.println("Hi ha hagut un problema a l'escriure al fitxer!");
		}
	}


	/**
	 * Funció per a escriure en una llista en format serialitzable 	
	 * @param llista
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
	 * @param llista
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
	 * CASE 1: Afegir Software
	 * @param llista_p
	 */
	private static void afegirSoftware () {
		String nom;
		float preu;
		int estoc, op=0;
		String sist = "";

		try {
			System.out.println("Introdueix el nom:");
			nom=teclat.next();
			System.out.println("Introdueix el preu:");
			preu=teclat.nextFloat();
			System.out.println("Introdueix l'estoc:");
			estoc=teclat.nextInt();

			op = 0;
			while (op < 1 || op > 3) {
				System.out.println("Selecciona el sistema operatiu:");
				System.out.println("1- WINDOWS,  2-MACOS,  3-LINUX");
				op=teclat.nextInt();
				switch (op) {
				case 1:
					sist= "WINDOWS";
					break;
				case 2:
					sist= "MACOS";
					break;
				case 3:
					sist= "LINUX";
					break;
				default:
					System.out.println("Has introduit un nombre erroni! Torna a provar");
				}

			}
			llista_productes.afegirProducte(new Software(nom, preu, estoc, sist));
		}catch(InputMismatchException e){
			System.out.println("Nom/preu/estoc incorrecte");
			teclat.nextLine();		}
	}

	/**
	 * CASE 2: Afegir un Hardware
	 * @param llista_p
	 */
	private static void afegirHardware () {
		String nom;
		float preu;
		int estoc, op=2;
		String tipus= "";

		try {

			System.out.println("Introdueix el nom:");
			nom=teclat.next();
			System.out.println("Introdueix el preu:");
			preu=teclat.nextFloat();
			System.out.println("Introdueix l'estoc:");
			estoc=teclat.nextInt();

			do {
				System.out.println("Selecciona el tipus de hardware:");
				System.out.println("1- CPU,  2-MB,  3-HDD, 4-RAM, 5-GPU, 6-PERIFERIC");
				op=teclat.nextInt();
				switch (op) {
				case 1: 
					tipus = "CPU";
					break;
				case 2:
					tipus = "MB";
					break;
				case 3:
					tipus = "HDD";
					break;
				case 4:
					tipus = "RAM";
					break;
				case 5:
					tipus = "GPU";
					break;
				case 6:
					tipus = "PERIFERIC";
					break;
				default:
					System.out.println("Has introduit un nombre erroni! Torna a provar");
				}
			}while(op<1 || op>6);
			llista_productes.afegirProducte(new Hardware(nom, preu, estoc, tipus));
		} catch(InputMismatchException e) {
			System.out.println("Dades introduides erronies, torni a escollir una opcio");
			teclat.nextLine();
		}
	}

	/**
	 * CASE 3: Afegir una nova configuracio
	 * @param llista_p
	 */
	private static void afegirConfiguracio() {
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
			for (int i=0; i<llista_productes.getnElem();i++) {
				if ((llista_productes.getLlista()[i])instanceof Hardware) {
					if ((((Hardware)llista_productes.getLlista()[i]).getTipusHardwareString()).equalsIgnoreCase("HDD")) {
						System.out.println(i+"-"+((Hardware)llista_productes.getLlista()[i]).toString());
					}
				}
			}
			System.out.println("Intordueix el id del HDD que vols:");
			int pos=teclat.nextInt();
			System.out.println(((Hardware)llista_productes.getLlista()[pos]).getTipus().toString());
			llista_h[j]=((Hardware)llista_productes.getLlista()[pos]);
			j++;
			System.out.println("Vols afegir un altre compontent HDD?");
			System.out.println("1- Si    2- No");
			op=teclat.nextInt();
		}
		System.out.println("Escull un component periferic:");
		op=0;
		while (op!=2) {
			for (int i=0; i<llista_productes.getnElem();i++) {
				if ((llista_productes.getLlista()[i])instanceof Hardware) {
					if ((((Hardware)llista_productes.getLlista()[i]).getTipusHardwareString()).equalsIgnoreCase("Periferic")) {
						System.out.println(i+"-"+((Hardware)llista_productes.getLlista()[i]).toString());
					}
				}
			}
			System.out.println("Intordueix el id del Periferic que vols:");
			int pos=teclat.nextInt();
			llista_h[j]=((Hardware)llista_productes.getLlista()[pos]);
			j++;
			System.out.println("Vols afegir un altre compontent Periferic?");
			System.out.println("1- Si    2- No");
			op=teclat.nextInt();
		}
		System.out.println("Escull un component RAM:");
		op=0;
		while (op!=2) {
			for (int i=0; i<llista_productes.getnElem();i++) {
				if ((llista_productes.getLlista()[i])instanceof Hardware) {
					if ((((Hardware)llista_productes.getLlista()[i]).getTipusHardwareString()).equalsIgnoreCase("RAM")) {
						System.out.println(i+"-"+((Hardware)llista_productes.getLlista()[i]).toString());
					}
				}
			}
			System.out.println("Intordueix el id del RAM que vols:");
			int pos=teclat.nextInt();
			llista_h[j]=((Hardware)llista_productes.getLlista()[pos]);
			j++;
			System.out.println("Vols afegir un altre compontent RAM?");
			System.out.println("1- Si    2- No");
			op=teclat.nextInt();
		}
		System.out.println("Escull un component MB:");
		op=0;
		while (op!=2) {
			for (int i=0; i<llista_productes.getnElem();i++) {
				if ((llista_productes.getLlista()[i])instanceof Hardware) {
					if ((((Hardware)llista_productes.getLlista()[i]).getTipusHardwareString()).equalsIgnoreCase("MB")) {
						System.out.println(i+"-"+((Hardware)llista_productes.getLlista()[i]).toString());
					}
				}
			}
			System.out.println("Intordueix el id del MB que vols:");
			int pos=teclat.nextInt();
			llista_h[j]=((Hardware)llista_productes.getLlista()[pos]);
			j++;
			System.out.println("Vols afegir un altre compontent MB?");
			System.out.println("1- Si    2- No");
			op=teclat.nextInt();
		}
		System.out.println("Escull un component CPU:");
		op=0;
		while (op!=2) {
			for (int i=0; i<llista_productes.getnElem();i++) {
				if ((llista_productes.getLlista()[i])instanceof Hardware) {
					if ((((Hardware)llista_productes.getLlista()[i]).getTipusHardwareString()).equalsIgnoreCase("CPU")) {
						System.out.println(i+"-"+((Hardware)llista_productes.getLlista()[i]).toString());
					}
				}
			}
			System.out.println("Intordueix el id del CPU que vols:");
			int pos=teclat.nextInt();
			llista_h[j]=((Hardware)llista_productes.getLlista()[pos]);
			j++;
			System.out.println("Vols afegir un altre compontent CPU?");
			System.out.println("1- Si    2- No");
			op=teclat.nextInt();
		}
		System.out.println("Escull un component GPU:");
		op=0;
		while (op!=2) {
			for (int i=0; i<llista_productes.getnElem();i++) {
				if ((llista_productes.getLlista()[i])instanceof Hardware) {
					if ((((Hardware)llista_productes.getLlista()[i]).getTipusHardwareString()).equalsIgnoreCase("GPU")) {
						System.out.println(i+"-"+((Hardware)llista_productes.getLlista()[i]).toString());
					}
				}
			}
			System.out.println("Intordueix el id del GPU que vols:");
			int pos=teclat.nextInt();
			llista_h[j]=((Hardware)llista_productes.getLlista()[pos]);
			j++;
			System.out.println("Vols afegir un altre compontent GPU?");
			System.out.println("1- Si    2- No");
			op=teclat.nextInt();
		}
		op=0;j=0;
		while (op!=2) {
			for (int i=0; i<llista_productes.getnElem();i++) {
				if ((llista_productes.getLlista()[i])instanceof Software) {
					System.out.println(i+"-"+((Software)llista_productes.getLlista()[i]).toString());
				}
			}
			System.out.println("Introdueix el id del SO que vols:");
			int pos=teclat.nextInt();
			llista_s[j]=((Software)llista_productes.getLlista()[pos-1]);
			j++;
			System.out.println("Vols afegir un altre SO?");
			System.out.println("1- Si    2- No");
			op=teclat.nextInt();
		}
		llista_productes.afegirProducte(new Configuracio(nom, preu, estoc, llista_s, llista_h));
	}

	/**
	 * CASE 4: Donar de alta a un client.
	 * @param llista_cl
	 */
	private static void altaClient () {

		System.out.println("Introdueix el dni del client:");
		teclat.nextLine();
		String dni = teclat.nextLine();
		System.out.println("Introdueix el correu electronic del client:");
		String correu = teclat.nextLine();
		System.out.println("\nIntrodueix la direccio del client:");
		String direccio = teclat.nextLine();
		llista_clients.afegirClient(new Client (dni, correu, direccio));

	}

	/**
	 * CASE 5: donar de baixa a un client
	 * @param llista_cl
	 * @param llista_c
	 */
	private static void baixaClient () {

		String dni="";
		//try {
		System.out.println("\nIntrodueix el dni del client que es vol donar de baixa:");
		teclat.nextLine();//limpiar buffer
		dni = teclat.nextLine();
		llista_clients.eliminarClient(dni);
		llista_comandes.eliminarComandes(dni);
		//}catch (clientInexistentException e) {
		//inventar excepcio
		//}
	}

	/**
	 * CASE 6: Treure un llistat de tots els productes que tenen alguna comanda, mostrant les dades del client
	 *					que l’han fet.
	 * @param llista_p
	 * @param llista_c
	 * @param llista_cl
	 */
	private static void prodComanda () {

		LlistaProductes llista_aux = new LlistaProductes();
		boolean trobat = false;
		if (llista_comandes.getnComanda()!= 0) {

			for (int i = 0; i < llista_comandes.getnComanda(); i++) {
				for (int j = 0; j < llista_comandes.getLlista()[i].getLlistaProductes().getnElem(); j++) {
					for (int k = 0; k < llista_aux.getnElem() && !trobat; k++) {
						if ( llista_comandes.getLlista()[i].getLlistaProductes().getLlista()[j].getId() == llista_aux.getLlista()[k].getId()) {
							trobat=true;
						}	
					}
					if(!trobat) {
						llista_aux.afegirProducte(llista_comandes.getLlista()[i].getLlistaProductes().getLlista()[j]);
					}
					trobat = false;
				}
			}

			for (int i = 0; i < llista_aux.getnElem(); i++) {
				System.out.println(llista_aux.getLlista()[i]);
				for (int j = 0; j < llista_comandes.getnComanda(); j++) {
					if(llista_comandes.getLlista()[j].existeixProducte(llista_aux.getLlista()[i])) {
						System.out.println(llista_comandes.getLlista()[j].getClient());
					}
				}
				System.out.println("-----------------------------------------------");
			}
		}
		else {
			System.out.println("No hi ha cap comanda feta");
		}
	}

	/**
	 * CASE 7: Modificar l'estoc de qualsevol producte a partir del seu idenntificador.
	 * @param llista
	 */
	private static void modificarEstoc () {
		int i, nouEstoc;
		System.out.println(llista_productes.toString());
		System.out.println("Introdueix el numero del producte del qual vols modificar l'estoc:");
		try {
			i=teclat.nextInt();
			System.out.println("Quin es el nou estoc d'aquest producte?");
			nouEstoc=teclat.nextInt();
			llista_productes.getLlista()[i-1].setEstoc(nouEstoc);
			System.out.println("L'estoc actual es: "+nouEstoc);
		}
		catch(InputMismatchException e) {
			System.out.println("Has introduit un valor incorrecte.");
		}
	}

	/**
	 * CASE 8: Treure un llistat de tots el productes que tenen estoc>=1, indicant el seu estoc.
	 * @param llista 
	 * @return aux 
	 */
	private static String productesEstoc() {

		String aux="";
		for (int i=0; i<llista_productes.getnElem();i++) {
			if (llista_productes.getLlista()[i].getEstoc() >= 1) {
				aux+=llista_productes.getLlista()[i].toString()+"\n";
			}
		}
		return aux;
	}

	/**
	 * CASE 9: Treure un llistat de tots els productes que formen part d’alguna configuració.
	 * @param llista 
	 * @return aux 
	 */
	private static String productesConfiguracio() {
		String aux="";
		for (int i=0;i<llista_productes.getnElem();i++) {
			if (llista_productes.getLlista()[i] instanceof Configuracio) {
				aux+=llista_productes.getLlista()[i].toString()+"\n";
			}
		}
		return aux;
	}

	/**
	 * CASE 1O: Mostrar el producte del qual s’han fet més comandes i indicar el número d’aquestes.
	 * @param llista_p
	 * @param llista_c
	 * @param llista_cl
	 * @return Producte 
	 */
	private static void mesComandes () {
		LlistaProductes llista_aux = new LlistaProductes();
		int aux[] = new int [llista_productes.getnElem()];
		boolean trobat = false;
		int indexGran = 0, k = 0;
		for (int i = 0; i < llista_comandes.getnComanda(); i++) {
			for (int j = 0; j < llista_comandes.getLlista()[i].getLlistaProductes().getnElem(); j++) {
				for (k = 0; k < llista_aux.getnElem() && !trobat; k++) {

					if ( llista_comandes.getLlista()[i].getLlistaProductes().getLlista()[j].getId() == llista_aux.getLlista()[k].getId()) trobat = true;
				}
				if (!trobat) {
					aux[llista_aux.getnElem()]++;
					llista_aux.afegirProducte(llista_comandes.getLlista()[i].getLlistaProductes().getLlista()[j]);
				}
				else {
					aux[k-1]++;	// para que no se salga del array (!trobat) para descontarlo del for
				}
				trobat = false;
			}
		}
		for (int i = 1; i < aux.length; i++) {
			if(aux[i]>aux[indexGran]) indexGran = i;
		}
		if(llista_aux.getLlista()[indexGran] != null) {
			System.out.println(llista_aux.getLlista()[indexGran]);
			System.out.println("El numero de comandes que s'han fet es: "+aux[indexGran]);
		}
		else {
			System.out.println("No hi ha cap producte amb comandes.");
		}
	}


	/**
	 * CASE 11: Mostrar elements de qualsevol llista
	 * @param llista_p
	 * @param llista_c
	 * @param llista_cl
	 */
	private static void consultarLlistes () {
		int op;
		do {
			System.out.println("\n0. Sortir");
			System.out.println("\n1. Lista de productes");
			System.out.println("\n2. Lista de clients");
			System.out.println("\n3. Lista de comandes");
			System.out.println("\nQuina opcio vols escollir?:");
			try {
				op = teclat.nextInt();
			}catch (InputMismatchException e) {
				op= -1; //canviem valor op per a que no entri en el bucle
				teclat.nextLine();
			}

			switch (op) {
			case 0:{
			}break;
			case 1:{
				System.out.println("\nLLISTA DE PRODUCTES:");
				System.out.println("\n"+llista_productes.toString());
			}break;
			case 2:{
				System.out.println("\nLLISTA DE CLIENTS:");
				System.out.println("\n"+llista_clients.toString());
			}break;
			case 3:{
				System.out.println("\nLLISTA DE COMANDES:");
				System.out.println("\n"+llista_comandes.toString());
			}break;
			default:
				System.out.println("\nOpcio no valida, introdueixi un enter");
				break;
			}
		}while (op < 0 || op > 3);
	}
}


