package sitpolycab;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
    public static String getCellData(String filePath, int rowNum, int colNum) 
    {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();
            String value = formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));
            workbook.close();
            fis.close();
            return value;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public static int getRowCount(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getLastRowNum();
            workbook.close();
            fis.close();
            return rowCount;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return 0;
        }
    }
}