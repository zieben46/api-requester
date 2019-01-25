package com.nick.main.urlvalidator.textfield;

import com.nick.main.DataType;

public class TextFieldURLValidatorData implements DataType {
	private static final String type = "URL_VALIDATOR_TEXTFIELD";

	@Override
	public String getType() {
		return type;
	}
}
