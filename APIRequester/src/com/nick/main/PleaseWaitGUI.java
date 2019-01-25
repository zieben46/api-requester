package com.nick.main;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PleaseWaitGUI extends JPanel {
	private static JFrame frame;

	private PleaseWaitGUI() {		
		frame = new JFrame("Making API requests, Please wait.......");
		frame.setSize(new Dimension(300, 200));
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
	}

	public static void open() {
		new PleaseWaitGUI();
	}
	

	public static void close() {
		frame.dispose();
	}
}