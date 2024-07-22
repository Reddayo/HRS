package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JToolBar;

public class PlainToolBar extends JToolBar
{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -5513026088158535020L;
	Color bgColor = new Color(236, 236, 236);
	PlainToolBar(){
		super.setBorder(null);
	}
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(bgColor);
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

    }
}
