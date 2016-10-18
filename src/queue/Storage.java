package queue;

/**
 * Created by Антон on 18.10.2016.
 */
public class Storage {
    private int requestMaxNumber;
    private int currentRequestNumber;

    public Storage(int requestMaxNumber){
        this.requestMaxNumber = requestMaxNumber;
    }

    public int getRequestMaxNumber() {
        return requestMaxNumber;
    }

    public void setRequestMaxNumber(int requestMaxNumber) {
        this.requestMaxNumber = requestMaxNumber;
    }

    public int getCurrentRequestNumber() {
        return currentRequestNumber;
    }

    public void setCurrentRequestNumber(int currentRequestNumber) {
        this.currentRequestNumber = currentRequestNumber;
    }
}
