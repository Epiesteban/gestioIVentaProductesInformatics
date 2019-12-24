package interficieGrafica;

import javax.swing.*;

import controladors.mainBotiga;
import models.LlistaClients;
import models.LlistaComandes;
import models.LlistaProductes;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Scanner;

public class ProgramaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;

	private Container container;

	/**
	 * LLISTES 
	 */
	private LlistaComandes ll_comandes;
	private LlistaClients ll_clients;
	private LlistaProductes ll_productes;


	// ELEMENTS FINESTRA
	/* Info Usuari */
	private JPanel usuariPanel = new JPanel(); //Panell principal
	private JLabel usuariNom = new JLabel();
	private JLabel usuariDni = new JLabel();
	private JLabel usuariCorreu = new JLabel();
	private JButton usuariComandesRealitzades = new JButton("Ves a les teves comandes!");

	private JPanel northPanel = new JPanel();

	/* Cerca Producte */
	private JPanel producteBuscadorPanel = new JPanel();
	private JPanel producteTitolPanel = new JPanel();
	private JTextField producteTitol = new JTextField();
	private JLabel producteLabel = new JLabel("Introdueixi el producte desitjat: ");
	private JTextField producteNom = new JTextField("");
	private JButton producteBuscadorBoto = new JButton("BUSCAR");
	private JLabel producteTipus = new JLabel("Filtres: ");
	private JPanel tipusList = new JPanel();
	private JScrollPane tipusChecklistScroll = new JScrollPane();

	/* Llista productes */
	private JPanel productesListPanel = new JPanel();
	private JScrollPane productesListPanelScroll = new JScrollPane();
	private BotoGuardar llistaBotonsProductes[];
	private int numProductes = 0;
	private JCheckBox tsoftware, thardware, tconfiguracio;

	// INFORMACIÓ USUARI
	private String dni;

	// INFORMACIÓ GUARDAR DADES A FITXER
	private boolean unsavedData = false;

	/**
	 * Constructor 
	 * @param ll_comandes
	 * @param ll_productes
	 * @param ll_clients
	 */
	public ProgramaPrincipal (LlistaComandes ll_comandes, LlistaProductes ll_productes, LlistaClients ll_clients) {
		
		super("mainBotiga"); //Agafem la info de mainBotiga

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		//this.addWindowListener(new AccioTancarFinestraEmergent(this));

		setSize(700, 680); //afegim un tamany de finestra

		container = getContentPane();
		container.setLayout(new BorderLayout());

		// Recuperar referència a les llistes
		this.ll_productes = ll_productes;
		this.ll_clients = ll_clients;
		this.ll_comandes = ll_comandes;
		

		// Demanar DNI al executar la finestra
		dni = JOptionPane.showInputDialog("Introdueix el teu dni per poder accedir al menu de clients: ");
		Scanner dni = new Scanner(System.in);
		
		/**
		 Panell de l'usuari
		 */
		usuariPanel.setLayout(new GridLayout(5, 1));
		usuariPanel.add(usuariNom);
		usuariPanel.add(usuariDni);
		usuariPanel.add(usuariCorreu);
		
		usuariComandesRealitzades.addActionListener(new ComandesClient(this));
		
		//ICONO
		try {
			usuariComandesRealitzades.setIcon(new ImageIcon(getClass().getResource("/resources/book.png")));
			usuariComandesRealitzades.setVerticalTextPosition(JButton.TOP);
			usuariComandesRealitzades.setHorizontalTextPosition(JButton.RIGHT);
		} catch (NullPointerException e) {
			
		}		
		usuariPanel.add(usuariComandesRealitzades);
		
		northPanel.add(usuariPanel);

		container.add(northPanel, BorderLayout.NORTH);

		
		tipusList.setLayout(new GridLayout(3,1)); 
			
			/**
			 *SOFTWARE
			 */
			tsoftware = new JCheckBox("Software");
			tsoftware.setMnemonic(KeyEvent.VK_0);
			tsoftware.setSelected(false);
			/**
			 * HARDWARE
			 */
			thardware= new JCheckBox("Hardware");
			thardware.setMnemonic(KeyEvent.VK_0);
			thardware.setSelected(false);
			/**
			 * CONFIGURACIO
			 */
			tconfiguracio = new JCheckBox("Configuracio");
			tconfiguracio.setMnemonic(KeyEvent.VK_0);
			tconfiguracio.setSelected(false);
			
			tipusChecklistScroll.setViewportView(tipusList);
			this.add(tipusChecklistScroll);
			
			
		producteBuscadorPanel.setLayout(new BoxLayout(producteBuscadorPanel, BoxLayout.Y_AXIS));
			producteTitolPanel.setLayout(new GridLayout(2,1));
			producteTitol.addActionListener(new AccioBuscaProducte(this));
				producteTitolPanel.add(producteLabel);
				producteTitolPanel.add(producteTitol);
				producteBuscadorPanel.add(producteTitolPanel);
			 
				try {
					producteBuscadorBoto.setIcon(new ImageIcon(getClass().getResource("buscar imatge de lupa")));
					producteBuscadorBoto.setVerticalTextPosition(JButton.TOP);
					producteBuscadorBoto.setHorizontalTextPosition(JButton.RIGHT);
				} catch (NullPointerException e) {
				
				}
					producteBuscadorBoto.addActionListener(new AccioBuscaProducte(this));
						producteBuscadorPanel.add(producteTipus);
						producteBuscadorPanel.add(tipusChecklistScroll);
						producteBuscadorPanel.add(producteBuscadorBoto);

			container.add(producteBuscadorPanel, BorderLayout.WEST);
		
			
			
			
			//Date avui = new Date();
			
			String[] comandesFetes = ll_comandes.
					
//186 a 217
			//llistaBotonsProductes = new BotoGuardar[ll_productes.getnElem()];
			//productesListPanel.setLayout(new GridLayout(1, 1));
			//	for (int i=0; i<ll_productes.getnElem();i++) {
			//		try {
			//			boolean trobat = false;
			//			for (int j=0; j>50 && !trobat; j++) {
			//				trobat = ll_productes.getnElem(i).equals(comandesFetes[j]);
			//			}
			//			if () {
			//				
			//			}
			//		}
			//	}
			
			/**
			 * Retorna el text introduit per buscar el llibre
			 * @return -->
			 */
			public String getTextSearch() {
				return producteTitol.getText();
			}

			/**
			 * Actualitza la llista de llibres que es mostra a la GUI.
			 * @param input la cadena d'entrada
			 */
			public void actualitzarLlistaProductes (String ratoli) {
				/**Borrem el panel de la llista de botons**/
				container.remove(productesListPanel);
				repaint();			
				
				/**Fem un reset al panell general**/
				numProductes = 0;
				JPanel bookListPanel = new JPanel();
				bookListPanel.setLayout(new GridLayout(ll_productes.getnElem(), 1));
			
			
		

	//FUNCIONS FORA DEL MAIN	 
	public void itemStateChanged (ItemEvent e) {
		Object source = e.getItemSelectable();
		if (source == tsoftware) {
			//
		}else if (source == thardware){
			//
		}else if (source == tconfiguracio) {
			//
		}
		if (e.getStateChange() == ItemEvent.DESELECTED) {
			System.out.println("No hi ha cap filtre seleccionat.");
		}
	}
	}

