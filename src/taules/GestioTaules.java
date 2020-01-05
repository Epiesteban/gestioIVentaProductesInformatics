package taules;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;



/**
 * Permet gestionar la taula i els events que es fan en ella 
 */
public class GestioTaules extends DefaultTableCellRenderer{
	
	private String tipus = "text";

	//Per defecte definim com volem que siguin les taules i amb quina lletra 
	private Font normal = new Font( "Verdana",Font.PLAIN ,12 );
	private Font bold = new Font( "Verdana",Font.BOLD ,12 );
	

	/**
	 * Constructor buit
	 */
	public GestioTaules(){
		
	}

	/**
	 * Constructor
	 * @param tipus
	 */
	public GestioTaules (String tipus){
		this.tipus = tipus;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
		//Aquest metode controla tota la taula, podem obtenir el valor que conté, definir quina casella esta seleccionada, i el color segons si la informacio es numerica o no
		
		
		//Definim alguns colors per defecte
        Color colorFondo = null;
        Color colorFondoPerDefecte = new Color( 192, 192, 192);
        Color colorFondoSeleccio = new Color( 140, 140 , 140);
    	
    
        //Si la casella es la seleccionada se li assigna un color per defecte 
        if (selected) {                
            this.setBackground(colorFondoPerDefecte );   
        }
        else
        {
        	//Per les que no estan seleccionades el fons de la casella es pinta en blanc
            this.setBackground(Color.white);
        }
                
        
        //Defineix si el el disseny segons si es tracta de si es numeric o si es tracta d'una paraula 
        if(tipus.equals("text"))
        {
            if (focused) {
    			colorFondo = colorFondoSeleccio;
    		}else{
    			colorFondo = colorFondoPerDefecte;
    		}
            this.setHorizontalAlignment( JLabel.LEFT );
            this.setText( (String) value );
            //this.setForeground( (selected)? new Color(255,255,255) :new Color(0,0,0) );   
            //this.setForeground( (selected)? new Color(255,255,255) :new Color(32,117,32) );
            this.setBackground( (selected)? colorFondo :Color.WHITE);	
            this.setFont(normal);   
            //this.setFont(bold);
            return this;
        }
         
        if(tipus.equals("numeric"))
        {           
        	if (focused) {
     			colorFondo = colorFondoSeleccio;
     		}else{
     			colorFondo = colorFondoPerDefecte;
     		}
        	// System.out.println(value);
            this.setHorizontalAlignment( JLabel.CENTER );
            this.setText( (String) value );            
            this.setForeground( (selected)? new Color(255,255,255) :new Color(32,117,32) );    
            this.setBackground( (selected)? colorFondo :Color.WHITE);
           // this.setBackground( (selected)? colorFondo :Color.MAGENTA);
            this.setFont(bold);            
            return this;   
        }
		
		return this;
	
	}
	
}
