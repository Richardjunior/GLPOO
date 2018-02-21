package fractales.fr.domain;


public class Launcher {

	public static void main(String[] args) {
		
		/*
		 * Param :
		 *         0 -> une Julia   (modifier les paramètres dans FractaleLauncherFrame)
		 *         1 -> Mandelbrot
		 *         autre -> fusion de deux Julia
		 */		
		new FractaleLauncherFrame(0);
	}
}
