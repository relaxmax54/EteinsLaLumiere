import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JToggleButton;


public class Grille extends JPanel{
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
	private int move=Principale.MOVE;
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
		setLayout(new GridLayout(Principale.TAILLEGRILLE,Principale.TAILLEGRILLE));
		lampe=new Lampe[Principale.TAILLEGRILLE][Principale.TAILLEGRILLE];
		/*
		 * construction du listener
		 */
		listener=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				switch (mode){//on réagit en fonction des modes sélectionnés
				case 0://mode accueil
					if(move>0){

					}
					move--;
					break;
				case 1://mode jouer
					if(move>0){
						int ligne=(((Lampe)((JToggleButton)(e.getSource()))).getl());
						int colonne=(((Lampe)((JToggleButton)(e.getSource()))).getc());
						System.out.println("1: "+ligne+"/"+colonne);
						lampe[ligne][colonne].changeEtat();
						lampe[ligne-1][colonne].changeEtat();
						lampe[ligne+1][colonne].changeEtat();
						lampe[ligne][colonne-1].changeEtat();
						lampe[ligne][colonne+1].changeEtat();
					}
					move--;
					break;
				case 2://mode configurer
					
					break;
				case 3://mode aléatoire
					for(int i=0;i<Principale.NBCASESAUDEPART;i++){
						int ligne=(int)(Math.random()*Principale.TAILLEGRILLE);
						int colonne=(int)(Math.random()*Principale.TAILLEGRILLE);
						lampe[ligne][colonne].changeEtat();
					}
				default:
					break;
				}
			}
		};
		for(int i=0;i<Principale.TAILLEGRILLE;i++){
			for(int j=0;j<Principale.TAILLEGRILLE;j++){
				lampe[i][j]=new Lampe(false,i,j);
				lampe[i][j].addActionListener(listener);	
				lampe[i][j].setActionCommand(Integer.toString(i));	
				add(lampe[i][j]);
			}
		}
	}
	public void setMode(int m){
		this.mode=m;
	}
}


