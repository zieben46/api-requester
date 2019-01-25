package com.nick.main.sentimentanalysis;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.google.maps.errors.ApiException;

public class SentimentAnalysisApiRequestTool {

	private static Exception apiException;
	private static Exception unkownException;
	private Sentiment sentiment;

	public SentimentAnalysisApiRequestTool(String comment)  {
		if (!comment.equals("Blank")) {
			try {
				sentiment = requestSentiment(comment);
			} catch (Exception ex) {
				if (ex instanceof ApiException) {
					apiException = ex;
				} else {
					unkownException = ex;
				}
			}
		}
	}

	private Sentiment requestSentiment(String comment) throws Exception {
		try (LanguageServiceClient language = LanguageServiceClient.create()) {
			Document doc = Document.newBuilder()
					.setContent(comment).setType(Type.PLAIN_TEXT).build();
			return language.analyzeSentiment(doc).getDocumentSentiment();
		}
	}


	public String getScore() {
		return sentiment != null ? Double.toString(sentiment.getScore()) : "";
	}

	public String getMagnitude() {
		return sentiment != null ? Double.toString(sentiment.getMagnitude()) : "";
	}
	
	public static void reset() {
		apiException = null;
		unkownException = null;
	}

	public static Exception getApiException() {
		return apiException;
	}
	
	public static Exception getUnkownException() {
		return unkownException;
	}

}
