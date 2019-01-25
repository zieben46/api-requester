package com.nick.main.urlvalidator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.nick.main.DataType;
import com.nick.main.Master;
import com.nick.main.StorageManager;
import com.nick.main.geocode.excel.ExcelGeocodeData;
import com.nick.main.geocode.textfield.TextFieldGeocodeData;
import com.nick.main.geocode.textfile.TextFileGeocodeData;
import com.nick.main.urlvalidator.textfield.TextFieldURLValidatorData;

public class URLValidatorGUI extends JFrame {
	private String apiKey;
	private JTextArea apiKeyDisplay;

	public URLValidatorGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(680, 270);
		setTitle("GeoCoder");
		setLocationRelativeTo(null);
		apiKey = StorageManager.getApiKey();
		Container pane = getContentPane();
		addButtons(pane);
		addDisplay(pane);
		addMenu();
	}

	private void addButtons(Container pane) {
		int height = 50;
		JPanel buttonPanel=new JPanel();
		buttonPanel.setPreferredSize(new Dimension(50, height));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		JButton backButton = new JButton("Back");
		backButton.setMaximumSize(new Dimension(50, height));
		buttonPanel.add(backButton);
		backButton.addActionListener(e -> backButtonPushed());

		JPanel spacerPanelLeft = new JPanel();
		buttonPanel.add(spacerPanelLeft);

		JButton changeApiKeyButton=new JButton("Change API Key");
		changeApiKeyButton.setMaximumSize(new Dimension(50, height));
		buttonPanel.add(changeApiKeyButton);
		changeApiKeyButton.addActionListener(e -> changeApiKey());

		JPanel spacerPanelRight = new JPanel();
		buttonPanel.add(spacerPanelRight);

		JButton textFieldButton=new JButton("Live Input");
		textFieldButton.setMaximumSize(new Dimension(100, height));
		buttonPanel.add(textFieldButton);
		textFieldButton.addActionListener(e -> textFieldButtonPushed());

		JPanel spacerPanelRight2 = new JPanel();
		buttonPanel.add(spacerPanelRight2);

		pane.add(buttonPanel, BorderLayout.SOUTH);
	}

	private void backButtonPushed() {
		dispose();
		Master.restart();
	}

	private void addDisplay(Container pane) {
		JPanel middlePanel=new JPanel();
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));

		JTextArea apiKeyTitle=new JTextArea();
		apiKeyTitle.setEditable(false);
		apiKeyTitle.setFont(new Font("Courier", 1, 25));
		apiKeyTitle.setSize(100, 10);
		apiKeyTitle.setText(" API Key:");

		apiKeyDisplay=new JTextArea();
		apiKeyDisplay.setFont(new Font("Courier", 1, 12));
		apiKeyDisplay.setEditable(false);
		apiKeyDisplay.setSize(100, 10);
		apiKeyDisplay.setText("  " + apiKey);

		

		middlePanel.add(apiKeyTitle);
		middlePanel.add(apiKeyDisplay);
		
		Color color = new Color(248, 248, 255);
		apiKeyTitle.setBackground(color);
		apiKeyDisplay.setBackground(color);

		pane.add(middlePanel,BorderLayout.CENTER);
		setVisible(true);
	}

	private void addMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");		
		menuBar.add(menu);

		JMenuItem aboutItem = new JMenuItem("About...");
		aboutItem.addActionListener(e -> new URLValidatorAbout());
		menu.add(aboutItem);
		setJMenuBar(menuBar);

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(e -> System.exit(0));
		menu.add(exitItem);
		setJMenuBar(menuBar);
	}

	private void changeApiKey() {
		StorageManager.setApiKey();
		apiKey = StorageManager.getApiKey();
		apiKeyDisplay.setText("  " + apiKey);
	}

	private void textFieldButtonPushed() {
		dispose();
		DataType datatype = new TextFieldURLValidatorData();
		Master.begin(datatype, apiKey);
	}
}
