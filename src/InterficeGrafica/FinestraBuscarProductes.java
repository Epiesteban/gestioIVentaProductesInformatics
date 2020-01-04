package InterficeGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import models.Producte;
import taules.GestioTaules;
import taules.GestioTitolTaules;
import taules.ModelTaulaProductes;
import taules.NomTaulaProductes;


public class FinestraBuscarProductes extends JFrame{
JPanel panel;
	private JScrollPane scrollPaneTable; //No els ha de tocar ningú
	private JTable tProductes;
	ArrayList<Producte> ll_productes;
	ModelTaulaProductes model;//model definit a ModelTaulaProductes
		private int files;
		private int columnes;
		
		
	//S'HA D'AFEGIR UN JTEXTFIELD PER A CRECAR LLETRA PER LLETRA, UN BOTÓ PER A QUE ENS PORTI A UNA PESTANYA EMERGENT (FinestraFiltresBuscarProductes)
	//ON HI PUGUIS AFEGIR ELS FILTRES (HARDWARE, SOFTWARE I CONFIGURACIONS)-JCHECKBOXES I UN BOTÓ PER GUARDAR ELS FILTRES- I UN BOTÓ PER FER COMANDES.
	//EL BOTO DE FER COMANDES ENS DURÀ A UNA PESTANYA EMERGENT ON ENS SORTIRÀ EL PREU TOTAL DE TOTS ELS PRODUCTES DE LA COMANDA I L'IDENTIFICADOR DE 
	//LA COMANDA DIENT QUE LA COMANDA S'HA REALITZAT CORRECTAMENT. SI ELS PRODUCTES DE LA COMANDA TENEN ESTOC >=1 ES GUARDARÀ I ES RESTARAN LES UNITATS
	//D'ESTOC DE CADA PRODUCTE.
		
	/*Xènia: El que he fet en aquesta part de programa, es fer el diseny d'una taula (totes les seves caracteristiques estan al nou package de taules) on
	 * es vagin mostrant tots els elements de la llista productes (on es pot veure el nom, preu i estoc); aquesta mateixa plantilla es pot utilitzar
	 * per fer la taula de Comandes realitzades. Queda afegir l'actions buttons (cada click sobre un producte fa que es seleccioni i que a la llarga
	 * es pugui fer una comanda + em cas de configuracions ha de sortir una pantalla emergent explicant les caracteristiques d'aquesta). No he fet que
	 * llegeixi la llista de Productes perque volia provar primer si la taula funcionava amb unes dades senzilles (ArrayList<Productes>) però han saltat 
	 * bastantes excepcions!!! Evidentment, no cal dir que falta acabar aquesta classe, però crec que puc dir que esta força avançada.*/	
			
	//creem el Frame 
	public FinestraBuscarProductes() {
		super();
		
		this.setTitle("BUSCADOR DE PRODUCTES");
		this.panel.setBackground(Color.pink);
		this.getContentPane().setLayout(new BorderLayout());
		this.setBackground(Color.pink);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1121, 453);
		
