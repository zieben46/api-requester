package com.nick.main.sentimentanalysis.excel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nick.main.exceptions.CanceledException;
import com.nick.main.exceptions.FormulaInAddressException;
import com.nick.main.exceptions.InputException;
import com.nick.main.exceptions.NoAddressHeaderException;
import com.nick.main.sentimentanalysis.SentimentAnalysisInput;

public class ExcelSentimentAnalysisInput implements SentimentAnalysisInput {
	public static final String pathMod = " Sentiment Analyzed";

	private String filePath;
	private int numbRows;
	private int numbColumns;
	private List<String> varNames;
	private Map<String, Integer> columnIndexByVarName;
	private Map<Integer, List<String>> commentsByRow;
	
	public ExcelSentimentAnalysisInput(ExcelSentimentAnalysisData dataType) {
		this.filePath = dataType.getfilePath();
		this.varNames = askVarNames();
		varNames.forEach(var -> var.toLowerCase());
	}
	
	private List<String> askVarNames() {
		String varNamesString = JOptionPane.showInputDialog(null, "List the variable names, seperated by a space");
		if (varNamesString == null) {
			return Arrays.asList("CANCELED");
		} else if (varNamesString.equals("")) {
			return askVarNames();
		} else {
			return Arrays.asList(varNamesString.split(" +"));
		}
	}

	@Override
	public void retrieveInput() throws IOException, InputException {
		if (varNames.equals(Arrays.asList("CANCELED"))) {throw new CanceledException();}
		File file = new File(filePath);
		createCopy(file);
		Sheet inputSheet = readSheet(file);
		numbRows = inputSheet.getLastRowNum();
		
		columnIndexByVarName = columnIndexByVarName(inputSheet);
		commentsByRow = columnIndexByVarName.size() != 0 ? pullComments(inputSheet) : null;
		testAddresses();
	}
	
	private Sheet readSheet(File file) throws IOException {
		FileInputStream fs = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		fs.close();
		Sheet sheet = workbook.getSheetAt(0);
		workbook.close();
		return sheet;
	}

	private void testAddresses() throws InputException {
		if (commentsByRow == null) {throw new NoAddressHeaderException();}
		if (hasFormulas()) {throw new FormulaInAddressException();}	
	}
	
	private void createCopy(File file) throws IOException {
		File copiedFile = new File(filePath.replace(".xlsx", pathMod+".xlsx"));
		FileUtils.copyFile(file, copiedFile);
	}

	private Map<String, Integer> columnIndexByVarName(Sheet sheet) {
		Row headerRow = sheet.getRow(0);
		numbColumns = headerRow.getLastCellNum();
		
		Map<String, Integer> output = new HashMap<>();
		for (int i = 0; i < numbColumns; i ++) {
			String varName = headerRow.getCell(i).getStringCellValue().toLowerCase();
			if (varNames.contains(varName)) {
				output.put(varName, i);
			}
		}
				return output;		
	}

	private boolean hasFormulas() {
		return commentsByRow.values().contains("Formula");
	}

	public Map<Integer, List<String>> pullComments(Sheet sheet) {
		return IntStream.rangeClosed(0, numbRows)
				.boxed()
				.collect(Collectors.toMap(rowNumb -> rowNumb, rowNumb -> commentsByRowNumb(rowNumb, sheet)));
	}

	private List<String> commentsByRowNumb(int rowNumb, Sheet sheet) {
		List<String> output = new ArrayList<>();
		try {
			Row row = sheet.getRow(rowNumb);
			for (String varName: varNames) {
			Cell cell = row.getCell(columnIndexByVarName.get(varName));
			String cellValue = getStringValue(cell);
			output.add(cellValue);
			}
			return output;
		} catch (NullPointerException e) {
		}
		return output;
	}

	private String getStringValue(Cell cell) {
		if (cell == null) {
			return "Blank";
		}
		switch (cell.getCellTypeEnum()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			return String.valueOf((int) cell.getNumericCellValue());
		case BOOLEAN:
			return Boolean.toString(cell.getBooleanCellValue());
		case FORMULA:
			return "Formula";
		default:
			return "Blank";
		}
	}

	@Override
	public void catchException(Exception ex) {
		if (ex instanceof NoAddressHeaderException) {JOptionPane.showMessageDialog(null, "Error:  Specified column names not found.");
		} else if (ex instanceof FormulaInAddressException) {JOptionPane.showMessageDialog(null, "Error:  Remove formulas comments");
		} else if (ex instanceof IOException) {JOptionPane.showMessageDialog(null, "Error:  Unable to load/save Excel file");}
	}

	@Override
	public Map<Integer, List<String>> getInput() {
		return commentsByRow;
	}
}
