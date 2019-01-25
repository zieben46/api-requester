package com.nick.main.distance.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nick.main.distance.DistanceInput;
import com.nick.main.exceptions.CanceledException;
import com.nick.main.exceptions.InputException;
import com.nick.main.exceptions.NoPositionHeaderException;

public class ExcelDistanceInput implements DistanceInput {
	public static final String pathMod = " DistanceCalculated";
	private String filePath;

	private int numbRows;
	private int numbColumns;
	private int[] point1ColIndex;
	private int[] point2ColIndex;

	private List<String> point1;
	private List<String> point2;

	public ExcelDistanceInput(ExcelDistanceData dataType) {
		this.filePath = dataType.getfilePath();
	}


	@Override
	public List<List<String>> getInput() {
		return Arrays.asList(point1, point2);
	}

	@Override
	public void catchException(Exception ex) {
		if (ex instanceof NoPositionHeaderException) {JOptionPane.showMessageDialog(null, "Error:  Failed to find specified column names");}
		else if (ex instanceof FileNotFoundException) {JOptionPane.showMessageDialog(null, "Error: "+ex);}
		else if (ex instanceof IOException) {JOptionPane.showMessageDialog(null, "Error:  Unable to load/save Excel file");}
	}

	@Override
	public void retrieveInput() throws IOException, InputException {
		String[] loc1ColName = getColName("first location");
		String[] loc2ColName = getColName("second location");

		File file = new File(filePath);
		createCopy(file);
		Sheet inputSheet = readSheet(file);
		numbRows = inputSheet.getLastRowNum();
		point1ColIndex = columnIndexesOf(inputSheet, loc1ColName);
		point2ColIndex =  columnIndexesOf(inputSheet, loc2ColName);
		point1 = point1ColIndex.length != 0 ? pullPoint(inputSheet, point1ColIndex) : null;
		point2 = point2ColIndex.length != 0 ? pullPoint(inputSheet, point2ColIndex) : null;
		testAddresses();
	}

	private void testAddresses() throws NoPositionHeaderException {
		if (point1 == null || point2 == null) {throw new NoPositionHeaderException();};
	}


	public List<String> pullPoint(Sheet sheet, int[] indexes) {
		List<String> firstList = IntStream.rangeClosed(1, numbRows)
				.mapToObj(rowNumb -> valueByRowCol(rowNumb,indexes[0], sheet))
				.collect(Collectors.toList());
		if (indexes.length == 2) {
			List<String> secondList = IntStream.rangeClosed(1, numbRows)
					.mapToObj(rowNumb -> valueByRowCol(rowNumb, indexes[1], sheet ))
					.collect(Collectors.toList());
			return joinedLatLong(firstList, secondList);
		} else {
			return cleaned(firstList);
		}
	}

	private List<String> cleaned(List<String> firstList) {
		return firstList.stream()
				.map(str -> str.replaceAll(" ", "+"))
				.collect(Collectors.toList());
	}


	private List<String> joinedLatLong(List<String> firstList, List<String> secondList) {
		return IntStream.range(0, firstList.size())
				.mapToObj(i -> firstList.get(i)+","+ secondList.get(i))
				.collect(Collectors.toList());
	}

	private String valueByRowCol(int rowNumb, int colNumb, Sheet sheet) {
		try {
			Row row = sheet.getRow(rowNumb);
			Cell cell = row.getCell(colNumb);
			String cellValue = getStringValue(cell);
			return cellValue != "Blank" ? cellValue : "Blank"+String.valueOf(rowNumb);
		} catch (NullPointerException e) {
			return "Blank"+String.valueOf(rowNumb);
		}
	}

	private int[] columnIndexesOf(Sheet sheet, String[] locationColName) {
		Row headerRow = sheet.getRow(0);
		numbColumns = headerRow.getLastCellNum();
		Function<Integer, String> colNameByIndex = index -> getStringValue(headerRow.getCell(index)).toLowerCase();
		Predicate<String> isALocColName = colName -> Arrays.asList(locationColName).contains(colName);
		Predicate<Integer> locColNameFilter = colIndex -> isALocColName.test(colNameByIndex.apply(colIndex));

		return IntStream.range(0, numbColumns)
				.peek(e -> colNameByIndex.apply(e))
				.filter(e -> locColNameFilter.test(e))
				.toArray();
	}

	private String getStringValue(Cell cell) {
		if (cell == null) {
			return "Blank";
		}
		switch (cell.getCellTypeEnum()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			return String.valueOf( cell.getNumericCellValue());
		case BOOLEAN:
			return Boolean.toString(cell.getBooleanCellValue());
		case FORMULA:
			return "Formula";
		default:
			return "Blank";
		}
	}

	private Sheet readSheet(File file) throws IOException {
		FileInputStream fs = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		fs.close();
		Sheet sheet = workbook.getSheetAt(0);
		workbook.close();
		return sheet;
	}

	private String[] getColName(String text) throws CanceledException {
		if (isLatLong(text)) {
			return latLongColNames(text);
		} else {
			return addressColName(text);
		}

	}

	private boolean isLatLong(String text) throws CanceledException {
		int isLatLong = JOptionPane.showConfirmDialog(null, "Is the "+text+" Lat/Long?");
		switch(isLatLong) {
		case 0: return true;
		case 1:	return false;
		default: throw new CanceledException();
		}
	}

	private String[] latLongColNames(String text) throws CanceledException {
		JTextField latTextField = new JTextField();
		JTextField lngTextField = new JTextField();

		Object[] message = {"Name of latitude Column:", latTextField,"Name of longitude Column:", lngTextField,};
		int option = JOptionPane.showConfirmDialog(null, message, "Lat/Long names:  "+text, JOptionPane.OK_CANCEL_OPTION);
		String latHeader = latTextField.getText().toLowerCase();
		String lngHeader = lngTextField.getText().toLowerCase();
		if (option == JOptionPane.OK_OPTION && !latHeader.equals("") && !lngHeader.equals("")) {
			return new String[]{latHeader, lngHeader};
		} else if (option == JOptionPane.CANCEL_OPTION) {
			throw new CanceledException();
		} else {
			return latLongColNames(text);
		}
	}

	private String[] addressColName(String text) throws CanceledException {
		String name = JOptionPane.showInputDialog(null, "Enter header name for "+text);
		if (name == null) {
			throw new CanceledException();
		} else if (!name.equals("")) {
			return new String[]{name.toLowerCase()};
		} else {
			return addressColName(text);
		}
	}

	private void createCopy(File file) throws IOException {
		File copiedFile = new File(filePath.replace(".xlsx", pathMod+".xlsx"));
		FileUtils.copyFile(file, copiedFile);
	}



}
