package EuroProject.euroProject;

import org.apache.log4j.Logger;

import frame.TirageJFrame;

public class LauncherIHM {
	private static final Logger LOGGER = Logger.getLogger(LauncherIHM.class);
	
	public static void main(String[] args) {
		LOGGER.debug("LOTO PSYCHEDELIQUE");		
		final TirageJFrame f = new TirageJFrame();
		f.setVisible(true);
				
	}
}
