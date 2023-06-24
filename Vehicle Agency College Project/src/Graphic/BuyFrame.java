// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package Graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.desktop.ScreenSleepEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.MissingFormatArgumentException;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Graphic.VehiclePanel;

import System.DB;
import VehicleStats.Vehicle;

import static Graphic.VehicleAgency.setDisableBuyButton;
import static Graphic.VehicleAgency.setDisableTestButton;
import static Graphic.VehicleAgency.setEnableLoadButton;
import static Graphic.VehicleAgency.setEnableSaveButton;
import static Graphic.VehicleAgency.setEnableColorButton;


public class BuyFrame extends JFrame implements ActionListener {
	JLabel titleText;
	JPanel panel1;
	JLabel panel1Text;

	VehiclePanel wantedVehicle;
	VehicleAgency callerAgency;
	DB database;

	MyButtons confirmButton;
	MyButtons cancelButton;
	Thread heart;
	public static ArrayList<VehiclePanel> vehicleList;

	public BuyFrame(VehicleAgency callerAgency, DB database, VehiclePanel vehicle) {
		super("Buy Menu");

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.setLayout(new BorderLayout(0, 0));
		this.setSize(450, 200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setBackground(new Color(0xE1FFB1));

		this.setTitle("Purchase confirmation");

		setDisableBuyButton();
		setDisableTestButton();

		wantedVehicle = vehicle;
		this.callerAgency = callerAgency;

		titleText = new JLabel("Thank you for using our agency!");
		titleText.setFont(new Font("Helvetica", Font.BOLD, 22));
		titleText.setForeground(new Color(0xFF5D5D));// set font color
		titleText.setOpaque(true);
		titleText.setHorizontalAlignment(JLabel.CENTER);
		titleText.setBorder(new LineBorder(new Color(0xE1FFB1), 10));

		titleText.setBackground(new Color(0xE1FFB1));

		panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		panel1.setPreferredSize(new Dimension(200, 200));
		panel1.setBounds(0, 0, 400, 400);
		panel1.setBackground(new Color(0xE1FFB1));
		panel1.setVisible(true);

		panel1Text = new JLabel("Choose one of the following:");
		panel1Text.setFont(new Font("Helvetica", Font.BOLD, 24));
		panel1Text.setHorizontalAlignment(JLabel.CENTER);
		panel1.add(panel1Text, BorderLayout.NORTH);

		confirmButton = new MyButtons("Confirm Purchase", 0, 0);
		confirmButton.addActionListener(this);
		confirmButton.setBackground(new Color(0xB6E388));
		confirmButton.setForeground(new Color(0xFF5D5D));

		cancelButton = new MyButtons("Cancel", 0, 0);
		cancelButton.addActionListener(this);
		cancelButton.setBackground(new Color(0xB6E388));
		cancelButton.setForeground(new Color(0xFF5D5D));

		panel1.add(confirmButton);
		panel1.add(cancelButton);

		this.add(titleText, BorderLayout.NORTH);
		this.add(panel1, BorderLayout.CENTER);

		new Thread(() -> {
			int sleepFor = getRandomNumber(5, 10);
			System.out.println("Sleeping for :" + sleepFor);
			try {
				Thread.sleep(1000 * sleepFor);
			} catch (InterruptedException e) {
				// nothing
			}
			this.setVisible(true);

		}).start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new Thread(() -> {
			String src = e.getSource().toString();
			switch (src) {
			case "Confirm Purchase": {
				JDialog d = createUpdateDialog();
					try {
						Thread.sleep(1000 * getRandomNumber(3, 8));
						d.dispose();
					} catch (InterruptedException e2) {
					}
					if (database.VehiclePanelArray.contains(wantedVehicle)) {
						wantedVehicle.updateToolTip();
						database.VehiclePanelArray.remove(wantedVehicle);
						callerAgency.updateCenterPanel();
					} else {
						JOptionPane.showMessageDialog(null, "Error! " + "\n" + "The vehicle does not exit anymore!",
								"Vehicle is gone!", JOptionPane.ERROR_MESSAGE);
					}
				callerAgency.setChosenAvailable(wantedVehicle,"Available");
				setEnableLoadButton();
				setEnableSaveButton();
				setEnableColorButton();
				dispose();
				break;
			}
			case "Cancel": {
				callerAgency.setChosenAvailable(wantedVehicle,"Available");
				wantedVehicle.updateToolTip();
				setEnableLoadButton();
				setEnableSaveButton();
				setEnableColorButton();
				dispose();
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + e.getSource());
			}
		}).start();

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
