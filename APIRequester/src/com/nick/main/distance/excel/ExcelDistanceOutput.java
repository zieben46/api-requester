package com.nick.main.distance.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nick.main.distance.DistanceOutput;
import com.nick.main.distance.textfile.TextFileDistanceData;

public class ExcelDistanceOutput implements DistanceOutput {

	public static final String pathMod = " DistanceCalculated";

	private Workbook workbook;
	private Sheet sheet;
	private File file;
	private String outputFilePath;
	private int beginColumn;
	private Map<Integer, List<String>> infoByRowNumb;
	
	public ExcelDistanceOutput(ExcelDistanceData dataType) {
		outputFilePath = dataType.getfilePath().replace(".xlsx", pathMod+".xlsx");
	}

	private Workbook getWorkbook() throws IOException {
		FileInputStream fs = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(fs);
		fs.close();
		return workbook;
	}
	
	@Override
	public void documentOutput() throws IOException {
		prepOutputFile();
		for (int rowNumb = 1; rowNumb <= infoByRowNumb.size(); rowNumb++) {
			List<String> info = infoByRowNumb.get(rowNumb);
			printToRow(rowNumb, info);
		}

		close();
		JOptionPane.showMessageDialog(null, "Process Complete!");		
	}

	private void prepOutputFile() throws IOException {
		file = new File(outputFilePath);
		workbook = getWorkbook();
		sheet = workbook.getSheetAt(0);
		int numbColumns = sheet.getRow(0).getLastCellNum();
		beginColumn = numbColumns + 1;
		List<String> headers = Arrays.asList("distMiles", "duratSeconds", "duratSecondsInTraffic", "fare");
		printToRow(0, headers);
	}

	private void printToRow(int currRowIndex, List<String> info) {
		String distMiles = info.get(0);
		String duratSeconds =  info.get(1);
		String duratSecondsInTraffic =  info.get(2);
		String fare =  info.get(3);
		Row row = sheet.getRow(currRowIndex);
		Cell c1=row.createCell(beginColumn - 1);
		Cell c2=row.createCell(beginColumn);
		Cell c3=row.createCell(beginColumn + 1);
		Cell c4=row.createCell(beginColumn + 2);
		c1.setCellValue(distMiles);
		c2.setCellValue(duratSeconds);
		c3.setCellValue(duratSecondsInTraffic);
		c4.setCellValue(fare);
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
	public void setResults(Map<Integer, List<String>> infoByRowNumb, List<String> pointsA,List<String> pointsB) {
		this.infoByRowNumb = infoByRowNumb;
	}
}
