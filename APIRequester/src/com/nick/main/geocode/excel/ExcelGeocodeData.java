package com.nick.main.geocode.excel;

import com.nick.main.DataType;


public class ExcelGeocodeData implements DataType {
	private static final String type = "GEOCODE_EXCEL";
	private String filePath;
	
	public ExcelGeocodeData(String filePath) {
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
