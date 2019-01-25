package com.nick.main.sentimentanalysis.excel;

import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import com.nick.main.DataType;


public class ExcelSentimentAnalysisData implements DataType {
	private static final String type = "SENTIMENT_ANALYSIS_EXCEL";
	private String filePath;
	
	public ExcelSentimentAnalysisData(String filePath) {
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
