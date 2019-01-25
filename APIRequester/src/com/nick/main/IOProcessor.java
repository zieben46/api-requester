package com.nick.main;

import com.nick.main.distance.DistanceIOProcessor;
import com.nick.main.distance.DistanceInput;
import com.nick.main.distance.DistanceOutput;
import com.nick.main.geocode.GeocodeIOProcessor;
import com.nick.main.geocode.GeocodeInput;
import com.nick.main.geocode.GeocodeOutput;
import com.nick.main.sentimentanalysis.SentimentAnalysisIOProcessor;
import com.nick.main.sentimentanalysis.SentimentAnalysisInput;
import com.nick.main.sentimentanalysis.SentimentAnalysisOutput;
import com.nick.main.urlvalidator.URLValidatorIOProcessor;
import com.nick.main.urlvalidator.URLValidatorInput;
import com.nick.main.urlvalidator.URLValidatorOutput;

public interface IOProcessor {

	abstract void sendTestRequest() throws Exception;
	abstract void process() throws Exception;
	abstract void catchException(Exception ex);

	public static IOProcessor createProcessor(DataType dataType, String apiKey, Input input, Output output) {
		String type = dataType.getType();
		switch (type) {
		case "GEOCODE_EXCEL":
		case "GEOCODE_TEXTFIELD":
		case "GEOCODE_TEXTFILE":
			return new GeocodeIOProcessor(apiKey,(GeocodeInput) input,(GeocodeOutput) output);
		case "DISTANCE_EXCEL":
		case "DISTANCE_TEXTFILE":
			return new DistanceIOProcessor(apiKey,(DistanceInput) input,(DistanceOutput) output);
		case "SENTIMENT_ANALYSIS_EXCEL":
			return new SentimentAnalysisIOProcessor((SentimentAnalysisInput) input,(SentimentAnalysisOutput) output);
		case "URL_VALIDATOR_TEXTFIELD":
			return new URLValidatorIOProcessor(apiKey, (URLValidatorInput) input,(URLValidatorOutput) output);
		}
		return null;
	}
	
}
