package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlainToggleButton extends JToggleButton{

    
	/**
	 * 
	 */
	private static final long serialVersionUID = 839727447148226577L;
	private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;
    private Color normalBackgroundColor;
    PlainToggleButton(String name){
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
                } else if(getModel().isSelected()){
                    setBackground(hoverBackgroundColor);
                }else if(!getModel().isSelected()){
                    setBackground(normalBackgroundColor);
                } else {
                    setBackground(normalBackgroundColor);
                }
            }
        });
         addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverBackgroundColor);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(!getModel().isSelected()){
                    setBackground(normalBackgroundColor);
                    repaint();
                }
                
            }
        });
    }

  
}
