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
	 * nombre de lampes allumées
	 */
	private static int lampeAllumees=0;
	/*
	 * Attribut listener 
	 */
	private ActionListener listener;
	/*
	 * Attribut définissant le nombre de coups joués
	 */
	private int move=Principale.MOVE;

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
				int ligne,colonne;
				switch (Principale.MODE){//on réagit en fonction des modes sélectionnés
				case 0://mode accueil
					break;
				case 1://mode jouer
					if(move>0){
						ligne=(((Lampe)((JToggleButton)(e.getSource()))).getl());
						colonne=(((Lampe)((JToggleButton)(e.getSource()))).getc());
						lampe[ligne][colonne].changeEtat();
						if(ligne-1<0)
							lampe[Principale.TAILLEGRILLE-1][colonne].changeEtat();
						else
							lampe[ligne-1][colonne].changeEtat();
						if(ligne+1>Principale.TAILLEGRILLE-1)
							lampe[0][colonne].changeEtat();
						else
							lampe[ligne+1][colonne].changeEtat();
						if(colonne-1<0)
							lampe[ligne][Principale.TAILLEGRILLE-1].changeEtat();
						else
							lampe[ligne][colonne-1].changeEtat();
						if(colonne+1>Principale.TAILLEGRILLE-1)
							lampe[ligne][0].changeEtat();
						else
							lampe[ligne][colonne+1].changeEtat();
					}
					move--;
					break;

				case 2://mode configurer

					ligne=(((Lampe)((JToggleButton)(e.getSource()))).getl());
					colonne=(((Lampe)((JToggleButton)(e.getSource()))).getc());
					System.out.println("1: "+ligne+"/"+colonne);
					lampe[ligne][colonne].changeEtat();
					break;
				case 3://mode aléatoire
					for(int i=0;i<Principale.NBLAMPESAUDEPART;i++){
						ligne=(int)(Math.random()*Principale.TAILLEGRILLE);
						colonne=(int)(Math.random()*Principale.TAILLEGRILLE);
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
		Principale.MODE=m;
	}
}


