package fractales.Frame;

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

import fractales.fractales.Mandelbrot;


public class MandelbrotFrame extends JFrame {

	private BufferedImage buffer; // Image tampon
	private int LARGEUR = 1000;
	private int HAUTEUR = 600;
	private String fileName;
	private Mandelbrot mdb;

	public MandelbrotFrame(final float echelle) {

		mdb = new Mandelbrot(echelle, LARGEUR, HAUTEUR);
		buffer = mdb.getImage();

		setTitle("Mandelbrot");
		setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
		setResizable(true); 
		setMinimumSize(new Dimension(400, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Pour fermer la fenetre on pourra cliquer sur la croix rouge

		final JMenuBar menu = configureMenu();

		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				int largeur = getWidth(); 
				int hauteur = getHeight();
				if (largeur != LARGEUR || hauteur != HAUTEUR) {
					LARGEUR = largeur;
					HAUTEUR = hauteur;
					mdb = new Mandelbrot(echelle, LARGEUR, HAUTEUR);
					buffer = mdb.getImage();
					
					setPreferredSize(new Dimension(LARGEUR, HAUTEUR));

					setContentPane(new JLabel(new ImageIcon(buffer)));
					setJMenuBar(menu);
					pack();
				}
			}
		});

		getContentPane().add(new JLabel(new ImageIcon(buffer)));
		setJMenuBar(menu);

		pack(); // Permet d'adapter la taille de la fenetre suivant les cas de figures
		setVisible(true); // Pour que la fenetre soit visible

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

		public void actionPerformed(ActionEvent e) {
			// On demande le nom du fichier
			fileName = JOptionPane.showInputDialog(null, "Veuillez entrer le nom de l'image",
					"Enregistrement du fichier", JOptionPane.QUESTION_MESSAGE);

			// On enregistre l'image
			Graphics2D g2d = (Graphics2D) buffer.getGraphics();
			g2d.dispose();
			try {
				ImageIO.write(buffer, "png", new File(fileName + "_mandelbrot.png"));
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

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	}

}
