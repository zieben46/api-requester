package com.nick.main.geocode;

import java.util.List;

import com.nick.main.Input;

public abstract interface GeocodeInput extends Input {
	
	abstract List<String> getInput();

}
