package fractales.fr.fractales;

import java.awt.image.BufferedImage;
import java.util.List;

public class JuliaFusion {

	private final List<BufferedImage> images; // Chaque image a la même taille
	private BufferedImage imageResultante = null;

	public JuliaFusion(List<BufferedImage> images) {
		this.images = images;

		additionnerCouleurs();
	}

	private void additionnerCouleurs() {
		final int LARGEUR = images.get(0).getWidth();
		final int HAUTEUR = images.get(0).getHeight();

		BufferedImage temp = new BufferedImage(LARGEUR, HAUTEUR, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < LARGEUR; x += 1) {
			for (int y = 0; y < HAUTEUR; y += 1) {
				for (BufferedImage image : images) {
					temp.setRGB(x, y, temp.getRGB(x,y) + image.getRGB(x, y));
				}
			}
		}

		imageResultante = temp;

	}

	public BufferedImage getImageResultante() {
		return imageResultante;
	}

}
