package fractales.fr.fractales;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class JuliaFractale {

	private final int LARGEUR; 
	private final int HAUTEUR;
	private final int ITERATIONS; 

	private final float COEFF_L = (float) 2.0; // plus ce coefficient est grand plus on décale le dessin vers la gauche
	private final float COEFF_H = (float) 2.0; // plus ce coefficient est grand plus on décale le dessin vers le bas
												// => (2,2) centré
	private BufferedImage buffer; // Image tampon

	// PARAMETRES MODIFIABLES via MAIN
	private final float Im_C;
	private final float Re_C;
	private float ECHELLE;
	private final float estompage;
	private final float couleur;

	public JuliaFractale(final float reC, final float imC, final int echelle, int largeur, int hauteur,
			final float estompage,final float couleur,final int ITERATIONS) {
    // On instancie le buffer : dimensions et coloration
		buffer = new BufferedImage(largeur, hauteur, BufferedImage.TYPE_INT_RGB);
		
	// Initialisation des paramètres du dessin
		this.Im_C = imC;
		this.Re_C = reC;
		this.ECHELLE = echelle;
		this.LARGEUR = largeur;
		this.HAUTEUR = hauteur;
		this.estompage = estompage;
		this.couleur = couleur;
		this.ITERATIONS = ITERATIONS;
	}

	public BufferedImage getImage() {
		julia();
		return buffer;
	}

	private void julia() {
		for (int x = 0; x < LARGEUR; x += 1)
			for (int y = 0; y < HAUTEUR; y += 1) {
				final int color = calculCouleur((x - LARGEUR / COEFF_L) / ECHELLE, (y - HAUTEUR / COEFF_H) / ECHELLE);
				buffer.setRGB(x, y, color);
				
			}
	}

	private int calculCouleur(float zx, float zy) {
		int n = 0;

		for (; n < ITERATIONS; n++) {

			/*
			 * On applique la formule générale pour générer l'ensemble de Julia (on parcourt
			 * tous les z0 du plan)
			 *
			 * z(n) = zx(n) + i*zy(n) z(n+1) = z(n)*z(n) +c = zx(n)*zx(n) - zy(n)*zy(n) +
			 * 2*zx(n)*zy(n)*i +c Donc |zx(n+1) = zx(n)*zx(n) - zy(n)*zy(n) + Re(c) |zy(n+1)
			 * = 2*zx(n)*zy(n) +Im(c)
			 */

			float xtemp = zx * zx - zy * zy;
			zy = 2 * zx * zy - Im_C;
			zx = xtemp + Re_C;

			if (zx * zx + zy * zy > 4) { // On consière que si, après un certain nombre d'itérations n, la norme du
											// complexe z(n) dépasse 2 alors la suite diverge
				break;
			}
		}
		if (n == ITERATIONS)
			return 0x00000000; // Après 100 itérations la suite (avec cette valeur de z0) ne semble pas
								// diverger donc on considère qu'elle converge (parfois approximation fausse) =>
								// noir
		return Color.HSBtoRGB(couleur - (float) estompage * n / ITERATIONS, 0.6f, 1);
	}

	public int getLargeur() {
		return LARGEUR;
	}

	public int getHauteur() {
		return HAUTEUR;
	}

}
