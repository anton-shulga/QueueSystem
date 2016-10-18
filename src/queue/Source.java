package queue;

import java.util.Random;

/**
 * Created by Антон on 18.10.2016.
 */
public class Source {
    private double screeningProbability;
    private Random random;

    public Source(double screeningProbability){
        this.screeningProbability = screeningProbability;
        this.random = new Random();
    }

    public double getScreeningProbability() {
        return screeningProbability;
    }

    public void setScreeningProbability(double screeningProbability) {
        this.screeningProbability = screeningProbability;
    }

    public boolean isGeneratedRequest(){
        return random.nextDouble() < 1 - screeningProbability;
    }
}
