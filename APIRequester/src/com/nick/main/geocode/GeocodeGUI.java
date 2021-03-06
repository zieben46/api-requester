package com.nick.main.geocode;
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

public class GeocodeGUI extends JFrame {
	private static String filePath = "M:\\Data";
	private String apiKey;
	private JTextArea filePathDisplay;
	private JTextArea apiKeyDisplay;

	public GeocodeGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(680, 270);
		setTitle("GeoCoder");
		setLocationRelativeTo(null);
		apiKey = StorageManager.getApiKey();
		Container pane = getContentPane();
		addButtons(pane);
		addDisplay(pane);
		addMenu();
		filePathDisplay.setText("  "+filePath);
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

		JButton changeFilePathButton=new JButton("Select Excel or .txt File");
		changeFilePathButton.setMaximumSize(new Dimension(50, height));
		buttonPanel.add(changeFilePathButton);
		changeFilePathButton.addActionListener(e -> selectExcelFile());

		JButton geocodeBeginButton=new JButton("Begin GeoCoding File...");
		geocodeBeginButton.setMaximumSize(new Dimension(100, height));
		buttonPanel.add(geocodeBeginButton);
		geocodeBeginButton.addActionListener(e ->beginButtonPressed());

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

		JTextArea filePathTitle=new JTextArea();
		filePathTitle.setEditable(false);
		filePathTitle.setFont(new Font("Courier", 1, 25));
		filePathTitle.setText(" Excel or .txt File: ");

		filePathDisplay=new JTextArea();
		filePathDisplay.setFont(new Font("Courier", 1, 12));
		filePathDisplay.setEditable(false);
		filePathDisplay.setText("  " + filePath);

		JTextArea nullArea=new JTextArea();

		middlePanel.add(apiKeyTitle);
		middlePanel.add(apiKeyDisplay);
		middlePanel.add(filePathTitle);
		middlePanel.add(filePathDisplay);
		middlePanel.add(nullArea);

		Color color = new Color(248, 248, 255);
		apiKeyTitle.setBackground(color);
		apiKeyDisplay.setBackground(color);
		filePathTitle.setBackground(color);
		filePathDisplay.setBackground(color);
		nullArea.setBackground(color);

		pane.add(middlePanel,BorderLayout.CENTER);
		setVisible(true);
	}

	private void addMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");		
		menuBar.add(menu);

		JMenuItem aboutItem = new JMenuItem("About...");
		aboutItem.addActionListener(e -> new GeocodeAbout());
		menu.add(aboutItem);
		setJMenuBar(menuBar);

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(e -> System.exit(0));
		menu.add(exitItem);
		setJMenuBar(menuBar);
	}

	private void selectExcelFile() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".xlsx, .txt","txt", "xlsx");
		JFileChooser fc = new JFileChooser(filePath);
		fc.setFileFilter(filter);
		fc.showOpenDialog(null);
		try {
			filePath = fc.getSelectedFile().getAbsolutePath();
		} catch (NullPointerException e) {}

		filePathDisplay.setText("  "+filePath);
	}

	private void changeApiKey() {
		StorageManager.setApiKey();
		apiKey = StorageManager.getApiKey();
		apiKeyDisplay.setText("  " + apiKey);
	}

	private void beginButtonPressed() {
			if (filePath.endsWith(".xlsx")) {
				dispose();
				DataType datatype = new ExcelGeocodeData(filePath);
				Master.begin(datatype, apiKey);
			} else if (filePath.endsWith(".txt")) {
				dispose();
				DataType datatype = new TextFileGeocodeData(filePath);
				Master.begin(datatype, apiKey);
			}
	}

	private void textFieldButtonPushed() {
		dispose();
		DataType  datatype = new TextFieldGeocodeData();
		Master.begin(datatype, apiKey);
	}

	public String getFilePath() {
		return filePath;
	}
}
