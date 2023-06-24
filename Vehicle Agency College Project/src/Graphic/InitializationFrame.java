// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package Graphic;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.DataBuffer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import System.DB;
import VehicleTypes.Jeep;

public class InitializationFrame extends JFrame implements ActionListener {

	JLabel titleText;

	JPanel panel1;
	JLabel panel1Text;

	MyButtons createButton;
	MyButtons finishButton;

	public InitializationFrame() {

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.setLayout(new BorderLayout(0,0));
		this.setSize(450, 200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setBackground(new Color(0xE1FFB1));

		this.setTitle("Vehicle Agency");

		titleText = new JLabel("Welcome to our vehicle agency!");
		titleText.setFont(new Font("Helvetica", Font.BOLD, 24));
		titleText.setForeground(new Color(0xFF5D5D));// set font color
		titleText.setOpaque(true);
		titleText.setHorizontalAlignment(JLabel.CENTER);
		titleText.setBorder(new LineBorder(new Color(0xE1FFB1),10));
		
		titleText.setBackground(new Color(0xE1FFB1));

		panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		panel1.setPreferredSize(new Dimension(200,200));
		panel1.setBounds(0, 0, 400, 200);
		panel1.setBackground(new Color(0xE1FFB1));
		panel1.setVisible(true);

		panel1Text = new JLabel("Choose one of the following:");
		panel1Text.setFont(new Font("Helvetica", Font.BOLD,24));
		panel1Text.setHorizontalAlignment(JLabel.CENTER);
		panel1.add(panel1Text,BorderLayout.NORTH);
		
		createButton = new MyButtons("Create new vehicle",0,0);
		createButton.addActionListener(this);
        createButton.setBackground(new Color(0xB6E388));
        createButton.setForeground(new Color(0xFF5D5D));
		
		finishButton = new MyButtons("Finish",0,0);
		finishButton.addActionListener(this);
        finishButton.setBackground(new Color(0xB6E388));
        finishButton.setForeground(new Color(0xFF5D5D));
		finishButton.setEnabled(false);
		
		
		panel1.add(createButton);
		panel1.add(finishButton);
		
		

		this.add(titleText,BorderLayout.NORTH);
		this.add(panel1,BorderLayout.CENTER);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		

		String src = e.getSource().toString();
		switch (src) 
		{	
		case "Create new vehicle": {
			
			new VehicleCreator();
			finishButton.setEnabled(true);
			break;
		}
		case "Finish":
		{
			System.out.println("Finish");
			VehicleAgency.getInstance();
			dispose();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + e.getSource());
		}
		

	}


}
