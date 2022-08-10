package in.zaankari.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import in.zaankari.factory.Factory;

public class ExcelUtil {

//	private String Test_Data_Sheet = "./src/test/resources/testData/data.xlsx";
	private XSSFWorkbook book;
	private XSSFSheet sheet;
	
	public Object[][] getTestData(String Test_Data_Sheet,String sheetName) {
		
		Object data[][] = null;
		try {
			
			FileInputStream ip = new FileInputStream(Test_Data_Sheet);
			book = new XSSFWorkbook(ip);
			sheet = book.getSheet(sheetName);

			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i =0;i<sheet.getLastRowNum();i++)
			  {
			   for(int j =0;j<sheet.getRow(0).getLastCellNum();j++)
			   	{
				   if(sheet.getRow(i+1).getCell(j)!=null)
					   data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				   else
					   data[i][j] = null;
				   }
			   }
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return data;
		
	}

}
