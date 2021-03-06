import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;

public class JButtonArrondi extends JButton{
	/**
	 * constructeur du bouton personnalisé
	 * @param nom :	String		nom du bouton
	 */
	public JButtonArrondi(String nom){
		super(nom);
	    	setContentAreaFilled(false);
	}
	/**
	 * surcharge de la méthode d'affichage du bouton pour un modéle plus personnalisé
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		int w = getWidth();
	    int h = getHeight();
		RoundRectangle2D.Float r2d =new RoundRectangle2D.Float(0, 0, w-1 , h-1, 30, 30);
		g2d.clip(r2d);
	    GradientPaint gradient = new GradientPaint(0, 0, Color.green, 0, h, Color.white, false);
		g2d.setPaint(gradient);
		g2d.fillRect(0,0,w,h);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);	
		super.paintComponent(g);
	}
}

