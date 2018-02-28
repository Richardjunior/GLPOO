package IHM.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
//import org.apache.log4j.Logger;

import IHM.models.ModeleDynamique;


public class WelcomeFrame extends JFrame {

		//private static final Logger LOGGER = Logger.getLogger(TirageJFrame.class);
		private ModeleDynamique modele = new ModeleDynamique();
		private final JTable tableau = new JTable(modele);
		

		public WelcomeFrame() {
			// SETTING DE LA PAGE
			super();

			buildAccueil();

			pack();
		}
		
		private void buildAccueil() {
			setTitle("Loto Psychédélique");
			setPreferredSize(new Dimension(1068, 613));
			// setLocationRelativeTo(null);
			setDefaultCloseOperation(EXIT_ON_CLOSE);

			// préparation du label avec l'image
			ImageIcon icon = null;
			try {
				icon = new ImageIcon(ImageIO.read(new File("src/main/ressources/Accueil1.jfif")));
			} catch (IOException e) {
				// pb de chargement de l'image
				e.printStackTrace();
			}

			JLabel contentPane = new JLabel(icon) {
				@Override
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					if (getIcon() != null)
						g.drawImage(((ImageIcon) getIcon()).getImage(), 0, 0, getWidth(), getHeight(), null);
				}
			};

			// ajoute le conteneur
			setContentPane(contentPane);

			// modification du layout
			getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 20, 180));

			final JPanel buttonsPane = new JPanel();

			buttonsPane.add(new JButton(new Jouer()));
			buttonsPane.add(Box.createRigidArea(new Dimension(0, 10)));
			buttonsPane.add(new JButton(new Options()));
			buttonsPane.add(Box.createRigidArea(new Dimension(0, 10)));
			buttonsPane.add(new JButton(new Bonus()));
			buttonsPane.add(Box.createRigidArea(new Dimension(0, 10)));
			buttonsPane.add(new JButton(new Credits()));
			
			buttonsPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // ajoute un cadre vide autour des boutons

			buttonsPane.setLayout(new BoxLayout(buttonsPane, BoxLayout.PAGE_AXIS)); // aligne les boutons verticalement

			// ajout de composants
			getContentPane().add(buttonsPane);

			pack();
		}


		
		private class Jouer extends AbstractAction {

			private Jouer() {
				super("Jouer");
				
			}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				JFrame f = new TirageJFrame();
				f.setVisible(true);
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
