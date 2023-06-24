// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package Graphic;

import javax.swing.JButton;

public class MyButtons extends JButton {

	public MyButtons(String text, int xPosition, int yPosition) {

		this.setText(text);

		this.setBounds(xPosition, yPosition, 150, 100);
		this.setFocusable(false);

	}

	@Override
	public String toString() {

		return getText();
	}
}
