package com.nick.main.urlvalidator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.google.maps.errors.ApiException;
import com.nick.main.IOProcessor;
import com.nick.main.PleaseWaitGUI;
import com.nick.main.exceptions.InvalidKeyException;

public class URLValidatorIOProcessor implements IOProcessor {
	private URLValidatorInput input;
	private URLValidatorOutput output;
	private String apiKey;

	public URLValidatorIOProcessor(String apiKey, URLValidatorInput input, URLValidatorOutput output) {
		this.apiKey = apiKey;
		this.input = input;
		this.output = output;
	}

	@Override
	public void process() throws Exception {
		List<String> urls = input.getInput();
		//sendTestRequest();
		PleaseWaitGUI.open();
		Map<String, Boolean> results = testURLS(urls);
		output.setResults(results);
		PleaseWaitGUI.close();
	}

	@Override
	public void sendTestRequest() throws Exception {
		String test = "4272 Rivermoor Road";
		new URLValidatorApiRequestTool(apiKey, test);
		
		if (URLValidatorApiRequestTool.getApiException() != null) {
			throw URLValidatorApiRequestTool.getApiException();
		} else if (URLValidatorApiRequestTool.invalidKey()) {
			throw new InvalidKeyException();
		} else if (URLValidatorApiRequestTool.getUnkownException() != null) {
			throw URLValidatorApiRequestTool.getUnkownException();
		}
		URLValidatorApiRequestTool.reset();
	}
	
	private Map<String, Boolean> testURLS(List<String> urls)  {
		Map<String, Boolean> results = new HashMap<>();
		for (String url: urls) {
			results.put(url, testURL(url));
			try {
				Thread.sleep(1010);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return results;
//		
//		return  urls.stream()
//				//.parallel()
//				.collect(Collectors.toMap(url -> url, url -> testURL(url)));
	}

	private Boolean testURL(String address) {
		URLValidatorApiRequestTool apiRequestTool = new URLValidatorApiRequestTool(apiKey, address);
		return apiRequestTool.isValid();
	}

	@Override
	public void catchException(Exception ex) {
		if (ex instanceof ApiException) {JOptionPane.showMessageDialog(null,"TEST REQUEST FAILURE:  "+ex.getMessage());} 
		else if (ex instanceof InvalidKeyException) {JOptionPane.showMessageDialog(null,"TEST REQUEST FAILURE:  Invalid api key.");}
	}
}
