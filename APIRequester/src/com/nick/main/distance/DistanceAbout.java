package com.nick.main.distance;

import javax.swing.JOptionPane;

public class DistanceAbout {
	
	public DistanceAbout() {
		JOptionPane.showMessageDialog(null,
		"<html><font size='5'><u>About Distance Calculator</u><br></font>"+
		"<br>"+
		"Calculates driving distance/time between 2 points using Google Distance Matrix API service.<br>"+
		"Each point can either be a lat/long pair, or an address/zip/general area.<br>"+
		"This program handles both Excel files and .txt files.<br>"+
		"If using the Excel version, the excel file should contain 2 columns of points with a header name on the first row.<br>"+
		"If using the .txt version, there should be 2 points per line, with the two points seperated by tab.<br>"+
		"DO NOT Use the Excel version if you have a large set of points (anything greater than around 5,000).<br>"+
		"DO Use the .txt version instead."+
		"</html>" ,"Distance Matrix About", 
		JOptionPane.PLAIN_MESSAGE);
	}
}
