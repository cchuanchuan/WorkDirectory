
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1></h1>
 * 程序运行后徐选择两个文件
 * 一个为专线用户接入号，为一个txt文件，一行为一个接入号，该数据由前端提供，目前长时间为更新
 * 另一个为文件夹，文件夹中包含60个上月互联网业务，组网业务的excel数据
 * 程序将读取接入号及excel，自动帅选出有用数据并导出一个对应的excel
 *
 * @Author: CCC
 * @Date 2020/11/23 16:57
 */
public class AnalyseSystem {
    public static Map<String, AccessAccount> dataMap = new HashMap<>();
    private ImportAccessData importAccessData;
    private ImportAccessAccount importAccessAccount;
    private JFileChooser jfc;

    // 初始化数据
    public AnalyseSystem() {
        dataMap = new HashMap<>();
        jfc = new JFileChooser();
        importAccessAccount = new ImportAccessAccount();
        importAccessData = new ImportAccessData();

        // 导入接入号
        File file1 = getAccessAccountFile();
        importAccessAccount.readAccessTxt(file1.getAbsolutePath());
        System.out.println("导入接入号成功");

        // 导入数据
        File directory = getAccessDataDirectory();
        File files[] = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println("正在读取第" + (i + 1) + "个文件，共" + files.length + "个");
            importAccessData.readExcel(files[i].getAbsolutePath());
        }

        // 分析数据库
        System.out.println("正在分析数据");
        for (AccessAccount accessAccount:dataMap.values()){
            accessAccount.analysData();
        }

        // 导出数据
        File file = new File("file/3月专线速率分析.xlsx");
        System.out.println("导出数据到："+file.getAbsolutePath());
        try {
            OutPutExcel outPutExcel = new OutPutExcel(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(jfc.getSelectedFile().getName());
//        importAccessAccount.readAccessTxt("");// 导入接入号
//        importAccessData.readExcel("file/11月大数据/ies/20201026镇江3.xlsx"); // 导入excel
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        new AnalyseSystem();
    }

    public File getAccessAccountFile() {
        jfc.setCurrentDirectory(new File("file").getAbsoluteFile());
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.showDialog(new JLabel(), "选择接入号TXT");
        File file = jfc.getSelectedFile();
        if (file == null || !file.isFile() || !file.getName().endsWith(".txt")) {
            JOptionPane.showMessageDialog(null, "请选择txt类型接入号文件");
            return getAccessAccountFile();
        }
        return file;
    }

    public File getAccessDataDirectory() {
        jfc.setCurrentDirectory(new File("file").getAbsoluteFile());
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.showDialog(new JLabel(), "选择数据文件夹");
        File file = jfc.getSelectedFile();
        if (file == null || !file.isDirectory()) {
            JOptionPane.showMessageDialog(null, "请选择数据文件夹");
            return getAccessDataDirectory();
        }
        return file;
    }
}
