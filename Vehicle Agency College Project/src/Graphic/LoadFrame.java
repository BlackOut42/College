// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package Graphic;

import javax.swing.*;
import java.awt.*;

import static Graphic.VehicleAgency.caretaker;
import static Graphic.VehicleAgency.database;
import static  Graphic.VehicleAgency.setEnableVisibleButtonPanel;

public class LoadFrame extends JFrame {
    private static boolean initialized;
    private JButton firstIndexButton;
    private JButton secondIndexButton;
    private JButton thirdIndexButton;
    private JButton closeButton;
    private JButton saveButtonFromLast;
    private JLabel topLabel;
    public LoadFrame(JButton saveB) {
        if (!initialized) {
            initialized = true;
            saveButtonFromLast = saveB;
            createFrame();
        }
    }
    private void createFrame() {
        initializeComponents();
        setupLayout();
        setupListeners();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(300, 200);
        getContentPane().setBackground(new Color(0xE1FFB1));
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setTitle("Choose save state");
    }
    private void initializeComponents() {
        if (caretaker.getMemento(0) != null) {
            firstIndexButton = new JButton("Load " + caretaker.getMemento(0).toString());
        }
        else {
            firstIndexButton = new JButton("-NO SAVE-");
            firstIndexButton.setEnabled(false);
        }
        if (caretaker.getMemento(1) != null) {
            secondIndexButton = new JButton("Load " + caretaker.getMemento(1).toString());
        }
        else {
            secondIndexButton = new JButton("-NO SAVE-");
            secondIndexButton.setEnabled(false);
        }
        if (caretaker.getMemento(2) != null) {
            thirdIndexButton = new JButton("Load " + caretaker.getMemento(2).toString());
        }
        else {
            thirdIndexButton = new JButton("-NO SAVE-");
            thirdIndexButton.setEnabled(false);
        }
        closeButton = new JButton("CLOSE WINDOW");
        closeButton.setBackground(new Color(0xB6E388));
        closeButton.setForeground(new Color(0xFF5D5D));
        closeButton.setFocusable(false);

        firstIndexButton.setBackground(new Color(0xB6E388));
        firstIndexButton.setForeground(new Color(0xFF5D5D));
        firstIndexButton.setFocusable(false);

        secondIndexButton.setBackground(new Color(0xB6E388));
        secondIndexButton.setForeground(new Color(0xFF5D5D));
        secondIndexButton.setFocusable(false);

        thirdIndexButton.setBackground(new Color(0xB6E388));
        thirdIndexButton.setForeground(new Color(0xFF5D5D));
        thirdIndexButton.setFocusable(false);

        topLabel = new JLabel("Choose one of the following save states below");
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        topLabel.setBackground(new Color(0xE1FFB1));
        topLabel.setForeground(new Color(0xFF5D5D));
        topLabel.setOpaque(true);
    }
    private void setupLayout() {
        GridLayout newGL = new GridLayout(5, 1);
        setLayout(newGL);

        add(topLabel);
        add(firstIndexButton);
        add(secondIndexButton);
        add(thirdIndexButton);
        add(closeButton);
    }
    private void setupListeners() {
        firstIndexButton.addActionListener(e -> {
            loadStateFunction(0);
        });
        secondIndexButton.addActionListener(e -> {
            loadStateFunction(1);
        });
        thirdIndexButton.addActionListener(e -> {
            loadStateFunction(2);
        });
        closeButton.addActionListener(e -> {
        	setEnableVisibleButtonPanel();
            resetInitialization();
        });
    }
    private void loadStateFunction(int index) {
        String tempTitleName = caretaker.getMemento(index).toString();
        database.restore(caretaker.popMemento(index));
        VehicleAgency.updateCenterPanel();
        JOptionPane.showMessageDialog(null, tempTitleName + " Load has been successfully loaded", "Load successful", JOptionPane.INFORMATION_MESSAGE);
        resetInitialization();
    }
    private void resetInitialization() {
        initialized = false;
        saveButtonFromLast.setEnabled(true);
        dispose();
    }
}
