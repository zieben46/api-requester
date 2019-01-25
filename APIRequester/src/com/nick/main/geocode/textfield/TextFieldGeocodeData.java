package com.nick.main.geocode.textfield;

import com.nick.main.DataType;

public class TextFieldGeocodeData implements DataType {
	private static final String type = "GEOCODE_TEXTFIELD";

	@Override
	public String getType() {
		return type;
	}
}
