/**
 * Created by Evgenii on 15. 11. 24..
 */
//import java.util.Arrays;
import java.util.Random;

public class Quicksort <E extends Comparable<? super E>> {
    //why DSutil works, without making an object?
    //Quicksort

    Random randomGenerator = new Random();
    void qsort (E[] A, int i, int j){
        int pivotindex = findpivot (A, i, j); //Pick a pivot
        DSutil.swap(A, pivotindex, j);        //Stick pivot at end
        // k will be the first position in the right subarray
        int k = partition (A, i-1, j, A[j]);
        DSutil.swap(A,k,j);                 //Put pivot in place
        if ((k-i) > 1) qsort(A, i, k - 1);  //Sort left partition
        if ((j-k) > 1) qsort(A, k+1,j);     //Sort right partition
    }

    //Should change the pivot into random select
    int findpivot (E[] A, int i, int j) {
        //To give a range
        // Random.nextInt(max - min + 1) + min;
        int randomNum = (randomGenerator.nextInt(j - i + 1) + i);
        //return (i + j) / 2;
        return randomNum;
    }

    int partition (E[] A, int l, int r, E pivot){
        do {                    // Move bounds inward until they meet
            while (A[++l].compareTo(pivot) < 0);
            while ((r != 0) && (A[--r].compareTo(pivot) > 0));
            DSutil.swap(A, l, r);         // Swap out-of-place values
        }   while (l < r);              // Stop when they cross
        DSutil.swap(A, l, r);             // Reverse last, wasted swap
        return l;           //return first position in right partition
    }

    // This function is for allocating an generic array of currentSizeOfArray n
   // private static <E> E[] newArray(int length, E... array) {
    //    return Arrays.copyOf(array, length);
   // }
}
