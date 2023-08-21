package mergesort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Sorter implements Callable<List<Integer>> {
    //variable

    private List<Integer> arrayToSort;

    private ExecutorService executorservice;
    //create a constructor.
    public Sorter(List<Integer> arrayToSort, ExecutorService es){
        this.arrayToSort = arrayToSort;
        this.executorservice = es;
    }


    @Override
    public List<Integer> call() throws Exception {
        System.out.println("call ThreadName: " + Thread.currentThread().getName() + " Array is:  " + arrayToSort);
        // S1: Base Case
        if(arrayToSort.size() <= 1){
            return arrayToSort;
        }

        int mid = arrayToSort.size()/2;

        // s2 create left and right array.

        List<Integer> leftArray = new ArrayList<>();
        for(int i=0; i<mid;++i){
            leftArray.add(arrayToSort.get(i));
        }

        List<Integer> rightArray = new ArrayList<>();
        for(int i=mid; i<arrayToSort.size();++i){
            rightArray.add(arrayToSort.get(i));
        }

        // s3 HOW DO WE CALL the MERGE SORT? recursive call sort on left and right array
        // i want to create object of my sorter class, as i have inputs i want to pass in my method. want to pass new array to my method
        Sorter leftSorter = new Sorter(leftArray, executorservice);
        Sorter rightSorter = new Sorter(rightArray, executorservice);

// calling the method.
        //ExecutorService ee = Executors.newCachedThreadPool();
        // creating the object and passing the new object.
        Future<List<Integer>> leftSortedArrayFuture = executorservice.submit(leftSorter);
        Future<List<Integer>> rightSortedArrayFuture = executorservice.submit(rightSorter);

        // WE have gotten the sorted arrays. We need to merge them Now
// merge two sorted arrays using two pointer
        //it will be blocking call after this line as i am waiting for my left-right sorted array, i want to merge now...
        List<Integer> sortedArray = new ArrayList<>();

        // s4 USING TWO POINTERS to merge
        int i=0, j=0;

        List<Integer> leftSortedArray = leftSortedArrayFuture.get();
        List<Integer> rightSortedArray = rightSortedArrayFuture.get();

        while(i < leftSortedArray.size() && j < rightSortedArray.size()){
            if(leftSortedArray.get(i) < rightSortedArray.get(j)){
                sortedArray.add(leftSortedArray.get(i));
                ++i;
            }else{
                sortedArray.add(rightSortedArray.get(j));
                ++j;
            }
        }
// add remaining elements in final array...
        // s5 WHAT ELSE? --> check for remaining half
        while(i < leftSortedArray.size()){
            sortedArray.add(leftSortedArray.get(i)); i++;
        }

        while(j < rightSortedArray.size()){
            sortedArray.add(rightSortedArray.get(j)); j++;
        }

        // s6 now return --> LEts go to CLIENT
        return sortedArray;
    }
}
