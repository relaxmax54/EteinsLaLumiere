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
		//ActionListener listener=new ActionListener();
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				lampe[i][j]=new Lampe(false);
				//lampe[i][j].addActionListener(listener);	
				add(lampe[i][j]);
			}
		}
	}
}


