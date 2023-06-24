// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package Graphic;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ComboImages extends JComboBox<Icon> {
    public ComboImages(String[] imgList, String path) {
        //this.setPreferredSize(new Dimension(200,120));
        this.setModel(loadImages(imgList, path));
    }
    private DefaultComboBoxModel<Icon> loadImages(String[] list, String filePath) {
        File currentDirFile = new File(filePath);
        String helper = currentDirFile.getAbsolutePath();

        DefaultComboBoxModel<Icon> dm = new DefaultComboBoxModel<>();

        for (String name : list) {
            ImageIcon icon = new ImageIcon(helper + "/" + name);

            Image image = icon.getImage();
            Image newImage = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);

            icon = new ImageIcon(newImage);
            dm.addElement(icon);
        }
        return dm;
    }

}