		setLocationRelativeTo(null);
			iniciarComponents();
			construirTaula();
	}

	private void iniciarComponents() {
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel taulaProductes = new JLabel("PRODUCTES TROBATS");
		taulaProductes.setFont(new Font("Rockwell", Font.BOLD, 25));
		panel.add(taulaProductes, BorderLayout.NORTH);
		
		scrollPaneTable = new JScrollPane();
		panel.add(scrollPaneTable);
		
		tProductes = new JTable();
		tProductes.setBackground(Color.WHITE);
		tProductes.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		tProductes.addMouseListener((MouseListener) this);
		
		//tablaSeguimiento.addKeyListener(this);
		taulaProductes.setOpaque(false);
		scrollPaneTable.setViewportView(taulaProductes);
		
	}
	
	/**
	 * Llista de productes de prova
	 * @return la llista de productes de prova 
	 */
	private ArrayList<Producte> consultarLlistaProductes() {
		ArrayList<Producte> provaLlista=new ArrayList<>();
		
		provaLlista.add(new Producte("tele",1000,1));
		provaLlista.add(new Producte("teclat",1000,1));		
		provaLlista.add(new Producte("ratoli",1000,1));
		provaLlista.add(new Producte("tablet",1000,0));
		
		return provaLlista;
	}
	/**
	 * Omple la informació de la taula utilitzant una llista de productes treballada 
	 * @param titolsLlista
	 * @return
	 */
	private Object[][] obtenirMatriuDades(ArrayList<String> titolsLlista) {
		
		//es crea una matriu on les files son dinamiques i les columnes son estatiques (definides a continuació per defecte)
		String informacio[][] = new String[ll_productes.size()][titolsLlista.size()];
		
		for (int x = 0; x < informacio.length; x++) {
			
			informacio[x][NomTaulaProductes.NOM] = ll_productes.get(x).getNom()+ "";
			informacio[x][NomTaulaProductes.PREU] = ll_productes.get(x).getPreu()+ "";
			informacio[x][NomTaulaProductes.ESTOC] = ll_productes.get(x).getEstoc()+ "";
			informacio[x][NomTaulaProductes.IDENTIFICADOR] = ll_productes.get(x).getId()+ "";
		}
		
		return informacio;
	}

	/**
	 * Amb els titols i la informació a mostrar es crea el model per a personalitzar la taula, asginant tamany a les caselles, etc.
	 * @param titols
	 * @param dada
	 */
	private void construirTaulaFinal(String[] titols, Object[][] dada) {
		model = new ModelTaulaProductes(dada, titols);
		//S'assigna el model de la taula
		tProductes.setModel(model);
		
		files = tProductes.getRowCount();
		columnes = tProductes.getColumnCount();
		
		//S'assigna el tipus de dada que tindra cada casella de cada columna definida per a validar la seva personalització
		tProductes.getColumnModel().getColumn(NomTaulaProductes.NOM).setCellRenderer(new GestioTaules("text"));
		tProductes.getColumnModel().getColumn(NomTaulaProductes.PREU).setCellRenderer(new GestioTaules("numeric"));
		tProductes.getColumnModel().getColumn(NomTaulaProductes.ESTOC).setCellRenderer(new GestioTaules("numeric"));
		tProductes.getColumnModel().getColumn(NomTaulaProductes.IDENTIFICADOR).setCellRenderer(new GestioTaules("numeric"));
	
		//Recorre i asigna a la resta de caselles les dades que s'emmagatzema a cadascuna
		for (int i = 0; i < titols.length-4; i++) {//es resta 4 perque les 4 columnes ja s'han definit a dalt
			System.out.println(i);
			tProductes.getColumnModel().getColumn(i).setCellRenderer(new GestioTaules("text"));
		}
		
			tProductes.getTableHeader().setReorderingAllowed(false);
			tProductes.setRowHeight(25);//tamaño de las celdas
			tProductes.setGridColor(new java.awt.Color(0, 0, 0)); 
		
			//Es defineix el tamany de llarg per cada columna i el seu contingut
			tProductes.getColumnModel().getColumn(NomTaulaProductes.NOM).setPreferredWidth(130);//NOM
			tProductes.getColumnModel().getColumn(NomTaulaProductes.PREU).setPreferredWidth(380);//PREU
			tProductes.getColumnModel().getColumn(NomTaulaProductes.ESTOC).setPreferredWidth(350);//ESTOC
			tProductes.getColumnModel().getColumn(NomTaulaProductes.IDENTIFICADOR).setPreferredWidth(130);//IDENTIFICADOR
	
		//Personalització de l'encapçalament
		JTableHeader jtableHeader = tProductes.getTableHeader();
	    jtableHeader.setDefaultRenderer(new GestioTitolTaules());
	    tProductes.setTableHeader(jtableHeader);
	    
	    //S'assigna la taula al scrollPanel
	    scrollPaneTable.setViewportView(tProductes);
	}
	
	/**
	 * Metode que permet construir una taula de productes, primer es creen les columnes i despres s'asigna la inofmració
	 */
	private void construirTaula() {
		
		ll_productes = consultarLlistaProductes();
		
		ArrayList<String> titolsLlista =new ArrayList<>();
		
		titolsLlista.add("NOM");
		titolsLlista.add("PREU");
		titolsLlista.add("ESTOC");
		titolsLlista.add("IDENTIFICADOR");

				
		//s'asignen les columnes per començar a crear la taula
		String titols[] = new String[titolsLlista.size()];
		for (int i = 0; i < titols.length; i++) {
			titols[i]=titolsLlista.get(i);
		}
		//obtenim les dades de la llista i les guardem a la matriu per enviarho a la contruccio de la taula
		Object[][] dada = obtenirMatriuDades(titolsLlista);
		construirTaulaFinal(titols,dada);
		
		this.setVisible (true);
	}
	
	public static void main(String[] args) {
		new FinestraBuscarProductes();
       
	}
}
