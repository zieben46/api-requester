package com.nick.main.distance;

import java.util.Arrays;
import java.util.List;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.Unit;

public class DistanceApiRequestTool {
	private List<String> info;
	private static boolean invalidKey;
	private static Exception apiException;
	private static Exception unkownException;

	public DistanceApiRequestTool(String apiKey, String point1, String point2) {
		try {
			info = requestInfo(apiKey, point1, point2);
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

	private List<String> requestInfo(String apiKey, String point1, String point2) throws Exception {
		if (point1.length() == 0 || point2.length() == 0) {
			return null;
		}
		
		GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();
		DistanceMatrix dm = DistanceMatrixApi.getDistanceMatrix(context, new String[]{point1}, new String[]{point2}).units(Unit.IMPERIAL).await();
		DistanceMatrixRow[] rows = dm.rows;
		DistanceMatrixRow dmRow = rows[0];
		DistanceMatrixElement[] dmElementRows = dmRow.elements;
		DistanceMatrixElement dmElement = dmElementRows[0];
		String distMiles = dmElement.distance != null? dmElement.distance.humanReadable: "";
		String duratSeconds = dmElement.duration != null? dmElement.duration.inSeconds+"" : "";
		String duratSecondsInTraffic = dmElement.durationInTraffic != null ? dmElement.durationInTraffic.inSeconds+"" : "";
		String fare = dmElement.fare != null? dmElement.fare.currency.toString() : "";
		return Arrays.asList(distMiles, duratSeconds, duratSecondsInTraffic, fare);
	}

	public List<String> getInfo() {
		return info;
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
