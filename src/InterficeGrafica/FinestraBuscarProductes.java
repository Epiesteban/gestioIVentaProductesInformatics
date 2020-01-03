package InterficeGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controladors.mainClients;

public class FinestraBuscarProductes extends JFrame implements ChangeListener{
	JPanel panel;
	JCheckBox check1,check2,check3;
	
	public FinestraBuscarProductes() {
		super();
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		this.setSize(500,500);
		
		this.setTitle("BUSCAR PRODUCTES");
		String nom=JOptionPane.showInputDialog("Introdueix el nom del producte que busca: ");
		while (mainClients.llista_productes.buscarProducte_nom(nom) == null) {
			// Missatge d'error.
			JOptionPane.showMessageDialog(null, "No existeix cap producte amb aquest nom", "ERROR", JOptionPane.ERROR_MESSAGE);
			nom = JOptionPane.showInputDialog("Introdueix el nom");
		}
		JOptionPane.showMessageDialog(null,"Productes relacionats amb la seva busqueda: \n"+mainClients.llista_productes.buscarProducte_nom(nom).toString() );
		 
		
		this.panel.setBackground(Color.pink);
		this.getContentPane().setLayout(new BorderLayout());
		this.setBackground(Color.pink);
		
		//afegim filtres
        check1=new JCheckBox("Hardware");
        check1.setBounds(10,1,500,30);
        check1.addChangeListener(this); //canviarà l'estat dels filtres
        add(check1).setVisible(true);
        
        check2=new JCheckBox("Software");
        check2.setBounds(10,15,500,30);
        check2.addChangeListener(this);        
        add(check2).setVisible(true);
        
        check3=new JCheckBox("Configuracions");
        check3.setBounds(10,20,500,30);
        check3.addChangeListener(this);        
        add(check3).setVisible(true);;        
		
	}
	
	//que fa cada filtre
	 public void stateChanged(ChangeEvent e){
        String cad="";
        if (check1.isSelected()==true) {
            //MOSTRAR HARDWARE
        }
        if (check2.isSelected()==true) {
        	//MOSTRAR SOFTWARE
        }
        if (check3.isSelected()==true) {
        	//MOSTRAR CONFIGURACIONS
        }
        setTitle(cad);
    }

    public static void main(String[] ar) {
        FinestraBuscarProductes filtres1 = new FinestraBuscarProductes();
        filtres1.setVisible(true);
    }    
}

