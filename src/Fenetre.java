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
	private static int lampeAllumees=0;
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
		 * On crée et on ajoute le composant IHM pour Interface Home Machine
		 */
		JPanel ihm = new JPanel();
		ihm.setLayout(new BoxLayout(ihm,BoxLayout.PAGE_AXIS));
		String[] texte={"Configurer","Aléatoire","Jouer","0"};
		mode=new JButtonArrondi[4];
		for(int i=0;i<4;i++){
			mode[i]=new JButtonArrondi(texte[i]);
			ihm.add(mode[i]);	
			mode[i].addActionListener(new EcouteIhm());
		}
		contenant.add(ihm,BorderLayout.WEST);
		/*
		 * On crée et on ajoute le composant grille qui représente les lampes
		 */
		JPanel grille=new JPanel();
		grille.setLayout(new GridLayout(TAILLEGRILLE,TAILLEGRILLE));
		lampe=new Lampe[TAILLEGRILLE][TAILLEGRILLE];
		for(int i=0;i<TAILLEGRILLE;i++){
			for(int j=0;j<TAILLEGRILLE;j++){
				lampe[i][j]=new Lampe(false,i,j);
				lampe[i][j].addActionListener(new EcouteGrille());	
				//lampe[i][j].setActionCommand(Integer.toString(i));
				grille.add(lampe[i][j]);
			}
		}
		contenant.add(grille,BorderLayout.CENTER);
		this.setContentPane(contenant);
	}
	/*
	 * Déclaration de deux classes interne chargées d'écouter les composants et de réagir en conséquence
	 */
	class EcouteIhm implements ActionListener{
		public void actionPerformed(ActionEvent e) {
	    	System.out.println(e);
			switch (((JButton)(e.getSource())).getText()){
			case "Configurer":
				MODE=2;
				//this.((JButton)(e.getSource())
				break;
			case "Aléatoire":
				MODE=3;
				break;
			case "Jouer":
				MODE=1;
				mode[3].setText(Integer.toString(MOVE));
				break;
			default:
				System.out.println(((JButton)(e.getSource())).getText());
				break;
			}
		}
	}
	class EcouteGrille implements ActionListener{
	   	public void actionPerformed(ActionEvent e) {
			System.out.println(e);
			int ligne,colonne;
			switch (MODE){//on réagit en fonction des modes sélectionnés
			case 0://mode accueil
				break;
			case 1://mode jouer
				if(move>0){
					ligne=(((Lampe)((JToggleButton)(e.getSource()))).getl());
					colonne=(((Lampe)((JToggleButton)(e.getSource()))).getc());
					lampe[ligne][colonne].changeEtat();
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

				for(int i=1;i<NBLAMPESAUDEPART;i++){
					ligne=(int)(Math.random()*TAILLEGRILLE);
					colonne=(int)(Math.random()*TAILLEGRILLE);
					lampe[ligne][colonne].changeEtat();
				}
			default:
				break;
			}
		}
	}
}
