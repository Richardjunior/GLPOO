package fractales.fractales;

import java.awt.image.BufferedImage;
import java.util.List;

public class FractaleService {

	private static FractaleService singleton;

	private final int ZOOM_COEF = 2;
	private final float TRANSLATION_COEF = 0.025f;

	private FractaleService() {}

	public static FractaleService getInstance() {
		if (singleton == null) {
			singleton = new FractaleService();
		}
		return singleton;
	}

	public float getTRANSLATION_COEF() {
		return TRANSLATION_COEF;
	}

	public void changeCenter(Fractale f, float X, float Y) {
		final float centreX = (float) (f.getCentreX() + (float) X * f.getWidth() / f.getScale());
		final float centreY = (float) (f.getCentreY() + (float) Y * f.getHeight() / f.getScale());
		f.setCentreX(centreX);
		f.setCentreY(centreY);

		f.calcMinMax();

	}

	public void zoom(Fractale f, boolean bool) {
		int scale = f.getScale();
		int iterations = f.getIterations();
		if (bool) {
			f.setScale(scale * ZOOM_COEF); // à utiliser max 5fois : echelleMAX = 10^7
		//	f.setIterations((int) (iterations * 1.3f));
		} else {
			f.setScale((int) scale / ZOOM_COEF);
			//f.setIterations((int) (iterations / 1.3f));
		}
		f.calcMinMax();

	}

	public void changeSize(Fractale f, int width, int height) {
		f.setHeight(height);
		f.setWidth(width);
		f.setBuffer(new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB));

	}

	/*****************************
	 * FUSION                  
	 *****************************/

	public BufferedImage joinFractales(final List<BufferedImage> images) {
		// On test le format d'entrée
		final int width = images.get(0).getWidth();
		final int height = images.get(0).getHeight();
		for (BufferedImage buffer : images) {
			if (width != buffer.getWidth() && height != buffer.getHeight()) {
				throw new IllegalArgumentException("L'une au moins des images n'a pas les mêmes dimensions");
			}
		}
		BufferedImage res = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//Addition des couleurs
		for (int x = 0; x < width; x += 1) {
			for (int y = 0; y < height; y += 1) {
				for (BufferedImage image : images) {
					res.setRGB(x, y, res.getRGB(x, y) + image.getRGB(x, y));
				}
			}
		}
		return res;
	}

}
