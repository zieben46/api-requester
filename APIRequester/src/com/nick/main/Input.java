package com.nick.main;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nick.main.distance.excel.ExcelDistanceData;
import com.nick.main.distance.excel.ExcelDistanceInput;
import com.nick.main.distance.textfile.TextFileDistanceData;
import com.nick.main.distance.textfile.TextFileDistanceInput;
import com.nick.main.geocode.excel.ExcelGeocodeData;
import com.nick.main.geocode.excel.ExcelGeocodeInput;
import com.nick.main.geocode.textfield.TextFieldGeocodeInput;
import com.nick.main.geocode.textfile.TextFileGeocodeData;
import com.nick.main.geocode.textfile.TextFileGeocodeInput;
import com.nick.main.sentimentanalysis.excel.ExcelSentimentAnalysisInput;
import com.nick.main.urlvalidator.textfield.TextFieldURLValidatorData;
import com.nick.main.urlvalidator.textfield.TextFieldURLValidatorInput;
import com.nick.main.sentimentanalysis.excel.ExcelSentimentAnalysisData;

public interface Input {
	
	abstract void catchException(Exception ex);
	abstract void retrieveInput() throws Exception;
	
	default boolean hasDuplicates(List<String> addresses) {
		Set<String> set = new HashSet<String>(addresses);
		return set.size() != addresses.size();
	}
	
	 public static Input createInput(DataType dataType) {
		String type = dataType.getType();
		switch (type) {
		case "GEOCODE_EXCEL":
			return new ExcelGeocodeInput((ExcelGeocodeData) dataType);
		case "GEOCODE_TEXTFIELD":
			return new TextFieldGeocodeInput();
		case "GEOCODE_TEXTFILE":
			return new TextFileGeocodeInput((TextFileGeocodeData) dataType);
		case "DISTANCE_EXCEL":
			return new ExcelDistanceInput((ExcelDistanceData) dataType);
		case "DISTANCE_TEXTFILE":
			return new TextFileDistanceInput((TextFileDistanceData) dataType);
		case "SENTIMENT_ANALYSIS_EXCEL":
			return new ExcelSentimentAnalysisInput((ExcelSentimentAnalysisData) dataType);
		case "URL_VALIDATOR_TEXTFIELD":
			return new TextFieldURLValidatorInput();
		}
		return null;
	}
}