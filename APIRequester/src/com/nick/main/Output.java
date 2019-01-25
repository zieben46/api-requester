package com.nick.main;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.nick.main.distance.excel.ExcelDistanceData;
import com.nick.main.distance.excel.ExcelDistanceOutput;
import com.nick.main.distance.textfile.TextFileDistanceData;
import com.nick.main.distance.textfile.TextFileDistanceOutput;
import com.nick.main.geocode.excel.ExcelGeocodeData;
import com.nick.main.geocode.excel.ExcelGeocodeOutput;
import com.nick.main.geocode.textfield.TextFieldGeocodeOutput;
import com.nick.main.geocode.textfile.TextFileGeocodeData;
import com.nick.main.geocode.textfile.TextFileGeocodeOutput;
import com.nick.main.sentimentanalysis.excel.ExcelSentimentAnalysisData;
import com.nick.main.sentimentanalysis.excel.ExcelSentimentAnalysisOutput;
import com.nick.main.urlvalidator.textfield.TextFieldURLValidatorOutput;

public interface Output {
	abstract void documentOutput() throws Exception;
	abstract void catchException(Exception ex);
	
	public static Output createOutput(DataType dataType) {
		String type = dataType.getType();
		switch (type) {
		case "GEOCODE_EXCEL":
			return new ExcelGeocodeOutput((ExcelGeocodeData) dataType);
		case "GEOCODE_TEXTFIELD":
			return new TextFieldGeocodeOutput();
		case "GEOCODE_TEXTFILE":
			return new TextFileGeocodeOutput((TextFileGeocodeData) dataType);
		case "DISTANCE_EXCEL":
			return new ExcelDistanceOutput((ExcelDistanceData) dataType);
		case "DISTANCE_TEXTFILE":
			return new TextFileDistanceOutput((TextFileDistanceData) dataType);
		case "SENTIMENT_ANALYSIS_EXCEL":
			return new ExcelSentimentAnalysisOutput((ExcelSentimentAnalysisData) dataType);
		case "URL_VALIDATOR_TEXTFIELD":
			return new TextFieldURLValidatorOutput();
		}
		return null;
	}
}
