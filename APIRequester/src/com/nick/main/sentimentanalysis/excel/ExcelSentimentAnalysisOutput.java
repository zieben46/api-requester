package com.nick.main.sentimentanalysis.excel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nick.main.sentimentanalysis.SentimentAnalysisOutput;

public class ExcelSentimentAnalysisOutput implements SentimentAnalysisOutput {
	public static final String pathMod = " Sentiment Analyzed";
	
	private Workbook workbook;
	private Sheet sheet;
	private File file;
	private int numbColumns;
	
	private String outputFilePath;
private List<String> varNames;
private Map<Integer, List<String>> sentimentByRowNumb;
	
	
	public ExcelSentimentAnalysisOutput(ExcelSentimentAnalysisData dataType) {
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
		numbColumns = sheet.getRow(0).getLastCellNum();
		setHeader();
		//printHeader(0, varNames, numbColumns);
	}
	
//	private void printHeader(int rowNumb, List<String> varNames,int numbColumns) {
//		Row row = sheet.getRow(rowNumb);
//		int colIndex = numbColumns + 1;
//		for (String varName: varNames) {
//			Cell scoreCell = row.createCell(colIndex);
//			Cell magnitudeCell = row.createCell(colIndex + 1);
//			scoreCell.setCellValue(varName+"_Score");
//			magnitudeCell.setCellValue(varName+"_Magnitude");
//			colIndex+=2;
//		}		
//	}
	
	private void setHeader() {
		List<String> header = new ArrayList<>();
		for (String varName: varNames) {
			String scoreHeader = varName+"_Score";
			String magnitudeHeader = varName+"_Magnitude";
			header.add(scoreHeader);
			header.add(magnitudeHeader);
		}	
		sentimentByRowNumb.put(0, header);
	}
	
	private void printBody() {
		Predicate<String> isDouble = str -> StringUtils.isNumeric(str.replaceFirst("-", "").replaceFirst("\\.", ""));
		for(Integer rowNumb: sentimentByRowNumb.keySet()) {
			Row row = sheet.getRow(rowNumb);
			int colIndex = numbColumns + 1;
			List<String> rowSentiment = sentimentByRowNumb.get(rowNumb);
			for (String numb: rowSentiment) {
				Cell numbCell = row.createCell(colIndex);
				if (isDouble.test(numb)) {
					numbCell.setCellValue(Double.valueOf(numb));
				} else {
					numbCell.setCellValue(numb);
				}
				colIndex++;
			}		
		}
	}


	@Override
	public void documentOutput() throws IOException {
		prepOutputFile();
		printBody();
		close();
		JOptionPane.showMessageDialog(null, "Process Complete!");		
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
	public void setResults(List<String> varNames, Map<Integer, List<String>> sentimentByRowNumb) {
		this.varNames = varNames;
		this.sentimentByRowNumb = sentimentByRowNumb;
	}
}
