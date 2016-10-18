package queue;

import java.util.Random;

/**
 * Created by Антон on 18.10.2016.
 */
public class Canal {
    private double screeningProbability;
    private boolean isBusy;
    private Random random;

    public Canal(double screeningProbability){
        random = new Random();
        this.screeningProbability = screeningProbability;
    }

    public double getScreeningProbability() {
        return screeningProbability;
    }

    public void setScreeningProbability(double screeningProbability) {
        this.screeningProbability = screeningProbability;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public boolean isProcessedRequest(){
        return random.nextDouble() < 1 - screeningProbability;
    }
}
