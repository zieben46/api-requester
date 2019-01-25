package com.nick.main.urlvalidator;

import java.util.Map;

import com.nick.main.Output;

public interface URLValidatorOutput extends Output {
	abstract void setResults(Map<String, Boolean> results);
}
