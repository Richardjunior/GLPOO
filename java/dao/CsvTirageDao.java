package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import domain.SimpleTirage;
import domain.Tirage;

public class CsvTirageDao {
	private String fileName;

	public CsvTirageDao(String fileName) {
		this.fileName = fileName;
	}

	private List<String> lectureFichier() throws Exception {

		// On instancie un fichier
		final File file = new File(fileName);

		final List<String> lines = new ArrayList<>();

		final FileReader fr = new FileReader(file);
		final BufferedReader br = new BufferedReader(fr);

		// met les lignes du buffer dans la liste de lignes de tirage
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			lines.add(line);
		}

		br.close();
		fr.close();

		return lines;
	}

	private Tirage lineToTirage(final String line) {
		final SimpleTirage tirage = new SimpleTirage();
		final String[] colonnes = line.split(";");

		// On set les attributs du tirage

		final String[] boulesValues = colonnes[12].split("-");
		tirage.setB1(new Integer(boulesValues[1]));
		tirage.setB2(new Integer(boulesValues[2]));
		tirage.setB3(new Integer(boulesValues[3]));
		tirage.setB4(new Integer(boulesValues[4]));
		tirage.setB5(new Integer(boulesValues[5]));

		final String[] etoilesValues = colonnes[13].split("-");
		tirage.setE1(new Integer(etoilesValues[1]));
		tirage.setE2(new Integer(etoilesValues[2]));

		return tirage;

	}

	public List<Tirage> findAllTirages() {
		final List<Tirage> tirages = new ArrayList<>();

		// Fichier > tab de ligne
		List<String> lines;
		try {
			lines = lectureFichier();

			// ligne > tirage
			boolean isFirst = true;
			for (final String line : lines) {
				if (line.trim().isEmpty() || line.startsWith("#")) {
					continue;
				}

				if (isFirst) {
					isFirst = false;
					continue;
				}

				final Tirage tirage = lineToTirage(line);
				tirages.add(tirage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// return
		return tirages;
	}
}
