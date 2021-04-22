import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ImportAccessAccount {
    /**
     * 读取文件
     *
     * @param filePath 文件路径
     * @return
     */
    public void readAccessTxt(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("File is not exit!");
            return;
        }

        Scanner sc = null;
        try {
            sc = new Scanner(file);
            String line = "";
            while ((line = sc.nextLine()) != null) {
                if (AnalyseSystem.dataMap.get(line.trim()) == null && !line.trim().equals("")) {
                    AnalyseSystem.dataMap.put(line.trim(), new AccessAccount(line.trim()));
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR:" + e.getMessage());
            sc.close();
        } catch (NoSuchElementException e) {
        }
    }
}
