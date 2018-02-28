package fractales.fractales;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class JuliaFractale implements Fractale {

	@Override
	public String toString() {
		return "JuliaFractale [width=" + width + ", height=" + height + ", iterations=" + iterations + ", buffer="
				+ buffer + ", Im_C=" + Im_C + ", Re_C=" + Re_C + ", scale=" + scale + ", estompage=" + estompage
				+ ", COLOR=" + COLOR + ", centreX=" + centreX + ", centreY=" + centreY + ", maxX=" + maxX + ", minX="
				+ minX + ", maxY=" + maxY + ", minY=" + minY + "]";
	}

	private int width; 
	private int height;
	private int iterations; 
	private BufferedImage buffer; // Image tampon

	// PARAMETRES MODIFIABLES via MAIN
	private final float Im_C;
	private final float Re_C;
	private int scale;
	private final float estompage;
	private final float COLOR;
	private float centreX;
	private float centreY;
	private float maxX;
	private float minX;
	private float maxY;
	private float minY;
	
	

	public JuliaFractale(final float ReC, final float ImC, int scale, int width, int height,
			final float estompage,final float COLOR,int iterations) {
    // On instancie le buffer : dimensions et coloration
		buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
	// Initialisation des paramètres du dessin
		this.Im_C = ImC;
		this.Re_C = ReC;
		this.scale = scale;
		this.width = width;
		this.height = height;
		this.estompage = estompage;
		this.COLOR = COLOR;
		this.iterations = 200;
		
		centreX = 0; 
		centreY = 0;
		calcMinMax();
		
	}

	public BufferedImage getDrawing() {
		draw();
		return buffer;
	}

	private void draw() {
		float X,Y;
		for (int x = 0; x < width; x += 1) {
			X = (float) x/scale+minX;
			for (int y = 0; y < height; y += 1) {
				Y = (float) y/scale + minY;
				final int COLOR = colorCalcul(X,Y);
				buffer.setRGB(x, y, COLOR);
			}
		}
	}
	
	
		

	private int colorCalcul(float zx, float zy) {
		int n = 0;

		for (; n < iterations; n++) {

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
		if (n == iterations)
			return 0x00000000; // Après 100 itérations la suite (avec cette valeur de z0) ne semble pas
								// diverger donc on considère qu'elle converge (parfois approximation fausse) =>
								// noir
		return Color.HSBtoRGB(0.5f + COLOR - (float) estompage * n / iterations, 0.6f, 1);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getIterations() {
		return iterations;
	}

	public int getScale() {
		return scale;
	}

	public float getCentreX() {
		return centreX;
	}

	public float getCentreY() {
		return centreY;
	}

	public float getMaxX() {
		return maxX;
	}

	public float getMinX() {
		return minX;
	}

	public float getMaxY() {
		return maxY;
	}

	public float getMinY() {
		return minY;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public void setCentreX(float centreX) {
		this.centreX = centreX;
	}

	public void setCentreY(float centreY) {
		this.centreY = centreY;
	}

	public void setMaxX(float maxX) {
		this.maxX = maxX;
	}

	public void setMinX(float minX) {
		this.minX = minX;
	}

	public void setMaxY(float maxY) {
		this.maxY = maxY;
	}

	public void setMinY(float minY) {
		this.minY = minY;
	}
	
	public int setWidth() {
		return width;
	}

	public int setHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		
	}

	public void setWidth(int width) {
		this.width = width;
		
	}
	public void setBuffer(BufferedImage bufferedImage) {
		this.buffer = bufferedImage;
	}

	public void calcMinMax() {
			maxX = centreX + (float) 0.5 * width / scale;
			minX = centreX - (float) 0.5 * width / scale;
			
			maxY = centreY + (float) 0.5 * height / scale;
			minY = centreY - (float) 0.5 * height / scale;
			
			
	}
		
}	
