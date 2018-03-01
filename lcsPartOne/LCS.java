/**
 * Created by Evgenii on 2016. 5. 28..
 */
import com.sun.org.apache.bcel.internal.generic.SWAP;

import java.util.Scanner;
public class LCS {

    public static String geneA = "";
    public static String geneB = "";

    public static int firstIndex;
    public static int secondIndex;
    public static int finalMaxString;


    public static int maximum(int x, int y){
        if (x > y)
            return x;
        else
            return y;
    }

    public static int findWithSpaces(char[] A, char[] B) {
        int[][] LCS = new int[A.length + 1][B.length + 1];
        String[][] solution = new String[A.length + 1][B.length + 1];
        // if A is null then LCS of A, B =0
        for (int i = 0; i <= B.length; i++) {
            LCS[0][i] = 0;
            solution[0][i] = "0";
        }

        // if B is null then LCS of A, B =0
        for (int i = 0; i <= A.length; i++) {
            LCS[i][0] = 0;
            solution[i][0] = "0";
        }

        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                    solution[i][j] = "diagonal";
                } else {
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                    if (LCS[i][j] == LCS[i - 1][j]) {
                        solution[i][j] = "top";
                    } else {
                        solution[i][j] = "left";
                    }
                }
            }
        }
        // below code is to just print the result
        String x = solution[A.length][B.length];
        //String answer = "";
        String answerA = "";
        String answerB = "";
        int a = A.length;
        int b = B.length;
        while (x != "0") {
            if (solution[a][b] == "diagonal") {
               // answer = A[a - 1] + answer;
                answerA = A[a - 1] + answerA;
                answerB = B[b - 1] + answerB;
                a--;
                b--;
            } else if (solution[a][b] == "left") {
                answerB = "_" + answerB;
                b--;
            } else if (solution[a][b] == "top") {
                answerA = "_" + answerA;
                a--;
            }
            x = solution[a][b];
        }
        //System.out.println(answerA);

        //For empty spaces in the beggining
        if (answerA.length() < answerB.length()){
            while (answerA.length() != answerB.length()){
                answerA = "_" + answerA;
            }

        } else if (answerA.length() > answerB.length()){
            while (answerA.length() != answerB.length()){
                answerB = "_" + answerB;
            }
        }

            geneA = answerA;
            geneB = answerB;


        return LCS[A.length][B.length];
    }

    public static int findWithoutSpaces(char[] A, char[] B) {
        int[][] LCS = new int[A.length + 1][B.length + 1];
        String[][] solution = new String[A.length + 1][B.length + 1];
        // if A is null then LCS of A, B =0
        for (int i = 0; i <= B.length; i++) {
            LCS[0][i] = 0;
            solution[0][i] = "0";
        }

        // if B is null then LCS of A, B =0
        for (int i = 0; i <= A.length; i++) {
            LCS[i][0] = 0;
            solution[i][0] = "0";
        }

        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                    solution[i][j] = "diagonal";
                } else {
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                    if (LCS[i][j] == LCS[i - 1][j]) {
                        solution[i][j] = "top";
                    } else {
                        solution[i][j] = "left";
                    }
                }
            }
        }
        // below code is to just print the result
        String x = solution[A.length][B.length];
        //String answer = "";
        String answerA = "";
        String answerB = "";
        int a = A.length;
        int b = B.length;
        while (x != "0") {
            if (solution[a][b] == "diagonal") {
                // answer = A[a - 1] + answer;
                answerA = A[a - 1] + answerA;
                answerB = B[b - 1] + answerB;
                a--;
                b--;
            } else if (solution[a][b] == "left") {

                b--;
            } else if (solution[a][b] == "top") {

                a--;
            }
            x = solution[a][b];
        }



        geneA = answerA;
        geneB = answerB;

        return LCS[A.length][B.length];
    }

    public static void functionItself(String A, String B, int geneA_int, int geneB_int, String Input){

        String geneA_str = A.substring(geneA_int, geneA_int+ 10);
        String geneB_str = B.substring(geneB_int, geneB_int + 10);
        for (int x= 0; x < A.length() - 10; x += 5){

            int stringLegth = 0;
            int tempStringLength = 0;
            int maxString = 0;
            int tempMaxString = 0;

            geneA_int = x;
           geneA_str = A.substring(geneA_int, geneA_int+ 10);
            for (int y =5; y < B.length() - 10; y += 5 ){

                geneB_int = y;
                geneB_str = B.substring(geneB_int, geneB_int + 10);

                stringLegth = findWithoutSpaces(geneA_str.toCharArray(), geneB_str.toCharArray());
                //System.out.println("LCS: " + stringLegth);

                maxString = maximum(stringLegth, tempStringLength);
                tempStringLength = stringLegth;

                if (maxString > tempMaxString){
                    firstIndex = x;
                    secondIndex = y;
                    finalMaxString = maxString;
                }

                tempMaxString = maxString;



                if (Input.charAt(0) == 'A'){
                    System.out.println("A" + x + ",B" + y);
                } else if (Input.charAt(0) == 'B'){
                    System.out.println("B" + x + ",A" + y);
                }

                System.out.println(geneA);
                System.out.println(geneB);
                System.out.println("finalMaxString is " + finalMaxString + " firstIdx " + firstIndex + " second " + secondIndex);


            }
            String FinalgeneA_str = A.substring(firstIndex, firstIndex+ 10);
            String FinalgeneB_str = B.substring(secondIndex, secondIndex + 10);
            findWithSpaces(FinalgeneA_str.toCharArray(), FinalgeneB_str.toCharArray());
            //printing current input
            if (Input.charAt(0) == 'A'){
                System.out.println("A" + firstIndex + ",B" + secondIndex);
            } else if (Input.charAt(0) == 'B'){
                System.out.println("B" + firstIndex + ",A" + secondIndex);
            }
            System.out.println(geneA);
            System.out.println(geneB);

        }
    }

    public static void main(String[] args) {
        String A = "AGTCAGGGCGAAAAAAAGAGTCAGGGC";
        String B = "TCGATCTGCGGCGGCGGCAGCCCCCGA";
       // System.out.println("LCS :" + find(A.toCharArray(), B.toCharArray()));
        System.out.println("String A length is " + A.length());
        System.out.println();
        //Scanner keyboard = new Scanner(System.in);
        String str = "A0,B5";

        String Input = str;
        //System.out.println(Input);

        //Code for extracting numbers from string
        str = str.replaceAll("[^0-9]+", " ");


        if (str.charAt(0) == ' ')
        {
            str = str.substring(1);
        }

        String[] parts = str.split(" ");
        int[] n1 = new int[parts.length];
        for(int n = 0; n < parts.length; n++) {
            n1[n] = Integer.parseInt(parts[n]);
        }

        //1st number is in geneA_int, 2nd is in geneB_int
        int geneA_int = n1[0];
        int geneB_int = n1[1];

        functionItself(A, B, geneA_int, geneB_int, Input);

        str = "B0,A5";
        Input = str;

        str = str.replaceAll("[^0-9]+", " ");


        if (str.charAt(0) == ' ')
        {
            str = str.substring(1);
        }

         parts = str.split(" ");
         n1 = new int[parts.length];
        for(int n = 0; n < parts.length; n++) {
            n1[n] = Integer.parseInt(parts[n]);
        }

        //1st number is in geneA_int, 2nd is in geneB_int
         geneA_int = n1[0];
         geneB_int = n1[1];


        //if compare geneB with geneA
        if (Input.charAt(0) == 'B'){
            String temp;
            temp = A;
            A = B;
            B = temp;
        }

        functionItself(A, B, geneA_int, geneB_int, Input);



        //System.out.println(geneA_str);
        //System.out.println(geneB_str);

    }

}