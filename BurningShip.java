import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JComponent;

public class BurningShip extends JComponent 
{
	
	public static void main(String[] args)
	{
		new BurningShip();
	}
	
	public static final int LARGEUR = 1280; // Définition de la largeur de la fenetre
	public static final int HAUTEUR = 720; // '' hauteur de la fenetre
	public static final int ITERATIONS = 100; // Définition du nombre d'itérations
	public static final float ECHELLE = 250; // Definition de l'echelle de grandeur
	
	private BufferedImage buffer; // Image tampon
	
	public BurningShip()
	{
		buffer = new BufferedImage(LARGEUR, HAUTEUR, BufferedImage.TYPE_INT_RGB); // On instancie le buffer : dimensions et coloration
		
		burntheship();
		
		JFrame frame = new JFrame("Mandelbrot"); // Instance de la fenetre
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Pour fermer la fenetre on pourra cliquer sur la croix rouge
		frame.setResizable(true); // On pourra resizer la fenetre
		frame.getContentPane().add(this); // ??
		frame.pack(); // Permet d'adapter la taille de la fenetre suivant les cas de figures
		frame.setVisible(true); // Pour que la fenetre soit visible


	}

	@Override 
	public void addNotify()
	{
		setPreferredSize(new Dimension(LARGEUR,HAUTEUR)); // Pas trop compris ce paramètre mais c'est pour faire lien entre les différents layers du panel (container etc avec awt)
	}
	
	public void burntheship()
	{
		for (int x = 0 ; x < LARGEUR ; x++)
			for (int y = 0; y < HAUTEUR ; y++)
			{
				int color = calculCouleur((x - LARGEUR/2f)/ECHELLE, (y - HAUTEUR/2f)/ECHELLE);
				buffer.setRGB(x, y, color);
			}
	}
	
	public int calculCouleur(float x, float y)
	{	
		int n = 0;
		float zx = x;
		float zy = y;
		
		for(; n < ITERATIONS; n++)
		{
			
			if (zx*zx + zy*zy < 4) break;
			//On applique la formule du burningShip
			float xtemp = zx*zx - zy*zy + x;
			zy = 2*Math.abs(zx)*Math.abs(zy);
			zy = xtemp;
		}
		if(n == ITERATIONS) return 0x00000000; // noir => hors de l'ensemble
		return Color.HSBtoRGB((float)n/ITERATIONS , 0.5f, 1); 
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.drawImage(buffer,  0,  0, null);
	}
	

}