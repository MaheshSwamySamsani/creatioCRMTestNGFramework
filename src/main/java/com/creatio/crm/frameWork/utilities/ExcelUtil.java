package com.creatio.crm.frameWork.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	// Store data into collections
	public static List<Map<String, String>> readData(String fileName, String sheetName) {

		List<Map<String, String>> data = new ArrayList<Map<String, String>>();

		try {

			// Read the Excel File
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\" + fileName);

			// Load the file into excel class
			XSSFWorkbook wb = new XSSFWorkbook(fis);

			// get the sheet to read the data
			XSSFSheet sh = wb.getSheet(sheetName);

			// Get the total rows and columns having data
			int totalRows = sh.getPhysicalNumberOfRows();
			int totalColumns = sh.getRow(0).getPhysicalNumberOfCells();

			// Start reading data using loops and store the data into List<Map<>>

			// Start from 2nd row till last row (1st row have column headers)
			for (int r = 1; r < totalRows; r++) {

				// Create the map to store each row data
				Map<String, String> rowData = new HashMap<String, String>();

				// Start from 1st column till last column
				for (int c = 0; c < totalColumns; c++) {
					String key = sh.getRow(0).getCell(c).getStringCellValue();
					String value = sh.getRow(r).getCell(c).getStringCellValue();
					rowData.put(key, value);
				}
				data.add(rowData);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	// Store data into Arrays
	public static String [] [] readExcelData(String fileName, String sheetName) {

		String [][] data = null;

		try {

			// Read the Excel File
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\" + fileName);

			// Load the file into excel class
			XSSFWorkbook wb = new XSSFWorkbook(fis);

			// get the sheet to read the data
			XSSFSheet sh = wb.getSheet(sheetName);

			// Get the total rows and columns having data
			int totalRows = sh.getPhysicalNumberOfRows();
			int totalColumns = sh.getRow(0).getPhysicalNumberOfCells();
			
			// Initialize array with specific length
			data = new String [totalRows-1][totalColumns]; //totalRows-1 because we are reducing column headers data

			// Start reading data using loops and store the data into List<Map<>>

			// Start from 2nd row till last row (1st row have column headers)
			for (int r = 1; r < totalRows; r++) {
			
				// Start from 1st column till last column
				for (int c = 0; c < totalColumns; c++) {
					data[r-1][c] = sh.getRow(r).getCell(c).getStringCellValue();
				}				

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

}
