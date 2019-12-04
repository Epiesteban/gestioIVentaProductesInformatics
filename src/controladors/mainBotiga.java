package controladors;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Scanner;

import models.*;

public class mainBotiga {

	static Scanner teclat=new Scanner(System.in);

	public static void main(String[] args) {

		LlistaProductes llista_p=new LlistaProductes();
		int op=0;
		
		menu();
		op=teclat.nextInt();
		while (op!=12) {
			switch (op) {
			case 1:{
				
			}break;
			case 2:{
			}break;
			case 3:{
			}break;
			case 4:{
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
				System.out.println("Vigila! Has introduit un numero err�ni");
			}
			}
		}
		teclat.close();
	}
	
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
	
	public static void menu () {
		System.out.println("Benvingut a la botiga! Que vols fer?");
		System.out.println("1-Afegir un producte de software");
		System.out.println("2-Afegir un producte de hardware");
		System.out.println("3-Afegir una configuraci� completa");
		System.out.println("4-Donar d'alta un client");
		System.out.println("5-Donar de baixa un client");
		System.out.println("6-Visualitzar els productes que tenen alguna comanda");
		System.out.println("7-Modificar l'estoc");
		System.out.println("8-Visualitzar els productes que estan en estoc");
		System.out.println("9-Visualitzar els productes que formen part d'una configuraci�");
		System.out.println("10-Mostrar el producte amb m�s comandes");
		System.out.println("11-Consultar tots els elements d'una llista");
		System.out.println("12-Sortir");
	}
	
	public static Software afegirSoftware () {
		
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
	
	
	public static Hardware afegirHardware () {
		
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
	
	
	public static Configuracio afegirConfiguracio() {
		return new Configuracio("nom", 23, 32);
	}
	
	public static void modificarEstoc (LlistaProductes l) {

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
	
	public static String productesEstoc(LlistaProductes l) {
		String aux="";
		for (int i=0; i<l.getnElem();i++) {
			if (!(l.getLlista()[i] instanceof Configuracio)) {
				aux=aux+l.getLlista()[i].toString();
			}
		}
		return aux;
	}
	
	public static String productesConfiguracio(LlistaProductes l) {
		String aux="";
		for (int i=0;i<l.getnElem();i++) {
			if (l.getLlista()[i] instanceof Configuracio) {
				aux=aux+l.getLlista()[i].toString();
			}
			i++;
		}
		return aux;
	}
	
	
	public static void mostrarLlista (LlistaProductes lp, LlistaClients lc) {
	
		int op=0;
		
		while (!(op==1 || op==2)) {
			System.out.println("De quina llista vols veure els elements?");
			System.out.println("1- Llista de productes,  2- Llista de clients");
			op=teclat.nextInt();
			switch (op) {
			case 1:{
				System.out.println(lp.toString());
			}break;
			case 2:{
				System.out.println(lc.toString());
			}break;

			default:
				System.out.println("Has introdu�t un numero erroni!Prova un altre numeo");
			}
		}
	}
}
