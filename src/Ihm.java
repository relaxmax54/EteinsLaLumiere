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
	/*
	 * constructeur
	 */
	public Ihm(){
		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		String[] texte={"Configurer","Aléatoire","Jouer","0"};
		JButton mode[]=new JButton[4];
		//construction du listener
		listener=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				switch (((JButton)(e.getSource())).getText()){
					case "Configurer":
						//setMode(2);
						break;
					case "Aléatoire":
						break;
					case "*":
						break;
					case "Clr":
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
