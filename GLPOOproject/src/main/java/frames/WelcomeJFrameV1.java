package frames;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Canvas;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;

public class WelcomeJFrameV1 extends JFrame {

	private JPanel contentPane;

	
	public WelcomeJFrameV1() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1070, 540);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 219, 505);
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton(new Jouer());
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setForeground(SystemColor.text);
		btnNewButton.setBackground(new Color(178, 34, 34));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(12, 134, 190, 52);
		panel.add(btnNewButton);
		
		JButton btnOptions = new JButton(new Options());
		btnOptions.setFont(new Font("Tahoma", Font.ITALIC, 13));
		btnOptions.setForeground(new Color(255, 255, 255));
		btnOptions.setBackground(new Color(189, 183, 107));
		btnOptions.setBounds(12, 281, 190, 28);
		panel.add(btnOptions);
		
		JButton btnBonus = new JButton( new Bonus());
		btnBonus.setFont(new Font("Tahoma", Font.ITALIC, 13));
		btnBonus.setForeground(new Color(255, 255, 255));
		btnBonus.setBackground(new Color(0, 128, 128));
		btnBonus.setBounds(12, 332, 190, 28);
		panel.add(btnBonus);
		
		JButton btnCredits = new JButton(new Credits());
		btnCredits.setFont(new Font("Tahoma", Font.ITALIC, 13));
		btnCredits.setForeground(new Color(255, 255, 255));
		btnCredits.setBackground(new Color(46, 139, 87));
		btnCredits.setBounds(12, 383, 190, 28);
		panel.add(btnCredits);
		
		JLabel lblWelcome = new JLabel("Welcome in");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblWelcome.setForeground(Color.WHITE);
		lblWelcome.setBackground(Color.WHITE);
		lblWelcome.setBounds(12, 13, 190, 28);
		panel.add(lblWelcome);
		
		JLabel lblInEuropschyco = new JLabel("EURO-PSYCHO");
		lblInEuropschyco.setHorizontalAlignment(SwingConstants.CENTER);
		lblInEuropschyco.setForeground(Color.WHITE);
		lblInEuropschyco.setFont(new Font("Stencil", Font.PLAIN, 25));
		lblInEuropschyco.setBackground(Color.WHITE);
		lblInEuropschyco.setBounds(0, 54, 219, 28);
		panel.add(lblInEuropschyco);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(219, 0, 918, 600);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(-173, -47, 1091, 590);
		label.setIcon(new ImageIcon("C:\\Users\\bourd\\eclipse-workspace\\GLPOOproject\\src\\main\\ressources\\Accueil1.jfif"));
		panel_1.add(label);
	}
	
	
	
	private class Jouer extends AbstractAction {
		private TirageJFrameV1 frame;

		private Jouer() {
			super("Jouer");
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			frame = new TirageJFrameV1();
			frame.setVisible(true);
		}
		
	}

	private class Options extends AbstractAction {

		public Options() {
			super("Options");
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//LOGGER.debug("Pas encore developpé");
		}
		
	}
	
	private class Bonus extends AbstractAction {

		public Bonus() {
			super("Bonus");
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//LOGGER.debug("Pas encore developpé");
		}
		
	}
	
	private class Credits extends AbstractAction {

		public Credits() {
			super("Crédits");
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//LOGGER.debug("Pas encore developpé");
		}
		
	}
}
