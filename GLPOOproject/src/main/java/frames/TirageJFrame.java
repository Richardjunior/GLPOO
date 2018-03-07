package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.*;
import dao.*;
import fractales.Fractale;
import fractales.TirageToFractaleConverter;



public class TirageJFrame extends JFrame {
	private ModeleDynamique modele = new ModeleDynamique();
	private final JTable tableau = new JTable(modele);

	public TirageJFrame() {
		
		super();
		setTitle("Liste des tirages");
		setPreferredSize(new Dimension(1068, 576));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		tableau.setBackground(Color.DARK_GRAY);
		tableau.setForeground(Color.WHITE);
		tableau.setRowHeight(25);
		tableau.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(Color.BLACK);
		
		JButton button_julia = new JButton(new GenerateWtihCSV_Julia());
		button_julia.setBackground(Color.BLACK);
		button_julia.setForeground(Color.WHITE);
		
		buttonPane.add(button_julia);
		
		JButton button_mandelbrot = new JButton(new GenerateWtihCSV_Mandelbrot());
		button_mandelbrot.setBackground(Color.BLACK);
		button_mandelbrot.setForeground(Color.WHITE);
		
		buttonPane.add(button_mandelbrot);
		
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

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
		
		JMenuItem test2 = new JMenuItem(new GenerateWtihCSV_Julia());
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
			//f.setVisible(true);
		}
    	
    }
	

	private class GenerateWtihCSV_Julia extends AbstractAction {
		
		private TirageToFractaleConverter converter;
		private FractaleJFrame fractaleFrame;

		private GenerateWtihCSV_Julia() {
			super("Generate draw with Julia");
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
				converter = new TirageToFractaleConverter(tirages,"Julia");
				List<Fractale> fractales = converter.convertTirageToFractale();
				
				fractaleFrame = new FractaleJFrame(fractales,800,800);
			}else {
				System.out.println("Aucun tirage séléctionné !");
				//LOGGER.debug("Aucun tirage séléctionné !");
			}
		}

	}
	
	private class GenerateWtihCSV_Mandelbrot extends AbstractAction {
		
		private TirageToFractaleConverter converter;
		private FractaleJFrame fractaleFrame;

		private GenerateWtihCSV_Mandelbrot() {
			super("Generate draw with Mandelbrot");
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
				converter = new TirageToFractaleConverter(tirages,"Mandelbrot");
				List<Fractale> fractales = converter.convertTirageToFractale();
				
				fractaleFrame = new FractaleJFrame(fractales,800,800);
			}else {
				System.out.println("Aucun tirage séléctionné !");
				//LOGGER.debug("Aucun tirage séléctionné !");
			}
		}

	}
}
