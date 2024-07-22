import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
      //args[0] = constant, args[1] = file_train, args[i] = file_test
      //Example arguments: 1 "src/train-set.csv" "src/test-set.csv"

      //Setting constant
      Perceptron.constant = Integer.parseInt(args[0]);

      //Preparing train_set
        List<Perceptron> perceptrons = read_file_and_add_perceptron(args[1]);

        //Setting Attribute_count for Perceptron
        Perceptron.attribute_count = perceptrons.get(0).input_vector.size();
        //Setting Weight_vector
        Perceptron.prepare_weight_vector();
        Perceptron.próg = 1;

        //Computing...
        Collections.shuffle(perceptrons);

        //Classes contains all given decision attributes
        Set<String> classes = new HashSet<>();
        for (int i = 0; i < perceptrons.size(); i++) {
             classes.add(perceptrons.get(i).attribute);
        }
        String[] classes2 = classes.toArray(new String[0]);

        //Running "Choosing UI"
        UI ui = new UI(classes2);
        System.out.println(Perceptron.weight_vector);
        System.out.println(ui.set[ui.first]);
        System.out.println(ui.set[ui.second]);
        //Learning perceptron
        Trainer.train(perceptrons,ui);
        //Preparing Test_set
        List<Perceptron> perceptrons2 = read_file_and_add_perceptron(args[2]);
        //Calculating accuracy
        List<Double> results = Trainer.Calculate_Accuracy(perceptrons2,ui);

        //Running UI to show accuracy
        boolean b = true;
        while (b) {
            b = ui.UI2(results.get(0), results.get(1));
            if (b) {
                Trainer.train(perceptrons, ui);
                results = Trainer.Calculate_Accuracy(perceptrons2,ui);
            }
        }
        ui.UI3();
    }
    public static List<Perceptron> read_file_and_add_perceptron(String filename) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        List<Perceptron> perceptrons = new ArrayList<>();
        line = br.readLine();
        while (line != null && line.split(";").length>1) {
            //Making input vector
            String[] arr = line.split(";");
            List<Double> list = new ArrayList<>();
            for (int i = 0; i < arr.length-1; i++) {
                list.add(Double.parseDouble(arr[i]));
            }
            Perceptron p = new Perceptron(list,arr[arr.length-1]);
            perceptrons.add(p);
            line = br.readLine();
        }
        br.close();
        return perceptrons;
    }
}
