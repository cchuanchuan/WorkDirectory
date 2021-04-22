
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1></h1>
 * �������к���ѡ�������ļ�
 * һ��Ϊר���û�����ţ�Ϊһ��txt�ļ���һ��Ϊһ������ţ���������ǰ���ṩ��Ŀǰ��ʱ��Ϊ����
 * ��һ��Ϊ�ļ��У��ļ����а���60�����»�����ҵ������ҵ���excel����
 * ���򽫶�ȡ����ż�excel���Զ�˧ѡ���������ݲ�����һ����Ӧ��excel
 *
 * @Author: CCC
 * @Date 2020/11/23 16:57
 */
public class AnalyseSystem {
    public static Map<String, AccessAccount> dataMap = new HashMap<>();
    private ImportAccessData importAccessData;
    private ImportAccessAccount importAccessAccount;
    private JFileChooser jfc;

    // ��ʼ������
    public AnalyseSystem() {
        dataMap = new HashMap<>();
        jfc = new JFileChooser();
        importAccessAccount = new ImportAccessAccount();
        importAccessData = new ImportAccessData();

        // ��������
        File file1 = getAccessAccountFile();
        importAccessAccount.readAccessTxt(file1.getAbsolutePath());
        System.out.println("�������ųɹ�");

        // ��������
        File directory = getAccessDataDirectory();
        File files[] = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println("���ڶ�ȡ��" + (i + 1) + "���ļ�����" + files.length + "��");
            importAccessData.readExcel(files[i].getAbsolutePath());
        }

        // �������ݿ�
        System.out.println("���ڷ�������");
        for (AccessAccount accessAccount:dataMap.values()){
            accessAccount.analysData();
        }

        // ��������
        File file = new File("file/3��ר�����ʷ���.xlsx");
        System.out.println("�������ݵ���"+file.getAbsolutePath());
        try {
            OutPutExcel outPutExcel = new OutPutExcel(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(jfc.getSelectedFile().getName());
//        importAccessAccount.readAccessTxt("");// ��������
//        importAccessData.readExcel("file/11�´�����/ies/20201026��3.xlsx"); // ����excel
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        new AnalyseSystem();
    }

    public File getAccessAccountFile() {
        jfc.setCurrentDirectory(new File("file").getAbsoluteFile());
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.showDialog(new JLabel(), "ѡ������TXT");
        File file = jfc.getSelectedFile();
        if (file == null || !file.isFile() || !file.getName().endsWith(".txt")) {
            JOptionPane.showMessageDialog(null, "��ѡ��txt���ͽ�����ļ�");
            return getAccessAccountFile();
        }
        return file;
    }

    public File getAccessDataDirectory() {
        jfc.setCurrentDirectory(new File("file").getAbsoluteFile());
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.showDialog(new JLabel(), "ѡ�������ļ���");
        File file = jfc.getSelectedFile();
        if (file == null || !file.isDirectory()) {
            JOptionPane.showMessageDialog(null, "��ѡ�������ļ���");
            return getAccessDataDirectory();
        }
        return file;
    }
}
