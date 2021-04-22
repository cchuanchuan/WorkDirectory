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

        Double upAvgStream = -1.0;// ����ƽ��
        Double downAvgStream = -1.0;// ����ƽ��
        Double upPeek = -1.0;// ���з�ֵ
        Double upValley = -1.0;// ���й�ֵ
        Double downPeek = -1.0;// ���з�ֵ
        Double downValley = -1.0;// ���й�ֵ
        try {
            upAvgStream = Double.parseDouble(row.getCell(15).toString());// ����ƽ��
            downAvgStream = Double.parseDouble(row.getCell(16).toString());// ����ƽ��
            upPeek = Double.parseDouble(row.getCell(17).toString());// ���з�ֵ
            upValley = Double.parseDouble(row.getCell(19).toString());// ���й�ֵ
            downPeek = Double.parseDouble(row.getCell(18).toString());// ���з�ֵ
            downValley = Double.parseDouble(row.getCell(20).toString());// ���й�ֵ
        }catch (Exception e){}
        accessAccount.addData(upAvgStream,downAvgStream,upPeek,upValley,downPeek,downValley);
    }


    public void readExcel(String filePath) {
        // ��ù�����
        Workbook workbook = getWorkBook(filePath);
        if (workbook == null){
            System.out.println("�ļ�·������ȷ��");
            return;
        }
        // ��ù��������
        int sheetCount = workbook.getNumberOfSheets();
        // Ĭ�������ڵ�һ��������
        Sheet sheet = workbook.getSheetAt(0);
        // �������
        int rows = sheet.getLastRowNum() + 1;

        // ��ȡ����,�ӵ�3�п�ʼ��ȡ��1��2��Ϊ����)
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
