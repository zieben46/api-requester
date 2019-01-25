package com.nick.main.geocode.excel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nick.main.geocode.GeocodeOutput;


public class ExcelGeocodeOutput implements GeocodeOutput {
	public static final String pathMod = " GeoCoded";
	
	private Workbook workbook;
	private Sheet sheet;
	private File file;
	private int latColumnindex;
	private int lngColumnindex;
	
	private String outputFilePath;
private List<String> addresses;
private Map<String, List<String>> coordsByAddress;
	
	
	public ExcelGeocodeOutput(ExcelGeocodeData dataType) {
		outputFilePath = dataType.getfilePath().replace(".xlsx", pathMod+".xlsx");
	}
		
	private Workbook getWorkbook() throws IOException {
	    FileInputStream fs = new FileInputStream(file);
	    Workbook workbook = new XSSFWorkbook(fs);
	    fs.close();
	    return workbook;
	}
	
	private void prepOutputFile() throws IOException {
		file = new File(outputFilePath);
		workbook = getWorkbook();
		sheet = workbook.getSheetAt(0);
		int numbColumns = sheet.getRow(0).getLastCellNum();
		latColumnindex = numbColumns + 1;
		lngColumnindex = numbColumns + 2;
		printToRow(0, "Latitude", "Longitude");
	}
	
	@Override
	public void documentOutput() throws IOException {
		prepOutputFile();	
		Function<String, String> addressToLat = address -> coordsByAddress.get(address).get(0);
		Function<String, String> addressToLng = address -> coordsByAddress.get(address).get(1);
		
		int currRow = 1;
		for (String address: addresses) {
			String lat = addressToLat.apply(address);
			String lng = addressToLng.apply(address);
			printToRow(currRow, lat, lng);
			currRow++;
		}
		close();
		JOptionPane.showMessageDialog(null, "Process Complete!");		
	}

	private void printToRow(int currRowIndex, String lat, String lng) {
		Row row = sheet.getRow(currRowIndex);
		Cell c1=row.createCell(latColumnindex - 1);
		Cell c2=row.createCell(lngColumnindex - 1);
		Predicate<String> isDouble = str -> StringUtils.isNumeric(str.replaceFirst("-", "").replaceFirst("\\.", ""));
		if (isDouble.test(lat) && isDouble.test(lng)) {
			c1.setCellValue(Double.valueOf(lat));
			c2.setCellValue(Double.valueOf(lng));
		} else {
			c1.setCellValue(lat);
			c2.setCellValue(lng);
		}
	}
	
	private void close() throws IOException {
			FileOutputStream outFile = new FileOutputStream(file);
			workbook.write(outFile);
			outFile.close();
			workbook.close();
	}

	@Override
	public void catchException(Exception ex) {
	}

	@Override
	public void setResults(List<String> addresses, Map<String, List<String>> coordsByAddress) {
		this.addresses = addresses;
		this.coordsByAddress = coordsByAddress;
	}

}
