package fractales.fr.fractales;

import java.awt.image.BufferedImage;

public class FractaleService {

	private static FractaleService singleton;
	
	private final int ZOOM_COEF = 2;
	
	private FractaleService() {}
	
	public static FractaleService getInstance() {
		if(singleton == null) {
			singleton = new FractaleService();
		}
		return singleton;
	}
	
	
	public void changeCenter(Fractale f, float X, float Y) {
		final float centreX = (float) (f.getCentreX() + (float) X * f.getWidth()/f.getScale());
		final float centreY =  (float) (f.getCentreY() + (float) Y * f.getHeight()/f.getScale());
		f.setCentreX( centreX );
		f.setCentreY( centreY );
		
		f.calcMinMax();
		
	}

	public void zoom(Fractale f,boolean bool) {
		int scale = f.getScale();
		int iterations = f.getIterations();
		if(bool) {
			f.setScale(scale * ZOOM_COEF); // à utiliser max 5fois : echelleMAX = 10^7 
			f.setIterations((int) (iterations*1.25f));
		}else {
			f.setScale((int) scale / ZOOM_COEF);
			f.setIterations((int) (iterations/1.25f));
		}
		f.calcMinMax();
		
	}

	public void changeSize(Fractale f,int width, int height) {
		f.setHeight(height);
		f.setWidth(width);
		f.setBuffer(new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB));
		
	}
}
