package fractales;



import java.util.ArrayList;
import java.util.List;

import dao.Tirage;
import frames.FractaleJFrame;

public class TirageToFractaleConverter {
	private int p; // => b1
	private int q; // => b2
	private float estompage; // => e1
	private int echelle; // b3
	private float couleur; // => b4
	private int iterations; // pas de boule
	private String typeFractale;
	
	private List<Tirage> tirages;
	
	// Prochaine verison : trouver des parametres supplémentaires (pour b5 et e2)

	public TirageToFractaleConverter(List<Tirage> tirages, String typeFractale) {
		this.tirages = tirages;
		this.typeFractale = typeFractale;
		
	}
	
	public List<Fractale> convertTirageToFractale(){
		
		List<Fractale> fractales = new ArrayList<Fractale>();
		for(Tirage tirage : tirages) {
			convertTirageToParamFractale(tirage.getB1(),tirage.getB2(),tirage.getB3(),tirage.getB4(),10,tirage.getE1(),10);
			Fractale fractale = getFractale();
			fractales.add(fractale);
		}
		return fractales;
	}


	private void convertTirageToParamFractale(int b1,int b2, int b3, int b4, int b5, int e1, int e2) {
		p = Math.min(b1, b2);
		q = Math.max(b1,b2);
		
		echelle = 200 + 10 * (b3 -1);
		couleur = (float) b4/50;
		iterations = b5 * 10;
		
		estompage = (float) e1+2; 
		
	}
	
	

	private Fractale getFractale() {
		float Re_C = (float) ((float) ((float)1/2*Math.cos((float)2*Math.PI*p/q) - (float)1/4*Math.cos((float)4*Math.PI*p/q)) );
		float Im_C = (float) ((float) ((float)1/2*Math.sin((float)2*Math.PI*p/q) - (float)1/4*Math.sin((float)4*Math.PI*p/q)));
		
		if(typeFractale.equals("Julia")) {
		
			/*
			 * On choisit le complexe constant C comme le centre du bulbe p/q translaté (donc à la périphérie du bulbe)
			 */
			float X =  (float) ((float) 1/q*Math.cos((float) (2*Math.PI*p/q)));
			
			
			JuliaFractale julia = new JuliaFractale(Re_C + X,Im_C + X,echelle,800,800,estompage,couleur,iterations);
			return julia;
		}
		else {
			return new Mandelbrot(Re_C, Im_C, echelle, 800, 800, estompage, couleur, iterations);
		}
	}

}
