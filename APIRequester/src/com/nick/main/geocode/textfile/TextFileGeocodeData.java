package com.nick.main.geocode.textfile;

import com.nick.main.DataType;

public class TextFileGeocodeData implements DataType {
	private static final String type = "GEOCODE_TEXTFILE";
	private String filePath;

	public TextFileGeocodeData(String filePath) {
		this.filePath = filePath;
	}
	
	public String getfilePath() {
		return filePath;
	}

	@Override
	public String getType() {
		return type;
	}
}
