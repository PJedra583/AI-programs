import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Trainer {

    public static List<Perceptron> train(List<Perceptron> perceptrons, UI ui){
        for (int i = 0; i < perceptrons.size(); i++) {
            if (perceptrons.get(i).attribute.equals(ui.set[ui.first])) {
                perceptrons.get(i).compute();
                perceptrons.get(i).learn(0);
            } else if (perceptrons.get(i).attribute.equals(ui.set[ui.second])) {
                perceptrons.get(i).compute();
                perceptrons.get(i).learn(1);
            }
        }
        return perceptrons;
    }
    public static List<Double> Calculate_Accuracy(List<Perceptron> perceptrons, UI ui){
        List<Double> list = new ArrayList();
        int count = 0;
        int count1 = 0;
        int counter = 0;
        int counter1 = 0;

        for (int i = 0; i < perceptrons.size(); i++) {
            Perceptron p = perceptrons.get(i);
            if (p.attribute.equals(ui.set[ui.first])) {
                p.compute();
                System.out.println(p);
                if (p.output == 0) {
                    count++;
                }
                counter++;
            } else if (p.attribute.equals(ui.set[ui.second])) {
                p.compute();
                System.out.println(p);
                if (p.output == 1) {
                    count1++;
                }
                counter1++;
            }
        }
        System.out.println(count + "    " + count1);
        System.out.println(counter + "    " + counter1);

        list.add(((double) count)/((double) counter ));
        list.add(((double) count1)/((double) counter1 ));
        return list;
    }
}
