package controladors;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import classes.LlistaComanda;

public class mainBotiga {

	public static void main(String[] args) {
		
		System.out.println("Punt de partida mainBotiga.");
	}
/**
 * Funció per a escriure en una llista en format serialitzable 	
 * @param llista
 */
public static void guardarDataSerialitzable (LlistaComanda llista) {
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
public static void llegirDataSerialitzable (LlistaComanda llista) {
	ObjectInputStream lfitxer;
	try {
		lfitxer = new ObjectInputStream (new FileInputStream("nomfitxer.ser"));
		for (int i=0; i<llista.getnumElem(); i++) {
			llista=(LlistaComanda)lfitxer.readObject();
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
}
	

