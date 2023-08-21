package PrintOneToHundred;

public class PrintNumberTask implements Runnable{

    //defined variable
    private Integer noToPrint;

    //define a constructor
    public PrintNumberTask(Integer noToPrint){

        this.noToPrint = noToPrint;
    }

    @Override
    public void run() {
        System.out.println("number is :  " + noToPrint + " Thread name is :" + Thread.currentThread().getName());
    }
}
