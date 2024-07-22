import java.io.*;

public class Prepare_files {
    public static void main(String[] args) {
        String s1 = "src/iris.csv";
        String s2 = "src/train-set.csv";
        String s3 = "src/test-set.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(s1))) {
            String line;
            BufferedWriter trainWriter = new BufferedWriter(new FileWriter(s2));
            BufferedWriter testWriter = new BufferedWriter(new FileWriter(s3));
            int lineCount = 0;
            int podzial = 35; // tu wpisz podzial
            int podzial2 = 50;
            while ((line = br.readLine()) != null) {
                if (lineCount >= podzial) {
                    testWriter.write(line+"\n");
                    if (lineCount == 50) {
                      lineCount = -1;
                    }
                } else {
                    trainWriter.write(line+"\n");
                }
                lineCount++;
            }
            trainWriter.close();
            testWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
