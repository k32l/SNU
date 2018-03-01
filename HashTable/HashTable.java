/**
 * Created by Evgenii on 15. 12. 3..
 */
public class HashTable {

    int NOT_VALID = -1;
    int [] theArray;
    int jumpBy;
    int arraySize;
    String method;
    int updatedCount = 1, count = 1;
    int c1 = 0, c2 = 0, c3 = 0;

    public HashTable(int n) {
        jumpBy = 0;
        arraySize = n;
        theArray = new int[arraySize];
        for (int i = 0; i < arraySize; i++){
            theArray[i] = -1;
        }

        // fill your code
    }

    public void Start(String policy, int constant1, int constant2, int constant3){
        c1 = constant1;
        c2 = constant2;
        c3 = constant3;
        method = policy;
        if(policy.equals("linear")) {
            jumpBy = constant1;
        }
        else if(policy.equals("quadratic")) { //TO DO: Fix to c1*i^2 + c2*i + c3

            for (int i = 1;  i <= updatedCount; i++) {
                jumpBy = constant1 * (i * i) + constant2 * i + constant3;

            }
        }
    }


    public void Insert(int value) {

        int key = value % arraySize;
        int initialKey = key;

        while (!doInsert(key, value)){
            key = updateKey(key, initialKey);
            if (key == NOT_VALID){
                System.out.print("Cannot insert.");
                return;
            }
        }
    }

    private boolean doInsert(int key, int value){
        if(theArray[key] != NOT_VALID) {
            count++;
            if (count > 2) {
                updatedCount++;
            }
            return false;
        } else{
            theArray[key] = value;
            updatedCount = 1;
            count = 1;
            return true;
        }
    }

    private int updateKey(int oldKey, int initialKey){
        Start(method, c1, c2, c3);
        if (count > 2)
            oldKey = initialKey;
        if((oldKey + jumpBy) >= arraySize){ // wrap
            // oldKey = jumpBy - (arraySize - oldKey);
            oldKey =  (oldKey + jumpBy) - arraySize;
        } else {
            oldKey += jumpBy;
        }

        if (initialKey == oldKey){
            return NOT_VALID;
        }

        return oldKey;
    }

    public int find(int value) {
        int key = value % arraySize;
        int initialKey = key;
        while(theArray[key] != value){
            key = updateKey(key, initialKey);
            if (key == NOT_VALID){
                System.out.println("Cannot Find");
                break;
            }
        }
        return key;

    }


}

