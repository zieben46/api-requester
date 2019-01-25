package com.nick.main;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.nick.main.distance.DistanceGUI;
import com.nick.main.geocode.GeocodeGUI;
import com.nick.main.sentimentanalysis.SentimentAnalyzerGUI;
import com.nick.main.urlvalidator.URLValidatorGUI;

public class APIChooserGUI extends JFrame {

	public APIChooserGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 290);
		setTitle("API Requester");
		setLocationRelativeTo(null);
		Container pane = getContentPane();
		addButtons(pane);
		addDisplay(pane);
		addMenu();
		setVisible(true);
	}
	
	private void addButtons(Container pane) {
		int height = 50;
		JPanel buttonPanel=new JPanel();
		buttonPanel.setPreferredSize(new Dimension(50, height));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		JButton geocodeButton = new JButton("GeoCoder");
		geocodeButton.setMaximumSize(new Dimension(50, height));
		buttonPanel.add(geocodeButton);
		geocodeButton.addActionListener(e -> geocodeButtonPushed());

		JPanel spacerPanelRight1 = new JPanel();
		buttonPanel.add(spacerPanelRight1);
		
		JButton drivingCalcButton=new JButton("Driving distance/time Calculator");
		drivingCalcButton.setMaximumSize(new Dimension(100, height));
		buttonPanel.add(drivingCalcButton);
		drivingCalcButton.addActionListener(e -> drivingCalcButtonPushed());
		
		JPanel spacerPanelRight2 = new JPanel();
		buttonPanel.add(spacerPanelRight2);
		
		JButton urlValidatorButton=new JButton("URL Validator");
		urlValidatorButton.setMaximumSize(new Dimension(100, height));
		buttonPanel.add(urlValidatorButton);
		urlValidatorButton.addActionListener(e -> urlvldtrButtonPushed());
		
		JPanel spacerPanelRight3 = new JPanel();
		buttonPanel.add(spacerPanelRight3);
		
		JButton sentimentAnalyzerButton=new JButton("Sentiment Analyzer");
		sentimentAnalyzerButton.setMaximumSize(new Dimension(50, height));
		buttonPanel.add(sentimentAnalyzerButton);
		sentimentAnalyzerButton.addActionListener(e -> sentimentAnalyzerButtonPushed());
		
		pane.add(buttonPanel, BorderLayout.SOUTH);
	}

	private void addDisplay(Container pane) {
		JPanel middlePanel=new JPanel();
		
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
		
		JTextArea title=new JTextArea();
		title.setEditable(false);
		title.setFont(new Font("Courier", 1, 45));
		title.setSize(100, 10);
		title.setText(" API Requester");
		middlePanel.add(title);

		pane.add(middlePanel);
	}

	private void sentimentAnalyzerButtonPushed() {
		dispose();
		new SentimentAnalyzerGUI();
	}

	private void drivingCalcButtonPushed() {
		dispose();
		new DistanceGUI();
	}

	private void geocodeButtonPushed() {
		dispose();
		new GeocodeGUI();
	}
	
	private void urlvldtrButtonPushed() {
		dispose();
		new URLValidatorGUI();
	}

	private void addMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");		
		menuBar.add(menu);

		JMenuItem aboutItem = new JMenuItem("About...");
		aboutItem.addActionListener(e -> new About());
		menu.add(aboutItem);
		setJMenuBar(menuBar);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(e -> System.exit(0));
		menu.add(exitItem);
		setJMenuBar(menuBar);
	}
}
