package interficieGrafica;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AccioTancarFinestraEmergent {
	public class AccioTancarFinestra implements WindowListener {

		private ProgramaPrincipal finestra;
		
		/**
		 * Constructor de l'acci�.
		 * @param finestra
		 */
		AccioTancarFinestra (ProgramaPrincipal finestra) {
			this.finestra = finestra;
		}

		/**
		 * Acci� que s'executa al tancar la finestra principal.
		 * Crida al m�tode handleClosing de la classe FinestraPrincipal.
		 */
		@Override
		public void windowClosed(WindowEvent e) {
			finestra.dispose();
		}

		/**
		 * Acci� que s'executa al tancar la finestra principal.
		 * Crida al m�tode handleClosing de la classe FinestraPrincipal.
		 */
		@Override
		public void windowClosing(WindowEvent e) {
			finestra.dispose();
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

}
}
