package com.nick.main.geocode;

import java.util.List;
import java.util.Map;

import com.nick.main.Output;

public interface GeocodeOutput extends Output {
	abstract void setResults(List<String> addresses, Map<String, List<String>> coordsByAddress);

}
