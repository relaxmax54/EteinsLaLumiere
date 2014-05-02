import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JToggleButton;
/**
 * classe qui modélise le comportement des Lampes
 */
public class Lampe extends JToggleButton{
	/**
	 * nombre de lampes allumées
	 */
	private static int lampesAllumees=0;
	/**
	 * attributs qui permet de retrouver les coordonnées d'une lampe dans la grille
	 * le coin supérieur gauche fait office d'origine
	 */
	private int l;
	private int c;
	/**
	 * constructeur
	 * @param e : booléen état de la lampe au départ
	 * @param l : n° ligne du bouton
	 * @param c : n° colonne du bouton	
	 */
	public Lampe(int l,int c){
		this.setBackground(Color.green);
		this.l=l;
		this.c=c;
	}
	/**
	 * getters pour récupérer l'abcisse d'un bouton
	 */
	public int getl(){
		return this.l;
	}
	/**
	 * getters pour récupérer l'ordonnée d'un bouton
	 */	
	public int getc(){
		return this.c;
	}
	/**
	 * getter pour connaître l'état du jeu
	 */
	public static int getLampesAllumees(){
		return lampesAllumees;
	}
	/**
	 * setter pour réinitialiser le nombre de lampes allumées
	 */
	public static void initialiseLampes(){
		lampesAllumees=0;
	}
	/**
	 * permet de modifier les états des boutons à chaque coup
	 *@param l : int ligne du tableau
	 *@param c : int colonne du tableau
	 */
	public void changeEtat(){
		if(this.isSelected()){
			this.setSelected(false);
			lampesAllumees--;
		}else{
			this.setSelected(true);
			lampesAllumees++;
		}
		if(lampesAllumees==0){
			Fenetre.mode=4;
		}
	}
	/**
	 * Surcharge de la méthode d'affichage des lampes pour un modèle personnalisé
	 */
	@Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (getModel().isPressed()) {
            g.setColor(Color.green);
            g2.fillRect(3, 3, getWidth() - 6, getHeight() - 6);
        }
        super.paintComponent(g);
        g2.setColor(new Color(128, 0, 128));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(1.2f));
        g2.draw(new RoundRectangle2D.Double(1, 1, (getWidth() - 3),(getHeight() - 3), 12, 8));
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawLine(4, getHeight() - 3, getWidth() - 4, getHeight() - 3);
        g2.dispose();
    }
}
