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
    private double denialProbability;
    private double relativeBandwidth;
    private double absoluteBandwidth;
    private double intensity;
    private double time;
    private double processedRequestNumber;
    private double queueTime;
    private double firstCanalTime;
    private double secondCanalTime;
    private double inSystemNumber;

    public QueueSystem(Source source, Storage storage, Canal firstCanal, Canal secondCanal, int tactNumber) {
        this.source = source;
        this.storage = storage;
        this.firstCanal = firstCanal;
        this.secondCanal = secondCanal;
        this.tactNumber = tactNumber;
        this.time = 0;
        this.processedRequestNumber = 0;
        this.inSystemNumber = 0;

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

    public int getGeneratedRequestNumber() {
        return generatedRequestNumber;
    }

    public void setGeneratedRequestNumber(int generatedRequestNumber) {
        this.generatedRequestNumber = generatedRequestNumber;
    }

    public double getDenialProbability() {
        return denialProbability;
    }

    public void setDenialProbability(double denialProbability) {
        this.denialProbability = denialProbability;
    }

    public double getRelativeBandwidth() {
        return relativeBandwidth;
    }

    public void setRelativeBandwidth(double relativeBandwidth) {
        this.relativeBandwidth = relativeBandwidth;
    }

    public double getAbsoluteBandwidth() {
        return absoluteBandwidth;
    }

    public void setAbsoluteBandwidth(double absoluteBandwidth) {
        this.absoluteBandwidth = absoluteBandwidth;
    }

    public double getIntensity() {
        return intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }

    public void processRequests() {
        for(int i = 0; i < tactNumber; i++) {
            if (secondCanal.isBusy()) {
                if(secondCanal.isProcessedRequest()){
                    processedRequestNumber++;

                    time += secondCanalTime;
                    secondCanalTime = 0;
                    secondCanal.setBusy(false);
                }else {
                    secondCanalTime++;
                }
            }

            if(firstCanal.isBusy()){
                if(firstCanal.isProcessedRequest()){
                    firstCanalTime++;
                    if(!secondCanal.isBusy()){
                        firstCanal.setBusy(false);
                        secondCanal.setBusy(true);
                        secondCanalTime += firstCanalTime;
                        firstCanalTime = 0;
                    }else {
                        firstCanalTime = 0;
                        denialNumber++;
                        firstCanal.setBusy(false);
                    }
                }else {
                    firstCanalTime++;
                }
            }
            if(storage.getCurrentRequestNumber() == storage.getRequestMaxNumber()){
                if(!firstCanal.isBusy()){
                    storage.setCurrentRequestNumber(storage.getCurrentRequestNumber() - 1);
                    queueTime++;
                    firstCanalTime += queueTime;
                    queueTime = 0;
                    firstCanal.setBusy(true);
                }
                else {
                    queueTime++;
                }
            }

            if(source.isGeneratedRequest()){
                generatedRequestNumber++;
                if(storage.getCurrentRequestNumber() < storage.getRequestMaxNumber()){
                    inSystemNumber++;
                    if(!firstCanal.isBusy()){
                        firstCanal.setBusy(true);
                        firstCanalTime++;
                    }else{
                        storage.setCurrentRequestNumber(storage.getCurrentRequestNumber() + 1);
                        queueTime++;
                    }
                }else {
                    denialNumber++;
                }
            }
        }

        denialProbability = (double) denialNumber / generatedRequestNumber;
        System.out.println("Denial probability = " + denialProbability);
        intensity = 1 - source.getScreeningProbability();
        relativeBandwidth = processedRequestNumber / generatedRequestNumber;
        System.out.println("Relative bandwidth = " + relativeBandwidth);
        System.out.println("System time = " + time/processedRequestNumber);
    }
}
