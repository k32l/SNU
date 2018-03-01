/**
 * Created by Evgenii on 15. 11. 24..
 */

public class Sorter{
    public int currentSizeOfArray;
    public Integer[] array;
    Quicksort qsort = new Quicksort();

    public Sorter(int n)
    {
        currentSizeOfArray = 0;
        array = new Integer[n];
    }

    public void add(int value){
        assert currentSizeOfArray <= array.length;
        array[currentSizeOfArray++] = value;
    }

    public boolean remove(int value){
        int found = binary(value);
        if (found == -1 )
        {
            //System.out.println("Element is ont in array");
            return false;
        } else {
            currentSizeOfArray--;
            for (int i = found; i < currentSizeOfArray; ++i)
            {
                array[i] = array[i+1];
            }
            return true;
        }



    }

    public void sort(String type){
        qsort.qsort(array, 0, currentSizeOfArray - 1);
        if (type.equals("ascend"))
        {

            System.out.print("SORT:");
            for (int i = 0; i < currentSizeOfArray ; i++) {

                System.out.print(" " + array[i]);
            }
            System.out.println();
        } else if (type.equals("descend"))
        {

            System.out.print("SORT:");
            for (int i = currentSizeOfArray - 1; i >= 0 ; i--) {

                System.out.print(" " + array[i]);
            }
            System.out.println();
        }
        //fill your code

    }

    public void top(int k, String type){
        qsort.qsort(array, 0, currentSizeOfArray - 1);

        //fill your code
        if (type.equals("smallest")){

            System.out.print("TOP:");
            for (int i = 0; i < k ; i++) {
                System.out.print(" " + array[i]);
            }
            System.out.println();
        } else if (type.equals("largest"))
        {

            System.out.print("TOP:");
            for (int i = currentSizeOfArray - 1; i >=currentSizeOfArray - k; i--) {
            System.out.print(" " + array[i]);
            }
            System.out.println();
        }

    }

    //Binary Search
    int binary(int k){
        int l = -1;
        int r = currentSizeOfArray; // l and r are beyond array bounds
        while (l + 1 != r ){  //Stop when l and r meet
            int i = (l+r)/2; //Check middle of remaining subarray
            if (k < array[i]) r = i; //In left half
            if (k == array[i]) return i; // Found it
            if (k > array[i]) l = i;    //In right half
        }
        return -1; // Search value not in A
    }
}

