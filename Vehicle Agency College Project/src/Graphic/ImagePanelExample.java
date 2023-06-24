// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package Graphic;

import System.DB;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class ImagePanelExample extends JFrame {
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JButton buyButton;
    private JButton testButton;
    private JButton nullDistanceButton;
    private JButton flagButton;
    private JButton addVehicleButton;
    private JButton exitButton;
    private JLabel vehicleInfo;
    DB database;

    public ImagePanelExample() {
        super("Image Panel Example");
        topPanel = new JPanel(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(780,50));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        centerPanel.setBorder(BorderFactory.createEtchedBorder());
        
        bottomPanel = new JPanel(new GridLayout(1,2));
        buyButton = new JButton("Buy vehicle");
        buyButton.setEnabled(false);
        testButton = new JButton("Test drive");
        testButton.setEnabled(false);
        nullDistanceButton = new JButton("Nullify distance to all");
        flagButton = new JButton("Change flag to all");
        addVehicleButton = new JButton("Add vehicle");
        exitButton = new JButton("Exit");
        vehicleInfo = new JLabel();

        GridLayout newGridlayout = new GridLayout(2,3);
        newGridlayout.setHgap(10);
        newGridlayout.setVgap(10);
        JPanel buttonPanel = new JPanel(newGridlayout);

        buttonPanel.add(buyButton);
        buttonPanel.add(testButton);
        buttonPanel.add(nullDistanceButton);
        buttonPanel.add(flagButton);
        buttonPanel.add(addVehicleButton);
        buttonPanel.add(exitButton);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        setPreferredSize(new Dimension(800, 600));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        vehicleInfo.setText("TEST");
        vehicleInfo.setHorizontalAlignment(JLabel.CENTER);
        vehicleInfo.setVerticalAlignment(JLabel.TOP);
        vehicleInfo.setBorder(new LineBorder(Color.BLACK));
        topPanel.add(vehicleInfo, BorderLayout.CENTER);

        updateTopPanel(); //TODO: Implement function 'updateTopPanel()'

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == buyButton) {
                    //TODO: Remove selected element from database and notify user
                }
            }
        });

        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == testButton) {
                    //TODO: Update filed of selected element from database and notify user
                }
            }
        });

        nullDistanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == nullDistanceButton) {
                    //TODO: Update static attribute for all elements in database [nullifyDistance() in vehicle class]
                }
            }
        });

        flagButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addVehicleButton) {
                    //TODO: Change flag for all elements in database []
                }
            }
        });

        addVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == exitButton) {
                    //TODO: Go back to previous page
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == exitButton) {
                    System.exit(0);
                }
            }
        });
        


        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);


        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void updateTopPanel() {
        centerPanel.removeAll();

        for (VehiclePanel panel : database.VehiclePanelArray) {
            centerPanel.add(panel);
        }

        centerPanel.repaint();
    }


//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new ImagePanelExample();
//            }
//        });
//    }
}
