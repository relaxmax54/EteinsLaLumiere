import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class Principale{
	
	/*
	 * Attribut définissant la taille de la grille
	 * TAILLEGRILLE	: maximum de lampes par côté dans une grille
	 * MOVE			: maximum de coups autorisés au joueur 
	 * MODE			: représente les différents modes du jeu :
	 * 0 accueil	: pas de réaction sans sélectionner un mode
	 * 1 jouer		: les lampes réagissent à partir d'un état de départ
	 * 2 configurer	: les lampes sont sélectionnées/déselectionnées sans réaction
	 * 3 aléatoire	: les lampes sont sélectionnées/déselectionnées de manière aléatoire 
	 * 4 fin		: le jeu est terminé
	 */
	public final 	static int TAILLEGRILLE=5;
	public final 	static int MOVE=15;
	public final 	static int NBLAMPESAUDEPART=8;
	public 			static int MODE=0;
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