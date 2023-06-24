// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package Graphic;

import javax.swing.Icon;

public class ImageText {
	
	private Icon img;
	private String text;
	
	public ImageText(Icon img,String text) {
		
		this.img = img;
		this.text= text.toUpperCase();
	}

	public Icon getImg() {
		return img;
	}

	public void setImg(Icon img) {
		this.img = img;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
