// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500

package Graphic;

import System.DB;
import VehicleStats.AirVehicle;
import VehicleStats.LandVehicle;
import VehicleStats.SeaVehicle;
import VehicleStats.Vehicle;
import VehicleStats.VehicleLock;
import VehicleTypes.AmphibiousVehicle;
import VehicleTypes.CommercialDrone;
import VehicleTypes.CruiseShip;
import VehicleTypes.Frigate;
import VehicleTypes.HybridPlane;

import static System.DB.setChosen;
import static Graphic.BuyFrame.vehicleList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.PlainDocument;

import DP.Decorator.ColoredVehicle;
import DP.Decorator.VehicleInterface;
import DP.Memento.DBCaretaker;
import DP.Observer.DistanceKeeper;
import DP.Threadpool.CustomThreadPool;
import DP.Threadpool.Task;
//import DP.Threadpool.DriverPool;

public class VehicleAgency extends JFrame {
	private static JPanel centerPanel;
	private JPanel bottomPanel;
	private static JPanel topPanel;
	private static JButton buyButton;
	private static JButton testButton;
	private static JPanel buttonPanel;
	private JButton nullDistanceButton;
	private static JButton flagButton;
	private JButton addVehicleButton;
	private JButton exitButton;
	private static JLabel vehicleInfo;
	static DB database;
	private static boolean created;
	boolean cantExit = false;

	// For Singleton
	private static VehicleAgency referenceToSelf;

	// For Memento
	private static JButton saveButton;
	private static JButton loadButton;

	static DBCaretaker caretaker;

	// For Threadpool

	CustomThreadPool threadPool = new CustomThreadPool(7);

	// For Decorator

	private static JButton colorButton;

	// For Observer
	private static JLabel totalDistanceLabel;

	/////////////////////////////////
	public static VehicleAgency getInstance() {

		if (referenceToSelf != null)
			return referenceToSelf;
		else {
			return new VehicleAgency();
		}

	}

