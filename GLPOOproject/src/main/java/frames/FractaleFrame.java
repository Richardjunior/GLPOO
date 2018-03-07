package frames;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fractales.Fractale;
import fractales.FractaleService;


public class FractaleJFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private BufferedImage buffer;
	private List<BufferedImage> images;
	private String fileName;
	private int WIDTH ; 
	private int HEIGHT ; 
	private List<Fractale> fractales;
	
	
	final JMenuBar menuBar;
	private JPanel panel;
	
	public FractaleJFrame(List<Fractale> fractales,int WIDTH,int HEIGHT) {
		this.fractales = fractales;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		buffer = getBuffer();
		
		
	//Initialisation de la fenêtre
		setTitle("Fractale Generator");
		//setBounds(0,0,HEIGHT,WIDTH);
		setMinimumSize(new Dimension(700,700)); //On impose une taille minimale pour la fenetre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		//fractaleJFrame.getContentPane().setLayout(null);
		
	//Ajout d'un bandeau d'action
		panel = new JPanel();
		//panel.setLayout(null);
		JButton backButton = new JButton(new BackAction("Back"));
		panel.add(backButton);
		//backButton.setBounds(0, 0, 50, 31);
		
		JButton reinitialiser = new JButton(new reinitialiser());
		panel.add(reinitialiser);
		
		
    //Ajout du menu dans la fenetre
		menuBar = configureMenu();

	//Permet de redimensionner la fenetre et de recalculer le dessin en conséquence
		addComponentListener(new Adapter());
		
	//Pour zoomer ou translater
		addKeyListener(new Key_Listener());
		
		
    //Ajout des composants dans la fenetre
		add(new JLabel(new ImageIcon(buffer)));
		
		setJMenuBar(menuBar);
		
		add(panel,BorderLayout.SOUTH);
		
    //Affichage
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		
	//AFFICHAGE DE LA BOITE DE DIALOGUE : Indications touches
		JOptionPane.showMessageDialog(null,"- You can resize the frame"
									  + "	\n\n- Press + or - to zoom "
        						      + "	\n\n- Press keyboard direction arrows to translate  "
        						     
        						      + "\n\n\nCAUTION : To have a good quality of image \nplease don't zoom too much!"
        						     );
	}

	private BufferedImage getBuffer() {
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		for(Fractale f : fractales) {
			images.add(f.getDrawing());
		}
		this.images = images;
		BufferedImage buffer = FractaleService.getInstance().joinFractales(images);
		return buffer;
	}

	private JMenuBar configureMenu() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fichierMenu = new JMenu("File");
		menuBar.add(fichierMenu);
		JMenuItem save = new JMenuItem(new SaveAction("Save"));
		fichierMenu.add(save);
		fichierMenu.addSeparator();
		JMenuItem close = new JMenuItem(new CloseAction("Quit"));
		fichierMenu.add(close);
		//JMenuItem backMenu = new JMenuItem(new BackAction("Go back to the previous menu"));
		//fichierMenu.add(backMenu);
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
				ImageIO.write(buffer, "png", new File("src/main/Images/"+fileName + "_julia.png"));
			} catch (IOException e1) {
				System.out.println(e1);
			}
			
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
	
	
	private class BackAction extends AbstractAction{
		private BackAction(String string) {
    		super(string);
    	}
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			TirageJFrame f = new TirageJFrame();
			f.setVisible(true);
		}
    	
    }
	
	private class Adapter extends ComponentAdapter {
		private FractaleService service = null;
		
		
		public void componentResized(ComponentEvent e) {
			service = FractaleService.getInstance();
			
			int width = getWidth();
			int height = getHeight();
			if (width != WIDTH || height != HEIGHT) {
				WIDTH = width;
				HEIGHT = height;
				
				images.clear();
				for(Fractale fractale : fractales) {
					service.changeSize(fractale,width, height);
					images.add(fractale.getDrawing());
				}
				buffer = FractaleService.getInstance().joinFractales(images);

				setPreferredSize(new Dimension(width, height));

				setContentPane(new JLabel(new ImageIcon(buffer)));
				
				setJMenuBar(menuBar);
				
				pack();
			}
		}
	}
	
	private class reinitialiser extends AbstractAction {

		public reinitialiser() {
			super("Réinitialiser");
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			getBuffer();
		}
		
	}
	
	private class Key_Listener implements KeyListener {
		private FractaleService service = null;
		
		public void keyPressed(KeyEvent e) {
			service = FractaleService.getInstance();
			int keyCode = e.getKeyCode();
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
				for(Fractale fractale : fractales) {
					service.zoom(fractale,true);
				}
			}else if (keyCode == 109 || keyCode == 54){  // - 
				for(Fractale fractale : fractales) {
					service.zoom(fractale,false);
				}
			}
			
			if(keyCode == 37 || keyCode == 38 ||keyCode == 39 ||keyCode == 40 ) {
				for(Fractale fractale : fractales) {
					service.changeCenter(fractale,X, Y);
				}
			}
			images.clear();
			for(Fractale fractale : fractales) {
				images.add(fractale.getDrawing());
			}
			 buffer = FractaleService.getInstance().joinFractales(images);

			setContentPane(new JLabel(new ImageIcon(buffer)));
			getContentPane().add(panel,BorderLayout.SOUTH);
			setJMenuBar(menuBar);
			
			pack();
		}

		public void keyReleased(KeyEvent arg0) {}

		public void keyTyped(KeyEvent arg0) {}
	}
}
	

