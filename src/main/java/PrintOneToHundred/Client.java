package PrintOneToHundred;

public class Client {
    public static void main(String[] args) {
        System.out.println("main thread is: " + Thread.currentThread().getName());

        //need for loop as i need 1000 threads
        for(int i=0; i<1000; i++){
            //initilize the object of the task..
            PrintNumberTask task = new PrintNumberTask(i);
            if(i==100){
                System.out.println("break at 100...");
            }
            //task.run();  //not a multi threaded code..
            Thread t = new Thread(task);
            t.start();
        }
    }
}
