package com.nick.main.urlvalidator;

import java.util.List;

import com.nick.main.Input;

public abstract interface URLValidatorInput extends Input {
	
	abstract List<String> getInput();

}
