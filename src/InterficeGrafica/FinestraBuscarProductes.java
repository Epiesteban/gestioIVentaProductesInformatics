package InterficeGrafica;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame; 
import javax.swing.JScrollPane; 
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import controladors.mainClients;
import edu.uclouvain.swing.DefaultCheckListModel; //importat del package edu.uclouvain.swing
import edu.uclouvain.swing.JCheckList;
import models.Configuracio;
import models.Hardware;
import models.LlistaProductes;
import models.Producte;
import models.Software;
import sun.tools.jar.resources.jar;

public class FinestraBuscarProductes extends JFrame{

	public FinestraBuscarProductes() {
		super();
		AccioTancaPestanyaProductes accioR = new AccioTancaPestanyaProductes(this);
		JTextField textField;
		JTable j;
		
		JFrame finestra = new JFrame ("BUSCA PRODUCTES");
		finestra.getContentPane().setBackground(Color.decode("#afc3da"));
		
		textField = new JTextField("Busca el producte que vulguis", 40); //Aqui ficarem la busqueda --> lletra x lletra anirà eliminant productes 
		textField.setBackground(new Color(204, 204, 204));
		textField.setForeground(new java.awt.Color(102, 102, 255));
		//Busquem lletra per lletra el nom del producte 

		//Afegim una acció al JTextField per a que al polsar <enter> sapigui que s'ha acabat d'escriure
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Acabant de buscar resultats...");
				System.out.println(((JTextField)e.getSource()).getText());
			}
		});
		//Afegim un focus per a que l'usuari pugui fer altres coses despres dacabr descriure el que volia i deixi descriure
		textField.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				System.out.println(((JTextField)e.getSource()).getText());
			}
			public void focusGained(FocusEvent e) {
				// No fem res
			}
		});
		JButton botoCerca = new JButton("CERCA AMB FILTRES"); //El click cercarà els productes amb filtres inclosos
		JButton botoComanda = new JButton("FES UNA COMANDA"); //El click fara un comanda amb els productes seleccionats 
		JButton botoRetorna = new JButton("TORNA AL MENU PRINCIPAL"); //El click fara que retornem al menu principal
			botoRetorna.addActionListener(accioR);
		
			//Filtres
			JCheckBox[] checkList= new JCheckBox[5];
			checkList[0]=new JCheckBox("Hardwares", false);
			checkList[1]=new JCheckBox("Softwares", false);
			checkList[2]=new JCheckBox("Configuracions", false);
			checkList[3]=new JCheckBox("Productes amb estoc", false);
			checkList[4]=new JCheckBox("Productes sense estoc", false);
			//Panell de filtres
			JPanel filtres= new JPanel();
			filtres.setLayout(new GridLayout(5, 1));
			filtres.add(checkList[0]);
			filtres.add(checkList[1]);
			filtres.add(checkList[2]);
			filtres.add(checkList[3]);
			filtres.add(checkList[4]);
		//Per a que el programa sapigui quines de les caselles estan checked
		

		
		/**
		 * TAULA DE PRODUCTES
		 */
		// Column Names 
		String[] columnNames = { "NOM", "PREU", "ESTOC", "INFORMACIÓ"}; 
		// Informacio per omplir la taula
		final Class[] tiposColumnas = new Class[]{
	            java.lang.String.class,
	            java.lang.Float.class,
	            java.lang.Integer.class,
	            JButton.class // la ultima fila es pels botons
	        };
		
		//Carregar info productes
		Object [][]data= new Object[100][3];	
		for (int i=0; i<mainClients.llista_productes.getnElem();i++) {
			data[i][0]=mainClients.llista_productes.getLlista()[i].getNom();
			data[i][1]=mainClients.llista_productes.getLlista()[i].getPreu();			
			data[i][2]=mainClients.llista_productes.getLlista()[i].getEstoc();
		}

		// Initializing the JTable
		j = new JTable(data, columnNames); 
		
		for (int i=0;i<mainClients.llista_productes.getnElem();i++) {

		}
		j.setBounds(30, 40, 200, 300); 
		
		/**
		 * INFORMACIO EN UN CLICK DEL PRODUCTE
		 */
	    
        // Defineixo el TableModel i li indico les dades i noms de columnes
		j.setModel(new javax.swing.table.DefaultTableModel(data, columnNames) {
        	Class[] tipos = tiposColumnas;
        	public Class getColumnClass(int columnIndex) {
                // Aquest mètode és invocat pel CellRenderer per saber que dibuixar a la cel·la, observen que estem retornant la classe que definim per endavant.
                return tipos[columnIndex];
            }
        	@Override
            public boolean isCellEditable(int row, int column) {
        		// Sobreescrivim el metode per evitar que la columna que conté els botons sigui editada.
                return !(this.getColumnClass(column).equals(JButton.class));
            }
        });
        j.setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
            	JLabel etiqueta = new JLabel();

                if(columna == 4){
                    if(estaSeleccionado)
                        etiqueta.setBackground (Color.CYAN);
                    else
                        etiqueta.setBackground (Color.YELLOW);
                }else
                    etiqueta.setBackground (j.getBackground());
            	
            	/**
                 * Retornar l'objecte que es va a dibuixar a la cel·la. 
                 * Això vol dir que es dibuixarà a la cel·la l'objecte que torni el TableModel. 
                 * També vol dir que aquest renderer ens permetria dibuixar qualsevol objecte gràfic a la graella, 
                 * ja que retorna l'objecte tal com el rep.
                 */
                return (Component) objeto;
            }
        });
        j.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = j.rowAtPoint(e.getPoint());
                int columna = j.columnAtPoint(e.getPoint());

               
                if (j.getModel().getColumnClass(columna).equals(JButton.class)) {
                   
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < j.getModel().getColumnCount(); i++) {
                        if (!j.getModel().getColumnClass(i).equals(JButton.class)) {
                            sb.append("\n").append(j.getModel().getColumnName(i)).append(": ").append(j.getModel().getValueAt(fila, i));
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Seleccionada la fila" + fila + sb.toString());
                }
            }
        });

      //Funció de botoCerca
		botoCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LlistaProductes llista_aux=new LlistaProductes();
				boolean selected=false; //Instancia que indica si hi ha alguna cosa seleccionada
				int sel_aux=0, aux=0;
				while (sel_aux<5 && !selected) {
					if (checkList[sel_aux].isSelected()) {
						selected=true;
					}else {
						sel_aux++;
					}
				}
				if (selected) {//Si hi ha seleccionat algun filtre 
					if (!((textField.getText()).equals(""))) {//Hi ha seleccionat un filtre i hi ha text al TextField
						eliminarTaula(j);
						aux=0;
						llista_aux=mainClients.llista_productes.buscarProducte_nom(textField.getText());
						for (int i=0;i<llista_aux.getnElem();i++) {
							if (checkList[0].isSelected()) {
								if((checkList[3].isSelected())&& !(checkList[4].isSelected())) {//Tipus Hardware i amb estoc
									if ((llista_aux.getLlista()[i] instanceof Hardware) && (llista_aux.getLlista()[i].getEstoc()>0)) {
										j.setValueAt(llista_aux.getLlista()[i].getNom(), aux, 0);
										j.setValueAt(llista_aux.getLlista()[i].getPreu(), aux, 1);
										j.setValueAt(llista_aux.getLlista()[i].getEstoc(), aux, 2);
										aux++;
									}
								}else if (!(checkList[3].isSelected()) && (checkList[4].isSelected())) {
									if ((llista_aux.getLlista()[i] instanceof Hardware) && (llista_aux.getLlista()[i].getEstoc()<0)) {
										j.setValueAt(llista_aux.getLlista()[i].getNom(), aux, 0);
										j.setValueAt(llista_aux.getLlista()[i].getPreu(), aux, 1);
										j.setValueAt(llista_aux.getLlista()[i].getEstoc(), aux, 2);
										aux++;
									}
								}else {
									if (llista_aux.getLlista()[i] instanceof Hardware) {
										j.setValueAt(llista_aux.getLlista()[i].getNom(), aux, 0);
										j.setValueAt(llista_aux.getLlista()[i].getPreu(), aux, 1);
										j.setValueAt(llista_aux.getLlista()[i].getEstoc(), aux, 2);
										aux++;
									}
								}
							}
							if (checkList[1].isSelected()) {
								if((checkList[3].isSelected())&& !(checkList[4].isSelected())) {//Tipus Hardware i amb estoc
									if ((llista_aux.getLlista()[i] instanceof Software) && (llista_aux.getLlista()[i].getEstoc()>0)) {
										j.setValueAt(llista_aux.getLlista()[i].getNom(), aux, 0);
										j.setValueAt(llista_aux.getLlista()[i].getPreu(), aux, 1);
										j.setValueAt(llista_aux.getLlista()[i].getEstoc(), aux, 2);
										aux++;
									}
								}else if (!(checkList[3].isSelected()) && (checkList[4].isSelected())) {
									if ((llista_aux.getLlista()[i] instanceof Software) && (llista_aux.getLlista()[i].getEstoc()<0)) {
										j.setValueAt(llista_aux.getLlista()[i].getNom(), aux, 0);
										j.setValueAt(llista_aux.getLlista()[i].getPreu(), aux, 1);
										j.setValueAt(llista_aux.getLlista()[i].getEstoc(), aux, 2);
										aux++;
									}
								}else {
									if (llista_aux.getLlista()[i] instanceof Software) {
										j.setValueAt(llista_aux.getLlista()[i].getNom(), aux, 0);
										j.setValueAt(llista_aux.getLlista()[i].getPreu(), aux, 1);
										j.setValueAt(llista_aux.getLlista()[i].getEstoc(), aux, 2);
										aux++;
									}
								}
							}
							if (checkList[2].isSelected()) {
								if((checkList[3].isSelected())&& !(checkList[4].isSelected())) {//Tipus Hardware i amb estoc
									if ((llista_aux.getLlista()[i] instanceof Configuracio) && (llista_aux.getLlista()[i].getEstoc()>0)) {
										j.setValueAt(llista_aux.getLlista()[i].getNom(), aux, 0);
										j.setValueAt(llista_aux.getLlista()[i].getPreu(), aux, 1);
										j.setValueAt(llista_aux.getLlista()[i].getEstoc(), aux, 2);
										aux++;
									}
								}else if (!(checkList[3].isSelected()) && (checkList[4].isSelected())) {
									if ((llista_aux.getLlista()[i] instanceof Configuracio) && (llista_aux.getLlista()[i].getEstoc()<0)) {
										j.setValueAt(llista_aux.getLlista()[i].getNom(), aux, 0);
										j.setValueAt(llista_aux.getLlista()[i].getPreu(), aux, 1);
										j.setValueAt(llista_aux.getLlista()[i].getEstoc(), aux, 2);
										aux++;
									}
								}else {
									if (llista_aux.getLlista()[i] instanceof Configuracio) {
										j.setValueAt(llista_aux.getLlista()[i].getNom(), aux, 0);
										j.setValueAt(llista_aux.getLlista()[i].getPreu(), aux, 1);
										j.setValueAt(llista_aux.getLlista()[i].getEstoc(), aux, 2);
										aux++;
									}
								}
							}
						}
					}else {
						eliminarTaula(j);
						aux=0;
						for (int i=0;i<mainClients.llista_productes.getnElem();i++) {
							if ((checkList[0].isSelected())) {
								if((checkList[3].isSelected())&& !(checkList[4].isSelected())) {//Tipus Hardware i amb estoc
									if ((mainClients.llista_productes.getLlista()[i] instanceof Hardware) && (mainClients.llista_productes.getLlista()[i].getEstoc()>0)) {
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getNom(), aux, 0);
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getPreu(), aux, 1);
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getEstoc(), aux, 2);
										aux++;
									}
								}else if (!(checkList[3].isSelected()) && (checkList[4].isSelected())) {
									if ((mainClients.llista_productes.getLlista()[i] instanceof Hardware) && (mainClients.llista_productes.getLlista()[i].getEstoc()<0)) {
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getNom(), aux, 0);
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getPreu(), aux, 1);
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getEstoc(), aux, 2);
										aux++;
									}
								}else {
									if (mainClients.llista_productes.getLlista()[i] instanceof Hardware) {
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getNom(), aux, 0);
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getPreu(), aux, 1);
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getEstoc(), aux, 2);
										aux++;
									}
								}
							}
							if (checkList[1].isSelected()) {
								if((checkList[3].isSelected())&& !(checkList[4].isSelected())) {//Tipus Hardware i amb estoc
									if ((mainClients.llista_productes.getLlista()[i] instanceof Software) && (mainClients.llista_productes.getLlista()[i].getEstoc()>0)) {
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getNom(), aux, 0);
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getPreu(), aux, 1);
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getEstoc(), aux, 2);
										aux++;
									}
								}else if (!(checkList[3].isSelected()) && (checkList[4].isSelected())) {
									if ((mainClients.llista_productes.getLlista()[i] instanceof Software) && (mainClients.llista_productes.getLlista()[i].getEstoc()<0)) {
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getNom(), aux, 0);
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getPreu(), aux, 1);
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getEstoc(), aux, 2);
										aux++;
									}
								}else {
									if (mainClients.llista_productes.getLlista()[i] instanceof Software) {
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getNom(), aux, 0);
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getPreu(), aux, 1);
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getEstoc(), aux, 2);
										aux++;
									}
								}
							}
							if (checkList[2].isSelected()) {
								if((checkList[3].isSelected())&& !(checkList[4].isSelected())) {//Tipus Hardware i amb estoc
									if ((mainClients.llista_productes.getLlista()[i] instanceof Configuracio) && (mainClients.llista_productes.getLlista()[i].getEstoc()>0)) {
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getNom(), aux, 0);
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getPreu(), aux, 1);
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getEstoc(), aux, 2);
										aux++;
									}
								}else if (!(checkList[3].isSelected()) && (checkList[4].isSelected())) {
									if ((mainClients.llista_productes.getLlista()[i] instanceof Configuracio) && (mainClients.llista_productes.getLlista()[i].getEstoc()<0)) {
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getNom(), aux, 0);
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getPreu(), aux, 1);
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getEstoc(), aux, 2);
										aux++;
									}
								}else {
									if (mainClients.llista_productes.getLlista()[i] instanceof Configuracio) {
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getNom(), aux, 0);
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getPreu(), aux, 1);
										j.setValueAt(mainClients.llista_productes.getLlista()[i].getEstoc(), aux, 2);
										aux++;
									}
								}
							}									
						}
					}
				}else {
					if (!((textField.getText()).equals(""))) {
						eliminarTaula(j);
						llista_aux=mainClients.llista_productes.buscarProducte_nom(textField.getText());
						for (int i=0;i<llista_aux.getnElem();i++) {
							j.setValueAt(llista_aux.getLlista()[i].getNom(), i, 0);
							j.setValueAt(llista_aux.getLlista()[i].getPreu(), i, 1);
							j.setValueAt(llista_aux.getLlista()[i].getEstoc(), i, 2);
						}
					}else {
						eliminarTaula(j);
						for (int i=0;i<mainClients.llista_productes.getnElem();i++) {
							j.setValueAt(mainClients.llista_productes.getLlista()[i].getNom(), i, 0);
							j.setValueAt(mainClients.llista_productes.getLlista()[i].getPreu(), i, 1);
							j.setValueAt(mainClients.llista_productes.getLlista()[i].getEstoc(), i, 2);
						}
					}
				}
			}
		});
		
		
		
		//Funcio boto comanda
		botoComanda.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//INICIALITZEM LA FINESTRA
				JFrame fComandes=new JFrame("Realitzar comanda");
				JPanel panell=new JPanel();
				JCheckBox[] checkComanda=new JCheckBox[100];
				int i =0;
				while (j.getValueAt(i, 0)!= null) {
					checkComanda[i]=new JCheckBox(((String)j.getValueAt(i, 0)), false);
					panell.add(checkComanda[i]);
					i++;
				}
				panell.setLayout(new GridLayout(i,1));
				JScrollPane scroll= new JScrollPane(panell);
				fComandes.setLayout(new BorderLayout());
				fComandes.setSize(300,500);
				fComandes.setVisible(true);
				fComandes.add(scroll, BorderLayout.EAST);
				fComandes.add(panell, BorderLayout.WEST);
				
				JButton confirmar=new JButton("CONFIRMA COMANDA");
				fComandes.add(confirmar, BorderLayout.NORTH);
				confirmar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int reply = JOptionPane.showConfirmDialog(null, "N'estas segur de realitzar la comanda?", "CONFIRMAR COMANDA", JOptionPane.YES_NO_CANCEL_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							
						}else {
							
						}	
					}
				});
			}
		});
				
	
		// Adding it to JScrollPane 
		JScrollPane sp = new JScrollPane(j); 

		//Per ferho tot visible 
		finestra.setLayout(new FlowLayout());
		finestra.setSize(800, 550);
		finestra.setVisible(true);
		finestra.add(textField);
		finestra.add(botoCerca);
		finestra.add(botoComanda);
		finestra.add(filtres);
		finestra.add(sp);
		finestra.add(botoRetorna);
		finestra.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}



	public static void main(String[] args) {
		new FinestraBuscarProductes();
	}
	
	public void eliminarTaula(JTable taula) {
		for (int i=0;i<taula.getColumnCount();i++) {
			for (int j=0;j<taula.getRowCount();j++) {
				taula.setValueAt(null, j, i);
			}
		}
	}

}

