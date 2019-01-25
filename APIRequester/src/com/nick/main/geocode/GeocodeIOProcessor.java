package com.nick.main.geocode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.google.maps.errors.ApiException;
import com.nick.main.IOProcessor;
import com.nick.main.PleaseWaitGUI;
import com.nick.main.exceptions.InvalidKeyException;

public class GeocodeIOProcessor implements IOProcessor {
	private GeocodeInput input;
	private GeocodeOutput output;
	private String apiKey;

	public GeocodeIOProcessor(String apiKey, GeocodeInput input, GeocodeOutput output) {
		this.apiKey = apiKey;
		this.input = input;
		this.output = output;
	}

	@Override
	public void process() throws Exception {
		List<String> addresses = input.getInput();
		sendTestRequest();
		PleaseWaitGUI.open();
		Map<String, List<String>> coordinatesByAddress = getCoordinatesByAddress(addresses);
		output.setResults(addresses, coordinatesByAddress);
		PleaseWaitGUI.close();
	}

	@Override
	public void sendTestRequest() throws Exception {
		String test = "4272 Rivermoor Road";
		new GeocodeApiRequestTool(apiKey, test);
		
		if (GeocodeApiRequestTool.getApiException() != null) {
			throw GeocodeApiRequestTool.getApiException();
		} else if (GeocodeApiRequestTool.invalidKey()) {
			throw new InvalidKeyException();
		} else if (GeocodeApiRequestTool.getUnkownException() != null) {
			throw GeocodeApiRequestTool.getUnkownException();
		}
		GeocodeApiRequestTool.reset();
	}
	
	private Map<String, List<String>> getCoordinatesByAddress(List<String> addresses)  {
		return  addresses.stream()
				.parallel()
				.collect(Collectors.toMap(address -> address, address -> getCoordinates(address)));
	}

	private List<String> getCoordinates(String address) {
		GeocodeApiRequestTool apiRequestTool = new GeocodeApiRequestTool(apiKey, address);
		String lat = apiRequestTool.getLat();
		String lng = apiRequestTool.getLng();
		return Arrays.asList(lat, lng);
	}

	@Override
	public void catchException(Exception ex) {
		if (ex instanceof ApiException) {JOptionPane.showMessageDialog(null,"TEST REQUEST FAILURE:  "+ex.getMessage());} 
		else if (ex instanceof InvalidKeyException) {JOptionPane.showMessageDialog(null,"TEST REQUEST FAILURE:  Invalid api key.");}
	}
}
