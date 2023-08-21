package threads;

public class Client {
    public static void main(String[] args) {

        System.out.println("hello world from main...."+ Thread.currentThread().getName());
        //create object of class
        HelloWorldPrinter task = new HelloWorldPrinter();
        //task.run(); //it will not create new thread.


        // create the thread and pass the task to my thread
        Thread t = new Thread(task);
        //start the thread
        t.start();
    }
}
