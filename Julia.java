import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JComponent;

public class Julia extends JComponent
{
	
	public static void main(String[] args)
	{
		new Julia();
	}
	
	public static final int LARGEUR = 1280; // Définition de la largeur de la fenetre
	public static final int HAUTEUR = 720; // '' hauteur de la fenetre
	public static final int ITERATIONS = 100; // Définition du nombre d'itérations
	public static final float ECHELLE = 500; // Definition de l'echelle de grandeur
	
	private BufferedImage buffer; // Image tampon
	
	public Julia()
	{
		buffer = new BufferedImage(LARGEUR, HAUTEUR, BufferedImage.TYPE_INT_RGB); // On instancie le buffer : dimensions et coloration
		
		julia();
		
		JFrame frame = new JFrame("Julia"); // Instance de la fenetre
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
	
	public void julia()
	{
		for (int x = 0 ; x < LARGEUR ; x+=1)
			for (int y = 0; y < HAUTEUR ; y+=1)
			{
				int color = calculCouleur((x - LARGEUR/2f)/ECHELLE, (y - HAUTEUR/2f)/ECHELLE);
				buffer.setRGB(x, y, color);
			}
	}
	
	public int calculCouleur(float zx, float zy)
	{
		int n = 0;
		
		float cx = (float)0.285;
		float cy = (float)0.01;
		
		for(; n < ITERATIONS; n++)
		{
			if (zx*zx + zy*zy > 16) break; // Condition "infinie" dans notre monde fini
			//On applique la formule générale pour générer l'ensemble de Julia (on parcourt tous les z0 du plan)
			float zxzx = zx*zx;
			float zyzy = zy*zy;
			float twozxzy = 2*zx*zy;
			zx = zxzx - zyzy + cx;
			zy = twozxzy + cy;
			
		}
		if(n == ITERATIONS) return 0x00000000; // blanc => hors de l'ensemble
		return Color.HSBtoRGB((float)8*n/(ITERATIONS) , 0.6f, 1); // noir => dans l'ensemble
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.drawImage(buffer,  0,  0, null);
	}
	
	
	// HSBtoRGB(float hue, float saturation, float brightness)
	//Converts the components of a color, as specified by the HSB model, to an equivalent set of values for the default RGB model.
}