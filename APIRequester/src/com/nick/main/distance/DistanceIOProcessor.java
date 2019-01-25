package com.nick.main.distance;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JOptionPane;

import com.google.maps.errors.ApiException;
import com.nick.main.IOProcessor;
import com.nick.main.PleaseWaitGUI;
import com.nick.main.exceptions.InvalidKeyException;
import com.nick.main.geocode.GeocodeApiRequestTool;

public class DistanceIOProcessor implements IOProcessor {
	private DistanceInput input;
	private DistanceOutput output;
	private String apiKey;

	public DistanceIOProcessor(String apiKey, DistanceInput input, DistanceOutput output) {
		this.apiKey = apiKey;
		this.input = input;
		this.output = output;
	}

	@Override
	public void process() throws Exception {
		List<List<String>> points = input.getInput();
		List<String> pointsA = points.get(0);
		List<String> pointsB = points.get(1);
		sendTestRequest();
		PleaseWaitGUI.open();
		Map<Integer, List<String>>  infoByRowNumb = getInfoByRowNumb(pointsA, pointsB);
		output.setResults(infoByRowNumb, pointsA, pointsB);
		PleaseWaitGUI.close();
	}
	
	@Override
	public void sendTestRequest() throws Exception {
		String[] testPair = new String[]{"Pewaukee+WI", "Denver+CO"};
		getInfo(testPair);
		
		if (DistanceApiRequestTool.getApiException() != null) {
			throw DistanceApiRequestTool.getApiException();
		} else if (DistanceApiRequestTool.invalidKey()) {
			throw new InvalidKeyException();
		} else if (DistanceApiRequestTool.getUnkownException() != null) {
			throw DistanceApiRequestTool.getUnkownException();
		}
		DistanceApiRequestTool.reset();
	}

	private Map<Integer, List<String>> getInfoByRowNumb(List<String> point1, List<String> point2)  {
		Function<Integer, String[]> pairByRowNumb = rowNumb -> new String[]{point1.get(rowNumb - 1), point2.get(rowNumb - 1)};
		
		return IntStream.range(1, point1.size() + 1)
						.parallel()
						.boxed()
						.collect(Collectors.toMap(rowNumb -> rowNumb, rowNumb -> getInfo(pairByRowNumb.apply(rowNumb))));
	}
	
	private List<String> getInfo(String[] pair) {
		String p1 = pair[0];
		String p2 = pair[1];
		DistanceApiRequestTool apiRequestTool = new DistanceApiRequestTool(apiKey, p1, p2);
		return apiRequestTool.getInfo();
	}

	@Override
	public void catchException(Exception ex) {
		if (ex instanceof ApiException) {JOptionPane.showMessageDialog(null,"TEST REQUEST FAILURE:  "+ex.getMessage());} 
		else if (ex instanceof InvalidKeyException) {JOptionPane.showMessageDialog(null,"TEST REQUEST FAILURE:  Invalid api key.");}
	}
}
