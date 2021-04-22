import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * <h1></h1>
 *
 * @Author: CCC
 * @Date 2020/11/24 14:09
 */
public class OutPutExcel {

    public OutPutExcel(File file) throws IOException {
        //创建Excel文件薄
        XSSFWorkbook workbook=new XSSFWorkbook();
        //创建工作表sheeet
        Sheet sheet=workbook.createSheet();
        //创建第一行
        String[] title={"接入号","客户名称","签约速率","下行流量","上行流量","下行峰值速率","上行峰值速率","下行超过签约次数","上行超过签约次数"};
        Row row=sheet.createRow(0);
        Cell cell=null;
        for (int i=0;i<title.length;i++){
            cell=row.createCell(i);
            cell.setCellValue(title[i]);
        }
        int count = 1;
        System.out.println("数据数量："+AnalyseSystem.dataMap.values().size());
        //追加数据
        for (AccessAccount aa:AnalyseSystem.dataMap.values()){
            Row nextrow=sheet.createRow(count);
            nextrow.createCell(0).setCellValue(aa.getAccount());
            nextrow.createCell(1).setCellValue(aa.getName());
            nextrow.createCell(2).setCellValue(aa.getRate());
            nextrow.createCell(3).setCellValue(aa.getUpFlow());
            nextrow.createCell(4).setCellValue(aa.getDownFlow());
            nextrow.createCell(5).setCellValue(aa.getMaxDownPeek());
            nextrow.createCell(6).setCellValue(aa.getMaxUpPeek());
            nextrow.createCell(7).setCellValue(aa.getDownPeekCount());
            nextrow.createCell(8).setCellValue(aa.getUpPeekCount());
            count++;
        }
        //创建一个文件
//        File file=new File("file/poi_test.xlsx");
        file.createNewFile();
        FileOutputStream stream= new FileOutputStream(file);
        workbook.write(stream);
        stream.close();
    }
}
