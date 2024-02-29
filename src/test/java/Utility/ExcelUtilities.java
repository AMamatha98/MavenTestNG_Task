package Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	private XSSFWorkbook workbook;
	private XSSFSheet Worksheet;
	private XSSFCell cell;

	private String excelFilePath = System.getProperty("user.dir") + "\\TestData.xlsx";

	private Properties prop = new Properties();
	
	public String ReadFromExcel(String strVariable, String strSheetname, int iColumnNo, int iRowNo) throws Exception {
		// System.out.println("In Read from Excel");
		String strText = null;
		String strData;
		String Position;
		// int i=0;
		try {
			FileInputStream ExcelFile = new FileInputStream(excelFilePath);
			workbook = new XSSFWorkbook(ExcelFile);
			Worksheet = workbook.getSheet(strSheetname);
			int totalrows = Worksheet.getLastRowNum();
			for (int i = 0; i < totalrows + 1; i++) {
				strData = getcelldata(i, 1);
				
				if (strVariable.equals(strData.toString())) {
					//strText = getcelldata(i, iColumnNo);

					strText = getcelldata(iRowNo, iColumnNo);
					
					break;
				}
			}

		}

		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception while reading from Excel : " + e.getMessage());
			e.printStackTrace();
		}
		return strText;

	}

	
	
	
	private String getcelldata(int rownum, int colnum) throws Exception {
		String celldata = null;
		DataFormatter formatter = new DataFormatter();
		try {
			
			celldata = formatter.formatCellValue(Worksheet.getRow(rownum).getCell(colnum));
			

		} catch (Exception e) {
			System.out.println("Exception while getCellData : Row,Col" + rownum + "," + colnum + e.getMessage());
			e.printStackTrace();
		}
		return celldata;
	}

	
}
