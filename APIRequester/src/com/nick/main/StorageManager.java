package com.nick.main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JOptionPane;

public class StorageManager {
	private static BufferedReader bufferedReader;
    private static FileWriter filewriter;
    private static String apiKey;
    
    public static void load() {
        try {
        	bufferedReader = new BufferedReader(new FileReader(new File("API Requester Storage.txt")));
            apiKey = bufferedReader.readLine();
            bufferedReader.close();
        } catch (Exception e) {
            setApiKey();
            load();
        }
    }
    
    public static void setApiKey() {
        String newApi = null;        
        do {
        	newApi = JOptionPane.showInputDialog(null, "Set Google API key");  
        } while ("".equals(newApi));
        if (newApi != null) {
        	apiKey = newApi;
        }
        save();
    }
    
    private static void save() {
        try {
        	filewriter = new FileWriter(new File("API Requester storage.txt"));
        	filewriter.write(apiKey);
        	filewriter.close();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"An unexpected error has occured while saving...");   
        }  
    }
    
    public static String getApiKey() {
        return apiKey;
    }
}
