package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JEditorPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.AbstractAction;
import javax.swing.ComboBoxEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

//import org.apache.log4j.Logger;

import dao.*;
import frames.*;

public class WelcomeJFrame extends JFrame {

	// private static final Logger LOGGER = Logger.getLogger(WelcomeJFrame.class);

	private JFrame frmMainMenu;

	final private String[] gameMode = { "Tirage CSV", "Tirage aléatoire", "Tirage choisi" };
	private JComboBox comboBox = new JComboBox(gameMode);
	private CsvTirageDao dao;
	private String tirage;

	public WelcomeJFrame() {
		initialize();
		this.frmMainMenu.setVisible(true);
	}

	private void initialize() {

		frmMainMenu = new JFrame();
		frmMainMenu.setTitle("Main menu");
		frmMainMenu.setResizable(false);
		frmMainMenu.setBounds(100, 100, 1068, 576);
		frmMainMenu.setLocationRelativeTo(null);
		frmMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainMenu.getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(0, 1, 1052, 573);
		frmMainMenu.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 215, 534);
		panel_1.add(panel);
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(10, 167, 175, 55);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JButton btnNewButton = new JButton(new Jouer());
		btnNewButton.setBounds(0, 0, 175, 55);
		panel_2.add(btnNewButton);
		btnNewButton.setFont(new Font("OCR A Extended", Font.PLAIN, 22));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton button_1 = new JButton("Crédits");
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(Color.BLACK);
		button_1.setFont(new Font("OCR A Extended", Font.PLAIN, 17));
		button_1.setBounds(10, 445, 195, 40);
		panel.add(button_1);

		JButton button = new JButton(new Bonus());
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLACK);
		button.setFont(new Font("OCR A Extended", Font.PLAIN, 17));
		button.setBounds(10, 382, 195, 40);
		panel.add(button);

		JButton btnNewButton_1 = new JButton("Options");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("OCR A Extended", Font.PLAIN, 17));
		btnNewButton_1.setBackground(Color.BLACK);
		btnNewButton_1.setBounds(10, 320, 195, 40);
		panel.add(btnNewButton_1);

		JLabel lblWelcome = new JLabel("WELCOME");
		lblWelcome.setFont(new Font("OCR A Extended", Font.BOLD, 29));
		lblWelcome.setForeground(Color.WHITE);
		lblWelcome.setBounds(38, 32, 146, 40);
		panel.add(lblWelcome);

		JLabel label_1 = new JLabel(" EURO-PSYCHO");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		label_1.setBounds(24, 75, 181, 40);
		panel.add(label_1);

		// Choix du mode de jeu

		comboBox.setForeground(Color.WHITE);
		comboBox.setBackground(Color.BLACK);
		comboBox.setBounds(10, 167, 195, 55);
		panel.add(comboBox);

		JLabel label = new JLabel("");
		label.setBounds(70, 11, 982, 513);
		panel_1.add(label);
		label.setIcon(new ImageIcon("src\\main\\ressources\\Accueil1.jfif"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		pack();
	}

	private class Jouer extends AbstractAction {

		private Jouer() {
			super("Jouer");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (comboBox.getSelectedIndex()) {
			case 0:
				frmMainMenu.dispose();
				JFrame f = new TirageJFrame();
				f.setVisible(true);
				pack();
				break;
			case 1:
				// LOGGER.debug("Pas encore développé !");
				System.out.println("Pas encore développé !");
				break;
			case 2:
				tirage = JOptionPane.showInputDialog(null,
						"Veuillez entrer votre tirage sous la forme : b1,b2,b3,b4,b5,e1,e2",
						"Entée d'un tirage par l'utilisateur", JOptionPane.QUESTION_MESSAGE);
				System.out.println(tirage);
				// Tirage newTirage = new SimpleTirage(tirage);
				dispose();
				// new TirageToFractaleJulia(newTirage);
				break;
			default:
				System.out.println("Pas encore développé !");
				// LOGGER.info("index inconnu !");
			}
		}

	}

	private class Options extends AbstractAction {

		public Options() {
			super("Options");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// LOGGER.debug("Pas encore developpé");
		}

	}

	private class Bonus extends AbstractAction {

		public Bonus() {
			super("Bonus");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {

			switch (comboBox.getSelectedIndex()) {
			case 0:
				frmMainMenu.dispose();
				JFrame f = new TirageJFrameBonus();
				f.setVisible(true);
				pack();
				break;
			}

		}

	}

	private class Credits extends AbstractAction {

		public Credits() {
			super("Crédits");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// LOGGER.debug("Pas encore developpé");
		}

	}
}
