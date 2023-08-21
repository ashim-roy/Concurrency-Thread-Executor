package threads;

public class HelloWorldPrinter implements Runnable{
    @Override
    public void run() {
        System.out.println("hello world from run..."+ Thread.currentThread().getName());
    }
}
