import java.awt.event.ActionListener;

import javax.swing.JButton;
/*
 * classe qui modélise le comportement des Lampes
 */
public class Lampe extends JButton{
	private static ActionListener listener;
/*
 * attribut qui qualifie l'état de la lampe
 * false : éteint / true : allumé
 */
	private boolean etat;
/*
 * constructeur
 * @param e : booléen état de la lampe au départ	
 */
	public Lampe(boolean e){
		this.etat=e;
	}
}
