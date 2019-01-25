package com.nick.main.urlvalidator.textfield;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.nick.main.geocode.GeocodeOutput;
import com.nick.main.urlvalidator.URLValidatorOutput;

public class TextFieldURLValidatorOutput implements URLValidatorOutput {
	Map<String, Boolean> results;

	public TextFieldURLValidatorOutput() {
	}

	@Override
	public void setResults(Map<String, Boolean> results) {
		this.results = results;
	}
	

	@Override
	public void documentOutput() {
			String[][] data = results.keySet()
					.stream()
					.map(result -> new String[]{result, String.valueOf(results.get(result))})
					.toArray(String[][]::new);
			displayResults(data);
	}

	private void displayResults(String[][] data) {
			JFrame frame = new JFrame(); 
			frame.setTitle("GeoCoder results");
			String header[] = {"URL","Valid?"};
			JTable jTable = new JTable(data, header);
			jTable.setBounds(30, 40, 600, 600);          
			JScrollPane sp = new JScrollPane(jTable);    
			frame.add(sp);          
			frame.setSize(600, 800);    
			frame.setLocationRelativeTo(null);
			frame.setAlwaysOnTop(true);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent windowEvent) {
					if (confirmedExit(frame)){
						frame.dispose();
					}
				}
			});
	}

	private boolean confirmedExit(JFrame frame) {
		int submit = JOptionPane.showConfirmDialog(frame, "Close?", "?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		return submit == 0;
	}

	@Override
	public void catchException(Exception ex) {
	}



//	public void sleep() {
//		try {
//			Thread.sleep(300);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

}
