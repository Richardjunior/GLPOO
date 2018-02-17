
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JComponent;

public class Newton extends JComponent
{
	
	public static void main(String[] args)
	{
		new Newton();
	}
	
	public static final int LARGEUR = 1280; // Définition de la largeur de la fenetre
	public static final int HAUTEUR = 720; // '' hauteur de la fenetre
	public static final int ITERATIONS = 100; // Définition du nombre d'itérations
	public static final float ECHELLE = 300; // Definition de l'echelle de grandeur
	
	private BufferedImage buffer; // Image tampon
	
	public Newton()
	{
		buffer = new BufferedImage(LARGEUR, HAUTEUR, BufferedImage.TYPE_INT_RGB); // On instancie le buffer : dimensions et coloration
		
		newton();
		
		JFrame frame = new JFrame("Newton"); // Instance de la fenetre
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
	
	public void newton()
	{
		for (int x = 0 ; x < LARGEUR ; x++)
			for (int y = 0; y < HAUTEUR ; y++)
			{
				int color = calculCouleur((x - LARGEUR/2f)/ECHELLE, (y - HAUTEUR/2f)/ECHELLE);
				buffer.setRGB(x, y, color);
			}
	}
	
	public int calculCouleur(float zx, float zy)
	{
		int n = 0;
		
		for(; n < ITERATIONS; n++)
		{
			if (zx*zx + zy*zy > 1000000) break; // Condition "infinie" dans notre monde fini
			
			//On applique la formule générale 
			float scalaire = 1/((zx*zx - zy*zy)*(zx*zx - zy*zy) + 4*zx*zx*zy*zy);
			float xtemp = 6*(zx*zx*zx*zx*zx + zy*zy*zy*zy*zy) -24*zx*zx*zx*zy*zy + 18*zx*zy*zy*zy*zy - 6*zy*zy*zy*zx*zx + 3*(zx*zx -zy*zy);
			float ytemp = 36*(zx*zx*zx*zy*zy + zx*zx*zy*zy*zy) + 18*(zx*zx*zx*zx*zy - zx*zx*zy*zy*zy) + 12*(zx*zy*zy*zy*zy - zx*zx*zx*zx*zy) -6*zx*zy;
			zx = scalaire*xtemp;
			zy = scalaire*ytemp;
		}
		if(n == ITERATIONS) return 0x00000000; // blanc => hors de l'ensemble
		return Color.HSBtoRGB((float)6*n/ITERATIONS , 0.5f, 1);
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.drawImage(buffer,  0,  0, null);
	}
	
	
	// HSBtoRGB(float hue, float saturation, float brightness)
	//Converts the components of a color, as specified by the HSB model, to an equivalent set of values for the default RGB model.
}