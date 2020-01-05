package InterficeGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class AccioTancaPestanyaComandes implements ActionListener {
	private FinestraConsultarComandes finestra;
	public AccioTancaPestanyaComandes (FinestraConsultarComandes f) {
		finestra = f;
	}
	public void actionPerformed (ActionEvent e) {
		int reply = JOptionPane.showConfirmDialog(null, "N'estas segur de tornar al menu principal?", "RETORNA AL MENU PRINCIPAL", JOptionPane.YES_NO_CANCEL_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			finestra.setVisible(false);
			new FinestraConsultarComandes();
		}else {
			//No fa res  
		}	
	}
}



