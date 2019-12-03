package controladors;

import java.util.Scanner;

import com.sun.glass.ui.Window;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.sun.xml.internal.ws.Closeable;

import models.LlistaClients;
import models.LlistaProductes;
import models.SO;
import models.configuracio;
import models.hardware;
import models.productes;
import models.software;
import models.tipus_hardware;
import sun.launcher.resources.launcher;

public class mainBotiga {

	
	public static void main(String[] args) {
		Scanner teclat=new Scanner(System.in);
		LlistaProductes llista_p=new LlistaProductes(200);
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
				System.out.println("Vigila! Has introduit un numero erròni");
			}
			}
		}
		teclat.close();
	}
	
	
	public static void menu () {
		System.out.println("Benvingut a la botiga! Que vols fer?");
		System.out.println("1-Afegir un producte de software");
		System.out.println("2-Afegir un producte de hardware");
		System.out.println("3-Afegir una configuració completa");
		System.out.println("4-Donar d'alta un client");
		System.out.println("5-Donar de baixa un client");
		System.out.println("6-Visualitzar els productes que tenen alguna comanda");
		System.out.println("7-Modificar l'estoc");
		System.out.println("8-Visualitzar els productes que estan en estoc");
		System.out.println("9-Visualitzar els productes que formen part d'una configuració");
		System.out.println("10-Mostrar el producte amb més comandes");
		System.out.println("11-Consultar tots els elements d'una llista");
		System.out.println("12-Sortir");
	}
	
	public static software afegirSoftware () {
		Scanner t=new Scanner(System.in);
		String nom;
		float preu;
		int estoc, op=2;
		SO sist=SO.Windows;//S'HA D'INICIALITZAR AMB QUALSEVOL ENUM?
		
		
		System.out.println("Introdueix el nom:");
		nom=t.next();
		System.out.println("Introdueix el preu:");
		preu=t.nextFloat();
		System.out.println("Introdueix l'estoc:");
		estoc=t.nextInt();
		while (op<=1 && op>=3) {
			System.out.println("Selecciona el sistema operatiu:");
			System.out.println("1- Windows,  2-MacOS,  3-Linux");
			op=t.nextInt();
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
		t.close();
		return(new software(nom, preu, estoc, sist));
	}
	
	
	public static hardware afegirHardware () {
		Scanner t=new Scanner(System.in);
		String nom;
		float preu;
		int estoc, op=2;
		tipus_hardware tipus=tipus_hardware.CPU;//S'HA D'INICIALITZAR AMB QUALSEVOL ENUM?
		
		
		System.out.println("Introdueix el nom:");
		nom=t.next();
		System.out.println("Introdueix el preu:");
		preu=t.nextFloat();
		System.out.println("Introdueix l'estoc:");
		estoc=t.nextInt();
		while (op<=1 && op>=6) {
			System.out.println("Selecciona el tipus de hardware:");
			System.out.println("1- Case,  2-CPU,  3-HDD, 4-MoBo, 5-PSU, 6-RAM");
			op=t.nextInt();
			switch (op) {
			case 1:{
				tipus=tipus_hardware.Case;
			}break;
			case 2:{
				tipus=tipus_hardware.CPU;
			}break;
			case 3:{
				tipus=tipus_hardware.HDD;
			}break;
			case 4:{
				tipus=tipus_hardware.MoBo;
			}break;
			case 5:{
				tipus=tipus_hardware.PSU;
			}break;
			case 6:{
				tipus=tipus_hardware.RAM;
			}break;

			default:
				System.out.println("Has introduit un nombre erroni! Torna a provar");
			}
		}
		t.close();
		return(new hardware(nom, preu, estoc, tipus));
	}
	
	
	public static configuracio afegirConfiguracio() {
		
	}
	
	public static void modificarEstoc (LlistaProductes l) {
		Scanner t=new Scanner (System.in);
		int i, nouEstoc;
		System.out.println(l.toString());
		System.out.println("Introdueix el numero del producte del qual vols modificar l'estoc:");
		i=t.nextInt();
		System.out.println("Quin es el nou estoc d'aquest producte?");
		nouEstoc=t.nextInt();
		if (l.modificarEstoc(i, nouEstoc)==true) {
			System.out.println("S'ha modificat correctament l'estoc a:"+nouEstoc);
		}else {
			System.out.println("No s'ha realitzat l'operacio");
		}
		t.close();
	}
	
	public static String productesEstoc(LlistaProductes l) {
		String aux="";
		for (int i=0; i<l.getnElem();i++) {
			if (!(l.getLlista()[i] instanceof configuracio)) {
				aux=aux+l.getLlista()[i].toString();
			}
		}
		return aux;
	}
	
	public static String productesConfiguracio(LlistaProductes l) {
		String aux="";
		for (int i=0;i<l.getnElem();i++) {
			if (l.getLlista()[i] instanceof configuracio) {
				aux=aux+l.getLlista()[i].toString();
			}
			i++;
		}
		return aux;
	}
	
	
	public static void mostrarLlista (LlistaProductes lp, LlistaClients lc) {
		Scanner t= new Scanner(System.in);
		int op=0;
		
		while (!(op==1 || op==2)) {
			System.out.println("De quina llista vols veure els elements?");
			System.out.println("1- Llista de productes,  2- Llista de clients");
			op=t.nextInt();
			switch (op) {
			case 1:{
				System.out.println(lp.toString());
			}break;
			case 2:{
				System.out.println(lc.toString());
			}break;

			default:
				System.out.println("Has introduït un numero erroni!Prova un altre numeo");
			}
		}
		t.close();
	}
}
