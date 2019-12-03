package controladors;

import java.util.Scanner;

public class mainBotiga {

	public static void main(String[] args) {
		Scanner teclat=new Scanner(System.in);
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
}
