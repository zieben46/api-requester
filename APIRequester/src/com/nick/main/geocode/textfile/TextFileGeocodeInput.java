package com.nick.main.geocode.textfile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.nick.main.geocode.GeocodeInput;

public class TextFileGeocodeInput implements GeocodeInput {
	private List<String> addresses = new ArrayList<>();
	private String filePath;
	
	BufferedReader br;

	public TextFileGeocodeInput(TextFileGeocodeData dataType) {
		this.filePath = dataType.getfilePath();
	}

	@Override
	public void retrieveInput() throws Exception {
		br = new BufferedReader(new FileReader(filePath));
		String currLine;
		while ((currLine = br.readLine()) != null) {
			addresses.add(currLine);
			}
		br.close();
		}
		
	@Override
	public void catchException(Exception ex) {
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//catch other exceptions
	}


	@Override
	public List<String> getInput() {
		return addresses;
	}
}


