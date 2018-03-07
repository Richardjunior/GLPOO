package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.ModeleDynamique;

public class TirageJFrameBonus extends JFrame {
	private ModeleDynamique modele = new ModeleDynamique();
	private final JTable tableau = new JTable(modele);

	public TirageJFrameBonus() {
		super();
		setTitle("Liste des tirages (Bonus)");
		setPreferredSize(new Dimension(1068, 576));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		tableau.setBackground(Color.DARK_GRAY);
		tableau.setForeground(Color.WHITE);
		tableau.setRowHeight(25);
		tableau.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(Color.BLACK);

		JButton button_puzzle = new JButton(new GeneratePuzzleImg());
		button_puzzle.setBackground(Color.BLACK);
		button_puzzle.setForeground(Color.WHITE);

		buttonPane.add(button_puzzle);

		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		tableau.setAutoCreateRowSorter(true);
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableau.getModel());
		tableau.setRowSorter(sorter);

		pack();
		setLocationRelativeTo(null);

	}

	private class GeneratePuzzleImg extends AbstractAction {
		private GeneratePuzzleImg() {
			super("Generate Puzzle Image");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Bouton Puzzle");
			JFrame frame = new ImagePuzzleJFrame();
			frame.setVisible(true);

		}
	}

}
