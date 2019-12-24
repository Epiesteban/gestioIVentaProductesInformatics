package interficieGrafica;

import models.LlistaClients;
import models.LlistaComandes;
import models.LlistaProductes;

public class FinestraProducte extends JFrame {

/**
	 * Aques JFrame implementa la finestra que mostra la informaci� d'un llibre seleccionat de la llista de llibres disponibles.
	 * @author Aitor Arjona (aitor.arjona@estudiants.urv.cat)
	 * @author Cesc Ferr� (francesc.ferre@estudiants.urv.cat)
	 * @author Aleix Sancho (aleix.sancho@estudiants.urv.cat)
	 * @author Miquel Buxons (miquel.buxons@estudiants.urv.cat)
	 *
	 */

		private static final long serialVersionUID = 1L;

		// Dades usuari
		private String nom;
		private String dni;
		private String correu;
		
		// Refer�ncies de les llistes
		private LlistaProductes ll_Llibres;
		private LlistaComandes ll_comandes;
		private LlistaClients ll_clients;
		
		// Refer�ncia a la finestra principal
		private ProgramaPrincipal finestraPrincipal;

		/**
		 * Constructor de FinestraLlibre.
		 * @param codi del llibre.
		 * @param esSoci que indica si l'usuari es soci o no.
		 * @param dni del usuari.
		 * @param finestraPrincipal Refer�ncia a FinestraPrincipal.
		 * @param ll_Llibres Refer�ncia a la llista de llibres.
		 * @param ll_LlibresPrestec Refer�ncia a la llista de llibres en pr�stec.
		 * @param ll_LlibresReservats Refer�ncia a la llista de llibres reservats.
		 */
		public FinestraProducte (String codi, boolean esSoci, String dni, FinestraPrincipal finestraPrincipal,
				LlistaLlibres ll_Llibres, LlistaLlibresPrestec ll_LlibresPrestec, LlistaLlibresReservats ll_LlibresReservats) {
			
			super(codi);
			setSize(400, 250);
			setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			this.ll_Llibres = ll_Llibres;
			this.ll_LlibresPrestec = ll_LlibresPrestec;
			this.ll_LlibresReservats = ll_LlibresReservats;
			
			this.finestraPrincipal = finestraPrincipal;

			Llibre llibre = new Llibre();

			this.codi = codi;
			this.esSoci = esSoci;
			this.dni = dni;

			try {
				llibre = ll_Llibres.getLlibre(codi);
			} catch (LlibreNoTrobatException e) {
				System.out.println("Error");
			}

			Container container = getContentPane();
			container.setLayout(new BorderLayout());

			JLabel titol = new JLabel("Titol: " + llibre.getTitol());
			JLabel autors = new JLabel();
			String[] autorsAux = llibre.getAutors();
			String labelAux = "Autor(s): ";
			for (int i = 0; i < autorsAux.length; i++) {
				if (i > 0)
					labelAux += ",";
				labelAux += " " + autorsAux[i];
			}
			autors.setText(labelAux);
			JLabel tema = new JLabel("Tema: " + llibre.getTema());
			JLabel edicio = new JLabel("Edicio: " + llibre.getEdicio());
			JLabel any = new JLabel("Any: " + llibre.getAny());
			JLabel codiLabel = new JLabel("Codi: " + llibre.getCodi());

			JButton buttonPrestec = new JButton();
			if(esSoci) {
				buttonPrestec = new JButton("Demanar en pr�stec");
				buttonPrestec.addActionListener(new AccioPrestecReservaLlibre(this));
			}
			JButton buttonReserva = new JButton("Reservar per consulta en biblioteca");
			buttonReserva.addActionListener(new AccioPrestecReservaLlibre(this));

			JPanel infoPanel = new JPanel();
			infoPanel.setLayout(new GridLayout(8, 1));

			infoPanel.add(titol);
			infoPanel.add(autors);
			infoPanel.add(tema);
			infoPanel.add(edicio);
			infoPanel.add(any);
			infoPanel.add(codiLabel);
			if(esSoci) infoPanel.add(buttonPrestec);
			infoPanel.add(buttonReserva);

			container.add(infoPanel, BorderLayout.CENTER);

			setLocationRelativeTo(null);
			setVisible(true);
		}

