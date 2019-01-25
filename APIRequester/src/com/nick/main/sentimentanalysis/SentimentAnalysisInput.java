package com.nick.main.sentimentanalysis;

import java.util.List;
import java.util.Map;

import com.nick.main.Input;

public interface SentimentAnalysisInput extends Input {
	
	Map<Integer, List<String>> getInput();

}
