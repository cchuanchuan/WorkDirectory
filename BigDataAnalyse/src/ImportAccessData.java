import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * <h1></h1>
 *
 * @Author: CCC
 * @Date 2020/11/24 8:49
 */
public class ImportAccessData {

    public void analyseRow(Row row){
        String account = row.getCell(1).toString();

        AccessAccount accessAccount = AnalyseSystem.dataMap.get(account);
        if (accessAccount == null){
            return;
        }
        if (!accessAccount.isInit()){
            String name = row.getCell(2).toString();
            String address = row.getCell(3).toString();
            Integer rate = -1;
            Integer points = -1;
            try {
                rate = Integer.parseInt(row.getCell(8).toString());
                points = Integer.parseInt(row.getCell(10).toString());
            }catch (Exception e){}
            accessAccount.init(name,address,rate,points);
        }

        Double upAvgStream = -1.0;// 上行平均
        Double downAvgStream = -1.0;// 下行平均
        Double upPeek = -1.0;// 上行峰值
        Double upValley = -1.0;// 上行谷值
        Double downPeek = -1.0;// 下行峰值
        Double downValley = -1.0;// 下行谷值
        try {
            upAvgStream = Double.parseDouble(row.getCell(15).toString());// 上行平均
            downAvgStream = Double.parseDouble(row.getCell(16).toString());// 下行平均
            upPeek = Double.parseDouble(row.getCell(17).toString());// 上行峰值
            upValley = Double.parseDouble(row.getCell(19).toString());// 上行谷值
            downPeek = Double.parseDouble(row.getCell(18).toString());// 下行峰值
            downValley = Double.parseDouble(row.getCell(20).toString());// 下行谷值
        }catch (Exception e){}
        accessAccount.addData(upAvgStream,downAvgStream,upPeek,upValley,downPeek,downValley);
    }


    public void readExcel(String filePath) {
        // 获得工作簿
        Workbook workbook = getWorkBook(filePath);
        if (workbook == null){
            System.out.println("文件路径不正确！");
            return;
        }
        // 获得工作表个数
        int sheetCount = workbook.getNumberOfSheets();
        // 默认数据在第一个工作表
        Sheet sheet = workbook.getSheetAt(0);
        // 获得行数
        int rows = sheet.getLastRowNum() + 1;

        // 读取数据,从第3行开始读取（1，2行为标题)
        for (int row = 2; row < rows; row++) {
            Row curRow = sheet.getRow(row);
            analyseRow(curRow);
        }
    }

    public static Workbook getWorkBook(String filePath){
        Workbook workbook = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return workbook = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return workbook = new XSSFWorkbook(is);
            }else{
                return workbook = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }


}
