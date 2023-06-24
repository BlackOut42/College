// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package Graphic;

import static Graphic.VehicleAgency.agencyCreated;
import static Graphic.VehicleAgency.updateCenterPanel;

import System.DB;
import VehicleTypes.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import DP.Factories.AirVehicleConcreteFactory;
import DP.Factories.HybridVehicleConcreteFactory;
import DP.Factories.LandVehicleConcreteFactory;
import DP.Factories.SeaVehicleConcreteFactory;

public class VehicleCreator extends JFrame implements ActionListener {

	VehicleCreator instance = null;
	String[] defaulVehicleArr = new String[] { "f1.jpg", "f2.jpg", "f3.jpg" };
	File defaultVehicleCurrentDirFile = new File("src/Graphic/icons/frigate icons");

	String iconMainPath = "src/Graphic/icons/";
	String[] vehicleImgArr = null;
	String CurrentImageDir;
	File VehicleCurrentDirFile = null;
	DB database;
	
	LandVehicleConcreteFactory landVehicleFactory = new LandVehicleConcreteFactory();
	AirVehicleConcreteFactory airVehicleFactory = new AirVehicleConcreteFactory();
	SeaVehicleConcreteFactory seaVehicleFactory = new SeaVehicleConcreteFactory();
	HybridVehicleConcreteFactory hybridVehicleFactory = new HybridVehicleConcreteFactory();

	// -----LEFT PANEL-----//
	JPanel buttonsJPanel;
	JLabel leftText;
	JRadioButton jeepRadioButton;
	JRadioButton bicycleRadioButton;
	JRadioButton toyDroneRadioButton;
	JRadioButton spyDroneRadioButton;
	JRadioButton frigateRadioButton;
	JRadioButton cruiseShipRadioButton;
	JRadioButton amphibiousRadioButton;
	JRadioButton electricBicycleRadioButton;
	JRadioButton hybridRadioButton;
	public ButtonGroup radioGroup;
	// -----LEFT PANEL----

	// -----RIGHT PANEL-----
	JPanel customizationPanel;
	JLabel rightText;
	// Parameters
	JTextField modelField;
	JTextField maxPassengersField;
	JTextField maxSpeedField;
	JTextField numOfWheelsField;
	JTextField averageFuelConsumptionField;
	JTextField averageLifetimeField;
	JTextField PowerSourceField;

	JComboBox vehicleBox;
	JComboBox flagBox;
	JComboBox roadTypeBox;
	JComboBox energyScoreBox;

	JCheckBox windDirectionButton;
	JCheckBox millitaryUseButton;
	// Labels
	JLabel modelLabel;
	JLabel maxSpeedLabel;
	JLabel maxPassengerLabel;
	JLabel numOfWheelsLabel;
	JLabel averageFuelConsumptionLabel;
	JLabel averageLifetimeLabel;
	JLabel powerSourceLabel;
	JLabel vehicleLabel;
	JLabel flagLabel;
	JLabel roadtypeLabel;
	JLabel energyScoreLabel;
	JLabel windDirectionLabel;
	JLabel millitaryUseLabel;

	// Panels

	JPanel modelPanel;
	JPanel maxSpeedPanel;
	JPanel maxPassengerPanel;
	JPanel numOfWheelsPanel;
	JPanel averageFuelConsumptionPanel;
	JPanel averageLifetimePanel;
	JPanel powerSourcePanel;
	JPanel vehiclePanel;
	JPanel flagPanel;
	JPanel roadtypePanel;
	JPanel energyScorePanel;
	JPanel windDirectionPanel;
	JPanel millitaryUsePanel;

	MyButtons submitButton;
	JButton uploadButton;
	DefaultComboBoxModel vehicleDefmodel;// vehicle design combobox model.
	// -----RIGHT PANEL-----

	public VehicleCreator() {
		super("Vehicle Creator");
		database = new DB(); // database initialization

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout(0, 0));
		this.setSize(800, 800);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		// -----------------LEFT PANEL-----------------
		leftText = new JLabel("Choose One:");
		leftText.setFont(new Font("Helvetica", Font.BOLD, 24));
		leftText.setForeground(new Color(0xFF5D5D));

		jeepRadioButton = new JRadioButton("Jeep");
		jeepRadioButton.setBackground(new Color(0xE1FFB1));
		jeepRadioButton.setForeground(new Color(0xFF5D5D));
		jeepRadioButton.setFocusable(false);

		bicycleRadioButton = new JRadioButton("Bicycle");
		bicycleRadioButton.setBackground(new Color(0xE1FFB1));
		bicycleRadioButton.setForeground(new Color(0xFF5D5D));
		bicycleRadioButton.setFocusable(false);

		toyDroneRadioButton = new JRadioButton("Toy Drone");
		toyDroneRadioButton.setBackground(new Color(0xE1FFB1));
		toyDroneRadioButton.setForeground(new Color(0xFF5D5D));
		toyDroneRadioButton.setFocusable(false);

		spyDroneRadioButton = new JRadioButton("Spy Drone");
		spyDroneRadioButton.setBackground(new Color(0xE1FFB1));
		spyDroneRadioButton.setForeground(new Color(0xFF5D5D));
		spyDroneRadioButton.setFocusable(false);

		frigateRadioButton = new JRadioButton("Frigate");
		frigateRadioButton.setBackground(new Color(0xE1FFB1));
		frigateRadioButton.setForeground(new Color(0xFF5D5D));
		frigateRadioButton.setFocusable(false);

		cruiseShipRadioButton = new JRadioButton("Cruise Ship");
		cruiseShipRadioButton.setBackground(new Color(0xE1FFB1));
		cruiseShipRadioButton.setForeground(new Color(0xFF5D5D));
		cruiseShipRadioButton.setFocusable(false);

