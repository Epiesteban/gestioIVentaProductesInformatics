package controladors;
import java.io.*;
import models.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class mainBotiga {

	static Scanner teclat=new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		LlistaClients llista_clients = new LlistaClients();
		LlistaProductes llista_productes = new LlistaProductes();
		LlistaComandes llista_comandes = new LlistaComandes();

		llegirFitxerClients(llista_clients);
		llegirFitxerProductes(llista_productes);
		llegirDataSerialitzable(llista_comandes);

		// Creacio d'instancies per comprovar que els metodes funcionen correctament.
		llista_productes.afegirProducte(new Software("hola", 65, 26, "WINDOWS"));
		llista_productes.afegirProducte(new Software("adios", 65, 26, "LINUX"));
		llista_productes.afegirProducte(new Software("met", 65, 26, "MACOS"));

		llista_comandes.afegirComanda(new Comanda(llista_clients.getLlista()[0]));
		llista_comandes.getLlista()[0].afegirProducteComanda(llista_productes.getLlista()[1]);
		llista_comandes.getLlista()[0].afegirProducteComanda(llista_productes.getLlista()[0]);
		llista_comandes.getLlista()[0].afegirProducteComanda(llista_productes.getLlista()[1]);
		llista_comandes.getLlista()[0].afegirProducteComanda(llista_productes.getLlista()[1]);

		llista_comandes.afegirComanda(new Comanda(llista_clients.getLlista()[1]));
		llista_comandes.getLlista()[1].afegirProducteComanda(llista_productes.getLlista()[2]);
		llista_comandes.getLlista()[1].afegirProducteComanda(llista_productes.getLlista()[2]);
		llista_comandes.getLlista()[1].afegirProducteComanda(llista_productes.getLlista()[2]);
		llista_comandes.getLlista()[1].afegirProducteComanda(llista_productes.getLlista()[0]);

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
				afegirSoftware(llista_productes);
			}break;
			case 2:{
				System.out.println("\nHas escollit: afegir un producte de hardware");
				afegirHardware(llista_productes);
			}break;
			case 3:{
				System.out.println("\nHas escollit: afegir una configuració completa");
				afegirConfiguracio(llista_productes);
			}break;
			case 4:{
				System.out.println("\nHas escollit: donar d'alta un client");
				altaClient(llista_clients);
			}break;
			case 5:{
				System.out.println("\nHas escollit: donar de baixa a un client");
				baixaClient(llista_clients, llista_comandes);
			}break;
			case 6:{
				System.out.println("\nHas escollit: treure un llistat de tots els productes que tenen alguna comanda (amb les dades del client) ");
				prodComanda(llista_productes, llista_comandes, llista_clients);
			}break;
			case 7:{
				System.out.println("\nHas escollit: modificar l'estoc de qualsevol dels productes que s'han donat d'alta a partir del seu identificador");
				modificarEstoc(llista_productes);
			}break;
			case 8:{
				System.out.println("\nHas escollit: treure un llistat de tots els productes que tenen estic >= 1, indicant el seu estoc");
				if (!productesEstoc(llista_productes).equals("")) System.out.println(productesEstoc(llista_productes));
				else System.out.println("No hi ha cap producte amb estoc >= 1.");
			}break;
			case 9:{
				System.out.println("\nHas escollit: treure un llistat de tots els productes que formen part d'alguna configuracio");
				if (!productesConfiguracio(llista_productes).equals("")) { 
					System.out.println(productesConfiguracio(llista_productes));
				}
				else {
					System.out.println("No hi ha cap producte que formi part d'una configuració");	
				}
			}break;
			case 10:{
				System.out.println("\nHas escollit: mostrar el producte del qual s'han fet més comandes i indicar el numero d'aquestes");
				mesComandes(llista_productes, llista_comandes, llista_clients);
			}break;
			case 11:{
				System.out.println("\nHas escollit: consultar tots els elements de qualsevol llista que tingueu definida");
				consultarLlistes(llista_productes, llista_comandes, llista_clients);


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
			guardarFitxerClients(llista_clients);
			// guardarFitxerProductes(llista_productes);
			//guardarDataSerialitzable(llista_comandes);
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
	private static void llegirFitxerClients(LlistaClients llista) {
		String result="";
		try {
			Scanner f=new Scanner(new File("clients.txt"));
			while (f.hasNextLine()) {
				result= f.nextLine();
				String[] separador = result.split("\\*");
				llista.afegirClient(new Client(separador[0], separador[1], separador[2]));
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
	private static void guardarFitxerClients(LlistaClients llista_clients) throws IOException  {
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
	 * Funcio per llegir fitxer Productes
	 * @param llista
	 * @throws FileNotFoundException
	 */
	private static void llegirFitxerProductes(LlistaProductes llista) throws FileNotFoundException {
		String result="";
		try {
			Scanner f=new Scanner(new File("productes.txt"));
			while (f.hasNextLine()) {
				result= f.nextLine();
				String[] separador = result.split("\\*");
				if (separador[3].equals("WINDOWS") || separador[3].equals("LINUX") || separador[3].equals("MACOS")) {
					llista.afegirProducte(new Software(separador[0], Float.parseFloat(separador[1]), Integer.parseInt(separador[2]), separador[3]));
				}
				else {
					llista.afegirProducte(new Hardware(separador[0], Float.parseFloat(separador[1]), Integer.parseInt(separador[2]), separador[3]));
				}
			}
			f.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("No existeix el fitxer.");
		}
		catch(Exception e) {
			System.out.println("Hi ha hagut algun error en la lectura de l'arxiu o al afegir els elements a la llista.\n");
		}
	}

	/**
	 * Funcio per guardar fitxer productes
	 * @param llista_p
	 */
	private static void guardarFitxerProductes(LlistaProductes llista_p) {

	}

	/**
	 * Funció per a escriure en una llista en format serialitzable 	
	 * @param llista
	 */
	private static void guardarDataSerialitzable (LlistaComandes llista) {
		ObjectOutputStream gfitxer;
		try {
			gfitxer = new ObjectOutputStream (new FileOutputStream("comandes.ser"));
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
			lfitxer = new ObjectInputStream (new FileInputStream("comandes.ser"));
			for (int i=0; i<llista.getnComanda(); i++) {
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
	private static void afegirSoftware (LlistaProductes llista_p) {
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
			llista_p.afegirProducte(new Software(nom, preu, estoc, sist));
		}catch(InputMismatchException e){
			System.out.println("Nom/preu/estoc incorrecte");
			teclat.nextLine();		}
	}

	/**
	 * CASE 2: Afegir un Hardware
	 * @param llista_p
	 */
	private static void afegirHardware (LlistaProductes llista_p) {
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
			llista_p.afegirProducte(new Hardware(nom, preu, estoc, tipus));
		} catch(InputMismatchException e) {
			System.out.println("Dades introduides erronies, torni a escollir una opcio");
			teclat.nextLine();
		}
	}

	/**
	 * CASE 3: Afegir una nova configuracio
	 * @param llista_p
	 */
	private static void afegirConfiguracio(LlistaProductes llista_p) {
		Hardware hardwares[];
		Software softwares[];
		String nom;
		Float preu;
		int estoc;
		System.out.println("Introdueix el nom:");
		nom=teclat.next();
		System.out.println("Introdueix el preu:");
		preu=teclat.nextFloat();
		System.out.println("Introdueix l'estoc:");
		estoc=teclat.nextInt();
		System.out.println("Aquests son els softwares que pots introduir: ");
		for (int i = 0; i < llista_p.getnElem(); i++) {
			if(llista_p.getLlista()[i] instanceof Software)
				System.out.println(i+"- "+llista_p.getLlista()[i]);
		}
		System.out.println();
		//llista_p.afegirProducte(new Configuracio("Configuracio1", preu, estoc, softwares, hardwares));
		//	llista_p.getLlista()[llista_p.getnElem()].
	}

	/**
	 * CASE 4: Donar de alta a un client.
	 * @param llista_cl
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

	}

	/**
	 * CASE 5: donar de baixa a un client
	 * @param llista_cl
	 * @param llista_c
	 */
	private static void baixaClient (LlistaClients llista_cl, LlistaComandes llista_c) {

		String dni="";
		//try {
		System.out.println("\nIntrodueix el dni del client que es vol donar de baixa:");
		teclat.nextLine();//limpiar buffer
		dni = teclat.nextLine();
		llista_cl.eliminarClient(dni);
		llista_c.eliminarComandes(dni);
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
	private static void prodComanda (LlistaProductes llista_p, LlistaComandes llista_c, LlistaClients llista_cl) {

		LlistaProductes llista_aux = new LlistaProductes();
		boolean trobat = false;
		if (llista_c.getnComanda()!= 0) {

			for (int i = 0; i < llista_c.getnComanda(); i++) {
				for (int j = 0; j < llista_c.getLlista()[i].getLlistaProductes().getnElem(); j++) {
					for (int k = 0; k < llista_aux.getnElem() && !trobat; k++) {
						if ( llista_c.getLlista()[i].getLlistaProductes().getLlista()[j].getId() == llista_aux.getLlista()[k].getId()) {
							trobat=true;
						}	
					}
					if(!trobat) {
						llista_aux.afegirProducte(llista_c.getLlista()[i].getLlistaProductes().getLlista()[j]);
					}
					trobat = false;
				}
			}

			for (int i = 0; i < llista_aux.getnElem(); i++) {
				System.out.println(llista_aux.getLlista()[i]);
				for (int j = 0; j < llista_c.getnComanda(); j++) {
					if(llista_c.getLlista()[j].existeixProducte(llista_aux.getLlista()[i])) {
						System.out.println(llista_c.getLlista()[j].getClient());
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
	private static void modificarEstoc (LlistaProductes llista) {
		int i, nouEstoc;
		System.out.println(llista.toString());
		System.out.println("Introdueix el numero del producte del qual vols modificar l'estoc:");
		try {
			i=teclat.nextInt();
			System.out.println("Quin es el nou estoc d'aquest producte?");
			nouEstoc=teclat.nextInt();
			llista.getLlista()[i-1].setEstoc(nouEstoc);
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
	private static String productesEstoc(LlistaProductes llista) {

		String aux="";
		for (int i=0; i<llista.getnElem();i++) {
			if (llista.getLlista()[i].getEstoc() >= 1) {
				aux+=llista.getLlista()[i].toString()+"\n";
			}
		}
		return aux;
	}

	/**
	 * CASE 9: Treure un llistat de tots els productes que formen part d’alguna configuració.
	 * @param llista 
	 * @return aux 
	 */
	private static String productesConfiguracio(LlistaProductes llista) {
		String aux="";
		for (int i=0;i<llista.getnElem();i++) {
			if (llista.getLlista()[i] instanceof Configuracio) {
				aux+=llista.getLlista()[i].toString()+"\n";
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
	private static void mesComandes (LlistaProductes llista_p, LlistaComandes llista_c, LlistaClients llista_cl) {
		LlistaProductes llista_aux = new LlistaProductes();
		int aux[] = new int [llista_p.getnElem()];
		boolean trobat = false;
		int indexGran = 0, k = 0;
		for (int i = 0; i < llista_c.getnComanda(); i++) {
			for (int j = 0; j < llista_c.getLlista()[i].getLlistaProductes().getnElem(); j++) {
				for (k = 0; k < llista_aux.getnElem() && !trobat; k++) {

					if ( llista_c.getLlista()[i].getLlistaProductes().getLlista()[j].getId() == llista_aux.getLlista()[k].getId()) trobat = true;
				}
				if (!trobat) {
					aux[llista_aux.getnElem()]++;
					llista_aux.afegirProducte(llista_c.getLlista()[i].getLlistaProductes().getLlista()[j]);
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
	private static void consultarLlistes (LlistaProductes llista_p, LlistaComandes llista_c, LlistaClients llista_cl) {
		int op;
		do {
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
			case 1:{
				System.out.println("\nLLISTA DE PRODUCTES:");
				System.out.println("\n"+llista_p.toString());
			}break;
			case 2:{
				System.out.println("\nLLISTA DE CLIENTS:");
				System.out.println("\n"+llista_cl.toString());
			}break;
			case 3:{
				System.out.println("\nLLISTA DE COMANDES:");
				System.out.println("\n"+llista_c.toString());
			}break;
			default:
				System.out.println("\nOpcio no valida, introdueixi un enter");
				break;
			}
		}while (op != 1 ||op != 2||op != 3);
	}
}


