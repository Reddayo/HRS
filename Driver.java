package shutupCompiler;

import Controller.*;
import Model.*;
import View.*;
import javax.swing.UIManager;
public class Driver {
	public static void main (String[] args) {
		
		/*
		 try {
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

		 */
		
		
		 HotelListManager HRS = new HotelListManager();
		 HRS_GUI gui = new HRS_GUI("HRS++");
		 HRS_Controller controller = new HRS_Controller(HRS, gui);
		
		//.args./.init();
		
	}
}
