package new1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readExcel {
	Workbook wb=null;
	org.apache.poi.ss.usermodel.Sheet ws=null;
	readExcel(String path) {
	FileInputStream fis;
	try {
		fis = new FileInputStream(new File(path));
	
	wb = new XSSFWorkbook(fis);
	 ws= wb.getSheetAt(0);
   fis.close();
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
 public int getRowCount(String Sheetname) {
	 int x= wb.getSheetIndex(Sheetname);
	 if(x==-1) {
		 return 0;
	 }else
	 {
		 ws= wb.getSheetAt(x);
		 int rowCount=ws.getLastRowNum()+1;
		 return rowCount; 
		 
	 }
	
}
 
 public int getColumnCount(String Sheetname) {
	 int y=wb.getSheetIndex(Sheetname);
	 if(y==-1) {
		 return y;
	 }else {
		 ws=wb.getSheetAt(y);
		 int colNum= ws.getRow(0).getLastCellNum();
		 return colNum;
	 }
 }

 public String getValueFromCell(String columnName, String ws) {
	
	 int sheetIndex=wb.getSheetIndex(ws);
	 int rowNum= getRowCount(ws);
	 int colNum=getColumnCount(ws);
	// System.out.println(rowNum+"  "+colNum);
	 int coulumnNumber=0;
	 String result=null;
	 String value=null;
	 XSSFRow suiteRow= (XSSFRow) wb.getSheetAt(sheetIndex).getRow(0);
	 for(int i=0; i<colNum; i++) {
		 
		 //System.out.println(suiteRow.getCell(i).getStringCellValue().trim());
		if(columnName.equalsIgnoreCase(suiteRow.getCell(i).getStringCellValue().trim())) 
			coulumnNumber=i;
		//System.out.println(coulumnNumber);
			
	 }
		for(int j=1; j<rowNum; j++) {
			XSSFRow col=(XSSFRow) wb.getSheetAt(sheetIndex).getRow(j);
			XSSFCell cell=col.getCell(coulumnNumber);
			//value=cell.getStringCellValue();
			value=getFormattedCellValu(cell);
			System.out.println(value);
			
		}
		return value;
	 }
	
 public String getFormattedCellValu(Cell myCell) {
	 DataFormatter formatter = new DataFormatter();
	 String formattedCellValue = formatter.formatCellValue(myCell);
	return formattedCellValue;
 }
 
 
 
	
	
	/*
	 * public static void main(String[] args) { readExcel re; re = new
	 * readExcel("C:\\Users\\summaity.ORADEV\\Documents\\SM\\LastMin\\FirstApi.xlsx"
	 * );
	 * 
	 * 
	 * re.getValueFromCell("baseURI", "Sheet1");
	 * re.getValueFromCell("basePath","Sheet1"); re.getValueFromCell("unit",
	 * "Sheet1");
	 * 
	 * re.getValueFromCell("key", "Sheet1");
	 * 
	 * }
	 */
	 }

