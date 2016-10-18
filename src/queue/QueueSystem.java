package queue;

/**
 * Created by Антон on 18.10.2016.
 */
public class QueueSystem {

    private Source source;
    private Storage storage;
    private Canal firstCanal;
    private Canal secondCanal;
    private int tactNumber;
    private int denialNumber;
    private int generatedRequestNumber;

    public QueueSystem(Source source, Storage storage, Canal firstCanal, Canal secondCanal, int tactNumber) {
        this.source = source;
        this.storage = storage;
        this.firstCanal = firstCanal;
        this.secondCanal = secondCanal;
        this.tactNumber = tactNumber;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Canal getFirstCanal() {
        return firstCanal;
    }

    public void setFirstCanal(Canal firstCanal) {
        this.firstCanal = firstCanal;
    }

    public Canal getSecondCanal() {
        return secondCanal;
    }

    public void setSecondCanal(Canal secondCanal) {
        this.secondCanal = secondCanal;
    }

    public int getTactNumber() {
        return tactNumber;
    }

    public void setTactNumber(int tactNumber) {
        this.tactNumber = tactNumber;
    }

    public int getDenialNumber() {
        return denialNumber;
    }

    public void setDenialNumber(int denialNumber) {
        this.denialNumber = denialNumber;
    }

    public void processRequests() {
        for(int i = 1; i < tactNumber; i++) {
            if (secondCanal.isBusy()) {
                if(secondCanal.isProcessedRequest()){
                    secondCanal.setBusy(false);
                }
            }
            if(firstCanal.isBusy()){
                if(firstCanal.isProcessedRequest()){
                    if(!secondCanal.isBusy()){
                        firstCanal.setBusy(false);
                        secondCanal.setBusy(true);
                    }else {
                        denialNumber++;
                        firstCanal.setBusy(false);
                    }
                }
            }
            if(storage.getCurrentRequestNumber() == storage.getRequestMaxNumber()){
                if(!firstCanal.isBusy()){
                    storage.setCurrentRequestNumber(storage.getCurrentRequestNumber() - 1);
                    firstCanal.setBusy(true);
                }
            }

            if(source.isGeneratedRequest()){
                generatedRequestNumber++;
                if(storage.getCurrentRequestNumber() < storage.getRequestMaxNumber()){
                    if(!firstCanal.isBusy()){
                        firstCanal.setBusy(true);
                    }else{
                        storage.setCurrentRequestNumber(storage.getCurrentRequestNumber() + 1);
                    }
                }else {
                    denialNumber++;
                }
            }
        }
    }
}
