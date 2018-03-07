package frames;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import dao.Tirage;
import fractales.Fractale;
import fractales.TirageToFractaleConverter;
import model.ModeleDynamique;



public class TirageJFrameV1 extends JFrame {
	private ModeleDynamique modele = new ModeleDynamique();
	private final JTable tableau = new JTable(modele);
	private JDialog dialog = new JDialog();
	
	public TirageJFrameV1() {
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
		
		JMenuItem test2 = new JMenuItem(new Dessiner());
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
			WelcomeJFrameV1 f = new WelcomeJFrameV1();
			f.setVisible(true);
		}
    	
    }
	

	private class Dessiner extends AbstractAction {
		
		private TirageToFractaleConverter converter;
		private FractaleJFrame fractaleFrame;

		private Dessiner() {
			super("Dessiner");
		}
		public void actionPerformed(ActionEvent e) {
			if (tableau.getSelectedRow() > -1) {
				//LOGGER.debug("Tirage séléctionné : ");
			
				List<Tirage> tirages = new ArrayList<Tirage>();
				
				
				final int[] selection = tableau.getSelectedRows();
				
				if (selection.length > 4) {
					JOptionPane.showMessageDialog(null,"Trop de lignes sélectionnées, le résultat risque de ne pas être agréable pour les yeux!");
				}else {
	
					for (int i = selection.length - 1; i >= 0; i--) {
						Tirage tirage = modele.rowToTirage(selection[i]);
						tirages.add(tirage);
					}

					Object[] possibilities = {"Julia", "Mandelbrot"};
					String s = (String)JOptionPane.showInputDialog(
					                    null,
					                    "Choose a type of fractale",
					                    "May the force be with you",
					                    JOptionPane.PLAIN_MESSAGE,
					                    null,
					                    possibilities,
					                    "Julia");

					//If a string was returned, say so.
					if ((s != null) && (s.length() > 0)) {
						dispose();
						converter = new TirageToFractaleConverter(tirages,s);
						List<Fractale> fractales = converter.convertTirageToFractale();
						
						fractaleFrame = new FractaleJFrame(fractales,800,800);
					}
				}
			}else {
				JOptionPane.showMessageDialog(null,"Sélectionnez au moins une ligne.");
				//LOGGER.debug("Aucun tirage séléctionné !");
			}
		}

	}
}
