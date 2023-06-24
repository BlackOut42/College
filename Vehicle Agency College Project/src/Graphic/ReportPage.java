// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package Graphic;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.concurrent.Semaphore;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import System.DB;

public class ReportPage extends JFrame implements FocusListener{

	private static JPanel Panel;
	private static boolean created;
	public static boolean reportFocus;
	
	public ReportPage(Semaphore reportlock) {
		super("Report Page");
		created = true;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setBackground(new Color(0xE1FFB1));
		setSize(new Dimension(200,400));
		
		Panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		Panel.setPreferredSize(new Dimension(50, 50));
		Panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		Panel.setBackground(new Color(0xE1FFB1));
		
		JScrollBar scroller = new JScrollBar();
		JButton finishButton = new JButton("Finish");
		finishButton.setPreferredSize(new Dimension(20,30));
		finishButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				reportlock.release();
				created = false;
				dispose();
			}
		});
		Panel.add(scroller);
		add(Panel,BorderLayout.CENTER);
		add(finishButton,BorderLayout.SOUTH);
		
		
		Panel.setVisible(true);
		
		
		updateReportPanel();
		setVisible(true);

		}
	public static void updateReportPanel() {
		Panel.removeAll();

		for (VehiclePanel panel : DB.VehiclePanelArray) {
			Panel.add(panel);
		}

		Panel.repaint();
		Panel.revalidate();
	}
	@Override
	public void focusGained(FocusEvent e) {
		ReportPage.reportFocus =true;
		setAutoRequestFocus(reportFocus);
		updateReportPanel();
		Panel.repaint();
		Panel.revalidate();
		
	}
	@Override
	public void focusLost(FocusEvent e) {
		ReportPage.reportFocus =false;
		setAutoRequestFocus(reportFocus);
		
	}
	

}
