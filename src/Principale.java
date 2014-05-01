import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;

public class Principale{
    public static void main (String[] arg){
    	/*
    	 * On crée une fenêtre et on lui donne le focus
    	 */
    	Fenetre fenetre=new Fenetre("Jeu des lampes");
    	fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	final Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
    	fenetre.pack();
    	fenetre.setLocation((tailleEcran.width-fenetre.getWidth())/2,(tailleEcran.height-fenetre.getHeight())/2);
    	fenetre.setVisible(true);
    	fenetre.getContentPane().requestFocusInWindow();
    }
}