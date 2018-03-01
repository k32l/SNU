
public class LongInt {
    private int[] backward_num;
    private int len;
    private String s;
    private boolean isNegative;
    /***
     * CONSTRUCTOR: Initializes a LongInt data type
     * @param s string of numbers
     */
    public LongInt(String s){

        // sign problem in the multiplication
        this.isNegative = false;
        if (s.charAt(0) == '-'){
            isNegative = true;
            s = s.substring(1);
        }
        // Remove extra zeroes
        s = s.replaceFirst("^0+(?!$)", "");
        this.s = s;

        // Declare the backward_num array of many integers as
        // items in the String object
        backward_num = new int[s.length()];
        len = backward_num.length;

        // Set the integer array
        for (int i = 0; i < len; i++) {
            backward_num[i] = s.charAt(len - 1 - i) - 48;
        }
    }


    public LongInt add(LongInt opnd){
        int max_len = len > opnd.len ? len : opnd.len;
        int min_len = len > opnd.len ?opnd.len : len;


        // Logic for carry
        int carry = 0;
        int sum[] = new int[max_len + 1];

        for (int k = 0; k < max_len; k++) {
            int temp_sum;

            // In case one of the arrays is longer than the other a IndexOutOfBounds exception
            // will be raised so just try one of two cases either opnd/this.backward_num is smaller
            // thus we can assume that its kth element is 0.
            if(k >= len){
                temp_sum = opnd.backward_num[k] + carry;
            }else if(k >= opnd.len){
                temp_sum = backward_num[k] + carry;
            }
            else{
                temp_sum = backward_num[k] + opnd.backward_num[k] + carry;
            }
            /*try {
                temp_sum = backward_num[k] + opnd.backward_num[k] + carry;
            }
            catch (ArrayIndexOutOfBoundsException e){
                try{
                    temp_sum = backward_num[k] + carry;
                }
                catch (ArrayIndexOutOfBoundsException ee){
                    temp_sum = opnd.backward_num[k] + carry;
                }
            }*/

            sum[k] =  temp_sum % 10;

            if(temp_sum >= 10)
                carry = 1;
            else
                carry = 0;
        }

        sum[max_len] = carry;

        String resultStr = toStringBackword(sum, max_len + 1);

        // Logic for "-" sign

        String tmpResult;

        if(this.isNegative && opnd.isNegative){
            tmpResult ="-" + resultStr;
        } else if (this.isNegative ){ //TODO: Sign for this condition

            String a;
            String b;

            a = this.s;
            b = opnd.s;

            LongInt tmp_a = new LongInt(a);
            LongInt tmp_b = new LongInt(b);

            LongInt tmp_result = tmp_a.subtract(tmp_b);
            tmpResult = tmp_result.toStringBackword();

            //To cut the '-' that appear after subtract method
            if(tmpResult.charAt(0) == '-')
                tmpResult = tmpResult.substring(1);
            tmpResult = tmpResult.trim();

            if (len > opnd.len){
                tmpResult ="-" + tmpResult;
            } else if (len == opnd.len){
                for (int i = 0; i < len; i++){
                    if (backward_num[len -1 - i] > opnd.backward_num[opnd.len -1 - i]) { //TODO check all compareTo
                        //(this.toStringBackword().compareTo(opnd.toStringBackword()) < 0)
                        //(t1.charAt(t1.length() - 1) == '0')
                        tmpResult ="-" + tmpResult;
                        break;

                    }else if (backward_num[len -1 - i] < opnd.backward_num[opnd.len -1 - i]){
                        break;
                    }
                }
            }
        } else if (opnd.isNegative){
            String a;
            String b;

            a = this.s;
            b = opnd.s;

            LongInt tmp_a = new LongInt(a);
            LongInt tmp_b = new LongInt(b);

            LongInt tmp_result = tmp_a.subtract(tmp_b);
            tmpResult = tmp_result.toStringBackword();

            //To cut the '-' that appear after subtract method
            if(tmpResult.charAt(0) == '-')
                tmpResult = tmpResult.substring(1);
            tmpResult = tmpResult.trim();

            if (len < opnd.len){
                tmpResult ="-" + tmpResult;
            } else if (len == opnd.len){
                for (int i = 0; i < len; i++){
                    if (backward_num[len -1 - i] < opnd.backward_num[opnd.len -1 - i]) { //TODO check all compareTo

                        tmpResult ="-" + tmpResult;
                        break;
                    }else if (backward_num[len -1 - i] > opnd.backward_num[opnd.len -1 - i]){
                        break;
                    }
                }

            }

        }


        else{
            tmpResult = resultStr;
        }

        System.out.println(this.toStringBackword() + "+" + opnd.toStringBackword() + "=" + resultStr);
        return new LongInt(tmpResult);
    }

