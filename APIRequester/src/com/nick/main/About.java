package com.nick.main;
import javax.swing.JOptionPane;

public class About {
	
	public About() {
		JOptionPane.showMessageDialog(null,
				"<html><font size='5'><u>About API Requester</u><br></font>"+
						"<br>"+
						"This program takes advantage of various Google APIs for quick data gathering.<br>"+
						"<br>"+
						"Various data gathering processes include:<br>"+
						"GeoCoder:<br>"+
						"Converts addresses into it's latitude/longitude coordinates using Google Maps Geocoding API.<br>"+ 
						" Distance Calculator:<br>"+
						"Calculate driving distance/time between 2 points using Google Distance Matrix API.<br>"+
						"Sentiment Analyzer:<br>"+
						"Score text with a net positive, neutral, or negative score using Google Sentiment Analysis API.<br>"+
						"<br>"+
						"This program requires a Google API key in order to work, however the key is free.<br>"+
						"<br>"+
						"By Nick Ziebert<br>"+
						"</html>" ,"GeoCoder", 
						JOptionPane.PLAIN_MESSAGE);
	}
}