	private VehicleAgency() {
		super("Vehicle Agency");
		created = true;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		/// for Singleton
		referenceToSelf = this;

		/// For Memento
		caretaker = new DBCaretaker();

		// For Observer
		DistanceKeeper keeper = DistanceKeeper.getIntance();
		totalDistanceLabel = new JLabel("Total Distance Traveled: " + ((Long) keeper.getTotal()).toString());
		totalDistanceLabel.setForeground(new Color(0xFF5d5d));
		totalDistanceLabel.setBackground(new Color(0xB6E388));

		//////////////
		topPanel = new JPanel(new BorderLayout());
		topPanel.setPreferredSize(new Dimension(780, 75));
		topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		topPanel.setBackground(new Color(0xE1FFB1));

		centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		centerPanel.setBorder(BorderFactory.createEtchedBorder());
		centerPanel.setBackground(new Color(0xE1FFB1));

		bottomPanel = new JPanel(new GridLayout(1, 2));

		saveButton = new JButton("Save");
		saveButton.setBackground(new Color(0xB6E388));
		saveButton.setForeground(new Color(0xFF5D5D));
		saveButton.setFocusable(false);

		loadButton = new JButton("Load");
		loadButton.setBackground(new Color(0xB6E388));
		loadButton.setForeground(new Color(0xFF5D5D));
		loadButton.setFocusable(false);

		colorButton = new JButton("Color");
		colorButton.setEnabled(false);
		colorButton.setBackground(new Color(0xB6E388));
		colorButton.setForeground(new Color(0xFF5D5D));
		colorButton.setFocusable(false);

		buyButton = new JButton("Buy vehicle");
		buyButton.setEnabled(false);
		buyButton.setBackground(new Color(0xB6E388));
		buyButton.setForeground(new Color(0xFF5D5D));
		buyButton.setFocusable(false);

		testButton = new JButton("Test drive");
		testButton.setEnabled(false);
		testButton.setBackground(new Color(0xB6E388));
		testButton.setForeground(new Color(0xFF5D5D));
		testButton.setFocusable(false);

		nullDistanceButton = new JButton("Nullify distance to all");
		nullDistanceButton.setBackground(new Color(0xB6E388));
		nullDistanceButton.setForeground(new Color(0xFF5D5D));
		nullDistanceButton.setFocusable(false);

		flagButton = new JButton("Change flag to all");
		flagButton.setBackground(new Color(0xB6E388));
		flagButton.setForeground(new Color(0xFF5D5D));
		flagButton.setFocusable(false);

		addVehicleButton = new JButton("Add vehicle");
		addVehicleButton.setBackground(new Color(0xB6E388));
		addVehicleButton.setForeground(new Color(0xFF5D5D));
		addVehicleButton.setFocusable(false);

		exitButton = new JButton("Exit");
		exitButton.setBackground(new Color(0xB6E388));
		exitButton.setForeground(new Color(0xFF5D5D));
		exitButton.setFocusable(false);

		vehicleInfo = new JLabel();
		vehicleInfo.setForeground(new Color(0x84243B));
		vehicleInfo.setFont(new Font("Helvetica", Font.BOLD, 12));

		GridLayout newGridlayout = new GridLayout(3, 4);
		newGridlayout.setHgap(10);
		newGridlayout.setVgap(10);
		buttonPanel = new JPanel(newGridlayout);

		buttonPanel.add(buyButton);
		buttonPanel.add(testButton);
		buttonPanel.add(colorButton);
		buttonPanel.add(flagButton);
		buttonPanel.add(addVehicleButton);
		buttonPanel.add(nullDistanceButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(loadButton);
		buttonPanel.add(exitButton);
		buttonPanel.add(totalDistanceLabel);
		bottomPanel.add(buttonPanel, BorderLayout.EAST);
		buttonPanel.setBackground(new Color(0xE1FFB1));

		setPreferredSize(new Dimension(820, 600));
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		vehicleInfo.setText("Vehicle Information");
		vehicleInfo.setHorizontalAlignment(JLabel.CENTER);
		vehicleInfo.setVerticalAlignment(JLabel.TOP);
		vehicleInfo.setBorder(new LineBorder(Color.BLACK));
		topPanel.add(vehicleInfo, BorderLayout.CENTER);

		updateCenterPanel(); // TODO: Implement function 'updateTopPanel()'

		colorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JColorChooser colorChooser = new JColorChooser();
				Color color = colorChooser.showDialog(buttonPanel, "Pick a color", getForeground());
				if (color != null) {
					VehiclePanel chosen = DB.getChosen();
					VehicleInterface currentVehicle = chosen.getVehicle();
					if (currentVehicle instanceof ColoredVehicle)
						((ColoredVehicle) currentVehicle).setColor(color);
					else
						chosen.setVehicle(new ColoredVehicle(chosen.getVehicle(), color));
					updateCenterPanel();
				}
			}
		});

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == saveButton) {
					if (tryExit()) {
						caretaker.saveMemento(DB.createMemento());
						JOptionPane.showMessageDialog(null, "Success! " + "\n" + "State has been saved", "Save state",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == loadButton) {
					if (tryExit()) {
						setDisableBuyButton();
						setDisableTestButton();
						setDisableColorButton();
						buttonPanel.setVisible(false);
						new LoadFrame(saveButton);
						saveButton.setEnabled(false);
					}
				}
			}
		});

		buyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buyButton) {
					VehiclePanel chosenPanel = database.getChosen();
					if ((chosenPanel.getVehicle()).move(0)) {
						setChosenAvailable(chosenPanel, "Buy");
						chosenPanel.updateToolTip();
						setDisableLoadButton();
						setDisableSaveButton();
						setDisableColorButton();
						new Thread(() -> {
							synchronized (chosenPanel) {
								chosenPanel.setLiveStatus(true);
								if (chosenPanel != null) {
									String vehicleModel = chosenPanel.getVehicle().getModel();
									new BuyFrame(referenceToSelf, database, chosenPanel);
								}
							}
						}).start();
					}
				}
			}
		});

		testButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == testButton) {
					VehiclePanel chosenPanel = database.getChosen();
					if ((chosenPanel.getVehicle()).move(0)) {
						loadButton.setEnabled(false);
						saveButton.setEnabled(false);
						setChosenAvailable(chosenPanel, "Test");
						chosenPanel.updateToolTip();
						JDialog d = new JDialog();
						d.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
						d.setTitle(chosenPanel.getVehicle().getModel() + " test drive");
						JLabel dText = new JLabel("Driver is currently mid test... Please wait...");
						dText.setForeground(new Color(0xFF5D5D));
						d.add(dText);
						d.setSize(280, 80);
						d.setResizable(false);
						d.setLocationRelativeTo(null);
						d.getContentPane().setBackground(new Color(0xE1FFB1));

						testDialog(chosenPanel, d);

					}
				}
			}
		});

		nullDistanceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadButton.setEnabled(false);
				saveButton.setEnabled(false);
				if (e.getSource() == nullDistanceButton) {
					new Thread(() -> {
						synchronized ("reset") {
							JDialog dialog = createUpdateDialog();
							try {
								Thread.sleep(1000 * getRandomNumber(3, 8));
							} catch (InterruptedException e1) {
							}
							for (VehiclePanel vehiclePan : database.VehiclePanelArray) {
								vehiclePan.vehicle.nullifyDistance();

							}
							dialog.dispose();
							loadButton.setEnabled(true);
							saveButton.setEnabled(true);
							JOptionPane.showMessageDialog(null, "Success! " + "\n" + "Reset all vehicles",
									"Reset Mileage", JOptionPane.INFORMATION_MESSAGE);
						}
					}).start();
				}
			}
		});

		flagButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == flagButton) {
					new Thread(() -> {
						Vehicle wantedVehicle = null;
						synchronized ("flags") {
							setDisableLoadButton();
							setDisableSaveButton();
							JComboBox flagBox = new JComboBox();
							flagBox.setPreferredSize(new Dimension(200, 60));

							flagBox.setAlignmentX(CENTER_ALIGNMENT);
							// initialize model
							String[] imgArr = new String[] { "israel.png", "germany.png", "greece.png", "italy.png",
									"usa.png", "somalia.png", "pirate.png" };
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
							int chosenFlag = JOptionPane.showConfirmDialog(null, flagBox, "Choose a flag:",
									JOptionPane.DEFAULT_OPTION);
							if (chosenFlag == JOptionPane.OK_OPTION) {
								JDialog dialog = createUpdateDialog();
								try {
									Thread.sleep(1000 * getRandomNumber(3, 8));
								} catch (Exception e2) {
								}
								for (VehiclePanel vehicle : database.VehiclePanelArray) {
									if (vehicle.getVehicle() instanceof ColoredVehicle) {
										ColoredVehicle newVehicle = (ColoredVehicle) vehicle.getVehicle();
										wantedVehicle = (Vehicle) newVehicle.getVehicle();
									} else {
										wantedVehicle = (Vehicle) vehicle.getVehicle();
									}
									if (wantedVehicle instanceof SeaVehicle) {
										if (wantedVehicle instanceof Frigate) {
											((Frigate) wantedVehicle)
													.setFlag(((ImageText) flagBox.getSelectedItem()).getText());
										} else if (wantedVehicle instanceof CruiseShip) {
											((CruiseShip) wantedVehicle)
													.setFlag(((ImageText) flagBox.getSelectedItem()).getText());
										} else if (wantedVehicle instanceof AmphibiousVehicle) {
											((AmphibiousVehicle) wantedVehicle)
													.setFlag(((ImageText) flagBox.getSelectedItem()).getText());
										} else if (wantedVehicle instanceof HybridPlane) {
											((HybridPlane) wantedVehicle)
													.setFlag(((ImageText) flagBox.getSelectedItem()).getText());
										}
									}
								}
								dialog.dispose();
								ImageText flagImgText = (ImageText) flagBox.getSelectedItem();
								setEnableLoadButton();
								setEnableSaveButton();
								updateCenterPanel();
								JOptionPane.showMessageDialog(null,
										"Success! " + "\n" + "Changed all sea vehicle flags to  "
												+ flagImgText.getText(),
										"Flag Changed", JOptionPane.INFORMATION_MESSAGE);
							} else if (chosenFlag == JOptionPane.CLOSED_OPTION) {
							}
						}
					}).start();
				}
			}
		});

		addVehicleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == addVehicleButton) {
					new Thread(() -> {
						synchronized ("Creator") {
							VehicleCreator vCreator = new VehicleCreator();
						}
					}).start();
				}
			}
		});

		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == exitButton) {
					if (tryExit()) {
						System.exit(0);
					}

				}
			}
		});

		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void updateCenterPanel() {
		centerPanel.removeAll();

		for (VehiclePanel panel : database.VehiclePanelArray) {
			centerPanel.add(panel);
			panel.updateToolTip();
		}

		centerPanel.repaint();
		centerPanel.revalidate();
	}

	public static void setInformation(String vehicleInf) {
		vehicleInfo.setText(vehicleInf);
		topPanel.repaint();
	}

	public static boolean agencyCreated() {
		return created;
	}

	public static void setDisableTestButton() {
		testButton.setEnabled(false);
	}

	public static void setEnableTestButton() {
		testButton.setEnabled(true);
	}

	public static void setEnableColorButton() {
		colorButton.setEnabled(true);
	}

	public static void setDisableColorButton() {
		colorButton.setEnabled(false);
	}

	public static void setDisableBuyButton() {
		buyButton.setEnabled(false);
	}

	public static void setEnableBuyButton() {
		buyButton.setEnabled(true);
	}

	public static void setDisableSaveButton() {
		saveButton.setEnabled(false);
	}

	public static void setEnableSaveButton() {
		saveButton.setEnabled(true);
	}

	public static void setDisableLoadButton() {
		loadButton.setEnabled(false);
	}

	public static void setEnableLoadButton() {
		loadButton.setEnabled(true);
	}

	public static void setEnableVisibleButtonPanel() {
		buttonPanel.setVisible(true);
	}

	public static void setDisableVisibleButtonPanel() {
		buttonPanel.setVisible(false);
	}

	void testDialog(VehiclePanel chosenPanel, JDialog waitMessage) {

		JFrame inputFrame = new JFrame();
		inputFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		inputFrame.setSize(300, 150);
		inputFrame.setResizable(false);
		inputFrame.setLocationRelativeTo(null);
		JPanel intPanel = new JPanel(new FlowLayout());
		JLabel intLabel = new JLabel("How long do you want your test drive to be:");
		intLabel.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField intText = new JTextField();
		PlainDocument document = (PlainDocument) intText.getDocument();
		intText.setPreferredSize(new Dimension(200, 30));
		intText.setHorizontalAlignment(SwingConstants.CENTER);
		document.setDocumentFilter(new IntFilter());
		intPanel.setBackground(new Color(0xE1FFB1));

		intLabel.setBackground(new Color(0xE1FFB1));
		intLabel.setForeground(new Color(0xFF5D5D));
		intLabel.setOpaque(true);
		intText.setBackground(new Color(0xE1FFB1));
		intLabel.setVisible(true);
		intPanel.add(intText);
		intText.setVisible(true);

		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton confirmButton = new JButton();
		JButton cancelButton = new JButton();
		buttonPanel.setBackground(new Color(0xE1FFB1));
		confirmButton.setVisible(true);
		confirmButton.setText("Confirm");
		cancelButton.setVisible(true);
		cancelButton.setText("Cancel");
		buttonPanel.add(confirmButton);
		buttonPanel.add(cancelButton);

		inputFrame.add(intLabel, BorderLayout.NORTH);
		inputFrame.add(intPanel, BorderLayout.CENTER);
		inputFrame.add(buttonPanel, BorderLayout.SOUTH);

		confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (intText.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No information provided", "Error", JOptionPane.ERROR_MESSAGE);
					chosenPanel.setLiveStatus(false);
				} else {
					int drive = Integer.parseInt(intText.getText());

					Task driver = new Task(waitMessage);
					driver.setVehicle(chosenPanel.getVehicle());
					driver.UpdateDistance(drive);
					threadPool.execute(driver);
				}
				inputFrame.dispose();
			}
		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setEnableLoadButton();
				setEnableSaveButton();
				setChosenAvailable(chosenPanel, "Available");
				inputFrame.dispose();
			}
		});

		inputFrame.setVisible(true);

	}

	public void setChosenAvailable(VehiclePanel chosen, String state) {
		if (chosen.getVehicle() instanceof ColoredVehicle)
			((Vehicle) (((ColoredVehicle) chosen.getVehicle()).getVehicle())).setState(state);
		else
			((Vehicle) (chosen.getVehicle())).setState(state);
	}

	void runningProgressBar(long time, String name) {
		ProgressBar progressBar = new ProgressBar(name);
		progressBar.setVisible(true);
		long finishTime = System.currentTimeMillis() + 100L * time;
		progressBar.showProgress(finishTime);
	}

	public boolean tryExit() {

		boolean canExit = false;
		int i;
		int size = database.VehiclePanelArray.size();
		VehiclePanel currentPanel;

		for (i = 0; i < size; i++) {
			currentPanel = database.VehiclePanelArray.get(i);
			if (currentPanel.getVehicle().move(0))
				continue;
			else
				break;
		}
		if (i == size)
			canExit = true;
		return canExit;
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

	public static void updateTotalDistance(long distance) {
		totalDistanceLabel.setText("Total Distance Traveled: " + ((Long) distance).toString());
	}
}
