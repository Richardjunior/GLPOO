import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import org.apache.commons.math3.complex.Complex;


import javax.swing.JFrame;
import javax.swing.JComponent;

public class Householder extends JComponent
{
	
	public static void main(String[] args)
	{
		new Householder();
	}
	
	public static final int LARGEUR = 1280; // Définition de la largeur de la fenetre
	public static final int HAUTEUR = 720; // '' hauteur de la fenetre
	public static final int ITERATIONS = 500; // Définition du nombre d'itérations
	public static final float ECHELLE = 300; // Definition de l'echelle de grandeur
	public static final Complex r1 = new Complex(1,0); // Racine réelle du polynome de Newton classique
	public static final Complex r2 = new Complex(-0.5,-Math.sqrt(3)/2); // Racine complexe du polynome de Newton classique
	public static final Complex r3 = new Complex(-0.5,Math.sqrt(3)/2); // Racine complexe conjuguée
	
	public static final double epsilon = (double)0.1; //plus epsilon est petit, plus le calcul est précis, et le temps de calcul évolue de manière exponentielle. Tester avec de petites valeurs au début
	public static final double alpha = (double)1;
	
	
	static Complex [] roots = {r1,r2,r3};
	
	private BufferedImage buffer; // Image tampon
	
	public Householder()
	{
		buffer = new BufferedImage(LARGEUR, HAUTEUR, BufferedImage.TYPE_INT_RGB); // On instancie le buffer : dimensions et coloration
		
		householder();
		
		JFrame frame = new JFrame("Householder"); // Instance de la fenetre
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
	
	public void householder()
	{
		for (int x = 0 ; x < LARGEUR ; x++)
			for (int y = 0; y < HAUTEUR ; y++)
			{
				int color = calculCouleur((x - LARGEUR/2f)/ECHELLE, (y - HAUTEUR/2f)/ECHELLE);
				buffer.setRGB(x, y, color);
			}
	}
	
	public int calculCouleur(double zx, double zy)
	{
		int n = 0;
		
		Complex z = new Complex(zx,zy);
		
		for(; n < ITERATIONS; n++)
		{
			Complex h = (poly(z).multiply(deriv2(z))).divide((deriv(z).pow(2).multiply(2)));
			z = z.subtract(poly(z).divide(deriv(z)).multiply(h.add(1))); 
			for(Complex item : roots)
			{
				if(Math.abs(z.getReal() - item.getReal()) < epsilon && Math.abs(z.getImaginary() - item.getImaginary()) < epsilon)
						{
							if(item.getImaginary()==0)
							{
								return Color.HSBtoRGB((float)Math.sqrt((double)40*n/ITERATIONS) , 0.5f, 1); // ce point converge vers la racine réelle
								//return 0xFF0000FF;
							}
							else if(item.getImaginary()<0)
							{
								return Color.HSBtoRGB((float)Math.sqrt((double)40*n/ITERATIONS) , 0.5f, 1); // ce point converge vers la 1ere racine complexe
								//return 0xFFFF0000;
							}
							else if(item.getImaginary()>0)
							{
								return Color.HSBtoRGB((float)Math.sqrt((double)40*n/ITERATIONS) , 0.5f, 1); // ce point converge vers la racine complexe conjuguée
								//return 0xFF00FF00;
							}
						}
			}
		}
		return 0xFFFFFFFF;
}
	
	public Complex poly(Complex z)
	{
		return (z.pow(3)).subtract(1);
	}
	
	public Complex deriv(Complex z)
	{
		return (z.pow(2)).multiply(3);
	}
	public Complex deriv2(Complex z)
	{
		return z.multiply(6);
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.drawImage(buffer,  0,  0, null);
	}
	
	
	// HSBtoRGB(float hue, float saturation, float brightness)
	//Converts the components of a color, as specified by the HSB model, to an equivalent set of values for the default RGB model.
}
