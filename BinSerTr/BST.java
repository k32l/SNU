import com.sun.xml.internal.bind.v2.TODO;

import java.util.ArrayList;

/**
 * Created by Evgenii on 8/27/15.
 * Here's a program I want to make. It will accept a text file
 * as a COMMAND LINE ARGUMENT and store each word (accepting ALL punctuation
 * as if it were a space character, including apostrophe's) in a binary tree.
 * Each node in the tree stores an ArrayList which holds the number
 * of times that word occurs(first element) and each position in
 * the text file that it occurs (all following elements).
 * The user is shown the total number of words, and you can search for a
 * word to see how many times and where in the file it is stored. Enjoy!

 */
public class BST {
    //TODO:Added by TA
    protected boolean NOBSTified = false;
    protected boolean OBSTified = false;

    Node root;
    //int nodeCount;

    int wordPosition;           //Total number of words in file
    //int uniqueWordCount = 0;    //number of unique words in the file
    int highestCount;           //how many times the word was used

    String mostUsedWord;




    //TODO:Added by TA
    //Constructor initialize root to null
    public BST() {
        root = null;
      //  nodeCount = 0;

    }

    /**Constructor initializes root to parameter value
      * @param theRoot the root of this BST object
      */
    public BST (Node theRoot){
        root = theRoot;
    }

    /**
     * Constructor: initialize root to hold nodes containing the words
     * and data from the parameter file.
     * @param text the input file containing words
     */
    /*
    public BST (File text)
    {
        try{
            BufferedReader bR = new BufferedReader(new FileReader(text));
            root = readBinaryTree(bR);
        }
        catch(IOException e)
        {
            System.out.println("Error reading file. Exiting.");
            System.exit(1);
        }
    }
    */

    //TODO:The size() method returns the number of keys in the tree.

    public int size() { }

    /** TODO: The insert() method inserts a key into the tree
     * using the standard BST insertion algorithm.
     * If the same key exists already in the tree,
     * increment its frequency by one without inserting
     * the key itself into the tree.
     * Otherwise, insert the key and set the frequency to one. */

    // @param item the item to add to the BST
    public void insert(String key) {
        root = add(root, key);
    }

    private Node add (Node localRoot,String key) {
        if (localRoot == null) // item is not in a tree
        {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(1);
            temp.add(wordPosition);
            return new Node(key, temp);

        }
        else if (key.compareTo(localRoot.word) == 0){
            localRoot.countAndPos.set(0, localRoot.countAndPos.get(0) + 1);
            localRoot.countAndPos.add(wordPosition);
            return localRoot;
        } else if (key.compareTo(localRoot.word) < 0){ //item < localRootData
            localRoot.leftTree = add(localRoot.leftTree, key);
            return localRoot;
        } else { // item > localRootData
            localRoot.rightTree = add(localRoot.rightTree, key);
            return localRoot;
        }

        
    }


    /**
     * TODO:The find() method probes the tree to find a search key.
     * A Boolean value true is returned if the key is found.
     * Otherwise, false is returned. In either case,
     * the find() method increments the access count by one for
     * all the nodes probed (i.e., all the nodes on the search path from the root).
     */
    public boolean find(String key) {
    }



    //TODO:The sumFreq() method returns the frequency sum of all the keys in the tree
    public int sumFreq() { }

    //TODO: sumProbes() method returns the access count sum of all the keys in the tree
    public int sumProbes() { }

    //TODO: The sumWeightePdPath() method returns the sum of weighted path
    // lengths of the tree, which can be formulated by ni=1 wili,
    // where wi and li are the weight and one plus the level of
    // the ith node, respectively.
    public int sumWeightedPath() { }

    //TODO: The resetCounters() method resets both the frequencies
    // and access counts of all keys in the tree to zero.

    public void resetCounters() { }

    //TODO:The obst() and nobst() methods convert the tree
    // from a plain BST into an Optimal BST (OBST) and
    // a Nearly Optimal BST (NOBST), respectively.
    // he frequencies of keys are used as weights.

    public void nobst() { }	// Set NOBSTified to true.
    public void obst() { }	// Set OBSTified to true.

    //TODO: The print() method prints the keys in the tree in
    // the increasing order of their values. Each key should
    // appear on a separate line in the format of
    // [key:frequency:access count].
    public void print() { }
}


/**
 * This class holds a node for a binary search tree. The node includes
 * a pointer to it's left subtree and it's right subtree, as well as String
 * data, and an ArrayList containing the number of occurences of the
 * data in the user's input file in the first position, followed by the
 * word number in each following position.
 */

class Node {

        String word;
        Node leftTree;
        Node rightTree;
        //Need to implemetn ArraList but for now just want
        //to implement the BST using it
        ArrayList<Integer> countAndPos;

        /**
        * Constructor: no arg constructor must never be used to avoid
        * confusion between a null node element and a null element content.
         */
        public Node()
        {
            System.out.println("Cannot creat empty node, sorry.");
        }

        /**
         * Constructor: initializes parent, word, and countAndPos instance
         * variables.
         * @param theWord the word to be stored in this node
         * @param theCountAndPos an ArrayList containing this node's word's
         * count and position's
         */
        public Node(String theWord, ArrayList<Integer> theCountAndPos)
        {
            leftTree = null;
            rightTree = null;
            word = theWord;
            countAndPos = theCountAndPos;
        }

        /*
        public Node (String key){
            this.key = key;
        }

        public String toString(){
            return "has the key" + key;
        } */

    }