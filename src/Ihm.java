import javax.swing.JPanel;
import javax.swing.JTextField;
/*
 * Classe qui modélise l'interface du jeu
 */
public class Ihm extends JPanel{
	/*
	 * constructeur
	 */
	public Ihm(){
		JTextField deplacement=new JTextField("0");
		deplacement.setHorizontalAlignment(JTextField.RIGHT);
	}
}
