import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class Fenetre extends JFrame{
	/**
	 * taille de la grille	: nombre maximum de lampes dans une grille
	 */
	public final 	static int TAILLEGRILLE=5;
	/**
	 *  MAXCOUPS			: maximum de coups autorisés au joueur 
	 */
	public final 	static int MAXCOUPS=15;
	/**
	 * compteur de coups joués
	 */
	private 		int nbcoups=MAXCOUPS;
	/**
	 * mode			: représente les différents modes du jeu :
	 * 0 accueil	: pas de réaction sans sélectionner un mode
	 * 1 jouer		: les lampes réagissent à partir d'un état de départ
	 * 2 configurer	: les lampes sont sélectionnées/déselectionnées sans réaction
	 * 3 aléatoire	: les lampes sont sélectionnées/déselectionnées de manière aléatoire 
	 * 4 fin		: le jeu est terminé
	 */
	public 			static int mode=0;
	/**
	 * nombre de lampes allumées au départ en mode aléatoire
	 */
	public final 	static int NBLAMPESAUDEPART=8;
	/**
	 *  tableau des boutons de sélection
	 */
	private static JButtonArrondi[] defmode;
	/**
	 *  tableau des lampes 
	 */
	private Lampe[][] lampe;
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
		/*
		 * On ajoute les 4 boutons qui définissent les modes de jeu
		 */
		String[] texte={"Configurer","Aléatoire","Jouer","0"};
		defmode=new JButtonArrondi[4];
		for(int i=0;i<4;i++){
			defmode[i]=new JButtonArrondi(texte[i]);
			defmode[i].addActionListener(ecouteur);
			boutons.add(defmode[i]);	
		}
		defmode[3].setEnabled(false);
		contenant.add(boutons,BorderLayout.WEST);
		/*
		 * On crée et on ajoute le composant grille qui représente les lampes
		 */
		JPanel grille=new JPanel();
		grille.setLayout(new GridLayout(TAILLEGRILLE,TAILLEGRILLE));
		/*
		 * On ajoute les lampes
		 */
		lampe=new Lampe[TAILLEGRILLE][TAILLEGRILLE];
		for(int i=0;i<TAILLEGRILLE;i++){
			for(int j=0;j<TAILLEGRILLE;j++){
				lampe[i][j]=new Lampe(i,j);
				lampe[i][j].addActionListener(ecouteur);	
				grille.add(lampe[i][j]);
			}
		}
		contenant.add(grille,BorderLayout.CENTER);
		this.setContentPane(contenant);
	}
	/**
	 * initialise la grille avec toutes les lampes éteintes
	 */
	private void initialiserGrille(){
		for(int i=0;i<TAILLEGRILLE;i++){
			for(int j=0;j<TAILLEGRILLE;j++){
				lampe[i][j].setSelected(false);
			}
		}
		Lampe.initialiseLampes();
	}
	/**
	 * Déclaration de la classe interne chargée d'écouter les composants et de réagir en conséquence
	 */
	class Ihm implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int ligne,colonne;
			/*
			 * On teste les évènements envoyés par les boutons
			 */
	    	switch (e.getActionCommand()){
			case "Configurer":
				if(mode!=3)
					initialiserGrille();
				mode=2; //mode configuration
				defmode[3].setText(Integer.toString(0));
				break;
			case "Aléatoire":
				mode=3;
				defmode[3].setText(Integer.toString(0));
				initialiserGrille();
				for(int i=0;i<NBLAMPESAUDEPART;i++){
					Random r1 = new Random();
					ligne = r1.nextInt(TAILLEGRILLE);
					Random r2 = new Random();
					colonne = r2.nextInt(TAILLEGRILLE);
					lampe[ligne][colonne].changeEtat();
				}
				break;
			case "Jouer":
				if(mode==0)
					initialiserGrille();
				mode=1;//mode jeu
				nbcoups=15;
				defmode[3].setText(Integer.toString(nbcoups));
				break;
			default: // Dans tous les autres cas, un évènement est renvoyé par la grille
				/*
				 * on récupère les coordonnées de la lampe sélectionnée
				 */
				ligne=(((Lampe)((JToggleButton)(e.getSource()))).getl());
				colonne=(((Lampe)((JToggleButton)(e.getSource()))).getc());
				switch (mode){//on réagit en fonction des modes sélectionnés
				case 0://mode accueil : aucune réaction
					break;
				case 1://mode jouer
					if(nbcoups>0){//tant qu'il reste des coups à jouer
						/*
						 * on vérifie que l'on ne sort pas de la grille
						 */
						if(ligne-1>-1)
							lampe[ligne-1][colonne].changeEtat();
						if(ligne+1<TAILLEGRILLE)
							lampe[ligne+1][colonne].changeEtat();
						if(colonne-1>-1)
							lampe[ligne][colonne-1].changeEtat();
						if(colonne+1<TAILLEGRILLE)
							lampe[ligne][colonne+1].changeEtat();
						nbcoups--;
						defmode[3].setText(Integer.toString(nbcoups));
						if(nbcoups==0||mode==4){//fin du jeu
							mode=4;
							JOptionPane alerte;
							alerte = new JOptionPane();
							//Boîte du message d'information
							if(Lampe.getLampesAllumees()==0)
								alerte.showMessageDialog(null, "Vous avez gagné !", "Bien joué !", JOptionPane.INFORMATION_MESSAGE);
							else
								alerte.showMessageDialog(null, "Vous avez perdu !", "Dernier coup joué !", JOptionPane.INFORMATION_MESSAGE);
							initialiserGrille();
							mode=0;
							defmode[3].setText(Integer.toString(0));
						}
					}
					break;
				case 2://mode configurer : aucun réaction
					break;
				case 3://mode aléatoire
					/*
					 * on empêche la modification des lampes
					 */
					lampe[ligne][colonne].changeEtat();
					break;
				case 4://Fin du jeu
					/*
					 * on bloque la modification des lampes
					 */
					lampe[ligne][colonne].changeEtat();
					JOptionPane alerte;
					alerte = new JOptionPane();
					//Boîte du message d'information
					if(Lampe.getLampesAllumees()==0)
						alerte.showMessageDialog(null, "Vous avez gagné !", "Bien joué !", JOptionPane.INFORMATION_MESSAGE);
					else
						alerte.showMessageDialog(null, "Vous avez perdu !", "Dernier coup joué !", JOptionPane.INFORMATION_MESSAGE);
					initialiserGrille();
					break;
				default:
					break;
				}
				break;
			}
		}
	}
}
