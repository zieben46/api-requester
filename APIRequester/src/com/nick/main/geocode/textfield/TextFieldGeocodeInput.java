package com.nick.main.geocode.textfield;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.nick.main.exceptions.CanceledException;
import com.nick.main.exceptions.InputException;
import com.nick.main.geocode.GeocodeInput;

public class TextFieldGeocodeInput implements GeocodeInput {
	private List<String> addresses;

	public TextFieldGeocodeInput() {
		
	}

	@Override
	public void retrieveInput() throws InputException {
		JTextArea textArea = new JTextArea();
		textArea.setColumns(30);
		textArea.setRows(30);
		textArea.setLineWrap(true);
		int submit = JOptionPane.showConfirmDialog(null, new JScrollPane(textArea), "Input a list of addresses, one per row.",
				JOptionPane.OK_CANCEL_OPTION);
		String addressesText =  textArea.getText();
		if (submit == 0 && !addressesText.isEmpty()) {
			addresses = Arrays.asList(addressesText.split("\r|\n"));
		} else if (submit == 0 && addressesText.isEmpty()) {
			retrieveInput();
		} else {
			addresses = Arrays.asList("");
			throw new CanceledException();
		}
	}

	@Override
	public List<String> getInput() {
		return addresses;
	}

	@Override
	public void catchException(Exception ex) {
		if (ex instanceof CanceledException) {}
		else if (ex instanceof NullPointerException) {}
		else if (hasDuplicates(addresses)) {JOptionPane.showMessageDialog(null, "Error:  Remove duplicate addresses.");}
		}
	}
