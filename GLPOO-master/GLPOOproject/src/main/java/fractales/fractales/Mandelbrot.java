package fractales.fractales;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Mandelbrot {

	private final int ITERATIONS = 100;
	private float ECHELLE; 
	private int LARGEUR;
	private int HAUTEUR;
	
	private BufferedImage buffer;
	
	public Mandelbrot(float echelle,int LARGEUR, int HAUTEUR) {
		buffer = new BufferedImage(LARGEUR, HAUTEUR, BufferedImage.TYPE_INT_RGB);
		this.ECHELLE = echelle;
		this.LARGEUR = LARGEUR;
		this.HAUTEUR = HAUTEUR;
	}

	public BufferedImage getImage() {
		mandelbrot();
		return buffer;
	}
	private void mandelbrot() {
		for (int x = 0; x < LARGEUR; x++)
			for (int y = 0; y < HAUTEUR; y++) {
				int color = calculCouleur((x - LARGEUR / 2f) / ECHELLE, (y - HAUTEUR / 2f) / ECHELLE);
				buffer.setRGB(x, y, color);
			}
	}

	private int calculCouleur(float x, float y) {
		final float C_X = x;
		final float C_Y = y;
		
		int n = 0;

		for (; n < ITERATIONS; n++) {
			
			// On applique la formule générale pour générer l'ensemble de Mandelbrot (z(n+1)
			// = z(n)**2 + constante complexe
			float zx = x * x - y * y + C_X;
			float zy = 2 * x * y + C_Y;
			
			x = zx;
			y = zy;
			
			if (x * x + y * y > 4)
				break;
			

		}
		if (n == ITERATIONS)
			return 0x00000000; // noir => dans l'ensemble
		return Color.HSBtoRGB((float) 0.5*n / ITERATIONS, 0.5f, 1); //hors de l'ensemble avec plus ou moins d'itererations
	}
}
