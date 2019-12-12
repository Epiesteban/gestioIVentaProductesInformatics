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

		llista_productes.afegirProducte(new Software("hola", 65, 26, "WINDOWS"));
		llista_productes.afegirProducte(new Software("adios", 65, 26, "LINUX"));
		llista_productes.afegirProducte(new Software("met", 65, 26, "MACOS"));

	/*	llista_comandes.afegirComanda(new Comanda(llista_clients.getLlista()[0]));
		llista_comandes.getLlista()[0].afegirProducteComanda(llista_productes.getLlista()[1]);
		llista_comandes.getLlista()[0].afegirProducteComanda(llista_productes.getLlista()[0]);
		llista_comandes.getLlista()[0].afegirProducteComanda(llista_productes.getLlista()[2]);
		llista_comandes.getLlista()[0].afegirProducteComanda(llista_productes.getLlista()[1]);
*/

		//llegirFitxerProductes(llista_productes);
		//llegirDataSerialitzable(llista_comandes);

		int op=0;
		do {
			menu();
			try {
				op = teclat.nextInt();
			} catch (InputMismatchException e) {
				op = -1;
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
				System.out.println(productesEstoc(llista_productes)); 
			}break;
			case 9:{
				System.out.println("\nHas escollit: treure un llistat de tots els productes que formen part d'alguna configuracio");
				//excepcio llista buida 
				System.out.println(productesConfiguracio(llista_productes));
			}break;
			case 10:{
				System.out.println("\nHas escollit: mostrar el producte del qual s'han fet més comandes i indicar el numero d'aquestes");
				System.out.println(mesComandes(llista_productes, llista_comandes, llista_clients));
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
	 * @throws FileNotFoundException
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
	/*private static void llegirFitxerProductes(LlistaProductes llista) throws FileNotFoundException {
		String result="";
		Scanner f=new Scanner(new File("productes.txt"));
		while (f.hasNextLine()) {
			result= f.nextLine();
			String[] separador = result.split("\\*");
			//switch llista.afegirHardware, etc.
			//	llista.afegirProducte(new Producte(separador[0], separador[1], Float.parseFloat(separador[2]), Integer.parseInt(separador[3]), Integer.parseInt(separador[4])));
		}
		f.close();
	}*/

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
	 * CASE 1
	 * @return --> 
	 */
	private static void afegirSoftware (LlistaProductes llista_p) {
		String nom;
		float preu;
		int estoc, op=0;
		String sist = "";

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
	}

	/**
	 * CASE 2
	 */
	private static void afegirHardware (LlistaProductes llista_p) {
		String nom;
		float preu;
		int estoc, op=2;
		String tipus= "";

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
	}

	/**
	 * CASE 3
	 */
	private static void afegirConfiguracio(LlistaProductes llista_p) {
		//haced cosas
		llista_p.afegirProducte(new Configuracio("nom", 23, 32));
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
	}

	/**
	 * CASE 5
	 */
	private static void baixaClient (LlistaClients llista_cl, LlistaComandes llista_c) {
		String dni;

		System.out.println("\nIntrodueix el dni del client que es vol donar de baixa:");
		teclat.nextLine();//limpiar buffer
		dni = teclat.nextLine();
		llista_cl.eliminarClient(dni);
		llista_c.eliminarComandes(dni);
	}

	/**
	 * CASE 6: 	Treure un llistat de tots els productes que tenen alguna comanda, mostrant les dades del client
						que l’han fet.
	 */
	private static void prodComanda (LlistaProductes llista_p, LlistaComandes llista_c, LlistaClients llista_cl) {
		//FALTA ACABAR (JOEL)
		LlistaProductes llista_aux = new LlistaProductes();

		for (int i = 0; i < llista_c.getnComanda(); i++) {
			for (int j = 0; j < llista_c.getLlista()[i].getLlistaProductes().getnElem(); j++) {
				for (int k = 0; k < llista_aux.getnElem(); k++) {
					if ( llista_c.getLlista()[i].getLlistaProductes().getLlista()[j].getId() != llista_aux.getLlista()[k].getId()) {
						llista_aux.afegirProducte(llista_c.getLlista()[i].getLlistaProductes().getLlista()[j]);
						System.out.println(llista_c.getLlista()[i].getLlistaProductes().getLlista()[j]);
					}	
					System.out.println(llista_c.getLlista()[i].getClient());
				}
			}
		}
	}

	/**
	 * CASE 7
	 * @param llista --> 
	 */
	private static void modificarEstoc (LlistaProductes llista) {
		int i, nouEstoc;
		System.out.println(llista.toString());
		System.out.println("Introdueix el numero del producte del qual vols modificar l'estoc:");
		i=teclat.nextInt();
		System.out.println("Quin es el nou estoc d'aquest producte?");
		nouEstoc=teclat.nextInt();
		llista.getLlista()[i].setEstoc(nouEstoc);
		System.out.println("L'estoc actual es: "+nouEstoc);
	}

	/**
	 * CASE 8
	 * @param llista :
	 * @return-->
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
	 * CASE 9
	 * @param llista :
	 * @return-->
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
	 */
	private static Producte mesComandes (LlistaProductes llista_p, LlistaComandes llista_c, LlistaClients llista_cl) {
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
					aux[k-1]++;
					
				}
				trobat = false;
			}
		}
		for (int i = 1; i < aux.length; i++) {
			if(aux[i]>aux[indexGran]) indexGran = i;
		}
		return llista_aux.getLlista()[indexGran];
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


