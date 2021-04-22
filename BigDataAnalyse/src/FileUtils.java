import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;

public class FileUtils {

    public static void writeExcel() throws IOException {
        // 创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet("sheet1");

        for (int row = 0; row < 10; row++) {
            HSSFRow rows = sheet.createRow(row);
            for (int col = 0; col < 10; col++) {
                // 向工作表中添加数据
                rows.createCell(col).setCellValue("data" + row + col);
            }
        }

        File xlsFile = new File("poi.xls");
        FileOutputStream xlsStream = new FileOutputStream(xlsFile);
        workbook.write(xlsStream);
    }

//    public static Workbook getWorkBook(String filePath){
//        Workbook workbook = null;
//        if(filePath==null){
//            return null;
//        }
//        String extString = filePath.substring(filePath.lastIndexOf("."));
//        InputStream is = null;
//        try {
//            is = new FileInputStream(filePath);
//            if(".xls".equals(extString)){
//                return workbook = new HSSFWorkbook(is);
//            }else if(".xlsx".equals(extString)){
//                return workbook = new XSSFWorkbook(is);
//            }else{
//                return workbook = null;
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return workbook;
//    }
//    public static List<String> readExcel(String filePath) throws IOException, InvalidFormatException {
//        // 获得工作簿
//        Workbook workbook = getWorkBook(filePath);
//        if (workbook == null){
//            System.out.println("文件路径不正确！");
//            return  null;
//        }
//        // 获得工作表个数
//        int sheetCount = workbook.getNumberOfSheets();
//        // 遍历工作表
//        for (int i = 0; i < sheetCount; i++) {
//            Sheet sheet = workbook.getSheetAt(i);
//            // 获得行数
//            int rows = sheet.getLastRowNum() + 1;
//            // 获得列数，先获得一行，在得到改行列数
//            Row tmp = sheet.getRow(0);
//            if (tmp == null) {
//                continue;
//            }
//            // 读取数据
//            for (int row = 0; row < rows; row++) {
//                Row r = sheet.getRow(row);
//                int cols = r.getPhysicalNumberOfCells();
//                for (int col = 0; col < cols; col++) {
//                    System.out.print(r.getCell(col).toString()+",  ");
//                }
//                System.out.println();
//            }
//        }
//        return null;
//    }


//    public static void main(String[] args) throws IOException, InvalidFormatException {
//        readExcel("file/11月大数据/ies/20201026镇江3.xlsx");
//
//    }
}
