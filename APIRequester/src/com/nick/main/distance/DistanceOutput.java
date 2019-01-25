package com.nick.main.distance;

import java.util.List;
import java.util.Map;

import com.nick.main.Output;

public interface DistanceOutput extends Output {
	
	abstract void setResults(Map<Integer, List<String>> infoByRowNumb, List<String> pointsA, List<String> pointsB);

}
