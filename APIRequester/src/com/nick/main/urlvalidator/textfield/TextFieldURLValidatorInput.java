package com.nick.main.urlvalidator.textfield;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.nick.main.exceptions.CanceledException;
import com.nick.main.exceptions.InputException;
import com.nick.main.urlvalidator.URLValidatorInput;

public class TextFieldURLValidatorInput implements URLValidatorInput {
	private List<String> urls;

	public TextFieldURLValidatorInput() {
		
	}

	@Override
	public void retrieveInput() throws InputException {
		JTextArea textArea = new JTextArea();
		textArea.setColumns(30);
		textArea.setRows(30);
		textArea.setLineWrap(true);
		int submit = JOptionPane.showConfirmDialog(null, new JScrollPane(textArea), "Input a list of urls, one per row.",
				JOptionPane.OK_CANCEL_OPTION);
		String addressesText =  textArea.getText();
		if (submit == 0 && !addressesText.isEmpty()) {
			urls = Arrays.asList(addressesText.split("\r|\n"));
		} else if (submit == 0 && addressesText.isEmpty()) {
			retrieveInput();
		} else {
			urls = Arrays.asList("");
			throw new CanceledException();
		}
	}

	@Override
	public List<String> getInput() {
		return urls;
	}

	@Override
	public void catchException(Exception ex) {
		if (ex instanceof CanceledException) {}
		else if (ex instanceof NullPointerException) {}
		else if (hasDuplicates(urls)) {JOptionPane.showMessageDialog(null, "Error:  Remove duplicate addresses.");}
		}
	}
