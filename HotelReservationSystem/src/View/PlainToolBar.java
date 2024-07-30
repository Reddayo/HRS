package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JToolBar;
/**
 * A custom toolbar component that extends {@link JToolBar}.
 * This toolbar has a plain background color.
 * The background color is set to a light gray (RGB: 236, 236, 236).
 * 
 * @author Jusper Angelo Cesar
 * @version 4.4
 */
public class PlainToolBar extends JToolBar
{
    
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5513026088158535020L;
	/**
	 * Background color used for the component.
	 */
	
	Color bgColor = new Color(236, 236, 236);
	
	 /**
     * Constructs a {@code PlainToolBar} with no border.
     * The default background color is set to light gray.
     */
	PlainToolBar(){
		super.setBorder(null);
	}
	
	 /**
     * Paints the component with the custom background color.
     * This method is called by the AWT/Swing painting system.
     *
     * @param g the {@link Graphics} object used to paint the component
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(bgColor);
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

    }
}
