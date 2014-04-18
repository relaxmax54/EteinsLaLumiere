import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JToggleButton;


public class Grille extends JPanel{
	/*
	 * Attribut définissant la taille de la grille
	 */
	public final int TAILLEGRILLE=5;
	public final int MOVE=15;
	/*
	 * tableau des lampes
	 */
	private Lampe[][] lampe;
	/*
	 * Attribut listener 
	 */
	private ActionListener listener;
	/*
	 * Attribut définissant le nombre de coups joués
	 */
	private int move=MOVE;
	/*
	 * Les différents modes du jeu :
	 * 0 accueil : pas de réaction sans sélectionner un mode
	 * 1 jouer : les boutons réagissent à partir d'un état de départ
	 * 2 configurer : les boutons enregistrent les actions sans réagir
	 */
	private int mode=0;
	/*
	 * constructeur
	 */
	public Grille(){
		setLayout(new GridLayout(5,5));
		lampe=new Lampe[TAILLEGRILLE][TAILLEGRILLE];
		/*
		 * construction du listener
		 */
		listener=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				switch (mode){//on réagit en fonction des modes sélectionnés
				case 0://accueil
					if(move>0){
						System.out.println(e);
						System.out.println(((JToggleButton)(e.getSource())).getText());
						move--;
					}
					break;
				case 1://jouer
					break;
				case 2://configurer
					
					break;
				default:
					break;
				}
			}
		};
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				lampe[i][j]=new Lampe(false);
				lampe[i][j].addActionListener(listener);	
				lampe[i][j].setActionCommand(Integer.toString(i));	
				add(lampe[i][j]);
			}
		}
	}
}