		/**
		 * M�tode que s'executa al pr�mer el bot� efectuar pr�stec de la FinestraLlibre.
		 * Efectua el pr�stec del llibre seleccionat per al usuari registrat en aquell moment.
		 */
		public void prestecLlibre() {
			LlibrePrestec prova = new LlibrePrestec();
			Llibre siLlibreCientific;
			Data inici = new Data();
			Data inicial, dataFi;

			int pos = ll_Llibres.estaAquestLlibre(codi);
			try {
				if (pos == -1 || !ll_Llibres.getLlibre(codi).isDisponible()) {
					throw new NoEsPotAfegirPrestecException();
				} else {
					boolean prestec = ll_LlibresPrestec.estaEnPrestec(codi);
					if (!prestec) {

						inicial = inici.dataActual();

						siLlibreCientific = ll_Llibres.getLlibre(codi);
						if (siLlibreCientific instanceof LlibreCientific) {
							int hoes = 1;
							int dies = ((LlibreCientific) siLlibreCientific).getDataRetorn();
							dataFi = inicial.datafi(hoes, dies);
						} else {
							int hoes = 0;
							int dies = 0;
							dataFi = inicial.datafi(hoes, dies);
						}
						Hora horaFi = new Hora(dataFi.getDia(), dataFi.getMes(), dataFi.getAny(), 0, 0);
						boolean reserva = ll_LlibresReservats.estaReservat(codi, horaFi);
						if (!reserva) {
							prova = new LlibrePrestec(codi, dni, inicial, dataFi);
							ll_LlibresPrestec.afegirPrestec(prova);
							ll_Llibres.disponibilitat(codi);
							finestraPrincipal.actualitzarLlistaLlibres("");
						} else {
							throw new LlibreReservatEnAquestTerminiException();
						}

						JOptionPane.showMessageDialog(null,
								"S'ha demanat el pr�stec del llibre "
										+ ll_Llibres.getLlibre(codi).getTitol() + " correctament.",
								"Llibre Afegit Correctament", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			} catch (NoEsPotAfegirPrestecException exc) {
				// No es pot afegir
				JOptionPane.showMessageDialog(null,
						"No s'ha pogut demanar en pr�stec.\n" + "(No es pot afegir a la llista de llibres en pr�stec)",
						"Error", JOptionPane.ERROR_MESSAGE);
			} catch (LlibreReservatEnAquestTerminiException exc) {
				// Est� en reserva
				JOptionPane.showMessageDialog(null,
						"No s'ha pogut demanar en pr�stec.\n" + "(Aquest llibre ja est� en reserva)", "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (LlibreNoTrobatException exc) {
				// No s'ha trobat llibre
				JOptionPane.showMessageDialog(null, "No s'ha pogut demanar en pr�stec.\n" + "(No s'ha trobat el llibre)",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			
			finestraPrincipal.canvisEfectuats();

			dispose();
		}
		
		/**
		 * M�tode que s'executa al pr�mer el bot� de reservar llibre.
		 * Obre la finestra de fixar la data de reserva.
		 */
		public void reservaLlibre() {
			new FinestraData(esSoci, this);
		}

		/**
		 * M�tode que s'executa al fixar la data de reserva.
		 * Reserva un llibre per a una Hora determinada per al usuari registrat en aquell moment.
		 * @param horaReserva  �s de la reserva.
		 */
		public void realitzarReservaLlibre(Hora horaReserva) {
			boolean jaReservat = ll_LlibresReservats.estaReservat(codi, horaReserva);
			boolean jaPrestat =  ll_LlibresPrestec.estaPrestat(codi, horaReserva);
			boolean maximDia = ll_LlibresReservats.maximReservesPerDia(horaReserva);
			if (!jaReservat && !jaPrestat && maximDia) {
				LlibreReservat nouReserv = new LlibreReservat(codi, dni, horaReserva, esSoci);
				ll_LlibresReservats.afegirReservat(nouReserv);
				try {
					JOptionPane.showMessageDialog(null,
							"S'ha reservat el llibre "
									+ ll_Llibres.getLlibre(codi).getTitol() + " per al d�a." +
									ll_LlibresReservats.getLlibre(codi).getHoraRetorn().toString()+".",
							"Llibre Afegit Correctament", JOptionPane.INFORMATION_MESSAGE);
					finestraPrincipal.actualitzarLlistaLlibres("");
				} catch (LlibreNoTrobatException e) {
					JOptionPane.showMessageDialog(null, "No s'ha pogut reservar.\n" + "(No s'ha trobat el llibre en la llista de llibres reservats)",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			if (jaReservat) {
				JOptionPane.showMessageDialog(null, "No s'ha pogut reservar.\n" + "(Ja est� reservat)",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			if (jaPrestat) {
				JOptionPane.showMessageDialog(null, "No s'ha pogut reservar.\n" + "(Est� en pr�stec)",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			dispose();
		}

	}

}
