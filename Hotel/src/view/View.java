package view;

import java.awt.Dimension;

import javax.swing.JFrame;

public class View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2118299654730994785L;

	public View(){
		Dimension minimum = new Dimension(400, 400);
		Dimension maximum = new Dimension(800, 800);
		Dimension preferred = new Dimension(512, 512);
		
		setMinimumSize(minimum);
		setMaximumSize(maximum);
		setPreferredSize(preferred);
		
		setEnabled(true);
		setVisible(true);
		
		pack();
	}
}
