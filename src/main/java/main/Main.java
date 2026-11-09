package main;

import java.util.ArrayList;
import paket.*;

public class Main {

    public static void main(String[] args) {
        ArrayList listOfNumbers = new ArrayList();
        NumberStorage numberStorage = new NumberStorage(listOfNumbers);
        
        Producer producer = new Producer(numberStorage);
        Consumer consumer = new Consumer(numberStorage);
        
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        
        System.out.println("Unesite cele brojeve.");
        System.out.println("(Prekid unosa brojeva vrsi se unosom reci 'kraj'):");
        producerThread.start();
        consumerThread.start();
        
        try{
            producerThread.join();
            consumerThread.join();
            
            System.out.println("Lista svih unetih brojeva:");
            
            for(Object num: listOfNumbers){
                System.out.print(num + " ");
            }
        }
        catch(InterruptedException e){
            System.out.println("Prekid!!!");
        }
    }
}
