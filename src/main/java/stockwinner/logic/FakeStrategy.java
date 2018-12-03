package stockwinner.logic;

import javafx.util.Pair;

import java.util.List;
import java.util.Random;

public class FakeStrategy extends Strategy {

    public FakeStrategy(List<Double> input) {
        super(input);
    }

    public void startWork(){
        new Thread(() -> {
            Random r = new Random();
            for (double n : input){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
                output.add(new Pair<>(n, n + (1+0.1*r.nextDouble()/ Double.MAX_VALUE)));
            }
        }).start();
    }

}
