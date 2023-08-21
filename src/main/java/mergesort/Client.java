package mergesort;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {
    //purpose of my task is  sorting, so task name is sorter.
    public static void main(String[] args) throws Exception {
        System.out.println("ThreadName: " + Thread.currentThread().getName());
        List<Integer> arrayToSort = List.of(
                10, 2, 8, 11, 4, 3, 9, 1
        );

        ExecutorService ess = Executors.newCachedThreadPool();
        Sorter sorter = new Sorter(arrayToSort, ess);
        Future<List<Integer>> sortedListFuture = ess.submit(sorter);
        // if i explicitly call the call method it is not multithreaded. callable for recursion in java
        // Future<List<Integer>> sortedListFuture = es.submit(sorter);

        List<Integer> sortedList = sortedListFuture.get(); // blocking call
        for (Integer in: sortedList) {
            System.out.println(in);
        }

    }
}
