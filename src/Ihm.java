import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
/*
 * Classe qui modélise l'interface du jeu
 */
public class Ihm extends JPanel{
	private ActionListener listener;
	private JButton[] mode; 
	/*
	 * constructeur
	 */
	public Ihm(){
		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		String[] texte={"Configurer","Aléatoire","Jouer","0"};
		mode=new JButton[4];
		//construction du listener
		listener=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				switch (((JButton)(e.getSource())).getText()){
					case "Configurer":
						Principale.MODE=2;
						//this.((JButton)(e.getSource())
						break;
					case "Aléatoire":
						Principale.MODE=3;
						break;
					case "Jouer":
						Principale.MODE=1;
						mode[3].setText(Integer.toString(Principale.MOVE));
						break;
					default:
						System.out.println(((JButton)(e.getSource())).getText());
						break;
				}
			}
		};
		for(int i=0;i<4;i++){
			mode[i]=new JButtonArrondi(texte[i]);
			add(mode[i]);
			//mode[i].setActionCommand(Integer.toString(i));	
			mode[i].addActionListener(listener);
		}
		//JTextField deplacement=new JTextField("0");
		//deplacement.setHorizontalAlignment(JTextField.RIGHT);
	}
}