    public LongInt subtract (LongInt opnd) {
        int max_len = len > opnd.len ? len : opnd.len;
        int min_len = len > opnd.len ? opnd.len : len;

        int sub[] = new int[max_len];
        int carry = 0;

        int n1[] = new int[max_len];
        int n2[] = new int[min_len];

        if (len > opnd.len) {
            n1 = backward_num;
            n2 = opnd.backward_num;
        } else if (len == opnd.len) {
            for (int i = 0; i < len; i++){
                if (backward_num[len -1 - i] < opnd.backward_num[opnd.len - 1 - i]) { //TODO check all compareTo
                    //(this.toStringBackword().compareTo(opnd.toStringBackword()) < 0)
                    //(t1.charAt(t1.length() - 1) == '0')
                    n2 = backward_num;
                    n1 = opnd.backward_num;
                    break;
                } else if (backward_num[len -1 - i] > opnd.backward_num[opnd.len -1 - i]){
                    n1 = backward_num;
                    n2 = opnd.backward_num;
                    break;
                }

            }

        } else {
            n1 = opnd.backward_num;
            n2 = backward_num;
        }


        for (int k = 0; k < max_len; k++) {
            int temp_sub;

            //Logic for remainder
            int remainder = 0;
            if ((k < min_len) && ((n1[k] - carry) < n2[k])) {
                remainder = 10;
            } else {
                remainder = 0;
            }

            //calculation of sub

            /* if (n1[k] == 0 && carry == 1) {
            	temp_sub = 9;
            	remainder = 10;
            }else */

            if (k >= min_len) {
                if (n1[k] == 0 && carry == 1) {
                    temp_sub = 9;
                    remainder = 10;
                } else
                    temp_sub = n1[k] - carry;
            } else {
                temp_sub = (n1[k] + remainder - carry) - n2[k];
                System.out.println("temp_sub: " + temp_sub + " n1: " + n1[k] + " remainder: " + remainder +
                        " carry: " + carry + " n2: " + n2[k] + "	and k = " + k);
            }
            //Logic for carry

            sub[k] = temp_sub % 10;
            if (n1[k] + remainder >= 10)
                carry = 1;
            else
                carry = 0;
        }


        String resultStr = toStringBackword(sub, max_len);

        /*
        //Logic for sign without additional sign
        if (len < opnd.len)
            resultStr = "-" + resultStr;


        if (len == opnd.len) {
            if (this.toStringBackword().compareTo(opnd.toStringBackword()) < 0) {
                String tmpStr = "-";
                resultStr = tmpStr + resultStr;
            }
        }

        */
        //Logic for sign with additional sign
        // Logic for "-" sign

        String tmpResult = "-";
        if (!this.isNegative && !opnd.isNegative){
            if (len < opnd.len) {
                resultStr = "-" + resultStr;
            } else if (len == opnd.len){
                for (int i = 0; i < len; i++){
                    if (backward_num[len -1 - i] < opnd.backward_num[opnd.len -1 - i]) { //TODO check all compareTo

                        resultStr = "-" + resultStr;
                        break;

                    } else if (backward_num[len -1 - i] > opnd.backward_num[opnd.len -1 - i]){
                        break;
                    }
                }
            }
            //System.out.println(this.toStringBackword() + " compareTo " + opnd.toStringBackword() + " is " +
            //	this.toStringBackword().compareTo(opnd.toStringBackword()));

        }

        if (this.isNegative && opnd.isNegative) {

            if (len > opnd.len) {
                String a;
                String b;

                a = this.s;
                b = opnd.s;

                LongInt tmp_a = new LongInt(a);
                LongInt tmp_b = new LongInt(b);

                LongInt tmp_result = tmp_a.subtract(tmp_b);
                tmpResult = tmp_result.toStringBackword();

                //To cut the '-' that appear after subtract method
                if(tmpResult.charAt(0) == '-')
                    tmpResult = tmpResult.substring(1);
                tmpResult = tmpResult.trim();

                tmpResult = "-" + tmpResult;

            } else if (len == opnd.len) {
                for (int i = 0; i < len; i++){
                    if (backward_num[len -1 - i] < opnd.backward_num[opnd.len -1 - i]) { //TODO check all compareTo

                        String a;
                        String b;

                        a = this.s;
                        b = opnd.s;

                        LongInt tmp_a = new LongInt(a);
                        LongInt tmp_b = new LongInt(b);

                        LongInt tmp_result = tmp_a.subtract(tmp_b);
                        tmpResult = tmp_result.toStringBackword();

                        //To cut the '-' that appear after subtract method
                        if(tmpResult.charAt(0) == '-')
                            tmpResult = tmpResult.substring(1);
                        tmpResult = tmpResult.trim();

                        //tmpResult = "-" + tmpResult;
                        break;
                    }else if (backward_num[len -1 - i] > opnd.backward_num[opnd.len -1 - i]){
                        String a;
                        String b;

                        a = this.s;
                        b = opnd.s;

                        LongInt tmp_a = new LongInt(a);
                        LongInt tmp_b = new LongInt(b);

                        LongInt tmp_result = tmp_a.subtract(tmp_b);
                        tmpResult = tmp_result.toStringBackword();

                        //To cut the '-' that appear after subtract method
                        if(tmpResult.charAt(0) == '-')
                            tmpResult = tmpResult.substring(1);
                        tmpResult = tmpResult.trim();

                        tmpResult = "-" + tmpResult;
                        break;
                    }
                }
                /*if (this.toStringBackword().compareTo(opnd.toStringBackword()) > 0) { //TODO check compareTo
                    String a;
                    String b;

                    a = this.s;
                    b = opnd.s;

                    LongInt tmp_a = new LongInt(a);
                    LongInt tmp_b = new LongInt(b);

                    LongInt tmp_result = tmp_a.subtract(tmp_b);
                    tmpResult = tmp_result.toStringBackword();
                    tmpResult = "-" + tmpResult;

                } else {
                    String a;
                    String b;

                    a = this.s;
                    b = opnd.s;

                    LongInt tmp_a = new LongInt(a);
                    LongInt tmp_b = new LongInt(b);

                    LongInt tmp_result = tmp_a.subtract(tmp_b);
                    tmpResult = tmp_result.toStringBackword();
                } */
            } else {
                String a;
                String b;

                a = this.s;
                b = opnd.s;

                LongInt tmp_a = new LongInt(a);
                LongInt tmp_b = new LongInt(b);

                LongInt tmp_result = tmp_a.subtract(tmp_b);

                tmpResult = tmp_result.toStringBackword();

                //To cut the '-' that appear after subtract method
                if(tmpResult.charAt(0) == '-')
                    tmpResult = tmpResult.substring(1);
                tmpResult = tmpResult.trim();
            }


        } else if (this.isNegative) {
            String a;
            String b;

            a = this.s;
            b = opnd.s;

            LongInt tmp_a = new LongInt(a);
            LongInt tmp_b = new LongInt(b);

            LongInt tmp_result = tmp_a.add(tmp_b);
            tmpResult = tmp_result.toStringBackword();

            //To cut the '-' that appear after subtract method
            if(tmpResult.charAt(0) == '-')
                tmpResult = tmpResult.substring(1);
            tmpResult = tmpResult.trim();

            tmpResult = "-" + tmpResult;
        } else if (opnd.isNegative) {
            String a;
            String b;

            a = this.s;
            b = opnd.s;

            LongInt tmp_a = new LongInt(a);
            LongInt tmp_b = new LongInt(b);

            LongInt tmp_result = tmp_a.add(tmp_b);



            tmpResult = tmp_result.toStringBackword();


        } else {
            tmpResult = resultStr;
        }

        System.out.println(this.toStringBackword() + "-" + opnd.toStringBackword() + "=" + resultStr);
        return new LongInt(tmpResult);

    }

