package com.nick.main.sentimentanalysis;
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

import com.nick.main.About;
import com.nick.main.DataType;
import com.nick.main.Master;
import com.nick.main.StorageManager;
import com.nick.main.sentimentanalysis.excel.ExcelSentimentAnalysisData;

public class SentimentAnalyzerGUI extends JFrame {
	private static String filePath = "M:\\Data";
	//private String apiKey;
	private JTextArea filePathDisplay;
	private JTextArea apiKeyDisplay;

	public SentimentAnalyzerGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(680, 270);
		setTitle("Sentiment Analyzer");
		setLocationRelativeTo(null);
		//apiKey = StorageManager.getApiKey();
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
		
//		JButton changeApiKeyButton=new JButton("Change ????");
//		changeApiKeyButton.setMaximumSize(new Dimension(50, height));
//		buttonPanel.add(changeApiKeyButton);
//		changeApiKeyButton.addActionListener(e -> changeApiKey());
//		
//		JPanel spacerPanelRight = new JPanel();
//		buttonPanel.add(spacerPanelRight);
				
		JButton changeFilePathButton=new JButton("Select Excel file");
		changeFilePathButton.setMaximumSize(new Dimension(50, height));
		buttonPanel.add(changeFilePathButton);
		changeFilePathButton.addActionListener(e -> selectExcelFile());
		
		JButton sentimentAnalysisButton=new JButton("Begin Sentiment Analyzer...");
		sentimentAnalysisButton.setMaximumSize(new Dimension(100, height));
		buttonPanel.add(sentimentAnalysisButton);
		sentimentAnalysisButton.addActionListener(e -> sentimentAnalysisButtonPressed());
		
		pane.add(buttonPanel, BorderLayout.SOUTH);
	}

	private void backButtonPushed() {
		
		dispose();
		Master.restart();
	}

	private void addDisplay(Container pane) {
		JPanel leftPanel=new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

		JTextArea apiKeyTitle=new JTextArea();
		apiKeyTitle.setEditable(false);
		apiKeyTitle.setFont(new Font("Courier", 1, 25));
		apiKeyTitle.setSize(100, 10);
		apiKeyTitle.setText(" API Key:");
		leftPanel.add(apiKeyTitle);

		apiKeyDisplay=new JTextArea();
		apiKeyDisplay.setFont(new Font("Courier", 1, 12));
		apiKeyDisplay.setEditable(false);
		apiKeyDisplay.setSize(100, 10);
		apiKeyDisplay.setText("No key required.  Use google json for permission");
		leftPanel.add(apiKeyDisplay);

		JTextArea filePathTitle=new JTextArea();
		filePathTitle.setEditable(false);
		filePathTitle.setFont(new Font("Courier", 1, 25));
		filePathTitle.setText(" Excel File: ");
		leftPanel.add(filePathTitle);

		filePathDisplay=new JTextArea();
		filePathDisplay.setFont(new Font("Courier", 1, 12));
		filePathDisplay.setEditable(false);
		filePathDisplay.setText("  " + filePath);
		leftPanel.add(filePathDisplay);

		JTextArea nullArea=new JTextArea();

		Color color = new Color(248, 248, 255);
		apiKeyTitle.setBackground(color);
		apiKeyDisplay.setBackground(color);
		filePathTitle.setBackground(color);
		filePathDisplay.setBackground(color);
		nullArea.setBackground(color);

		pane.add(leftPanel,BorderLayout.CENTER);
		
//		JPanel rightPanel=new JPanel();
//		JTextArea title=new JTextArea();
//		title.setFont(new Font("Courier", 1, 25));
//		title.setText("Sentiment Analyzer");
//		rightPanel.add(title);
//		pane.add(rightPanel,BorderLayout.EAST);

		setVisible(true);
	}
	
	private void addMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");		
		menuBar.add(menu);

		JMenuItem aboutItem = new JMenuItem("About...");
		aboutItem.addActionListener(e -> new SentimentAbout());
		menu.add(aboutItem);
		setJMenuBar(menuBar);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(e -> System.exit(0));
		menu.add(exitItem);
		setJMenuBar(menuBar);
	}

	private void selectExcelFile() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".xlsx", "xlsx");
		JFileChooser fc = new JFileChooser(filePath);
		fc.setFileFilter(filter);
		fc.showOpenDialog(null);
		try {
			filePath = fc.getSelectedFile().getAbsolutePath();
		} catch (NullPointerException e) {}

		filePathDisplay.setText("  "+filePath);
	}

//	private void changeApiKey() {
//		StorageManager.setApiKey();
//		apiKey = StorageManager.getApiKey();
//		apiKeyDisplay.setText("  " + apiKey);
//	}

	private void sentimentAnalysisButtonPressed() {
		if (filePath != "") {
			dispose();
			DataType datatype = new ExcelSentimentAnalysisData(filePath);
			Master.begin(datatype, "");
		}
	}
	
	public String getFilePath() {
		return filePath;
	}
}
