package com.nick.main.distance.textfile;

import com.nick.main.DataType;

public class TextFileDistanceData implements DataType {
	private static final String type = "DISTANCE_TEXTFILE";
	private String filePath;

	public TextFileDistanceData(String filePath) {
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