    public String toStringBackword(){
        String tmp = toStringBackword(backward_num, len);
        if(this.isNegative)
            return new String("-" + tmp);
        else
            return tmp;
    }

    private String toStringBackword(int vec[], int len) {
        StringBuilder strBldr = new StringBuilder();

        for (int i = len - 1; i >= 0; i--) {
            strBldr.append(vec[i]);
        }
        return strBldr.toString();
    }

    public void print(){
        System.out.print(this.toStringBackword());
    }

    public LongInt multiplyTwoNumbersWithOneDigit(LongInt opnd){
        int max_len = len > opnd.len ? len : opnd.len;
        int min_len = len > opnd.len ? opnd.len : len;

        int sumAll[] = new int[max_len + 1];
        int carry = 0;

        int n1[] = new int[max_len];
        int n2[] = new int[min_len];

        if (len > opnd.len) {
            n1 = backward_num;
            n2 = opnd.backward_num;
        }  else {
            n1 = opnd.backward_num;
            n2 = backward_num;
        }

        for (int k = 0; k < max_len; k++){

            int temp_mult = 0;

            if (k < max_len){
                temp_mult = n1[k] * n2[0] + carry;
            }

            sumAll[k] = temp_mult % 10;
            carry = temp_mult / 10;
        }
        sumAll[max_len] = carry;
        String resultStr = toStringBackword(sumAll, max_len + 1);
        return new LongInt(resultStr);
    }
    // multip Algorithm
    private String multip(String t1,String t2){


        //TODO: Write the logic for multiplication with '0'

        if (t1.length() == 1 || t2.length() == 1){
            if (t1.charAt(0) == '0' || t2.charAt(0) == '0'){
                return 0 + "";
            }
        }

        if (t1.length() == 1 || t2.length() == 1){
            if (t1.charAt(0) == '1' || t2.charAt(0) == '1'){
                if (t1.length() == 1 && t1.charAt(0) == '1' )
                    return t2;
                else if (t2.length() == 1 && t2.charAt(0) == '1')
                    return t1;
            }
        }


        //Logic for multiplication of 2 numbers, where 1 of them consists of one digit
        if (t1.length() == 1 || t2.length() == 1){ //Check if it is sensitive to the method above with '1'
            LongInt longT1 = new LongInt(t1);
            LongInt longT2 = new LongInt(t2);

            LongInt tmp_result = longT1.multiplyTwoNumbersWithOneDigit(longT2);


            return tmp_result.toStringBackword(); //not sure if it will work

        }

        if(t1.length()<=9&&t2.length()<=9)

        {
            return (Long.parseLong(t1)*Long.parseLong(t2))+"";
        }


        else
        {
            //tried to implement method for 0 string inside Karatsuba
            if ((t1.charAt(t1.length() - 1) == '0') || (t2.charAt(t2.length() - 1) == '0')){
                int t1_len = t1.length();
                int t2_len = t2.length();
                String a;
                String b;
                int left_a;
                int left_b;

                if (t1.charAt(t1.length() - 1) == '0') {
                    if (t1.length() > 1){
                        int i = t1.length() - 1;
                        for (int k = 0; k < i ; k++){
                            if (t1.charAt(t1.length() - 1 - k) == '0'){
                                t1_len--;
                                if ((t1.charAt(t1.length() - 2 - k) != '0'))
                                    break;
                            }
                        }
                    }
                    String temp_a = t1;
                    a = temp_a.substring(0, t1_len);
                } else {
                    a = t1;
                }

                left_a = t1.length() - t1_len;
                if (t2.charAt(t2.length() - 1) == '0') {
                    if (t2.length() > 1){
                        int i = t2.length() - 1;
                        for (int k = 0; k < i ; k++){
                            if (t2.charAt(t2.length() - 1 - k) == '0'){
                                t2_len--;
                                if ((t2.charAt(t2.length() - 2 - k) != '0'))
                                    break;
                            }
                        }
                    }
                    String temp_b = t2;
                    b = temp_b.substring(0, t2_len);
                } else {
                    b = t2;
                }
                left_b = t2.length() - t2_len;

                System.out.println("left_a: " + left_a);
                System.out.println("left_b: " + left_b);

                String tmpResult = multip(a,b);

                while (left_a != 0){
                    tmpResult += "0";
                    left_a--;
                }
                while (left_b != 0){
                    tmpResult += "0";
                    left_b--;
                }

                System.out.println("tmpResult after x by o's: " + tmpResult);
                return tmpResult;
            }
            //to find the length of the min base
            int max_len = t1.length() > t2.length() ? t1.length() : t2.length();
            int min_len = t1.length() > t2.length() ? t2.length() : t1.length();


            String maxStr;
            String minStr;

            if(t1.length() >= t2.length()){
                maxStr = t1;
                minStr = t2;
            } else {
                maxStr = t2;
                minStr = t1;
            }



            //base is found here
            System.out.println("maxStr is " + maxStr);
            String tempBase;
            if (min_len > 6){
                tempBase = Integer.toString((int) Math.pow(10, 4));
            } else {
                tempBase = Integer.toString((int) Math.pow(10, min_len - 1));
                System.out.println("tempBase = " + tempBase);
            }

            //new assingment
            //          int mid1=t1.length()/2;
            //           int mid2=t2.length()/2;
            System.out.println("t1 is " + t1 + " t2 is " + t2);
            String low1=t1.substring(0, t1.length() - tempBase.length() + 1);//Al
            String high1=t1.substring(t1.length() - tempBase.length() + 1, t1.length());//Ar
            String low2=t2.substring(0, t2.length() - tempBase.length() + 1);//Bl
            String high2=t2.substring(t2.length() - tempBase.length() + 1, t2.length());//Br

            System.out.println("low1= " + low1 + " high1= " + high1 + " low2= " + low2 + " high2= " + high2);

            LongInt longLow1 = new LongInt(low1);
            LongInt longHigh1 = new LongInt(high1);
            LongInt longLow2 = new LongInt(low2);
            LongInt longHigh2 = new LongInt(high2);

            //code has a problem with the base
            String z1=multip(low1, low2);

            String z2=multip(longLow1.add(longHigh1).toStringBackword(),
                    longLow2.add(longHigh2).toStringBackword());
            //System.out.println("z1: " + z1 + " z2: " + z2 );
            //System.out.println("high1 " + high1 + "high2 " + high2);
            String z3=multip(longHigh1.toStringBackword(), longHigh2.toStringBackword());
            System.out.println("z1 is " + z1 + " z2 is " + z2 + " z3 is " + z3);
            String tt1, tt2, tt3, tt4;
            //here is the error
            tt1 = multip(z1, Integer.toString((int)Math.pow(10, (tempBase.length() - 1) * 2)));
            System.out.println("tt1 " + tt1);
            LongInt longz1 = new LongInt(z1);
            LongInt longz2 = new LongInt(z2);
            LongInt longz3 = new LongInt(z3);
            tt2 = multip((longz2.subtract(longz3).subtract(longz1).toStringBackword()), Integer.toString((int)Math.pow(10, tempBase.length() - 1)));
            System.out.println("tt1= " + tt1 + " tt2= " + tt2 + " z3 = " + z3);

            LongInt longt1 = new LongInt(tt1);
            LongInt longt2 = new LongInt(tt2);
            LongInt longt3 = new LongInt(z3);

            LongInt result = (longt1.add(longt2).add(longt3));


            return result.toStringBackword();
        }

    }

