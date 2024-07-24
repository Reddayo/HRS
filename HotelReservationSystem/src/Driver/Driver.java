package Driver;

import javax.swing.SwingUtilities;

import Controller.*;
import Model.*;
import View.*;

public class Driver {
    public static void main (String[] args){
        //HRS_GUI hrs = new HRS_GUI("Hotel Reservation++");
    	
    	//UIDefaults uiDefaults = UIManager.getDefaults();
    	//uiDefaults.put("activeCaption", new javax.swing.plaf.ColorUIResource(Color.gray));
    	//uiDefaults.put("activeCaptionText", new javax.swing.plaf.ColorUIResource(Color.white));
    	//JFrame.setDefaultLookAndFeelDecorated(true);
    	
    
    	SwingUtilities.invokeLater(() -> {
            // Initialize GUI components and model
            GUI_Main hrs_view = new GUI_Main("Hotel Reservation System++");
            HotelListManager hrs_model = new HotelListManager();
            @SuppressWarnings("unused")
			Controller cont = new Controller(hrs_model, hrs_view);
    	});
    }
}
