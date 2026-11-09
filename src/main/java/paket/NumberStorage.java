package paket;

import java.util.ArrayList;

public class NumberStorage {
    private ArrayList listOfNumbers;
    private boolean productionFinished = false;
    private int nextIndex = 0;

    public NumberStorage(ArrayList listOfNumbers) {
        this.listOfNumbers = listOfNumbers;
    }

    public synchronized void setProductionFinished(boolean productionFinished) {
        this.productionFinished = productionFinished;
        notify();
    }

    
    public synchronized void addNumber(int num){
        listOfNumbers.add(num);
        notify();
    }
    
    public synchronized int getNumber(){
        while(nextIndex >= listOfNumbers.size()){
            if(productionFinished){
                return -1;
            }
            
            try{
                wait();
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
                return -1;
            }
        }
        
        int num = (int) listOfNumbers.get(nextIndex);
        nextIndex++;
        //listOfNumbers.remove(0);
        return num;
    }
}
