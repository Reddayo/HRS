package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
/**
 * <p>
 * The </code>GUI_Main</code> class represents the main graphical user interface for the hotel reservation system.
 * It extends JFrame and provides a layout for various components including a tool bar, panels for
 * creating, viewing, managing, and simulating reservations, and a list for displaying hotels.
 * </p>
 * @author Jusper Angelo M. Cesar
 * @version 4.4
 * 
 */
public class GUI_Main extends JFrame {

    
	/**
	 * 
	 */
	private static final long serialVersionUID = -772240044098015686L;
	private GUI_toolBar toolBar;
    private GUI_Create createDialog;
    private GUI_View viewPanel;
    private GUI_Manage managePanel;
    private GUI_Simulate simulateDialog;
    private JList<String> hotelList; 
	private JScrollPane listScrollPane;
    private CardLayout viewmanagetitleshower;
    private JPanel kronii;
    
    /**
     * Constructs a new GUI_Main frame with the specified title.
     *
     * @param name The title of the frame.
     */
    public GUI_Main(String name){


        super(name);
        kronii = new JPanel();
        viewmanagetitleshower = new CardLayout();
        kronii.setLayout(viewmanagetitleshower);
        toolBar = new GUI_toolBar();
        createDialog = new GUI_Create(this);
		setLayout(new BorderLayout());
        viewPanel = new GUI_View();
        simulateDialog = new GUI_Simulate(this);
        managePanel = new GUI_Manage(this);
        
		//submitButton.setActionCommand("CSubmit");
		
		setSize(new Dimension(1080, 800));
		setMinimumSize(new Dimension(1080, 800));
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
        hotelListInit();
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        topPanel.add(toolBar, BorderLayout.NORTH);
        topPanel.add(managePanel, BorderLayout.SOUTH);
        toolBar.getSimulateButton().setEnabled(false);
        managePanel.setVisible(false);
        
        this.add(topPanel, BorderLayout.NORTH);

        kronii.add(initTitle(), "Title");
        kronii.add(viewPanel, "View Hotel");
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,  listScrollPane, kronii);
        kronii.setPreferredSize(new Dimension(getWidth()/2+100, getHeight()));
       
        kronii.setMinimumSize(new Dimension(getWidth()/2, getHeight()));
        listScrollPane.setBorder(null);
        splitPane.setResizeWeight(0.7);
        this.add(splitPane, BorderLayout.CENTER);
        splitPane.setUI(new BasicSplitPaneUI() 
        {
            @Override
            public BasicSplitPaneDivider createDefaultDivider() 
            {
                return new BasicSplitPaneDivider(this) 
                {                
                    /**
					 * 
					 */
					private static final long serialVersionUID = 6804859109026597907L;

					public void setBorder(Border b) {}

                    @Override
                    public void paint(Graphics g) 
                    {
                        g.setColor(new Color(221, 221, 221));
                        g.fillRect(0, 0, getSize().width, getSize().height+20);
                        super.paint(g);
                    }
                };
            }
        });
        
        
       
