package View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI_Manage extends JPanel {

    
	/**
	 * 
	 */
	private static final long serialVersionUID = -2466504295136985156L;
	private ArrayList<PlainButton> buttons;
    private JFrame parentFrame;
    private PlainToolBar toolBar;
    private JButton confirmation;
    private JDialog dialog;
    private ListSelectionListener lsl; //more for just managing the names and stuff
    private JTextField newHotelNameField;
    private JTextField newBasePriceField;
    private JTextField newPriceModifierField;
    private JLabel newBasePrice;
    private String selected;
    private JList<String> reservationList;
    private JList<String> roomList;
    private JList<String> priceModsList;
    

    public GUI_Manage(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        confirmation = new JButton();
        initializeToolBar();
        initializeButtons();
        setLayout(new BorderLayout());
        
        add(toolBar, BorderLayout.NORTH);
        
        reservationList = new JList<String>();
    	roomList = new JList<String>();
    	priceModsList = new JList<String>();
        lsl = new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList<?> source = (JList<?>) e.getSource();
                
                if (!source.isSelectionEmpty()) {
                    enableButtons();
                    selected = (String) source.getSelectedValue();
                }else{
                    disableButtons();
                }
            }
            
        };
        
        
    
        
    }
    public void setListSelectionListener(ListSelectionListener listener) {
    	reservationList.addListSelectionListener(listener);
    	roomList.addListSelectionListener(listener);
    	priceModsList.addListSelectionListener(listener);
    
    }
    
    public void updateReservationList(DefaultListModel<String> listModel) {
    	
    	reservationList.setModel(listModel);
    	
    }
    
    public void updateRoomList(DefaultListModel<String> listModel) {
    	
	 	roomList.setModel(listModel);
    	
    }
    
    public void updateDatePriceModifierList(DefaultListModel<String> listModel) {
    	
	 	priceModsList.setModel(listModel);
    	
    }
    
    
    public String getSelectedReservationID() {
    	return reservationList.getSelectedValue();
    }
    
    public ArrayList<String> getSelectedRooms() {
        // Retrieve the selected values from the JList
        java.util.List<String> selectedValuesList = roomList.getSelectedValuesList();
        
        // Convert the list to an ArrayList and return
        return new ArrayList<>(selectedValuesList);
    }
    
    
    public ListSelectionListener getLSL(){
        return lsl;
    }

    public void setActionListener(ActionListener listener){
        confirmation.addActionListener(listener);
        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).addActionListener(listener);
           // System.out.println(buttons.get(i).getText());
        } 
        disableButtons();
        revalidate();
        repaint();
    }

    private void disableButtons(){
        
           
            for(int i = 0; i < buttons.size(); i++){
                buttons.get(i).setEnabled(false);
            }
           
            revalidate();
            repaint();
    }

    private void enableButtons(){
        
           
        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).setEnabled(true);
        }
        revalidate();
        repaint();
}

    private void initializeToolBar() {
        toolBar = new PlainToolBar();
        toolBar.setFloatable(false); // Makes sure the toolbar cannot be moved
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT)); // Set the layout to FlowLayout aligned to the left
    }

    private void initializeButtons() {
        buttons = new ArrayList<PlainButton>();
        
        
        String[] buttonLabels = {
            "Change Hotel Name", "Add Room", "Remove Room", 
            "Update Base Price", "Remove Reservation", "Remove Hotel", 
            "Modify Date Price"//, "Duplicate Hotel"
        };
        int i = 0;
        for (String buttonLabel : buttonLabels) {
            PlainButton button = new PlainButton(buttonLabel);
            buttons.add(button); // Add to button array
            toolBar.add(button);
            //System.out.println(buttons.get(i++).getText());
        }

        
    }

    public void openDialog(String dialogType) {
        JPanel panel = createPanel(dialogType);

        if (panel != null) {
            dialog = new JDialog(parentFrame, dialogType, true);
            confirmation.setActionCommand("Confirm " + dialogType);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setContentPane(panel);
            dialog.setResizable(false);
            dialog.pack();
            dialog.setLocationRelativeTo(parentFrame);
            dialog.setVisible(true);
            
        }else{
        	System.out.println("The panel is nulllllllll!");
        }
        

    }

    public void disposeManageDialog(){
        if(dialog!= null){  
            dialog.dispose();
        }
       
    }
    
    

    private JPanel createPanel(String dialogType) {
        switch (dialogType) {
            case "Change Hotel Name"://done
                return createChangeHotelNamePanel();
            case "Add Room"://tom
                return createAddRoomPanel();
            case "Remove Room"://done //we're just gonna show removable rooms
                return createRemoveRoomPanel();
            case "Update Base Price"://done
                return createUpdateBasePricePanel();
            case "Remove Reservation"://done
                return createRemoveReservationPanel();
            case "Remove Hotel"://done
                return createRemoveHotelPanel();
            case "Modify Date Price"://done
                return createModifyDatePricePanel();
            default:
                return null;
        }
    }

    private JPanel createChangeHotelNamePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel panel1 = new JPanel(new GridLayout(0, 1));
        JPanel panel2 = new JPanel(new GridLayout(1, 3));
        
        newHotelNameField = new JTextField(20);
        JLabel newHotelName = new JLabel("New Hotel Name:  " );
        confirmation.setActionCommand("Confirm");
        confirmation.setText("Change");
        
        panel1.add(new JLabel("Old Hotel Name: " + selected));
        panel1.add(newHotelName);
        
        panel1.add(newHotelNameField);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel2.add(new JLabel(" "));
        panel2.add(confirmation);
        panel2.add(new JLabel(" "));
        panel.add(panel1, BorderLayout.CENTER);
        panel.add(panel2, BorderLayout.SOUTH);
       
        return panel;
    }

    public String getNewHotelName(){
        return newHotelNameField.getText();
    }

    public String getNewPrice(){
        return newBasePriceField.getText();
    }
    
    public String getBasePriceField(){
        return newBasePriceField.getText();
    }
    private JPanel createAddRoomPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField roomAdd = new JTextField();
        JLabel addableRooms = new JLabel("Addable Rooms:");
        panel.add(addableRooms);
        panel.add(roomAdd);
        return panel;
    }

    private JPanel createRemoveRoomPanel() {
    
        
        JScrollPane scrollPane = new JScrollPane(roomList);
    	JPanel panel1 = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(0, 1));
        
        JLabel removableRooms = new JLabel("Select Room/s to Remove");
        roomList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        panel1.add(removableRooms, BorderLayout.NORTH);
        panel.add(scrollPane);
        
        confirmation.setText("Remove");
        //panel.add(confirmation);
        
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 20, 30, 20));
        panel1.add(panel, BorderLayout.CENTER);
        panel1.add(confirmation, BorderLayout.SOUTH);
        return panel1;
    }
    
   
    private JPanel createUpdateBasePricePanel() {
 
        JPanel panel = new JPanel(new BorderLayout());

        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panel2 = new JPanel(new GridLayout(1, 3));

        newBasePriceField = new JTextField(20);
        newBasePrice= new JLabel("New Base Price:  " );
        confirmation.setActionCommand("Confirm");
        confirmation.setText("Update");

        panel1.add(newBasePrice);
        
        panel1.add(newBasePriceField);
        panel2.add(new JLabel(" "));
        panel2.add(confirmation);
        panel2.add(new JLabel(" "));
        panel.add(panel1, BorderLayout.CENTER);
        panel.add(panel2, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createRemoveReservationPanel() {
    	
    	JScrollPane scrollPane = new JScrollPane(reservationList);
    	JPanel panel1 = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(0, 1));
        
        JLabel removingRes = new JLabel("Select a Reservation to Remove");

        panel1.add(removingRes, BorderLayout.NORTH);
        panel.add(scrollPane);
        
        confirmation.setText("Remove");
        //panel.add(confirmation);
        
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 20, 30, 20));
        panel1.add(panel, BorderLayout.CENTER);
        panel1.add(confirmation, BorderLayout.SOUTH);
        return panel1;
    }

    private JPanel createRemoveHotelPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
      //  JTextField hotelRemove = new JTextField();
        JLabel removingHotel = new JLabel("Removing Hotel.");
       // confirmation.setActionCommand("Confirm");
        confirmation.setText("Confirm");
        panel.add(removingHotel);
        panel.add(new JLabel(" "));
        panel.add(confirmation);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 30, 20));
        //panel.add(hotelRemove);
        return panel;
    }
    
    public int[] getSelectedDateIndices(){
    	return priceModsList.getSelectedIndices();
    	
    }
    
    public String getPriceModifier() {
    	return newPriceModifierField.getText();
    }

    private JPanel createModifyDatePricePanel() {
    	//no i wont size them, ill let them handle their size
            JScrollPane scrollPane = new JScrollPane(priceModsList);
        	JPanel panel1 = new JPanel(new BorderLayout());
        	JPanel panel2 = new JPanel(new BorderLayout());
        	JPanel panel3 = new JPanel(new BorderLayout());
            JPanel panel = new JPanel(new GridLayout(0, 1));
            
            JLabel modifiableDates = new JLabel("Select Dates to Modify: ");
            priceModsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            newPriceModifierField = new JTextField(20);
            panel1.add(modifiableDates, BorderLayout.NORTH);
            panel.add(scrollPane);
            panel3.add(new JLabel("Enter number: "), BorderLayout.NORTH);
            
            panel2.add(newPriceModifierField, BorderLayout.NORTH);
            confirmation.setText("Modify");
            //panel.add(confirmation);
            
            panel1.setBorder(BorderFactory.createEmptyBorder(10, 20, 30, 20));
            panel1.add(panel, BorderLayout.CENTER);
            panel2.add(confirmation, BorderLayout.SOUTH);
            panel3.add(panel2, BorderLayout.SOUTH);
            
            panel1.add(panel3, BorderLayout.SOUTH);
            return panel1;
     
    }
}
