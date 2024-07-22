package View;

import java.awt.Color;
import java.awt.Font;


import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PlainButton extends JButton{

   
	/**
	 * 
	 */
	private static final long serialVersionUID = 6921500074995276156L;
	private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;
    private Color normalBackgroundColor;
    PlainButton(String name){
        super(name);
        setContentAreaFilled(false);
        setOpaque(true);
        normalBackgroundColor = new Color(242, 242, 242);
        setBackground(normalBackgroundColor);
        hoverBackgroundColor = new Color(210, 210, 210);
        pressedBackgroundColor = new Color(210, 210, 210).darker();
        setForeground(Color.decode("0x000000"));
        setFocusPainted(false);
        setBorderPainted(false);
        setVisible(true);
        setFont(new Font("Segoe UI", Font.PLAIN, 12));

        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                if (getModel().isPressed()) {
                    setBackground(pressedBackgroundColor);
                } else if (getModel().isRollover()) {
                    setBackground( hoverBackgroundColor);
                } else {
                    setBackground(normalBackgroundColor);
                }
            }
        });

        
        
    }

  
}