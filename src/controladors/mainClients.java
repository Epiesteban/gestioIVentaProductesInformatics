package controladors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import models.*;

public class mainClients {
	
	public static void main(String[] args) throws FileNotFoundException {
		LlistaClients llistaClients = new LlistaClients();
		LlistaProductes llistaProductes = new LlistaProductes(100);
		llegirFitxerClients(llistaClients);
		llegirFitxerProductes(llistaProductes);
		llistaClients.toString();
	}
	
	static Scanner teclat=new Scanner(System.in);
	
	private static void llegirFitxerClients(LlistaClients llista) throws FileNotFoundException {
		String result="";
		Scanner f=new Scanner(new File("clients.txt"));
		while (f.hasNextLine()) {
			result= f.nextLine();
			String[] separador = result.split("\\*");
			llista.afegirClient(new Clients(separador[0], separador[1], separador[2]));
		}
		f.close();
	}


	private static void llegirFitxerProductes(LlistaProductes llista) throws FileNotFoundException {
		String result="";
		Scanner f=new Scanner(new File("productes.txt"));
		while (f.hasNextLine()) {
			result= f.nextLine();
			String[] separador = result.split("\\*");
			llista.afegirProducte(new productes(separador[0], Float.parseFloat(separador[1]), Integer.parseInt(separador[2]), Integer.parseInt(separador[3])));
		}
		f.close();
	}

}


