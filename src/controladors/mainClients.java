package controladors;

import controladors.*;
import interficieGrafica.*;
import models.*;


public class mainClients {

private static LlistaProductes ll_productes = new LlistaProductes();
private static LlistaComandes ll_comandes = new LlistaComandes();
private static LlistaClients ll_clients= new LlistaClients();


public static void mainClients(String[] args) {
	
	// RECUPERAR DADES DE FITXERS
			
			llegirFitxerClients();
			llegirFitxerProductes();
			llegirFitxerComandes();
			

	//Cridem al programa principal
	new ProgramaPrincipal (ll_productes, ll_comandes, ll_clients);
}
}