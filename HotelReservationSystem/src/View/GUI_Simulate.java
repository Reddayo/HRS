package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class GUI_Simulate extends JDialog{
       
		/**
	 * 
	 */
	private static final long serialVersionUID = -2149736069481276153L;
		private JTextField hotelNameFieldSB;
        private JTextField guestNameFieldSB;
        
        private JToggleButton standardButtonSB;
        private JToggleButton deluxeButtonSB;
        private JToggleButton executiveButtonSB;
        
        private ButtonGroup roomTypePick;
        
    private JTextField discountFieldSB;
    private JButton bookButtonSB;


    GUI_Simulate(JFrame owner){
      
        super(owner, "Simulate Booking", true);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        
        
        hotelNameFieldSB = new JTextField();
        guestNameFieldSB = new JTextField();
        discountFieldSB = new JTextField();
        
        
        standardButtonSB = new JToggleButton("Standard");
        deluxeButtonSB = new JToggleButton("Deluxe");
        executiveButtonSB = new JToggleButton("Executive");
        //bookButtonSB = new JButton("Book");
        
        
        //bookButtonSB.setActionCommand("new booking");
        standardButtonSB.setBackground(Color.LIGHT_GRAY);
        deluxeButtonSB.setBackground(Color.decode("0xA020F0"));
        executiveButtonSB.setBackground(Color.decode("0xFFD700"));
        
        bookButtonSB = new JButton("Book");
        
        
        //standardButtonSB.setSize(buttonSizes);
       /// deluxeButtonSB.setSize(buttonSizes);
       // executiveButtonSB.setSize(buttonSizes);
        
        roomTypePick = new ButtonGroup();
        
        roomTypePick.add(standardButtonSB);
        roomTypePick.add(deluxeButtonSB);
        roomTypePick.add(executiveButtonSB);
        
        
        //standardButtonSB.setBorder(BorderFactory.createRaisedBevelBorder());
       // deluxeButtonSB.setBorder(BorderFactory.createRaisedBevelBorder());
        //executiveButtonSB.setBorder(BorderFactory.createRaisedBevelBorder());
        
        JPanel roomTypePanel = new JPanel();
        
        roomTypePanel.setLayout(new GridLayout(1, 3));
        roomTypePanel.add(standardButtonSB);
        roomTypePanel.add(deluxeButtonSB);
        roomTypePanel.add(executiveButtonSB);
       
        
        panel.add(new JLabel("Hotel Name: "));//this should be a dropdown menu 
        panel.add(hotelNameFieldSB);
        panel.add(new JLabel("Your Name: "));
        panel.add(guestNameFieldSB);
        panel.add(new JLabel("Pick a Room Type"));
        panel.add(roomTypePanel);
        panel.add(new JLabel("Discount Coupon "));
        panel.add(discountFieldSB);
        panel.add(new JLabel(" "));
        panel.add(bookButtonSB);
        
        
        panel.setSize(400, 600);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 20, 50));
       
       this.add(panel);
       
       this.setSize(450, 600);
       this.setResizable(false); 
    }
 
    
    public void setActionListener(ActionListener listener){
        bookButtonSB.addActionListener(listener);
    }

}