package interficieGrafica;
import javax.swing.JButton;

public class BotoGuardar extends JButton {


		private static final long serialVersionUID = 1L;

		private String codi;

		/**
		 * Constructor de BotoLlibre.
		 * @param titol  és el titol del llibre
		 * @param codi  es el codi del llibre del llibre
		 */
		public BotoGuardar (String titol, String codi) {
			super(titol);
			this.codi = codi;
		}

		/**
		 * Retorna el codi del llibre.
		 * @return codi
		 */
		public String getCodi() {
			return codi;
		}

	}