        hotelList.setFont(new Font("Tahoma", Font.PLAIN, 14));
        revalidate();
        repaint();
        
    }
    
    /**
     * Sets the selected hotel in the hotel list based on the provided name.
     *
     * @param prevSelected The name of the hotel to select, or null to clear the selection.
     */
    public void setSelectedHotel(String prevSelected) {
    	if(prevSelected != null) {
    		hotelList.setSelectedValue(prevSelected, true);
    	}else {
    		hotelList.setSelectedIndex(-1);
    	}
    }
    
    /**
     * Initializes the title panel with the application title and developer information.
     *
     * @return A JPanel containing the title information.
     */
    private JPanel initTitle(){

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        
        // Set the alignment of the titlePanel to the center
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel titleLabel = new JLabel("Hotel Reservation System++");
        titleLabel.setFont(new Font("Consolas", Font.BOLD, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel devName = new JLabel("Made by: Jusper Angelo Cesar");
        devName.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel E2 = new JLabel("As a requirement for CCPROG3 MCO2");
        E2.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel mistakeName = new JLabel("Finished as of June 28th. 2024. v4.4");
        mistakeName.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        titlePanel.setPreferredSize(new Dimension(getWidth() / 3, getHeight()));
        titlePanel.add(new JLabel(" "));
        
        titlePanel.add(titleLabel);
        titlePanel.add(devName);
        titlePanel.add(E2);
        titlePanel.add(mistakeName);
        return titlePanel;
    }
    
    /**
     * Displays the title view in the main panel.
     */
    public void showTitle(){
        viewmanagetitleshower.show(kronii, "Title");
    }
    
    /**
     * Toggles the visibility of the manage panel.
     */
    public void showManagePanel(){
        managePanel.setVisible(!managePanel.isVisible());
        revalidate();
        repaint();
    }
    
    /**
     * Gets the view panel for displaying hotel details.
     *
     * @return The GUI_View instance.
     */
    public GUI_View getViewPanel(){
        return viewPanel;
    }

    
    /**
     * Initializes the hotel list with a default list model.
     */
    public void hotelListInit() {
        hotelList = new JList<>();
		listScrollPane = new JScrollPane(hotelList);
		hotelList.setModel(new DefaultListModel<String>());
		hotelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
		hotelList.setVisible(true);
		//hotelList.setEnabled(false);
		int lastIndex = -1;
		hotelList.setSelectedIndex(lastIndex);
		hotelList.ensureIndexIsVisible(lastIndex);
		hotelList.setEnabled(true);
		listScrollPane.setViewportView(hotelList);
	}

    
    /**
     * Updates the hotel list with the provided list model.
     *
     * @param listModel The list model containing the hotel data.
     */
    public void updateHotelList(DefaultListModel<String> listModel) {
	    hotelList.setModel(listModel); 
	    listScrollPane.setViewportView(hotelList); 
	}
    
    
    /**
     * Updates the room list in the view panel with the provided list model.
     *
     * @param listModel The list model containing the room data.
     */
    public void updateRoomList(DefaultListModel<String> listModel) {
	    viewPanel.updateRoomList(listModel);
	}
    
    /**
     * Updates the reservation list in the view panel with the provided list model.
     *
     * @param listModel The list model containing the reservation data.
     */
    public void updateReservationList(DefaultListModel<String> listModel) {
        viewPanel.updateReservationList(listModel);
    }
    
    /**
     * Sets a list selection listener for the hotel list.
     *
     * @param listener The ListSelectionListener to be added.
     */
    public void setListSelectionListener(ListSelectionListener listener){
        hotelList.addListSelectionListener(listener);
        hotelList.addListSelectionListener(getManagePanel().getLSL());
       
    }

    
    /**
     * Gets the create dialog for creating new hotels or reservations.
     *
     * @return The GUI_Create instance.
     */
    public GUI_Create getCreateDialog(){
        return createDialog;
    }

    
    /**
     * Sets an action listener for various components like buttons.
     *
     * @param listener The ActionListener to be added.
     */
    public void setActionListener(ActionListener listener){
        createDialog.addCreateButtonListener(listener);
        toolBar.setActionListener(listener);
        viewPanel.setActionListener(listener);
        simulateDialog.setActionListener(listener);
        managePanel.setActionListener(listener);
        
    }
    
    
    /**
     * Gets the simulate dialog for simulating bookings.
     *
     * @return The GUI_Simulate instance.
     */
    public GUI_Simulate getBooking() {
    	return simulateDialog;
    }

    
    /**
     * Sets a change listener for components that support state changes.
     *
     * @param listener The ChangeListener to be added.
     */
    public void setChangeListener(ChangeListener listener){
  
        viewPanel.setChangeListener(listener);
    }
    
    /**
     * Shows a popup with the given exception message.
     *
     * @param e The exception to display.
     */
    public void showPopup(Exception e){
        JOptionPane.showMessageDialog(createDialog, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    
    /**
     * Sets parameters for the create dialog.
     *
     * @param max_rooms The maximum number of rooms.
     * @param default_price The default price for rooms.
     * @param min_price The minimum price for rooms.
     */
    public void setCreateDialog(int max_rooms, double default_price, double min_price){
        createDialog.updateParameters(max_rooms, default_price, min_price);
    }

    
    /**
     * Shows the create dialog.
     */
    public void showCreateDialog() {
        createDialog.reset();
        createDialog.setVisible(true);
    }

    
    /**
     * Displays the view panel and updates its contents.
     */
    public void showViewPanel() {
    	if(!viewPanel.isVisible()) {
    		viewmanagetitleshower.show(kronii, "View Hotel");
    		viewPanel.shownothing();
    		viewPanel.resetInfo();
    		viewPanel.setVisibleList(true);
    		viewPanel.clearButtonSelection();
    	}else {
    		viewmanagetitleshower.show(kronii, "Title");
    		viewPanel.setVisibleList(false);
    	}
    		
        
    }

    
    /**
     * Gets the name of the selected hotel from the hotel list.
     *
     * @return The selected hotel's name, or null if none is selected.
     */
    public String getSelectedHotel() {
        return  hotelList.getSelectedValue();
    }

    
    /**
     * Shows the simulate booking dialog.
     */
    public void showSimulateBooking(){
    
    	simulateDialog.disposeDialog();
        simulateDialog.setVisible(true);
    }

    
    /**
     * Checks if room information is selected in the view panel.
     *
     * @return true if room information is selected, false otherwise.
     */
    public boolean isRoomInformationSelected() {
       
        return viewPanel.isRoomInformationSelected();
    }

    
    /**
     * Checks if reservation information is selected in the view panel.
     *
     * @return true if reservation information is selected, false otherwise.
     */
    public boolean isReservationInformationSelected() {
       
        return viewPanel.isReservationInformationSelected();
    }

    
    /**
     * Gets the manage panel for managing hotels and reservations.
     *
     * @return The GUI_Manage instance.
     */
    public GUI_Manage getManagePanel() {
        return managePanel;
    }

    
    /**
     * Shows a confirmation dialog with the given message.
     *
     * @param string The message to display.
     * @return The option selected by the user (JOptionPane.YES_OPTION or JOptionPane.NO_OPTION).
     */
    public int showConfirmationDialog(String string) {
        return JOptionPane.showConfirmDialog(null, string, string, JOptionPane.YES_NO_OPTION);
    }

	
    /**
     * Enables or disables the simulate button in the toolbar.
     *
     * @param b true to enable the button, false to disable it.
     */
    public void setSimulateEnabled(boolean b) {
		toolBar.getSimulateButton().setEnabled(b);;
		
	}

}
