package fractales.fr.Frame;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import fractales.fr.fractales.JuliaFractale;


public class JuliaFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private BufferedImage buffer;
	private String fileName;
	private int LARGEUR = 1280; 
	private int HAUTEUR = 720; 
	private JuliaFractale julia;


	public JuliaFrame(final float reC, final float imC, final int echelle, final float estompage,float couleur,final int iterations) {

		
	//Calcul du la fractale
		julia = new JuliaFractale(reC, imC, echelle, LARGEUR, HAUTEUR, estompage, couleur,iterations);
		buffer = julia.getImage();
		
	//Initialisation de la fenêtre
		setTitle("Julia");
		setPreferredSize(new Dimension(julia.getLargeur(), julia.getHauteur()));
		setMinimumSize(new Dimension(700,700)); //On impose une taille minimale d=pour la fenetre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
    //Ajout du menu dans la fenetre
		JMenuBar menuBar = configureMenu();

	//Permet de redimensionner la fenetre et de recalculer le dessin en conséquence
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				int largeur = getWidth();
				int hauteur = getHeight();
				if (largeur != LARGEUR || hauteur != HAUTEUR) {
					LARGEUR = largeur;
					HAUTEUR = hauteur;
					julia = new JuliaFractale(reC, imC, echelle, LARGEUR, HAUTEUR, estompage,couleur,iterations);
					buffer = julia.getImage();

					setPreferredSize(new Dimension(LARGEUR, HAUTEUR));

					setContentPane(new JLabel(new ImageIcon(buffer)));
					setJMenuBar(menuBar);
					pack();
				}
			}
		});
		
    //Ajout des composants dans la fenetre
		getContentPane().add(new JLabel(new ImageIcon(buffer)));
		setJMenuBar(menuBar);
		
    //Affichage
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		

	}

	private JMenuBar configureMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fichierMenu = new JMenu("Fichier");
		menuBar.add(fichierMenu);
		JMenuItem save = new JMenuItem(new SaveAction("Sauvegarder"));
		fichierMenu.add(save);
		JMenuItem close = new JMenuItem(new CloseAction("Quitter"));
		fichierMenu.add(close);
		return menuBar;
	}

	private class SaveAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		private SaveAction(String string) {
			super(string);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// On demande le nom du fichier
			fileName = JOptionPane.showInputDialog(null, "Veuillez entrer le nom de l'image",
					"Enregistrement du fichier", JOptionPane.QUESTION_MESSAGE);

			// On enregistre l'image
			Graphics2D g2d = (Graphics2D) buffer.getGraphics();
			g2d.dispose();
			try {
				ImageIO.write(buffer, "png", new File(fileName + "_julia.png"));
			} catch (IOException e1) {
				System.out.println(e1);
			}
			System.exit(0);
		}

	}

	private class CloseAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		private CloseAction(String string) {
			super(string);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	}

	
	
}
