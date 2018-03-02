package fractales;


import java.awt.image.BufferedImage;

public interface Fractale {
	
	public BufferedImage getDrawing();
	
	public int getWidth() ;

	public int getHeight();

	public int getIterations();

	public int getScale();

	public float getCentreX() ;

	public float getCentreY() ;

	public float getMaxX() ;

	public float getMinX();

	public float getMaxY();

	public float getMinY();

	public void setIterations(int iterations);

	public void setScale(int scale);

	public void setCentreX(float centreX);

	public void setCentreY(float centreY) ;

	public void setMaxX(float maxX);

	public void setMinX(float minX);

	public void setMaxY(float maxY);

	public void setMinY(float minY) ;

	public void setHeight(int height);
	
	public void setWidth(int width);

	public void setBuffer(BufferedImage bufferedImage);

	public void calcMinMax();

	public String toString();
}
