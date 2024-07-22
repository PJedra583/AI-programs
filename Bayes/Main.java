import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        HashMap<String[], String> trainSet = new HashMap<>((Map<String[], String>) readTest("trainingset.csv", true));
        List<String[]> testSet = new ArrayList((List<String[]>) readTest("testset.csv", false));
        for (int i = 0; i < testSet.size(); i++) {
            double max_value = 0;
            String actuall_decision = "";
            System.out.print("Attributes: ");

            //forma wydruku
            for (String s : testSet.get(i)) {
                System.out.print(s + " ");
            }
            System.out.println();

            for (String decisionAttribute : getSetOfKindsOfValue(trainSet) ) {
                System.out.println(decisionAttribute);
                System.out.print("Compute:  ");
                double value = computeOutput(testSet.get(i),trainSet,decisionAttribute);
                System.out.println();
                System.out.println(value);
                if (value > max_value){
                    actuall_decision = decisionAttribute;
                    max_value = value;
                }
            }

            //Forma wydruku
            System.out.println("Decision: " + actuall_decision);
            System.out.println("==============================");
        }
       // Zapętlone podawanie wektorów
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Input a vector: ");
            String input = scanner.next();
            String[] tab = input.split(",");
            if (tab.length != testSet.get(0).length) {
                System.out.println("Wrong count of attributes");
            } else {
                double max_value = 0;
                String actuall_decision = null;
                for (String decisionAttribute : getSetOfKindsOfValue(trainSet) ) {
                    System.out.println(decisionAttribute);
                    double value = computeOutput(tab,trainSet,decisionAttribute);
                    System.out.println(value);
                    if (value > max_value){
                        actuall_decision = decisionAttribute;
                        max_value = value ;
                    }
                }
                System.out.println("DECISION:   " + actuall_decision);
            }
        }
    }

    public static Object readTest(String filepath, boolean isTrainSet) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        String line;

        if (isTrainSet) {
            Map<String[], String> result = new HashMap<>();
            while ((line = br.readLine()) != null ) {
                String[] splited = line.split(",");
                if (splited.length>1) {
                    String[] tmp = Arrays.copyOf(splited, splited.length - 1);
                    result.put(tmp, splited[splited.length - 1]);
                }
            }
            br.close();
            return result;
        } else {
            List<String[]> result = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] splited = line.split(",");
                if (splited.length>1) {
                    result.add(splited);
                }
            }
            br.close();
            return result;
        }
    }
    public static Double computeOutput(String[] attributes,HashMap<String[],String> trainSet,String decisionAttribute){
        double sum = 1;
        for (int i = 0; i < attributes.length; i++) {
            double tmp = countHowMany(trainSet,attributes[i],i,decisionAttribute);
            double count = countMax(trainSet,decisionAttribute);
            if (tmp != 0){
                sum *= tmp/count;
                System.out.println(tmp + "    " + count);
            } else {
                var x = count+howManyKindsOfValue(trainSet,i);
                System.out.println(1 + "    " + x) ;
                sum *= 1/x;
            }
        }
        var x = countForKey(trainSet,decisionAttribute);
        return sum*x;
    }
    public static Double countHowMany(HashMap<String[],String> trainSet,String name,int position,String condition){
        double counter = 0;
        for (Map.Entry<String[],String> e:
             trainSet.entrySet()) {
            if (e.getKey()[position].equals(name) && e.getValue().equals(condition)) {
                counter++;
            }
        }
        return counter;
    }
    public static Double countForKey(HashMap<String[],String> trainSet,String decisionAttribute){
        double nominator = 0;
        double denominator = 0;
        for (Map.Entry<String[],String> e:
                trainSet.entrySet()) {
            if (e.getValue().equals(decisionAttribute)){
                nominator++;
            }
            denominator++;
        }
        System.out.println(nominator + "    " + denominator);
        return nominator/denominator;
    }
    public static Double countMax(HashMap<String[],String> trainSet,String decisionAttribute){
        double counter = 0;
        for (String s:
                trainSet.values()) {
            if (s.equals(decisionAttribute)) counter++;
        }
        return counter;
    }
    public static int howManyKindsOfValue(HashMap<String[],String> trainSet, int position){
        Set<String> set = new HashSet<>();
        for (Map.Entry<String[],String> entry : trainSet.entrySet()) {
            set.add(entry.getKey()[position]);
        }
        return set.size();
    }
    public static Set<String> getSetOfKindsOfValue(HashMap<String[],String> trainSet){
        Set<String> set = new HashSet<>();
        for (Map.Entry<String[],String> entry : trainSet.entrySet()) {
            set.add(entry.getValue());
        }
        return set;
    }
}
