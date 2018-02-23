package IHM.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import IHM.domain.Tirage;
import IHM.models.ModeleDynamique;
import fractales.domain.TirageToFractale;



public class TirageJFrame extends JFrame {
	private ModeleDynamique modele = new ModeleDynamique();
	private final JTable tableau = new JTable(modele);

	public TirageJFrame() {
		super();
		setTitle("Liste des tirages");
		setPreferredSize(new Dimension(800, 900));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);

		tableau.setAutoCreateRowSorter(true);
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableau.getModel());
		tableau.setRowSorter(sorter);

		build();

		pack();
		setLocationRelativeTo(null);
	}

	private void build() {
		JMenuBar menuBar = new JMenuBar();

		// MENU 1
		JMenu menu1 = new JMenu("Fichier");

		JMenuItem test = new JMenuItem(new Test1());
		menu1.add(test);
		
		JMenuItem test2 = new JMenuItem(new Test2());
		menu1.add(test2);

		// MENU BAR
		menuBar.add(menu1);

		setJMenuBar(menuBar);
	}

	private class Test1 extends AbstractAction {

		private Test1() {
			super("tryit");
		}

		public void actionPerformed(ActionEvent e) {
			setTitle("Liste des tirages (v2)");
			setPreferredSize(new Dimension(400, 500));
			setDefaultCloseOperation(EXIT_ON_CLOSE);

			pack();
		}
	}

	private class Test2 extends AbstractAction {

		private Test2() {
			super("recup données");
		}
		public void actionPerformed(ActionEvent e) {
			
			Tirage tirage = modele.rowsToTirage(tableau.getSelectedRow());
			dispose();
			new TirageToFractale(tirage);

		}

	}
}
