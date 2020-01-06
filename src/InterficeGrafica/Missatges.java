package InterficeGrafica;


import javax.swing.JOptionPane;

import controladors.mainClients;

public class Missatges {

	public Missatges() {
		// Demanar dades a l'usuari.
		String dni = JOptionPane.showInputDialog("Introdueix el dni:");
		while (mainClients.llista_clients.buscarClient(dni) == null) {
			// Missatge d'error.
			JOptionPane.showMessageDialog(null, "Client inexistent, cal un dni valid!", "ERROR", JOptionPane.ERROR_MESSAGE);
			dni = JOptionPane.showInputDialog("Introdueix el dni");
		}
		
		// Missatge d'informaci�.
		JOptionPane.showMessageDialog(null, "Client amb DNI: "+mainClients.llista_clients.buscarClient(dni).getDni()+" confirmat!", "Benvingut", JOptionPane.INFORMATION_MESSAGE);	
		new FinestraMenuClient(dni).setVisible(true);
	}
	public static void main(String[] args) {
		new Missatges();
		
	}

}
