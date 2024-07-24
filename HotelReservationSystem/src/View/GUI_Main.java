package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
        revalidate();
        repaint();
        
    }

    private JPanel initTitle(){

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        
        // Set the alignment of the titlePanel to the center
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel titleLabel = new JLabel("Hotel Reservation System++");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel devName = new JLabel("Made by: Jusper Angelo Cesar");
        devName.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel E2 = new JLabel("As a requirement for CCPROG3 MCO2");
        E2.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel mistakeName = new JLabel("This was a mistake!");
        mistakeName.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        titlePanel.setPreferredSize(new Dimension(getWidth() / 3, getHeight()));
        titlePanel.add(new JLabel(" "));
        
        titlePanel.add(titleLabel);
        titlePanel.add(devName);
        titlePanel.add(E2);
        titlePanel.add(mistakeName);
        return titlePanel;
    }

    public void showTitle(){
        viewmanagetitleshower.show(kronii, "Title");
    }

    public void showManagePanel(){
        managePanel.setVisible(!managePanel.isVisible());
        revalidate();
        repaint();
    }

    
    public GUI_View getViewPanel(){
        return viewPanel;
    }

      public void hotelListInit() {
        hotelList = new JList<>();
		listScrollPane = new JScrollPane(hotelList);
		hotelList.setModel(new DefaultListModel<String>());
		hotelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		/* 
		hotelList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                


                if (isSelected) {
                    // Set background color for selected items
                    component.setBackground(Color.GRAY);  // or any color you prefer
                    component.setForeground(Color.WHITE); // ensure text is readable
                } else {
                    // Set background color for non-selected items
                    component.setBackground(Color.BLACK);
                    component.setForeground(Color.WHITE); // ensure text is readable
                }
                // Set background color
                component.setBackground(new Color(60, 60, 60));
                
                // Set foreground color (text color) for better readability
                component.setForeground(Color.WHITE);
                
                // Optionally, set the font or other properties here
                 component.setFont(new Font("Arial", Font.PLAIN, 16));
                
                return component;
            }
        });
        hotelList.setBackground(new Color(60, 60, 60));
*/


        
		hotelList.setVisible(true);
		//hotelList.setEnabled(false);
		int lastIndex = -1;
		hotelList.setSelectedIndex(lastIndex);
		hotelList.ensureIndexIsVisible(lastIndex);
		hotelList.setEnabled(true);
		listScrollPane.setViewportView(hotelList);
	}

    public void updateHotelList(DefaultListModel<String> listModel) {
	    hotelList.setModel(listModel); 
	    listScrollPane.setViewportView(hotelList); 
	}
    
    public void updateRoomList(DefaultListModel<String> listModel) {
	    viewPanel.updateRoomList(listModel);
	}
    public void updateReservationList(DefaultListModel<String> listModel) {
        viewPanel.updateReservationList(listModel);
    }
    public void setListSelectionListener(ListSelectionListener listener){
        hotelList.addListSelectionListener(listener);
        hotelList.addListSelectionListener(getManagePanel().getLSL());
       
    }




    public GUI_Create getCreateDialog(){
        return createDialog;
    }

    public void setActionListener(ActionListener listener){
        createDialog.addCreateButtonListener(listener);
        toolBar.setActionListener(listener);
        viewPanel.setActionListener(listener);
        simulateDialog.setActionListener(listener);
        managePanel.setActionListener(listener);
        
    }
    
    public GUI_Simulate getBooking() {
    	return simulateDialog;
    }

    public void setChangeListener(ChangeListener listener){
  
        viewPanel.setChangeListener(listener);
    }
    public void showPopup(Exception e){
        JOptionPane.showMessageDialog(createDialog, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public void setCreateDialog(int max_rooms, double default_price, double min_price){
        createDialog.updateParameters(max_rooms, default_price, min_price);
    }


    public void showCreateDialog() {
        createDialog.reset();
        createDialog.setVisible(true);
    }



    public void showViewPanel() {
    	if(!viewPanel.isVisible()) {
    		viewmanagetitleshower.show(kronii, "View Hotel");
    		viewPanel.shownothing();
    		viewPanel.resetInfo();
    	}else {
    		viewmanagetitleshower.show(kronii, "Title");
    	}
    		
        
    }



    public String getSelectedHotel() {
        return  hotelList.getSelectedValue();
    }



    public void showSimulateBooking(){
    	simulateDialog.disposeDialog();
        simulateDialog.setVisible(true);
    }

    public boolean isRoomInformationSelected() {
       
        return viewPanel.isRoomInformationSelected();
    }

    public boolean isReservationInformationSelected() {
       
        return viewPanel.isReservationInformationSelected();
    }



    public GUI_Manage getManagePanel() {
        return managePanel;
    }

    public int showConfirmationDialog(String string) {
        return JOptionPane.showConfirmDialog(null, string, string, JOptionPane.YES_NO_OPTION);
    }

	public void setSimulateEnabled(boolean b) {
		toolBar.getSimulateButton().setEnabled(b);;
		
	}

}
