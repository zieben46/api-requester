package com.nick.main.geocode.textfield;
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

public class TextFieldGeocodeOutput implements GeocodeOutput {
	private List<String> addresses;
	Map<String, List<String>> coordsByAddress;

	public TextFieldGeocodeOutput() {

	}

	@Override
	public void setResults(List<String> addresses, Map<String, List<String>> coordsByAddress) {
		this.addresses = addresses;
		this.coordsByAddress = coordsByAddress;
	}

	@Override
	public void documentOutput() {
			Function<String, String> addressToLat = address -> coordsByAddress.get(address).get(0);
			Function<String, String> addressToLng = address -> coordsByAddress.get(address).get(1);
			Function<String, String[]> addressToData = address -> new String[]{address, addressToLat.apply(address), addressToLng.apply(address)};
			String[][] data = addresses.stream()
					.map(address -> addressToData.apply(address))
					.toArray(String[][]::new);
			//new DisplayGUI(data);
			displayResults(data);
	}

	private void displayResults(String[][] data) {
			JFrame frame = new JFrame(); 
			frame.setTitle("GeoCoder results");
			String header[] = {"Address","Latitude","Longitude"};
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
