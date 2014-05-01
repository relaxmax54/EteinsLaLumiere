import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class Fenetre extends JFrame{
	/*
	 * Attribut définissant la taille de la grille
	 * TAILLEGRILLE	: maximum de lampes par côté dans une grille
	 * MOVE			: maximum de coups autorisés au joueur 
	 * MODE			: représente les différents modes du jeu :
	 * 0 accueil	: pas de réaction sans sélectionner un mode
	 * 1 jouer		: les lampes réagissent à partir d'un état de départ
	 * 2 configurer	: les lampes sont sélectionnées/déselectionnées sans réaction
	 * 3 aléatoire	: les lampes sont sélectionnées/déselectionnées de manière aléatoire 
	 * 4 fin		: le jeu est terminé
	 */
	public final 	static int TAILLEGRILLE=5;
	public final 	static int MOVE=15;
	public final 	static int NBLAMPESAUDEPART=8;
	public 			static int MODE=0;
	// tableau des boutons de sélection
	private static JButtonArrondi[] mode;
	// tableau des lampes 
	private Lampe[][] lampe;
	// nombre de lampes allumées
	private static int lampesAllumees=0;
	// Attribut définissant le nombre de coups joués
	private int move=MOVE;
	/**
	 * Contructeur de la classe Fenetre
	 * @param titre	: titre de la fenêtre
	 */
	public Fenetre(String titre){
	 	/*
	 	 * On donne un titre 
	 	 */
		this.setTitle(titre);
		/*
		 * on crée et on ajoute un contenant
		 */
		JPanel contenant = new JPanel();
		contenant.setPreferredSize(new Dimension(600,400));
		contenant.setLayout(new BorderLayout());
		/*
		 * on crée un ActionListener commun à tous les composants
		 */
		Ihm ecouteur=new Ihm();
		/*
		 * On crée et on ajoute le composant IHM pour Interface Home Machine
		 */
		JPanel boutons = new JPanel();
		boutons.setLayout(new BoxLayout(boutons,BoxLayout.PAGE_AXIS));
		String[] texte={"Configurer","Aléatoire","Jouer","0"};
		mode=new JButtonArrondi[4];
		for(int i=0;i<4;i++){
			mode[i]=new JButtonArrondi(texte[i]);
			mode[i].addActionListener(ecouteur);
			boutons.add(mode[i]);	
		}
		mode[3].setEnabled(false);
		contenant.add(boutons,BorderLayout.WEST);
		/*
		 * On crée et on ajoute le composant grille qui représente les lampes
		 */
		JPanel grille=new JPanel();
		grille.setLayout(new GridLayout(TAILLEGRILLE,TAILLEGRILLE));
		lampe=new Lampe[TAILLEGRILLE][TAILLEGRILLE];
		for(int i=0;i<TAILLEGRILLE;i++){
			for(int j=0;j<TAILLEGRILLE;j++){
				lampe[i][j]=new Lampe(false,i,j);
				lampe[i][j].addActionListener(ecouteur);	
				grille.add(lampe[i][j]);
			}
		}
		contenant.add(grille,BorderLayout.CENTER);
		this.setContentPane(contenant);
	}
	private void initialiserGrille(){
		for(int i=0;i<TAILLEGRILLE;i++){
			for(int j=0;j<TAILLEGRILLE;j++){
				lampe[i][j].setSelected(false);
				lampesAllumees=0;
			}
		}
	}
	/*
	 * Déclaration de la classe interne chargée d'écouter les composants et de réagir en conséquence
	 */
	class Ihm implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int ligne,colonne;
	    	System.out.println(e.getActionCommand());
	    	switch (e.getActionCommand()){
			case "Configurer":
				MODE=2;
				mode[3].setText(Integer.toString(0));
				initialiserGrille();
				break;
			case "Aléatoire":
				MODE=3;
				mode[3].setText(Integer.toString(0));
				initialiserGrille();
				for(int i=1;i<NBLAMPESAUDEPART;i++){
					ligne=(int)(Math.random()*TAILLEGRILLE);
					colonne=(int)(Math.random()*TAILLEGRILLE);
					lampe[ligne][colonne].changeEtat();
				}
				break;
			case "Jouer":
				MODE=1;
				move=15;
				mode[3].setText(Integer.toString(MOVE));
				break;
			default:
				ligne=(((Lampe)((JToggleButton)(e.getSource()))).getl());
				colonne=(((Lampe)((JToggleButton)(e.getSource()))).getc());
				System.out.println("1: "+ligne+"/"+colonne);
				switch (MODE){//on réagit en fonction des modes sélectionnés
				case 0://mode accueil
					break;
				case 1://mode jouer
					if(move>0){					
						ligne=(((Lampe)((JToggleButton)(e.getSource()))).getl());
						colonne=(((Lampe)((JToggleButton)(e.getSource()))).getc());
						//
						if(ligne-1>-1)
							lampe[ligne-1][colonne].changeEtat();
						if(ligne+1<TAILLEGRILLE)
							lampe[ligne+1][colonne].changeEtat();
						if(colonne-1>-1)
							lampe[ligne][colonne-1].changeEtat();
						if(colonne+1<TAILLEGRILLE)
							lampe[ligne][colonne+1].changeEtat();
						/*
						if(ligne-1<0)
							lampe[TAILLEGRILLE-1][colonne].changeEtat();
						else
							lampe[ligne-1][colonne].changeEtat();
						if(ligne+1>TAILLEGRILLE-1)
							lampe[0][colonne].changeEtat();
						else
							lampe[ligne+1][colonne].changeEtat();
						if(colonne-1<0)
							lampe[ligne][TAILLEGRILLE-1].changeEtat();
						else
							lampe[ligne][colonne-1].changeEtat();
						if(colonne+1>TAILLEGRILLE-1)
							lampe[ligne][0].changeEtat();
						else
							lampe[ligne][colonne+1].changeEtat();
						*/
						move--;
						mode[3].setText(Integer.toString(move));
						if(move==0)//fin du jeu
							MODE=4;
					}
					break;
				case 2://mode configurer
					break;
				case 3://mode aléatoire
					lampe[ligne][colonne].changeEtat();
					break;
				case 4://Fin du jeu
					lampe[ligne][colonne].changeEtat();
				default:
					break;
				}
				break;
			}
		}
	}
}
