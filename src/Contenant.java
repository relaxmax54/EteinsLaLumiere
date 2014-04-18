import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
/*
 * Classe modélisant le conteneur principal du jeu
 */
public class Contenant extends JPanel{
	//private Ihm gauche;//conteneur de l'interface positionné à gauche
	//private Grille droite;//conteneur de la grille positionné à droite
	
	public Contenant(){
		setPreferredSize(new Dimension(600,400));
		setLayout(new BorderLayout());
		Ihm gauche=new Ihm();
		Grille droite=new Grille();
		
		add(gauche,BorderLayout.CENTER);
		add(droite,BorderLayout.CENTER
				);
	}
}
