package interficieGrafica;

import java.awt.*;
import javax.swing.*;

import models.Configuracio;
import models.Hardware;
import models.LlistaClients;
import models.LlistaComandes;
import models.LlistaProductes;
import models.Software;


public class FinestraConfiguracio extends JFrame {
	private static final long serialVersionUID = 1L;

	// Dades usuari

	private String dni;
	
	// Referències de les llistes
	private LlistaProductes ll_productes;
	private LlistaComandes ll_comandes;
	private LlistaClients ll_clients;
	
	// Referència a la finestra principal
	private ProgramaPrincipal finestraPrincipal;

	/**
	 * Constructor de FinestraLlibre.
	 * @param codi del llibre.
	 * @param esSoci que indica si l'usuari es soci o no.
	 * @param dni del usuari.
	 * @param finestraPrincipal Referència a FinestraPrincipal.
	 * @param ll_Llibres Referència a la llista de llibres.
	 * @param ll_LlibresPrestec Referència a la llista de llibres en prèstec.
	 * @param ll_LlibresReservats Referència a la llista de llibres reservats.
	 */
	public FinestraConfiguracio (String dni, ProgramaPrincipal finestraPrincipal,
			LlistaProductes ll_productes, LlistaComandes ll_comandes, LlistaClients ll_clients, Configuracio config) {
		

		setSize(400, 250);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.ll_productes = ll_productes;
		this.ll_comandes = ll_comandes;
		this.ll_clients = ll_clients;
		
		this.finestraPrincipal = finestraPrincipal;

			//NO SE COM FICAR-HO
		Configuracio config = new Configuracio();


		this.dni = dni;


		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		JLabel titol = new JLabel("Nom: " + config.getNom());
		JLabel productesVaris = new JLabel();
		Hardware[] productesAux = config.getHardwares();
		String labelAux = "Productes Hardware: ";
		for (int i = 0; i < productesAux.length; i++) {
			if (i > 0)
				labelAux += ",";
			labelAux += " " + productesAux[i];
		}
		Software[] productesAux2 = config.getSoftwares();
		String labelAux2 = "Productes Hardware: ";
		for (int i = 0; i < productesAux.length; i++) {
			if (i > 0)
				labelAux2 += ",";
			labelAux2 += " " + productesAux[i];
		}
		productesVaris.setText(labelAux);
		JLabel estoc = new JLabel("Estoc: " + config.getEstoc());
		JLabel preu = new JLabel("Preu: " + config.getPreu());
		JLabel id = new JLabel("Id: " + config.getId());
		

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(8, 1));

		infoPanel.add(titol);
		infoPanel.add(productesVaris);
		infoPanel.add(estoc);
		infoPanel.add(preu);
		infoPanel.add(id);

		container.add(infoPanel, BorderLayout.CENTER);

		setLocationRelativeTo(null);
		setVisible(true);
	}
}
