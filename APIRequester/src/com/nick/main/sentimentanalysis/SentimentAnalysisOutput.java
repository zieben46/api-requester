package com.nick.main.sentimentanalysis;

import java.util.List;
import java.util.Map;

import com.nick.main.Output;

public interface SentimentAnalysisOutput extends Output {
	abstract void setResults(List<String> varNames, Map<Integer, List<String>> sentimentByRowNumb);
}
