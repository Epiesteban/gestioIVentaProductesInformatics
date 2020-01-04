package InterficeGrafica;


import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controladors.mainClients;

public class FinestraFiltresBuscarProductes extends JFrame implements ChangeListener{
	private JCheckBox check1,check2,check3;
	private JButton boto1, boto2;
	
	
	public FinestraFiltresBuscarProductes() {
		
		this.setTitle("FILTRES DE BUSQUEDA");
		//String nom=JOptionPane.showInputDialog("Introdueix el nom del producte que busca: ");
		//while (mainClients.llista_productes.buscarProducte_nom(nom) == null) {
			// Missatge d'error.
			//JOptionPane.showMessageDialog(null, "No existeix cap producte amb aquest nom", "ERROR", JOptionPane.ERROR_MESSAGE);
			//nom = JOptionPane.showInputDialog("Introdueix el nom");
		//}
		//JOptionPane.showMessageDialog(null,"Productes relacionats amb la seva busqueda: \n"+mainClients.llista_productes.buscarProducte_nom(nom).toString() );
		 setLayout(null);

		
		/**
		 * FILTRES HARDWARE, SOFTWARE I CONFIGURACIONS 
		 */
		//afegim filtres
        check1=new JCheckBox("Hardware");
        check1.setBounds(10,10,150,30);
        check1.addChangeListener(this); //canviarà l'estat dels filtres
        add(check1).setVisible(true);
        
        check2=new JCheckBox("Software");
        check2.setBounds(10,50,150,30);
        check2.addChangeListener(this);        
        add(check2).setVisible(true);
        
        check3=new JCheckBox("Configuracions");
        check3.setBounds(10,90,150,30);
        check3.addChangeListener(this);        
        add(check3).setVisible(true);       
		
        //afegim botons (acceptar/cancelar) 
        
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

		this.setVisible(true);
    }

    public static void main(String[] ar) {
        FinestraFiltresBuscarProductes filtres1 = new FinestraFiltresBuscarProductes();
        filtres1.setBounds(0,0,300,200);
        filtres1.setVisible(true);
    }    
}
