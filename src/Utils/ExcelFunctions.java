package Utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFunctions {
	
	
	public void writeExcel(String path, String sheetName, String headers, ArrayList<String> data)
	{
		try {
		File file = new File(path);
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName);
		FileOutputStream fout = new FileOutputStream(path);
		wb.write(fout);
		fout.close();
		
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook wbo = new XSSFWorkbook(fis);
		XSSFSheet sheeto = wbo.getSheetAt(0);
		XSSFRow rowo = sheeto.createRow(0);
		
		//Method to split into array
		String[] headerList = headers.split(",");
		for(int i=0;i<headerList.length;i++) {
			rowo.createCell(i).setCellValue(headerList[i]);
		}
		
		System.out.println("size "+data.size());
		for(int i=0;i<data.size();i++) {
			String[] cellValues = data.get(i).split(";;");
			rowo = sheeto.createRow(i+1);
			for(int j=0;j<cellValues.length;j++) {
				rowo.createCell(j).setCellValue(cellValues[j]);
			}
		}
		FileOutputStream fout1 = new FileOutputStream(path);
		wbo.write(fout1);
		fout1.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	public ArrayList<String> readExcel(String path, String sheetName)
	{
		ArrayList<String> inputData = new ArrayList<String>();
		try {
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		int cellCount = sheet.getRow(0).getLastCellNum(); 
		String data;
		for(int i=1; i<rowCount+1;i++) {
			data = "";
			for(int j=0;j<cellCount;j++) {
				data = data+sheet.getRow(i).getCell(j).getStringCellValue()+";;";
			}
			inputData.add(data);
		}
		
		fis.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return inputData;
	}
	
}
