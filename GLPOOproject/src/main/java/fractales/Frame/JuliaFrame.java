package fractales.Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fractales.fractales.JuliaFractale;
import fractales.fractales.FractaleService;


public class JuliaFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private BufferedImage buffer;
	private String fileName;
	private int LARGEUR = 1200; 
	private int HAUTEUR = 700; 
	private JuliaFractale julia;
	private final FractaleService service;


	public JuliaFrame(final float reC, final float imC, final int echelle, final float estompage,float couleur,final int iterations) {
		
		service = FractaleService.getInstance();
		
	//Calcul du la fractale
		julia = new JuliaFractale(reC, imC, echelle, LARGEUR, HAUTEUR, estompage, couleur,iterations);
		buffer = julia.getDrawing();
		
	//Initialisation de la fenêtre
		setTitle("Julia");
		setPreferredSize(new Dimension(julia.getWidth(), julia.getHeight()));
		setMinimumSize(new Dimension(700,700)); //On impose une taille minimale d=pour la fenetre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		
		
    //Ajout du menu dans la fenetre
		final JMenuBar menuBar = configureMenu();

	//Permet de redimensionner la fenetre et de recalculer le dessin en conséquence
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				int largeur = getWidth();
				int hauteur = getHeight();
				if (largeur != LARGEUR || hauteur != HAUTEUR) {
					LARGEUR = largeur;
					HAUTEUR = hauteur;
					service.changeSize(julia,LARGEUR, HAUTEUR);
					buffer = julia.getDrawing();

					setPreferredSize(new Dimension(LARGEUR, HAUTEUR));

					setContentPane(new JLabel(new ImageIcon(buffer)));
					setJMenuBar(menuBar);
					pack();
				}
			}
		});
		
		
		addKeyListener(new KeyListener() {
			
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				System.out.println(keyCode);
				float X = 0, Y = 0;
				final float coef = service.getTRANSLATION_COEF();
				if(keyCode == 38) {        // UP
					X = 0;
					Y = -coef;
				}else if(keyCode == 40) {  // DOWN
					X = 0;
					Y = coef;
				}else if(keyCode == 39) {  // RIGHT
					X = coef;
					Y = 0;
				}else if(keyCode == 37){   // LEFT
					X = -coef;
					Y = 0;
				}else if(keyCode == 107 || keyCode == 61) { // + pavé numérique ou non
					service.zoom(julia,true);
				}else if (keyCode == 109 || keyCode == 54){  // - 
					service.zoom(julia,false);
				}
				
				if(keyCode == 37 || keyCode == 38 ||keyCode == 39 ||keyCode == 40 ) {service.changeCenter(julia,X, Y);}
				
				buffer = julia.getDrawing();

				setContentPane(new JLabel(new ImageIcon(buffer)));
				setJMenuBar(menuBar);
				pack();
			}

			public void keyReleased(KeyEvent arg0) {}

			public void keyTyped(KeyEvent arg0) {}
		});
		
		
    //Ajout des composants dans la fenetre
		getContentPane().add(new JLabel(new ImageIcon(buffer)));
		setJMenuBar(menuBar);
		
    //Affichage
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		
		final JDialog dialog = new JDialog();
		dialog.setTitle("Information");
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel("<html><p><h3><center>      KEYBOARD OPTIONS :     </center></h3></p> "
        						      + "	<p><center>    To use zoom function press + or -      </center> </p>"
        						      + "	<p><center>    To use translate function press <br>keyboard direction arrows       </center></p>"
        						      + "</html>");
        panel.add(label);
        panel.add(new JButton(new AbstractAction() {
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
				
			}
        	
        }));
        dialog.add(panel);
        dialog.setSize(350, 250);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);


	}

	private JMenuBar configureMenu() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fichierMenu = new JMenu("File");
		menuBar.add(fichierMenu);
		JMenuItem save = new JMenuItem(new SaveAction("Save"));
		fichierMenu.add(save);
		JMenuItem close = new JMenuItem(new CloseAction("Quit"));
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
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	}
	
}
