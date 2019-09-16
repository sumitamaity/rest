package new1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readExcel {
	Workbook wb=null;
	org.apache.poi.ss.usermodel.Sheet ws=null;
	public void readXL(String path) throws IOException {
	FileInputStream fis= new FileInputStream(new File(path));
	wb = new XSSFWorkbook(fis);
	 ws= wb.getSheetAt(0);
   fis.close();
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
	 int colNum=getRowCount(ws);
	 
	 XSSFRow suiteRow= (XSSFRow) wb.getSheetAt(0).getRow(0);
	 for(int i=0; i< colNum; i++) {
		if(suiteRow.getCell(i).getStringCellValue().equalsIgnoreCase(columnName)) {
			int coulumnNumber=i;
			
		}
	 }
	
	return columnName;
 }

 
}

