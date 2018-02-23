package fractales.domain;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import IHM.domain.Tirage;
import fractales.Frame.JuliaFrame;
import fractales.Frame.MandelbrotFrame;
import fractales.fractales.FractaleService;
import fractales.fractales.JuliaFractale;

public class TirageToFractale {
	private int p; // => b1
	private int q; // => b2
	private float estompage; // => e1
	private int echelle; // pas de boule
	private float couleur; // => b4
	private int iterations; // pas de boule
	
	// Prochaine verison : trouver des parametres supplémentaires (pour b3,b5 et e2)

	public TirageToFractale(Tirage tirage) {
		convertTirageToParamJulia(tirage.getB1(),tirage.getB2(),1,tirage.getB4(),10,tirage.getE1(),0);
		doLaunchJulia(p, q,echelle,estompage,couleur);
	}
		/*}else if(i == 1){
			
			final float echelle = 200;
			new MandelbrotFrame(echelle);
			
		}else {
			fusion();
		}
	}



	private void fusion() {
	//JULIA 1
		convertTirageToParamJulia(8,50,40,25,20,5,0); // p , q , echelle , couleur , iterations , estompage , null
		float Re_C = (float) ((float)1/2*Math.cos((float)2*Math.PI*p/q) - (float)1/4*Math.cos((float)4*Math.PI*p/q)); 
		float Im_C = (float) ((float)1/2*Math.sin((float)2*Math.PI*p/q) - (float)1/4*Math.sin((float)4*Math.PI*p/q)); 
		
		JuliaFractale julia1 = new JuliaFractale(Re_C,Im_C,echelle,800,800,estompage,couleur,iterations);
		BufferedImage image1 = julia1.getDrawing();
		
	//JULIA 2
		
		convertTirageToParamJulia(2,50,40,25,20,5,0); // p , q , echelle , couleur , iterations , estompage , null
		Re_C = (float) ((float)1/2*Math.cos((float)2*Math.PI*p/q) - (float)1/4*Math.cos((float)4*Math.PI*p/q)); 
		Im_C = (float) ((float)1/2*Math.sin((float)2*Math.PI*p/q) - (float)1/4*Math.sin((float)4*Math.PI*p/q)); 
		
		JuliaFractale julia2 = new JuliaFractale(Re_C,Im_C,echelle,800,800,estompage,couleur,iterations);
		BufferedImage image2 = julia2.getDrawing();
		
	
	//Fusion
		
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		images.add(image1);
		images.add(image2);
		
		BufferedImage image = FractaleService.getInstance().joinFractales(images);
		
	//frame
		JFrame frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new JLabel(new ImageIcon(image)));
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
*/


	private void convertTirageToParamJulia(int b1,int b2, int b3, int b4, int b5, int e1, int e2) {
		p = Math.min(b1, b2);
		q = Math.max(b1,b2);
		
		echelle = 200 + 37 * (b3-1);
		couleur = (float) b4/50;
		iterations = b5 * 10;
		
		estompage = (float) e1; 
		
	}
	
	

	private void doLaunchJulia(int p, int q, int ECHELLE, float estompage,float couleur) {
		/*
		 * On choisit le complexe constant C comme le centre du bulbe p/q translaté de +p/q (donc à la périphérie du bulbe)
		 */
		float Re_C = (float) ((float) ((float)1/2*Math.cos((float)2*Math.PI*p/q) - (float)1/4*Math.cos((float)4*Math.PI*p/q)) + (float) 1/q*Math.cos((float)p/q));
		float Im_C = (float) ((float) ((float)1/2*Math.sin((float)2*Math.PI*p/q) - (float)1/4*Math.sin((float)4*Math.PI*p/q)) + (float) 1/q*Math.sin((float)p/q));
		
		new JuliaFrame(Re_C, Im_C, ECHELLE, estompage,couleur,iterations);
	}

}
