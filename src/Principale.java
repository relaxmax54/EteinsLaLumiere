import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class Principale{
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