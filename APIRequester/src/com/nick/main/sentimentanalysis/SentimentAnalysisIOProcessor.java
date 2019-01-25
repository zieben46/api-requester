package com.nick.main.sentimentanalysis;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.google.maps.errors.ApiException;
import com.nick.main.IOProcessor;
import com.nick.main.PleaseWaitGUI;
import com.nick.main.exceptions.InvalidKeyException;

public class SentimentAnalysisIOProcessor implements IOProcessor {
	private SentimentAnalysisInput input;
	private SentimentAnalysisOutput output;

	public SentimentAnalysisIOProcessor (SentimentAnalysisInput input, SentimentAnalysisOutput output) {
		this.input = input;
		this.output = output;
	}

	@Override
	public void process() throws Exception {
		Map<Integer, List<String>> commentsByRowNumb = input.getInput();
		List<String> varNames = commentsByRowNumb.get(0);
		sendTestRequest();
		PleaseWaitGUI.open();
		Map<Integer, List<String>> sentimentByRowNumb = getSentimentByRowNumb(commentsByRowNumb);
		output.setResults(varNames, sentimentByRowNumb);
		PleaseWaitGUI.close();
	}

	@Override
	public void sendTestRequest() throws Exception {
		String test = "The world is a great place.";
		new SentimentAnalysisApiRequestTool(test);
		
		if (SentimentAnalysisApiRequestTool.getApiException() != null) {
			throw SentimentAnalysisApiRequestTool.getApiException();
		} else if (SentimentAnalysisApiRequestTool.getUnkownException() != null) {
			throw SentimentAnalysisApiRequestTool.getUnkownException();
		}
		SentimentAnalysisApiRequestTool.reset();
	}
	
	private Map<Integer, List<String>> getSentimentByRowNumb(Map<Integer, List<String>> commentsByRowNumb)  {
		return  commentsByRowNumb.keySet()
				.stream()
				.parallel()
				.collect(Collectors.toMap(rowNumb -> rowNumb, rowNumb -> getSentiment(commentsByRowNumb.get(rowNumb))));
	}

	private List<String> getSentiment(List<String> comments) {
		List<String> scores = new ArrayList<>();
		for (String comment: comments) {
		SentimentAnalysisApiRequestTool apiRequestTool = new SentimentAnalysisApiRequestTool(comment);
		scores.add(apiRequestTool.getScore());
		scores.add(apiRequestTool.getMagnitude());
		}
		return scores;
	}

	@Override
	public void catchException(Exception ex) {
		if (ex instanceof ApiException) {JOptionPane.showMessageDialog(null,"TEST REQUEST FAILURE:  "+ex.getMessage());} 
		else if (ex instanceof InvalidKeyException) {JOptionPane.showMessageDialog(null,"TEST REQUEST FAILURE:  Invalid api key.");}
	}
}
