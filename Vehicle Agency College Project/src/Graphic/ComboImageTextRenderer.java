// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package Graphic;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ComboImageTextRenderer extends JLabel implements ListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList list, Object val, int index, boolean selected,
			boolean focused) {

		// Get Values
		ImageText imageText = (ImageText) val;

		// Set Icon
		setIcon(imageText.getImg());
		setText(imageText.getText());

		if (selected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else{

			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		setFont(list.getFont());
		

		return this;
	}



}
