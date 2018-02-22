package EuroProject.euroProject;

import java.util.List;

import org.apache.log4j.Logger;

import dao.CsvTirageDao;
import domain.Tirage;

public class Launcher {
	private static final Logger LOGGER = Logger.getLogger(Launcher.class);
	
	public static void main(String[] args) {
		LOGGER.debug("Hello World");	
		
		final CsvTirageDao dao = new CsvTirageDao("src/main/ressources/euromillions_4.csv");
		final List<Tirage> tirages = dao.findAllTirages();
		
		LOGGER.debug("Listes crées");
		
		for(final Tirage tirage : tirages) {
			LOGGER.debug("* "+tirage.getB1()+" "+tirage.getB2()+" "+tirage.getB3()+" "+tirage.getB4()+" "+tirage.getB5()+" "+tirage.getE1()+" "+tirage.getE2()+"\n");
		}
	}
}
