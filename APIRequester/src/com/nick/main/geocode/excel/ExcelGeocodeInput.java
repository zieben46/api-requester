package com.nick.main.geocode.excel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nick.main.exceptions.DuplicateAddressException;
import com.nick.main.exceptions.FormulaInAddressException;
import com.nick.main.exceptions.InputException;
import com.nick.main.exceptions.NoAddressHeaderException;
import com.nick.main.geocode.GeocodeInput;

public class ExcelGeocodeInput implements GeocodeInput {
	private static final String addressHeader = "address";
	public static final String pathMod = " GeoCoded";

	private String filePath;
	private int numbRows;
	private int numbColumns;
	private int addressColumnIndex;
	private List<String> addresses;
	
	public ExcelGeocodeInput(ExcelGeocodeData dataType) {
		this.filePath = dataType.getfilePath();
	}

	@Override
	public void retrieveInput() throws IOException, InputException {
		File file = new File(filePath);
		createCopy(file);
		Sheet inputSheet = readSheet(file);
		numbRows = inputSheet.getLastRowNum();
		addressColumnIndex = addressColumnIndex(inputSheet);
		addresses = addressColumnIndex != -1 ? pullAddresses(inputSheet) : null;
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
		if (addressColumnIndex == -1) {throw new NoAddressHeaderException();}
		if (hasDuplicates(addresses)) {throw new DuplicateAddressException();}
		if (hasFormulas()) {throw new FormulaInAddressException();}	
	}
	
	private void createCopy(File file) throws IOException {
		File copiedFile = new File(filePath.replace(".xlsx", pathMod+".xlsx"));
		FileUtils.copyFile(file, copiedFile);
	}

	private int addressColumnIndex(Sheet sheet) {
		Row headerRow = sheet.getRow(0);
		numbColumns = headerRow.getLastCellNum();
		Function<Integer, String> columnByIndex = index -> getStringValue(headerRow.getCell(index)).toLowerCase();

		return IntStream.range(0, numbColumns)
				.filter(index -> columnByIndex.apply(index).equals(addressHeader))
				.findFirst()
				.orElse(-1);
	}

	private boolean hasFormulas() {
		return addresses.contains("Formula");
	}

	public List<String> pullAddresses(Sheet sheet) {
		return IntStream.rangeClosed(1, numbRows)
				.mapToObj(rowNumb -> addressByRowNumb(rowNumb, sheet))
				.collect(Collectors.toList());
	}

	private String addressByRowNumb(int rowNumb, Sheet sheet) {
		try {
			Row row = sheet.getRow(rowNumb);
			Cell cell = row.getCell(addressColumnIndex);
			String cellValue = getStringValue(cell);
			return cellValue != "Blank" ? cellValue : "Blank"+String.valueOf(rowNumb);
		} catch (NullPointerException e) {
			return "Blank"+String.valueOf(rowNumb);
		}
	}

	private String getStringValue(Cell cell) {
		if (cell == null) {	return "Blank";}
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
		if (ex instanceof NoAddressHeaderException) {JOptionPane.showMessageDialog(null, "Error:  \"Address\" column missing.");
		} else if (ex instanceof DuplicateAddressException) {JOptionPane.showMessageDialog(null, "Error:  Remove duplicate addresses.");
		} else if (ex instanceof FormulaInAddressException) {JOptionPane.showMessageDialog(null, "Error:  Remove formulas in address column.");
		} else if (ex instanceof IOException) {JOptionPane.showMessageDialog(null, "Error:  Unable to load/save Excel file");}
	}

	@Override
	public List<String> getInput() {
		return addresses;
	}

}