		amphibiousRadioButton = new JRadioButton("Amphibious");
		amphibiousRadioButton.setBackground(new Color(0xE1FFB1));
		amphibiousRadioButton.setForeground(new Color(0xFF5D5D));
		amphibiousRadioButton.setFocusable(false);

		hybridRadioButton = new JRadioButton("Hybrid");
		hybridRadioButton.setBackground(new Color(0xE1FFB1));
		hybridRadioButton.setForeground(new Color(0xFF5D5D));
		hybridRadioButton.setFocusable(false);

		electricBicycleRadioButton = new JRadioButton("Electric Bicycle");
		electricBicycleRadioButton.setBackground(new Color(0xE1FFB1));
		electricBicycleRadioButton.setForeground(new Color(0xFF5D5D));
		electricBicycleRadioButton.setFocusable(false);

		uploadButton = new JButton("Upload your design!");
		uploadButton.setPreferredSize(new Dimension(80, 50));
		uploadButton.setFocusable(false);
		uploadButton.addActionListener(this);

		radioGroup = new ButtonGroup();

		radioGroup.add(jeepRadioButton);
		radioGroup.add(bicycleRadioButton);
		radioGroup.add(toyDroneRadioButton);
		radioGroup.add(spyDroneRadioButton);
		radioGroup.add(frigateRadioButton);
		radioGroup.add(cruiseShipRadioButton);
		radioGroup.add(amphibiousRadioButton);
		radioGroup.add(hybridRadioButton);
		radioGroup.add(electricBicycleRadioButton);

		buttonsJPanel = new JPanel(new GridLayout(10, 1, 20, 0));
		buttonsJPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		buttonsJPanel.setOpaque(true);
		buttonsJPanel.setBackground(new Color(0xE1FFB1));

		buttonsJPanel.add(leftText);
		buttonsJPanel.add(jeepRadioButton);
		buttonsJPanel.add(bicycleRadioButton);
		buttonsJPanel.add(toyDroneRadioButton);
		buttonsJPanel.add(spyDroneRadioButton);
		buttonsJPanel.add(frigateRadioButton);
		buttonsJPanel.add(cruiseShipRadioButton);
		buttonsJPanel.add(amphibiousRadioButton);
		buttonsJPanel.add(hybridRadioButton);
		buttonsJPanel.add(electricBicycleRadioButton);

		jeepRadioButton.addActionListener(this);
		bicycleRadioButton.addActionListener(this);
		toyDroneRadioButton.addActionListener(this);
		spyDroneRadioButton.addActionListener(this);
		frigateRadioButton.addActionListener(this);
		cruiseShipRadioButton.addActionListener(this);
		amphibiousRadioButton.addActionListener(this);
		hybridRadioButton.addActionListener(this);
		electricBicycleRadioButton.addActionListener(this);

		this.add(buttonsJPanel, BorderLayout.WEST);

		// -----------------LEFT PANEL-----------------

		// -----------------Right PANEL-----------------

