package com.nick.main.geocode.textfile;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.nick.main.geocode.GeocodeOutput;

public class TextFileGeocodeOutput implements GeocodeOutput {
	public static final String pathMod = " Geocoded";
	private String outputFilePath;

	private List<String> addresses;
	Map<String, List<String>> coordsByAddress;

	
	public TextFileGeocodeOutput(TextFileGeocodeData dataType) {
		outputFilePath = dataType.getfilePath().replace(".txt", pathMod+".txt");
	}

	@Override
	public void documentOutput() throws Exception {
		List<String> lines = new ArrayList<String>();
		lines.add("Address"+"\t"+
				  "Latitude"+"\t"+
				  "Longitude"+"\t");
		
		for (String address: addresses) {
			String lat = coordsByAddress.get(address).get(0);
			String lng = coordsByAddress.get(address).get(1);
			lines.add(address+"\t"+
					  lat+"\t"+
					  lng+"\t");
		}
		
		Path file = Paths.get(outputFilePath);
		Files.write(file, lines, Charset.forName("UTF-8"));
		JOptionPane.showMessageDialog(null, "Process Complete!");
	}

	@Override
	public void catchException(Exception ex) {
	}

	@Override
	public void setResults(List<String> addresses, Map<String, List<String>> coordsByAddress) {
		this.addresses = addresses;
		this.coordsByAddress = coordsByAddress;
	}
}
