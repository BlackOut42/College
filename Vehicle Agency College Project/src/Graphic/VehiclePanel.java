// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package Graphic;
import static System.DB.setChosen;
import static Graphic.VehicleAgency.setInformation;
import static Graphic.VehicleAgency.setEnableTestButton;
import static Graphic.VehicleAgency.setEnableBuyButton;
import static Graphic.VehicleAgency.setEnableColorButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.BorderFactory;

import javax.swing.JLabel;
import javax.swing.JPanel;

import DP.Decorator.VehicleInterface;
import VehicleStats.Vehicle;

public class VehiclePanel extends JPanel implements MouseListener{

	VehicleInterface vehicle;
	ImageText design;
	public boolean selected;
	boolean isLive;
	
	
	
	public VehiclePanel(VehicleInterface vehicle,ImageText design) {
		this.vehicle = vehicle;
		this.design = new ImageText(design.getImg(),design.getText());
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(55,55));
		this.setBorder(BorderFactory.createLineBorder(new Color(0xFCFFB2),5));
		JLabel iconJLabel = new JLabel();
		iconJLabel.setSize(50, 50);
		iconJLabel.setIcon(design.getImg());
		this.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		this.setAlignmentY(JPanel.CENTER_ALIGNMENT);
		this.add(iconJLabel);
		this.setVisible(true);
		this.addMouseListener(this);
		updateToolTip();
	}
	
	public VehicleInterface getVehicle() {
		return vehicle;
	}
	public void setVehicle(VehicleInterface vehicle) {this.vehicle = vehicle;}
	
	public ImageText getImageText()
	{
		return design;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		selected = true;
		setEnableBuyButton();
		setEnableTestButton();
		setEnableColorButton();
		this.setBorder(BorderFactory.createLineBorder(new Color(0xFF5D5D).brighter(),2));
		setChosen(this);
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {	
		setInformation("<html>" + this.vehicle.toString()+ "</html>" );	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setInformation("Vehicle Information");
	}
		

	public void setLiveStatus(boolean status)
	{
		isLive = status;
	}
	public boolean getLiveStatus() {return isLive;}
	
	public void updateToolTip() {
		String msg = vehicle.toString().replace(",", "<br>");
		String newMsg = "<html>" + msg + "</html>";
		this.setToolTipText(newMsg);
	}
	}


