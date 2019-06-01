package Selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excel 
{
	public static HashMap<String, String> dataFetchFromExcel(String sheetName, int rowNumber) throws IOException, InvalidFormatException
	{
		File file = new File("TestData.xlsx");
		
		FileInputStream fileInputStream = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		HashMap<String,String> currenthash = new HashMap<String,String>();
		
		Row headerRow = sheet.getRow(0);
			
		for(int i = 1; i <= sheet.getPhysicalNumberOfRows(); i++)
		{
			Row currentRow = sheet.getRow(i);
			
			if(i == rowNumber)
			{
				for(int j = 0; j < headerRow.getPhysicalNumberOfCells(); j++)
				{
					Cell cell = currentRow.getCell(j);
					CellType celltype = cell.getCellTypeEnum();
					
					switch(celltype)
					{
					case STRING:
						currenthash.put(headerRow.getCell(j).getStringCellValue(), cell.getStringCellValue());
						break;
					case NUMERIC:
						currenthash.put(headerRow.getCell(j).getStringCellValue(), Integer.toString((int) cell.getNumericCellValue()));
						break;
					default:
						break;
					}
				//	currenthash.put(headerRow.getCell(j).getStringCellValue(), currentRow.getCell(j).getStringCellValue());
				}
			}
		}
		workbook.close();
		return currenthash;
		
	}
}
