package com.nick.main.geocode;

import javax.swing.JOptionPane;

public class GeocodeAbout {
	
	public GeocodeAbout() {
		JOptionPane.showMessageDialog(null,
		"<html><font size='5'><u>About GeoCoder</u><br></font>"+
		"<br>"+
		"The GeoCoder converts addresses into it's latitude/longitude coordinates using Google Maps Geocoding API service.<br>"+ 
		"<br>"+
		"<u>How to Use GeoCoder</u><br>"+ 
		"1.  Obtain a Google Geocoding API Key, it's free.<br>"+ 
		"2.  Store addresses anywhere on the <u>first</u> worksheet in an Excel .xlsx file, labeling the first row \"Address\".<br>"+
		"3.  Addresses should try to follow the form: 8908 Ashcroft Drive N. Richland Hills, Texas 76120<br>"+
		"4.  Run this program on the .xlsx Excel file.<br>"+
		"5.  Once complete, look for the newly generated .xlsx file located in the same folder as the original .xlsx file.<br>"+
		"<br>"+
		"* Reading a .txt file has been added.  Save each address 1 row per line in a .txt file.  Use this instead of Excel if cases exceed 5,000.<br>"+
		"<br>"+
		"<u>Also...<br></u>"+
		"- due to Google API, GeoCoder may take up to 8 minutes to convert 2,500 requests<br>"+
		"- the .xlsx file can contain much more data, not just an address column.<br>"+
		"- the API key provides up to 2,500 requests/day for free, additional requests cost $1/2,000 requests<br>"+
		"- Google's address lookup is smart, if an address doesn't contain a zip code or if it's misspelled, the lookup may still work<br>"+
		"- this program can handle more than street addresses.  A list of zip codes or business names can also be used to obtain<br>"+
		"latitude/longitude"+
		"</html>" ,"GeoCoder", 
		JOptionPane.PLAIN_MESSAGE);
		
	}

}
