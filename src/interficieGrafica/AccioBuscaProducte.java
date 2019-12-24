package interficieGrafica;
import java.awt.event.*;
import javax.swing.*;


public class AccioBuscaProducte implements ActionListener {

		private ProgramaPrincipal finestra;
	
		public AccioBuscaProducte (ProgramaPrincipal finestra) {
			this.finestra = finestra;
		}
		
		/**
		 * Acció de botó cerca que crida al mètode cercaLlibre de la finestra principal.
		 * Extreu el String del camp de cerca.
		 */
		public void actionPerformed (ActionEvent event) {
			String text = "";
			
			if (event.getSource() instanceof JTextField) {
				JTextField textField = (JTextField) event.getSource();
				text = textField.getText();
			} else if (event.getSource() instanceof JButton) {
				text = finestra.getTextSearch();
			}		
			finestra.cercaProducte(text);
		}
	}
