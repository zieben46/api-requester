package com.nick.main.geocode;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;

public class GeocodeApiRequestTool {
	
	private static boolean invalidKey;
	private static Exception apiException;
	private static Exception unkownException;
	private LatLng latLong;


	public GeocodeApiRequestTool(String apiKey, String address)  {
		try {
			latLong = requestLatLong(apiKey, address);
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

	private LatLng requestLatLong(String apiKey, String address) throws Exception {
		if (address.length() >= 6 && address.substring(0, 5).equals("Blank")) {
			return null;
		}
		GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();
		
		
		GeocodingResult[] results =  GeocodingApi.geocode(context, address).await();
		Geometry geo = results[0].geometry;
	
		
		return geo.location;
	}

	public String getLat() {
		return latLong != null ? Double.toString(latLong.lat) : "";
	}

	public String getLng() {
		return latLong != null ? Double.toString(latLong.lng) : "";
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