		customizationPanel = new JPanel(new GridLayout(16, 1, 40, 0));
		customizationPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, -20, 20));

		rightText = new JLabel("Fill the Following:");
		rightText.setForeground(new Color(0x84243B));
		rightText.setFont(new Font(null, Font.BOLD, 24));
		rightText.setHorizontalAlignment(JLabel.CENTER);

		// Labels
		modelLabel = new JLabel("Model:");

		maxSpeedLabel = new JLabel("Max speed:");

		maxPassengerLabel = new JLabel("Passenger capacity:");

		numOfWheelsLabel = new JLabel("Number of wheels:");

		averageFuelConsumptionLabel = new JLabel("Average fuel consumption:");

		averageLifetimeLabel = new JLabel("Average engine lifespan:");

		powerSourceLabel = new JLabel("Power source:");

		vehicleLabel = new JLabel("design:");

		flagLabel = new JLabel("Flag:");

		roadtypeLabel = new JLabel("Road type:");

		energyScoreLabel = new JLabel("energy score:");

		windDirectionLabel = new JLabel("Wind Direction:");

		millitaryUseLabel = new JLabel("millitary use:");

		// parameters
		modelField = new JTextField();
		modelField.setPreferredSize(new Dimension(300, 30));

		maxSpeedField = new JTextField();
		PlainDocument document = (PlainDocument) maxSpeedField.getDocument();
		document.setDocumentFilter(new IntFilter());
		maxSpeedField.setPreferredSize(new Dimension(300, 30));

		maxPassengersField = new JTextField();
		document = (PlainDocument) maxPassengersField.getDocument();
		document.setDocumentFilter(new IntFilter());
		maxPassengersField.setPreferredSize(new Dimension(300, 30));

		numOfWheelsField = new JTextField();
		document = (PlainDocument) numOfWheelsField.getDocument();
		document.setDocumentFilter(new IntFilter());
		numOfWheelsField.setPreferredSize(new Dimension(300, 30));

		averageFuelConsumptionField = new JTextField();
		document = (PlainDocument) averageFuelConsumptionField.getDocument();
		document.setDocumentFilter(new IntFilter());
		averageFuelConsumptionField.setPreferredSize(new Dimension(300, 30));

		averageLifetimeField = new JTextField();
		document = (PlainDocument) averageLifetimeField.getDocument();
		document.setDocumentFilter(new IntFilter());
		averageLifetimeField.setPreferredSize(new Dimension(300, 30));

		PowerSourceField = new JTextField();
		PowerSourceField.setPreferredSize(new Dimension(300, 30));

		// -----Comboboxes-----

		/////

		flagBox = new JComboBox();
		flagBox.setPreferredSize(new Dimension(200, 60));

		flagBox.setAlignmentX(CENTER_ALIGNMENT);
		// initialize model
		String[] imgArr = new String[] { "israel.png", "germany.png", "greece.png", "italy.png", "usa.png",
				"somalia.png", "pirate.png" };
		File currentDirFile = new File("src/Graphic/icons/flag icons");
		String helper = currentDirFile.getAbsolutePath();

		DefaultComboBoxModel defmodel = new DefaultComboBoxModel();

		for (String namePath : imgArr) {
			String nameOfFile = namePath.substring(0, namePath.indexOf('.'));

			ImageIcon icon = new ImageIcon(helper + "/" + namePath);

			Image image = icon.getImage();
			Image newImage = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);

			defmodel.addElement(new ImageText(new ImageIcon(newImage), nameOfFile));

		}

		flagBox.setModel(defmodel);
		flagBox.setRenderer(new ComboImageTextRenderer());

		/////

		vehicleBox = new JComboBox();
		vehicleBox.setPreferredSize(new Dimension(200, 150));

		vehicleBox.setAlignmentX(CENTER_ALIGNMENT);
		// initialize model
		String[] vehicleImgArr = defaulVehicleArr;
		File vehicleCurrentDirFile = defaultVehicleCurrentDirFile;
		String vehicleHelper = vehicleCurrentDirFile.getAbsolutePath();

		vehicleDefmodel = new DefaultComboBoxModel();

		for (String namePath : vehicleImgArr) {
			String nameOfFile = namePath.substring(0, namePath.indexOf('.'));

			ImageIcon icon = new ImageIcon(vehicleHelper + "/" + namePath);

			Image image = icon.getImage();
			Image newImage = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);

			vehicleDefmodel.addElement(new ImageText(new ImageIcon(newImage), nameOfFile));

		}

		vehicleBox.setModel(vehicleDefmodel);
		vehicleBox.setRenderer(new ComboImageTextRenderer());

		////

		String[] optionStrings = { "Paved", "Offroad" };
		roadTypeBox = new JComboBox<String>(optionStrings);
		roadTypeBox.setAlignmentX(CENTER_ALIGNMENT);

		String[] EnergyOptions = { "A", "B", "C" };
		energyScoreBox = new JComboBox<String>(EnergyOptions);
		energyScoreBox.setAlignmentX(CENTER_ALIGNMENT);

		windDirectionButton = new JCheckBox();
		millitaryUseButton = new JCheckBox();

		// sub-Panels
		modelPanel = new JPanel(new BorderLayout());
		modelPanel.add(modelLabel, BorderLayout.WEST);
		modelPanel.add(modelField, BorderLayout.EAST);

		maxSpeedPanel = new JPanel(new BorderLayout());
		maxSpeedPanel.add(maxSpeedLabel, BorderLayout.WEST);
		maxSpeedPanel.add(maxSpeedField, BorderLayout.EAST);

		maxPassengerPanel = new JPanel(new BorderLayout());
		maxPassengerPanel.add(maxPassengerLabel, BorderLayout.WEST);
		maxPassengerPanel.add(maxPassengersField, BorderLayout.EAST);

		numOfWheelsPanel = new JPanel(new BorderLayout());
		numOfWheelsPanel.add(numOfWheelsLabel, BorderLayout.WEST);
		numOfWheelsPanel.add(numOfWheelsField, BorderLayout.EAST);

		averageFuelConsumptionPanel = new JPanel(new BorderLayout());
		averageFuelConsumptionPanel.add(averageFuelConsumptionLabel, BorderLayout.WEST);
		averageFuelConsumptionPanel.add(averageFuelConsumptionField, BorderLayout.EAST);

		averageLifetimePanel = new JPanel(new BorderLayout());
		averageLifetimePanel.add(averageLifetimeLabel, BorderLayout.WEST);
		averageLifetimePanel.add(averageLifetimeField, BorderLayout.EAST);

		powerSourcePanel = new JPanel(new BorderLayout());
		powerSourcePanel.add(powerSourceLabel, BorderLayout.WEST);
		powerSourcePanel.add(PowerSourceField, BorderLayout.EAST);

		vehiclePanel = new JPanel(new BorderLayout());
		vehiclePanel.add(vehicleLabel, BorderLayout.WEST);
		vehiclePanel.add(uploadButton, BorderLayout.CENTER);
		vehiclePanel.add(vehicleBox, BorderLayout.EAST);

		flagPanel = new JPanel(new BorderLayout());
		flagPanel.add(flagLabel, BorderLayout.WEST);
		flagPanel.add(flagBox, BorderLayout.EAST);

		roadtypePanel = new JPanel(new BorderLayout());
		roadtypePanel.add(roadtypeLabel, BorderLayout.WEST);
		roadtypePanel.add(roadTypeBox, BorderLayout.EAST);

		energyScorePanel = new JPanel(new BorderLayout());
		energyScorePanel.add(energyScoreLabel, BorderLayout.WEST);
		energyScorePanel.add(energyScoreBox, BorderLayout.EAST);

		windDirectionPanel = new JPanel(new BorderLayout());
		windDirectionPanel.add(windDirectionLabel, BorderLayout.WEST);
		windDirectionPanel.add(windDirectionButton, BorderLayout.EAST);

		millitaryUsePanel = new JPanel(new BorderLayout());
		millitaryUsePanel.add(millitaryUseLabel, BorderLayout.WEST);
		millitaryUsePanel.add(millitaryUseButton, BorderLayout.EAST);

		customizationPanel.add(rightText);
		customizationPanel.add(modelPanel);
		customizationPanel.add(maxSpeedPanel);
		customizationPanel.add(maxPassengerPanel);
		customizationPanel.add(numOfWheelsPanel);
		customizationPanel.add(averageFuelConsumptionPanel);
		customizationPanel.add(averageLifetimePanel);
		customizationPanel.add(powerSourcePanel);
		customizationPanel.add(vehiclePanel);
		customizationPanel.add(flagPanel);
		customizationPanel.add(roadtypePanel);
		customizationPanel.add(energyScorePanel);
		customizationPanel.add(windDirectionPanel);
		customizationPanel.add(millitaryUsePanel);

		this.add(customizationPanel, BorderLayout.EAST);

		this.add(new JSeparator(JSeparator.VERTICAL), BorderLayout.CENTER);

		submitButton = new MyButtons("SUBMIT", 0, 0);
		submitButton.addActionListener(this);
		customizationPanel.add(submitButton, BorderLayout.SOUTH);
		customizationPanel.setVisible(false);
		customizationPanel.setOpaque(true);

		// -----------------Right PANEL-----------------

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		customizationPanel.setVisible(true);
		Dictionary<String,Object> vehicleParams = new Hashtable<>();
	

		if (e.getSource() instanceof JRadioButton) {
			JRadioButton src = (JRadioButton) e.getSource();
			String srcName = src.getText();
			switch (srcName) {
			case "Jeep": {
				resetRightPanel();
				modelField.setText("");
				maxSpeedField.setText("0");
				averageFuelConsumptionField.setText("0");
				averageLifetimeField.setText("0");
				powerSourcePanel.setVisible(false);
				maxPassengersField.setText("5");
				maxPassengersField.setEnabled(false);
				numOfWheelsField.setText("4");
				numOfWheelsField.setEnabled(false);
				roadTypeBox.setSelectedIndex(1);
				flagPanel.setVisible(false);
				roadTypeBox.setEnabled(false);
				energyScorePanel.setVisible(false);
				windDirectionPanel.setVisible(false);
				millitaryUsePanel.setVisible(false);
				CurrentImageDir = "jeep icons";
				vehicleImgArr = new String[] { "j1.png", "j2.jpg", "j3.jpg" };
				vehicleDefmodel = modelboxCreator();
				vehicleBox.setModel(vehicleDefmodel);

				customizationPanel.repaint();

				break;
			}
			case "Bicycle": {
				resetRightPanel();
				System.out.println("Bicycle");
				modelField.setText("");
				maxSpeedField.setText("0");

				maxPassengersField.setText("1");
				maxPassengersField.setEnabled(false);

				numOfWheelsField.setText("2");
				numOfWheelsField.setEnabled(false);

				averageFuelConsumptionPanel.setVisible(false);
				averageLifetimePanel.setVisible(false);

				PowerSourceField.setText("Manual");
				PowerSourceField.setEnabled(false);

				energyScoreBox.setSelectedIndex(0);
				energyScoreBox.setEnabled(false);

				flagPanel.setVisible(false);
				windDirectionPanel.setVisible(false);
				millitaryUsePanel.setVisible(false);
				powerSourcePanel.setVisible(false);

				CurrentImageDir = "bicycle icons";
				vehicleImgArr = new String[] { "b1.jpg", "b2.jpg", "b3.jpg" };
				vehicleDefmodel = modelboxCreator();
				vehicleBox.setModel(vehicleDefmodel);

				customizationPanel.repaint();

				break;
			}
			case "Toy Drone": {
				resetRightPanel();
				modelField.setText("Toy");
				modelField.setEnabled(false);

				maxPassengersField.setText("0");
				maxPassengersField.setEnabled(false);

				numOfWheelsField.setText("0");
				numOfWheelsField.setEnabled(false);

				maxSpeedField.setText("10");
				maxSpeedField.setEnabled(false);

				averageFuelConsumptionPanel.setVisible(false);
				averageLifetimePanel.setVisible(false);

				PowerSourceField.setText("Manual");
				PowerSourceField.setEnabled(false);

				roadtypePanel.setVisible(false);

				energyScoreBox.setSelectedIndex(0);
				energyScoreBox.setEnabled(false);

				flagPanel.setVisible(false);

				windDirectionPanel.setVisible(false);
				millitaryUsePanel.setVisible(false);

				System.out.println("Toy");

				CurrentImageDir = "toydrone icons";
				vehicleImgArr = new String[] { "td1.png", "td2.png", "td3.png" };
				vehicleDefmodel = modelboxCreator();
				vehicleBox.setModel(vehicleDefmodel);

				customizationPanel.repaint();
				break;
			}
			case "Spy Drone": {

				resetRightPanel();
				modelField.setText("Confidential");
				modelField.setEnabled(false);

				maxPassengersField.setText("1");
				maxPassengersField.setEnabled(false);

				numOfWheelsField.setText("0");
				numOfWheelsField.setEnabled(false);

				maxSpeedField.setText("50");
				maxSpeedField.setEnabled(false);

				averageFuelConsumptionPanel.setVisible(false);
				averageLifetimePanel.setVisible(false);
				PowerSourceField.setText("");

				roadtypePanel.setVisible(false);

				energyScoreBox.setSelectedIndex(2);
				energyScoreBox.setEnabled(false);

				flagPanel.setVisible(false);

				windDirectionPanel.setVisible(false);

				millitaryUseButton.setSelected(true);
				millitaryUseButton.setEnabled(false);
				System.out.println("Spy");

				CurrentImageDir = "spydrone icons";
				vehicleImgArr = new String[] { "sp1.jpg", "sp2.jpg", "sp3.png" };
				vehicleDefmodel = modelboxCreator();
				vehicleBox.setModel(vehicleDefmodel);

				customizationPanel.repaint();
				break;
			}
			case "Frigate": {
				resetRightPanel();

				flagBox.setSelectedIndex(0);
				flagBox.setEnabled(false);

				modelField.setText("");
				maxSpeedField.setText("0");
				maxPassengersField.setText("0");

				averageFuelConsumptionField.setText("500");
				averageFuelConsumptionField.setEnabled(false);

				averageLifetimeField.setText("4");
				averageLifetimeField.setEnabled(false);

				numOfWheelsPanel.setVisible(false);

				powerSourcePanel.setVisible(false);

				roadtypePanel.setVisible(false);
				energyScorePanel.setVisible(false);

				windDirectionButton.setEnabled(false);
				millitaryUsePanel.setVisible(false);

				System.out.println("Frigate");

				CurrentImageDir = "frigate icons";
				vehicleImgArr = new String[] { "f1.jpg", "f2.jpg", "f3.jpg" };
				vehicleDefmodel = modelboxCreator();
				vehicleBox.setModel(vehicleDefmodel);

				customizationPanel.repaint();
				break;
			}
			case "Cruise Ship": {
				resetRightPanel();

				numOfWheelsPanel.setVisible(false);
				powerSourcePanel.setVisible(false);

				modelField.setText("");
				maxSpeedField.setText("0");
				maxPassengersField.setText("0");
				averageFuelConsumptionField.setText("0");
				averageLifetimeField.setText("0");

				roadtypePanel.setVisible(false);

				windDirectionButton.setSelected(true);
				windDirectionButton.setEnabled(false);

				millitaryUsePanel.setVisible(false);

				energyScorePanel.setVisible(false);

				System.out.println("Cruise");

				CurrentImageDir = "cruiseship icons";
				vehicleImgArr = new String[] { "cs1.jpg", "cs2.jpg", "cs3.jpg" };
				vehicleDefmodel = modelboxCreator();
				vehicleBox.setModel(vehicleDefmodel);

				customizationPanel.repaint();
				break;
			}
			case "Amphibious": {
				resetRightPanel();

				modelField.setText("");
				maxSpeedField.setText("0");
				maxPassengersField.setText("0");
				numOfWheelsField.setText("0");
				averageFuelConsumptionField.setText("0");
				averageLifetimeField.setText("0");

				powerSourcePanel.setVisible(false);
				roadTypeBox.setSelectedIndex(0);
				roadTypeBox.setEnabled(false);
				energyScorePanel.setVisible(false);
				millitaryUsePanel.setVisible(false);

				System.out.println("Amphibious");

				CurrentImageDir = "amphibiousvehicle icons";
				vehicleImgArr = new String[] { "av1.jpg", "av2.jpg", "av3.jpg" };
				vehicleDefmodel = modelboxCreator();
				vehicleBox.setModel(vehicleDefmodel);

				customizationPanel.repaint();
				break;
			}
			case "Hybrid": {
				resetRightPanel();

				modelField.setText("");
				maxSpeedField.setText("0");
				maxPassengersField.setText("0");
				numOfWheelsField.setText("0");
				averageFuelConsumptionField.setText("0");
				averageLifetimeField.setText("0");

				powerSourcePanel.setVisible(false);
				roadTypeBox.setSelectedIndex(0);
				roadTypeBox.setEnabled(false);
				energyScorePanel.setVisible(false);
				millitaryUsePanel.setVisible(false);

				System.out.println("Hybrid");

				CurrentImageDir = "hybrid icons";
				vehicleImgArr = new String[] { "h1.jpg", "h2.jpg", "h3.jpg" };
				vehicleDefmodel = modelboxCreator();
				vehicleBox.setModel(vehicleDefmodel);

				customizationPanel.repaint();
				break;
			}
			case "Electric Bicycle": {
				resetRightPanel();
				System.out.println("Electric Bicycle");

				maxPassengersField.setText("1");
				maxPassengersField.setEnabled(false);

				numOfWheelsField.setText("2");
				numOfWheelsField.setEnabled(false);

				modelField.setText("");
				maxSpeedField.setText("0");
				averageLifetimeField.setText("0");

				averageFuelConsumptionField.setText("20");
				averageFuelConsumptionField.setEnabled(false);
				powerSourcePanel.setVisible(false);

				flagPanel.setVisible(false);
				windDirectionPanel.setVisible(false);
				millitaryUsePanel.setVisible(false);
				energyScorePanel.setVisible(false);

				CurrentImageDir = "electricbicycle icons";
				vehicleImgArr = new String[] { "eb1.jpg", "eb2.jpg", "eb3.jpg" };
				vehicleDefmodel = modelboxCreator();
				vehicleBox.setModel(vehicleDefmodel);

				customizationPanel.repaint();
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + e.getSource());
			}
		} else if (e.getSource() == submitButton) {

			Enumeration<AbstractButton> bg = radioGroup.getElements();

			while (bg.hasMoreElements()) {
				JRadioButton checkedRadioButton = (JRadioButton) bg.nextElement();
				if (checkedRadioButton.isSelected()) {
					ImageText img = (ImageText) vehicleBox.getSelectedItem();
					ImageText flag = (ImageText) flagBox.getSelectedItem();
					String model = modelField.getText();
					Integer maxSpeed;
					if (maxSpeedPanel.isVisible()) {
						maxSpeed = Integer.parseInt(maxSpeedField.getText());
					} else {
						maxSpeed = 0;
					}
					Integer maxPassenger;
					if (maxPassengerPanel.isVisible()) {
						maxPassenger = Integer.parseInt(maxPassengersField.getText());
					} else {
						maxPassenger = 0;
					}
					Integer numOfWheels;
					if (numOfWheelsPanel.isVisible()) {
						numOfWheels = Integer.parseInt(numOfWheelsField.getText());
					} else {
						numOfWheels = 0;
					}
					Integer averageFuelConsumption;
					if (averageFuelConsumptionPanel.isVisible()) {
						averageFuelConsumption = Integer.parseInt(averageFuelConsumptionField.getText());
					} else {
						averageFuelConsumption = 0;
					}

					Integer averageLifetime;
					if (averageLifetimePanel.isVisible()) {
						averageLifetime = Integer.parseInt(averageLifetimeField.getText());
					} else {
						averageLifetime = 0;
					}
					String powerSource = PowerSourceField.getText();
					String roadType = (String) roadTypeBox.getSelectedItem();
					Boolean windDirection = windDirectionButton.isSelected();
					
					//initializing parameter Dictionary
					vehicleParams.put("M", model);
					vehicleParams.put("MP", maxPassenger);
					vehicleParams.put("MS", maxSpeed);
					vehicleParams.put("NOW", numOfWheels);
					vehicleParams.put("AFC", averageFuelConsumption);
					vehicleParams.put("AL", averageLifetime);
					vehicleParams.put("F", flag.getText());
					vehicleParams.put("WD", windDirection);
					vehicleParams.put("RT", roadType);
					vehicleParams.put("PS", powerSource);
					vehicleParams.put("ES", energyScoreBox.getSelectedItem());
					

					switch (checkedRadioButton.getText()) {
					case "Jeep": {
						if (!(modelField.getText().isEmpty()) && !(maxSpeedField.getText().isEmpty())
								&& !(averageFuelConsumptionField.getText().isEmpty())
								&& !(averageLifetimeField.getText().isEmpty())) {
							
	
							if (!agencyCreated()) {
								database.VehiclePanelArray.add(new VehiclePanel(
										landVehicleFactory.CreateVehicle(checkedRadioButton.getText(),vehicleParams), img));
								JOptionPane.showMessageDialog(null, checkedRadioButton.getText() + " has been created",
										"Success!", JOptionPane.INFORMATION_MESSAGE);
							}
							if (agencyCreated()) {
								new Thread(() -> {
									JDialog jDialog = createUpdateDialog();
									try {
										Thread.sleep(1000 * getRandomNumber(3, 8));
										database.VehiclePanelArray.add(new VehiclePanel(
												landVehicleFactory.CreateVehicle(checkedRadioButton.getText(),vehicleParams),
												img));
									} catch (InterruptedException ex) {
										// TODO: handle exception
									}
									jDialog.dispose();
									updateCenterPanel();
									JOptionPane.showMessageDialog(null,
											checkedRadioButton.getText() + " has been created", "Success!",
											JOptionPane.INFORMATION_MESSAGE);
								}).start();
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Missing fields, cannot create " + checkedRadioButton.getText(), "ERROR!",
									JOptionPane.ERROR_MESSAGE);
						}
						break;
					}
					case "Bicycle": {
						if (!(modelField.getText().isEmpty()) && !(maxSpeedField.getText().isEmpty())) {
							if (!agencyCreated()) {
								database.VehiclePanelArray
										.add(new VehiclePanel(landVehicleFactory.CreateVehicle(checkedRadioButton.getText(),vehicleParams), img));
								JOptionPane.showMessageDialog(null, checkedRadioButton.getText() + " has been created",
										"Success!", JOptionPane.INFORMATION_MESSAGE);
							}
							if (agencyCreated()) {
								new Thread(() -> {
									JDialog jDialog = createUpdateDialog();
									try {
										Thread.sleep(1000 * getRandomNumber(3, 8));
										database.VehiclePanelArray
												.add(new VehiclePanel(landVehicleFactory.CreateVehicle(checkedRadioButton.getText(),vehicleParams), img));
									} catch (InterruptedException ex) {
										// TODO: handle exception
									}
									jDialog.dispose();
									updateCenterPanel();
									JOptionPane.showMessageDialog(null,
											checkedRadioButton.getText() + " has been created", "Success!",
											JOptionPane.INFORMATION_MESSAGE);
								}).start();
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Missing fields, cannot create " + checkedRadioButton.getText(), "ERROR!",
									JOptionPane.ERROR_MESSAGE);
						}
						break;
					}
					case "Toy Drone": {

						if (!agencyCreated()) {
							database.VehiclePanelArray.add(new VehiclePanel(airVehicleFactory.CreateVehicle(checkedRadioButton.getText(), vehicleParams), img));
							JOptionPane.showMessageDialog(null, checkedRadioButton.getText() + " has been created",
									"Success!", JOptionPane.INFORMATION_MESSAGE);
						}
						if (agencyCreated()) {
							new Thread(() -> {
								JDialog jDialog = createUpdateDialog();
								try {
									Thread.sleep(1000 * getRandomNumber(3, 8));
									database.VehiclePanelArray.add(new VehiclePanel(airVehicleFactory.CreateVehicle(checkedRadioButton.getText(), vehicleParams), img));
								} catch (InterruptedException ex) {
									// TODO: handle exception
								}
								jDialog.dispose();
								updateCenterPanel();
								JOptionPane.showMessageDialog(null, checkedRadioButton.getText() + " has been created",
										"Success!", JOptionPane.INFORMATION_MESSAGE);
							}).start();
						}

						break;
					}
					case "Spy Drone": {
						if (!(PowerSourceField.getText().isEmpty())) {
							if (!agencyCreated()) {
								database.VehiclePanelArray.add(new VehiclePanel(airVehicleFactory.CreateVehicle(checkedRadioButton.getText(), vehicleParams), img));
								JOptionPane.showMessageDialog(null, checkedRadioButton.getText() + " has been created",
										"Success!", JOptionPane.INFORMATION_MESSAGE);
							}
							if (agencyCreated()) {
								new Thread(() -> {
									JDialog jDialog = createUpdateDialog();
									try {
										Thread.sleep(1000 * getRandomNumber(3, 8));
										database.VehiclePanelArray
												.add(new VehiclePanel(airVehicleFactory.CreateVehicle(checkedRadioButton.getText(), vehicleParams), img));
									} catch (InterruptedException ex) {
										// TODO: handle exception
									}
									jDialog.dispose();
									updateCenterPanel();
									JOptionPane.showMessageDialog(null,
											checkedRadioButton.getText() + " has been created", "Success!",
											JOptionPane.INFORMATION_MESSAGE);
								}).start();
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Missing fields, cannot create " + checkedRadioButton.getText(), "ERROR!",
									JOptionPane.ERROR_MESSAGE);
						}
						break;
					}
					case "Frigate": {
						if (!(modelField.getText().isEmpty()) && !(maxPassengersField.getText().isEmpty())
								&& !(maxSpeedField.getText().isEmpty())) {
							if (!agencyCreated()) {
								database.VehiclePanelArray
										.add(new VehiclePanel(seaVehicleFactory.CreateVehicle(checkedRadioButton.getText(), vehicleParams), img));
								JOptionPane.showMessageDialog(null, checkedRadioButton.getText() + " has been created",
										"Success!", JOptionPane.INFORMATION_MESSAGE);
							}
							if (agencyCreated()) {
								new Thread(() -> {
									JDialog jDialog = createUpdateDialog();
									try {
										Thread.sleep(1000 * getRandomNumber(3, 8));
										database.VehiclePanelArray
												.add(new VehiclePanel(seaVehicleFactory.CreateVehicle(checkedRadioButton.getText(), vehicleParams), img));
									} catch (InterruptedException ex) {
										// TODO: handle exception
									}
									jDialog.dispose();
									updateCenterPanel();
									JOptionPane.showMessageDialog(null,
											checkedRadioButton.getText() + " has been created", "Success!",
											JOptionPane.INFORMATION_MESSAGE);
								}).start();
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Missing fields, cannot create " + checkedRadioButton.getText(), "ERROR!",
									JOptionPane.ERROR_MESSAGE);
						}
						break;
					}
					case "Cruise Ship": {
						if (!(modelField.getText().isEmpty()) && !(maxPassengersField.getText().isEmpty())
								&& !(maxSpeedField.getText().isEmpty())
								&& !(averageFuelConsumptionField.getText().isEmpty())
								&& !(averageLifetimeField.getText().isEmpty())) {
							if (!agencyCreated()) {
								database.VehiclePanelArray.add(new VehiclePanel(seaVehicleFactory.CreateVehicle(checkedRadioButton.getText(), vehicleParams), img));
								JOptionPane.showMessageDialog(null, checkedRadioButton.getText() + " has been created",
										"Success!", JOptionPane.INFORMATION_MESSAGE);
							}
							if (agencyCreated()) {
								new Thread(() -> {
									JDialog jDialog = createUpdateDialog();
									try {
										Thread.sleep(1000 * getRandomNumber(3, 8));
										database.VehiclePanelArray
												.add(new VehiclePanel(seaVehicleFactory.CreateVehicle(checkedRadioButton.getText(), vehicleParams), img));
									} catch (InterruptedException ex) {
										// TODO: handle exception
									}
									jDialog.dispose();
									updateCenterPanel();
									JOptionPane.showMessageDialog(null,
											checkedRadioButton.getText() + " has been created", "Success!",
											JOptionPane.INFORMATION_MESSAGE);
								}).start();
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Missing fields, cannot create " + checkedRadioButton.getText(), "ERROR!",
									JOptionPane.ERROR_MESSAGE);
						}
						break;
					}
					case "Amphibious": {
						if (!(modelField.getText().isEmpty()) && !(maxPassengersField.getText().isEmpty())
								&& !(maxSpeedField.getText().isEmpty()) && !(numOfWheelsField.getText().isEmpty())
								&& !(averageFuelConsumptionField.getText().isEmpty())
								&& !(averageLifetimeField.getText().isEmpty())
								&& !(numOfWheelsField.getText().isEmpty())) {

							if (!agencyCreated()) {
								database.VehiclePanelArray.add(new VehiclePanel(
										hybridVehicleFactory.CreateVehicle(checkedRadioButton.getText(), vehicleParams),
										img));
								JOptionPane.showMessageDialog(null, checkedRadioButton.getText() + " has been created",
										"Success!", JOptionPane.INFORMATION_MESSAGE);
							}
							if (agencyCreated()) {
								new Thread(() -> {
									JDialog jDialog = createUpdateDialog();
									try {
										Thread.sleep(1000 * getRandomNumber(3, 8));
										database.VehiclePanelArray.add(new VehiclePanel(hybridVehicleFactory.CreateVehicle(checkedRadioButton.getText(), vehicleParams), img));
									} catch (InterruptedException ex) {
										// TODO: handle exception
									}
									jDialog.dispose();
									updateCenterPanel();
									JOptionPane.showMessageDialog(null,
											checkedRadioButton.getText() + " has been created", "Success!",
											JOptionPane.INFORMATION_MESSAGE);
								}).start();
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Missing fields, cannot create " + checkedRadioButton.getText() + " vehicle",
									"ERROR!", JOptionPane.ERROR_MESSAGE);
						}
						break;
					}
					case "Hybrid": {
						if (!(modelField.getText().isEmpty()) && !(maxPassengersField.getText().isEmpty())
								&& !(maxSpeedField.getText().isEmpty()) && !(numOfWheelsField.getText().isEmpty())
								&& !(averageFuelConsumptionField.getText().isEmpty())
								&& !(averageLifetimeField.getText().isEmpty())
								&& !(numOfWheelsField.getText().isEmpty())) {
							if (!agencyCreated()) {
								database.VehiclePanelArray.add(new VehiclePanel(
										hybridVehicleFactory.CreateVehicle(checkedRadioButton.getText(), vehicleParams),
										img));
								JOptionPane.showMessageDialog(null, checkedRadioButton.getText() + " has been created",
										"Success!", JOptionPane.INFORMATION_MESSAGE);
							}
							if (agencyCreated()) {
								new Thread(() -> {
									JDialog jDialog = createUpdateDialog();
									try {
										Thread.sleep(1000 * getRandomNumber(3, 8));
										database.VehiclePanelArray.add(new VehiclePanel(hybridVehicleFactory.CreateVehicle(checkedRadioButton.getText(), vehicleParams), img));
									} catch (InterruptedException ex) {
										// TODO: handle exception
									}
									jDialog.dispose();
									updateCenterPanel();
									JOptionPane.showMessageDialog(null,
											checkedRadioButton.getText() + " has been created", "Success!",
											JOptionPane.INFORMATION_MESSAGE);
								}).start();
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Missing fields, cannot create " + checkedRadioButton.getText() + " vehicle",
									"ERROR!", JOptionPane.ERROR_MESSAGE);
						}
						break;

					}
					case "Electric Bicycle": {
						if (!(modelField.getText().isEmpty()) && !(maxSpeedField.getText().isEmpty())
								&& !(averageLifetimeField.getText().isEmpty())) {
							if (!agencyCreated()) {
								database.VehiclePanelArray.add(new VehiclePanel(
										landVehicleFactory.CreateVehicle(checkedRadioButton.getText(), vehicleParams), img));
								JOptionPane.showMessageDialog(null, checkedRadioButton.getText() + " has been created",
										"Success!", JOptionPane.INFORMATION_MESSAGE);
							}
							if (agencyCreated()) {
								new Thread(() -> {
									JDialog jDialog = createUpdateDialog();
									try {
										Thread.sleep(1000 * getRandomNumber(3, 8));
										database.VehiclePanelArray.add(new VehiclePanel(
												landVehicleFactory.CreateVehicle(checkedRadioButton.getText(), vehicleParams), img));
									} catch (InterruptedException ex) {
										// TODO: handle exception
									}
									jDialog.dispose();
									updateCenterPanel();
									JOptionPane.showMessageDialog(null,
											checkedRadioButton.getText() + " has been created", "Success!",
											JOptionPane.INFORMATION_MESSAGE);
								}).start();
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Missing fields, cannot create " + checkedRadioButton.getText(), "ERROR!",
									JOptionPane.ERROR_MESSAGE);
						}
						break;
					}
					}
				}

			}

		} else if (e.getSource() == uploadButton) {
			JFileChooser chooser = new JFileChooser();
			int response = chooser.showSaveDialog(null);
			if (response == JFileChooser.APPROVE_OPTION) {
				File fileName = new File(chooser.getSelectedFile().getAbsolutePath());
				modelboxCreatorUpdater(fileName, vehicleDefmodel);
				vehicleBox.setModel(vehicleDefmodel);
				customizationPanel.repaint();
			}
		}

	}

	DefaultComboBoxModel modelboxCreator() {

		// initialize model
		File vehicleCurrentDirFile = new File("src/Graphic/icons/" + CurrentImageDir);
		String vehicleHelper = vehicleCurrentDirFile.getAbsolutePath();

		DefaultComboBoxModel localVehicleDefmodel = new DefaultComboBoxModel();

		for (String namePath : vehicleImgArr) {
			String nameOfFile = namePath.substring(0, namePath.indexOf('.'));

			ImageIcon icon = new ImageIcon(vehicleHelper + "/" + namePath);

			Image image = icon.getImage();
			Image newImage = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);

			localVehicleDefmodel.addElement(new ImageText(new ImageIcon(newImage), nameOfFile));
		}

		return localVehicleDefmodel;
	}

	void modelboxCreatorUpdater(File absPath, DefaultComboBoxModel existantBox) {

		// initialize model
		String vehicleHelper = absPath.toString();

		ImageIcon icon = new ImageIcon(vehicleHelper);

		Image image = icon.getImage();
		Image newImage = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);

		existantBox.addElement(new ImageText(new ImageIcon(newImage), "new model"));
	}

	void resetListVisibility() {
		modelPanel.setVisible(true);
		maxSpeedPanel.setVisible(true);
		maxPassengerPanel.setVisible(true);
		numOfWheelsPanel.setVisible(true);
		averageFuelConsumptionPanel.setVisible(true);
		averageLifetimePanel.setVisible(true);
		powerSourcePanel.setVisible(true);
		flagPanel.setVisible(true);
		roadtypePanel.setVisible(true);
		energyScorePanel.setVisible(true);
		windDirectionPanel.setVisible(true);
		millitaryUsePanel.setVisible(true);
	}

	void resetFieldActivity() {
		modelField.setEnabled(true);
		maxPassengersField.setEnabled(true);
		maxSpeedField.setEnabled(true);
		numOfWheelsField.setEnabled(true);
		averageFuelConsumptionField.setEnabled(true);
		averageLifetimeField.setEnabled(true);
		PowerSourceField.setEnabled(true);
	}

	void resetComboBoxActivity() {
		flagBox.setEnabled(true);
		roadTypeBox.setEnabled(true);
		energyScoreBox.setEnabled(true);

	}

	void resetButtonActivity() {
		windDirectionButton.setEnabled(true);
		millitaryUseButton.setEnabled(true);
	}

	void resetButtonVisibility() {
		windDirectionButton.setVisible(true);
		millitaryUseButton.setVisible(true);
	}

	void resetRightPanel() {
		resetComboBoxActivity();
		resetListVisibility();
		resetFieldActivity();
		resetButtonActivity();
		resetButtonVisibility();
	}

	int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	JDialog createUpdateDialog() {

		JDialog d = new JDialog();
		d.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		d.setTitle("Processing");
		d.setSize(280, 80);
		d.setResizable(false);
		d.setLocationRelativeTo(null);
		d.getContentPane().setBackground(new Color(0xE1FFB1));
		JLabel textJLabel = new JLabel("Updating Database... Please wait");
		textJLabel.setForeground(new Color(0xFF5D5D));
		textJLabel.setVisible(true);
		d.add(textJLabel);
		d.setVisible(true);

		return d;
	}

}
