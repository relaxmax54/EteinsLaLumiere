import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
/*
 * classe qui modélise le comportement des Lampes
 */
public class Lampe extends JButton{
	private static ActionListener listener;
/*
 * attribut qui qualifie l'état de la lampe
 * false : éteint / true : allumé
 */
	private boolean etat;
/*
 * constructeur
 * @param e : booléen état de la lampe au départ	
 */
	public Lampe(boolean e){
		this.etat=e;
		//this.setBackground(Color.green);
	}
	public Lampe(String aNameString){
		super(aNameString);
		setContentAreaFilled(false);
	}
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		int w = getWidth();
		int h = getHeight();
		RoundRectangle2D.Float r2d =new RoundRectangle2D.Float(0, 0, w-1 , h-1, 10, 10);
		g2d.clip(r2d);
		 
		GradientPaint gradient = new GradientPaint(0, 0, Color.blue, 0, h, Color.gray, false);
			g2d.setPaint(gradient);
			g2d.fillRect(0,0,w,h);
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		    RenderingHints.VALUE_ANTIALIAS_OFF);	
			super.paintComponent(g);
	}
}
