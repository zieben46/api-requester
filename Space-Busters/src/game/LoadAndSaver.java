package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

public class LoadAndSaver {
	private static BufferedReader reader;
	private static FileWriter fw;
	private static String line;
	private static String levelNumber="1";

	public static int Load() {
		try {
			reader=new BufferedReader(new FileReader(new File("storage.txt")));
			levelNumber=reader.readLine();
			reader.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR:  NO SAVE FILE FOUND");
		}		
		return Integer.parseInt(levelNumber);	
	}

	public static void Save(int levelNumber) {
		try {
			fw = new FileWriter(new File("storage.txt"));
			fw.write(String.valueOf(levelNumber));
			fw.close();
			JOptionPane.showMessageDialog(null,"SAVE SUCCESSFUL");
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null,"AN UNEXPECTED ERROR HAS OCCURED WHILE SAVING...");   
		}  
	}
}
