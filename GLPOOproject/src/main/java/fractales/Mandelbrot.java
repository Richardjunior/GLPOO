package fractales;


import java.awt.Color;
import java.awt.image.BufferedImage;

public class Mandelbrot implements Fractale {

	private int width; 
	private int height;
	private int iterations; 
	private BufferedImage buffer; // Image tampon

	// PARAMETRES MODIFIABLES via MAIN
	private int scale;
	private float estompage;
	private float color;
	private float centreX;
	private float centreY;
	private float maxX;
	private float minX;
	private float maxY;
	private float minY;
	
	
	public Mandelbrot(float centreX, float centreY, int scale,int width, int height,float estompage, float color, int iterations) {
		buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.scale = scale;
		this.width = width;
		this.height = height;
		this.estompage = estompage;
		this.color = color;
		this.centreX = centreX;
		this.centreY = centreY;
		this.iterations = iterations;
		
		calcMinMax();
	}

	
	private void draw() {
		float X,Y;
		for (int x = 0; x < width; x += 1) {
			X = (float) x/scale+minX;
			for (int y = 0; y < height; y += 1) {
				Y = (float) y/scale + minY;
				final int color = colorCalcul(X,Y);
				buffer.setRGB(x, y, color);
			}
		}
	}

	private int colorCalcul(float x, float y) {
		final float C_X = x;
		final float C_Y = y;
		
		int n = 0;

		for (; n < iterations; n++) {
			
			// On applique la formule générale pour générer l'ensemble de Mandelbrot (z(n+1)
			// = z(n)**2 + constante complexe
			float zx = x * x - y * y + C_X;
			float zy = 2 * x * y + C_Y;
			
			x = zx;
			y = zy;
			
			if (x * x + y * y > 4)
				break;
			

		}
		if (n == iterations)
			return 0x00000000; // noir => dans l'ensemble
		return Color.HSBtoRGB(0.5f + color - (float) estompage * n / iterations, 0.6f, 1); //hors de l'ensemble avec plus ou moins d'itererations
	}
	
	@Override
	public void calcMinMax() {
		maxX = centreX + (float) 0.5 * width / scale;
		minX = centreX - (float) 0.5 * width / scale;
		
		maxY = centreY + (float) 0.5 * height / scale;
		minY = centreY - (float) 0.5 * height / scale;
		
		
}

	@Override
	public BufferedImage getDrawing() {
		draw();
		return buffer;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getIterations() {
		return iterations;
	}

	@Override
	public int getScale() {
		return scale;
	}

	@Override
	public float getCentreX() {
		return centreX;
	}

	@Override
	public float getCentreY() {
		return centreY;
	}

	@Override
	public float getMaxX() {
		return maxX;
	}

	@Override
	public float getMinX() {
		return minX;
	}

	@Override
	public float getMaxY() {
		return maxY;
	}

	@Override
	public float getMinY() {
		return minY;
	}

	@Override
	public void setIterations(int iterations) {
		this.iterations = iterations;
		
	}

	@Override
	public void setScale(int scale) {
		this.scale = scale;
		
	}

	@Override
	public void setCentreX(float centreX) {
		this.centreX = centreX;
		
	}

	@Override
	public void setCentreY(float centreY) {
		this.centreY = centreY;
		
	}

	@Override
	public void setMaxX(float maxX) {
		this.maxX = maxX;
		
	}

	@Override
	public void setMinX(float minX) {
		this.minX = minX;
		
	}

	@Override
	public void setMaxY(float maxY) {
		this.maxY = maxY;
		
	}

	@Override
	public void setMinY(float minY) {
		this.minY = minY;
		
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
		
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
		
	}

	@Override
	public void setBuffer(BufferedImage bufferedImage) {
		this.buffer = bufferedImage;
		
	}

	
}
