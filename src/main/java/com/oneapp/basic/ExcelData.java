package com.oneapp.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {

	public XSSFWorkbook wb;
	public File file;

	public ExcelData() {

		file = new File("./TestData/Data.xlsx");
		try {
			FileInputStream fis = new FileInputStream(file); // for converting the excel file into a RAW data.
			wb = new XSSFWorkbook(fis); // In order to read xlsx, we need XSSFworkbook class
		} catch (Exception e) {
			System.out.println("Unable to read excel file " + e.getMessage());
		}

	}

	public void WriteExceldata(String SR,String Kilometers, String SR_ID) {
//		XSSFSheet sheet3 = wb.createSheet("Book Service");
		XSSFSheet sheet3 = wb.getSheetAt(2);
		
		XSSFRow rowheading = sheet3.createRow(0);
		rowheading.createCell(0).setCellValue(SR);
		rowheading.createCell(1).setCellValue(Kilometers);
		for(int i=0; i<2; i++)
		{
			XSSFCellStyle stylerowheading = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setBold(true);
			font.setFontName(HSSFFont.FONT_ARIAL);
			font.setFontHeightInPoints((short) 11);
			stylerowheading.setFont(font);
			rowheading.getCell(i).setCellStyle(stylerowheading);
		}
		
		XSSFRow row = sheet3.createRow(1);
		row.createCell(0).setCellValue(SR_ID);
		
		
		for(int i=0; i<2; i++)
		{
			sheet3.autoSizeColumn(i);
		}
		
		try {
			FileOutputStream fos = new FileOutputStream(file);
			wb.write(fos);
			fos.close();
			wb.close();
			System.out.println("Excel written successfully");
		} catch (Exception e) {
			System.out.println("Unable to write on excel file " + e.getMessage());
		}


	}

	public String getStringData(String SheetName, int Row, int Column) {
		return wb.getSheet(SheetName).getRow(Row).getCell(Column).getStringCellValue();
	}

	public double getNumericData(String SheetName, int Row, int Column) {
		return wb.getSheet(SheetName).getRow(Row).getCell(Column).getNumericCellValue();
	}

}
