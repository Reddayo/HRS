package View;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.FocusListener;

public class HRS_GUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7413264850018485788L;
	private Font buttonFonts = new Font("Helvetica", Font.BOLD, 12);
	private Dimension buttonSizes = new Dimension(120, 30);
	//private JPanel panels;
	private JPanel cards;
	private CardLayout cardShower = new CardLayout();
	
	private JList<String> list = new JList<>();
	
	
	//TOP TOOL BAR
	private JButton createButton = new JButton("Create Hotel");
	private JButton viewButton = new JButton("View Hotel");
	private JButton manageButton = new JButton("Manage Hotel");;
	
	
	
	private JScrollPane listScrollPane;
	
	//Card 1
	//CREATE HOTEL
	private JTextField nameField = new JTextField(20);
    private JTextField standardField = new JTextField(10);
    private JTextField deluxeField = new JTextField(10);
    private JTextField executiveField = new JTextField(10);
    private JCheckBox defaultPriceCheckBox = new JCheckBox("Default Price (1299)");
    private JTextField priceField = new JTextField(10);
    private JLabel priceLabel = new JLabel("Price (must be > 100):");
    private JButton submitButton = new JButton("Submit");
    
    //Card 2
    //VIEW HOTEL
    private JLabel infoLabel;
    
    
    
    
    
    
    
    //Card 3
    //Manage Hotel
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //CARD 4
    //Simulate Booking
    
    
    
    

	
	public HRS_GUI(String name){
		super(name);
		
		setLayout(new BorderLayout());
		/*
		ImageIcon LMAO = new ImageIcon("C:\\Users\\Admin\\Documents\\RedsMCO2CCPROG3\\ExtendedHRS\\src\\View\\a.png");
		setIconImage(LMAO.getImage());
		*/
		/*
		ImageIcon LMAO = new ImageIcon("C:\\Users\\Admin\\Documents\\RedsMCO2CCPROG3\\ExtendedHRS\\src\\View\\a.png");
		createButton.setContentAreaFilled(false);
		createButton.setIcon(LMAO);
		*/
	   // new JPanel(new BorderLayout());
		submitButton.setActionCommand("CSubmit");
		setSize(800, 600);//setMaximumSize(new Dimension(1000, 800));
		setMinimumSize(new Dimension(800, 600));
		
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		init();
		//showCreateMenu();
		
		
	}
	
	
	
	public void setModel(DefaultListModel<String> listModel) {
		// TODO Auto-generated method stub
		listScrollPane = new JScrollPane(list);
		list.setModel(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//list.setCellRenderer();
		list.setVisible(true);
		list.setEnabled(true);
		int lastIndex = listModel.getSize() - 1;
		list.setSelectedIndex(lastIndex);
		list.ensureIndexIsVisible(lastIndex);
		listScrollPane.setViewportView(list);
		this.add(listScrollPane, BorderLayout.CENTER);
	}
	
	public void updateList(DefaultListModel<String> listModel) {
	    list.setModel(listModel); 
	    listScrollPane.setViewportView(list); 
	}
	
	private void init() {
		
		//North Tool Bar
		JPanel topToolBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		createButton.setPreferredSize(buttonSizes);
		viewButton.setPreferredSize(buttonSizes);
		manageButton.setPreferredSize(buttonSizes);
		
		createButton.setFont(buttonFonts);
		viewButton.setFont(buttonFonts);
		manageButton.setFont(buttonFonts);
		
		createButton.setFocusPainted(false);
		viewButton.setFocusPainted(false);
		manageButton.setFocusPainted(false);
		
		createButton.setBorderPainted(false);
		viewButton.setBorderPainted(false);
		manageButton.setBorderPainted(false);
		
		
		
		createButton.setBackground(Color.decode("0x353535"));
		createButton.setForeground(Color.decode("0xFFFFFF"));
		
		viewButton.setBackground(Color.decode("0x353535"));
		viewButton.setForeground(Color.decode("0xFFFFFF"));
		
		manageButton.setBackground(Color.decode("0x353535"));
		manageButton.setForeground(Color.decode("0xFFFFFF"));
		
		topToolBar.setBackground(Color.decode("0xD9D9D9"));
	    
	    
		topToolBar.add(createButton);
		topToolBar.add(viewButton);
		topToolBar.add(manageButton);
		
		
		
		
		this.add(topToolBar, BorderLayout.NORTH);
		
		
		
		
		
		cards = new JPanel(cardShower);
		JPanel titlePanel = new JPanel(new FlowLayout());
		JLabel titleLabel = new JLabel("Hotel Reservation System++");
		
		JLabel devName = new JLabel("Made by: Jusper Angelo Cesar");
		JLabel mistakeName = new JLabel("This was a mistake!");
		
		
		titlePanel.setPreferredSize(new Dimension(getWidth()/3, getHeight()));
		titlePanel.add(titleLabel);
		titlePanel.add(devName);
		//titlePanel.add(new JLabel("                                                          \n "));
		titlePanel.add(mistakeName);
		
		cards.add(titlePanel, "Title");
		JPanel createPanel = new JPanel(new GridLayout(0, 2));
		
		createPanel.setPreferredSize(new Dimension(getWidth()/3, getHeight()));
		createPanel.add(new JLabel("Name:"));
        createPanel.add(nameField);
        createPanel.add(new JLabel("Standard Rooms:"));
        createPanel.add(standardField);
        createPanel.add(new JLabel("Deluxe Rooms:"));
        createPanel.add(deluxeField);
        createPanel.add(new JLabel("Executive Rooms:"));
        createPanel.add(executiveField);
        createPanel.add(defaultPriceCheckBox);
        createPanel.add(new JLabel()); // Empty label for alignment
        createPanel.add(priceLabel);
        createPanel.add(priceField);
       //createPanel.add(new JLabel()); 
        createPanel.add(submitButton);

       
        createPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, getHeight()/3, 0));
        cards.add(createPanel, "Create Hotel");
		
		JPanel viewPanel = new JPanel(new FlowLayout());
		//viewPanel.add(new JLabel("This is view!"));
		
		infoLabel = new JLabel("Select a hotel to view");
		viewPanel.add(infoLabel);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		JPanel managePanel = new JPanel(new FlowLayout());
		managePanel.add(new JLabel("This is manage!"));
		
		//cards.add(createPanel, "Create Hotel");
		cards.add(viewPanel, "View Hotel");
		cards.add(managePanel, "Manage Hotel");
		//cards.setLayout(cardShower);
		cards.setPreferredSize(new Dimension(getWidth()/3, getHeight()/2));
		this.add(cards, BorderLayout.EAST);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
        //JPanel centerPanel = new JPanel(new BorderLayout());
		setModel(new DefaultListModel<String>());
		
		//panels.add(cards, BorderLayout.SOUTH);
		//this.add(listScrollPane, BorderLayout.CENTER);
		this.pack();
		//this.add(panels, BorderLayout.NORTH);
		
	
		//the list of hotels
		
		
		
		
	}
	
	public void updateCardPanelSize() {
        int width = getWidth() / 3;
        int height = getHeight()/3;
        cards.setPreferredSize(new Dimension(width, height));
        cards.revalidate();
        cards.repaint();
    }
	
	public void setActionListener(ActionListener listener) {
		createButton.addActionListener(listener);
		viewButton.addActionListener(listener);
		manageButton.addActionListener(listener);
		submitButton.addActionListener(listener);
		defaultPriceCheckBox.addActionListener(listener);
	}
	
	public void setComponentListener(ComponentListener e) {
		addComponentListener(e);
	}
	
	public void setDocumentListener(DocumentListener listener) {
		
		nameField.getDocument().addDocumentListener(listener);
		standardField.getDocument().addDocumentListener(listener);
		deluxeField.getDocument().addDocumentListener(listener);
		executiveField.getDocument().addDocumentListener(listener);
		priceField.getDocument().addDocumentListener(listener);
		
	}
	
	public void setFocusListener(FocusListener listener) {
		nameField.addFocusListener(listener);
	}
	
	public void setListListener(ListSelectionListener listener) {
		list.addListSelectionListener(listener);
	}
	
	
	
	public void removeNameFieldText() {
		
        nameField.setText("");
       
	}
	
	public void addGhostNameField() {
		nameField.setText("Enter Text");
	}
	
	
	
	
	public void showPopup(String title, String message) {
		
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
	public void showCreateMenu() {
		
			list.setEnabled(false);
			list.clearSelection();
			cardShower.show(cards, "Create Hotel");
			
			
			
		 	nameField.setText("Enter Text");
	        standardField.setText("0");
	        deluxeField.setText("0");
	        executiveField.setText("0");
	        defaultPriceCheckBox.setSelected(true);
	        priceField.setText("1299");
	        priceField.setVisible(false);
	        priceLabel.setVisible(false);
        //JOptionPane.showMessageDialog(this, "Create Hotel", "Hotel Creation", JOptionPane.INFORMATION_MESSAGE);
    }
	
	
	
	
	
	
	
	 public boolean isDefaultPriceCheckBoxSelected() {
		 return defaultPriceCheckBox.isSelected();
	 }

	public String getHotelName() {
		return nameField.getText();
	}
	public String getStandardRoomNum() {
		return standardField.getText();
	}
	public String getDeluxeRoomNum() {
		return deluxeField.getText();
	}
	public String getExecutiveRoomNum() {
		return executiveField.getText();
	}
	public String getPrice() {
		return priceField.getText();
	}

	

	public void showViewMenu() {
		// TODO Auto-generated method stub
		cardShower.show(cards, "View Hotel");
		list.setEnabled(true);
	}
	public void showManageMenu() {
		// TODO Auto-generated method stub
		cardShower.show(cards, "Manage Hotel");
	}
	public void toggleDefaultPrice() {
		boolean selected = defaultPriceCheckBox.isSelected();
		priceField.setVisible(!selected);
        priceLabel.setVisible(!selected);
        if (selected) {
            priceField.setText("1299");
        } else {
            priceField.setText("100");
        }
	}



	public void showHotelInfo() {
		// TODO Auto-generated method stub
		String selectedHotel = list.getSelectedValue();
        if (selectedHotel != null) {
            infoLabel.setText("Information about " + selectedHotel);
        }else {
        	infoLabel.setText("There is no hotel selected");
        }
	}
}
