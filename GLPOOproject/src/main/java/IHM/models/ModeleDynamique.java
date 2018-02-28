package IHM.models;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import IHM.dao.CsvTirageDao;
import IHM.domain.SimpleTirage;
import IHM.domain.Tirage;

public class ModeleDynamique extends AbstractTableModel {


	private final String[] header;

	private String fileName = "src/main/ressources/euromillions_4.csv";
	private CsvTirageDao dao = new CsvTirageDao(fileName);
	final List<Tirage> tirages;

	public ModeleDynamique(String fileName) {
		this.fileName = fileName;
		dao = new CsvTirageDao(fileName);
		tirages = dao.findAllTirages();
		header = new String[] { "B1", "B2", "B3", "B4", "B5", "E1", "E2" };
	}

	public ModeleDynamique() {
		tirages = dao.findAllTirages();
		header = new String[] { "Numero de tirage", "B1", "B2", "B3", "B4", "B5", "E1", "E2" };
	}

	public int getColumnCount() { // retourne le nombre de colonnes
		return header.length;
	}

	public int getRowCount() { // retourne le nombre de lignes
		return tirages.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) { // retourne la valeur de la case indiquée
		switch (columnIndex) {
		case 0:
			return rowIndex;
		case 1:
			return tirages.get(rowIndex).getB1();
		case 2:
			return tirages.get(rowIndex).getB2();
		case 3:
			return tirages.get(rowIndex).getB3();
		case 4:
			return tirages.get(rowIndex).getB4();
		case 5:
			return tirages.get(rowIndex).getB5();
		case 6:
			return tirages.get(rowIndex).getE1();
		case 7:
			return tirages.get(rowIndex).getE2();
		default:
			throw new IllegalArgumentException("ERROR");
		}
	}

	// A MODIFIER
	public String getColumnName(int columnIndex) { // retourne l'entete de la colonne indiquée
		return header[columnIndex];
	}

	/*
	 * @Override public Class<?> getColumnClass(int columnIndex) { switch
	 * (columnIndex) { case 0: return int.class; case 1: return int.class; case 2:
	 * return int.class; case 3: return int.class; case 4: return int.class; case 5:
	 * return int.class; default: throw new
	 * IllegalArgumentException("OLALAAAA problème au niveau des classes"); } }
	 */

	// Action des boutons sur la table
	/**
	 * 
	 * public void ajouterChat(final Tirage tirage) { LOGGER.debug("ajouterChat");
	 * 
	 * tirages.add(tirage);
	 * 
	 * final int position = tirages.size() - 1; fireTableRowsInserted(position,
	 * position); //le modele informe l'interface qu'il y a un changement }
	 * 
	 * public void supprimerChat(final int rowIndex) {
	 * LOGGER.debug("supprimerChat");
	 * 
	 * tirages.remove(rowIndex); fireTableRowsDeleted(rowIndex, rowIndex); }
	 */
	
	public Tirage rowToTirage(int rowIndex) {
		final SimpleTirage tirage = new SimpleTirage();
		
		tirage.setB1(tirages.get(rowIndex).getB1());
		tirage.setB2(tirages.get(rowIndex).getB2());
		tirage.setB3(tirages.get(rowIndex).getB3());
		tirage.setB4(tirages.get(rowIndex).getB4());
		tirage.setB5(tirages.get(rowIndex).getB5());
		tirage.setE1(tirages.get(rowIndex).getE1());
		tirage.setE2(tirages.get(rowIndex).getE2());
		
		return tirage;
	}

}
