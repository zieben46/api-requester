package com.nick.main.sentimentanalysis;

import javax.swing.JOptionPane;

public class SentimentAbout {

	public SentimentAbout() {
		JOptionPane.showMessageDialog(null,
		"<html><font size='5'><u>About Sentiment Analyzer</u><br></font>"+
		"<br>"+
		"Sentiment Analysis requires a file .json file saved onto drive in able to work.<br>"+
		" See Sentiment analysis help at google.com<br>"+
		"</html>" ,"GeoCoder", 
		JOptionPane.PLAIN_MESSAGE);
	}
}