    public LongInt multiply(LongInt opnd){


        String a;
        String b;

        a = this.s;
        b = opnd.s;


        String tmpResult = multip(a,b);
        String resultStr = "-";

        if(this.isNegative && opnd.isNegative){
            resultStr = tmpResult;
        } else if (this.isNegative || opnd.isNegative){
            resultStr += tmpResult;
        }
        else{
            resultStr = tmpResult;
        }



        System.out.println("sign in method multiply " + this.isNegative + "string: " + resultStr);
        System.out.println(this.toStringBackword() + "*" + opnd.toStringBackword() + "=" + resultStr);
        return new LongInt(resultStr);

    }

    public static void main(String[] args){
        LongInt n1 = new LongInt("99999999999999999999999999999999999999999999999999999999999999999999999999999");
        LongInt n2 = new LongInt("10101");



        //LongInt sum = n1.add(n2);
        // LongInt sum2= n2.add(n1);
        //System.out.println(n1.add(n2).add(n2).add(n2).toStringBackword());
        LongInt mult = n1.multiply(n2);
        // LongInt sub = n1.subtract(n2);

        //System.out.println("\nSum results:\n-------------\n");

        System.out.println("Mult: " + mult.toStringBackword());
        //System.out.println("sub : " + sub.toStringBackword());
        //System.out.println("Sum 1: " + sum.toStringBackword());
        //sum.print();
        //System.out.println("Sum 2: " + sum2.toStringBackword());
    }
}
