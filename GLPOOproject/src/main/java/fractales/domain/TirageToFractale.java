package fractales.domain;


import java.util.ArrayList;
import java.util.List;


import IHM.domain.Tirage;
import fractales.Frame.FractaleFrame;
import fractales.fractales.Fractale;
import fractales.fractales.JuliaFractale;

public class TirageToFractale {
	private int p; // => b1
	private int q; // => b2
	private float estompage; // => e1
	private int echelle; // pas de boule
	private float couleur; // => b4
	private int iterations; // pas de boule
	
	// Prochaine verison : trouver des parametres supplémentaires (pour b3,b5 et e2)

	public TirageToFractale(List<Tirage> tirages) {
		List<Fractale> fractales = new ArrayList<Fractale>();
		for(Tirage tirage : tirages) {
			convertTirageToParamJulia(tirage.getB1(),tirage.getB2(),tirage.getB3(),tirage.getB4(),10,tirage.getE1(),10);
			Fractale fractale = getFractale(p, q,echelle,estompage,couleur);
			fractales.add(fractale);
		}
		
		new FractaleFrame(fractales,800,800);
	}


	private void convertTirageToParamJulia(int b1,int b2, int b3, int b4, int b5, int e1, int e2) {
		p = Math.min(b1, b2);
		q = Math.max(b1,b2);
		
		echelle = 200 + 37 * (b3-1);
		couleur = (float) b4/50;
		iterations = b5 * 10;
		
		estompage = (float) e1+2; 
		
	}
	
	

	private Fractale getFractale(int p, int q, int ECHELLE, float estompage,float couleur) {
		/*
		 * On choisit le complexe constant C comme le centre du bulbe p/q translaté de +p/q (donc à la périphérie du bulbe)
		 */
		float X =  (float) ((float) 1/q*Math.cos((float) (2*Math.PI*p/q)));
		float Re_C = (float) ((float) ((float)1/2*Math.cos((float)2*Math.PI*p/q) - (float)1/4*Math.cos((float)4*Math.PI*p/q)) + X);
		float Im_C = (float) ((float) ((float)1/2*Math.sin((float)2*Math.PI*p/q) - (float)1/4*Math.sin((float)4*Math.PI*p/q)) + X);
		
		JuliaFractale julia = new JuliaFractale(Re_C,Im_C,ECHELLE,800,800,estompage,couleur,iterations);
		return julia;
	}

}
