package paket;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Consumer implements Runnable{
    
    NumberStorage numberStorage;

    public Consumer(NumberStorage numberStorage) {
        this.numberStorage = numberStorage;
    }
    
    private boolean isPrime(int num){
        if(num < 2){
            return false;
        }
        
        for(int i = 2; i <= Math.sqrt(num); i++){
            if(num % i == 0){
                return false;
            }
        }
        
        return true;
    }

    @Override
    public void run() {
        
        try(PrintWriter writer = new PrintWriter(new FileWriter("prime_nums.txt", true))){
            while(true){
                int num = numberStorage.getNumber();
                
                if(num == -1){
                    return;
                }
                
                System.out.println("***** " + num);

                if(isPrime(num)){
                    writer.println(num);
                }
            }
        } 
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
