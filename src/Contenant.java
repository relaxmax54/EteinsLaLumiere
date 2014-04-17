import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
/*
 * Classe modélisant le conteneur principal du jeu
 */
public class Contenant extends JPanel{
	//private Ihm gauche;//conteneur de l'interface positionné à gauche
	//private Grille droite;//conteneur de la grille positionné à droite
	
	public Contenant(){
		setPreferredSize(new Dimension(300,200));
		setLayout(new BorderLayout());
		Ihm gauche=new Ihm();
		Grille droite=new Grille();
		add(gauche,BorderLayout.WEST);
		add(droite,BorderLayout.EAST);
	}
}
