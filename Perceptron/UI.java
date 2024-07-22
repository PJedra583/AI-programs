import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    int first;
    int second;
    String[] set;
    Scanner scanner = new Scanner(System.in);
    public UI(String[] set){
        boolean pass = false;
        String response ="";
        this.set = set;
        while (!pass) {
            System.out.println("Select first class which you want to learn: ");
            for (int i = 0; i < set.length; i++) {
                System.out.println(1+i + ") " + set[i]);
            }
            response = scanner.next();
            try{
                if (Integer.parseInt(response) > 0 && Integer.parseInt(response) < set.length+1) {
                    pass = true;
                } else {
                    System.out.println("Input a number between 1 - " + set.length);
                }
            }catch (Exception e) {
                System.out.println("Input a number between 1 - " + set.length);
            }
        }
        this.first = Integer.parseInt(response)-1;
        pass = false;
        while (!pass) {
            System.out.println("Select second class which you want to learn: ");
            for (int i = 0; i < set.length; i++) {
                System.out.println(1+i + ") " + set[i]);
            }
            response = scanner.next();
            try{
                if (Integer.parseInt(response) > 0 && Integer.parseInt(response) < set.length+1) {
                    pass = true;
                } else {
                    System.out.println("Input a number between 1 - " + set.length);
                }
            }catch (Exception e) {
                System.out.println("Input a number between 1 - " + set.length);
            }
        }
        this.second = Integer.parseInt(response)-1;
    }

    //show accuracy constr
    public boolean UI2 (double accuracy1, double accuracy2){
        while (true) {
            System.out.println("=============================================================");
            System.out.println("Accuracy for " + set[first] + ": " + accuracy1*100 + "%");
            System.out.println("Accuracy for " + set[second] + ": " + accuracy2*100 + "%");
            System.out.println("=============================================================");
            System.out.println("Do you want to repeat the process? [y/n]");
            String s = scanner.next();
            if (s.equals("Y") || s.equals("y")) {
                return true;
            } else if (s.equals("N") || s.equals("n")) {
                return false;
            } else {
                System.out.println("Please type 'y' or 'n' ");
            }
        }
    }
    public void UI3() {
        while (true) {
            System.out.println("<<<Input vector>>>");
            String s = scanner.next();
            if (s.split(";").length != Perceptron.attribute_count) {
                System.out.println("Wrong count of variables");
            } else {
                List<Double> list = new ArrayList<>();
                try {
                    for (int i = 0; i < s.split(";").length; i++) {
                        list.add(Double.parseDouble(s.split(";")[i]));
                    }
                    Perceptron p = new Perceptron(list,"?");
                    if (p.compute() == 0) {
                        System.out.println(set[first]);
                    } else {
                        System.out.println(set[second]);
                    }
                } catch (Exception e) {
                    System.err.println("Error: NaN");
                }
            }

        }
    }
}
