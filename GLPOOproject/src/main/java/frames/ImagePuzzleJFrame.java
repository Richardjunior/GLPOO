package frames;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * Cette classe me permet de gérer l'affichage des images du puzzle
 */
public class ImagePuzzleJFrame extends JFrame {

	public ImagePuzzleJFrame() {

		/*
		 * C'est ici que je déclare la position aléatoire des images en fonction de X et
		 * de Y , en sachant q	ue le maximum de x est 2 et le min est 0. Le max de Y est
		 * 1 et le min est 0
		 * 
		 * 
		 * ***********LES POSITIONS GAGNANTES******** 
		 * ------->img1(0,0)
		 * ------->img2(1,0)
		 * ------->img3(2,0) 
		 * ------->img4(0,1) 
		 * ------->img5(1,1)
		 * ------->img6(2,1)
		 */
		

		this.setTitle("Image du Puzzle");
		this.setSize(1380, 1036);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setTitle("Image du Puzzle");
		this.setResizable(false);
		this.setBounds(100, 100, 1068, 576);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		// On crée nos différents conteneurs de couleur différente

		// image1
		JPanel cell1 = new JPanel();
		JLabel labelImg1 = new JLabel();
		labelImg1.setIcon(new ImageIcon("src/main/ressources/img1.png"));
		labelImg1.setLayout(new BoxLayout(labelImg1, BoxLayout.LINE_AXIS));
		cell1.add(labelImg1);

		// image2
		JPanel cell2 = new JPanel();
		JLabel labelImg2 = new JLabel();
		labelImg2.setIcon(new ImageIcon("src/main/ressources/img2.png"));
		labelImg2.setLayout(new BoxLayout(labelImg2, BoxLayout.LINE_AXIS));
		cell2.add(labelImg2);

		// image3
		JPanel cell3 = new JPanel();
		JLabel labelImg3 = new JLabel();
		labelImg3.setIcon(new ImageIcon("src/main/ressources/img3.png"));
		labelImg3.setLayout(new BoxLayout(labelImg3, BoxLayout.LINE_AXIS));
		cell3.add(labelImg3);

		// image4
		JPanel cell4 = new JPanel();
		JLabel labelImg4 = new JLabel();
		labelImg4.setIcon(new ImageIcon("src/main/ressources/img4.png"));
		labelImg4.setLayout(new BoxLayout(labelImg4, BoxLayout.LINE_AXIS));
		cell4.add(labelImg4);

		// image4
		JPanel cell5 = new JPanel();
		JLabel labelImg5 = new JLabel();
		labelImg5.setIcon(new ImageIcon("src/main/ressources/img5.png"));
		labelImg5.setLayout(new BoxLayout(labelImg5, BoxLayout.LINE_AXIS));
		cell5.add(labelImg5);

		// image4
		JPanel cell6 = new JPanel();
		JLabel labelImg6 = new JLabel();
		labelImg6.setIcon(new ImageIcon("src/main/ressources/img6.png"));
		labelImg6.setLayout(new BoxLayout(labelImg6, BoxLayout.LINE_AXIS));
		cell6.add(labelImg6);

		// Le conteneur principal
		JPanel content = new JPanel();
		content.setPreferredSize(new Dimension(300, 120));
		content.setBackground(Color.WHITE);
		// On définit le layout manager
		content.setLayout(new GridBagLayout());

		// L'objet servant à positionner les composants
		GridBagConstraints gbc = new GridBagConstraints();

		// On positionne la case de départ du composant
		gbc.gridx = 0;
		gbc.gridy = 0;
		content.add(cell1, gbc);
		// ---------------------------------------------
		gbc.gridx = 1;
		gbc.gridy = 0;
		content.add(cell2, gbc);
		// ---------------------------------------------
		gbc.gridx = 2;
		gbc.gridy = 0;
		content.add(cell3, gbc);
		// ---------------------------------------------
		gbc.gridx = 0;
		gbc.gridy = 1;
		content.add(cell4, gbc);
		// ---------------------------------------------
		gbc.gridx = 1;
		gbc.gridy = 1;
		content.add(cell5, gbc);
		// ---------------------------------------------
		gbc.gridx = 2;
		gbc.gridy = 1;
		content.add(cell6, gbc);

		// On ajoute le conteneur
		this.setContentPane(content);
		this.setVisible(true);

	}

}
