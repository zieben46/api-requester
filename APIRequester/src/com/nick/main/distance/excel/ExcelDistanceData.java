package com.nick.main.distance.excel;

import com.nick.main.DataType;

public class ExcelDistanceData implements DataType {
	private static final String type = "DISTANCE_EXCEL";
	private String filePath;

	public ExcelDistanceData(String filePath) {
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

