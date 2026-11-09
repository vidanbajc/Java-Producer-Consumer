package paket;

import java.util.Scanner;

public class Producer implements Runnable{
    
    NumberStorage numberStorage;

    public Producer(NumberStorage numberStorage) {
        this.numberStorage = numberStorage;
    }
    
    private int scanNext(){
        Scanner scanner = new Scanner(System.in);

        while(true){
            String value = scanner.nextLine();

            if(value.equalsIgnoreCase("kraj")){
                numberStorage.setProductionFinished(true);
                return -1;
            }

            try{
               int num = Integer.parseInt(value);
               
               if(num < 0){
                   System.out.println("***** Neispravan unos, broj mora biti pozitivan!");
                   continue;
               }
               
               return num;
            }
            catch(NumberFormatException e){
                System.out.println("***** Neispravan unos, pokusajte da unesete ceo broj(int)!");
            }
        }
    }

    @Override
    public void run() {
        while(true){
            int num = scanNext();
        
            if(num == -1){
                return;
            }

            numberStorage.addNumber(num);
        }
    }
}
