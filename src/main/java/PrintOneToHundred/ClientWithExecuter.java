package PrintOneToHundred;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientWithExecuter {
    public static void main(String[] args) {
        //create a executer service
        ExecutorService es = Executors.newFixedThreadPool(50);  //created 50 threads

        for(int i=0; i<1000; i++){
            //create a object of printnumbertask
            PrintNumberTask task = new PrintNumberTask(i);
            if(i==200){
                System.out.println("break");
            }
            es.submit(task);
        }
        es.shutdown();
    }
}
