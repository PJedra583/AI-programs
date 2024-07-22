import java.util.ArrayList;
import java.util.List;

public class Perceptron {
    List<Double> input_vector;
    String attribute;
    public static double próg;
    public static List<Double> weight_vector = new ArrayList<>();
    int output;
    public static int attribute_count;
    public static double constant = 1;
    public static int idcounter = 0;
    public int id;
    public double net;
    public Perceptron(List<Double> input_vector, String attribute) {
        this.id = idcounter++;
        this.input_vector = input_vector;
        this.attribute = attribute;
    }
    public void learn(int desired_output){
        //W' = W+(d-y)lX
        List<Double> new_weight_vector = new ArrayList<>();
        for (int i = 0; i < weight_vector.size(); i++) {
            double x = weight_vector.get(i)+((desired_output - this.output)*constant*this.input_vector.get(i));
            new_weight_vector.add(x);
        }
        weight_vector = new_weight_vector;

        //t' = t-(d-y)l
        próg = próg - ((desired_output-this.output)*constant);
    }
    public int compute(){
        //net = X*W-t
        double sum = 0;
        for (int i = 0; i < this.input_vector.size(); i++) {
            sum += input_vector.get(i)*weight_vector.get(i);
        }
        this.net = sum;
        if (sum >= próg) {
            this.output = 1;
            return 1;
        } else {
            this.output = 0;
            return 0;
        }
    }
    public static void prepare_weight_vector(double... args) {
        if (args.length == 0) {
            for (int i = 0; i < attribute_count; i++) {
                weight_vector.add((Math.random() * 10) - 5);
            }
        } else {
            for (int i = 0; i < args.length; i++) {
                weight_vector.add(args[i]);
            }
        }
    }
    public String toString(){
        String s = id + ") " + "\n" + "Values: " + input_vector + "\n" + "Weights: " + weight_vector + "   próg: " + próg;
        s += "\n" + "Class: " + attribute + " Output: " + output + " Net: " + net + "\n";
        return s;
    }
}
