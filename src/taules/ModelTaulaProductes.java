package taules;

import javax.swing.table.DefaultTableModel;

public class ModelTaulaProductes extends DefaultTableModel {
	String[] titols;
	Object[][] dades;
	
	public ModelTaulaProductes(Object[][] dades, String[] titols) {
		super();
		this.titols=titols;
		this.dades=dades;
		setDataVector(dades, titols);
	}
	public ModelTaulaProductes() {
		// TODO Auto-generated constructor stub
	}
	
}
