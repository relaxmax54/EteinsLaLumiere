import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Grille extends JPanel{
	private Lampe[][] lampe;
	/*
	 * constructeur
	 */
	public Grille(){
		setLayout(new GridLayout(5,5));
		lampe=new Lampe[5][5];
		ActionListener listener=new ActionListener();
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				lampe[i][j]=new Lampe(false);
				lampe[i][j].addActionListener(listener);	
				add(lampe[i][j]);
			}
		}
	}
}		
		
		
		//construction du listener
		listener=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				switch (((JButton)(e.getSource())).getText()){
					case "+":
						if(op==0){
							op=1;//on enregistre l'opération +
							val[1]=0;//on initialise la deuxième valeur à enregistrer
							index=1;//on enregistre la deuxième valeur de l'opération
							affichage=new StringBuffer("0.0");
						}else{
							val[1]+=val[0];
							affichage=new StringBuffer(String.valueOf(val[1]));
						}
						ecran.setText(new String(affichage));//maj affichage
						break;
					case "-":
						if(op==0){
							op=2;//on enregistre l'opération +
							index=1;//on enregistre la deuxième valeur de l'opération
							affichage=new StringBuffer("0.0");//maj affichage
						}else{
							val[1]=val[0]-val[1];
							affichage=new StringBuffer(String.valueOf(val[1]));
						}
						ecran.setText(new String(affichage));
						break;
					case "*":
						if(op==0){
							op=3;//on enregistre l'opération +
							index=1;//on enregistre la deuxième valeur de l'opération
							affichage=new StringBuffer("0.0");
						}else{
							val[1]*=val[0];
							affichage=new StringBuffer(String.valueOf(val[1]));
						}
						ecran.setText(new String(affichage));//maj affichage
						break;
					case "Clr":
						val[index]=0;
						affichage=new StringBuffer("0.0");
						ecran.setText(new String(affichage));
						break;
					case "x2":
						val[1]=val[0]*val[0];
						affichage=new StringBuffer(String.valueOf(val[1]));
						ecran.setText(new String(affichage));//maj affichage
						break;
					case "DEL":
						if(affichage.length()>3){
							affichage.deleteCharAt(0);
						}else
							affichage=new StringBuffer("0.0");
						val[index]=Double.parseDouble(new String(affichage));
						ecran.setText(new String(affichage));//maj affichage
						break;
					case "sqrt":
						break;
					case "=":
						switch(op){
						case 1://addition
							val[1]+=val[0];
							break;
						case 2://soustraction
							val[1]-=val[0];
							break;
						case 3://multiplication
							val[1]*=val[0];
							break;
						case 4://division
							val[1]/=val[0];
							break;
						default:
							break;
						}
						//on affiche le résultat
						affichage=new StringBuffer(String.valueOf(val[1]));
						ecran.setText(new String(affichage));//maj affichage
						op=0;//retour à la situation initiale
						index=0;//on enregistre à nouveau la première valeur de la prochaine opération
						break;
					default://on gère la saisie d'un chiffre
						if(val[index]==0)//si valeur=0 on change remplace le chiffre 0 par la nouvelle valeur
							affichage.replace(0,1,((JButton)(e.getSource())).getText());
						else//on ajoute un chiffre à gauche du nombre affiché	
							affichage.insert(affichage.length()-2,((JButton)(e.getSource())).getText());
						//maj valeur
						val[index]=Double.parseDouble(new String(affichage));
						//maj affichage
						ecran.setText(new String(affichage));
						break;
				}
			}
		};
	}
}
