package com.nick.main.urlvalidator;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.gax.rpc.ApiException;


public class URLValidatorApiRequestTool {
	
	private static boolean invalidKey;
	private static Exception apiException;
	private static Exception unkownException;
	private boolean valid;

	public URLValidatorApiRequestTool(String apiKey, String url)  {
		
		try {
			valid = testURL(apiKey, url);
		} catch (Exception ex) {
			if (ex instanceof ApiException) {
				apiException = ex;
			} else if (ex instanceof IllegalStateException) {
					invalidKey = true;
			} else {
				unkownException = ex;
			}
		}
	}
	
	public static void main(String[] args) {
		String apiKey = "AIzaSyBZpEgGQ_IPHNExJfMrAMnrHbT76QON-PY";
		String url =  "http://www.yahoo.com";
		new URLValidatorApiRequestTool(apiKey, url);
		
	}

	private boolean testURL(String apiKey, String url) throws Exception {
//		if (!(url.contains("https://")||url.contains("http://"))) {
//			url = "https://"+url;
//		}

	        HttpTransport httpTransport = new NetHttpTransport();
	        HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
	        GenericUrl args = new GenericUrl(
	            "https://searchconsole.googleapis.com/v1/urlTestingTools/mobileFriendlyTest:run"
	            + "?key="+apiKey);
	        JSONObject json = new JSONObject();
	        json.put("url", url);
	        String requestBody = json.toString();
	        HttpRequest request = requestFactory.buildPostRequest(
	            args, ByteArrayContent.fromString("application/json", requestBody));
	        //System.out.println("request was: " + requestBody);
	        HttpResponse httpResponse = request.setReadTimeout(70000).execute();
	        String content = httpResponse.parseAsString();
	        System.out.println("-----------------------------------------------");
	        System.out.println(url);
	        System.out.println(content);
	        System.out.println(content.contains("\"status\": \"COMPLETE\""));
	        return content.contains("\"status\": \"COMPLETE\"");
	}

	public boolean isValid() {
		return valid;
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

	public static boolean invalidKey() {
		return invalidKey;
	}	
}
