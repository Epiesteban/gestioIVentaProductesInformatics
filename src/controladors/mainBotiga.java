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
		//Comanda comanda=new Comanda(llista_clients.getLlista()[0]);
		//	System.out.println(comanda);
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
				//prodComanda();
			}break;
			case 7:{
				System.out.println("\nHas escollit: modificar l'estoc de qualsevol dels productes que s'han donat d'alta a partir del seu identificador");
				modificarEstoc(null); //????????
			}break;
			case 8:{
				System.out.println("\nHas escollit: treure un llistat de tots els productes que tenen estic >= 1, indicant el seu estoc");
				productesEstoc(null); //????????
			}break;
			case 9:{
				System.out.println("\nHas escollit: treure un llistat de tots els productes que formen part d'alguna configuracio");
				productesConfiguracio(null); //????????
			}break;
			case 10:{
				System.out.println("\nHas escollit: mostrar el producte del qual s'han fet més comandes i indicar el numero d'aquestes");
				//mesComandes();
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

		if (op == 1) guardarFitxers(llista_clients, llista_productes, llista_comandes);
		System.out.println("\nAdeu, fins aviat!");
		teclat.close();
		System.exit(0);
	}

	/**
	 * GUARDAR FITXERS
	 * @param llista_clients
	 * @param llista_productes
	 * @param llista_comandes
	 * @throws IOException 
	 */
	private static void guardarFitxers(LlistaClients llista_clients, LlistaProductes llista_productes, LlistaComandes llista_comandes) throws IOException  {
		BufferedWriter cl=new BufferedWriter(new FileWriter("clients.txt"));
		//BufferedWriter p=new BufferedWriter(new FileWriter("productes.txt"));
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
	 * FUNCIONS PER LLEGIR I ESCRIURE FITXERS 
	 */
	/**
	 * 
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
	 * 
	 * @param llista
	 * @throws FileNotFoundException
	 */
	private static void llegirFitxerProductes(LlistaProductes llista) throws FileNotFoundException {
		String result="";
		Scanner f=new Scanner(new File("productes.txt"));
		while (f.hasNextLine()) {
			result= f.nextLine();
			String[] separador = result.split("\\*");
			//switch llista.afegirHardware, etc.
			//	llista.afegirProducte(new Producte(separador[0], separador[1], Float.parseFloat(separador[2]), Integer.parseInt(separador[3]), Integer.parseInt(separador[4])));
		}
		f.close();
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
		do {
			System.out.println("Selecciona el sistema operatiu:");
			System.out.println("1- WINDOWS,  2-MACOS,  3-LINUX");
			op=teclat.nextInt();
			switch (op) {
			case 1:
				sist= "WINDOWS";
				break;
			case 2:
				sist= "MASOS";
				break;
			case 3:
				sist= "LINUX";
				break;
			default:
				System.out.println("Has introduit un nombre erroni! Torna a provar");
			}

		}while (op < 1 || op > 3);
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
	 * CASE 6
	 */
	/*	private static void prodComanda () {
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
	 */
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
	 * @param l :
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
	 * @param l :
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
	 * CASE 1O
	 */
	/*	private static void mesComandes () {
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
			nComandes = 0;
		}
		System.out.println("El producte amb mes comandes es:");
		System.out.println(llista_p.getLlista()[posicio].toString());
		System.out.println("amb un total de "+max+" comandes.");
	}
	 */
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


