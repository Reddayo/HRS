package View;

import java.awt.Color;
import java.awt.Font;


import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A custom button component that extends {@link JButton}.
 * This button has different background colors for different states: normal, hover, and pressed.
 * It also includes custom font and color settings.
 * 
 * @author Jusper Angelo Cesar
 * @version 4.4
 * 
 */
public class PlainButton extends JButton{

   
	/**
	 * 
	 */
	private static final long serialVersionUID = 6921500074995276156L;
	/**
	 * The background color of the button when the mouse hovers over it.
	 */
	private Color hoverBackgroundColor;

	/**
	 * The background color of the button when it is pressed.
	 */
	private Color pressedBackgroundColor;

	/**
	 * The default background color of the button when it is not interacted with.
	 */
	private Color normalBackgroundColor;
    
    /**
     * Constructs a {@code PlainButton} with the specified text.
     * Initializes the button with custom colors for different states, a custom font, and certain visual properties.
     * 
     * @param name the text to be displayed on the button
     */
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