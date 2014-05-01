import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class Principale{
	
	/*
	 * Attribut définissant la taille de la grille
	 * TAILLEGRILLE	: maximum de lampes par côté dans une grille
	 * MOVE			: maximum de coups autorisés au joueur 
	 */
	public final static int TAILLEGRILLE=5;
	public final static int MOVE=15;
	public final static int NBCASESAUDEPART=8;
	//
    public static void main (String[] arg){
	JFrame fenetre=new JFrame("Jeu des lampes");
	fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	final Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
	fenetre.setContentPane(new Contenant());
	fenetre.pack();
	fenetre.setLocation((tailleEcran.width-fenetre.getWidth())/2,(tailleEcran.height-fenetre.getHeight())/2);
	fenetre.setVisible(true);
	fenetre.getContentPane().requestFocusInWindow();
    }
}