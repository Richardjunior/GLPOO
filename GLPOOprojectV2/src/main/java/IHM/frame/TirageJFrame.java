package IHM.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

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
		
		JMenuItem test2 = new JMenuItem(new GenerateWtihCSV());
		menu1.add(test2);
		
		JMenuItem backMenu = new JMenuItem(new BackAction("Go back to the previous menu"));
		menu1.add(backMenu);

		// MENU BAR
		menuBar.add(menu1);

		setJMenuBar(menuBar);
	}

	private class BackAction extends AbstractAction{
		private BackAction(String string) {
    		super(string);
    	}
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			WelcomeJFrame f = new WelcomeJFrame();
			f.setVisible(true);
		}
    	
    }
	

	private class GenerateWtihCSV extends AbstractAction {

		private GenerateWtihCSV() {
			super("Generate draw");
		}
		public void actionPerformed(ActionEvent e) {
			if (tableau.getSelectedRow() > -1) {
				//System.out.println("Tirage séléctionné");
				//LOGGER.debug("Tirage séléctionné : ");
			
				List<Tirage> tirages = new ArrayList<Tirage>();
				
				
				final int[] selection = tableau.getSelectedRows();
	
				for (int i = selection.length - 1; i >= 0; i--) {
					Tirage tirage = modele.rowToTirage(selection[i]);
					tirages.add(tirage);
				}
				
				dispose();
				new TirageToFractale(tirages);
			}else {
				System.out.println("Aucun tirage séléctionné !");
				//LOGGER.debug("Aucun tirage séléctionné !");
			}
		}

	}
}
